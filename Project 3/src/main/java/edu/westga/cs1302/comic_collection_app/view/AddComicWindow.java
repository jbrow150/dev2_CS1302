package edu.westga.cs1302.comic_collection_app.view;

import edu.westga.cs1302.comic_collection_app.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddComicWindow {

	@FXML
	private TextField titleField;
	@FXML
	private TextField issueNumberField;
	@FXML
	private Button addButton;
	@FXML
	private Button cancelButton;

	private MainWindowViewModel vm;

	public void init(MainWindowViewModel viewModel) {
		this.vm = viewModel;

		// Bind fields
		titleField.textProperty().bindBidirectional(vm.getNewComicTitle());

		issueNumberField.textProperty().addListener((obs, oldVal, newVal) -> {
			try {
				vm.getNewComicIssue().set(Integer.parseInt(newVal));
			} catch (NumberFormatException e) {
				vm.getNewComicIssue().set(0);
			}
		});

		// Enable add only when both fields are not empty
		addButton.disableProperty()
				.bind(titleField.textProperty().isEmpty().or(issueNumberField.textProperty().isEmpty()));

		addButton.setOnAction(e -> {
			vm.addComic();
			closeWindow();
		});

		cancelButton.setOnAction(e -> closeWindow());
	}

	private void closeWindow() {
		Stage stage = (Stage) addButton.getScene().getWindow();
		stage.close();
	}
}
