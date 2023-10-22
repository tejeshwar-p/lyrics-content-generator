package com.generator.lyrics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongSheetRow {
    private Long songSerialNumber;
    private String teluguSongLyrics;
    private String teluguTitle;
    private String englishTransliteratedTitle;
    private String englishTransliteratedLyrics;
    private String displayTitle;
}
