

import io.restassured.RestAssured;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

public class JsonSchemaValidation {

	String uri = "";

	@Test
	public void jsonSchemaValidation() {

		// 1.get inferred json schema
		// 2.add schema file to classpath
		// 3.have dependencies
		// 4.validate. Must do static import to use matchesJsonSchemaInClasspath method

		RestAssured.given().when().get(uri).then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"))
				.statusCode(200);

	}
}
