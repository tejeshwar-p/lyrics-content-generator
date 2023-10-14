package com.generator.lyrics;

import com.generator.lyrics.converter.HtmlToXlsxConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class LyricsApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(LyricsApplication.class, args);
		System.out.println("Hello world");
		HtmlToXlsxConverter.convert();
		Thread.sleep(5000);
		System.exit(-1);
	}

}
