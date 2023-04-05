

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestWithTimeout {

	
	RequestSpecification request;
	
	@BeforeMethod          //have to setup timeout params before the request
	public void setUp() {
		
		HttpClientConfig httpClient= HttpClientConfig.httpClientConfig()
				.setParam("http.socket.timeout", 4000)
				.setParam("http.connection.timeout", 4000);
		RestAssured.config= RestAssuredConfig.config().httpClient(httpClient);
		
		RestAssured.baseURI="";
		RestAssured.basePath="";
		Header header= new Header("Accept","application/json");
		request=RestAssured.given().auth().basic("", "").header(header).queryParam("delay", 3000);
		
	}
	@Test
	public void getResponse() {
		Response response=request.when().get().andReturn();
		System.out.println(response.asString());
		
	}
	
}
