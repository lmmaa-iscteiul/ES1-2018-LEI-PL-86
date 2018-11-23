package backend;

import org.junit.BeforeClass;

public class ServerTest {
	static Server server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		server = new Server();
	}

}
