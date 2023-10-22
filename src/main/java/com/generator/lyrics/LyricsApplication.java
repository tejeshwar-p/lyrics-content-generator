package com.generator.lyrics;

import com.generator.lyrics.converter.HtmlToXlsxConverter;
import com.generator.lyrics.generator.ScriptGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LyricsApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(LyricsApplication.class, args);

		HtmlToXlsxConverter.convert();
		Thread.sleep(1000);

		ScriptGenerator.generateScripts();
		Thread.sleep(2000);

		System.exit(-1);
	}

}
