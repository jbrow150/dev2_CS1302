package edu.westga.cs1302.bill.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Entry point for the program
 * 
 * @version Fall 2025
 */
public class Main extends Application {
	private static final String WINDOW_TITLE = "Bill";
	private static final String GUI_RESOURCE = "/edu/westga/cs1302/bill/view/MainWindow.fxml";

	/**
	 * JavaFX entry point.
	 *
	 * @param primaryStage the primary stage for this application
	 * @throws IOException if the FXML file cannot be loaded
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		var resource = getClass().getResource(GUI_RESOURCE);
		if (resource == null) {
			throw new IOException("FXML file not found: " + GUI_RESOURCE);
		}

		Parent parent = FXMLLoader.load(resource);
		Scene scene = new Scene(parent);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Primary Java entry point.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}