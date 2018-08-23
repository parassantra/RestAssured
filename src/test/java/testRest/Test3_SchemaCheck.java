package testRest;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Test3_SchemaCheck {

	/** 
	 *  Verify response Type
	 */
	@Test
	public void testContentType(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/GB").
		then(). 
			statusCode(200). 
			contentType(ContentType.JSON);
	}
	
	/**
	 * 
	 */
	@Test
	public void testSchema(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/GB").
		then(). 
			statusCode(200).
			assertThat().body(matchesJsonSchemaInClasspath("test1.json"));
	}
	
	
}
