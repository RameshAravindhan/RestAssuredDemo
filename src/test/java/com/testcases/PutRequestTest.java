package com.testcases;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class PutRequestTest extends TestBase {

	Faker fake = new Faker();

	@Test
	public void putMethodPojoTest() {
		CreateStudentPojo student = new CreateStudentPojo();
		ArrayList<String> studentList = new ArrayList<String>();
		studentList.add("Java");
		studentList.add("Python");
		student.setFirstName(fake.name().firstName());
		student.setLastName(fake.name().lastName());
		student.setEmail(fake.internet().emailAddress());
		student.setProgramme("Engineering");
		student.setCourses(studentList);
		Response getResponse = given().when().get("/75");
		System.out.println("BEFORE UPDATE--------");
		getResponse.prettyPrint();
		given().when().contentType(ContentType.JSON).body(student).when().put("/75").then().log().status();
		Response updatedResponse = given().when().get("/75");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("AFTER UPDATE--------");
		updatedResponse.prettyPrint();

	}

}
