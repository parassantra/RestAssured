package testRest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Test1_BasicFeaturesForXML {
	
	/** test xml response
	 *  single content 
	 *  body
	*/
	@Test
	public void testSingleContent(){
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
		then().
			body("CUSTOMER.ID", equalTo("10"));
	}
	
	/** test xml response 
	 * multiple content 
	 * body*/
	@Test
	public void testMultipleContent(){
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
		then().
			body("CUSTOMER.ID", equalTo("10")).
			body("CUSTOMER.FIRSTNAME", equalTo("Sue")).
			body("CUSTOMER.LASTNAME", equalTo("Fuller")).
			body("CUSTOMER.STREET", equalTo("135 Upland Pl.")).
			body("CUSTOMER.CITY", equalTo("Dallas"));
	}
	
	/** Compare Complete 
	 * text in One Go*/
	@Test
	public void testCompleteTextInOneGo(){
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
		then().
			body("CUSTOMER.text()", equalTo("10SueFuller135 Upland Pl.Dallas"));
	}
	
	/** xpath to find values*/
	@Test
	public void testUsingXpath1(){
		given().
			get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10").
		then().
			body(hasXPath("/CUSTOMER/FIRSTNAME", containsString("Sue")));
	}
	
	/** xpath types*/
	@Test
	public void testUsingXpath2(){
		given().
			get("http://www.thomas-bayer.com/sqlrest/INVOICE").
		then().
		body(hasXPath("/INVOICEList/INVOICE[text()='40']"));
	}
}
