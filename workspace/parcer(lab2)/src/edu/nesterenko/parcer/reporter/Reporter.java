package edu.nesterenko.parcer.reporter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import edu.nesterenko.parcer.exception.PhisicalException;

public class Reporter {
	private Reporter() {}
	
	public static void report(String text, String filePath, String command) throws PhisicalException {
		if(text == null) {
			throw new PhisicalException("text must be not null");
		}
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(filePath, true), "utf-8"))){			
				writer.append("---" + command.toUpperCase() + "---\n\n");
				writer.append(text);
				writer.append("\n\n");
		} catch (IOException e) {
			throw new PhisicalException(e);
		}
	}
	
	public static void report(List<String> stringList, String filePath, String command) throws PhisicalException {
		if(stringList == null) {
			throw new PhisicalException("stringList must be not null");
		}
		StringBuilder stringBuilder = new StringBuilder();
		for(String stringElement: stringList) {
			stringBuilder.append(stringElement);
			stringBuilder.append("\n");
		}
		report(stringBuilder.toString(), filePath, command);
	}
}
