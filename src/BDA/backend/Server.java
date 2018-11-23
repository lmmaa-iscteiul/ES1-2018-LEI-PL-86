package backend;

import java.util.LinkedList;

import frontend.Table_line;
import integration.TwitterApp;

public class Server {

	private Task_list taskList;
	private TwitterApp twitter;
	private LinkedList<Table_line> unreadLines;
	private LinkedList<Worker> workers;
	private LinkedList<Table_line> resultsList;

	public Server() {
		this.twitter = new TwitterApp();
		this.taskList = new Task_list();
		this.unreadLines = new LinkedList<Table_line>();
		this.workers = new LinkedList<Worker>();
		this.resultsList = new LinkedList<Table_line>();
		for (int i = 0; i < 5; i++) {
			Worker worker = new Worker();
			workers.add(worker);
			worker.start();
		}
	}

	public LinkedList<Table_line> getUnreadLines() {
		return unreadLines;
	}


	public Task_list getTaskList() {
		return taskList;
	}

	public TwitterApp getTwitter() {
		return twitter;
	}

	public void fillUnreadLines(Table_line line) {
		unreadLines.add(line);
	}

	public boolean AllWorkersAreDone() {
		int CountWaiting = 0;
		for (Worker worker : workers) {
			if (!(worker.IsWorking())) {
				CountWaiting++;
			}
		}
		if (CountWaiting == workers.size()) {
			CountWaiting = 0;
			return true;
		} else {
			CountWaiting = 0;
			return false;
		}
	}

	public synchronized LinkedList<Table_line> getResultsList() {
		return resultsList;
	}

}
