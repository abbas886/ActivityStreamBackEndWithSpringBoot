package com.stackroute.activity.model;

import java.sql.Timestamp;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Stream")
public class Message {
	@Id 
	private int id;
	
	@Transient
	private int firstResult;
	@Transient
	private int fetchSize;
	
	@Transient
	private int maxSize;
	
	@Column(name="parent_id")
	private String parentID;
	
	@Column(name="sender_id")
	private String senderID;
	
	
	@Column(name="receiver_id")
	private String receiverID;
	
	@Column(name="circle_id")
	private String circleName;
	
	@Column(name="posted_date")
	private Timestamp postedDate;
	
	@Column(name="stream_type")
	private String streamType;
	
    private String tag;
	
	private String message;

	public int getId() {
		return id;
	}

	public void setId() {
		this.id = new Random().nextInt();
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getFetchSize() {
		return fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getSenderID() {
		return senderID;
	}

	public void setSenderID(String senderID) {
		this.senderID = senderID;
	}

	public String getReceiverID() {
		return receiverID;
	}

	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public Timestamp getPostedDate() {
		return postedDate;
	}

	public void setPostedDate() {
		this.postedDate = new Timestamp(System.currentTimeMillis());
	}

	public String getStreamType() {
		return streamType;
	}

	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
