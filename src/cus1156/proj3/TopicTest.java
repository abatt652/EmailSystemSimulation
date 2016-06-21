package cus1156.proj3;
/**
 * @author Aidan Battad
 * A JUnit test class for Topic class
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TopicTest {
	private Topic topic;
	private Message m;
	
	@Before
	public void setUp() throws Exception {
		topic= new Topic("Discussion");
		m= new Message("Aidan","Hello","Discussion");
	}

	@Test
	public void testSetSub()
	{
		User user=new User("Aidan");
		topic.setSub(user);
		assertTrue(topic.getSubs().contains(user));
	}
	
	@Test
	public void testGetSubs()
	{
		User user=new User("Tom");
		topic.setSub(user);
		assertTrue(topic.getSubs().contains(user));
	}
	
	@Test
	public void testAddMessageToTopic() {
		topic.addMessageToTopic(m);
		assertTrue(topic.doesTopicHaveMessages());	
	}
	
	@Test
	public void testGetMessage() {
		topic.addMessageToTopic(m);
		assertNotNull(topic.getMessage());
	}
	
	@Test
	public void testNotifyUsers()
	{
		MessagingSystem sys=new MessagingSystem();
		sys.addUser("Aidan");
		sys.SubToTopic("Discussion", "Aidan");
		Message M= new Message("Aidan","Changed State of Topic","Discussion");
		sys.retrieveTopic("Discussion").addMessageToTopic(M);
		sys.retrieveTopic("Discussion").notifyUsers();
		assertEquals(sys.retrieveUser("Aidan").getAllMessages().size(),1);
	}

	@Test
	public void testGetTopicName() {
		assertEquals(topic.getTopicName(),"Discussion");
	}

	@Test
	public void testDoesTopicHaveMessages() {
		assertFalse(topic.doesTopicHaveMessages());
	}

}
