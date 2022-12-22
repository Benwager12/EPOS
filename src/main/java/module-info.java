module com.benwager12.epos {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.benwager12.epos to javafx.fxml;
	exports com.benwager12.epos;
	exports com.benwager12.epos.controllers;
	exports com.benwager12.epos.utilities;
	opens com.benwager12.epos.utilities to javafx.fxml;
	exports com.benwager12.epos.displayable;
	opens com.benwager12.epos.displayable to javafx.fxml;
}