package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import backend.Message;

public class Window {

	public static final Window WINDOW_INSTANCE = new Window();
	private final int MESSAGE = 1;
	private final int TYPE = 2;
	private final int SENDER = 3;
	private final int SOURCE = 4;
	private List<Message> message_list;
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
	private JLabel message;
	private JLabel kind;
	private JLabel sender;
	private JLabel source;
	private JTextField tf;
	private TableModel dataModel;

	public Window() {

		this.message_list = new LinkedList<Message>();
		this.dataModel = new AbstractTableModel() {
			public int getColumnCount() {
				return 4;
			}

			public int getRowCount() {
				return 10000;
			}

			public Object getValueAt(int row, int col) {
				return message;
			}
		};
		
		this.table = new JTable(dataModel);
	}

	public void start_window() {
		frame = new JFrame("Bom Dia Academia");
		frame.setLayout(new GridLayout(0, 2));
		
		//left_panel
		left_panel = new JPanel();
		left_panel.setLayout(new GridLayout(5, 1));
		left_panel.setBorder(BorderFactory.createEmptyBorder(15,20,20,15));

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
		
		//Painel dos buttons no left_panel
		button_new = new JButton("NEW");
		button_sinchronize = new JButton("SYNCHRONIZE");
		buttons_panel = new JPanel();
		buttons_panel.setLayout(new GridLayout(2, 1));
		buttons_panel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		buttons_panel.add(button_new);
		buttons_panel.add(button_sinchronize);
		left_panel.add(buttons_panel);
		
		//Painel das checkboxes das fontes de informa��o no left_panel
		sources_panel = new JPanel();
		sources_panel.setLayout(new GridLayout(3,1));
		sources_panel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		facebook = new JCheckBox("Facebook");
		gmail = new JCheckBox("Gmail");
		twitter = new JCheckBox("Twitter");
		sources_panel.add(facebook);
		sources_panel.add(gmail);
		sources_panel.add(twitter);	
		left_panel.add(sources_panel);
		
		//right_panel
		right_panel = new JPanel();
		right_panel.setBorder(BorderFactory.createEmptyBorder(15,20,20,15));
		
		scroll = new JScrollPane(table);
		right_panel.add(scroll);	
		
		frame.add(left_panel);
		frame.add(right_panel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.pack();
		dataModel.setValueAt("this is a message", MESSAGE, 1);

	}

	public static Window get_window_instance() {
		return WINDOW_INSTANCE;
	}

	public static void main(String[] args) {
		Window.get_window_instance().start_window();
	}

}