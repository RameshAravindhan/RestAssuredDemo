package com.testcases;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class GetRequestTest extends TestBase {
	static RequestSpecBuilder builder;
	static RequestSpecification rspec;

	static ResponseSpecBuilder builder2;
	static ResponseSpecification rspec2;

	@BeforeAll
	public static void builder() {
		builder = new RequestSpecBuilder();
		builder.addParam("programme", "Computer Science");
		builder.addParam("limit", "2");
		rspec = builder.build();

		builder2 = new ResponseSpecBuilder();
		builder2.expectStatusCode(200);
		rspec2 = builder2.build();

	}

	@DisplayName("Get All Students list")
	@Test
	public void validateGet() {
		given().when().get("http://localhost:8080/student/list").then().statusCode(200);
	}

	@DisplayName("Print Students with Params")
	@Test
	public void validateParams() {
		Response response = given().queryParams("programme", "Computer Science", "limit", "2").when().get("/list");// .then().statusCode(200);
		response.prettyPrint();

	}

	/*
	 * This method is also allowed RestAssured.baseURI =
	 * "https://restapi.demoqa.com/utilities/weather/city"; RequestSpecification
	 * httpRequest = RestAssured.given(); Response response =
	 * httpRequest.get("/Hyderabad");
	 */

	@DisplayName("Print Students with Build Params")
	@Test
	public void validateParamsUsingBuilder() {

		// ResponseSpec can also be used to assert using expectHeader and expectBody
		System.out.println("validateParamsUsingBuilder");
		System.out.println("----------------------------------------------------------------------------------");
		given().spec(rspec).log().all().when().get("/list").then().spec(rspec2).log().ifStatusCodeIsEqualTo(200).log()
				.all();// .then().statusCode(200);

	}

	@DisplayName("Params using Maps")
	@Test
	public void validateMapParams() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("programme", "Computer Science");
		map.put("limit", "2");
		given().queryParams(map).when().get("http://localhost:8080/student/list").then().statusCode(200);
	}

	@DisplayName("Path Params Test")
	@Test
	public void validatePathParams() {
		Response response = given().pathParam("id", 75).when().get("http://localhost:8080/student/{id}");
		response.prettyPrint();
	}
}
