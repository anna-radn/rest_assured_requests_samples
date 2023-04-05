

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UploadingFilesUsingRA {

	String uri="";
	@Test
	public void fileUpload(){
	
	File file=new File("");//provide file path
	
	Response response=RestAssured
			.given()
			.multiPart("file", file, "multipart/form-data")//provide data type, file and type
			.post(uri)
			.thenReturn();
	System.out.println(response.prettyPrint());
	}	
}
