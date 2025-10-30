package edu.westga.cs1302.task_tracker.model.ascending_by_name;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.AscendingByName;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestCompare {

	@Test
	void testCompareDifferentNames() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		AscendingByName comparator = new AscendingByName();

		assertTrue(comparator.compare(taskA, taskB) < 0);
		assertTrue(comparator.compare(taskB, taskA) > 0);
	}

	@Test
	void testCompareSameNames() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskD = new Task("Alpha", "Description D", TaskPriority.MEDIUM);
		AscendingByName comparator = new AscendingByName();

		assertEquals(0, comparator.compare(taskA, taskD));
	}

	@Test
	void testSortingListByName() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		Task taskB = new Task("Bravo", "Description B", TaskPriority.MEDIUM);
		Task taskC = new Task("Charlie", "Description C", TaskPriority.LOW);
		AscendingByName comparator = new AscendingByName();

		List<Task> tasks = Arrays.asList(taskC, taskB, taskA);
		Collections.sort(tasks, comparator);
		assertEquals(Arrays.asList(taskA, taskB, taskC), tasks);
	}

	@Test
	void testCompareWithNullFirst() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		AscendingByName comparator = new AscendingByName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, taskA));
	}

	@Test
	void testCompareWithNullSecond() {
		Task taskA = new Task("Alpha", "Description A", TaskPriority.HIGH);
		AscendingByName comparator = new AscendingByName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(taskA, null));
	}

	@Test
	void testCompareWithBothNull() {
		AscendingByName comparator = new AscendingByName();

		assertThrows(IllegalArgumentException.class, () -> comparator.compare(null, null));
	}
}
