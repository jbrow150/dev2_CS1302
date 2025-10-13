package edu.westga.cs1302.task_tracker.model.ascending;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestAscendingComparator {

    @Test
    void testAscendingComparatorSortsLowToHigh() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task1", "High priority task", TaskPriority.HIGH));
        tasks.add(new Task("Task2", "Low priority task", TaskPriority.LOW));
        tasks.add(new Task("Task3", "Medium priority task", TaskPriority.MEDIUM));

        tasks.sort(new Ascending());

        assertEquals(TaskPriority.LOW, tasks.get(0).getPriority());
        assertEquals(TaskPriority.MEDIUM, tasks.get(1).getPriority());
        assertEquals(TaskPriority.HIGH, tasks.get(2).getPriority());
    }
    
    @Test
    void testAscendingComparatorEquality() {
        Task t1 = new Task("Task1", "mediium", TaskPriority.MEDIUM);
        Task t2 = new Task("Task2", "medium", TaskPriority.MEDIUM);

        Ascending asc = new Ascending();
        assertEquals(0, asc.compare(t1, t2));
    }

}