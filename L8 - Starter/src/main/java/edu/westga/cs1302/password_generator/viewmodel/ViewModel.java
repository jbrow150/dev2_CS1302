package edu.westga.cs1302.password_generator.viewmodel;

import java.util.Random;
import java.util.regex.Pattern;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Manages utilizing the model and makes properties available to bind the UI elements.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class ViewModel {
	private final StringProperty minimumLength;
	private final BooleanProperty requireDigits;
	private final BooleanProperty requireLowercase;
	private final BooleanProperty requireUppercase;

	private final StringProperty password;
	private final StringProperty errorText;
	private final ObservableList<String> passwordHistory;

	private final PasswordGenerator generator;

	private static final Pattern NUMERIC_PATTERN = Pattern.compile("^[1-9][0-9]*$");

	public ViewModel() {
		this.minimumLength = new SimpleStringProperty("1");
		this.requireDigits = new SimpleBooleanProperty(false);
		this.requireLowercase = new SimpleBooleanProperty(false);
		this.requireUppercase = new SimpleBooleanProperty(false);

		this.password = new SimpleStringProperty("");
		this.errorText = new SimpleStringProperty("");
		this.passwordHistory = FXCollections.observableArrayList();

		Random randomNumberGenerator = new Random();
		this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());

		// Input validation listener
		this.minimumLength.addListener((obs, oldVal, newVal) -> {
			if (newVal == null || newVal.isEmpty()) {
				this.errorText.set("Minimum length cannot be empty");
			} else if (!NUMERIC_PATTERN.matcher(newVal).matches()) {
				this.errorText.set("Invalid input: must be a positive integer");
			} else {
				this.errorText.set("");
			}
		});
	}

	public StringProperty getMinimumLength() {
		return this.minimumLength;
	}

	public BooleanProperty getRequireDigits() {
		return this.requireDigits;
	}

	public BooleanProperty getRequireUppercase() {
		return this.requireUppercase;
	}

	public BooleanProperty getRequireLowercase() {
		return this.requireLowercase;
	}

	public StringProperty getPassword() {
		return this.password;
	}

	public StringProperty getErrorText() {
		return this.errorText;
	}

	public ObservableList<String> getPasswordHistory() {
		return this.passwordHistory;
	}

	public void generatePassword() {
		this.password.set("");
		this.errorText.set("");

		String lengthInput = this.minimumLength.get();
		if (!NUMERIC_PATTERN.matcher(lengthInput).matches()) {
			this.errorText.set("Minimum length must be a positive integer.");
			return;
		}

		int minimumLength = Integer.parseInt(lengthInput);
		try {
			this.generator.setMinimumLength(minimumLength);
		} catch (IllegalArgumentException invalidLengthError) {
			this.errorText.set("Invalid Minimum Length: " + invalidLengthError.getMessage());
			return;
		}

		this.generator.setMustHaveAtLeastOneDigit(this.requireDigits.get());
		this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.requireLowercase.get());
		this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.requireUppercase.get());

		String newPassword = this.generator.generatePassword();

		this.password.set(newPassword);
		this.passwordHistory.add(0, newPassword); // add to top of list
	}
}