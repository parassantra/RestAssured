package testRest;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TestPostRequest {

	/** 
	 * 
	 */
	@Test
	public void testPostReq(){
		given().
			headers("AppKey","Key-value").
			param("wfsfirst_name","first").
			param("wfslast_name","last").
			param("wfsemail","test@test.com").
			
		when().
			get("http://api.fonts.com/rest/json/Accounts/").
		then(). 
			statusCode(401).log().all();
	}
	
	
}
