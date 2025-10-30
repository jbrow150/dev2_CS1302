package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compare two Tasks to identify the correct Descending ordering of the tasks
 * based on name.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class DescendingByName implements Comparator<Task> {

	/**
	 * Returns a value indicating ordering of the two tasks based on descending
	 * name.
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
		return o2.getName().compareToIgnoreCase(o1.getName());
	}

	@Override
	public String toString() {
		return "Descending by Name";
	}
}