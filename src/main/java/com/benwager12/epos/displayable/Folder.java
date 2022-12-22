package com.benwager12.epos.displayable;

public class Folder extends DisplayableObject {

	private final String folderName;
	private final String pagePath;

	public Folder(String folderName, String pagePath) {
		this.folderName = folderName;
		this.pagePath = pagePath;
	}

	public String getFolderName() {
		return folderName;
	}

	public String getPagePath() {
		return pagePath;
	}

	@Override
	public String display() {
		return getFolderName();
	}
}
