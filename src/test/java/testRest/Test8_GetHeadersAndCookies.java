package testRest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.path.json.JsonPath.*;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test8_GetHeadersAndCookies {

	/** 
	 *  to get Headers
	 */
	@Test
	public void testResponseHeaders(){
		Response response = get("https://jsonplaceholder.typicode.com/photos");
		String headerCFRAY = response.getHeader("CF-RAY");
		System.out.println("Header>>>>> "+headerCFRAY);
		System.out.println();
		Headers headers = response.getHeaders();
		for(Header h : headers){
			System.out.println(h.getName()+":"+h.getValue());
		}				
	}
	
	/** 
	 *  to get Cookies
	 */
	@Test
	public void testCookies(){
		Response response = get("https://jsonplaceholder.typicode.com/photos");
		Map<String, String> cookies = response.getCookies();
		
		for(Map.Entry<String, String> entry : cookies.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	
	/** 
	 *  to detailed Cookies
	 */
	@Test
	public void testDetailedCookies(){
		Response response = get("https://jsonplaceholder.typicode.com/photos");
		Cookie cookie = response.getDetailedCookie("__cfduid");
		System.out.println("Deatiled cookie: "+cookie.hasExpiryDate());
		System.out.println("Deatiled cookie: "+cookie.getExpiryDate());
		System.out.println("Deatiled cookie: "+cookie.hasValue());
	}
	
	
}
