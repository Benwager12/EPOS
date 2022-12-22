package com.benwager12.epos.utilities;

import com.benwager12.epos.Main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This contains methods all used in File manipulation and loading.
 *
 * @author Ben Wager
 * @since 21/12/2022
 */
public class FileUtilities {

	/**
	 * Gets the contents of a file in the resources folder.
	 * @param path The path to the file.
	 * @return The URL of the file.
	 */
	private static URL loadResource(String path) {
		URL resource = Main.class.getResource(path);
		assert resource != null;

		return resource;
	}

	/**
	 * Loads a text file from the resources folder.
	 * @param path The path to the file, relative to the resources folder.
	 * @return The contents of the file.
	 */
	public static String loadTextFile(String path) {
		URL resource = loadResource(path);
		final String file;

		try {
			file = Files.readString(Path.of(resource.toURI()), StandardCharsets.UTF_8);
		} catch (IOException | URISyntaxException e) {
			System.out.println("Encountered error loading page file.");
			return null;
		}

		return file;
	}
}
