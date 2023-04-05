import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UsageOfOath2 {

	static String accessToken;
	static String clientId="SOMENUMBERSANDLETTERS";
	static String secret="SOMENUMBERSANDLETTERS";
	
	@Test(priority=1)
	public void useOauth2() {
		//option 1
		Response response= RestAssured
				.given()
				.auth()//use auth() then oauth2
				.oauth2("")//use access token generated in postman
				.get("something.com")
				.andReturn();
		
		//option 2	
		Response resp= RestAssured//first get response
				.given()
				.auth().preemptive()
				.basic(clientId, secret)
				.param("grant_type", "client_credentials")
				.log().all()
				.post("somewhere.com");
		
		System.out.println(resp.prettyPrint());
		accessToken=resp.getBody().path("access_token");// then generate access token 
	}
	@Test(priority=2, dependsOnMethods="useOauth2")
	public void postRequestUsingGeneratedAccessToken() {
		
		String payload="some json format file";
		Response response= RestAssured
				.given()
				.contentType(ContentType.JSON)
				.auth().oauth2(accessToken)// here can use generated token from the first response
				.body(payload)
				.log().all()
				.post("somewhere.com");
		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 201);
		
	}
	
}
