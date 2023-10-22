package com.generator.lyrics.generator;

import com.generator.lyrics.model.DMLInsertStatement;
import com.generator.lyrics.model.SongSheetRow;
import com.generator.lyrics.util.GenericUtils;

import java.util.*;

public class DMLScriptGenerator {

    public String generateDMLScripts(List<SongSheetRow> songSheetRowList) {
        return generateLyricsLanguageDMLScripts().concat(generateSongDMLScripts(songSheetRowList));
    }

    private String generateLyricsLanguageDMLScripts() {
        Map<String, Object> lyricsLanguageTeluguMap = new LinkedHashMap<>();
        lyricsLanguageTeluguMap.put("language_id", GenericUtils.getUniqueIdByString("Telugu"));
        lyricsLanguageTeluguMap.put("language_name", "Telugu");
        lyricsLanguageTeluguMap.put("language_code", "tel");
        populateAuditColumns(lyricsLanguageTeluguMap);

        DMLInsertStatement teluguDMLInsertStatement = new DMLInsertStatement("lyrics_language", lyricsLanguageTeluguMap);

        Map<String, Object> lyricsLanguageEnglishMap = new LinkedHashMap<>();
        lyricsLanguageEnglishMap.put("language_id", GenericUtils.getUniqueIdByString("English"));
        lyricsLanguageEnglishMap.put("language_name", "English");
        lyricsLanguageEnglishMap.put("language_code", "eng");
        populateAuditColumns(lyricsLanguageEnglishMap);

        DMLInsertStatement englishDMLInsertStatement = new DMLInsertStatement("lyrics_language",
                lyricsLanguageEnglishMap);

        Map<String, Object> lyricsLanguageHindiMap = new LinkedHashMap<>();
        lyricsLanguageHindiMap.put("language_id", GenericUtils.getUniqueIdByString("Hindi"));
        lyricsLanguageHindiMap.put("language_name", "Hindi");
        lyricsLanguageHindiMap.put("language_code", "hin");
        populateAuditColumns(lyricsLanguageHindiMap);

        DMLInsertStatement hindiDMLInsertStatement = new DMLInsertStatement("lyrics_language",
                lyricsLanguageHindiMap);

        return teluguDMLInsertStatement.toString().concat(englishDMLInsertStatement.toString())
                .concat(hindiDMLInsertStatement.toString()).concat("\n");
    }

    private void populateAuditColumns(Map<String, Object> lyricsLanguageMap) {
        lyricsLanguageMap.put("created_by", "SYSTEM");
        lyricsLanguageMap.put("created_timestamp", "CURRENT_TIMESTAMP");
        lyricsLanguageMap.put("updated_by", "SYSTEM");
        lyricsLanguageMap.put("updated_timestamp", "CURRENT_TIMESTAMP");
    }

    private String generateSongDMLScripts(List<SongSheetRow> songSheetRowList) {
        DMLInsertStatement songDMLInsertStatement;
        DMLInsertStatement songDetailsDMLInsertStatement;
        DMLInsertStatement songTransliterationsDMLInsertStatement;
        String songDMLInsertScripts = "";
        String songDetailsDMLInsertScripts = "";
        String songTransliterationsDMLInsertScripts = "";
        for (SongSheetRow songSheetRow : songSheetRowList) {

            Map<String, Object> songMap = new LinkedHashMap<>();
            songMap.put("song_id", GenericUtils.getUniqueIdByString(songSheetRow.getTeluguTitle()));
            songMap.put("song_number", songSheetRow.getSongSerialNumber());
            songMap.put("song_name", songSheetRow.getTeluguTitle());
            songMap.put("song_display_name", songSheetRow.getDisplayTitle());
            songMap.put("original_language_id", GenericUtils.getUniqueIdByString("Telugu"));
            songMap.put("original_song_lyrics", songSheetRow.getTeluguSongLyrics());
            populateAuditColumns(songMap);
            songDMLInsertStatement = new DMLInsertStatement("song", songMap);
            songDMLInsertScripts = songDMLInsertScripts.concat(songDMLInsertStatement.toString()).concat("\n");

            Map<String, Object> songDetailsMap = new LinkedHashMap<>();
            songDetailsMap.put("song_details_id", GenericUtils.getUniqueIdByString(songSheetRow.getDisplayTitle()));
            songDetailsMap.put("song_id", GenericUtils.getUniqueIdByString(songSheetRow.getTeluguTitle()));
            songDetailsMap.put("album", null);
            songDetailsMap.put("lyricist", null);
            songDetailsMap.put("composers", null);
            songDetailsMap.put("artists", null);
            songDetailsMap.put("instruments", null);
            songDetailsMap.put("year", null);
            populateAuditColumns(songDetailsMap);
            songDetailsDMLInsertStatement = new DMLInsertStatement("song_details", songDetailsMap);
            songDetailsDMLInsertScripts = songDetailsDMLInsertScripts.concat(songDetailsDMLInsertStatement.toString()).concat("\n");

            Map<String, Object> songTransliterationsMap = new LinkedHashMap<>();
            /*songTransliterationsMap.put("song_transliteration_id",
                    GenericUtils.getUniqueIdByString(songSheetRow.getEnglishTransliteratedTitle()));*/
            songTransliterationsMap.put("song_transliteration_id", UUID.randomUUID().toString());
            songTransliterationsMap.put("song_id", GenericUtils.getUniqueIdByString(songSheetRow.getTeluguTitle()));
            songTransliterationsMap.put("transliteration_language_id", GenericUtils.getUniqueIdByString("English"));
            songTransliterationsMap.put("transliterated_song_name", songSheetRow.getEnglishTransliteratedTitle());
            songTransliterationsMap.put("transliterated_lyrics", songSheetRow.getEnglishTransliteratedLyrics());
            populateAuditColumns(songTransliterationsMap);
            songTransliterationsDMLInsertStatement = new DMLInsertStatement("song_transliterations",
                    songTransliterationsMap);
            songTransliterationsDMLInsertScripts = songTransliterationsDMLInsertScripts.concat(songTransliterationsDMLInsertStatement.toString()).concat("\n");

        }
        return songDMLInsertScripts.concat("\n").concat(songDetailsDMLInsertScripts).concat("\n").concat(songTransliterationsDMLInsertScripts).concat("\n");
    }

}
