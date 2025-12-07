package testPackage;


import static org.junit.jupiter.api.Assertions.*;

import edu.westga.cs1302.comic_collection_app.viewmodel.MainWindowViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for AddComicWindow.
 */
public class AddComicWindowViewModelTest {

    @FXML
    private TextField titleField;

    @FXML
    private TextField issueNumberField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    private MainWindowViewModel vm;

    /**
     * Initializes this window with the shared ViewModel.
     */
    public void init(MainWindowViewModel viewModel) {
        this.vm = viewModel;

        // Bind title
        this.titleField.textProperty().bindBidirectional(this.vm.getNewComicTitle());

        // Bind numeric issue input safely
        this.issueNumberField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.matches("\\d+")) {
                this.vm.getNewComicIssue().set(Integer.parseInt(newVal));
            } else {
                this.vm.getNewComicIssue().set(0);
            }
        });

        // Add button enabled only when BOTH fields are valid
        this.addButton.disableProperty().bind(
            this.titleField.textProperty().isEmpty()
            .or(this.issueNumberField.textProperty().isEmpty())
        );

        // Add Comic
        this.addButton.setOnAction(e -> {
            try {
                this.vm.addComic();
                this.closeWindow();
            } catch (IllegalArgumentException ex) {
                // You may later add an error label — for now print
                System.err.println("Error adding comic: " + ex.getMessage());
            }
        });

        // Cancel action
        this.cancelButton.setOnAction(e -> this.closeWindow());
    }

    /**
     * Closes the window.
     */
    private void closeWindow() {
        Stage stage = (Stage) this.addButton.getScene().getWindow();
        stage.close();
    }
}