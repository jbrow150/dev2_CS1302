package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.javafx_sample_starter.model.Task;
import edu.westga.cs1302.javafx_sample_starter.model.TaskCounter;

public class TaskCounterTestCases {
	@Test
    public void countByPriority_shouldReturnCorrectCount_whenMatchingPriorities() {
        List<Task> tasks = Arrays.asList(
            new Task("Task1", "Desc1", "High"),
            new Task("Task2", "Desc2", "High"),
            new Task("Task3", "Desc3", "Low")
        );

        int result = TaskCounter.countByPriority("High", tasks);

        assertEquals(2, result);
    }

    @Test
    public void countByPriority_shouldBeCaseInsensitive() {
        List<Task> tasks = Arrays.asList(
            new Task("Task1", "Desc1", "HIGH"),
            new Task("Task2", "Desc2", "high"),
            new Task("Task3", "Desc3", "Low")
        );

        int result = TaskCounter.countByPriority("high", tasks);

        assertEquals(2, result);
    }

    @Test
    public void countByPriority_shouldReturnZero_whenNoTasksMatch() {
        List<Task> tasks = Arrays.asList(
            new Task("Task1", "Desc1", "Medium"),
            new Task("Task2", "Desc2", "Low")
        );

        int result = TaskCounter.countByPriority("High", tasks);

        assertEquals(0, result);
    }

    @Test
    public void countByPriority_shouldReturnZero_whenListIsEmpty() {
        List<Task> tasks = Collections.emptyList();

        int result = TaskCounter.countByPriority("High", tasks);

        assertEquals(0, result);
    }

    @Test
    public void countByPriority_shouldThrowException_whenPriorityIsNull() {
        List<Task> tasks = Arrays.asList(
            new Task("Task1", "Desc1", "High")
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskCounter.countByPriority(null, tasks);
        });

        assertEquals("Priority and task list cannot be null.", exception.getMessage());
    }

    @Test
    public void countByPriority_shouldThrowException_whenTaskListIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TaskCounter.countByPriority("High", null);
        });

        assertEquals("Priority and task list cannot be null.", exception.getMessage());
    }
}
