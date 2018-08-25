package testRest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.path.json.JsonPath.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Test9_SetRequestData2 {

	/** 
	 *  to path parameters type 2
	 */
	@Test
	public void testSetPathParameter2(){
		given(). 
			pathParam("type", "json").
			pathParam("section", "Domains").
		when().
			get("http://api.fonts.com/rest/{type}/{section}").
		then().
			statusCode(400);
	}
	
	/** 
	 * Cookies can be set in request parameter
	 */
	@Test
	public void testSetCookiesInRequest(){
		given(). 
			cookie("__utmt","1").
		when(). 
			get( "http://webservicex.com/globalweather.asmx?op=GetCitiesByCountry").
		then().
			statusCode(404).log().all();
	}
	
	/** 
	 * Cookies can be set in request parameter
	 */
	@Test
	public void testSetMultipleCookiesInRequest(){
		given().cookie("key", "value1", "value2");
		
		Cookie cookie = new Cookie.Builder("some_cookie","some_value").setSecured(true).setComment("someComment").build();
		given().cookie(cookie).when().get("/cookie").then().assertThat().body(equalTo("x"));
		Cookie someCookie1 = new Cookie.Builder("some_cookie","some_value").setSecured(true).setComment("someComment").build();
		Cookie someCookie2 = new Cookie.Builder("some_cookie","some_value").setSecured(true).setComment("someComment").build();
		Cookies cookies = new Cookies(someCookie1,someCookie2);
		given().cookies(cookies).when().get("/cookie").then().assertThat().body(equalTo("x")).log().all();	
	}
	
	/** 
	 *  to set Headers
	 */
	@Test
	public void testSetHeaders(){
		given().
			header("k","v"). 
			header("k1","v1","v2","v3").
			headers("k2","v1","k3","v3").
		when().
			get("https://api.fonts.com/rest/json/Accounts/"). 
		then(). 
			statusCode(400);
	}
	

	/** 
	 *  to set Content Type
	 */
	@Test
	public void testSetContentType(){
		given().
			contentType(ContentType.JSON).
			contentType("application/json;charset=utf-8").
		when().
			get("https://api.fonts.com/rest/json/Accounts/"). 
		then(). 
			statusCode(400);
	}
	
	
}
