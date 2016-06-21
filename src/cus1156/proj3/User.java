package cus1156.proj3;
/**
 * @author Aidan Battad
 * A class called User that creates a user who will subscribe to a topic. Implements the interface Observer.
 */
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class User implements Observer
{
	private String name;
	private Topic tpc;
	private Message msg;
	private ArrayList <Message> msgList=new ArrayList <Message>();;
	
	/**
    Creates a User object.
    @param name- name of the user
	 */
	public User(String name)
	{
		this.name=name;
		this.getTopic();
	}

	/**
    Creates a Message object.
    @return returns object 
	 */
	public Topic getTopic()
	{
		return tpc;
	}
	
	/**
	 * returns the name of the user from the User object
	 * @return name of user
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * adds message to the User's list of messages
	 * @param Message object to be added
	 */
	public void addMessageToUser(Message message)
	{
		msgList.add(message);
	}
	
	/**
	 * After notifyObservers() method is called, all the users subscribed to the topic have their list of messages
	 * updated
	 * @param Observable topic in which its list messages has changed, the new Message object
	 */
	@Override
	public void update(Observable topic, Object newMessage) 
	{
		Topic topicAlert= (Topic) topic;
		tpc= topicAlert;
		msg=tpc.getMessage();
		addMessageToUser(msg);
		System.out.println("User read the message");
		//displayMessages();
	}
	
	/**
	 * returns the entire private ArrayList of messages from the User object
	 * @return arraylist of messages
	 *
	 */
	public ArrayList<Message> getAllMessages()
	{
		return msgList;
	}
	
	/*
	//testing if update method (works)
	public void displayMessages()
	{
		System.out.println("The user's list is: ");
		for(Message m: msgList)
		{
			System.out.println(m.toString());
			System.out.println("\n");
		}
	}*/
	
	/**
	 * Checks if there are messages in the user object
	 * @return true if the user contains no messages, false otherwise
	 */
	public boolean doesUserHaveMessages()
	{	
		if (!msgList.isEmpty())//if there is a message
		{ 
			return true;
		}
		return false;
	}


}
