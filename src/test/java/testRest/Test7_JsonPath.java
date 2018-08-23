package testRest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.path.json.JsonPath.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test7_JsonPath {

	/** 
	 *  Extracting details as String 
	 *  and Extracting further using string
	 */
	@Test
	public void testJsonPath(){
		String responseAsPath = 
				when(). 
					get("https://jsonplaceholder.typicode.com/photos").
				then().
					extract().asString();
		List<Integer> albumIds = from(responseAsPath).get("id");
		System.out.println(albumIds);
					
	}
	
	/**
	 * Extracting details as String 
	 *  and Extracting further using JsonPath
	 */
	@Test
	public void testJsonPath2(){
		String responseAsString = 
				when(). 
					get("http://services.groupkt.com/country/get/all").
				then().
					extract().asString();
		JsonPath jsonpath = new JsonPath(responseAsString).setRoot("RestResponse.result");
		
		List<String> list = jsonpath.get("name");
		System.out.println(list.size());
	}
	
}
