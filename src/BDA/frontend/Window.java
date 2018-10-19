package frontend;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import backend.Message;

public class Window {

	public static final Window WINDOW_INSTANCE = new Window();
	private JPanel right_panel;
	private JPanel left_panel;
	private JFrame frame;
	private JList message_list;
	private JButton button_new;
	private JButton button_sinchronize;
	private JScrollPane scroll;
	private JTextField title;

	public Window() {
		this.frame = new JFrame();
		this.message_list = new JList<String>();
		this.scroll = new JScrollPane(message_list);
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
//		frame.pack();
	}

	public static Window get_window_instance() {
		return WINDOW_INSTANCE;
	}

	public static void main(String[] args) {
		Window.get_window_instance().start_window();
	}

}
