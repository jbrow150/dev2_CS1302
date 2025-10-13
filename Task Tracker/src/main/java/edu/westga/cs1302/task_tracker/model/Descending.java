package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Comparator that sorts tasks in descending order of priority: HIGH → MEDIUM →
 * LOW
 * 
 * This comparator makes higher priority tasks come first.
 * 
 * @author CS1302
 * @version Fall 2025
 */
public class Descending implements Comparator<Task> {
	@Override
	public int compare(Task t1, Task t2) {
		return Integer.compare(t2.getPriority().ordinal(), t1.getPriority().ordinal());
	}

	@Override
	public String toString() {
		return "Descending";
	}
}
