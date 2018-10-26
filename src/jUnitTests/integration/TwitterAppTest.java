package integration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import frontend.Table_line;

public class TwitterAppTest {

	static List<Table_line> tweets;
	static TwitterApp tester; 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tester = new TwitterApp();
		tweets = tester.getTweets();
//		Table_line table = new Table_line(message, type, sender, source)
	}

	@Test
	public void testAddTweet() throws Exception {
//		List<Table_line> testTweets = tweets;
		tester.addTweet("", "", "", "");
	
		//assertStatement
		assertTrue(!tweets.isEmpty());
		
		//throw new RuntimeException("not yet implemented");
	}

	@Test
	public void testGetTweets() throws Exception {
		assertTrue(tester.getTweets().equals(tweets));
		
		//throw new RuntimeException("not yet implemented");
	}

}
