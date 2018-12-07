package frontend;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.LinkedList;
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
import integration.TwitterApp;

public class Window {

	public static final Window WINDOW_INSTANCE = new Window();
	private JFrame frame;
	private JPanel right_panel;
	private JPanel left_panel;
	private JPanel buttons_panel;
	private JPanel titles_panel;
	private JPanel sources_panel;
	private JPanel search_panel;
	private JPanel twetting_panel;
	private JTable table;
	private JButton button_new;
	private JButton button_synchronize;
	private JCheckBox facebook;
	private JCheckBox gmail;
	private JCheckBox twitter;
	private JScrollPane scroll;
	private JTextField searchTextField;
	public JTextField twetting_textField;
	private JButton searchBtn;
	private JLabel title;
	private JLabel subtitle;
	private Table_model dataModel;
	private String[] header = { "Message", "Type", "Sender", "Source" };
	private List<String> selectedBoxes = new ArrayList<String>();
	private Server server = new Server();
	private TwitterApp twitterapp = new TwitterApp();

	/**
	 * Initiates the component's of the UI window.
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

	/**
	 * Creates and shows the frame/window that the user will be seeing and
	 * interacting with.
	 */
	public void start_window() {
		table.getTableHeader().setBackground(new Color(0, 115, 204));
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(Color.BLUE);
		frame.setLayout(new GridLayout(0, 2));
		button_synchronize.setBackground(new Color(0, 115, 204));
		button_new.setBackground(new Color(0, 115, 204));

		// Titulo e subtitulo do left_panel

		titles_panel.setLayout(new GridLayout(2, 1));
		titles_panel.setBackground(Color.WHITE);
		title.setFont(title.getFont().deriveFont(40.0f));
		subtitle.setFont(subtitle.getFont().deriveFont(20, 25.6f));
		titles_panel.add(title);
		titles_panel.add(subtitle);

		// right_panel

		right_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));
		right_panel.setBackground(Color.WHITE);

		right_panel.add(scroll);

		// left_panel
		left_panel = new JPanel();
		left_panel.setBackground(Color.white);
		left_panel.setLayout(new GridLayout(6, 1));
		left_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));

		// Painel dos buttons no left_panel
		buttons_panel.setLayout(new GridLayout(2, 1));
		buttons_panel.setBackground(Color.white);
		buttons_panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		buttons_panel.add(button_new);
		buttons_panel.add(button_synchronize);
		left_panel.add(titles_panel);
		left_panel.add(buttons_panel);

		// Caixa texto para tweetar
		twetting_panel = new JPanel();
		twetting_panel.setBackground(Color.white);
		twetting_panel.setLayout(new GridLayout(1, 1));
		twetting_panel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		twetting_textField = new JTextField("What do you wish to tweet?");
		twetting_textField.setForeground(Color.GRAY);
		twetting_textField.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (twetting_textField.getText().isEmpty()) {
					twetting_textField.setForeground(Color.GRAY);
					twetting_textField.setText("What do you wish to tweet?");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (twetting_textField.getText().equals("What do you wish to tweet?")) {
					twetting_textField.setForeground(Color.BLACK);
					twetting_textField.setText("");
				}
			}
		});
		
		button_new.addActionListener(new ActionListener() {
			@Override
			public synchronized void actionPerformed(ActionEvent e) {
				String mensagem = twetting_textField.getText();
				server.postTweet(mensagem);
			}
		});

		twetting_panel.add(twetting_textField);
		left_panel.add(twetting_panel);

		// Syncronize button
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
		facebook.setBackground(Color.white);
		sources_panel.add(gmail);
		gmail.setBackground(Color.white);
		sources_panel.add(twitter);
		twitter.setBackground(Color.white);
		sources_panel.setBackground(Color.WHITE);
		left_panel.add(sources_panel);

		// Painel da pesquisa: TextBox + Button
		search_panel = new JPanel();
		search_panel.setBackground(Color.white);
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
				table.updateUI();
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

	public JButton getButton_new() {
		return button_new;
	}

	public JButton getButton_synchronize() {
		return button_synchronize;
	}

	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public JCheckBox getFacebook() {
		return facebook;
	}

	public JCheckBox getGmail() {
		return gmail;
	}

	public JCheckBox getTwitter() {
		return twitter;
	}

	public JFrame getFrame() {
		return frame;
	}

	public static Window get_window_instance() {
		return WINDOW_INSTANCE;
	}

	/**
	 * @return the data model table
	 */
	public Table_model getDataModel() {
		return dataModel;
	}

	/**
	 * Sets the data model table as the given object.
	 * 
	 * @param dataModel
	 *            - the data model to be set
	 */
	public void setDataModel(Table_model dataModel) {
		this.dataModel = dataModel;
	}

	/**
	 * Checks which of the check boxes are selected, and returns a list of
	 * Strings with the names of those that are.
	 * 
	 * @return a list of Strings with the name of the check boxes that are
	 *         selected.
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

	public static void main(String args[]) {
		Window window = WINDOW_INSTANCE;
		window.start_window();
	}

}