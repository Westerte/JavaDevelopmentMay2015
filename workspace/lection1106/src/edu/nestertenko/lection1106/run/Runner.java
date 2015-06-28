package edu.nestertenko.lection1106.run;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;


public class Runner {
	public static void main(String... args) {
		File file = new File("exit.txt");
		try {
			FileSystem fs = FileSystems.getDefault();
			Path path = fs.getPath("text.txt");
			Stream<String> obStream = Files.lines(path, StandardCharsets.UTF_8);
			List<String> obList = (List) obStream.collect(Collectors.toList());
			System.out.println(obList.getClass());
			for(String item : obList) {
				System.out.println(item);
			}
		}
		catch(IOException e) {
			
		}
	}
}
