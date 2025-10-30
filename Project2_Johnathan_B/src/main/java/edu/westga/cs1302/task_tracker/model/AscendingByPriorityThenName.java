package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compare two Tasks to identify the correct Ascending ordering of the tasks
 * based on priority first, then name.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class AscendingByPriorityThenName implements Comparator<Task> {

	/**
	 * Returns a value indicating ordering of the two tasks based on ascending
	 * priority, then ascending name.
	 * 
	 * @precondition o1 != null && o2 != null
	 * @postcondition none
	 * 
	 * @param o1 the first task to compare
	 * @param o2 the second task to compare
	 * @return -1 if o1 goes first, 0 if same, 1 if o1 goes last
	 */
	@Override
	public int compare(Task o1, Task o2) {
		if (o1 == null || o2 == null) {
			throw new IllegalArgumentException("Tasks must not be null");
		}
		int priorityCompare = Integer.compare(o1.getPriority().getValue(), o2.getPriority().getValue());
		if (priorityCompare != 0) {
			return priorityCompare;
		}
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

	@Override
	public String toString() {
		return "Ascending by Priority then Name";
	}
}