package cus1156.proj3;
/**
 * @author Aidan Battad
 * A class called Topic that represents a topic in a message board. Extends Observable class.
 *
 */
import java.util.ArrayList;
import java.util.Observable;

public class Topic extends Observable
{
	private ArrayList<Message> messages;
	private ArrayList<User>subs;
	private String topic;
	private Message msg;
	
	/**
	 * Constucts a topic object
	 * @param name of the topic
	 */
	public Topic(String topic)
	{
		this.topic=topic;
		messages= new ArrayList <Message>();
		subs= new ArrayList<User>();
	}
	
	/**
	 * adds a user to the subscriber list
	 * @param User object
	 *
	 */
	public void setSub(User usr)
	{
		subs.add(usr);
	}
	
	/**
	 * Returns the list of subscribers for this topic
	 * @return list of subscribers
	 *
	 */
	public ArrayList<User> getSubs()
	{
		return subs;
	}
	
	/**
	 * Adds a message to the topic's list of messages. Sets the current message.
	 *@param Message object to be added
	 */
	public void addMessageToTopic(Message m)
	{
		messages.add(m);
		msg=m;
	}
	
	/**
	 * Notifies the observers/users of a change to a Topic
	 */
	public void notifyUsers()
	{
		//we must first call setChanged() to indicate
				//that the state has changed and then we
				// call notifyObservers() in the parent class
				// which notifes all observers that the state
				// has changed. 
				setChanged();
				notifyObservers();
	}
	
	/**
	 * Retrieves the current Message
	 * @return message
	 *
	 */
	public Message getMessage()
	{
		return msg;
	}
	
	/**
	 * Gets the name of the topic
	 * @return string name of topic
	 *
	 */
	public String getTopicName()
	{
		return topic;
	}
	
	/**
	 * Checks if there are messages in the user object
	 * @return true if the user contains no messages, false otherwise
	 */
	public boolean doesTopicHaveMessages()
	{	
		if (!messages.isEmpty())//if there is a message
		{ 
			return true;
		}
		return false;
	}

}
