package edu.nesterenko.parcer.run;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.nesterenko.parcer.entity.Component;
import edu.nesterenko.parcer.exception.LogicalException;
import edu.nesterenko.parcer.logic.Parcer;

public class Runner {
	public static void main(String... args) throws LogicalException, IOException {
		Parcer parcer = new Parcer();
		parcer.configure("config\\conf.properties");
		String text = new String(Files.readAllBytes(Paths.get("text.txt")), StandardCharsets.UTF_8);
		parcer.parce(text);
		Component component = parcer.getHierarchy();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("exit.txt"), "utf-8"));
		String revivedText = parcer.reviveText();
		writer.write(revivedText);
		writer.close();
	}
}
