package com.ninza.hrm.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	static Connection con;
	static ResultSet set;
	static public FileUtils flib = new FileUtils();

	public static void getConnection() throws Throwable {
		Driver driver;
		try {

			driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(flib.getDataFromPropertyFile("DBUrl"), 
					flib.getDataFromPropertyFile("DBusername"), flib.getDataFromPropertyFile("DBpassword"));
			set = con.createStatement().executeQuery("Select * from employee");

		}

		catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public static  ResultSet CreateQuery(String query) {
		ResultSet set = null;
		try {
			Statement stat = con.createStatement();
			set = stat.executeQuery(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return set;
	}
	
	public static  boolean executeQueryandVerifyQuery(String query, int columnIndex, String expectedData) throws Throwable {
		boolean flag = false;
					Statement stat = con.createStatement();
			set = stat.executeQuery(query);
			while(set.next()) {
				if(set.getString(columnIndex).equals(expectedData)) {
				flag = true ;
				break;
		} }
			if(flag) {
				System.out.println(expectedData+"data verified");
				return true;
			}
			else
			{
				System.out.println(expectedData+"data verified");
				return false;
			}
		
	

}

	public void closeDb() {
		// TODO Auto-generated method stub
		
	}}