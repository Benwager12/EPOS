package com.benwager12.epos.displayable;

public record Folder(String folderName, String pagePath) {

	@Override
	public String toString() {
		return "Folder{" +
				"folderName='" + folderName + '\'' +
				", pagePath='" + pagePath + '\'' +
				'}';
	}
}
