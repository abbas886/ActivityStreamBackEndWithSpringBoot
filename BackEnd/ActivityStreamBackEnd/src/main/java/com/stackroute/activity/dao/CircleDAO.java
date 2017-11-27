package com.stackroute.activity.dao;

import java.util.List;

import com.stackroute.activity.model.Circle;
import com.stackroute.activity.model.UserCircle;

public interface CircleDAO {
	
	public boolean save(Circle circle);
	
	public boolean addUser(String userID, String circleID);
	
	public boolean removeUser(String userID, String circleID);
	
	public List<Circle> getAllCircles();
	
	
	
	public List<String> getMyCircles(String userID);
	
	public List<Circle> getAllCircles(String searchString);
	
	public List<UserCircle> getCircleUsers(String circleID);
	
	public Circle getCircleByID(String circleID);
	public Circle getCircleByName(String circleName);
	
	public UserCircle get(String userID, String circleID);
	
	public boolean update(Circle circle);

}
