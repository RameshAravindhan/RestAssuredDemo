package com.testcases;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.oracle.webservices.internal.api.databinding.Databinding.Builder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class GetRequestTest extends TestBase {
	static RequestSpecBuilder builder;
	static RequestSpecification rspec;

	@BeforeAll
	public static void builder() {
		builder = new RequestSpecBuilder();
		builder.addParam("programme", "Computer Science");
		builder.addParam("limit", "2");
		rspec = builder.build();

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

	@DisplayName("Print Students with Build Params")
	@Test
	public void validateParamsUsingBuilder() {

		// ResponseSpec can also be used to asset using expectHeader and expectBody

		Response response = given().spec(rspec).when().get("/list");// .then().statusCode(200);
		response.prettyPrint();
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
