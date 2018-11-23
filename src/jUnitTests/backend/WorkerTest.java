package backend;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import frontend.Window;

public class WorkerTest {
	static Worker worker;
	static Window window;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		worker = new Worker();
		window = new Window();
	}

	@Test
	public void testRun() throws Exception {
		assertNotNull(worker);
		worker.start();
		assertNotNull(window.getServer());
		assertNotNull(window.getServer().getTaskList());
		assertTrue(worker.IsWorking());

	}

}
