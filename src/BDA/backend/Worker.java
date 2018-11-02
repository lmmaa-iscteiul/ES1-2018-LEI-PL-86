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

	public Boolean IsWorking() {
		return isWorking;
	}

	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

}