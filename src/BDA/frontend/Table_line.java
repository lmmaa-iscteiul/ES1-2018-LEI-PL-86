package frontend;

public class Table_line {

	private String message;
	private String type;
	private String sender;
	private String source;

	public Table_line(String message, String type, String sender, String source) {
		this.message = message;
		this.type = type;
		this.sender = sender;
		this.source = source;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
