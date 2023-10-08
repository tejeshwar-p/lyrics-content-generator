package converter;

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
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlToXlsxConverter {
    public static void convert() throws IOException {
        String htmlContent = readHTMLFileToString("html/songsPage.html");
        System.out.println(htmlContent);

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Songs");

            Document doc = Jsoup.parse(htmlContent, "UTF-8");

            Elements songs = doc.select("div[data-role=page]");

            sheet.setColumnWidth(0,5*256);
            sheet.setColumnWidth(1,15*256);
            sheet.setColumnWidth(2,100*256);
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
                        System.out.println(songNumber);
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
                    }
                }
            }
            // Write to Excel file
            workbook.write(new FileOutputStream("Songs.xlsx"));
        }


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
