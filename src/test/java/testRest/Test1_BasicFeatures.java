package testRest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test1_BasicFeatures {
	
	/** Simply checking 
	 * the status code*/
	@Test
	public void testStatusCode(){
		given().
			get("http://jsonplaceholder.typicode.com/posts/3").
		then().
			statusCode(200);
	}
	
	/**It will verify the code and
	 *  print complete response in console*/
	@Test
	public void testLogging(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/IN").
		then(). 
			statusCode(200).
			log().all();
			
	}
	
	/**verify single content using 
	 * org.hamcrest.Matchers library's 
	 * equalTo method*/
	@Test
	public void testEqualToFunction(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/US").
		then(). 
			body("RestResponse.result.name", equalTo("United States of America"));
	}
	
	/**verify multiple content using 
	 * org.hamcrest.Matchers 
	 * library's */
	@Test
	public void testHasItemFunction(){
		given().
			get("http://services.groupkt.com/country/get/all").
		then(). 
			body("RestResponse.result.name", hasItems("Afghanistan", "Argentina", "Australia"));
	}
	
	/**parameters and headers 
	 * can be set */
	@Test
	public void testParametersAndHeaders(){
		given().
			param("key1","value1").
			header("headA", "valueA").
		when().
			get("http://services.groupkt.com/country/get/iso2code/GB").
		then(). 
			statusCode(200).
			log().all();
	}
	
	/**feature for readability */
	@Test
	public void testFeatureForReadability(){
		given().param("key1","value1").and().header("headA", "valueA").when().get("http://services.groupkt.com/country/get/iso2code/CN").then().statusCode(200);
	}
	
	
}
