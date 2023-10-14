package com.generator.lyrics.converter;

import com.generator.lyrics.transliterate.TeluguToEnglishTransliterator;
import com.ibm.icu.text.Transliterator;
import io.micrometer.common.util.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.generator.lyrics.transliterate.JavaScriptTransliterator.getTransliteratedText;

public class HtmlToXlsxConverter {

    private static final TeluguToEnglishTransliterator teluguToEnglishTransliterator = new TeluguToEnglishTransliterator();

    public static void convert() throws IOException {
        String htmlContent = readHTMLFileToString("html/songsPage.html");
        System.out.println(htmlContent);

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Songs");

            Document doc = Jsoup.parse(htmlContent, "UTF-8");

            Elements songs = doc.select("div[data-role=page]");

            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 15 * 256);
            sheet.setColumnWidth(2, 100 * 256);
            sheet.setColumnWidth(3, 100 * 256);
            sheet.setColumnWidth(4, 100 * 256);
            sheet.setColumnWidth(5, 100 * 256);
            sheet.setColumnWidth(6, 100 * 256);
            // Create a cell style with text wrapping enabled
            CellStyle wrapStyle = workbook.createCellStyle();
            wrapStyle.setWrapText(true);

            Row headerRow = sheet.createRow(0);
            headerRow.setHeightInPoints(15);
            Cell headerCell0 = headerRow.createCell(0);
            headerCell0.setCellValue("S.No");
            headerCell0.setCellStyle(wrapStyle);
            Cell headerCell1 = headerRow.createCell(1);
            headerCell1.setCellValue("Song Number");
            headerCell1.setCellStyle(wrapStyle);
            Cell headerCell2 = headerRow.createCell(2);
            headerCell2.setCellValue("Telugu Song Lyrics");
            headerCell2.setCellStyle(wrapStyle);
            Cell headerCell3 = headerRow.createCell(3);
            headerCell3.setCellValue("Telugu Title");
            headerCell3.setCellStyle(wrapStyle);
            Cell headerCell4 = headerRow.createCell(4);
            headerCell4.setCellValue("English Transliterated Title");
            headerCell4.setCellStyle(wrapStyle);
            Cell headerCell5 = headerRow.createCell(5);
            headerCell5.setCellValue("English Transliterated Lyrics");
            headerCell5.setCellStyle(wrapStyle);
            Cell headerCell6 = headerRow.createCell(6);
            headerCell6.setCellValue("Display Title");
            headerCell6.setCellStyle(wrapStyle);

            System.out.println("----------------------------------------------------------------------------------");

            int rowNum = 1;
            for (Element song : songs) {

                Node prev = Objects.requireNonNull(song.previousSibling()).previousSibling();
                String songLyricsText = song.select("div[data-role=main]").html();

                if (prev instanceof Comment comment) {
                    // Get the comment text
                    String text = comment.getData();
                    // Use a regular expression to extract the value between dashes
                    Pattern pattern = Pattern.compile("SONG\\s\\d+");
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        // Print out the value
                        String songNumber = matcher.group(0);
                        //System.out.println(songNumber);
                        Row row = sheet.createRow(rowNum++);

                        Cell serialNoCell = row.createCell(0);
                        serialNoCell.setCellValue(rowNum - 1);
                        serialNoCell.setCellStyle(wrapStyle);

                        Cell songNumberCell = row.createCell(1);
                        songNumberCell.setCellValue(matcher.group(0));
                        songNumberCell.setCellStyle(wrapStyle);

                        Cell lyricsCell = row.createCell(2);
                        lyricsCell.setCellValue(songLyricsText);
                        lyricsCell.setCellStyle(wrapStyle);

                        Cell teluguTitleCell = row.createCell(3);
                        String teluguTitleString = getTeluguTitleString(songLyricsText);
                        teluguTitleCell.setCellValue(teluguTitleString);
                        teluguTitleCell.setCellStyle(wrapStyle);

                        Cell englishTitleCell = row.createCell(4);
                        //String translitEnglishTitleString = getTeluguToEnglishTransliteratedString(teluguTitleString);
                        String translitEnglishTitleString = getTransliteratedText(teluguTitleString).replaceAll("\n","");
                                englishTitleCell.setCellValue(translitEnglishTitleString);
                        englishTitleCell.setCellStyle(wrapStyle);

                        Cell englishLyricsCell = row.createCell(5);
                        //String translitEnglishLyricsString = getTeluguToEnglishTransliteratedString(songLyricsText);
                        String translitEnglishLyricsString = getTransliteratedText(songLyricsText);
                                englishLyricsCell.setCellValue(translitEnglishLyricsString);
                        englishLyricsCell.setCellStyle(wrapStyle);

                        Cell displayTitleCell = row.createCell(6);
                        String displayTitleString = getDisplayTitleString(matcher.group(0), teluguTitleString,
                                translitEnglishTitleString);
                        displayTitleCell.setCellValue(displayTitleString);
                        displayTitleCell.setCellStyle(wrapStyle);

                        BigDecimal bigDecimalRowNum = new BigDecimal(rowNum - 1);
                        BigDecimal songsSize = new BigDecimal(songs.size());
                        BigDecimal oneHundred = new BigDecimal("100");
                        BigDecimal completionPercentage =
                                bigDecimalRowNum.divide(songsSize, 4, RoundingMode.HALF_UP).multiply(oneHundred);
                        //((rowNum - 1)/songs.size())*100;
                        System.out.print("\rProgress: " +completionPercentage+"%");
                    }
                }

            }
            // Write to Excel file
            workbook.write(new FileOutputStream("Songs.xlsx"));
            System.out.print("\rProgress: " +100+"%");
            System.out.println("\n\nFinished!");
        }


    }

    private static String getTeluguTitleString(String songLyricsText) {
        String teluguTitleString = null;
        // Define the regular expression pattern to match text between <span> and <br>
        Pattern pattern = Pattern.compile("<span>(.*?)<br>");
        // Create a Matcher to find the pattern in the input string
        Matcher matcher = pattern.matcher(songLyricsText);
        // Check if the pattern is found and extract the text
        if (matcher.find()) {
            teluguTitleString = matcher.group(1);
        } else {
            System.out.println("No match found.");
        }
        return teluguTitleString;
    }

    private static String getTranslitEnglishTitleString(String teluguTitleString) {
        // Create transliterator objects for Telugu to English and Telugu to Hindi
        Transliterator teluguToEnglish = Transliterator.getInstance("Telugu-Latin; Latin-ASCII");
        // Transliterate Telugu to English
        return teluguToEnglish.transliterate(teluguTitleString);
    }

    private static String getTeluguToEnglishTransliteratedString(String teluguTitleString) {
        return teluguToEnglishTransliterator.getTransliteratedText(teluguTitleString);
    }

    private static String getDisplayTitleString(String songNumberString, String teluguTitleString,
                                                String translitEnglishTitleString) {
        String displayTitleString = null;
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher matcher = numberPattern.matcher(songNumberString);
        if (matcher.find()) {
            String songNumber = matcher.group(0);
            if (StringUtils.isNotBlank(songNumber)) {
                displayTitleString =
                        songNumber.concat(". ").concat(teluguTitleString).concat("\n")
                                .concat("   ").concat(translitEnglishTitleString);
            }
        }
        return displayTitleString;
    }

    public static String readHTMLFileToString(String filePath) {
        InputStream inputStream = HtmlToXlsxConverter.class.getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("HTML file not found at path: " + filePath);
        }
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine()).append("\n");
        }
        return stringBuilder.toString();
    }
}
