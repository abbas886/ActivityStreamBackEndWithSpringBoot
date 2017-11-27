package com.stackroute.activity.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.activity.dao.MessageDAO;
import com.stackroute.activity.model.Message;


@Repository("streamDAO")
@Transactional
public class MessageDAOImpl implements MessageDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public MessageDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<Message> getMessages(List<String> circleNames) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean sendMessageToCircle(String circleName, Message message) {
		try {
			message.setPostedDate();
			getCurrentSession().save(message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean sendMessage(Message message) {
		boolean flag = true;
		try {
			message.setPostedDate();
			message.setId();
			if (message.getStreamType()==null ||message.getStreamType().isEmpty()) {
				message.setStreamType("String");
			}
			getCurrentSession().save(message);
		//	flag = buildInbox(message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return flag;
	}

	private boolean buildInbox(Message message) {

		try {
			message.getId();
			String query = "insert into message values (" + message.getId() + ", null, " + message.getSenderID()
					+ ", sysdate()," + message.getMessage() + ", \'Message', \'inserted\', \'pankaj\',null,null)";

			getCurrentSession().createQuery("").executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/*
	 * public boolean sendMessageToUser(String receiverID, Message message) { try {
	 * message.setReceiverID(receiverID); getCurrentSession().save(message); } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
	 * return false; } return true; }
	 */

	public boolean sendMessageToCircles(List<String> circleNames, Message message) {
		try {
			for (String circleName : circleNames) {
				sendMessageToCircle(circleName, message);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * select * from message where receiver_id='Abbas' or sender_id='Abbas' or
	 * circle_id in (select circle_id from user_circle where user_id='Abbas')
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getMyInbox(String userID) {
		List<Message> streams = getMyInbox(userID, 1, 10);

		return streams;
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMyInbox(String userID, int firstResult, int maxResult) {
		List<Message> streams = getCurrentSession()
				.createQuery("from Message where receiverID=? or senderID=?"
						+ " or circleName in (select circleName from UserCircle where userID=?)")
				.setString(0, userID).setString(0, userID).setString(1, userID).setString(2, userID)
				.setFirstResult(firstResult).setMaxResults(maxResult).list();

		return streams;
	}

	/**
	 * select * from Message where ID in (select stream_ID from Stream_Circle where
	 * circle_ID='hobes')
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getCircleMessages(String circleName) {
		return getCircleMessages(circleName, 1, 6);

	}

	@SuppressWarnings("unchecked")
	public List<Message> getCircleMessages(String circleName, int firstResult, int maxResult) {

		return (List<Message>) getCurrentSession().createCriteria(Message.class)
				.add(Restrictions.eq("circleName", circleName))
				.setFirstResult(firstResult)
				.setMaxResults(maxResult)
				.addOrder(Order.asc("postedDate"))
				.list();

	}

	@SuppressWarnings("unchecked")
	public List<Message> getUserMessages(String userID, String loggedInUserID) {
		return getUserMessages(userID, loggedInUserID, 1, 6);
	}

	@SuppressWarnings("unchecked")
	public List<Message> getUserMessages(String userID, String loggedInUserID, int firstResult, int maxResult) {
		return getCurrentSession().createQuery(
				"from Message where receiverID=? and senderID=? or receiverID=? and senderID=? order by postedDate asc")
				.setString(0, loggedInUserID).setString(1, userID).setString(2, userID).setString(3, loggedInUserID)
				.setFirstResult(firstResult).setMaxResults(maxResult).list();

	}

	private Message getMessage(int messageID) {
		return getCurrentSession().get(Message.class, messageID);
	}

	@Override
	public boolean deleteMessage(int messageID) {
		try {
			Message message = getMessage(messageID);
			if (message == null) {
				return false;
			}
			getCurrentSession().delete(message);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
