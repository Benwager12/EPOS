package com.benwager12.epos.controllers;

import com.benwager12.epos.utilities.ViewUtilities;
import javafx.event.ActionEvent;

public class AdminViewController {
	public void onViewItemListPressed(ActionEvent ignoredActionEvent) {
		ViewUtilities.setScene("admin-items-view");
	}

	public void onViewPageFilesPressed(ActionEvent ignoredActionEvent) {

	}
}
