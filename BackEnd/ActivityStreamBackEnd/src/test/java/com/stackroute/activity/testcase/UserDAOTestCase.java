package com.stackroute.activity.testcase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activity.config.SpringBootStarter;
import com.stackroute.activity.dao.UserDAO;
import com.stackroute.activity.model.User;


@RunWith(SpringRunner.class)
@SpringBootApplication(scanBasePackages={"com.stackroute.activity.config"})
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=SpringBootStarter.class)
@EnableAutoConfiguration

public class UserDAOTestCase {

	
	@Autowired
	private  UserDAO userDAO;

	@Autowired
	private  User user;

	
/**
 * will through DataIntegrityViolationException
 * */
	@Test(expected = DataIntegrityViolationException.class)
	public void createUserFailedTestCase() {
		user.setId("Rakesh");
		user.setName("Rakesh");
		user.setPassword("Rakesh");

		userDAO.save(user);

	}

	@Test
	public void updateUserTestCase() {
		user.setId("Dinesh");
		user.setName("Dinesh");
		user.setPassword("Dinesh@123");
		boolean flag = userDAO.update(user);

		// error - if there is in runtime errors - Red mark
		// success - if expected and actual is same - green mark
		// fail - if expected and actual is different - blue mark
		assertEquals(" update user test case", true, flag);

	}

	@Test
	public void validateUSerTestCase() {

		user = userDAO.validate("Dinesh", "Dinesh@123");

		assertNotNull(user);

	}

	@Test
	public void getAllUserTestCase() {
		int actualSize = userDAO.list().size();

		// will compare actual and expected
		// if actual and expected is same - TC will pass
		// if it is different - TC fail
		assertEquals(true, actualSize>=0);
	}

}
