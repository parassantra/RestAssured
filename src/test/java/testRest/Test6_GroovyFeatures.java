package testRest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.path.json.JsonPath.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Test6_GroovyFeatures {

	/** 
	 *  Verify same expected name present in response or not
	 */
	@Test
	public void testPreseneceOfElements(){
		given().
			get("http://services.groupkt.com/country/search?text=lands").
		then(). 
			body("RestResponse.result.name", hasItems("Cayman Islands", "Cook Islands")).log().all();
	}
	
	/**
	 * RestAssured is implemented in groovy 
	 * and hence groovy advantages can be taken
	 * Here we are adding length of all the alpha3_code code coming in response
	 */
	@Test
	public void testLengthOfResponse(){
		given().
			get("http://services.groupkt.com/country/search?text=lands").
		then(). 
			statusCode(200).
			body("RestResponse.result.alpha3_code*.length().sum()", greaterThan(40));
	}
	
	/**
	 * To get All Attributes as list
	 */
	@Test
	public void testGetResponseAsList(){
		String response =	get("http://services.groupkt.com/country/search?text=lands").asString();
		
		List<String> ls = 
				from(response).getList("RestResponse.result.name");
		System.out.println("Size of List: "+ ls.size());
		
		for(String country: ls){
			if(country.equals("Solomon Islands"))
				System.out.println("Found My Place");
		}
		
	}

	/**
	 * To get response as a list and Apply some conditions to it
	 * Groovy has  a implicit  variable called it which represents the current item in the list
	 */
	@Test
	public void testConditionOnList(){
		String response =	get("http://services.groupkt.com/country/search?text=lands").asString();
		
		List<String> ls = 
				from(response).getList("RestResponse.result.findAll {it.name.length() > 40}.name");
		System.out.println(ls);
	}

	
	
}
