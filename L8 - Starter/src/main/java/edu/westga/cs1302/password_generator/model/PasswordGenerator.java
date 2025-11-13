package edu.westga.cs1302.password_generator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Generates a random password based on the characteristics required.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class PasswordGenerator {

	private int minimumLength;
	private boolean mustHaveAtLeastOneDigit;
	private boolean mustHaveAtLeastOneUpperCaseLetter;
	private boolean mustHaveAtLeastOneLowerCaseLetter;
	private Random randomNumberGenerator;

	/**
	 * Creates a new password generator. Default minimum length = 1.
	 *
	 * @param seed the seed for the random number generator
	 */
	public PasswordGenerator(long seed) {
		this.randomNumberGenerator = new Random(seed);
		this.minimumLength = 1;
		this.mustHaveAtLeastOneDigit = false;
		this.mustHaveAtLeastOneUpperCaseLetter = false;
		this.mustHaveAtLeastOneLowerCaseLetter = false;
	}

	public int getMinimumLength() {
		return this.minimumLength;
	}

	public boolean getMustHaveAtLeastOneDigit() {
		return this.mustHaveAtLeastOneDigit;
	}

	public boolean getMustHaveAtLeastOneUpperCaseLetter() {
		return this.mustHaveAtLeastOneUpperCaseLetter;
	}

	public boolean getMustHaveAtLeastOneLowerCaseLetter() {
		return this.mustHaveAtLeastOneLowerCaseLetter;
	}

	public void setMinimumLength(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("minimum length must be at least 1");
		}
		this.minimumLength = length;
	}

	public void setMustHaveAtLeastOneDigit(boolean mustHaveAtLeastOneDigit) {
		this.mustHaveAtLeastOneDigit = mustHaveAtLeastOneDigit;
	}

	public void setMustHaveAtLeastOneUpperCaseLetter(boolean mustHaveAtLeastOneUpperCaseLetter) {
		this.mustHaveAtLeastOneUpperCaseLetter = mustHaveAtLeastOneUpperCaseLetter;
	}

	public void setMustHaveAtLeastOneLowerCaseLetter(boolean mustHaveAtLeastOneLowerCaseLetter) {
		this.mustHaveAtLeastOneLowerCaseLetter = mustHaveAtLeastOneLowerCaseLetter;
	}

	/**
	 * Generates a password according to the established requirements.
	 */
	public String generatePassword() {
		ArrayList<Character> passwordCharacters = new ArrayList<>();

		if (this.mustHaveAtLeastOneDigit) {
			passwordCharacters = this.addDigits(passwordCharacters);
		}
		if (this.mustHaveAtLeastOneUpperCaseLetter) {
			passwordCharacters = this.addUpperCaseLetters(passwordCharacters);
		}
		if (this.mustHaveAtLeastOneLowerCaseLetter) {
			passwordCharacters = this.addLowerCaseLetters(passwordCharacters);
		}
		int length = this.minimumLength + this.randomNumberGenerator.nextInt(this.minimumLength);
		while (passwordCharacters.size() < length) {
			passwordCharacters = this.addRandomCharacter(passwordCharacters);
		}

		return this.combineCharacters(passwordCharacters);
	}

	private ArrayList<Character> addRandomCharacter(ArrayList<Character> passwordCharacters) {
		int charType = this.randomNumberGenerator.nextInt(3);
		if (charType == 0) {
			passwordCharacters.add(this.getRandomUpperCaseLetter());
		} else if (charType == 1) {
			passwordCharacters.add(this.getRandomLowerCaseLetter());
		} else {
			passwordCharacters.add(this.getRandomDigit());
		}
		return passwordCharacters;
	}

	private ArrayList<Character> addLowerCaseLetters(ArrayList<Character> passwordCharacters) {
		double numberOfLowerCaseLettersToAdd = this.minimumLength / 3.0;
		for (int lettersAddedCount = 0; lettersAddedCount < numberOfLowerCaseLettersToAdd; lettersAddedCount++) {
			passwordCharacters.add(this.getRandomLowerCaseLetter());
		}
		return passwordCharacters;
	}

	private Character getRandomLowerCaseLetter() {
		return Character.toLowerCase(this.getRandomUpperCaseLetter());
	}

	private ArrayList<Character> addUpperCaseLetters(ArrayList<Character> passwordCharacters) {
		double numberOfUpperCaseLettersToAdd = this.minimumLength / 3.0;
		for (int lettersAddedCount = 0; lettersAddedCount < numberOfUpperCaseLettersToAdd; lettersAddedCount++) {
			passwordCharacters.add(this.getRandomUpperCaseLetter());
		}
		return passwordCharacters;
	}

	private Character getRandomUpperCaseLetter() {
		int letterToAdd = this.randomNumberGenerator.nextInt(26);
		return Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z').get(letterToAdd);
	}

	private ArrayList<Character> addDigits(ArrayList<Character> passwordCharacters) {
		double numberOfDigitsToAdd = this.minimumLength / 3.0;
		for (int digitsAddedCount = 0; digitsAddedCount < numberOfDigitsToAdd; digitsAddedCount++) {
			passwordCharacters.add(this.getRandomDigit());
		}
		return passwordCharacters;
	}

	private char getRandomDigit() {
		return ((Integer) (this.randomNumberGenerator.nextInt(10))).toString().charAt(0);
	}

	private String combineCharacters(ArrayList<Character> passwordCharacters) {
		StringBuilder password = new StringBuilder();
		for (Character currentChar : passwordCharacters) {
			password.append(currentChar);
		}
		return password.toString();
	}
}