package testRest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;



public class Test10_VerifyResponse {

	/** 
	 *  status code verification
	 */
	@Test
	public void testStatusInResponse(){
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusCode(200).log().all();
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusLine("HTTP/1.1 200 OK");
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusLine(containsString("OK"));
	}
	
	/** 
	 *  headers verification
	 */
	@Test
	public void testHeadersInResponse(){
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().header("X-Powered-By", "Express");
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().headers("Vary","Origin, Accept-Encoding","Content-Type",containsString("json"));
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().statusLine(containsString("OK"));
	}
	
	/** 
	 *  ContetType Verification
	 */
	@Test
	public void testContentType() {
		given().get("https://jsonplaceholder.typicode.com/photos").then().assertThat().contentType(ContentType.JSON);
	}
	
	/** 
	 *  Test Response Body
	 */
	@Test
	public void testBodyInResponse() {
		String ResponseBody = get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02").asString();
		given().get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02").then().assertThat().body(equalTo(ResponseBody));
	}
	
	/** 
	 *  Test Response Cookie
	 */
	@Test
	public void testCookieInResponse() {
		//given().get("http://jsonplaceholder.typicode.com/comments").then().log().all().assertThat().cookie("__cfduid","d4e63e3b8874a3328cd3f65833a5832a81535217771");
		given().get("http://jsonplaceholder.typicode.com/comments").then().log().all().assertThat().cookie("__cfduid",startsWith("d"));
	}
	
	/** 
	 *  Test Response Cookie
	 */
	@Test
	public void testBodyParametersInResponse() {
		given()
			.get("http://jsonplaceholder.typicode.com/photos/1"). 
		then(). 
			body("thumbnailUrl", new ResponseAwareMatcher<Response>() {
				
				@Override
				public Matcher<?> matcher(Response response) throws Exception {
					// TODO Auto-generated method stub
					return equalTo("https://via.placeholder.com/150/92c952");
				}
			});
		given().get("http://jsonplaceholder.typicode.com/photos/1").then().body("thumbnailUrl", response -> equalTo("https://via.placeholder.com/150/92c952"));
		given().get("http://jsonplaceholder.typicode.com/photos/1").then().body("thumbnailUrl", endsWith("92c952"));
	}	
}
