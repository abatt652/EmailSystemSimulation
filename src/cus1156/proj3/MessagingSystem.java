package cus1156.proj3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aidan Battad
   This class represents the method in which the messages are send and retrieved from
   the MessagingSystem 
*/

public class MessagingSystem 
{
	private Map <String, Topic> topics;
	private Map <String, User> users;
	private User person;
	
	/**
	Creates a MessagingSystem object which contains a HashMap
	*/
	public MessagingSystem()
	{ 
		topics= new HashMap <String, Topic>(); //the name of the user is the key and the mailbox is its value
		users= new HashMap <String, User>(); 
		topics.put("Commits", new Topic("Commits"));
		topics.put("Issues", new Topic("Issues"));
		topics.put("Discussion", new Topic("Discussion"));
		topics.put("Tasks", new Topic("Tasks"));
		
	}
	
	/**
	 * adds a user to the users HashMap
	 * @param the name of the user
	 */
	public void addUser(String name)
	{
		person= new User(name);
		users.put(name, person);
	}
	
	/**
	 * retrieves a user object in the HashMap
	 * @param name of the user as the key
	 * @return the user object
	 */
	public User retrieveUser(String name)
	{
		
		return users.get(name);
	}
	
	/**
	 * adds the current user as an observer for a chosen topic
	 * @param name of the topic key, name of the current user who is subscribing
	 */
	public void SubToTopic(String key, String user) 
	{
		Topic top= topics.get(key);
		User currentUser= retrieveUser(user);
		
		if(topics.containsKey(key) && key.equalsIgnoreCase("Commits"))
		{
			top.setSub(currentUser);
			top.addObserver(currentUser);
			System.out.println(currentUser.getName() + " subscribed to Commits");
		}
		else 
			if(topics.containsKey(key) && key.equalsIgnoreCase("Issues"))
			{
				top.setSub(currentUser);
				top.addObserver(currentUser);
				System.out.println(currentUser.getName() + " subscribed to Issues");
			}
			else
				if(topics.containsKey(key) && key.equalsIgnoreCase("Discussion"))
				{
					top.setSub(currentUser);
					top.addObserver(currentUser);
					System.out.println(currentUser.getName() + " subscribed to Discussion");
				}
				else 
					if(topics.containsKey(key) && key.equalsIgnoreCase("Tasks"))
					{
						top.setSub(currentUser);
						top.addObserver(currentUser);
						System.out.println(currentUser.getName() + " subscribed to Tasks");
					}
					else
						System.out.println("Topic not found");
	}
	
	/**
	 * retrieves a Topic object in the HashMap
	 * @param name of the key
	 * @return the user object
	 */
	public Topic retrieveTopic(String topic) //from hashmap
	{
		return topics.get(topic);
	}
	
	   /**
	    * checks to see if a user exists
	    * @param user's name
	    * @return true if the user exists, false otherwise
	    */
	   public boolean doesTheUserExist(String user)
	   {
		      if (!users.get(user).equals(null))
		            return true;
		      return false;
	   }
	   
	   
	   /**
		 * Checks if a topic exists in the hashmap
		 * @return true if the thread exists, false otherwise
		    */
		public boolean doesTopicExist(String topicName)
		{	
			if (topics.get(topicName) != null)//if there is such Topic object in hashmap
			{ 
				return true;
			}
			return false;
		}
	   
	   /**
	      The message will be added to the user's list
	      If the topic object does not exist in the hashmap, false is returned and vice-versa.
	      @param the message
	      @return true if successful, false if there is no mailbox for the user
	   */
	   public boolean delivery(Message msg, String topicName)
	   {  
	      Topic thread = topics.get(topicName); //retrieves topic object
	      if (!(thread.getMessage().toString()).equals(msg.toString()))//compares if the messages are equal
	    	  return false;
	      return true;
	   }
	   
	   public void displayAllMessages(String user) {
		   person= retrieveUser(user);
		   ArrayList<Message> allMessages = person.getAllMessages();
			for(Message m: allMessages)
			{
				System.out.println(m.toString());
			}
			
		}
	
}

