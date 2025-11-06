package edu.westga.cs1302.password_generator.view;

import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import edu.westga.cs1302.password_generator.viewmodel.PasswordGeneratorViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private TextArea output;
    @FXML private Button generatePasswordButton;
    
    private PasswordGenerator generator;

    @FXML
    void generatePassword(ActionEvent event) {
    	int minimumLength = -1;
    	
    	try {
    		minimumLength = Integer.parseInt(this.minimumLength.getText());
    	} catch (NumberFormatException numberError) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Invalid Minimum Length: must be a positive integer, but was " + this.minimumLength.getText());
    		alert.show();
    		return;
    	}
    	
    	try {
    		this.generator.setMinimumLength(minimumLength);
    	} catch (IllegalArgumentException invalidLengthError) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Invalid Minimum Length: " + invalidLengthError.getMessage());
    		alert.show();
    		return;
    	}
    	
    	this.generator.setMustHaveAtLeastOneDigit(this.mustIncludeDigits.isSelected());
    	this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustIncludeLowerCaseLetters.isSelected());
    	this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustIncludeUpperCaseLetters.isSelected());
    	
    	String password = this.generator.generatePassword();
    	
    	this.output.setText(password);
    }

    @FXML
    void initialize() {
        // Create ViewModel
        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();

        // Bind UI elements
        this.minimumLength.textProperty().bindBidirectional(viewModel.minimumLengthProperty(), new javafx.util.converter.NumberStringConverter());
        this.mustIncludeDigits.selectedProperty().bindBidirectional(viewModel.mustHaveDigitsProperty());
        this.mustIncludeLowerCaseLetters.selectedProperty().bindBidirectional(viewModel.mustHaveLowerCaseProperty());
        this.mustIncludeUpperCaseLetters.selectedProperty().bindBidirectional(viewModel.mustHaveUpperCaseProperty());
        this.output.textProperty().bind(viewModel.generatedPasswordProperty());

        // Replace direct model call with ViewModel
        this.generatePasswordButton.setOnAction(e -> viewModel.generatePassword());
    }
}
