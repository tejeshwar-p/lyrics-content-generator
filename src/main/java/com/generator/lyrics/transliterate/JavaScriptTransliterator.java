package com.generator.lyrics.transliterate;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.util.stream.Collectors;

public class JavaScriptTransliterator {
    /*public static void main(String[] args) throws IOException {
        System.out.println(getTransliteratedText("<span>హల్లెలూయా ఆరాధన రాజాధిరాజు యేసునకే<br>\n" +
                "  మహిమయు ఘనతయు సర్వాధికారి క్రీస్తునకే <br><br>\n" +
                "  చప్పట్లు కొట్టుచు పాటలు పాడుచు <br>\n" +
                "  ఆ ప్రభుని కీర్తించెదం<br>\n" +
                "  నాట్యము చేయుచు ఉత్సాహధ్వనులతొ <br>\n" +
                "  స్తోత్రార్పణ చేసెదం <br><br>\n" +
                "  ||హల్లెలూయా||<br><br>\n" +
                "  1.రూపింపబడక ముందే నన్ను ఎరిగితివి <br>\n" +
                "  నా పాదములు జారకుండ రక్షించి నడిపితివి<br>\n" +
                "  ||చప్పట్లు ||<br><br>\n" +
                "  2.అభిషేక వస్త్రమునిచ్చి వీరులుగ చేసితివి<br>\n" +
                "  అపవాది క్రియలు జయింప ప్రార్థన శక్తిని నిచ్చితివి<br>\n" +
                "  ||చప్పట్లు ||<br><br>\n" +
                "  3.నీ సొత్తైన జనముగ ప్రత్యేకపరచితివి <br>\n" +
                "  యుగయుగములు నీతో<br>\n" +
                "  నివసించె భాగ్యము నిచ్చితివి<br>\n" +
                "  ||చప్పట్లు ||<br><br>\n" +
                "  హల్లెలూయా ఆరాధన రాజాధిరాజు యేసునకే<br>\n" +
                "  మహిమయు ఘనతయు సర్వాధికారి క్రీస్తునకే </span>"));
    }*/

    public static String getTransliteratedText(String textToTransliterate) {

        textToTransliterate = cleanHTMLTagsFromText(textToTransliterate);

        String[] textToTransliterateSentences = textToTransliterate.split("\n");
        textToTransliterate = "";
        for (String textToTransliterateSentence : textToTransliterateSentences) {
            textToTransliterate = textToTransliterate.concat(textToTransliterateSentence.trim()).concat("\\n");
        }

        //System.out.println(textToTransliterate);
        Context context = Context.create();
        context.getBindings("js").putMember("console", System.out);

        InputStream padmaJs = JavaScriptTransliterator.class.getResourceAsStream("/js/encodings/Padma.js");
        InputStream rtsJs = JavaScriptTransliterator.class.getResourceAsStream("/js/encodings/RTS.js");
        InputStream sharedJs = JavaScriptTransliterator.class.getResourceAsStream("/js/encodings/Shared.js");
        InputStream syllableJs = JavaScriptTransliterator.class.getResourceAsStream("/js/encodings/Syllable.js");
        InputStream teluguJs = JavaScriptTransliterator.class.getResourceAsStream("/js/encodings/Telugu.js");
        InputStream unicodeJs = JavaScriptTransliterator.class.getResourceAsStream("/js/encodings/Unicode.js");
        InputStream parserJs = JavaScriptTransliterator.class.getResourceAsStream("/js/parsers/parser.js");
        InputStream rtsParserJs = JavaScriptTransliterator.class.getResourceAsStream("/js/parsers/RTSParser.js");
        InputStream rtsTransformersjs = JavaScriptTransliterator.class.getResourceAsStream("/js/transformers/RTSTransformer.js");
        InputStream transformersJs = JavaScriptTransliterator.class.getResourceAsStream("/js/transformers/Transformer.js");
        InputStream startHereJs = JavaScriptTransliterator.class.getResourceAsStream("/js/transformers/startHere.js");

        String str1 = new BufferedReader(new InputStreamReader(padmaJs)).lines().collect(Collectors.joining("\n"));
        String str2 = new BufferedReader(new InputStreamReader(rtsJs)).lines().collect(Collectors.joining("\n"));
        String str3 = new BufferedReader(new InputStreamReader(sharedJs)).lines().collect(Collectors.joining("\n"));
        String str4 = new BufferedReader(new InputStreamReader(syllableJs)).lines().collect(Collectors.joining("\n"));
        String str5 = new BufferedReader(new InputStreamReader(teluguJs)).lines().collect(Collectors.joining("\n"));
        String str6 = new BufferedReader(new InputStreamReader(unicodeJs)).lines().collect(Collectors.joining("\n"));
        String str7 = new BufferedReader(new InputStreamReader(parserJs)).lines().collect(Collectors.joining("\n"));
        String str8 = new BufferedReader(new InputStreamReader(rtsParserJs)).lines().collect(Collectors.joining("\n"));
        String str9 =
                new BufferedReader(new InputStreamReader(rtsTransformersjs)).lines().collect(Collectors.joining("\n"));
        String str10 = new BufferedReader(new InputStreamReader(transformersJs)).lines().collect(Collectors.joining(
                "\n"));
        String str11 = new BufferedReader(new InputStreamReader(startHereJs)).lines().collect(Collectors.joining(
                "\n"));

        context.eval("js", str1);
        context.eval("js", str2);
        context.eval("js", str3);
        context.eval("js", str4);
        context.eval("js", str5);
        context.eval("js", str6);
        context.eval("js", str7);
        context.eval("js", str8);
        context.eval("js", str10);
        context.eval("js", str9);
        context.eval("js", str11);


        /*context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/encodings/Padma.js');", "Padma.js").build());*/
       /* context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/encodings/RTS.js');", "RTS.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/encodings/Shared.js');", "Shared.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/encodings/Syllable.js');", "Syllable.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/encodings/Telugu.js');", "Telugu.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/encodings/Unicode.js');", "Unicode.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/parsers/parser.js');", "parser.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/parsers/RTSParser.js');", "RTSParser.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/transformers/RTSTransformer.js');", "RTSTransformer.js").build());
        context.eval(Source.newBuilder("js", "load('D:/MYCODE/GITHUBCODE/LYRICSCONTENTGENERATOR/lyrics-content-generator/src/main/resources/js/transformers/Transformer.js');", "Transformer.js").build());
*/

        /*context.eval("js", (CharSequence) new FileReader("src/main/resources/js/encodings/Padma.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/encodings/RTS.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/encodings/Shared.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/encodings/Syllable.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/encodings/Telugu.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/encodings/Unicode.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/parsers/parser.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/parsers/RTSParser.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/transformers/RTSTransformer.js"));
        context.eval("js", (CharSequence) new FileReader("src/main/resources/js/transformers/Transformer.js"));*/

        /* Value func = context.getBindings("js").getMember("convert");
        Value result = func.execute(textToTransliterate);*/

        Value result = context.eval("js", "vTransform('" + textToTransliterate + "')");

        //System.out.println(result);
        context.close();
        return result.asString();
    }

    private static String cleanHTMLTagsFromText(String textToTransliterate) {
        return textToTransliterate.replaceAll("<span>", "").replaceAll("</span>", "")
                .replaceAll(" <br>", "").replaceAll("<br>", "").replaceAll("</br>", "");
    }

    public static String getTransliteratedText2(String textToTransliterate) throws IOException {
        String resultValue = null;
        try {
            // Create a script engine manager
            ScriptEngineManager manager = new ScriptEngineManager();

            // Get the JavaScript engine
            ScriptEngine engine = manager.getEngineByName("js");

            if (engine == null) {
                System.err.println("JavaScript engine not found.");
                return null;
            }

            // Load and execute the JavaScript files
            engine.eval(new java.io.FileReader("src/main/resources/js/encodings/Padma.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/encodings/RTS.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/encodings/Shared.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/encodings/Syllable.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/encodings/Telugu.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/encodings/Unicode.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/parsers/parser.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/parsers/RTSParser.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/transformers/RTSTransformer.js"));
            engine.eval(new java.io.FileReader("src/main/resources/js/transformers/Transformer.js"));

            // Execute the function in myScript1.js with the string parameters
            Object result = ((Invocable) engine).invokeFunction("convert", textToTransliterate);

            // Get the result and print it
            resultValue = (String) result;
            System.out.println(resultValue);
        } catch (ScriptException | NoSuchMethodException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return resultValue;
    }
}
