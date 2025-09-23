package edu.westga.cs1302.javafx_sample_starter.model;

import java.util.List;

public class TaskCounter {
	public static int countByPriority(String priority, List<Task> tasks) {
		if (priority == null || tasks == null) {
			throw new IllegalArgumentException("Priority and task list cannot be null.");
		}

		int count = 0;
		for (Task task : tasks) {
			if (task.getPriority().equalsIgnoreCase(priority)) {
				count++;
			}
		}
		return count;
	}
}
