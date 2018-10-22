package integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import frontend.Table_line;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApp  {
	
	List<Table_line> tweets = new ArrayList<Table_line>();
		
	public TwitterApp(){
		try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("W1f0VvgWPfT8OBqVxvy4Mw")
        	  .setOAuthConsumerSecret("zKH2yAtRyefwsgOO8h8Szc4kru68iEm95QmIG7svw")
        	  .setOAuthAccessToken("36481851-VhzByC4f9MSsZES1QZQ4e4iBvA9bWGLyv9HKFpy7c")
        	  .setOAuthAccessTokenSecret("OahDuXF2Lhl5xlNYALhYZir6xSflAxKP9Zh89T05po");
        	TwitterFactory tf = new TwitterFactory(cb.build());
        	Twitter twitter = tf.getInstance();
            List<Status> statuses = twitter.getUserTimeline("ISCTEIUL");
            System.out.println("------------------------\n Showing home timeline \n------------------------");
    		int counter=0;
    		int counterTotal = 0;
            for (Status status : statuses) {
				// Filters only tweets from user "ISCTE - IUL"
//				if (status.getUser().getName() != null && status.getUser().getScreenName()) {
//            		System.out.println(status.getUser().getName() + ": " + status.getText());
            	System.out.println(status.getUser().getScreenName() + ": " + status.getText());
					counter++;
					addTweet(status.getText(), "tipo", status.getUser().getScreenName(), "Twitter");
				}
				counterTotal++;
//            }
            
    		System.out.println("-------------\nNº of Results: " + counter+"/"+counterTotal);
        } catch (Exception e) { System.out.println(e.getMessage()); }
     }
	
	private void addTweet(String message, String type, String sender, String source){
		Table_line tweet = new Table_line(message, type, sender, source);
		tweets.add(tweet);
	}
	
	public List<Table_line> getTweets(){	
		return tweets;		
	}
}
    
    
