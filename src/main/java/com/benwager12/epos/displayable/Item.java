package com.benwager12.epos.displayable;

public record Item(String name, double price, String description) {

	@Override
	public String toString() {
		return "Item{" +
				"name='" + name + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				'}';
	}
}
