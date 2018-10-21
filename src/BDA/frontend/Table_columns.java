package frontend;

public enum Table_columns {
	MESSAGE(0, "MESSAGE"), TYPE(1, "TYPE"), SENDER(2, "SENDER"), SOURCE(3, "SOURCE");
	private final int column;
	private final String str;

	Table_columns(int column, String str) {
		this.column = column;
		this.str = str;
	}

	public int getColumn() {
		return column;
	}
}