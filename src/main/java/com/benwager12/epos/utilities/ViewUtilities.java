package com.benwager12.epos.utilities;

import com.benwager12.epos.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ViewUtilities {

	public static Scene makeScene(String fxmlName) {
		FXMLLoader fxmlLoader = new FXMLLoader(
				Main.class.getResource("views/" + fxmlName + ".fxml")
		);

		try {
			return new Scene(fxmlLoader.load(), 600, 400);
		} catch (IOException e) {
			System.out.println("Encountered error loading \""+ fxmlName +"\".");
			throw new RuntimeException(e);
		}
	}

	public static void setScene(String fxmlName) {
		Main.getStage().setScene(makeScene(fxmlName));
	}
}
