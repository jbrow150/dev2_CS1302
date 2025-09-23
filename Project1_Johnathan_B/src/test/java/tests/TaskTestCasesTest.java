package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.javafx_sample_starter.model.Task;

import edu.westga.cs1302.javafx_sample_starter.model.Task;

public class TaskTestCasesTest {

    @Test
    void constructor_shouldThrowException_whenNameIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "desc", "high");
        });
        assertEquals("Name is required.", exception.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenNameIsBlank() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("   ", "desc", "high");
        });
        assertEquals("Name is required.", exception.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenDescriptionIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task1", null, "high");
        });
        assertEquals("Description is required.", exception.getMessage());
    }

    @Test
    void constructor_shouldThrowException_whenPriorityIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("Task1", "desc", null);
        });
        assertEquals("Priority is required.", exception.getMessage());
    }

    @Test
    void toString_shouldReturnName() {
        Task task = new Task("Task1", "desc", "high");
        assertEquals("Task1", task.toString());
    }

    @Test
    void equals_shouldReturnTrue_whenNamesMatchIgnoringCase() {
        Task task1 = new Task("Task1", "desc1", "high");
        Task task2 = new Task("task1", "desc2", "low");
        assertTrue(task1.equals(task2));
    }

    @Test
    void equals_shouldReturnFalse_whenDifferentClass() {
        Task task = new Task("Task1", "desc", "high");
        String notATask = "Not a task";
        assertFalse(task.equals(notATask));
    }

    @Test
    void equals_shouldReturnFalse_whenNamesDoNotMatch() {
        Task task1 = new Task("Task1", "desc", "high");
        Task task2 = new Task("Task2", "desc", "high");
        assertFalse(task1.equals(task2));
    }

    @Test
    void hashCode_shouldBeEqualForNamesIgnoringCase() {
        Task task1 = new Task("Task1", "desc1", "high");
        Task task2 = new Task("task1", "desc2", "low");
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void setDescription_shouldChangeDescription() {
        Task task = new Task("Task1", "desc", "high");
        task.setDescription("new desc");
        assertEquals("new desc", task.getDescription());
    }

    @Test
    void setDescription_shouldThrowException_whenNull() {
        Task task = new Task("Task1", "desc", "high");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
        assertEquals("Description cannot be null.", exception.getMessage());
    }
}
