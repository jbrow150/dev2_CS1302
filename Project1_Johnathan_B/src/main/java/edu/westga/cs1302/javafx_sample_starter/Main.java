package edu.westga.cs1302.javafx_sample_starter;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the program
 *
 * @author CS 1302
 * @version Fall 2025
 */
public class Main extends Application {
	private static final String WINDOW_TITLE = "JavaFX Starter Sample";
	private static final String GUI_RESOURCE = "views/MainWindow.fxml";

	/**
	 * JavaFX entry point.
	 * 
	 * @throws IOException
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws IllegalArgumentException, IOException {
		Parent parent = FXMLLoader.load(getClass().getResource(Main.GUI_RESOURCE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Primary Java entry point.
	 *
	 * @precondition none
	 * @postcondition none
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Main.launch(args);
	}

}