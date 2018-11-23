package frontend;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

 import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class WindowTest {
	static Window window;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		window = new Window();
	}

	@Test
	public void testGet_window_instance() throws Exception {
		window = Window.get_window_instance();
		assertFalse(window.equals(null));
	}

	@Test
	public void testStart_window() throws Exception {
		window.start_window();
		assertTrue(window.getFrame().isVisible());
		assertFalse(window.getButton_synchronize().getActionListeners().equals(null));
		assertFalse(window.getButton_new().getActionListeners().equals(null));
		window.getTwitter().setSelected(true);
		window.getFacebook().setSelected(true);
		window.getGmail().setSelected(true);
		window.getButton_synchronize().doClick();
		assertTrue(window.getTwitter().isSelected());

		window.getSearchTextField().grabFocus();
		window.getSearchTextField().transferFocus();

		window.getSearchBtn().doClick();
	}

	@Test
	public void testGetSelectedBoxes() throws Exception {
		window.getFacebook().setSelected(true);
		window.getGmail().setSelected(true);
		window.getTwitter().setSelected(true);
		List<String> list = window.getSelectedBoxes();
		for (int i = 0; i < list.size(); i++) {
			switch (list.get(i)) {
			case "facebook":
				assertTrue(window.getFacebook().isSelected());
				break;
			case "twitter":
				assertTrue(window.getTwitter().isSelected());
				break;
			case "gmail":
				assertTrue(window.getGmail().isSelected());
				break;
			default:
				break;
			}
		}
	}

	@Test
	public void testMain() throws Exception {
		window.main(null);
	}
}
