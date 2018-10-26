package frontend;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import integration.TwitterApp;

public class Window {

	public static final Window WINDOW_INSTANCE = new Window();
	private JFrame frame;
	private JPanel right_panel;
	private JPanel left_panel;
	private JPanel buttons_panel;
	private JPanel titles_panel;
	private JPanel sources_panel;
	private JTable table;
	private JButton button_new;
	private JButton button_synchronize;
	private JCheckBox facebook;
	private JCheckBox gmail;
	private JCheckBox twitter;
	private JScrollPane scroll;
	private JLabel title;
	private JLabel subtitle;
	private DefaultTableModel dataModel;
	private String[] header = { "Message", "Type", "Sender", "Source" };
	private String[][] messages = new String[50][100];
	private List<String> selectedBoxes = new ArrayList<String>();
	private TwitterApp twitter_app;

	/**
	 * Creates and initiates a TwitterApp instance named 'twitter_app'.
	 * <p>The twitter_app gets the most recent ISCTE-IUL's tweets from their twitter account to the dataModel table.
	 * <p>Creates and initiates a DefaultTableModel instance named 'dataModel'.
	 * <p>The dataModel table displays the information about ISCTE-IUL to the user.
	 */
	public Window() {

		this.twitter_app = new TwitterApp();

		this.dataModel = new DefaultTableModel(messages, header) {
			public int getColumnCount() {
				return 4;
			}

			public int getRowCount() {
				return 10000;
			}

			public Object getValueAt(int row, int col) {
				return messages[row][col];
			}

			@Override
			public void setValueAt(Object string, int row, int col) {
				messages[row][col] = (String) string;
			}
		};
	}

	/**
	 * Creates and shows the frame/window that the user will be seeing and interacting with.
	 */
	public void start_window() {

		frame = new JFrame("Bom Dia Academia");
		frame.setLayout(new GridLayout(0, 2));

		// Titulo e subtitulo do left_panel
		title = new JLabel("BOM DIA ACADEMIA");
		subtitle = new JLabel("ISCTE");
		titles_panel = new JPanel();
		titles_panel.setLayout(new GridLayout(2, 1));
		title.setFont(title.getFont().deriveFont(40.0f));
		subtitle.setFont(subtitle.getFont().deriveFont(20, 25.6f));
		titles_panel.add(title);
		titles_panel.add(subtitle);

		// right_panel
		right_panel = new JPanel();
		right_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));

		table = new JTable(dataModel);
		scroll = new JScrollPane(table);
		right_panel.add(scroll);
		setColumnsSize("Message");

		// left_panel
		left_panel = new JPanel();
		left_panel.setLayout(new GridLayout(5, 1));
		left_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));

		// Painel dos buttons no left_panel
		button_new = new JButton("NEW");
		button_synchronize = new JButton("SYNCHRONIZE");
		buttons_panel = new JPanel();
		buttons_panel.setLayout(new GridLayout(2, 1));
		buttons_panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		buttons_panel.add(button_new);
		buttons_panel.add(button_synchronize);
		left_panel.add(titles_panel);
		left_panel.add(buttons_panel);

		button_synchronize.addActionListener(new ActionListener() {
			@Override
			public synchronized void actionPerformed(ActionEvent e) {
				getSelectedBoxes();
				for (int i = 0; i < selectedBoxes.size(); i++) {
					switch (selectedBoxes.get(i)) {
					case "gmail":
						break;
					case "facebook":
						break;
					case "twitter":
						List<Table_line> tweets = new ArrayList<Table_line>();
						tweets = twitter_app.getTweets();
						for (int j = 0; j < tweets.size(); j++) {
							fillTableRow(tweets.get(j));
						}
						table.updateUI();
					default:
						break;
					}
				}

			}
		});

		// Painel das checkboxes das fontes de informação no left_panel
		sources_panel = new JPanel();
		sources_panel.setLayout(new GridLayout(3, 1));
		sources_panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		facebook = new JCheckBox("Facebook");
		gmail = new JCheckBox("Gmail");
		twitter = new JCheckBox("Twitter");
		sources_panel.add(facebook);
		sources_panel.add(gmail);
		sources_panel.add(twitter);
		left_panel.add(sources_panel);

		frame.add(left_panel);
		frame.add(right_panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.pack();

		// // TABLE TESTE!
		// Table_line line = new Table_line("triggered ", "testededed", "luis",
		// "facebook");
		// fillTableRow(line);
		// Table_line line1 = new Table_line("please work", "informação", "pedro",
		// "twitter");
		// fillTableRow(line1);
		// //
		// // Teste dos tweets
		// List<Table_line> tweets = new ArrayList<Table_line>();
		// tweets = twitter_app.getTweets();
		// for (int i = 0; i < tweets.size(); i++) {
		// fillTableRow(tweets.get(i));
		// }

	}

	public static Window get_window_instance() {
		return WINDOW_INSTANCE;
	}

	/**
	 * Fills the next available dataModel's row with a new message, along with its type, sender, and source.
	 * <p> It gets the message's information it needs from the provided Table_line object 'line'.
	 * @param line - the provided Table_line object.
	 */
	public void fillTableRow(Table_line line) {
		int row = nextRowAvailable();
		for (int i = 0; i < header.length; i++) {
			switch (dataModel.getColumnName(i)) {
			case "Message":
				dataModel.setValueAt(line.getMessage(), row, i);
				break;
			case "Type":
				dataModel.setValueAt(line.getType(), row, i);
				break;
			case "Sender":
				dataModel.setValueAt(line.getSender(), row, i);
				break;
			case "Source":
				dataModel.setValueAt(line.getSource(), row, i);
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * Tells you which is the next available tableModel's row to add a new message to.
	 * @return the next available row to add a new message to (as an integer); or returns null if the tableModel is full.
	 */
	public int nextRowAvailable() {
		for (int i = 0; i < messages.length; i++) {
			if (messages[i][0] == null) {
				return i;
			}
		}
		// não me lembro como fazer exit -.-
		return (Integer) null;
	}

	/**
	 * Checks which of the check boxes are selected, and returns a list of Strings with the names of those that are.
	 * @return a list of Strings with the name of the check boxes that are selected.
	 */
	public List<String> getSelectedBoxes() {
		if (facebook.isSelected())
			selectedBoxes.add("facebook");
		if (twitter.isSelected())
			selectedBoxes.add("twitter");
		if (gmail.isSelected())
			selectedBoxes.add("gmail");
		return selectedBoxes;
	}
	
	/**
	 * If the given string (parameter "Column") has the name of one of the dataModel's columns, it creates a TableColumn
	 * and sets its width at 300.
	 * @param Column - the name of the column of which size you're trying to set
	 */
	public void setColumnsSize(String Column) {
		for (int i = 0; i < header.length; i++) {
			if (dataModel.getColumnName(i) == Column) {
				TableColumn tableColumn = table.getTableHeader().getColumnModel().getColumn(i);
				tableColumn.setPreferredWidth(300);
			}
		}
	}

	public static void main(String[] args) {
		Window.get_window_instance().start_window();
	}

}