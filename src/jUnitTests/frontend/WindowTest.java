package frontend;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.java.swing.plaf.windows.resources.windows;

import integration.TwitterApp;

public class WindowTest {
	public static final Window WINDOW_INSTANCE = new Window();
	public static JFrame frame;
	public static JPanel right_panel;
	public static JPanel left_panel;
	public static JPanel buttons_panel;
	public static JPanel titles_panel;
	public static JPanel sources_panel;
	public static JTable table;
	public static JButton button_new;
	public static JButton button_synchronize;
	public static JCheckBox facebook;
	public static JCheckBox gmail;
	public static JCheckBox twitter;
	public static JScrollPane scroll;
	public static JLabel title;
	public static JLabel subtitle;
	public static DefaultTableModel dataModel;
	public static String[] header = { "Message", "Type", "Sender", "Source" };
	public static String[][] messages = new String[50][100];
	public static List<String> selectedBoxes = new ArrayList<String>();
	public static TwitterApp twitter_app;
	public int next;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// twitter_app = new TwitterApp();
		// twitter_app = new TwitterApp();
		// frame = new JFrame("Bom Dia Academia");
		// title = new JLabel("BOM DIA ACADEMIA");
		// subtitle = new JLabel("ISCTE");
		// titles_panel = new JPanel();
		// right_panel = new JPanel();
		// table = new JTable(dataModel);
		// scroll = new JScrollPane(table);
		// left_panel = new JPanel();
		// button_new = new JButton("NEW");
		// button_synchronize = new JButton("SYNCHRONIZE");
		// buttons_panel = new JPanel();
		// sources_panel = new JPanel();
		// facebook = new JCheckBox("Facebook");
		// gmail = new JCheckBox("Gmail");
		// twitter = new JCheckBox("Twitter");

	}

	@Test
	public void testGet_window_instance() throws Exception {
		assertTrue(WINDOW_INSTANCE != null);
	}

	@Test
	public void testStart_window() throws Exception {
		WINDOW_INSTANCE.start_window();
	}

//	@Test
//	public void testFillTableRow() throws Exception {
//		Table_line tl = new Table_line("message", "type", "sender", "source");
//		WINDOW_INSTANCE.fillTableRow(tl);
//		assertTrue(WINDOW_INSTANCE.getDataModel().getValueAt(0, 0) == "message");
//		assertTrue(WINDOW_INSTANCE.getDataModel().getValueAt(0, 1) == "type");
//		assertTrue(WINDOW_INSTANCE.getDataModel().getValueAt(0, 2) == "sender");
//		assertTrue(WINDOW_INSTANCE.getDataModel().getValueAt(0, 3) == "source");
//
//	}

//	@Test
//	public void testNextRowAvailable() throws Exception {
//		int aux = WINDOW_INSTANCE.nextRowAvailable();
//		assertFalse(aux == next);
//	}

	@Test
	public void testGetSelectedBoxes() throws Exception {
		WINDOW_INSTANCE.getFacebook().setSelected(true);
		List<String> list = WINDOW_INSTANCE.getSelectedBoxes();
		for (int i = 0; i < list.size(); i++) {
			switch (list.get(i)) {
			case "facebook":
				assertTrue(facebook.isSelected());
				break;
			case "twitter":
				assertFalse(twitter.isSelected());
				break;
			case "gmail":
				assertFalse(gmail.isSelected());
				break;
			default:
				break;
			}
		}
	}

	@Test
	public void testSetColumnsSize() throws Exception {
	}

}
