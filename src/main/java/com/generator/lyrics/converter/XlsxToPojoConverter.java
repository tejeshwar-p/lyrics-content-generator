package com.generator.lyrics.converter;

import com.generator.lyrics.model.SongSheetRow;
import io.micrometer.common.util.StringUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlsxToPojoConverter {

    private static void prepareSongSheetRowFromEachCell(SongSheetRow songSheetRow, Cell cell) {
        if (null != cell && StringUtils.isNotBlank(cell.toString()) && cell.getColumnIndex() == 0) {
            songSheetRow.setSongSerialNumber((long) Double.parseDouble(cell.toString()));
        }
        if (null != cell && StringUtils.isNotBlank(cell.toString()) && cell.getColumnIndex() == 2) {
            songSheetRow.setTeluguSongLyrics(cell.toString());
        }
        if (null != cell && StringUtils.isNotBlank(cell.toString()) && cell.getColumnIndex() == 3) {
            songSheetRow.setTeluguTitle(cell.toString());
        }
        if (null != cell && StringUtils.isNotBlank(cell.toString()) && cell.getColumnIndex() == 4) {
            songSheetRow.setEnglishTransliteratedTitle(cell.toString());
        }
        if (null != cell && StringUtils.isNotBlank(cell.toString()) && cell.getColumnIndex() == 5) {
            songSheetRow.setEnglishTransliteratedLyrics(cell.toString());
        }
        if (null != cell && StringUtils.isNotBlank(cell.toString()) && cell.getColumnIndex() == 6) {
            songSheetRow.setDisplayTitle(cell.toString());
        }
    }

    public List<SongSheetRow> convert(String filePath, String sheetName) {
        List<SongSheetRow> songSheetRows = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet != null) {
                songSheetRows = new ArrayList<>();
                for (Row row : sheet) {
                    if (0 == row.getRowNum()) {
                        continue;
                    }
                    SongSheetRow songSheetRow = new SongSheetRow();
                    for (Cell cell : row) {
                        prepareSongSheetRowFromEachCell(songSheetRow, cell);
                    }
                    songSheetRows.add(songSheetRow);
                }
            } else {
                System.out.println("Sheet "+sheetName+" not found in the Excel file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return songSheetRows;
    }
}
