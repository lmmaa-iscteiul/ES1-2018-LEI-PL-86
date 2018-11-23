package frontend;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

public class Table_model extends AbstractTableModel {
	public LinkedList<Table_line> list;
	String[] header;

	public Table_model(LinkedList<Table_line> l, String[] head) {
		this.list = l;
		this.header = head;
	}

	public LinkedList<Table_line> getList() {
		return list;
	}

	public void setList(LinkedList<Table_line> list) {
		this.list = list;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public void addList(LinkedList<Table_line> data) {
		this.list = data;
	}

	public int getColumnCount() {
		return header.length;
	}

	public int getRowCount() {
		return list.size();
	}

	public String getColumnName(int col) {
		return header[col];
	}

	/**
	 * 
	 * @param row - row of the wanted value
	 * @param col - column of the wanted value
	 * @return the value at a specific position in the table (given by the row and column).
	 */
	public String getValueAt(int row, int col) {
		String temp = null;
		switch (col) {
		case 0:
			temp = list.get(row).getMessage();
			break;
		case 1:
			temp = list.get(row).getType();
			break;
		case 2:
			temp = list.get(row).getSender();
			break;
		case 3:
			temp = list.get(row).getSource();
			break;
		default:
			break;
		}
		return temp;
	}

}