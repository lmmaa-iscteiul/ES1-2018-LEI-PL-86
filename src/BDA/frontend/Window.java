package frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import backend.Server;

public class Window {

	public static final Window WINDOW_INSTANCE = new Window();
	private JFrame frame;
	private JPanel right_panel;
	private JPanel left_panel;
	private JPanel buttons_panel;
	private JPanel titles_panel;
	private JPanel sources_panel;
	private JPanel search_panel;
	private JTable table;
	private JButton button_new;
	private JButton button_synchronize;
	private JCheckBox facebook;
	private JCheckBox gmail;
	private JCheckBox twitter;
	private JScrollPane scroll;
	private JTextField searchTextField;
	private JButton searchBtn;
	private JLabel title;
	private JLabel subtitle;
	private Table_model dataModel;
	private String[] header = { "Message", "Type", "Sender", "Source" };
	private List<String> selectedBoxes = new ArrayList<String>();
	private Server server = new Server();

	/**
	 * Creates and initiates a TwitterApp instance named 'twitter_app'.
	 * <p>
	 * The twitter_app gets the most recent ISCTE-IUL's tweets from their twitter
	 * account to the dataModel table.
	 * <p>
	 * Creates and initiates a DefaultTableModel instance named 'dataModel'.
	 * <p>
	 * The dataModel table displays the information about ISCTE-IUL to the user.
	 */
	public Window() {

		this.dataModel = new Table_model(server.getResultsList(), header);
		this.table = new JTable(dataModel);
		this.frame = new JFrame("Bom Dia Academia");
		this.title = new JLabel("BOM DIA ACADEMIA");
		this.subtitle = new JLabel("ISCTE");
		this.titles_panel = new JPanel();
		this.right_panel = new JPanel();
		this.scroll = new JScrollPane(table);
		this.left_panel = new JPanel();
		this.button_new = new JButton("NEW");
		this.button_synchronize = new JButton("SYNCHRONIZE");
		this.buttons_panel = new JPanel();
		this.sources_panel = new JPanel();
		this.facebook = new JCheckBox("Facebook");
		this.gmail = new JCheckBox("Gmail");
		this.twitter = new JCheckBox("Twitter");
	}

	public Server getServer() {
		return server;
	}

	public static Window getWindowInstance() {
		return WINDOW_INSTANCE;
	}

	public JCheckBox getFacebook() {
		return facebook;
	}

	public void setFacebook(JCheckBox facebook) {
		this.facebook = facebook;
	}

	public JCheckBox getGmail() {
		return gmail;
	}

	public void setGmail(JCheckBox gmail) {
		this.gmail = gmail;
	}

	public JCheckBox getTwitter() {
		return twitter;
	}

	public void setTwitter(JCheckBox twitter) {
		this.twitter = twitter;
	}

	public void setSelectedBoxes(List<String> selectedBoxes) {
		this.selectedBoxes = selectedBoxes;
	}

	/**
	 * Creates and shows the frame/window that the user will be seeing and
	 * interacting with.
	 */
	public void start_window() {

		frame.setLayout(new GridLayout(0, 2));

		// Titulo e subtitulo do left_panel

		titles_panel.setLayout(new GridLayout(2, 1));
		title.setFont(title.getFont().deriveFont(40.0f));
		subtitle.setFont(subtitle.getFont().deriveFont(20, 25.6f));
		titles_panel.add(title);
		titles_panel.add(subtitle);

		// right_panel

		right_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));

		right_panel.add(scroll);

		// left_panel
		left_panel = new JPanel();
		left_panel.setLayout(new GridLayout(6, 1));
		left_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));

		// Painel dos buttons no left_panel

		buttons_panel.setLayout(new GridLayout(2, 1));
		buttons_panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		buttons_panel.add(button_new);
		buttons_panel.add(button_synchronize);
		left_panel.add(titles_panel);
		left_panel.add(buttons_panel);

		button_synchronize.addActionListener(new ActionListener() {
			@Override
			public synchronized void actionPerformed(ActionEvent e) {
				server.getResultsList().clear();
				server.getUnreadLines().clear();
				table.updateUI();
				getSelectedBoxes();
				for (int i = 0; i < selectedBoxes.size(); i++) {
					switch (selectedBoxes.get(i)) {
					case "gmail":
						break;
					case "facebook":
						break;
					case "twitter":
						List<Table_line> tweets = new ArrayList<Table_line>();
						tweets = server.getTwitter().getTweets();
						for (Table_line line : server.getTwitter().getTweets()) {
							server.fillUnreadLines(line);
						}
					default:
						break;
					}
				}
				server.getTaskList().createTasks(" ");
				table.updateUI();
			}
		});
		// Painel das checkboxes das fontes de informa��o no left_panel
		sources_panel = new JPanel();
		sources_panel.setLayout(new GridLayout(3, 1));
		sources_panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		sources_panel.add(facebook);
		sources_panel.add(gmail);
		sources_panel.add(twitter);
		left_panel.add(sources_panel);

		// Painel da pesquisa: TextBox + Button
		search_panel = new JPanel();
		search_panel.setLayout(new GridLayout(1, 2));
		search_panel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		searchTextField = new JTextField("Search for keywords");
		searchTextField.setForeground(Color.GRAY);
		searchTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (searchTextField.getText().isEmpty()) {
					searchTextField.setForeground(Color.GRAY);
					searchTextField.setText("Search for keywords");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (searchTextField.getText().equals("Search for keywords")) {
					searchTextField.setForeground(Color.BLACK);
					searchTextField.setText("");
				}
			}
		});
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public synchronized void actionPerformed(ActionEvent e) {
				server.getResultsList().clear();
				server.getUnreadLines().clear();
				table.updateUI();
				getSelectedBoxes();
				for (int i = 0; i < selectedBoxes.size(); i++) {
					switch (selectedBoxes.get(i)) {
					case "gmail":
						break;
					case "facebook":
						break;
					case "twitter":
						List<Table_line> tweets = new ArrayList<Table_line>();
						tweets = server.getTwitter().getTweets();
						for (Table_line line : server.getTwitter().getTweets()) {
							server.fillUnreadLines(line);
						}
					default:
						break;
					}
				}
				server.getTaskList().createTasks(searchTextField.getText());
				while (!(server.AllWorkersAreDone())) {
				}
				table.updateUI();
			}
		});
		search_panel.add(searchTextField);
		search_panel.add(searchBtn);
		left_panel.add(search_panel);

		frame.add(left_panel);
		frame.add(right_panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.pack();
	}

	public static Window get_window_instance() {
		return WINDOW_INSTANCE;
	}

	/**
	 * Fills the next available dataModel's row with a new message, along with its
	 * type, sender, and source.
	 * <p>
	 * It gets the message's information it needs from the provided Table_line
	 * object 'line'.
	 * 
	 * @param line - the provided Table_line object.
	 */
//	public void fillTableRow(Table_line line) {
//		int row = nextRowAvailable();
//		for (int i = 0; i < header.length; i++) {
//			switch (dataModel.getColumnName(i)) {
//			case "Message":
//				dataModel.setValueAt(line.getMessage(), row, i);
//				break;
//			case "Type":
//				dataModel.setValueAt(line.getType(), row, i);
//				break;
//			case "Sender":
//				dataModel.setValueAt(line.getSender(), row, i);
//				break;
//			case "Source":
//				dataModel.setValueAt(line.getSource(), row, i);
//				break;
//			default:
//				break;
//			}
//		}
//	}

	public Table_model getDataModel() {
		return dataModel;
	}

	public void setDataModel(Table_model dataModel) {
		this.dataModel = dataModel;
	}

//	public void fillResultsTable() {
//		for (Table_line line : getWindowInstance().getServer().getResultsList()) {
//			fillTableRow(line);
//		}
//	}

	/**
	 * Tells you which is the next available tableModel's row to add a new message
	 * to.
	 * 
	 * @return the next available row to add a new message to (as an integer); or
	 *         returns null if the tableModel is full.
	 */
//	public int nextRowAvailable() {
//		for (int i = 0; i < server.getResultsList().size(); i++) {
//			if (server.getResultsList() == null) {
//				return i;
//			}
//		}
//		return (Integer) null;
//	}

	/**
	 * Checks which of the check boxes are selected, and returns a list of Strings
	 * with the names of those that are.
	 * 
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
	 * If the given string (parameter "Column") has the name of one of the
	 * dataModel's columns, it creates a TableColumn and sets its width at 300.
	 * 
	 * @param Column - the name of the column of which size you're trying to set
	 */
//	public void setColumnsSize(String Column) {
//		for (int i = 0; i < header.length; i++) {
//			if (dataModel.getColumnName(i) == Column) {
//				TableColumn tableColumn = table.getTableHeader().getColumnModel().getColumn(i);
//				tableColumn.setPreferredWidth(300);
//			}
//		}
//	}

	public static void main(String args[]) {
		Window window = WINDOW_INSTANCE;
		window.start_window();
	}

}