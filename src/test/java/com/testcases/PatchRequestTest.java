package com.testcases;

import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class PatchRequestTest extends TestBase {

	Faker fake = new Faker();

	@Test
	public void patchMethodPojoTest() {
		CreateStudentPojo student = new CreateStudentPojo();
		student.setEmail(fake.internet().emailAddress());
		Response getResponse = given().when().get("/75");
		System.out.println("BEFORE UPDATE:");
		getResponse.prettyPrint();
		given().when().contentType(ContentType.JSON).body(student).when().patch("/75").statusCode();
		Response updatedResponse = given().when().get("/75");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("AFTER UPDATE:");
		updatedResponse.prettyPrint();

	}

}
