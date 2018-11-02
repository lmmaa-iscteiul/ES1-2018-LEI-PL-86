package backend;

import frontend.Table_line;

public class Task {

	private Table_line line;
	private String palavra;

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public Table_line getLine() {
		return line;
	}

	public void setLine(Table_line line) {
		this.line = line;
	}

	public Task(String p, Table_line l) {
		this.palavra = p;
		this.line = l;
	}

}