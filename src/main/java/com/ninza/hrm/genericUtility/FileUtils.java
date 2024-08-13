package com.ninza.hrm.genericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtils {
	
	public String getDataFromPropertyFile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("./configEnvData/configEnvData.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String data = prop.getProperty(key);
		return data;
	}

}
