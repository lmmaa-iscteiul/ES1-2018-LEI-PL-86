package frontend;

public class Table_line {

	private String message;
	private String type;
	private String sender;
	private String source;

	/**
	 * Creates a line that will be displayed on the DefaultTableModel.
	 * <p>
	 * Each line contains a message, along with the message's type, sender, and
	 * source.
	 * 
	 * @param message - a String with the actual message/content that the sender
	 *                wanted to share.
	 * @param type    - the type of message.
	 * @param sender  - who sent the message.
	 * @param source  - where the message was shared, or from where we got said
	 *                message.
	 */
	public Table_line(String message, String type, String sender, String source) {
		this.message = message;
		this.type = type;
		this.sender = sender;
		this.source = source;
	}

	/**
	 * Gets the Table_line's message.
	 * 
	 * @return the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets with Table_line's message.
	 * 
	 * @param message - the message you want to share.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the Table_line's message type.
	 * 
	 * @return the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets with Table_line's type.
	 * 
	 * @param type - the type of the message you want to share.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the Table_line's message sender.
	 * 
	 * @return the sender.
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Sets with Table_line's sender.
	 * 
	 * @param sender - the sender of the message you want to share.
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Gets the Table_line's message source.
	 * 
	 * @return the source.
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Sets with Table_line's source.
	 * 
	 * @param source - the source of the message you want to share.
	 */
	public void setSource(String source) {
		this.source = source;
	}

}
