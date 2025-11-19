package edu.westga.cs1302.password_generator.view;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private Label errorTextLabel;
    @FXML private Label minLengthErrorText;
    @FXML private Button generatePasswordButton;
    @FXML private ListView<String> passwordHistory;
    @FXML private MenuItem menuSave;
    @FXML private MenuItem menuAbout;
    @FXML private MenuItem menuClose;
    
    private ViewModel vm;
    
    @FXML
    void initialize() {
    	this.vm = new ViewModel();
        this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
        this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
        this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
        this.minimumLength.setText(this.vm.getMinimumLength().getValue());
        this.vm.getMinimumLength().bind(this.minimumLength.textProperty());

        this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
        this.passwordHistory.setItems(this.vm.getPasswordHistory());

        // Enable/Disable Generate Password button based on minimumLength input
        this.minimumLength.textProperty().addListener((obs, oldVal, newVal) -> {
            boolean isValid = newVal.matches("\\d+") && Integer.parseInt(newVal) > 0;
            this.minLengthErrorText.setVisible(!isValid);
            this.generatePasswordButton.setDisable(!isValid);
        });

        // Menu actions
        this.menuSave.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            File file = fileChooser.showSaveDialog(this.generatePasswordButton.getScene().getWindow());
            if (file != null) {
                try (PrintWriter writer = new PrintWriter(file)) {
                    for (String password : this.vm.getPasswordHistory()) {
                        writer.println(password);
                    }
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving file: " + e.getMessage());
                    alert.showAndWait();
                }
            }
        });

        this.menuAbout.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About Password Generator");
            alert.setHeaderText("Password Generator Project");
            alert.setContentText("Author: Your Name\nThis project generates passwords based on user-specified rules.");
            alert.showAndWait();
        });

        this.menuClose.setOnAction(event -> {
            ((Node)(this.generatePasswordButton)).getScene().getWindow().hide();
        });

        // Generate Password button action
        this.generatePasswordButton.setOnAction(event -> this.vm.generatePassword());

        // Initialize button state
        this.generatePasswordButton.setDisable(true);
    }
}
