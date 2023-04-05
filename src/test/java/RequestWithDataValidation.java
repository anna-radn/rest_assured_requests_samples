import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RequestWithDataValidation {

	String baseUri = "https://something.com";

	@Test
	public void readAllProducts() {

		/*
		 * given all input details(baseURI, Headers, Authorization, Payload/Body, Query
		 * parameters) when: submit api requests(HTTP method, EndPoint/Resource) then:
		 * validate response(status code, Headers, responseTime, Payload/Body)
		 */

		Response response = RestAssured.

				given().baseUri(baseUri).header("Content-Type", "application/json; charset=UTF-8").auth().preemptive()
				.basic("username", "password").when().get("/read.php").then().extract().response();

		// validate status code
		int statusCode = response.getStatusCode();
		System.out.println("Status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

		// validate response time
		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("response time: " + responseTime);
		if (responseTime <= 2000) {
			System.out.println("Response time is within range");
		} else {
			System.out.println("Response time out of range");
		}

		// validate header
		String responseHeader = response.getHeader("Content-Type");
		System.out.println("Response header is: " + responseHeader);
		Assert.assertEquals(responseHeader, "application/json; charset=UTF-8");

		// print response
		String responseBody = response.getBody().asString();// or use .prettyPrint()
		System.out.println("Response Body is:" + responseBody);

		JsonPath jsonPath = new JsonPath(responseBody);

		// getting id from json file
		String productId = jsonPath.getString("id");
		System.out.println("Product id is: " + productId);

		// validate records from json data
		String firstProductId = jsonPath.getString("records[0].id");// getting first record which is id
		System.out.println("First product id is: " + firstProductId);
		if (firstProductId != null) {
			System.out.println("Records are not null");
		} else {
			System.out.println("Records are null");
		}
	}

}
