package edu.westga.cs1302.javafx_sample_starter.views;

import edu.westga.cs1302.javafx_sample_starter.model.Task;
import edu.westga.cs1302.javafx_sample_starter.model.TaskCounter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

	@FXML
	private TextField taskNameField;

	@FXML
	private TextArea taskDescriptionArea;

	@FXML
	private ComboBox<String> priorityComboBox;

	@FXML
	private Button addTaskButton;

	@FXML
	private ListView<Task> taskListView;

	@FXML
	private TextArea selectedDescriptionArea;

	@FXML
	private TextField selectedPriorityField;

	@FXML
	private Button removeTaskButton;

	@FXML
	private Label lowPriorityCountLabel;

	@FXML
	private Label mediumPriorityCountLabel;

	@FXML
	private Label highPriorityCountLabel;

	private ObservableList<Task> taskList;

	/**
	 * Perform any needed initialization of UI components and underlying objects.
	 */
	public void initialize() {
		this.taskList = FXCollections.observableArrayList();
		this.taskListView.setItems(this.taskList);

		this.priorityComboBox.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));
		this.priorityComboBox.getSelectionModel().selectFirst();

		this.addTaskButton.setOnAction(event -> this.handleAddTask());

		this.taskListView.setOnMouseClicked(event -> this.handleDisplayTask());
		this.removeTaskButton.disableProperty()
				.bind(this.taskListView.getSelectionModel().selectedItemProperty().isNull());
	}

	private void handleAddTask() {
		String name = this.taskNameField.getText();
		String description = this.taskDescriptionArea.getText();
		String priority = this.priorityComboBox.getValue();

		assert name != null && !name.isBlank() : "Task name cannot be blank.";
		assert description != null : "Task description cannot be null.";
		assert priority != null : "Task priority must be selected.";

		// Check for duplicates
		for (Task existing : this.taskList) {
			assert !existing.getName().equalsIgnoreCase(name) : "Duplicate task name.";
		}

		Task newTask = new Task(name, description, priority);
		this.taskList.add(newTask);

		// Reset input fields
		this.taskNameField.clear();
		this.taskDescriptionArea.clear();
		this.priorityComboBox.getSelectionModel().selectFirst();
	}

	private void handleDisplayTask() {
		Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();

		if (selectedTask != null) {
			this.selectedDescriptionArea.setText(selectedTask.getDescription());
			this.selectedPriorityField.setText(selectedTask.getPriority());
		} else {
			this.selectedDescriptionArea.clear();
			this.selectedPriorityField.clear();
		}
	}

	@FXML
	private void handleUpdateDescription() {
		Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();

		assert selectedTask != null : "No task selected.";

		String newDescription = this.selectedDescriptionArea.getText();

		assert newDescription != null : "Description cannot be null.";

		selectedTask.setDescription(newDescription);

		// Force refresh in list view (to update internal rendering if needed)
		this.taskListView.refresh();
	}

	@FXML
	private void handleRemoveTask() {
		Task selectedTask = this.taskListView.getSelectionModel().getSelectedItem();

		assert selectedTask != null : "No task selected to remove.";

		this.taskList.remove(selectedTask);

		// Clear the display fields after removal
		this.selectedDescriptionArea.clear();
		this.selectedPriorityField.clear();
	}

	@FXML
	private void handleCountTasksByPriority() {
		int lowCount = TaskCounter.countByPriority("Low", this.taskList);
		int mediumCount = TaskCounter.countByPriority("Medium", this.taskList);
		int highCount = TaskCounter.countByPriority("High", this.taskList);

		this.lowPriorityCountLabel.setText(String.valueOf(lowCount));
		this.mediumPriorityCountLabel.setText(String.valueOf(mediumCount));
		this.highPriorityCountLabel.setText(String.valueOf(highCount));
	}
}
