package cus1156.proj3;
/**
 *  Constructs a Message object
 * @author Aidan Battad
 *
 */
public class Message 
{
	
	private String sender;
	private String text;
	private String topic;
	
	/**
    Creates a Message object.
    @param from- the sender of the message
    @param recip - the recipient of the message
    @param txt - the message text
	 */
	public Message(String composer, String msg, String tpc)
	{  
	  sender = composer;
	  text = msg;
	  topic = tpc;
	  
	}
	
	/**
	 * retrieves the sender's name 
	 * @return sender's name
	 */
	public String getSender() {
		return sender;
	}

	
	/**
    Formats the message.
    @return formatted message
	 */
	public String toString()
	{
		return "Posted on Topic: " + topic + "\n" + "Posted by: " + sender + text + "*****************\n";
	}
}

