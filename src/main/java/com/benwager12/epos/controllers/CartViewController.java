package com.benwager12.epos.controllers;

import com.benwager12.epos.displayable.Folder;
import com.benwager12.epos.displayable.Item;
import com.benwager12.epos.utilities.CartUtilities;
import com.benwager12.epos.utilities.ViewUtilities;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	public Label priceLbl;

	private int pageNumber;

	public void initialize() {
		loadPage();
		pageNumber = 0;
	}

	private void loadProductButtons() {
		Button[] productButtons = {product1, product2, product3, product4, product5,
				product6, product7, product8, product9};

		for (int btnNumber = 0; btnNumber < productButtons.length; btnNumber++) {
			Button productButton = productButtons[btnNumber];

			Object currentDisplayable = getDisplayableFromNumber(btnNumber);
			productButton.setVisible(currentDisplayable != null);

			if (currentDisplayable == null) {
				continue;
			}

			productButton.setText(getBtnText(currentDisplayable));
		}
	}

	private Object getDisplayableFromNumber(int number) {
		try {
			return loadedDisplayables.get(number + pageNumber * 9);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	private String getBtnText(Object displayable) {
		if (displayable instanceof Folder f) {
			return f.folderName();
		} else if (displayable instanceof Integer i) {
			return getLoadedItemList().get(i - 1).name();
		}
		return null;
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
		Object displayable = loadedDisplayables.get(buttonNumber - 1 + (pageNumber * 9));

		// If the displayable is a folder, load the folder.
		if (displayable instanceof Folder f) {
			CartUtilities.pageName = f.pagePath();
			loadPage();
			// If the displayable is an item, add it to the cart.
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

		clearCart();
		ViewUtilities.setScene("main-menu-view");
	}

	private void addToBasket(Item i) {
		basket.add(i);
		displayBasket();
		updatePrice();
	}

	private void displayBasket() {
		clearCart();
		basket.forEach(item -> {
			productNameBox.getChildren().add(new Label(item.name()));
			String formattedPrice = String.format("%.2f", item.price());
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
		updatePrice();
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

	private void updatePrice() {
		double price = basket.stream().mapToDouble(Item::price).sum();
		priceLbl.setText(String.format("£%.2f", price));
	}

	public void onNextBtnPressed(ActionEvent ignoredActionEvent) {
		pageNumber++;
		loadPage();
	}

	public void onPrevBtnPressed(ActionEvent ignoredActionEvent) {
		pageNumber--;
		loadPage();
	}
}
