package com.benwager12.epos;

import com.benwager12.epos.utilities.CartUtilities;
import com.benwager12.epos.utilities.ViewUtilities;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage stage) {
		Main.stage = stage;

		CartUtilities.loadItemList();
		CartUtilities.loadDisplayablesFromPage();

		Scene scene = ViewUtilities.makeScene("main-menu-view");

		stage.setMinWidth(615);
		stage.setMinHeight(430);
		stage.setTitle("EPOS");

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}