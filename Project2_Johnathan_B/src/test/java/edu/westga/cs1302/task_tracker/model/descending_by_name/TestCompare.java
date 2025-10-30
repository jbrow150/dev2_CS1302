package edu.westga.cs1302.task_tracker.model.descending_by_name;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.westga.cs1302.task_tracker.model.*;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

public class TestCompare {

	@Test
	void testCompareDifferentNames() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		DescendingByName comparator = new DescendingByName();

		assertTrue(comparator.compare(taskA, taskB) > 0);
		assertTrue(comparator.compare(taskB, taskA) < 0);
	}

	@Test
	void testCompareSameNames() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskD = new Task("Alpha", "Description D", TaskPriority.MEDIUM);
		DescendingByName comparator = new DescendingByName();

		assertEquals(0, comparator.compare(taskA, taskD));
	}

	@Test
	void testSortingListByName() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		Task taskC = new Task("Charlie", "Description C", TaskPriority.LOW);
		DescendingByName comparator = new DescendingByName();

		List<Task> tasks = Arrays.asList(taskA, taskB, taskC);
		Collections.sort(tasks, comparator);
		assertEquals(Arrays.asList(taskC, taskB, taskA), tasks);
	}

	@Test
	void testCompareWithNullFirst() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		DescendingByName comparator = new DescendingByName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, taskA));
	}

	@Test
	void testCompareWithNullSecond() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		DescendingByName comparator = new DescendingByName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(taskA, null));
	}

	@Test
	void testCompareWithBothNull() {
		DescendingByName comparator = new DescendingByName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, null));
	}
}