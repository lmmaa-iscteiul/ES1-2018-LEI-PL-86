package frontend;

import java.awt.BorderLayout;
import java.awt.Container;
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
	private JPanel right_panel;
	private JPanel left_panel;
	private JFrame frame;
	private JTable table;
	private JButton button_new;
	private JButton button_sinchronize;
	private JScrollPane scroll;
	private JTextField title;
	private TableModel dataModel;

	public Window() {
		this.frame = new JFrame();
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
		this.scroll = new JScrollPane(table);
		this.button_new = new JButton("NEW");
		this.button_sinchronize = new JButton("SYNCHRONIZE");
		this.right_panel = new JPanel();
		this.left_panel = new JPanel();
		this.title = new JTextField("BOM DIA ACADEMIA");
	}

	public void start_window() {

		frame.setLayout(new GridLayout(0, 2));
		frame.add(left_panel);
		frame.add(right_panel);
		left_panel.setLayout(new GridLayout(5, 0));
		right_panel.setLayout(new GridLayout(1, 1));
		title.setEditable(false);
		left_panel.add(title);
		right_panel.add(scroll);
		left_panel.add(button_new);
		left_panel.add(button_sinchronize);
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
