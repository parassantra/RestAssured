package testRest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;



public class Test11_TimeMeasurement {

	/** 
	 *  test Response Time
	 */
	@Test
	public void testResponseTime(){
		long t = given().get("https://jsonplaceholder.typicode.com/photos").time();
		System.out.println("Time(ms): "+t);
	}
	
	/** 
	 * test Response Time In Units
	 */
	@Test
	public void testResponseTimeInUnits(){
		long t = given().get("https://jsonplaceholder.typicode.com/photos").timeIn(TimeUnit.MILLISECONDS);
		System.out.println("Time(ms): "+t);
	}
	
	/** 
	 * test Response Time Assertion
	 */
	@Test
	public void testResponseAssertion(){
		given().get("https://jsonplaceholder.typicode.com/photos").then().time(lessThan(900L));
	}	
}
