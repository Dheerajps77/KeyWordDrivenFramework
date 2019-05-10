package com.qa.BaseClasses;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.ConfigUtils.EnvPropertiesReader;

public class TestBase {
	
	Properties prop;
	String url;
	String userName;
	String passWord;
	String headlessRequire;
	String browser;
	WebDriver driver;
	
	
	public WebDriver InitDriver(String browserName, String headless)
	{
		try
		{
			if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\dheeraj.singh\\eclipse-workspace\\KeyWordDrivenFramework\\Drivers\\chromedriver.exe");
				if(headless.equalsIgnoreCase("yes"))
				{
					ChromeOptions chromeOptions=new ChromeOptions();
					chromeOptions.addArguments("--headless");
					driver=new ChromeDriver(chromeOptions);
				}
				else
				{
					return driver=new ChromeDriver();
				}
			}
			
			else if(browserName.equalsIgnoreCase("ff"))
			{
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\dheeraj.singh\\eclipse-workspace\\KeyWordDrivenFramework\\Drivers\\geckodriver.exe");
				if(headless.equalsIgnoreCase("yes"))
				{
					FirefoxOptions firefoxOption=new FirefoxOptions();
					firefoxOption.addArguments("--headless");
					driver=new FirefoxDriver(firefoxOption); 
				}
				else
				{
					return driver=new FirefoxDriver();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	
	public Properties ReadConfigValues()
	{
		EnvPropertiesReader obj=EnvPropertiesReader.getInstances();
		prop=obj.PropertiesFiles();
		try
		{
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

}
