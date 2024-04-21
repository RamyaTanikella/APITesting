package APITestCases;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LoginAPITest extends BaseAPITest{

	String token=null;
	@Test(priority=-1)
	public void loginTest01() {
		RestAssured.baseURI= "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
		Response loginRes=RestAssured.given().header("Content-Type","application/json")
				.body("{\"username\": \"deekshith@ta.com\", \"password\": \"deekshith010\"}")
				.when().post("login").then().statusCode(201).extract().response();
	
		
	token=loginRes.jsonPath().getString("token").replace("[","").replace("]","");
		System.out.println(loginRes.asPrettyString());
		
		}
	
	
	@Test
	public void getDataTest01() {
	//	RestAssured.baseURI= "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
		Response getReq=RestAssured.given().header("Content-Type","application/json")
				.header("token",token)
				.when().get("getdata").then().statusCode(200).extract().response();
	
		
//	token=getReq.jsonPath().getString("token").replace("[","").replace("]","");
		System.out.println(getReq.asPrettyString());
		
		}
	
	@Test
	public void addUserTest01() {
		
	HashMap<String, String> user1 = new HashMap<String, String>();
		user1.put("accountno", "TA-5781444");
		user1.put("departmentno", "4");
		user1.put("salary", "2355");
		user1.put("pincode", "345667");
		
		JsonObject user = new JsonObject();
		user.addProperty("accountno", "TA-5781444");
		user.addProperty("departmentno", "4");
		user.addProperty("salary", "2355");
		user.addProperty("pincode", "345667");
		
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("token", token);
		
	//	RestAssured.baseURI= "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
		Response addUser=RestAssured.given().headers(headers)
				.body(user)
				.when().post("addData").then().statusCode(200).extract().response();
	
		
//	token=getReq.jsonPath().getString("token").replace("[","").replace("]","");
		System.out.println(addUser.asPrettyString());
		
		}
}
