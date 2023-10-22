package com.generator.lyrics.generator;

import com.generator.lyrics.model.DDLColumnChunk;
import com.generator.lyrics.model.DDLConstraintChunk;
import com.generator.lyrics.model.DDLCreateTableChunk;
import com.generator.lyrics.model.DDLStatement;

import java.util.Arrays;
import java.util.List;

public class DDLScriptGenerator {

    public String generateDDLScripts() {
        return generateLyricsLanguageTableDDL()
                .concat(generateSongTableDDL())
                .concat(generateSongDetailsTableDDL())
                .concat(generateSongTransliterationsTableDDL())
                .concat(generateLabelsTableDDL())
                .concat(generateSongLabelsTableDDL())
                .concat(generateFavouriteSongsTableDDL());
    }

    public String generateLyricsLanguageTableDDL() {
        DDLStatement lyricsLanguageDDL = new DDLStatement();
        DDLCreateTableChunk ddlCreateTableChunk = new DDLCreateTableChunk("lyrics_language");
        DDLColumnChunk languageId = new DDLColumnChunk("language_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, DDLColumnChunk.DDL_PRIMARY_KEY);
        DDLColumnChunk languageName = new DDLColumnChunk("language_name", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk languageCode = new DDLColumnChunk("language_code", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_10, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdBy = new DDLColumnChunk("created_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdTimestamp = new DDLColumnChunk("created_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, DDLColumnChunk.DDL_NOT_NULL, DDLColumnChunk.DDL_DEFAULT, DDLColumnChunk.DDL_CURRENT_TIMESTAMP, null);
        DDLColumnChunk updatedBy = new DDLColumnChunk("updated_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, null, null, null, null);
        DDLColumnChunk updatedTimestamp = new DDLColumnChunk("updated_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, null, null, null, null);
        lyricsLanguageDDL.setDdlCreateTableChunk(ddlCreateTableChunk);
        lyricsLanguageDDL.setDdlColumnChunks(Arrays.asList(languageId, languageName, languageCode, createdBy, createdTimestamp, updatedBy, updatedTimestamp));
        return lyricsLanguageDDL.toString();
    }

    public String generateSongTableDDL() {
        DDLStatement songDDL = new DDLStatement();
        DDLCreateTableChunk ddlCreateTableChunk = new DDLCreateTableChunk("song");
        DDLColumnChunk song_id = new DDLColumnChunk("song_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, DDLColumnChunk.DDL_PRIMARY_KEY);
        DDLColumnChunk song_number = new DDLColumnChunk("song_number", DDLColumnChunk.DDL_TYPE_BIGINT,
                null, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk song_name = new DDLColumnChunk("song_name", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_100, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk song_display_name = new DDLColumnChunk("song_display_name", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_300, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk original_language_id = new DDLColumnChunk("original_language_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk original_song_lyrics = new DDLColumnChunk("original_song_lyrics",
                DDLColumnChunk.DDL_TYPE_VARCHAR, DDLColumnChunk.SIZE_3000, DDLColumnChunk.DDL_NOT_NULL, null, null,
                null);
        DDLColumnChunk createdBy = new DDLColumnChunk("created_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdTimestamp = new DDLColumnChunk("created_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, DDLColumnChunk.DDL_NOT_NULL, DDLColumnChunk.DDL_DEFAULT, DDLColumnChunk.DDL_CURRENT_TIMESTAMP, null);
        DDLColumnChunk updatedBy = new DDLColumnChunk("updated_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, null, null, null, null);
        DDLColumnChunk updatedTimestamp = new DDLColumnChunk("updated_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, null, null, null, null);
        DDLConstraintChunk languageIdConstraint = new DDLConstraintChunk("original_language_id",
                "lyrics_language", "language_id");
        songDDL.setDdlCreateTableChunk(ddlCreateTableChunk);
        songDDL.setDdlColumnChunks(Arrays.asList(song_id, song_number, song_name, song_display_name,
                original_language_id, original_song_lyrics, createdBy,
                createdTimestamp, updatedBy, updatedTimestamp));
        songDDL.setDdlConstraintChunks(List.of(languageIdConstraint));
        return songDDL.toString();
    }

    public String generateSongDetailsTableDDL() {
        DDLStatement songDetailsDDL = new DDLStatement();
        DDLCreateTableChunk ddlCreateTableChunk = new DDLCreateTableChunk("song_details");
        DDLColumnChunk song_details_id = new DDLColumnChunk("song_details_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, DDLColumnChunk.DDL_PRIMARY_KEY);
        DDLColumnChunk song_id = new DDLColumnChunk("song_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk album = new DDLColumnChunk("album", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_100, null, null, null, null);
        DDLColumnChunk lyricist = new DDLColumnChunk("lyricist", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_100, null, null, null, null);
        DDLColumnChunk composers = new DDLColumnChunk("composers", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_100, null, null, null, null);
        DDLColumnChunk singers = new DDLColumnChunk("artists",
                DDLColumnChunk.DDL_TYPE_VARCHAR, DDLColumnChunk.SIZE_300, null, null, null,
                null);
        DDLColumnChunk instruments = new DDLColumnChunk("instruments",
                DDLColumnChunk.DDL_TYPE_VARCHAR, DDLColumnChunk.SIZE_300, null, null, null,
                null);
        DDLColumnChunk year = new DDLColumnChunk("year",
                DDLColumnChunk.DDL_TYPE_VARCHAR, DDLColumnChunk.SIZE_4, null, null, null,
                null);
        DDLColumnChunk createdBy = new DDLColumnChunk("created_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdTimestamp = new DDLColumnChunk("created_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, DDLColumnChunk.DDL_NOT_NULL, DDLColumnChunk.DDL_DEFAULT, DDLColumnChunk.DDL_CURRENT_TIMESTAMP, null);
        DDLColumnChunk updatedBy = new DDLColumnChunk("updated_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, null, null, null, null);
        DDLColumnChunk updatedTimestamp = new DDLColumnChunk("updated_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, null, null, null, null);
        DDLConstraintChunk songIdConstraint = new DDLConstraintChunk("song_id", "song", "song_id");
        songDetailsDDL.setDdlCreateTableChunk(ddlCreateTableChunk);
        songDetailsDDL.setDdlColumnChunks(Arrays.asList(song_details_id, song_id, album, lyricist,
                composers, singers, instruments, year, createdBy,
                createdTimestamp, updatedBy, updatedTimestamp));
        songDetailsDDL.setDdlConstraintChunks(List.of(songIdConstraint));
        return songDetailsDDL.toString();
    }

    public String generateSongTransliterationsTableDDL() {
        DDLStatement songTransliterationsDDL = new DDLStatement();
        DDLCreateTableChunk ddlCreateTableChunk = new DDLCreateTableChunk("song_transliterations");
        DDLColumnChunk song_transliteration_id = new DDLColumnChunk("song_transliteration_id",
                DDLColumnChunk.DDL_TYPE_VARCHAR, DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null,
                null, DDLColumnChunk.DDL_PRIMARY_KEY);
        DDLColumnChunk song_id = new DDLColumnChunk("song_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk transliteration_language_id = new DDLColumnChunk("transliteration_language_id",
                DDLColumnChunk.DDL_TYPE_VARCHAR, DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null,
                null, null);
        DDLColumnChunk transliterated_song_name = new DDLColumnChunk("transliterated_song_name",
                DDLColumnChunk.DDL_TYPE_VARCHAR, DDLColumnChunk.SIZE_200, DDLColumnChunk.DDL_NOT_NULL, null,
                null, null);
        DDLColumnChunk transliterated_lyrics = new DDLColumnChunk("transliterated_lyrics", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_3000, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdBy = new DDLColumnChunk("created_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdTimestamp = new DDLColumnChunk("created_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, DDLColumnChunk.DDL_NOT_NULL, DDLColumnChunk.DDL_DEFAULT, DDLColumnChunk.DDL_CURRENT_TIMESTAMP, null);
        DDLColumnChunk updatedBy = new DDLColumnChunk("updated_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, null, null, null, null);
        DDLColumnChunk updatedTimestamp = new DDLColumnChunk("updated_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, null, null, null, null);
        DDLConstraintChunk songIdConstraint = new DDLConstraintChunk("song_id", "song", "song_id");
        DDLConstraintChunk transliterationLanguageIdConstraint = new DDLConstraintChunk("transliteration_language_id",
                "lyrics_language", "language_id");
        songTransliterationsDDL.setDdlCreateTableChunk(ddlCreateTableChunk);
        songTransliterationsDDL.setDdlColumnChunks(Arrays.asList(song_transliteration_id, song_id,
                transliteration_language_id, transliterated_song_name, transliterated_lyrics, createdBy,
                createdTimestamp, updatedBy, updatedTimestamp));
        songTransliterationsDDL.setDdlConstraintChunks(List.of(songIdConstraint, transliterationLanguageIdConstraint));
        return songTransliterationsDDL.toString();
    }

    public String generateLabelsTableDDL() {
        DDLStatement labelsDDL = new DDLStatement();
        DDLCreateTableChunk ddlCreateTableChunk = new DDLCreateTableChunk("label");
        DDLColumnChunk label_id = new DDLColumnChunk("label_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, DDLColumnChunk.DDL_PRIMARY_KEY);
        DDLColumnChunk label_name = new DDLColumnChunk("label_name", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_100, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdBy = new DDLColumnChunk("created_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdTimestamp = new DDLColumnChunk("created_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, DDLColumnChunk.DDL_NOT_NULL, DDLColumnChunk.DDL_DEFAULT, DDLColumnChunk.DDL_CURRENT_TIMESTAMP, null);
        DDLColumnChunk updatedBy = new DDLColumnChunk("updated_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, null, null, null, null);
        DDLColumnChunk updatedTimestamp = new DDLColumnChunk("updated_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, null, null, null, null);
        labelsDDL.setDdlCreateTableChunk(ddlCreateTableChunk);
        labelsDDL.setDdlColumnChunks(Arrays.asList(label_id, label_name, createdBy, createdTimestamp,
                updatedBy, updatedTimestamp));
        return labelsDDL.toString();
    }


    public String generateSongLabelsTableDDL() {
        DDLStatement songLabelsDDL = new DDLStatement();
        DDLCreateTableChunk ddlCreateTableChunk = new DDLCreateTableChunk("song_labels");
        DDLColumnChunk song_label_id = new DDLColumnChunk("song_label_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, DDLColumnChunk.DDL_PRIMARY_KEY);
        DDLColumnChunk song_id = new DDLColumnChunk("song_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk label_id = new DDLColumnChunk("label_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdBy = new DDLColumnChunk("created_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdTimestamp = new DDLColumnChunk("created_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, DDLColumnChunk.DDL_NOT_NULL, DDLColumnChunk.DDL_DEFAULT, DDLColumnChunk.DDL_CURRENT_TIMESTAMP, null);
        DDLColumnChunk updatedBy = new DDLColumnChunk("updated_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, null, null, null, null);
        DDLColumnChunk updatedTimestamp = new DDLColumnChunk("updated_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, null, null, null, null);
        DDLConstraintChunk songIdConstraint = new DDLConstraintChunk("song_id", "song", "song_id");
        DDLConstraintChunk labelIdConstraint = new DDLConstraintChunk("label_id", "label", "label_id");
        songLabelsDDL.setDdlCreateTableChunk(ddlCreateTableChunk);
        songLabelsDDL.setDdlColumnChunks(Arrays.asList(song_label_id, song_id, label_id, createdBy,
                createdTimestamp, updatedBy, updatedTimestamp));
        songLabelsDDL.setDdlConstraintChunks(List.of(songIdConstraint, labelIdConstraint));
        return songLabelsDDL.toString();
    }

    public String generateFavouriteSongsTableDDL() {
        DDLStatement favouriteSongsDDL = new DDLStatement();
        DDLCreateTableChunk ddlCreateTableChunk = new DDLCreateTableChunk("favourite_songs");
        DDLColumnChunk favourite_song_id = new DDLColumnChunk("favourite_song_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, DDLColumnChunk.DDL_PRIMARY_KEY);
        DDLColumnChunk user_id = new DDLColumnChunk("user_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk song_id = new DDLColumnChunk("song_id", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdBy = new DDLColumnChunk("created_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, DDLColumnChunk.DDL_NOT_NULL, null, null, null);
        DDLColumnChunk createdTimestamp = new DDLColumnChunk("created_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, DDLColumnChunk.DDL_NOT_NULL, DDLColumnChunk.DDL_DEFAULT, DDLColumnChunk.DDL_CURRENT_TIMESTAMP, null);
        DDLColumnChunk updatedBy = new DDLColumnChunk("updated_by", DDLColumnChunk.DDL_TYPE_VARCHAR,
                DDLColumnChunk.SIZE_50, null, null, null, null);
        DDLColumnChunk updatedTimestamp = new DDLColumnChunk("updated_timestamp", DDLColumnChunk.DDL_TYPE_DATETIME,
                null, null, null, null, null);
        DDLConstraintChunk songIdConstraint = new DDLConstraintChunk("song_id", "song", "song_id");
        favouriteSongsDDL.setDdlCreateTableChunk(ddlCreateTableChunk);
        favouriteSongsDDL.setDdlColumnChunks(Arrays.asList(favourite_song_id, user_id, song_id, createdBy,
                createdTimestamp, updatedBy, updatedTimestamp));
        favouriteSongsDDL.setDdlConstraintChunks(List.of(songIdConstraint));
        return favouriteSongsDDL.toString();
    }
}
