package NinzaTest;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.genericUtility.DataBaseUtility;
import com.ninza.hrm.genericUtility.ExcelUtils;
import com.ninza.hrm.genericUtility.FileUtils;
import com.ninza.hrm.genericUtility.JavaUtils;
import com.ninzahrm.constants.endpoints.IEndPoints;

import POJO.Projectt;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProjectTest {

	Projectt p;
	public FileUtils flib = new FileUtils();
	public ExcelUtils elib = new ExcelUtils();
	public JavaUtils jlib = new JavaUtils();
	public DataBaseUtility dlib = new DataBaseUtility();
	           
	@Test
	public void addSingleProject() throws Throwable {
			
		String BaseUri=flib.getDataFromPropertyFile("BaseUri");
		
		
		String projectName = "AAK_" + jlib.getRandomNumber();
		
		
		p = new Projectt("anushka", projectName , "created", 2);
		
		Response resp = given()
								.contentType(ContentType.JSON)
								.body(p)
								.when()
								.post(BaseUri+IEndPoints.addProj);
								
						resp
								.then()
								.assertThat().statusCode(201)
								.assertThat().time(Matchers.lessThan(3000L))
								.assertThat().contentType(ContentType.JSON)
								.log().all();
					
		String actMsg = resp.jsonPath().get("msg");
		String expectedmsg="Successfully Added";
		Assert.assertEquals(expectedmsg, actMsg);
	}
	
	@Test(dependsOnMethods = "addSingleProject")
	public void createDuplicateProject() throws Throwable {

		String BaseUri=flib.getDataFromPropertyFile("BaseUri");

							given()
									.contentType(ContentType.JSON)
									.body(p)
									.when()
									.post(BaseUri+IEndPoints.addProj)
									
							.then()
							.assertThat().statusCode(409)
							.assertThat().time(Matchers.lessThan(3000L))
							.log().all();
				
		
}
}
