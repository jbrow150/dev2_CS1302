package edu.westga.cs1302.comic_collection_app.view;

import edu.westga.cs1302.comic_collection_app.model.Comic;
import edu.westga.cs1302.comic_collection_app.viewmodel.FindComicViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FindComicWindow {

    @FXML
    private TextField titleField;
    @FXML
    private TextField issueField;
    @FXML
    private Button searchButton;
    @FXML
    private Button closeButton;
    @FXML
    private Label resultLabel;

    private FindComicViewModel vm;

    public void init(FindComicViewModel viewModel) {
        this.vm = viewModel;

        // Bindings
        this.vm.searchTitleProperty().bindBidirectional(titleField.textProperty());

        // Issue field: only integer
        issueField.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                vm.searchIssueNumberProperty().set(Integer.parseInt(newVal));
            } catch (NumberFormatException e) {
                vm.searchIssueNumberProperty().set(0);
            }
        });

        // Button actions
        searchButton.setOnAction(e -> searchComic());
        closeButton.setOnAction(e -> closeWindow());
    }

    private void searchComic() {
        vm.searchComic();
        Comic found = vm.resultProperty().get();
        if (found != null) {
            resultLabel.setText("Found: " + found);
        } else {
            resultLabel.setText("Comic not found.");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}