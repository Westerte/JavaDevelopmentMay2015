package edu.nesterenko.parcer.run;


import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import edu.nesterenko.parcer.exception.LogicalException;
import edu.nesterenko.parcer.exception.PhisicalException;
import edu.nesterenko.parcer.filereader.FileReader;
import edu.nesterenko.parcer.logic.TextEditor;
import edu.nesterenko.parcer.logic.TextFinder;
import edu.nesterenko.parcer.parser.Parser;
import edu.nesterenko.parcer.reporter.Reporter;

public class Runner {
	private final static String SAVE_FILE_PATH = "exit.txt";
	private final static Logger LOG = Logger.getLogger(Runner.class);
	
	static {
		new DOMConfigurator().doConfigure("config/log4j.xml", LogManager.getLoggerRepository());
	}
	
	public static void main(String... args) {
		try {
			Parser parser = new Parser();
			parser.configure("config\\conf.properties");
			String text = FileReader.readFromFIle("text.txt");
			parser.parse(text);	
			String revivedText = parser.reviveText();
			Reporter.report(revivedText , SAVE_FILE_PATH, "Revive Text");
			List<String> strings = TextFinder.findSentenceOrderedByWordCount(parser);
			Reporter.reportWithStringList(strings, SAVE_FILE_PATH, "Find Sentence Ordered By Word Count");
			TextEditor.deleteWordsStartingWithConsonant(parser, 3);
			Reporter.report("DONE", SAVE_FILE_PATH, "Delete Words Starting With Consonant");
			TextEditor.swapFirstAndLastWords(parser);
			Reporter.report("DONE", SAVE_FILE_PATH, "Swap First And Last Words");
			revivedText = parser.reviveText();
			Reporter.report(revivedText , SAVE_FILE_PATH, "Revive Text");
			strings = TextFinder.findSentenceOrderedByWordCount(parser);
		} catch (PhisicalException | LogicalException e) {
			LOG.error(e);
		}
	}
}
