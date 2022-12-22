package com.benwager12.epos.displayable;

public class Item extends DisplayableObject {

	private final String name;
	private final double price;
	private final String description;

	public Item(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String display() {
		return getName();
	}
}
