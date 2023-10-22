package com.generator.lyrics.util;

import java.util.UUID;

public class GenericUtils {
    public static String cleanHTMLTagsFromText(String textToTransliterate) {
        return textToTransliterate.replaceAll("<span>", "").replaceAll("</span>", "")
                .replaceAll(" <br>", "").replaceAll("<br>", "").replaceAll("</br>", "");
    }

    public static String getUniqueIdByString(String stringForId) {
        UUID uuid = UUID.nameUUIDFromBytes(stringForId.getBytes());
        return uuid.toString();
    }
}
