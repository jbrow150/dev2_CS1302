package edu.westga.cs1302.javafx_sample_starter.model;

import java.util.Objects;

public class Task {

	private final String name;
	private String description;
	private final String priority;

	public Task(String name, String description, String priority) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Name is required.");
		}
		if (description == null) {
			throw new IllegalArgumentException("Description is required.");
		}
		if (priority == null) {
			throw new IllegalArgumentException("Priority is required.");
		}

		this.name = name;
		this.description = description;
		this.priority = priority;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public String getPriority() {
		return this.priority;
	}

	public String toString() {
		return this.name;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Task)) {
			return false;
		}
		Task otherTask = (Task) other;
		return this.name.equalsIgnoreCase(otherTask.name);
	}

	public int hashCode() {
		return Objects.hash(this.name.toLowerCase());
	}

	public void setDescription(String newDescription) {
		if (newDescription == null) {
			throw new IllegalArgumentException("Description cannot be null.");
		}
		this.description = newDescription;
	}
}
