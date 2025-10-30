package edu.westga.cs1302.task_tracker.model.ascending_by_priority_then_name;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.westga.cs1302.task_tracker.model.*;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;



public class TestCompare {

	// SUNNY DAY
	@Test
	void testCompareDifferentPriority() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		AscendingByPriorityThenName comparator = new AscendingByPriorityThenName();

		assertTrue(comparator.compare(taskA, taskB) < 0);
		assertTrue(comparator.compare(taskB, taskA) > 0);
	}

	@Test
	void testCompareSamePriorityDifferentName() {
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		Task taskD = new Task("Alpha", "Description D", TaskPriority.MEDIUM);
		AscendingByPriorityThenName comparator = new AscendingByPriorityThenName();

		assertTrue(comparator.compare(taskB, taskD) > 0);
	}

	@Test
	void testSortingListByPriorityThenName() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		Task taskC = new Task("Charlie", "Description C", TaskPriority.LOW);
		Task taskD = new Task("Alpha", "Description D", TaskPriority.MEDIUM);
		AscendingByPriorityThenName comparator = new AscendingByPriorityThenName();

		List<Task> tasks = Arrays.asList(taskC, taskB, taskD, taskA);
		Collections.sort(tasks, comparator);
		assertEquals(Arrays.asList(taskA, taskD, taskB, taskC), tasks);
	}

	// RAINY DAY
	@Test
	void testCompareWithNullFirst() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		AscendingByPriorityThenName comparator = new AscendingByPriorityThenName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, taskA));
	}

	@Test
	void testCompareWithNullSecond() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		AscendingByPriorityThenName comparator = new AscendingByPriorityThenName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(taskA, null));
	}

	@Test
	void testCompareWithBothNull() {
		AscendingByPriorityThenName comparator = new AscendingByPriorityThenName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, null));
	}
}