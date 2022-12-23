package com.benwager12.epos.controllers;

import com.benwager12.epos.displayable.Folder;
import com.benwager12.epos.displayable.Item;
import com.benwager12.epos.utilities.CartUtilities;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static com.benwager12.epos.utilities.CartUtilities.*;

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
	public Button previousPageBtn;
	public Button nextPageBtn;
	public VBox productNameBox;
	public VBox productPriceBox;
	public VBox productDeleteBox;
	public HBox productView;

	private int pageNumber;

	public void initialize() {
		loadPage();
		pageNumber = 0;
	}

	private void loadProductButtons() {

		Button[] productButtons = {product1, product2, product3, product4, product5,
				product6, product7, product8, product9};
		ArrayList<Item> loadedItems = getLoadedItemList();

		for (int btnNumber = 0; btnNumber < productButtons.length; btnNumber++) {
			Button productButton = productButtons[btnNumber];

			Object currentDisplayable;
			try {
				currentDisplayable = loadedDisplayables.get(btnNumber);
				productButton.setVisible(true);
			} catch (IndexOutOfBoundsException e) {
				productButton.setVisible(false);
				continue;
			}

			if (currentDisplayable instanceof Folder f) {
				productButton.setText(f.getFolderName());
			} else if (currentDisplayable instanceof Integer i) {
				productButton.setText(loadedItems.get(i - 1).getName());
			}
		}
	}

	private void loadPageChangeButtons() {

		previousPageBtn.setVisible(pageNumber != 0);
		nextPageBtn.setVisible(loadedDisplayables.size() > 9  * (pageNumber + 1));
	}

	public void onProductClick(ActionEvent actionEvent) {
		if (!(actionEvent.getSource() instanceof Button b)) {
			return;
		}
		int buttonNumber = Integer.parseInt(b.getId().substring(7));
		System.out.printf("Button number %d was clicked.%n", buttonNumber);

		Object displayable = loadedDisplayables.get(buttonNumber - 1 + (pageNumber * 9));

		if (displayable instanceof Folder f) {
			CartUtilities.pageName = f.getPagePath();
			loadPage();
		} else if (displayable instanceof Integer i) {
			Item item = getLoadedItemList().get(i - 1);
			addToBasket(item);
		}
	}

	private void loadPage() {
		loadDisplayablesFromPage();
		loadProductButtons();
		loadPageChangeButtons();
	}

	public void onBackBtnPressed(ActionEvent ignoredActionEvent) {
		if (!pageName.equalsIgnoreCase("index.page")) {
			pageName = "index.page";
			loadPage();
			return;
		}

		// Go back to the main menu.
	}

	private void addToBasket(Item i) {
		basket.add(i);
		displayBasket();
	}

	private void displayBasket() {
		clearCart();
		basket.forEach(item -> {
			productNameBox.getChildren().add(new Label(item.getName()));
			String formattedPrice = String.format("%.2f", item.getPrice());
			productPriceBox.getChildren().add(new Label(formattedPrice));
			productDeleteBox.getChildren().add(makeDeleteLabel());
		});
	}

	private Label makeDeleteLabel() {
		Label deleteLabel = new Label("x");
		deleteLabel.setOnMouseClicked(mouseEvent -> {
			int index = productDeleteBox.getChildren().indexOf(deleteLabel);
			basket.remove(index - 1);

			displayBasket();
		});
		return deleteLabel;
	}

	public void onClearBtnPressed(ActionEvent ignoredActionEvent) {
		basket.clear();
		clearCart();
	}

	private void clearCart() {
		while (productNameBox.getChildren().size() > 1) {
			productView.getChildren().forEach(node -> {
				if (node instanceof VBox v) {
					v.getChildren().remove(1);
				}
			});
		}
	}
}
