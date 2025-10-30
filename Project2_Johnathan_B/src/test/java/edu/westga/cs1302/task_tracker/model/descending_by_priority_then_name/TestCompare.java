package edu.westga.cs1302.task_tracker.model.descending_by_priority_then_name;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.DescendingByPriorityThenName;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestCompare {

	@Test
	void testCompareDifferentPriority() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		DescendingByPriorityThenName comparator = new DescendingByPriorityThenName();

		assertTrue(comparator.compare(taskA, taskB) > 0);
		assertTrue(comparator.compare(taskB, taskA) < 0);
	}

	@Test
	void testCompareSamePriorityDifferentName() {
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		Task taskD = new Task("Alpha", "Description D", TaskPriority.MEDIUM);
		DescendingByPriorityThenName comparator = new DescendingByPriorityThenName();

		assertTrue(comparator.compare(taskB, taskD) < 0);
	}

	@Test
	void testSortingListByPriorityThenName() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		Task taskC = new Task("Charlie", "Description C", TaskPriority.LOW);
		Task taskD = new Task("Alpha", "Description D", TaskPriority.MEDIUM);
		DescendingByPriorityThenName comparator = new DescendingByPriorityThenName();

		List<Task> tasks = Arrays.asList(taskC, taskB, taskD, taskA);
		Collections.sort(tasks, comparator);
		assertEquals(Arrays.asList(taskC, taskB, taskD, taskA), tasks);
	}

	@Test
	void testCompareWithNullFirst() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		DescendingByPriorityThenName comparator = new DescendingByPriorityThenName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, taskA));
	}

	@Test
	void testCompareWithNullSecond() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		DescendingByPriorityThenName comparator = new DescendingByPriorityThenName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(taskA, null));
	}

	@Test
	void testCompareWithBothNull() {
		DescendingByPriorityThenName comparator = new DescendingByPriorityThenName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, null));
	}

}
