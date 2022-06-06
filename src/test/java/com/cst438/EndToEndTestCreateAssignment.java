package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cst438.domain.Assignment;
import com.cst438.domain.AssignmentGrade;
import com.cst438.domain.AssignmentGradeRepository;
import com.cst438.domain.AssignmentRepository;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

/*
 * This example shows how to use selenium testing using the web driver 
 * with Chrome browser.
 * 
 *  - Buttons, input, and anchor elements are located using XPATH expression.
 *  - onClick( ) method is used with buttons and anchor tags.
 *  - Input fields are located and sendKeys( ) method is used to enter test data.
 *  - Spring Boot JPA is used to initialize, verify and reset the database before
 *      and after testing.
 *      
 *  In SpringBootTest environment, the test program may use Spring repositories to 
 *  setup the database for the test and to verify the result.
 */

@SpringBootTest
public class EndToEndTestCreateAssignment {

	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/Users/LocalUser/Downloads/chromedriver_win32/chromedriver.exe";

	public static final String URL = "https://cst438-gradefe-wooten.herokuapp.com/";
	public static final String TEST_USER_EMAIL = "test@csumb.edu";
	public static final String TEST_INSTRUCTOR_EMAIL = "dwisneski@csumb.edu";
	public static final int SLEEP_DURATION = 1000; // 1 second.
	public static final String TEST_ASSIGNMENT_NAME = "Test Assignment";
	public static final String TEST_COURSE_TITLE = "Test Course";
	public static final String TEST_STUDENT_NAME = "Test";
	public static final int TEST_COURSE_ID = 99999;

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	AssignmentGradeRepository assignnmentGradeRepository;

	@Autowired
	AssignmentRepository assignmentRepository;

	@Test
	public void addAssignmentTest() throws Exception {

		AssignmentGrade ag = null;
	
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get(URL);
		Thread.sleep(SLEEP_DURATION);
		

		try {
			
	         WebElement we = driver.findElement(By.id("addAssignment"));
	         we.findElement(By.id("assignmentName")).sendKeys("Test Assignment");
	         we.findElement(By.id("dueDate")).sendKeys("2021-09-01");
	         we.findElement(By.id("courseId")).sendKeys("123456");
	         
	         Thread.sleep(SLEEP_DURATION);
	         
	         driver.findElement(By.xpath("//*[@id=\"btnAdd\"]")).click();
	         Thread.sleep(SLEEP_DURATION);
	         
	         /*
	          * 
	          *   verify that assignment show up in updated data grid table
	          */
	         
	         we = driver.findElement(By.xpath("//div[@data-value='Test Assigment']"));
	         assertEquals("Test Assigment", we.getAttribute("data-value"));
	  	         
		} catch (Exception ex) {
			throw ex;
		} finally {

			/*
			 *  clean up database so the test is repeatable.
			 */

			driver.quit();
		}

	}
}