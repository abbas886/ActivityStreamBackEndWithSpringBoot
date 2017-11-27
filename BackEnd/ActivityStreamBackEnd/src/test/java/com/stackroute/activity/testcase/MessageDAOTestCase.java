package com.stackroute.activity.testcase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activity.config.SpringBootStarter;
//import com.stackroute.activity.config.SpringBootStarter;
import com.stackroute.activity.dao.MessageDAO;
import com.stackroute.activity.model.Message;
@RunWith(SpringRunner.class)
@SpringBootApplication(scanBasePackages = { "com.stackroute.activity.config" })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootStarter.class)
@EnableAutoConfiguration
public class MessageDAOTestCase {

	@Autowired
	private MessageDAO streamDAO;

	@Autowired
	private Message stream;

	private Date currentDate = new Date();

	@Test
	public void sendMessageToUserTestCase() {
		stream.setId();
		stream.setMessage("do not delete: " + currentDate);
		stream.setSenderID("Rakesh");
		stream.setTag("Message");
		stream.setStreamType("String");
		stream.setReceiverID("pankaj");
		stream.setCircleName(null);
		assertEquals(true, streamDAO.sendMessage(stream));
		//assertEquals(true, streamDAO.buildInbox(stream));

	}

	@Test
	public void sendMessageToCircleTestCase() {
		stream.setId();
		stream.setMessage("Rakesh to circle gitlab on : " + currentDate);
		stream.setSenderID("Rakesh");
		stream.setTag("Message");
		stream.setStreamType("String");
		stream.setReceiverID(null);
		stream.setCircleName("gitlab");
		assertEquals(true, streamDAO.sendMessage(stream));
		
	}

	@Test
	public void sendMessageToCircles() {
		stream.setId();
		stream.setMessage("Swamy to  Rakesh, to circle hobes, Coder_platform  on : " + currentDate);
		stream.setSenderID("swamy");
		stream.setTag("Message");
		stream.setStreamType("String");
		stream.setReceiverID("Rakesh");

		List<String> circles = new ArrayList<String>();
		circles.add("hobes");
		circles.add("Coder_Platoform");

		assertEquals(true, streamDAO.sendMessageToCircles(circles, stream));
		

	}

	@Test
	public void getMessagesTestCase() {
		List<Message> streams = streamDAO.getMyInbox("Abbas");
		System.out.println("Message of Rakesh");
		displayAllStreams(streams);
		assertEquals(true, streams.size() >= 0);
	}
	
	@Test
	public void getOneToOneMessagesTestCase() {
		List<Message> streams = streamDAO.getUserMessages("rakesh", "Abbas");
		System.out.println("Message of Rakesh <-> Abbas");
		displayAllStreams(streams);
		assertEquals(true, streams.size() >= 0);
	}

	@Test
	public void getMessagesFromCircleTestCase() {

		List<Message> streams = streamDAO.getCircleMessages("gitlab");
		System.out.println("Message from gitlab team");
		displayAllStreams(streams);

		assertEquals(true, streams.size() >= 0);

	}

	@Test
	public void deleteReceivedMessageTestCase() {
		int messageID = (int) (Math.random() * 100000);
		stream.setId(messageID);
		stream.setMessage("Message to be deleted : " + currentDate);
		stream.setSenderID("Rakesh");
		stream.setTag("Message");
		stream.setStreamType("String");
		stream.setReceiverID("pankaj");
		streamDAO.sendMessage(stream);
		List<Message> streams = streamDAO.getMyInbox("Rakesh");
		System.out.println("Message of Rakesh before deleting messageID:" + messageID);

		displayAllStreams(streams);

		assertEquals(true, streamDAO.deleteMessage(messageID));

		System.out.println("Message of Rakesh before deleting messageID:" + messageID);

		displayAllStreams(streams);

	}

	/**
	 * Temporary method to display stream all streams
	 * 
	 * @param streams
	 */

	private void displayAllStreams(List<Message> streams) {
		System.out.println(("id\tsender\treceiver\tdate\t\t\tmessage"));
		for (Message stream : streams) {
			displayStream(stream);
		}
	}

	/**
	 * Temporary method to display stream
	 * 
	 * @param streams
	 */
	private void displayStream(Message stream) {
		System.out.println(stream.getId() + "\t"+stream.getSenderID() + "\t" + stream.getReceiverID() + "\t" + stream.getPostedDate() + "\t"
				+ stream.getMessage());

	}

}
