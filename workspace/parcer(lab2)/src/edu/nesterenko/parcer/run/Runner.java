package edu.nesterenko.parcer.run;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import edu.nesterenko.parcer.entity.Component;
import edu.nesterenko.parcer.exception.LogicalException;
import edu.nesterenko.parcer.exception.PhisicalException;
import edu.nesterenko.parcer.logic.Parser;
import edu.nesterenko.parcer.logic.TextEditor;
import edu.nesterenko.parcer.logic.TextFinder;

public class Runner {
	public static void main(String... args) throws LogicalException, IOException, PhisicalException {
		Parser parcer = new Parser();
		parcer.configure("config\\conf.properties");
		String text = new String(Files.readAllBytes(Paths.get("text.txt")), StandardCharsets.UTF_8);
		parcer.parce(text);
		Component component = parcer.getHierarchy();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("exit.txt"), "utf-8"));
		String revivedText = parcer.reviveText();
		List<String> strings = TextFinder.findSentenceOrderedByWordCount(parcer);
		for(String string : strings) {
			System.out.println(string);
		}
		System.out.println();
		TextEditor.deleteWordsStartingWithConsonant(parcer, 3);
		strings = TextFinder.findSentenceOrderedByWordCount(parcer);
		for(String string : strings) {
			System.out.println(string);
		}
		writer.write(revivedText);
		writer.close();
	}
}
