package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private ListView<String> passwordList;
    @FXML private Label errorTextLabel;
    @FXML private Button generatePasswordButton;

    private ViewModel vm;

    @FXML
    void initialize() {
    	this.vm = new ViewModel();

    	this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
    	this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
    	this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());

    	this.minimumLength.textProperty().bindBidirectional(this.vm.getMinimumLength());
    	this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
    	this.passwordList.setItems(this.vm.getPasswordHistory());

    	this.generatePasswordButton.setOnAction(event -> this.vm.generatePassword());
    }
}