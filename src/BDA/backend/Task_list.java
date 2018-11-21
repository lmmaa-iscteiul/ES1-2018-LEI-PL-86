package backend;

import java.util.LinkedList;

import frontend.Table_line;
import frontend.Window;

public class Task_list {
	private LinkedList<Task> tasks;

	public Task_list() {
		this.tasks = new LinkedList<Task>();
	}

	/**
	 * 
	 * @return the first task, if there is one, from the tasks List.
	 */
	public synchronized Task getTask() {
		while (tasks.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return tasks.removeFirst();
	}

	/**
	 * Creates Tasks with the specified word as one their parameters.
	 * @param palavra - the specified word.
	 */
	public synchronized void createTasks(String palavra) {
		for (Table_line line : Window.getWindowInstance().getServer().getUnreadLines()) {
			Task task = new Task(palavra, line);
			tasks.add(task);
		}
		notifyAll();

	}

}
