package edu.nesterenko.parcer.filereader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.nesterenko.parcer.exception.PhisicalException;

public class FileReader {
	private FileReader() {}
	
	public static String readFromFIle(String filePath) throws PhisicalException {
		String text;
		try {
			text = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new PhisicalException(e);
		}
		return text;
	}
	
}
