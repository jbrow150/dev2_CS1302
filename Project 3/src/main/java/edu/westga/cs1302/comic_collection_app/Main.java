package edu.westga.cs1302.comic_collection_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    private static final String WINDOW_TITLE = "Comic Collection";
    private static final String MAIN_WINDOW_FXML = "/edu/westga/cs1302/comic_collection_app/view/MainWindow.fxml";

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane pane = loadGui();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setTitle(WINDOW_TITLE);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load GUI!");
            e.printStackTrace();
        }
    }

    private Pane loadGui() throws IOException {
        URL fxmlLocation = getClass().getResource(MAIN_WINDOW_FXML);
        if (fxmlLocation == null) {
            throw new IOException("FXML file not found! Checked path: " + MAIN_WINDOW_FXML);
        }
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        return loader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}