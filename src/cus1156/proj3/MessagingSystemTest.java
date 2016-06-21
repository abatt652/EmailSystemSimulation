package cus1156.proj3;
/**
 * @author Aidan Battad
 * A JUnit class that tests the MessagingSystem class
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MessagingSystemTest {
	private MessagingSystem system;
	String name="Aidan";
	@Before
	public void setUp() throws Exception {
		system=new MessagingSystem();
		system.addUser(name);
	}

	@Test
	public void testAddUser() {
		system.addUser("Bob");
    	assertTrue(system.doesTheUserExist("Bob"));
	}
	
	@Test
	public void testRetrieveUser() {
    	assertEquals(system.retrieveUser("Aidan").getName(), "Aidan");
	}
	
	@Test
	public void testSubToTopic()
	{
		system.addUser("Tom");
		system.addUser("Ben");
		Topic t=system.retrieveTopic("Commits");
		system.SubToTopic("Commits", "Tom");
		system.SubToTopic("Commits", "Ben");
		int observers=t.countObservers();
		assertEquals(observers, 2);
	}
	
	@Test
	public void testRetrieveTopic() {
	    	assertEquals(system.retrieveTopic("Commits").getTopicName(),"Commits");
		}
	
	@Test
	public void testDoesTheUserExistx() {
		system.addUser("Aidan");
    	assertTrue(system.doesTheUserExist("Aidan"));
	}
	
	@Test
	public void testDoesTheTopicExist(){
    	assertTrue(system.doesTopicExist("Tasks"));
	}
	
	@Test
	public void testDelivery() {
		Message msg= new Message("Bill","Discussion", "Do you understand the question?");
		Topic t= system.retrieveTopic("Discussion");
		t.addMessageToTopic(msg);
    	assertTrue(system.delivery(msg,"Discussion"));
	}

}
