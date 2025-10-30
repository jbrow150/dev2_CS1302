package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/**
 * Compare two Tasks to identify the correct Ascending ordering of the tasks.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class AscendingByName implements Comparator<Task> {

	/**
	 * Returns a value indicating ordering of the two tasks based on Ascending
	 * priority.
	 * 
	 * @precondition o1 != null && o2 != null
	 * @postcondition none
	 * 
	 * @param o1 the first task to compare
	 * @param o2 the second task to compare
	 * 
	 * @return -1 if o1 goes first 0 if o1 and o2 are same 1 if o1 goes last
	 */
	@Override
	public int compare(Task o1, Task o2) {
		if (o1 == null || o2 == null) {
			throw new IllegalArgumentException("Tasks must not be null");
		}
		return o1.getName().compareToIgnoreCase(o2.getName());
	}

	/**
	 * Returns the name of the task to represent the task as a String
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the task
	 */

	@Override
	public String toString() {
		return "Ascending by Name";
	}
}
