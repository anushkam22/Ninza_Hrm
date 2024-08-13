package NinzaTest;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.genericUtility.Baseclass;
import com.ninza.hrm.genericUtility.DataBaseUtility;
import com.ninza.hrm.genericUtility.ExcelUtils;
import com.ninza.hrm.genericUtility.FileUtils;
import com.ninza.hrm.genericUtility.JavaUtils;
import com.ninzahrm.constants.endpoints.IEndPoints;

import POJO.Employee;
import POJO.Projectt;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * 
 * @author HP
 *
 */

public class EmployeeTest extends Baseclass {
	
	Employee e;
	
	@Test
	public void addEmployeeTest() throws Throwable {

		
		String BaseUri=flib.getDataFromPropertyFile("BaseUri");
		
			String projectName = "Airtell_" + jlib.getRandomNumber();
			String userName = "user_"+jlib.getRandomNumber();
			
			Projectt p = new Projectt("anushkam", projectName, "created", 0);
		
					given()
							.contentType(ContentType.JSON)
							.body(p)
					.when()
							.post(BaseUri+IEndPoints.addProj)
					.then()
							.assertThat().statusCode(201)
							.log().all();
		
		
		
					e = new Employee("Architecture","22-10-1997","aak@gmail.com",
				userName,4,"7066363194", projectName ,"ROLE_EMPLOYEE",userName);
		
		Response rsp=			given()
							.contentType(ContentType.JSON)
							.body(e)
					.when()
							.post(BaseUri+IEndPoints.addEmp);
					rsp.then()
							.assertThat().statusCode(201)
							.log().all();
					int i=rsp.getStatusCode();

					dlib.getConnection();
			boolean flag=dlib.executeQueryandVerifyQuery("select * from employee", 5, userName);
			Assert.assertTrue(flag,"Project in Db not verified");

	}
	
	@Test(dependsOnMethods = "addEmployeeTest")
	public void duplicatename() throws Throwable {

//		
     String BaseUri=flib.getDataFromPropertyFile("BaseUri");
//		
//			String projectName = "Airtell_" + jlib.getRandomNumber();
//			String userName = "user_"+jlib.getRandomNumber();
//			
//			Projectt p = new Projectt("anushkam", projectName, "created", 0);
//		
//					given()
//							.contentType(ContentType.JSON)
//							.body(p)
//					.when()
//							.post(BaseUri+IEndPoints.addProj)
//					.then()
//							.assertThat().statusCode(201)
//							.log().all();
//		
//		
//		
//					Employee e = new Employee("Architecture","22-10-1997","aak@gmail.com",
//				userName,4,"7066363194", projectName ,"ROLE_EMPLOYEE",userName);
		
		Response rsp=			given()
							.contentType(ContentType.JSON)
							.body(e)
					.when()
							.post(BaseUri+IEndPoints.addEmp);
					rsp.then()
							//.assertThat().statusCode(201)
							.log().all();
					int i=rsp.getStatusCode();

					dlib.getConnection();
		//	boolean flag=dlib.executeQueryandVerifyQuery("select * from employee", 5, userName);
			//Assert.assertTrue(flag,"Project in Db not verified");

	}


	@Test
	public void withoutEmailEmployeeTest() throws Throwable {

		String BaseUri=flib.getDataFromPropertyFile("BaseUri");


		String projectName = "Airtell_" + jlib.getRandomNumber();
		String userName = "user_"+jlib.getRandomNumber();
		
		Projectt p = new Projectt("anushkam", projectName, "created", 0);
		
					given()
							.contentType(ContentType.JSON)
							.body(p)
					.when()
					.post(BaseUri+IEndPoints.addProj)
					.then()
							.assertThat().statusCode(201)
							.log().all();
		
		
		
		Employee e = new Employee("Architecture","22-10-1997","",
				userName,4,"7066363194", projectName ,"ROLE_EMPLOYEE",userName);
		
					given()
							.contentType(ContentType.JSON)
							.body(e)
					.when()
							.post(BaseUri+IEndPoints.addEmp)
					 .then()
							.assertThat().statusCode(500)
							.log().all();
}
	
@Test
public void withoutNameEmployeeTest() throws Throwable {

	String BaseUri=flib.getDataFromPropertyFile("BaseUri");


	String projectName = "Airtell_" + jlib.getRandomNumber();
	String userName = "user_"+jlib.getRandomNumber();
	
	Projectt p = new Projectt("anushkam", projectName, "created", 0);
	
				given()
						.contentType(ContentType.JSON)
						.body(p)
				.when()
				.post(BaseUri+IEndPoints.addProj)
				.then()
						.assertThat().statusCode(201)
						.log().all();
	
	
	
	Employee e = new Employee("Architecture","22-10-1997","aak@gmail.com",
			"",4,"7066363194", projectName ,"ROLE_EMPLOYEE","");
	
				given()
						.contentType(ContentType.JSON)
						.body(e)
				.when()
						.post(BaseUri+IEndPoints.addEmp)
				 .then()
						.assertThat().statusCode(500)
						.log().all();
}

@Test
public void withInvalidEmailEmployeeTest() throws Throwable {

	String BaseUri=flib.getDataFromPropertyFile("BaseUri");


	String projectName = "Airtell_" + jlib.getRandomNumber();
	String userName = "user_"+jlib.getRandomNumber();
	
	Projectt p = new Projectt("anushkam", projectName, "created", 0);
	
				given()
						.contentType(ContentType.JSON)
						.body(p)
				.when()
				.post(BaseUri+IEndPoints.addProj)
				.then()
						.assertThat().statusCode(201)
						.log().all();
	
	
	
	Employee e = new Employee("Architecture","22-10-1997","aak@",
			"",4,"7066363194", projectName ,"ROLE_EMPLOYEE","");
	
				given()
						.contentType(ContentType.JSON)
						.body(e)
				.when()
						.post(BaseUri+IEndPoints.addEmp)
				 .then()
						.assertThat().statusCode(500)
						.log().all();
				
				
}

@Test
public void withoutNoEmployeeTest() throws Throwable {

	String BaseUri=flib.getDataFromPropertyFile("BaseUri");


	String projectName = "Airtell_" + jlib.getRandomNumber();
	String userName = "user_"+jlib.getRandomNumber();
	
	Projectt p = new Projectt("anushkam", projectName, "created", 0);
	
				given()
						.contentType(ContentType.JSON)
						.body(p)
				.when()
				.post(BaseUri+IEndPoints.addProj)
				.then()
						.assertThat().statusCode(201)
						.log().all();
	
	
	
	Employee e = new Employee("Architecture","22-10-1997","aak@",
			"",4,"", projectName ,"ROLE_EMPLOYEE","");
	
				given()
						.contentType(ContentType.JSON)
						.body(e)
				.when()
						.post(BaseUri+IEndPoints.addEmp)
				 .then()
						.assertThat().statusCode(500)
						.log().all();
}
}