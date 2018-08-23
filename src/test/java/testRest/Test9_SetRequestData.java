package testRest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.path.json.JsonPath.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test9_SetRequestData {

	/** 
	 *  Test Connect Request
	 */
	@Test
	public void testConnectRequest(){
		when(). 
			request("CONNECT", "https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
	/** 
	 * In get request we can set the query parameter
	 */
	@Test
	public void testQueryParameters(){
		given(). 
			queryParam("A", "A value").
			queryParam("B", "B value"). 
		when(). 
			get( "https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
	/** 
	 *  In Post request we set form parameter
	 */
	@Test
	public void testFormParameters(){
		given(). 
			formParam("A", "A value").
			formParam("B", "B value"). 
		when(). 
			post("https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
	/** 
	 *  To set parameter recommended way
	 *  get : query parameter
	 *  post: form parameter
	 */
	@Test
	public void testSetParameters(){
		given(). 
			param("A", "A value").
			param("B", "B value"). 
		when(). 
			get("https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
	/** 
	 *  To set multiple parameter
	 *  We can pass list, multiple parameters or no parameters
	 */
	@Test
	public void testSetMultipleValueParameters(){
		List<String> list = new ArrayList<String>();
		list.add("one");
		list.add("two");
		given(). 
			param("A", "val1", "val2", "val3").
			param("B").
			param("C", list).
		when(). 
			get("https://api.fonts.com/rest/json/Accounts").
		then().
			statusCode(400);
	}
	
}
