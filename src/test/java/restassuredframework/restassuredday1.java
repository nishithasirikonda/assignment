package restassuredframework;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

@SuppressWarnings("unused")
public class restassuredday1 {
	@Test(enabled = false)
	public void firsttestcase()
	{
		Response obj = RestAssured.get("http://localhost:3000/ibmstudents");
		System.out.println(obj.asString());
		System.out.println(obj.statusCode());
		System.out.println(obj.headers());
		
	}

	@Test(enabled = false)
	public void testcase2()
	{
		Response obj = RestAssured.delete("http://localhost:3000/ibmstudents/2");
		System.out.println(obj.asString());
	}
	
	@Test(enabled = true)
	public void testcase3()
	{
		RestAssured.baseURI="http://localhost:3000/";
		
		given()
			.get("ibmstudents").
		then()
			.statusCode(200).log().all();
	}
	@Test(enabled = true)
	public void testcase4()
	{
		RestAssured.baseURI="http://localhost:3000/";		
		String bodyvariable  = "{\"name\":\"deepthi\",\"batchno\":\"2\"}";
		given()
	       // .contentType(ContentType.JSON)
		   //.headers ("content-type","application/json")
		    .body(bodyvariable).
		when()
		     .post("ibmstudents").
		then()
		     .statusCode(201)
		     .log().all();
		
		
	}
	@SuppressWarnings("unchecked")
	@Test(enabled = true)
	public void testcase5()
	{
		RestAssured.baseURI="http://localhost:3000/";	
		JSONObject obj = new JSONObject();
		//creating json body
		//this put is your json object class function
		//its not http method
		
		obj.put("name", "jani");
		obj.put("batchno", 5);
		given()
	       // .contentType(ContentType.JSON)
		   .headers ("content-type","application/json")
		    .body(obj.toJSONString()).
		when()
		     .post("ibmstudents").
		then()
		     .statusCode(201)
		     .log().all();
	}	
	@Test(enabled = false)
	public void testcase6()
	{
		RestAssured.baseURI="http://petstore.swagger.io/v2/";	
		
		given()
	      .queryParam("username","nishitha")
	      .queryParam("password","123456789").log().all().
		when()
		     .post("user/login").
		then()
		     .statusCode(200)
		     .log().all();
	}	
	 @DataProvider(name="testdata")
	    public Object[][] data()
	    
	{
		 Object[][] studentdata = new Object[2][2];
	    studentdata[0][0]= "nishitha";
	    studentdata[0][1]= "5";
	    studentdata[1][0]= "jani";
	    studentdata[1][1]= "7";
		return studentdata;
		
	}
       @SuppressWarnings("unchecked")
	@Test(enabled = true,dataProvider="testdata")
       public void testcase7(String fname,String bno)
 {
	RestAssured.baseURI="http://localhost:3000/";
	JSONObject obj = new JSONObject();
	obj.put("name", fname);
	obj.put("batchno", bno);
	
	given()
	.headers ("content-type","application/json")
    .body(obj.toJSONString()).
	when()
	     .post("ibmstudents").
	then()
	     .statusCode(201)
	     .log().all();
}	
}

	
	
