package edu.westga.cs1302.task_tracker.model.descending;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Descending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestDescendingComparator {

    @Test
    void testDescendingComparatorSortsHighToLow() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", "High priority task", TaskPriority.HIGH));
        tasks.add(new Task("Task2", "Low priority task", TaskPriority.LOW));
        tasks.add(new Task("Task3", "Medium priority task", TaskPriority.MEDIUM));

        tasks.sort(new Descending());

        assertEquals(TaskPriority.HIGH, tasks.get(0).getPriority());
        assertEquals(TaskPriority.MEDIUM, tasks.get(1).getPriority());
        assertEquals(TaskPriority.LOW, tasks.get(2).getPriority());
    }
    

    @Test
    void testDescendingComparatorEquality() {
        Task t1 = new Task("Task1", "High", TaskPriority.HIGH);
        Task t2 = new Task("Task2", "High", TaskPriority.HIGH);

        Descending desc = new Descending();
        assertEquals(0, desc.compare(t1, t2));
    }
}