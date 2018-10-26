package backend;

public class Message {

	private String MESSAGE;
	private String TYPE;
	private String SENDER;
	private String SOURCE;

	/**
	 * Defines what a Message is composed of: a message, a type, a sender, and a source.
	 * @param message - the shared message/content.
	 * @param type - the message's type.
	 * @param sender - who shared the message.
	 * @param source - where the message was obtained from.
	 */
	public Message(String message, String type, String sender, String source) {
		this.MESSAGE = message;
		this.TYPE = type;
		this.SENDER = sender;
		this.SOURCE = source;
	}
}
