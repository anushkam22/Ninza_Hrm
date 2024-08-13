package com.ninza.hrm.genericUtility;

import static io.restassured.RestAssured.given;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonUtility {

	public FileUtils flib = new FileUtils();

	public String getDataOnJsonPath(Response resp,String jsonXpath)
	{
		List<Object> list = JsonPath.read(resp.asString(), jsonXpath);
		return list.get(0).toString();
		
	}
	
	
	public String getDataOnXmlPath(Response resp,String xmlXpath)
	{
		return  resp.xmlPath().get(xmlXpath);
		
	}
	
	public boolean verifyDataOnJsonPath(Response resp,String jsonXpath, Object expectedData)
	{
		List<String> list = JsonPath.read(resp.asString(), jsonXpath);
		boolean flag = false;
		for(String str:list)
		{
			if(str.equals(expectedData))
			{
				System.out.println("pass");
				flag=true;
			}
		}
			if(flag==false)
			{
				System.out.println("fail");

			}
				
			return flag; 
	}
	
	
	
	public String getAcessToken() throws Throwable {
		Response resp=	 given()
				.formParam("client_id", flib.getDataFromPropertyFile("clientId"))
				.formParam("client_secret", flib.getDataFromPropertyFile("clientSecret"))
				.formParam("grant_type", "client_credentials")
			//	.formParam("redirectURL","http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token")

				.when().post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
				resp.then()
				.log().all();
				
				String accessToken=resp.jsonPath().get("access_token").toString();
				return accessToken;
	}
}
