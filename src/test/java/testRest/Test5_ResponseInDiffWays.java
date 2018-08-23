package testRest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.io.InputStream;


public class Test5_ResponseInDiffWays {
	
	/** 
	 * To get all response as String
	 */
	@Test
	public void testGetResponseAsString(){
		String responseAsString = get("http://services.groupkt.com/country/search?text=lands").asString();
		System.out.println(responseAsString);
	}
	
	/** 
	 * To get all response as InputStream
	 * @throws IOException 
	 */
	@Test
	public void testGetResponseAsInputStream() throws IOException{
		InputStream stream = get("http://services.groupkt.com/country/search?text=lands").asInputStream();
		System.out.println("Stream length: "+stream.toString().length());
		stream.close();
	}
	
	/** 
	 * Extract details using path
	 */
	@Test
	public void testExtractDetailsUsingPath(){
		String href =
		when(). 
			get("https://jsonplaceholder.typicode.com/photos/1"). 
		then(). 
			contentType(ContentType.JSON).
			body("albumId",equalTo(1)).
		extract().
			path("url");
		System.out.println(href);
		when().get(href).then().statusCode(200);
	}
	
	/** 
	 * Extract details using path
	 */
	@Test
	public void testExtractPathInOneline(){
		//type1:
		String href1 =get("https://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
		System.out.println("Fetch Url1: "+ href1);
		when().get(href1).then().statusCode(200);
		
		//type2:
		String href2 =get("https://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath().getString("thumbnailUrl");
		System.out.println("Fetch Url2: "+ href2);
		when().get(href2).then().statusCode(200);
	}
	

	/** 
	 * Extract details as Response for further use 
	 */
	@Test
	public void testExtractUsingResponse(){
		Response response =
				when(). 
					get("https://jsonplaceholder.typicode.com/photos/1"). 
				then(). 
				extract(). 
					response();
		System.out.println("Content Type: "+response.contentType());
		System.out.println("Href: "+response.path("url"));
		System.out.println("StatusCode: "+response.statusCode());
	}
	
	
	
	
}
