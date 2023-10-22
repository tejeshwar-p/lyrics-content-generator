package com.generator.lyrics.generator;

import com.generator.lyrics.converter.XlsxToPojoConverter;
import com.generator.lyrics.model.SongSheetRow;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ScriptGenerator {
    private static final String inputFilePath = "D:\\MYCODE\\GITHUBCODE\\LYRICSCONTENTGENERATOR\\lyrics-content-generator\\Songs.xlsx";
    private static final String outputFileName = "SongsDDLAndDMLScripts.sql";

    public static void generateScripts() throws IOException {
        log.info("Starting script generation");
        DDLScriptGenerator ddlScriptGenerator = new DDLScriptGenerator();
        String createTableDDLScripts = ddlScriptGenerator.generateDDLScripts();

        XlsxToPojoConverter xlsxToPojoConverter = new XlsxToPojoConverter();
        List<SongSheetRow> songSheetRowList = xlsxToPojoConverter.convert(inputFilePath);

        DMLScriptGenerator dmlScriptGenerator = new DMLScriptGenerator();
        String insertIntoDMLScripts = dmlScriptGenerator.generateDMLScripts(songSheetRowList);

        writeToSQLFile(createTableDDLScripts, insertIntoDMLScripts);
    }

    public static void writeToSQLFile(String createTableDDLScripts, String insertIntoDMLScripts) throws IOException {
        File file = new File(outputFileName);
        FileUtils.writeStringToFile(file, createTableDDLScripts, "UTF-8");
        FileUtils.writeStringToFile(file, insertIntoDMLScripts, "UTF-8", true);
        log.info("Completed script generation");
        log.info("Please find generated file at " + file.getAbsolutePath() + " location");
    }

}
