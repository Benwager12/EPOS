package com.benwager12.epos.controllers;

import com.benwager12.epos.displayable.Folder;
import com.benwager12.epos.utilities.CartUtilities;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * The view for the cart, which includes the addition of items.
 *
 * @author Ben Wager
 * @since 21/12/2022
 */
public class CartViewController {

	public Button product1;
	public Button product2;
	public Button product3;
	public Button product4;
	public Button product5;
	public Button product6;
	public Button product7;
	public Button product8;
	public Button product9;
	public Button backBtn;

	public void initialize() {
		loadButtonView();
	}

	private void loadButtonView() {
		ArrayList<Object> displayables = CartUtilities.loadedDisplayables;

		Button[] productButtons = {product1, product2, product3, product4, product5,
				product6, product7, product8, product9};

		for (int btnNumber = 0; btnNumber < productButtons.length; btnNumber++) {
			Button productButton = productButtons[btnNumber];
			Object currentDisplayable = displayables.get(btnNumber);

			if (currentDisplayable instanceof Folder f) {
				productButton.setText(f.getFolderName());
			} else if (currentDisplayable instanceof Integer i) {
				productButton.setText(CartUtilities.getItemList().get(i - 1).getName());
			}
		}
	}

	public void clickProduct(ActionEvent actionEvent) {
		if (!(actionEvent.getSource() instanceof Button b)) {
			return;
		}
		System.out.printf("%s was clicked.%n", b.getId());
		System.out.println(CartUtilities.loadedDisplayables);
	}
}
