package com.ninza.hrm.genericUtility;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Baseclass {

	
	public FileUtils flib = new FileUtils();
	public ExcelUtils elib = new ExcelUtils();
	public JavaUtils jlib = new JavaUtils();
	public DataBaseUtility dlib = new DataBaseUtility();
	public  static RequestSpecification specReqObj;
	public  static ResponseSpecification specRespObj;
	
	@BeforeSuite
	public void configBS() throws Throwable {
		 dlib.getConnection();
		 System.out.println("============Connect TO DB==========");
		 RequestSpecBuilder builder = new RequestSpecBuilder();
		 builder.setContentType(ContentType.JSON);
		// builder.setAuth(basic("username", "password"));
		// builder.addHeader("", "");
		 builder.setBaseUri(flib.getDataFromPropertyFile("BaseUri"));
		 System.out.println(flib.getDataFromPropertyFile("BaseUri"));
		 specReqObj =  builder.build();
		 
		 ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		 resBuilder.expectContentType(ContentType.JSON);
		 specRespObj =  resBuilder.build();
	}
	
	@AfterSuite
	public void configAS()  {
		dlib.closeDb();
		System.out.println("==========DisConnectToDB===========");
	}

}
