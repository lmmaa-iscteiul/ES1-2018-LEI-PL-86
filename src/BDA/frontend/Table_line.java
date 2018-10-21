package frontend;

public class Table_line {

	private String MESSAGE;
	private String TYPE;
	private String SENDER;
	private String SOURCE;
	private int row;

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getSENDER() {
		return SENDER;
	}

	public void setSENDER(String sENDER) {
		SENDER = sENDER;
	}

	public String getSOURCE() {
		return SOURCE;
	}

	public void setSOURCE(String sOURCE) {
		SOURCE = sOURCE;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Table_line(String message, String type, String sender, String source, int row) {
		this.MESSAGE = message;
		this.TYPE = type;
		this.SENDER = sender;
		this.SOURCE = source;
		this.row = row;
	}

}
