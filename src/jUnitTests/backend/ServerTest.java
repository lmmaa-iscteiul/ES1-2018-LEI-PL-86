package backend;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class ServerTest {
	static Server server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		server = new Server();
	}

	@Test
	public void testGetUnreadLines() throws Exception {
		assertNotNull(server.getUnreadLines());
		server.getUnreadLines().clear();
		assertTrue(server.getUnreadLines().isEmpty());
		server.setUnreadLines(null);
		assertNull(server.getUnreadLines());
	}

	@Test
	public void testAllWorkersAreDone() throws Exception {
		for (Worker worker : server.getWorkers()) {
			worker.setIsWorking(true);
		}
		assertFalse(server.AllWorkersAreDone());
		for (Worker worker : server.getWorkers()) {
			worker.setIsWorking(false);
		}
		assertTrue(server.AllWorkersAreDone());
	}

}
