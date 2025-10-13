package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Comparator that sorts tasks in ascending order of priority: LOW → MEDIUM →
 * HIGH
 * 
 * @author CS1302
 * @version Fall 2025
 */
public class Ascending implements Comparator<Task> {
	@Override
	public int compare(Task t1, Task t2) {
		return Integer.compare(t1.getPriority().ordinal(), t2.getPriority().ordinal());
	}

	@Override
	public String toString() {
		return "Ascending";
	}
}
