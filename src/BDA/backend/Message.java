package backend;

public class Message {

	private String MESSAGE;
	private String TYPE;
	private String SENDER;
	private String SOURCE;

	public Message(String message, String type, String sender, String source) {
		this.MESSAGE = message;
		this.TYPE = type;
		this.SENDER = sender;
		this.SOURCE = source;
	}

}
