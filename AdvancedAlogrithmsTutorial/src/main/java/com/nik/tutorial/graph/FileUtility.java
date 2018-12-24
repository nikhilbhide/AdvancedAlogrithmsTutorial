package com.nik.tutorial.graph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Class provides utility functions for file system related options.
 *  
 * @author nikhil.bhide
 *
 */
public class FileUtility {
	public ArrayList<String> readFileContents(String filePath) {
		ArrayList<String> fileContents = new ArrayList();
		
		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.forEach(line-> fileContents.add(line));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContents;
	}
}