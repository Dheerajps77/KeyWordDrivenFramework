package com.qa.ConfigUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class EnvPropertiesReader {

	private Properties prop;
	public static EnvPropertiesReader objEnvPropertiesReader = null;

	public EnvPropertiesReader() {
		prop= PropertiesFiles();
	}

	public static EnvPropertiesReader getInstances() {
		try {
			if (objEnvPropertiesReader == null) {
				objEnvPropertiesReader = new EnvPropertiesReader();
			} else {
				return objEnvPropertiesReader;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objEnvPropertiesReader;
	}
	
	public Properties PropertiesFiles()
	{
		File file;
		FileInputStream fis;
		String filePath="C:\\Users\\dheeraj.singh\\eclipse-workspace\\KeyWordDrivenFramework\\src\\main\\java\\com\\qa\\ConfigUtils\\config.properties";
		Properties properties=new Properties();

		try
		{
			file=new File(filePath);
			fis=new FileInputStream(file);
			properties.load(fis);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public String getProperties(String key) 
	{
		String value="";
		try
		{
			prop=PropertiesFiles();
		  	value=prop.getProperty(key);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
