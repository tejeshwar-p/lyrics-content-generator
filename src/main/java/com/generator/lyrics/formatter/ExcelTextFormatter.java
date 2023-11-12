package com.generator.lyrics.formatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTextFormatter {
    public static void main(String[] args) {
        try {
            // Load the Excel file
            FileInputStream file = new FileInputStream("src/main/resources/data/Songs.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet("Bethesda Songs 12 November 2023");

            // Create a cell style with text wrapping enabled
            CellStyle wrapStyle = workbook.createCellStyle();
            wrapStyle.setWrapText(true);

            // Process the data
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Cell cellC = row.getCell(2); // Column C
                String textC = cellC.getStringCellValue();

                // Format the text
                String formattedTextD = formatText(textC);

                // Write formatted text to Column D
                Cell formattedCellD = row.createCell(3); // Column D
                formattedCellD.setCellValue(formattedTextD);
                formattedCellD.setCellStyle(wrapStyle);

                Cell cellG = row.getCell(6); // Column G
                String textG = cellG.getStringCellValue();

                // Format the text
                String formattedTextH = formatText(textG);

                // Write formatted text to Column H
                Cell formattedCellH = row.createCell(7); // Column H
                formattedCellH.setCellValue(formattedTextH);
                formattedCellH.setCellStyle(wrapStyle);
            }

            // Write changes back to the Excel file
            FileOutputStream outFile = new FileOutputStream("src/main/resources/data/Songs.xlsx");
            workbook.write(outFile);
            outFile.close();
            workbook.close();

            System.out.println("Formatting complete.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to format the text
    public static String formatText(String text) {
        String[] lines = text.split("\\r?\\n");
        StringBuilder formattedText = new StringBuilder();

        for (String line : lines) {
            if (line.trim().matches("\\d+(?:\\.|\\)|\\s\\.|\\s\\)).*")) {
                formattedText.append("\n");
            }
            formattedText.append(line.trim()).append("\n");
        }

        return formattedText.toString();
    }
}

