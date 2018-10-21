package frontend;

import java.awt.GridLayout;
import java.awt.Point;
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
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class Window {

	public static final Window WINDOW_INSTANCE = new Window();
	private final int MESSAGE = 1;
	private final int TYPE = 2;
	private final int SENDER = 3;
	private final int SOURCE = 4;
	private JFrame frame;
	private JPanel right_panel;
	private JPanel left_panel;
	private JPanel buttons_panel;
	private JPanel titles_panel;
	private JPanel sources_panel;
	private JTable table;
	private JButton button_new;
	private JButton button_sinchronize;
	private JCheckBox facebook;
	private JCheckBox gmail;
	private JCheckBox twitter;
	private JScrollPane scroll;
	private JLabel title;
	private JLabel subtitle;
	private JTextField tf;
	private TableModel dataModel;
	private String[][] messages = new String[50][100];

	public Window() {

		this.dataModel = new AbstractTableModel() {
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

	public void start_window() {
		frame = new JFrame("Bom Dia Academia");
		frame.setLayout(new GridLayout(0, 2));

		// left_panel
		left_panel = new JPanel();
		left_panel.setLayout(new GridLayout(5, 1));
		left_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));

		this.table = new JTable(dataModel);
		// Titulo e subtitulo do left_panel
		title = new JLabel("BOM DIA ACADEMIA");
		subtitle = new JLabel("School Companion wut??");
		titles_panel = new JPanel();
		titles_panel.setLayout(new GridLayout(2, 1));
		title.setFont(title.getFont().deriveFont(40.0f));
		subtitle.setFont(subtitle.getFont().deriveFont(20, 25.6f));
		titles_panel.add(title);
		titles_panel.add(subtitle);
		left_panel.add(titles_panel);

		// Painel dos buttons no left_panel
		button_new = new JButton("NEW");
		button_sinchronize = new JButton("SYNCHRONIZE");
		buttons_panel = new JPanel();
		buttons_panel.setLayout(new GridLayout(2, 1));
		buttons_panel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		buttons_panel.add(button_new);
		buttons_panel.add(button_sinchronize);
		left_panel.add(buttons_panel);

		// Painel das checkboxes das fontes de informaï¿½ï¿½o no left_panel
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

		// right_panel
		right_panel = new JPanel();
		right_panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 15));

		scroll = new JScrollPane(table);
		right_panel.add(scroll);

		frame.add(left_panel);
		frame.add(right_panel);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.pack();

		// TABLE TESTE!
		Table_line line = new Table_line("this is a message", "teste", "luis", "facebook", 0);
		fillTableRow(line);
		Table_line line1 = new Table_line("this is a message1", "teste1", "pedro", "twitter", 0);
		fillTableRow(line1);

	}

	public static Window get_window_instance() {
		return WINDOW_INSTANCE;
	}

	public void fillTableRow(Table_line line) {
		int row = nextRowAvailable();
		dataModel.setValueAt(line.getMESSAGE(), row, Table_columns.MESSAGE.getColumn());
		dataModel.setValueAt(line.getTYPE(), row, Table_columns.TYPE.getColumn());
		dataModel.setValueAt(line.getSENDER(), row, Table_columns.SENDER.getColumn());
		dataModel.setValueAt(line.getSOURCE(), row, Table_columns.SOURCE.getColumn());
	}

	public int nextRowAvailable() {
		for (int i = 0; i < messages.length; i++) {
			if (messages[i][0] == null) {
				return i;
			}
		}
		// não me lembro como fazer exit -.-
		return (Integer) null;
	}

	public static void main(String[] args) {
		Window.get_window_instance().start_window();
	}

}