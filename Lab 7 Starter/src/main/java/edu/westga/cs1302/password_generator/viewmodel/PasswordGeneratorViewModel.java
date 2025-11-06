package edu.westga.cs1302.password_generator.viewmodel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.*;

import java.util.Random;

public class PasswordGeneratorViewModel {

	private final IntegerProperty minimumLength = new SimpleIntegerProperty(1);
	private final BooleanProperty mustHaveDigits = new SimpleBooleanProperty(false);
	private final BooleanProperty mustHaveLowerCase = new SimpleBooleanProperty(false);
	private final BooleanProperty mustHaveUpperCase = new SimpleBooleanProperty(false);
	private final StringProperty generatedPassword = new SimpleStringProperty("");

	private final PasswordGenerator model;

	public PasswordGeneratorViewModel() {
		this.model = new PasswordGenerator(new Random().nextLong());
	}

	// Property getters
	public IntegerProperty minimumLengthProperty() {
		return this.minimumLength;
	}

	public BooleanProperty mustHaveDigitsProperty() {
		return this.mustHaveDigits;
	}

	public BooleanProperty mustHaveLowerCaseProperty() {
		return this.mustHaveLowerCase;
	}

	public BooleanProperty mustHaveUpperCaseProperty() {
		return this.mustHaveUpperCase;
	}

	public StringProperty generatedPasswordProperty() {
		return this.generatedPassword;
	}

	// Generate password
	public void generatePassword() {
		try {
			model.setMinimumLength(this.minimumLength.get());
		} catch (IllegalArgumentException e) {
			this.generatedPassword.set("Error: " + e.getMessage());
			return;
		}

		model.setMustHaveAtLeastOneDigit(this.mustHaveDigits.get());
		model.setMustHaveAtLeastOneLowerCaseLetter(this.mustHaveLowerCase.get());
		model.setMustHaveAtLeastOneUpperCaseLetter(this.mustHaveUpperCase.get());

		String password = model.generatePassword();
		this.generatedPassword.set(password);
	}
}