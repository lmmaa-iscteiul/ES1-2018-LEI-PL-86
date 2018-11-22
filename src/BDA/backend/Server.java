package backend;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import frontend.Table_line;
import integration.TwitterApp;

public class Server {

	private Task_list taskList;
	private TwitterApp twitter;
	private LinkedList<Table_line> unreadLines;
	private LinkedList<Worker> workers;
	private LinkedList<Table_line> resultsList;


	static Logger log = LogManager.getLogger(TwitterApp.class);

	/**
	 * Initiates a TwitterApp object.
	 * <p>
	 * Initiates a Task_list object.
	 * <p>
	 * Creates five (5) Worker objects, adds them to a LinkedList of Workers, and starts running them.
	 * 
	 */
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
		try {			
			return unreadLines;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Could not get Unread Lines. More details: " + e.getMessage());
			return null;
		}
	}

	public void setUnread_tasks(LinkedList<Table_line> unread) {
		this.unreadLines = unread;
	}

	public Task_list getTaskList() {
		return taskList;
	}

	public void setTaskList(Task_list taskList) {
		this.taskList = taskList;
	}

	public TwitterApp getTwitter() {
		return twitter;
	}

	public void setTwitter(TwitterApp twitter) {
		this.twitter = twitter;
	}

	public void fillUnreadLines(Table_line line) {
		unreadLines.add(line);
	}

	/**
	 * Tells you if all the Workers are working/occupied or not.
	 * @return true or false
	 */
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
