package com.generator.lyrics.converter;

import com.generator.lyrics.transliterate.TeluguToEnglishTransliterator;
import com.generator.lyrics.util.GenericUtils;
import com.ibm.icu.text.Transliterator;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.generator.lyrics.transliterate.JavaScriptTransliterator.getTransliteratedText;

@Slf4j
public class HtmlToXlsxConverter {

    private static final List<Integer> exclusionList = Arrays.asList(11, 36, 63, 64, 66, 81, 83, 108, 109, 111, 123,
            136,
            155, 220, 271, //Found duplicate during inserting records into db
            142, 146, 149, 158, 170, 172, 173, 174, 175, 181, 185, 188, 189, 195, 197, 201, 203, 209, 226, 246, 254, 258,
            263, 265, 282, 285, 294, 301, 302, 304, 305, 306);
    //,307,308,309,310,311,312,313,315

    private static final TeluguToEnglishTransliterator teluguToEnglishTransliterator = new TeluguToEnglishTransliterator();

    public static void convert() throws IOException {
        printSeparatorLine();
        String htmlContent = readHTMLFileToString("html/songsPage.html");
        log.info("Read HTML content from file");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet allSongsSheet = workbook.createSheet("All Songs");
            XSSFSheet bethesdaSongsSheet = workbook.createSheet("Bethesda Songs");

            Document doc = Jsoup.parse(htmlContent, "UTF-8");

            Elements songs = doc.select("div[data-role=page]");

            setColumnWidthsForSheet(allSongsSheet);
            setColumnWidthsForSheet(bethesdaSongsSheet);

            // Create a cell style with text wrapping enabled
            CellStyle wrapStyle = workbook.createCellStyle();
            wrapStyle.setWrapText(true);

            createHeaderRowForSheet(allSongsSheet, wrapStyle);
            createHeaderRowForSheet(bethesdaSongsSheet, wrapStyle);

            printSeparatorLine();

            int rowNum = 1;
            int bethesdaSongSheetRowNum = 1;
            for (Element song : songs) {

                Node prev = Objects.requireNonNull(song.previousSibling()).previousSibling();
                String songLyricsText = song.select("div[data-role=main]").html();

                if (prev instanceof Comment comment) {
                    // Get the comment text
                    String commentText = comment.getData();
                    // Use a regular expression to extract the value between dashes
                    Pattern pattern = Pattern.compile("SONG\\s\\d+");
                    Matcher matcher = pattern.matcher(commentText);
                    if (matcher.find()) {
                        // Print out the value
                        String songNumber = matcher.group(0);
                        //System.out.println(songNumber);

                        Row allSongsSheetRow = allSongsSheet.createRow(rowNum++);
                        createCellsForEachRow(wrapStyle, rowNum, songLyricsText, matcher, songNumber, allSongsSheetRow);
                        if (!exclusionList.contains(rowNum - 1)) {
                            Row bethesdaSongsSheetRow = bethesdaSongsSheet.createRow(bethesdaSongSheetRowNum++);
                            createCellsForEachRow(wrapStyle, bethesdaSongSheetRowNum, songLyricsText, matcher, songNumber, bethesdaSongsSheetRow);
                        }

                        printCompletionPercentage(songs, rowNum);
                    }
                }

            }
            // Write to Excel file
            workbook.write(new FileOutputStream("Songs.xlsx"));
            System.out.print("\rProgress: " + 100 + "%");
            System.out.println("\n\nFinished!");
            File file = new File("Songs.xlsx");
            System.out.println("Please find generated file at " + file.getAbsolutePath() + " location");
        }
    }

    private static void createCellsForEachRow(CellStyle wrapStyle, int rowNum, String songLyricsText,
                                              Matcher matcher, String songNumber, Row allSongsSheetRow) {

        String teluguTitleString = getTeluguTitleString(songLyricsText);

        //Remove all the HTML tags from songLyricsText
        songLyricsText = GenericUtils.cleanHTMLTagsFromText(songLyricsText);

        Cell serialNoCell = allSongsSheetRow.createCell(0);
        serialNoCell.setCellValue(rowNum - 1);
        serialNoCell.setCellStyle(wrapStyle);

        Cell songNumberCell = allSongsSheetRow.createCell(1);
        songNumberCell.setCellValue(songNumber);
        songNumberCell.setCellStyle(wrapStyle);

        Cell lyricsCell = allSongsSheetRow.createCell(2);
        lyricsCell.setCellValue(songLyricsText);
        lyricsCell.setCellStyle(wrapStyle);

        Cell teluguTitleCell = allSongsSheetRow.createCell(3);
        teluguTitleCell.setCellValue(teluguTitleString);
        teluguTitleCell.setCellStyle(wrapStyle);

        Cell englishTitleCell = allSongsSheetRow.createCell(4);
        //String translitEnglishTitleString = getTeluguToEnglishTransliteratedString(teluguTitleString);
        String translitEnglishTitleString = getTransliteratedText(teluguTitleString).replaceAll("\n", "");
        englishTitleCell.setCellValue(translitEnglishTitleString);
        englishTitleCell.setCellStyle(wrapStyle);

        Cell englishLyricsCell = allSongsSheetRow.createCell(5);
        //String translitEnglishLyricsString = getTeluguToEnglishTransliteratedString(songLyricsText);
        String translitEnglishLyricsString = getTransliteratedText(songLyricsText);
        englishLyricsCell.setCellValue(translitEnglishLyricsString);
        englishLyricsCell.setCellStyle(wrapStyle);

        Cell displayTitleCell = allSongsSheetRow.createCell(6);
        String displayTitleString = getDisplayTitleString(String.valueOf(rowNum - 1), teluguTitleString,
                translitEnglishTitleString);
        displayTitleCell.setCellValue(displayTitleString);
        displayTitleCell.setCellStyle(wrapStyle);
    }

    private static void setColumnWidthsForSheet(XSSFSheet allSongsSheet) {
        allSongsSheet.setColumnWidth(0, 5 * 256);
        allSongsSheet.setColumnWidth(1, 15 * 256);
        allSongsSheet.setColumnWidth(2, 100 * 256);
        allSongsSheet.setColumnWidth(3, 100 * 256);
        allSongsSheet.setColumnWidth(4, 100 * 256);
        allSongsSheet.setColumnWidth(5, 100 * 256);
        allSongsSheet.setColumnWidth(6, 100 * 256);
    }

    private static void createHeaderRowForSheet(XSSFSheet songsSheet, CellStyle wrapStyle) {
        Row headerRow = songsSheet.createRow(0);
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
    }

    private static void printCompletionPercentage(Elements songs, int rowNum) {
        BigDecimal bigDecimalRowNum = new BigDecimal(rowNum - 1);
        BigDecimal songsSize = new BigDecimal(songs.size());
        BigDecimal oneHundred = new BigDecimal("100");
        BigDecimal completionPercentage =
                bigDecimalRowNum.divide(songsSize, 4, RoundingMode.HALF_UP).multiply(oneHundred);
        //((rowNum - 1)/songs.size())*100;
        System.out.print("\rProgress: " + completionPercentage + "%");
    }

    private static void printSeparatorLine() {
        System.out.println("----------------------------------------------------------------------------------");
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

    private static String getDisplayTitleString(String songNumber, String teluguTitleString,
                                                String translitEnglishTitleString) {
        String displayTitleString = null;
        /*Pattern numberPattern = Pattern.compile("\\d+");
        Matcher matcher = numberPattern.matcher(songNumberString);
        if (matcher.find()) {
            String songNumber = matcher.group(0);*/
        if (StringUtils.isNotBlank(songNumber)) {
            displayTitleString =
                    songNumber.concat(". ").concat(teluguTitleString.trim()).concat("\n")
                            .concat(translitEnglishTitleString.trim());
        }
        /*}*/
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
