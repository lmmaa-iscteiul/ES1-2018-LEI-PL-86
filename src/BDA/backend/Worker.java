package backend;

import frontend.Window;

public class Worker extends Thread {
	private Boolean isWorking;

	public Worker() {
	}

	@Override
	public void run() {
		while (true) {
			isWorking = false;
			Task task = Window.getWindowInstance().getServer().getTaskList().getTask();
			isWorking = true;
			if (task.getLine().getMessage().split(task.getPalavra()).length > 1) {
				Window.getWindowInstance().getServer().getResultsList().add(task.getLine());
			}
		}

	}

	/**
	 * 
	 * @return true if the Worker instance is working/occupied with a task.
	 */
	public Boolean IsWorking() {
		return isWorking;
	}

	/**
	 * Sets the Worker object's attribute "isWorking" either true or false
	 * @param isWorking - true or false
	 */
	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

}
