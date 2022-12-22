package com.benwager12.epos.utilities;

import com.benwager12.epos.displayable.Folder;
import com.benwager12.epos.displayable.Item;

import java.util.ArrayList;
import java.util.stream.Stream;

public class CartUtilities {

	/** The path to the list of items. */
	public static final String itemListPath = "pages/items.txt";

	/** The current page name. */
	public static String pageName = "1";

	// Loaded items are either integers or folders.
	/** The currently loaded displayables. */
	public static ArrayList<Object> loadedDisplayables = new ArrayList<>();

	/** The currently loaded items. */
	private static ArrayList<Item> itemList;

	/**
	 * Loads the displayables from the page.
	 *
	 * @param page The page to load.
	 *
	 * @return The loaded displayables.
	 */
	public static ArrayList<Object> getDisplayablesFromPage(String page) {
		String items = FileUtilities.loadTextFile("pages/" + page + ".page");
		assert items != null;

		Stream<String> lines = items.lines();

		ArrayList<Object> displayables = new ArrayList<>();
		lines.forEach(line -> displayables.add(parseDisplayable(line)));

		return displayables;
	}

	/**
	 * Loads the displayables into the loadedDisplayables variable.
	 *
	 * @param page The page to load.
	 */
	public static void loadDisplayablesFromPage(String page) {
		loadedDisplayables = getDisplayablesFromPage(page);
	}

	/**
	 * Loads the displayables into the loadedDisplayables variable from the pageName variable.
	 */
	public static void loadDisplayablesFromPage() {
		loadDisplayablesFromPage(pageName);
	}

	/**
	 * Parse a single line of a page file.
	 * @param item The line to parse.
	 * @return The parsed item - either an integer or a folder.
	 */
	private static Object parseDisplayable(String item) {
		// Item can be either folders or items.
		boolean isFolder = item.endsWith(".page");

		if (isFolder) {
			// Folder
			String[] itemSplit = item.split(";");

			String folderName = itemSplit[0];
			String pagePath = itemSplit[1];

			return new Folder(folderName, pagePath);
		}

		// Item - the item ID.
		return Integer.parseInt(item);
	}

	/**
	 * Retrieves the items from the item list.
	 * @param path The path to the item list.
	 * @return The items.
	 */
	public static ArrayList<Item> getItemList(String path) {
		String itemList = FileUtilities.loadTextFile(path);
		assert itemList != null;

		String[] lines = itemList.split("\n");

		ArrayList<Item> items = new ArrayList<>();

		for (int ln = 1; ln <= lines.length; ln++) {
			String item = lines[ln - 1];

			String[] itemSplit = item.split(";");

			String itemName = itemSplit[0];
			double itemPrice = Double.parseDouble(itemSplit[1]);
			String itemDescription = itemSplit[2];

			items.add(new Item(itemName, itemPrice, itemDescription));
		}

		return items;
	}

	/**
	 * Retrieves the items from the item list.
	 * @return The items.
	 */
	public static ArrayList<Item> getItemList() {
		return getItemList(itemListPath);
	}

	/**
	 * Loads the items from the item list.
	 * @param path The path to the item list.
	 */
	public static void loadItemList(String path) {
		itemList = getItemList(path);
	}

	/**
	 * Loads the items from the item list.
	 */
	public static void loadItemList() {
		loadItemList(itemListPath);
	}
}
