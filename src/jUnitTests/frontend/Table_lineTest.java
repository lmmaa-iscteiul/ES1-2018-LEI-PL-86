package frontend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class Table_lineTest {

	private static String message;
	private static String type;
	private static String sender;
	private static String source;
	private static Table_line table_line;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		message = "testMessage";
		type = "testType";
		sender = "testSender";
		source = "testSouce";
		table_line = new Table_line(message, type, sender, source);
	}

	@Test
	public void testGetMessage() throws Exception {
		table_line.setMessage("testMessage");
		assertTrue(table_line.getMessage() == "testMessage");

	}

	@Test
	public void testSetMessage() throws Exception {
		String testMessage = "test";
		table_line.setMessage(testMessage);
		assertTrue(table_line.getMessage() == "test");
	}

	@Test
	public void testGetType() throws Exception {
		table_line.setType("testType");
		assertTrue(table_line.getType() == "testType");
	}

	@Test
	public void testSetType() throws Exception {
		String testType = "test";
		table_line.setType(testType);
		assertTrue(table_line.getType() == "test");
	}

	@Test
	public void testGetSender() throws Exception {
		table_line.setSender("testSender");
		assertTrue(table_line.getSender() == "testSender");
	}

	@Test
	public void testSetSender() throws Exception {
		String testSender = "test";
		table_line.setSender(testSender);
		assertTrue(table_line.getSender() == "test");
	}

	@Test
	public void testGetSource() throws Exception {
		table_line.setSource("testSource");
		assertTrue(table_line.getSource() == "testSource");
	}

	@Test
	public void testSetSource() throws Exception {
		String testSource = "test";
		table_line.setSource(testSource);
		assertTrue(table_line.getSource() == "test");
	}

}
