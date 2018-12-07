package integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import frontend.Table_line;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApp {

	List<Table_line> tweets = new ArrayList<Table_line>();
	static Logger log = LogManager.getLogger(TwitterApp.class);
	Status stat;
	
	TwitterFactory tf;
	Twitter twitter;
	/**
	 * The method that finds and adds the wanted tweets to a List of Table_line
	 * objects.
	 * 
	 */

	public TwitterApp() {
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true).setOAuthConsumerKey("nPV5ykycJGJn63UlppNasKKKH")
					.setOAuthConsumerSecret("lM6nvuydU3PGkUc5qaRqjhj0hd1LhU6Nzn5SMKWYhIdTy3Riao")
					.setOAuthAccessToken("1053600037043879938-zoCbbpECD52XraIEjmsC98RONQSfA0")
					.setOAuthAccessTokenSecret("WK17lfyHv0G7JG8yjll69pIr6K8yzt3Ja89o9hHGuNUvd");
			tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();
			List<Status> statuses = twitter.getUserTimeline("ISCTEIUL");
			System.out.println("------------------------\n Showing home timeline \n------------------------");
			int counter = 0;
			int counterTotal = 0;
			for (Status status : statuses) {
				addTweet(status.getText(), "tipo", status.getUser().getScreenName(), "Twitter");
			}

		} catch (TwitterException e) {
			log.error("Could not connect to twitter account. More details: " + e.getMessage());
		}

	}
	
	public void postTweet(String message) throws TwitterException {
		stat = twitter.updateStatus(message);
		log.debug("Successfully updated status to " + stat.getText());
	}

	/**
	 * Adds a Table_line object (a tweet) to the List of tweets.
	 * <p>
	 * The object is composed by four Strings: a message, a type, a sender, and
	 * a source.
	 * 
	 * @param message
	 *            - the shared message/content.
	 * @param type
	 *            - the message's type.
	 * @param sender
	 *            - who shared the message.
	 * @param source
	 *            - - where the message was obtained from.
	 */
	public void addTweet(String message, String type, String sender, String source) {
		Table_line tweet = new Table_line(message, type, sender, source);
		tweets.add(tweet);
	}

	/**
	 * Gets a List of the most recent tweets posted by '@ISCTEIUL' in the form
	 * of Table_line objects.
	 * 
	 * @return a List of Table_line objects (the tweets).
	 */
	public List<Table_line> getTweets() {
		return tweets;
	}
}
