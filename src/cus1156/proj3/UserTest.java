package cus1156.proj3;

/**
 * @author Aidan Battad
 * A JUnit test class for User class
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class UserTest{
	private User user;

	@Before
	public void setUp() throws Exception {
		user= new User("Aidan");
	}

	@Test
	public void testGetTopic() {
		assertEquals(user.getTopic(), null);
	}

	@Test
	public void testGetName() {
		assertEquals(user.getName(),"Aidan");
	}
	
	@Test
	public void testAddMessageToUser() {
		user.addMessageToUser(new Message("Bob", "Appointment", "Task"));
		assertTrue(user.doesUserHaveMessages());
	}

	@Test
	public void testUpdate() {
		MessagingSystem sys=new MessagingSystem();
		sys.addUser("Aidan");
		sys.SubToTopic("Discussion", "Aidan");
		Message M= new Message("Aidan","Changed State of Topic","Discussion");
		sys.retrieveTopic("Discussion").addMessageToTopic(M);
		sys.retrieveTopic("Discussion").notifyUsers();
		user.update(sys.retrieveTopic("Discussion"), M);
		assertEquals(user.getAllMessages().size(),1);
	}
	
	@Test
	public void testGetAllMessages()
	{
		user.addMessageToUser(new Message("Bob", "Appointment", "Task"));
		user.addMessageToUser(new Message("Anna", "Project", "Discussion"));
		user.addMessageToUser(new Message("Tim", "Errors", "Issues"));
		assertEquals(user.getAllMessages().size(), 3);
	}

	@Test
	public void testDoesUserHaveMessages() {
		Message m= new Message("Sam", "Hi", "Tasks");
		user.addMessageToUser(m);
		assertTrue(user.doesUserHaveMessages());
	}

}
