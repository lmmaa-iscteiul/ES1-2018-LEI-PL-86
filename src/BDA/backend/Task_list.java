package backend;

import java.util.LinkedList;

import frontend.Table_line;
import frontend.Window;

public class Task_list {
	private LinkedList<Task> tasks;

	public Task_list() {
		this.tasks = new LinkedList<Task>();
	}

	public synchronized Task getTask() {
		while (tasks.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		return tasks.removeFirst();
	}

	public synchronized void createTasks(String palavra) {
		for (Table_line line : Window.getWindowInstance().getServer().getUnreadLines()) {
			Task task = new Task(palavra, line);
			tasks.add(task);
		}
		notifyAll();

	}

}
