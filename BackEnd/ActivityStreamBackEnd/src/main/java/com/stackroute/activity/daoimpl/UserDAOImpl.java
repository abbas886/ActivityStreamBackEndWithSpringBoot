package com.stackroute.activity.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.activity.dao.UserDAO;
import com.stackroute.activity.model.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO{
	
	
	public UserDAOImpl()
	{
		
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	private Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	public boolean save(User user) {
		try {
			getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(User user) {
		try {
			getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(User user) {
		try {
			getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<User> list() {
		return getCurrentSession().createQuery("from User").list();
	}

	public User validate(String id, String password) {
	return	(User) getCurrentSession().createCriteria(User.class)
			.add(Restrictions.eq("id", id))
			.add(Restrictions.eq("password", password))
			.uniqueResult();
	
	
	}

	public User get(String id) {
		return (User) getCurrentSession().get(User.class, id);
			
	}

}
