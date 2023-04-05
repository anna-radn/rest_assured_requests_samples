

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DownloadingFilesUsingRA {

	String uri;
	@Test
	public void fileDownload() throws IOException {
		
		Response response=RestAssured
				.given()
				.when()
				.get(uri)
				.andReturn();
		
		byte[] bytes=response.getBody().asByteArray();//get response first, then convert to byte[]
		File file=new File("");//file path
		try(FileOutputStream fos=new FileOutputStream(file);){//use try with resources so it will always close it
		fos.write(bytes);
		}
	}
	
}
