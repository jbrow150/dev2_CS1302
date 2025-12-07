package edu.westga.cs1302.comic_collection_app.view;

import edu.westga.cs1302.comic_collection_app.model.ComicCollection;
import edu.westga.cs1302.comic_collection_app.model.Comic;
import edu.westga.cs1302.comic_collection_app.viewmodel.FindComicViewModel;
import edu.westga.cs1302.comic_collection_app.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {

	@FXML
	private AnchorPane guiPane;
	@FXML
	private TextField collectionNameField;
	@FXML
	private Button addCollectionButton;
	@FXML
	private Button removeCollectionButton;
	@FXML
	private Button addComicButton;
	@FXML
	private Button removeComicButton;
	@FXML
	private ListView<ComicCollection> collectionsListView;
	@FXML
	private ListView<Comic> comicsListView;
	@FXML
	private MenuItem removeContextMenuItem;
	@FXML
	private MenuItem removeComicContextMenuItem;
	
	@FXML
	private Button findComicButton;

	private MainWindowViewModel vm;

	@FXML
	void initialize() {
		this.vm = new MainWindowViewModel();

		// Bind collection TextField
		this.vm.getNewCollectionName().bindBidirectional(this.collectionNameField.textProperty());

		// Bind collections ListView
		this.collectionsListView.setItems(this.vm.getCollections());

		// Bind selected collection
		this.collectionsListView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldVal, newVal) -> this.vm.setSelectedCollection(newVal));

		// Add Collection button
		this.addCollectionButton.setOnAction(event -> {
			try {
				this.vm.addCollection();
			} catch (IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage());
			}
		});

		// Remove Collection button
		this.removeCollectionButton.setOnAction(event -> this.vm.removeCollection());

		// Context menu remove collection
		this.removeContextMenuItem.setOnAction(event -> this.vm.removeCollection());

		// Disable Add Collection button when empty
		this.addCollectionButton.disableProperty().bind(this.collectionNameField.textProperty().isEmpty());

		// Comics ListView
		this.comicsListView.setItems(this.vm.getComics());

		// Bind selected comic
		this.comicsListView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldVal, newVal) -> this.vm.getSelectedComic().set(newVal));

		// Add Comic button
		this.addComicButton.setOnAction(e -> openAddComicWindow());

		// Remove Comic button
		this.removeComicButton.setOnAction(e -> this.vm.removeComic());

		// Context menu remove comic
		this.removeComicContextMenuItem.setOnAction(e -> this.vm.removeComic());
		
		 findComicButton.setOnAction(e -> openFindComicWindow());
	}
	
	private void openFindComicWindow() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(
	                "/edu/westga/cs1302/comic_collection_app/view/FindComicWindow.fxml"));
	        Stage stage = new Stage();
	        stage.setScene(new Scene(loader.load()));

	        FindComicWindow controller = loader.getController();
	        // Pass all collections to the ViewModel
	        controller.init(new FindComicViewModel(vm.getCollections()));

	        stage.showAndWait();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}
	
	private void openAddComicWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/edu/westga/cs1302/comic_collection_app/view/AddComicWindow.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));
			AddComicWindow controller = loader.getController();
			controller.init(vm);
			stage.showAndWait();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}