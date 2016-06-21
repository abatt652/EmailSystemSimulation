package cus1156.proj3;

import java.util.Scanner;

/**
 * @author Aidan Battad
   This class creates a user interface for the publish-subscribe simulation
*/

public class MenuInterface
{
	private Scanner scan;
	private MessagingSystem system = null;
	private String name=null; //user logged in
	private String topic=null;
	
	public MenuInterface()
	{
		scan=new Scanner(System.in);
	}
	/**
	 * runs the menu display by passing the messaging system as a parameter 
	 * and allows user interaction for that menu
	 * @param MessagingSystem object
	 */
	public void run(MessagingSystem System)
	{
		menuDisplay(System);
	}
	
	/**
	 * prints the menu and allows user interaction for that menu
	 * @param MessagingSystem object
	 */
	public void menuDisplay(MessagingSystem ms) 
	{
		system=ms;
		boolean quit=false;//if Q is inputted, boolean quit becomes true and ends the do while loop/simulation
		System.out.println("Welcome to the Email Server!");
		
		do{	//loops the menu for multiple inputs
		System.out.println("Please enter a command");
		System.out.println("I: login in");
		System.out.println("P: publish a message to a topic");
		System.out.println("S: subscribe to a topic");
		System.out.println("D: display all messages received by the current user");
		System.out.println("O: log out");
		System.out.println("Q: quit");
		System.out.println(">>>>>>>>>>");
		String input=scan.next();
		
		switch(input)
		{
			case "I": //login
			{
				if (name!=null) //tests if someone is logged on
				{
					System.out.println("You are still logged on\nYou have been automatically logged off\n");
					name=null;
				}
				login();
				break;
			}
			case "D"://Display all messages by recipient or latest message first
			{
				displayMessages();
				break;
			}
			case "O": //logout
			{
				System.out.println(name +" logged out.");
				name=null;
				break;
			}
			
			case "P": //Publish
			{
				publish();
				break;
			}
			case "S": //Subscribe
			{
				subscribe();
				break;
			}
			case "Q": //quit
			{
				System.out.println("Bye!");
				quit=true;
				break;
			}
			default: //input error
				System.out.println("Error: Incorrect Input!");	
		}
		System.out.println();
	   }while(quit==false); //ends menuDisplay method if quit = true
	}
	

	/**
	 * displays all messages from a topic that a user is subscribed to
	 */
	private void displayMessages() 
	{
		if (name==null) //tests if someone is not logged in
		{
			System.out.println("You must log in first to view all your messages");
		}
		else
			if((system.retrieveUser(name))!=null && (system.retrieveUser(name).doesUserHaveMessages()==true))
			{
				System.out.println("The current list of messages in this topic are: ");
				system.displayAllMessages(name);
			}
			else
				if(system.retrieveUser(name).doesUserHaveMessages()==false)
					System.out.println("Messages cannot be displayed because there is no messages in the User's account");
				if(system.retrieveUser(name)==null)
					System.out.println("Messages cannot be displayed because there is no such user in the system");
	}

	/**
	 * checks to see if the user has created a User object to login with, if not, it will create one
	 */
	private void login() { //login
		System.out.print("Username: ");
		name=scan.next();
		if (system.retrieveUser(name)== null)
		{
			system.addUser(name);
			System.out.println("New user added");
		}
		System.out.println("User logged in");		
	}

	
	/**
	 * creates a Message object that contains the user's name and the formatted post
	 * @param MessagingSystem object
	 */
	private Message createMessage() //create message
	{
		Message msg=null;
		topic=null;
		if (name==null) //tests if someone is not logged in
		{
			System.out.println("You must log in first to create messages");
		}
		else//if user is logged in
		{
			System.out.print("Post to topic: ");
			topic=scan.next();
			if(system.doesTopicExist(topic)==false)//searches if the recipient has a mailbox in messaging system
			{
				System.out.println("No such topic in the System.");
				topic=null;
			}
			else//if there is such topic 
			{
				System.out.println("Create a post, and end with a single Q");
				String message = "";
				String text = "";
					
				while (!text.equalsIgnoreCase("Q"))
				{
					text = scan.nextLine();
					message = message + "\n" + text;
				}
				message = message.substring(0, message.length()-1); //removes Q in message
				msg= new Message(name, message, topic);//creates a Message object out of the message string
				System.out.println("Post published");
				system.retrieveTopic(topic).addMessageToTopic(msg);//adds current message to the current topic's list
				system.retrieveTopic(topic).notifyUsers();//notifies all observers of the topic on the added message
				return msg;
			}
		}
		return msg;
	}
	
	/**
	 * calls the createMessage method to create a Message object that contains
	 *  the user's name and message to post. It then uses the deliver() method to send the message to the
	 *  corresponding Topic. If delivered, boolean delivered will be true, else it would be false
	 *
	 */
	private void publish() {//publish message to topic
		if (name==null) //tests if someone is not logged in
		{
			if(system.retrieveTopic(topic)==null)
			{
				System.out.println("You must login and subscribe to topics first in order to publish a message to a topic");
			}
			else
				System.out.println("You must log in first to publish a message to a topic");
		}
		else
		{  
		   Message message = createMessage(); //uses the createMessage method above to initialize a constructed Message object
		   if (message != null)
		   {
			   boolean delivered  = system.delivery(message, topic);
			   if (delivered== true)
			   {
				   System.out.println("Message posted");
			   }
			   else
				   System.out.println("Message not posted");
		   }
		   else
			   System.out.println("Message was not posted. Please create a message.");
		}
	} 
	
	/**
	 * A Message object is initialized with the readNextMessage()method which will return the last Message object
	 * retrieved from the user's Mailbox. If the Message object assigned null, then the method will print "No unread
	 * messages". Else, the Message object is printed in a formatted way.
	 *
	 */
	private void subscribe() {//subscribe to topic
		if (name==null) //tests if someone is logged in
		{
			System.out.println("You must log in first to subscribe to a topic");
		}
		else
		{
			String SubTopic=null;
			Topic top;
			do{
				System.out.print("Enter the Topic you want to subcribe to: ");
				System.out.println("Commits, Issues, Discussion, or Tasks");
				SubTopic=scan.next();
				if(system.doesTopicExist(SubTopic)==false)
				{
					System.out.print("Not a valid topic. Try again");
					System.out.println("\n");
				}
				
				top= system.retrieveTopic(SubTopic);
				if (top!= null) //the topic object retrieved is not null
				{
					system.SubToTopic(SubTopic, name); 
				}
			}while(system.doesTopicExist(SubTopic)==false && top==null);
		}
		
	}
}


