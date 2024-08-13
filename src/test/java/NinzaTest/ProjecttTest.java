package NinzaTest;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.genericUtility.Baseclass;
import com.ninzahrm.constants.endpoints.IEndPoints;

import POJO.Projectt;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjecttTest extends Baseclass {

	Projectt p;	         
	@Test
	public void addSingleProject() throws Throwable {
			
		String projectName = "AAK_" + jlib.getRandomNumber();
		
		
		p = new Projectt("anushka", projectName , "created", 0);
		
		Response resp = given(specReqObj)
								.body(p)
								.when()
								.post(IEndPoints.addProj);
								
						resp
								.then()
								.assertThat().statusLine("HTTP/1.1 201 ")
								.assertThat().statusCode(201)
								.assertThat().contentType(ContentType.JSON)
								
								
						
								.log().all();

						String actmsg=resp.jsonPath().get("msg");
						Assert.assertEquals(actmsg, "Successfully Added");
					
	}
	
	@Test
	public void addProjectWitONGoingStatus() throws Throwable
	{
	String projectName = "AAK_" + jlib.getRandomNumber();
		p= new Projectt("anushka", projectName , "Ongoing", 0);
		
		Response resp = given(specReqObj)
				.body(p)
				.when()
				.post(IEndPoints.addProj);
		
		resp.then()
		.assertThat().statusCode(201)
		.assertThat().statusLine("HTTP/1.1 201 ")
		.assertThat().header("Content-Type", "application/json")
		.log().all();
		
		 long t = resp.getTime();
		 System.out.println(t);
	}
	
	@Test
	public void addProjectWithCompletedStatus() throws Throwable
	{
	
		String projectName = "AAK_" + jlib.getRandomNumber();
		p= new Projectt("anushka", projectName , "Completed", 0);
		
		Response resp = given(specReqObj)
				.body(p)
				.when()
				.post(IEndPoints.addProj);		
		resp.then()
		.assertThat().statusCode(201)
		.assertThat().statusLine("HTTP/1.1 201 ")
		.assertThat().header("Content-Type", "application/json")
		.log().all();
		
		 long t = resp.getTime();
		 System.out.println(t);
	}
	
	
	@Test
	public void addProjectWithoutName() throws Throwable
	{
		String projectName = "AAK_" + jlib.getRandomNumber();
		p= new Projectt("anushka","", "Completed", 0);
		
		Response resp = given(specReqObj)
				.body(p)
				.when()
				.post(IEndPoints.addProj);;
		
		resp.then()
		.assertThat().statusCode(502)
		.assertThat().time(Matchers.lessThanOrEqualTo(3000L))
		.assertThat().statusLine("HTTP/1.1 502 ")
	.assertThat().header("Content-Type", "application/json")
		.log().all();
		
		 long t = resp.getTime();
		 System.out.println(t);
	}
	
	@Test
	public void addProjectWithNull() throws Throwable
	{
		String projectName = "AAK_" + jlib.getRandomNumber();
		p= new Projectt("anushka",null, "Completed", 0);
		
		Response resp = given(specReqObj)
				.body(p)
				.when()
				.post(IEndPoints.addProj);
		
		resp.then()
		.assertThat().statusCode(502)
		.assertThat().time(Matchers.lessThanOrEqualTo(3000L))
		.assertThat().statusLine("HTTP/1.1 502 ")
	.assertThat().header("Content-Type", "application/json")
		.log().all();
		
		 long t = resp.getTime();
		 System.out.println(t);
	}
	
	@Test
	public void addProjectWithoutStaus() throws Throwable
	{
		
		String projectName = "AAK_" + jlib.getRandomNumber();
		p= new Projectt("anushka",projectName, "", 0);
		
		Response resp = given(specReqObj)
				.body(p)
				.when()
				.post(IEndPoints.addProj);
		
		resp.then()
		.assertThat().statusCode(502)
		.assertThat().time(Matchers.lessThanOrEqualTo(3000L))
		.assertThat().statusLine("HTTP/1.1 502 ")
	.assertThat().header("Content-Type", "application/json")
		.log().all();
		
		 long t = resp.getTime();
		 System.out.println(t);
	}
	
	
	@Test
	public void addProjectWithNegativeTeamSize() throws Throwable
	{
		String projectName = "AAK_" + jlib.getRandomNumber();
		p= new Projectt("anushka",projectName, "Ongoing", -10);
		
		Response resp = given(specReqObj)
				.body(p)
				.when()
				.post(IEndPoints.addProj);
		
		resp.then()
		.assertThat().statusCode(502)
		.assertThat().time(Matchers.lessThanOrEqualTo(3000L))
		.assertThat().statusLine("HTTP/1.1 502 ")
	.assertThat().header("Content-Type", "application/json")
		.log().all();
		
		 long t = resp.getTime();
		 System.out.println(t);
	}
	
//	
//	@Test
//	public void addProjectWithNegativeTeamSize1() throws Throwable
//	{
//		String BaseUri=flib.getDataFromPropertyFile("BaseUri");
//		
//		
//		String projectName = "AAK_" + jlib.getRandomNumber();
//		p= new Projectt("anushka",projectName, "Ongoing", 1-);
//		
//		Response resp=given()
//				.contentType(ContentType.JSON)
//				.body(p)
//				.post(BaseUri + IEndPoints.addProj);
//		
//		resp.then()
//		.assertThat().statusCode(502)
//		.assertThat().time(Matchers.lessThanOrEqualTo(3000L))
//		.assertThat().statusLine("HTTP/1.1 502 ")
//	.assertThat().header("Content-Type", "application/json")
//		.log().all();
//		
//		 long t = resp.getTime();
//		 System.out.println(t);
//	}
//	

	@Test
	public void addProjectWithInavlidStaus() throws Throwable
	{
		String BaseUri=flib.getDataFromPropertyFile("BaseUri");
		
		
		String projectName = "AAK_" + jlib.getRandomNumber();
		p= new Projectt("anushka",projectName, "Com", -10);
		
		Response resp = given(specReqObj)
				.body(p)
				.when()
				.post(IEndPoints.addProj);
		
		resp.then()
		.assertThat().statusCode(502)
		.assertThat().time(Matchers.lessThanOrEqualTo(3000L))
		.assertThat().statusLine("HTTP/1.1 502 ")
	.assertThat().header("Content-Type", "application/json")
		.log().all();
		
		 long t = resp.getTime();
		 System.out.println(t);
	}
	
	@Test(dependsOnMethods = "addSingleProject")
	public void createDuplicateProject() throws Throwable {

		String BaseUri=flib.getDataFromPropertyFile("BaseUri");
		Response resp = null;
		try {
			 resp = given(specReqObj)
					.body(p)
					.when()
					.post(IEndPoints.addProj);
					
			resp.then()
			.assertThat().statusCode(409)
			.assertThat().time(Matchers.lessThan(3000L))
			.log().all();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
					finally {
						long t = resp.getTime();
						System.out.println(t);
						String statusline = resp.getStatusLine();
						System.out.println(statusline);
					}
				
		
}
}
