package com.tradeleaves.marketplace.testcases.uicheckonpages;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.tradeleaves.marketplace.baseautomation.Baseautomation;
import com.tradeleaves.marketplace.pages.LandingPage;
import com.tradeleaves.marketplace.pages.SupplierFlow;
import com.tradeleaves.marketplace.pages.UICheckOnPages;

import jxl.read.biff.BiffException;

public class Run_SupplierFlow extends Baseautomation
{
	
	private LandingPage landingpage;
	private SupplierFlow supplierflow;
	
	
	
	private StringBuilder verificationErrors = new StringBuilder();
	static Logger log = Logger.getLogger(Run_UICheckTest.class);
	
	
	//Initialize the Page objects
	@Test(priority = 1)
	public void Initialize() 
	{
		landingpage = PageFactory.initElements(_driver, LandingPage.class);
		supplierflow = PageFactory.initElements(_driver, SupplierFlow.class);
	}
	
	//Launch marketplace application with browserName as a parameter
	@Parameters("browserName")
	@BeforeTest
	public void setUp(String browserName) throws InterruptedException
	{
	super.launchMarketplaceApplicationIN(browserName);
	}
	
	
	//Method to Register a supplier, register his business, subscription for that supplier & post a product to marketplace
	@Test(priority = 2)
	public void doUICheckforSupplierFlow() throws IOException, BiffException, ClassNotFoundException, SQLException, InterruptedException, AWTException, FindFailed
	{
		try {
			System.out.println("********UI Check Started********");
			System.out.println("--------------------------------------");
			landingpage.ValidatePageTitle();
			supplierflow.doUICheckforSupplierFlow();
			
			System.out.println("********UI Check Ends********");
			
		} catch (IOException ioexp) 
		{
		System.out.println("Failed");
		}	
	}
	
	//TakeScreenshot once supplier flow fails
		@AfterMethod 
		public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
			if (testResult.getStatus() == ITestResult.FAILURE) { 
				File scrFile = ((TakesScreenshot)_driver).getScreenshotAs(OutputType.FILE); 
				FileUtils.copyFile(scrFile, new File("IssuesScreenshots/UICheckOnPages/UICheck_"+System.currentTimeMillis()+".jpeg")); 
			} 
		}
		
	
	//Method to quit the driver
	//@AfterTest()
	public void teardown()
	{
		_driver.quit();
	}
	
}

