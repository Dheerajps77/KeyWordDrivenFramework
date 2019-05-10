package com.qa.TestClasses;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.BaseClasses.TestBase;

public class KeyWordEngine {

	public WebDriver driver = null;
	public Properties prop = null;
	public Workbook workbook = null;
	public Sheet sheet = null;

	public final String path = "C:\\Users\\dheeraj.singh\\eclipse-workspace\\KeyWordDrivenFramework\\TestFile\\hubspot_scenario.xlsx";
	TestBase obj;

	public void startExecution(String sheetName) {
		File file;
		FileInputStream fis;
		Workbook wb;
		String locator;
		String locatorName = null;
		String locatorValue = null;
		String action;
		String value;
		WebElement element;
		try {
			file = new File(path);
			fis = new FileInputStream(file);
			wb = WorkbookFactory.create(fis);
			sheet = wb.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int k = 0;
			for (int i = 0; i < rowCount; i++) {
				try {
					locator = sheet.getRow(i + 1).getCell(k + 1).getStringCellValue().toString().trim();
					if (!locator.equalsIgnoreCase("NA")) {
						locatorName = locator.split("=")[0].trim();
						locatorValue = locator.split("=")[1].trim();
					}
					action = sheet.getRow(i + 1).getCell(k + 2).getStringCellValue().toString().trim();
					value = sheet.getRow(i + 1).getCell(k + 3).getStringCellValue().toString().trim();
					obj = new TestBase();

					switch (action) {
					case "open browser":
						prop = obj.ReadConfigValues();
						if (value.equalsIgnoreCase("NA") || value.isEmpty()) {
							driver = obj.InitDriver(prop.getProperty("Browser"), prop.getProperty("Headless"));
						} else {
							driver = obj.InitDriver(value, prop.getProperty("Headless"));
						}
						break;

					case "enter url":
						if (value.equalsIgnoreCase("NA") || value.isEmpty()) {
							driver.get(prop.getProperty("Url"));
						} else {
							driver.get(value);
						}
						break;

					case "quit":
						driver.quit();
						break;

					default:
						break;
					}

					switch (locatorName) {
					case "id":
						element = driver.findElement(By.id(locatorValue));
						if (action.equalsIgnoreCase("sendkeys")) {
							element.clear();
							element.sendKeys(value);
						} else if (action.equalsIgnoreCase("click")) {
							element.click();
						}
						locatorName = null;
						break;
					case "linkText":
						element = driver.findElement(By.linkText(locatorValue)); {
						element.click();
					}
						locatorName = null;
						break;
					default:
						break;
					}
				} catch (Exception e) {					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
