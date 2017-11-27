package com.stackroute.activity.dao;

import java.util.List;

import com.stackroute.activity.model.Message;



public interface MessageDAO {

	// public boolean sendMessageToCircle(String circleName,Stream stream);

	public boolean sendMessage(Message stream);

	// public boolean sendMessageToUser(String userID,Stream stream);

	public boolean sendMessageToCircles(List<String> circleNames, Message stream);

	public List<Message> getCircleMessages(String circleName);
	public List<Message> getCircleMessages(String circleName, int firstResult, int maxResult);


	public List<Message> getUserMessages(String userID,String loggedInUserID);
	public List<Message> getUserMessages(String userID,String loggedInUserID, int firstResult, int maxResult);

	

	public List<Message> getMyInbox(String userID, int firstResult, int maxResult);

	public boolean deleteMessage(int messageID);
	public List<Message> getMyInbox(String userID);
	

	// public boolean buildInbox(Stream stream);

}
