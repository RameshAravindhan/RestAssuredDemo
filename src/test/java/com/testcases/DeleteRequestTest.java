package com.testcases;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

public class DeleteRequestTest extends TestBase {
	Faker fake = new Faker();

	@Test
	public void deleteMethodTest() {
		String s = Integer.toString(fake.random().nextInt(5, 90));
		System.out.println(s);
		given().when().get("/" + s).prettyPrint();
		given().when().contentType(ContentType.JSON).delete("/" + s);
		given().when().get("/" + s).prettyPrint();
	}

}
