package com.benwager12.epos.controllers;

import com.benwager12.epos.utilities.ViewUtilities;
import javafx.event.ActionEvent;

public class MainMenuViewController {
	public void onUserViewPressed(ActionEvent ignoredActionEvent) {
		ViewUtilities.setScene("cart-view");
	}

	public void onAdminViewPressed(ActionEvent ignoredActionEvent) {

	}
}
