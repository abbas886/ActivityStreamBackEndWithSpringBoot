package com.stackroute.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name ="user_circle")
public class UserCircle {
	
	@Id 
	private int id;
	
	@Column(name="user_id")
	private String userID;
	
	@Column(name="circle_id")
	private String circleID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCircleID() {
		return circleID;
	}

	public void setCircleID(String circleID) {
		this.circleID = circleID;
	}

	
}
