package testRest;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TestSetting_Root {

	/** Basic way to test
	 *  all the parameters
	 */
	@Test
	public void testWithoutRoot(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/GB").
		then(). 
			body("RestResponse.result.name", equalTo("United Kingdom of Great Britain and Northern Ireland")). 
			body("RestResponse.result.alpha2_code", equalTo("GB")).
			body("RestResponse.result.alpha3_code", equalTo("GBR"));
	}
	
	/**Recommended way to test all 
	 * feature using root feature
	 */
	@Test
	public void testWithRoot(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/GB").
		then(). 
			root("RestResponse.result"). 
			body("name", is("United Kingdom of Great Britain and Northern Ireland")). 
			body("alpha2_code", is("GB")).
			body("alpha3_code", is("GBR"));
	}
	
	/**
	 * We can deatch Root
	 */
	@Test
	public void testDetachRoot(){
		given().
			get("http://services.groupkt.com/country/get/iso2code/GB").
		then(). 
			root("RestResponse.result"). 
			body("name", is("United Kingdom of Great Britain and Northern Ireland")). 
			body("alpha2_code", is("GB")).
			detachRoot("result").
			body("result.alpha3_code", is("GBR"));
	}
}
