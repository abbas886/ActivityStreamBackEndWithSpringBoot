package com.stackroute.activity.dao;

import java.util.List;

import com.stackroute.activity.model.User;

public interface UserDAO {
	
	public boolean save(User user);
	public boolean update(User user);
	public boolean delete(User user);
	public List<User> list();
	public User validate(String id, String password);
	public User get(String id);
	
	

}
