package com.testcases;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class PostRequestTest extends TestBase {

	Faker fake = new Faker();

	// POST method by passing body directly
	/*
	 * @Test public void postMethodTest() { String payload =
	 * "{\"firstName\":\"Aravind\",\"lastName\":\"Harper\",\"email\":\"aravind@massaQuisqueporttitor.org\",\"programme\":\"Financial Analysis\","
	 * + "\"courses\":[\"Accounting\",\"Statistics\"]}";
	 * given().when().contentType(ContentType.JSON).when().body(payload).post().then
	 * ().statusCode(201); }
	 */

	@Test
	public void postMethodPojoTest() {
		CreateStudentPojo student = new CreateStudentPojo();
		ArrayList<String> studentList = new ArrayList<String>();
		studentList.add("Java");
		studentList.add("Python");
		student.setFirstName(fake.name().firstName());
		student.setLastName(fake.name().lastName());
		student.setEmail(fake.internet().emailAddress());
		student.setProgramme("Engineering");
		student.setCourses(studentList);

		given().log().all().when().contentType(ContentType.JSON).body(student).when().post().then().log().status()
				.statusCode(201);
		/*
		 * log().all()|| log().ifValidationFails() || log().body() || log().params()
		 * ||log().ifError() || log().status()
		 */

	}

}
