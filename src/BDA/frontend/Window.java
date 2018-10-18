package frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import backend.Message;

public class Window {

	public static final Window WINDOW_INSTANCE = new Window();
	private JPanel left_pane;
	private JPanel right_pane;
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
		this.left_pane = new JPanel();
		this.right_pane = new JPanel();
		this.title = new JTextField("BOM DIA ACADEMIA");
	}

	public void start_window() {

		frame.setLayout(new BorderLayout());
		frame.add(left_pane, BorderLayout.WEST);
		frame.add(right_pane, BorderLayout.EAST);
		left_pane.setLayout(new GridLayout(5, 0));
		title.setEditable(false);
		left_pane.add(title);
		right_pane.add(scroll);
		left_pane.add(button_new);
		left_pane.add(button_sinchronize);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public static Window get_window_instance() {
		return WINDOW_INSTANCE;
	}

	public static void main(String[] args) {
		Window.get_window_instance().start_window();
	}

}
