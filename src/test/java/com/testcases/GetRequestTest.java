package com.testcases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class GetRequestTest extends TestBase {
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
