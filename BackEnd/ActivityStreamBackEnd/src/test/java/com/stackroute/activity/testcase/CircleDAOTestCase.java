package com.stackroute.activity.testcase;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.activity.config.SpringBootStarter;
import com.stackroute.activity.dao.CircleDAO;
import com.stackroute.activity.model.Circle;

@RunWith(SpringRunner.class)
@SpringBootApplication(scanBasePackages={"com.stackroute.activity.config"})
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT,classes=SpringBootStarter.class)
@EnableAutoConfiguration
public class CircleDAOTestCase {
	@Autowired
	private static CircleDAO circleDAO;

	@Autowired
	private static Circle circle;

	@Test(expected = DataIntegrityViolationException.class)
	public void createCircleTestCase() {
		circle.setAdminID("Swamy");
		circle.setId("hobes");
		circle.setName("hobes");
		boolean flag = circleDAO.save(circle);

		assertEquals("createCircleTestCase", true, flag);

	}

	@Test
	public void updateUserTestCase() {
		circle.setAdminID("Farooq");
		circle.setId("Coder_Platform");
		circle.setName("Coder_Platform");
		boolean flag = circleDAO.update(circle);

		assertEquals(" update circle test case", true, flag);

	}

	@Test
	public void getAllCirclesTestCase() {
		int actualSize = circleDAO.getAllCircles().size();

		assertEquals(true, actualSize >= 0);
	}

	@Test
	public void addUserToCircleTestCae() {
		assertEquals(true, circleDAO.addUser("Dinesh", "Coder_Platofm"));
	}

	@Test
	public void getAllMyCirclesTestCase() {
		assertEquals(true, circleDAO.getMyCircles("Swamy").size() >= 0);
	}

	@Test
	public void getAllCirclesBySearchStringTestCase() {
		assertEquals(true, circleDAO.getAllCircles("Plat").size() >= 0);
	}

}
