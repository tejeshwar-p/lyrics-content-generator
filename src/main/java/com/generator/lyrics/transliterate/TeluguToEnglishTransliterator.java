package com.generator.lyrics.transliterate;

import com.ibm.icu.text.Transliterator;

public class TeluguToEnglishTransliterator {

    /*LDC "\u0c3e > aa; \u0c3f > e; \u0c40 > ee; \u0c41 > u; \u0c42 > uu; \u0c43 > ru; \u0c44 > ruu; \u0c46 > ay; \u0c47 > aay; \u0c48 > ai; \u0c4a > o; " +
            "\u0c4b > oo; \u0c4c > ou; \u0c4d > ^; \u0c56 > ai; \u0c05 > a; \u0c06 > aa; \u0c07 > e; \u0c08 > ee; \u0c09 > u; \u0c0a > uu; \u0c0b > ru; \u0c0e > ay; \u0c0f > aay; " +
            "\u0c10 > ai; \u0c12 > o; \u0c13 > oo; \u0c14 > ou; \u0c02 > M; \u0c03 > ah; " +
            "\u0c15\u0c4d\u0c37\u0c4d > ksh; \u0c15\u0c4d\u0c37 > ksha; " +
            "\u0c15\u0c4d > k; \u0c16\u0c4d > kk; \u0c17\u0c4d > g; \u0c18\u0c4d > gg; " +
            "\u0c1a\u0c4d > ch; \u0c1b\u0c4d > cch; \u0c1c\u0c4d> j; " +
            "\u0c1f\u0c4d > t; \u0c20\u0c4d > tt; \u0c21\u0c4d > d; \u0c23\u0c4d > nn; " +
            "\u0c24\u0c4d > th; \u0c25\u0c4d > tth; \u0c26\u0c4d > dh; \u0c27\u0c4d > ddh; \u0c28\u0c4d > n; " +
            "\u0c2a\u0c4d > p; \u0c2b\u0c4d > ph; \u0c2c\u0c4d > b; \u0c2d\u0c4d > bbh; \u0c2e\u0c4d > m; " +
            "\u0c2f\u0c4d > y; \u0c30\u0c4d > r; \u0c32\u0c4d\u0c32\u0c4d > ll; \u0c32\u0c4d > l; \u0c35\u0c4d > v; \u0c36\u0c4d > sy; \u0c37\u0c4d > sh; \u0c38\u0c4d > s; \u0c39\u0c3e > h; " +
            "\u0c33\u0c4d > ll; \u0c31\u0c4d > rr; \u0c15 > ka; \u0c16 > kha; \u0c17 > ga; \u0c18 > gha; \u0c19 > ini; \u0c1a > cha; " +
            "\u0c1b > chha; \u0c1c > ja; \u0c1d > jha; \u0c1e > nya; \u0c1f > ta; \u0c20 > tta; \u0c21 > da; \u0c22 > dda; \u0c23 > ana; \u0c24 > tha; \u0c25 > thha; " +
            "\u0c26 > dha; \u0c27 > dhha; \u0c28 > na; \u0c2a > pa; \u0c2b > pha; \u0c2c > ba; \u0c2d > bha; \u0c2e > ma; \u0c2f > ya; \u0c30 > ra; \u0c32 > la; \u0c35 > va; " +
            "\u0c36 > sya; \u0c37 > sha; \u0c38 > sa; \u0c39 > ha; \u0c33 > la; \u0c34 > lla; \u0c31 > rra; \u0c60 > rruu; "*/


    //Version 1
        /*String customRules = "ా > aa; ి > e; ీ > ee; ు > u; ూ > uu; ృ > ru; ౄ > ruu; ె > ay; ే > aay; ై > ai; ొ > o; " +
                "ో > oo; ౌ > ou; ్ > '-'; ౖ > ai; అ > a; ఆ > aa; ఇ > e; ఈ > ee; ఉ > u; ఊ > uu; ఋ > ru; ఎ > ay; ఏ > aay;" +
                "ఐ > ai; ఒ > o; ఓ > oo; ఔ > ou; ం > M; ః > ah; " +
                "క్ష్ > ksh; క్ష > ksha; " +
                "క్ > k; ఖ్ > kk; గ్ > g; ఘ్ > gg; " +
                "చ్ > ch; ఛ్ > cch; జ్> j; " +
                "ట్ > t; ఠ్ > tt; డ్ > d; ణ్ > nn; " +
                "త్ > th; థ్ > tth; ద్ > dh; ధ్ > ddh; న్ > n; " +
                "ప్ > p; ఫ్ > ph; బ్ > b; భ్ > bbh; మ్ > m; " +
                "య్ > y; ర్ > r; ల్ల్ > ll; ల్ > l; వ్ > v; శ్ > sy; ష్ > sh; స్ > s; హా > h; " +
                "ళ్ > ll; ఱ్ > rr; క > ka; ఖ > kha; గ > ga; ఘ > gha; ఙ > ini; చ > cha; " +
                "ఛ > chha; జ > ja; ఝ > jha; ఞ > nya; ట > ta; ఠ > tta; డ > da; ఢ > dda; ణ > ana; త > tha; థ > thha; " +
                "ద > dha; ధ > dhha; న > na; ప > pa; ఫ > pha; బ > ba; భ > bha; మ > ma; య > ya; ర > ra; ల > la; వ > va; " +
                "శ > sya; ష > sha; స > sa; హ > ha; ళ > la; ఴ > lla; ఱ > rra; ౠ > rruu; ";*/

    //Version 2
    /*public static final String CUSTOM_TELUGU_RULES = "ా > aa; ి > e; ీ > ee; ు > u; ూ > uu; ృ > ru; ౄ > ruu; ె > ay; ే > " +
            "aay;" +
            " ై > ai; ొ > " +
            "o; " +
            "ో > oo; ౌ > ou; ్ > '-'; ౖ > ai; అ > a; ఆ > aa; ఇ > e; ఈ > ee; ఉ > u; ఊ > uu; ఋ > ru; ఎ > ay; ఏ > aay;" +
            "ఐ > ai; ఒ > o; ఓ > oo; ఔ > ou; ం > M; ః > ah; " +
            "క్ష్ > ksh; క్ష > ksha; " +
            "క్ > k; ఖ్ > kk; గ్ > g; ఘ్ > gg; " +
            "చ్ > ch; ఛ్ > cch; జ్> j; " +
            "ట్ > t; ఠ్ > tt; డ్ > d; ణ్ > nn; " +
            "త్ > th; థ్ > tth; ద్ > dh; ధ్ > ddh; న్ > n; " +
            "ప్ > p; ఫ్ > ph; బ్ > b; భ్ > bbh; మ్ > m; " +
            "య్ > y; ర్ > r; ల్ల్ > ll; ల్ > l; వ్ > v; శ్ > sy; ష్ > sh; స్ > s; హా > h; " +
            "ళ్ > ll; ఱ్ > rr; క > k; ఖ > kh; గ > g; ఘ > gh; ఙ > ini; చ > ch; " +
            "ఛ > chh; జ > j; ఝ > jh; ఞ > ny; ట > t; ఠ > tt; డ > d; ఢ > dd; ణ > ana; త > th; థ > thh; " +
            "ద > dh; ధ > dhh; న > n; ప > p; ఫ > ph; బ > b; భ > bh; మ > m; య > y; ర > r; ల > l; వ > v; " +
            "శ > s; ష > sh; స > s; హ > h; ళ > l; ఴ > ll; ఱ > rr; ౠ > rruu; ";*/

    //Version 3
    public static final String CUSTOM_TELUGU_RULES = "ా > aa; ి > e; ీ > ee; ు > u; ూ > uu; ృ > ru; ౄ > ruu; ె > ay; ే > " +
            "aay;" +
            " ై > ai; ొ > " +
            "o; " +
            "ో > oo; ౌ > ou; ్ > '-'; ౖ > ai; అ > a; ఆ > aa; ఇ > e; ఈ > ee; ఉ > u; ఊ > uu; ఋ > ru; ఎ > ay; ఏ > aay;" +
            "ఐ > ai; ఒ > o; ఓ > oo; ఔ > ou; ం > M; ః > ah; " +
            "క్ష్ > ksh; క్ష > ksha; " +
            "క్ > k; ఖ్ > kk; గ్ > g; ఘ్ > gg; " +
            "చ్ > ch; ఛ్ > cch; జ్> j; " +
            "ట్ > t; ఠ్ > tt; డ్ > d; ణ్ > nn; " +
            "త్ > th; థ్ > tth; ద్ > dh; ధ్ > ddh; న్ > n; " +
            "ప్ > p; ఫ్ > ph; బ్ > b; భ్ > bbh; మ్ > m; " +
            "య్ > y; ర్ > r; ల్ల్ > ll; ల్ > l; వ్ > v; శ్ > sy; ష్ > sh; స్ > s; హా > h; " +
            "ళ్ > ll; ఱ్ > rr; క > k; ఖ > kh; గ > g; ఘ > gh; ఙ > ini; చ > ch; " +
            "ఛ > chh; జ > j; ఝ > jh; ఞ > ny; ట > t; ఠ > tt; డ > d; ఢ > dd; ణ > ana; త > th; థ > thh; " +
            "ద > dh; ధ > dhh; న > na; ప > pa; ఫ > ph; బ > ba; భ > bha; మ > ma; య > ya; ర > ra; ల > l; వ > va; " +
            "శ > sa; ష > sha; స > sa; హ > ha; ళ > la; ఴ > lla; ఱ > rra; ౠ > rruu; ";

    public String getTransliteratedText(String teluguText) {
        Transliterator customTransliterator = Transliterator.createFromRules("customTeluguToEnglish",
                CUSTOM_TELUGU_RULES, Transliterator.FORWARD);
        return customTransliterator.transliterate(teluguText);
    }
}
