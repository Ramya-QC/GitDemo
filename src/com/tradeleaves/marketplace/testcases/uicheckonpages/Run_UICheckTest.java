/**
* Copyright TradeLeaves and/or its affiliates,Inc. All Rights Reserved. * 
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
* 
* This code is a sole proprietorship of TradeLeaves,Inc.
* Any redistribution and/or modifications on this code is liable to TradeLeaves,Inc.
* 
* Please visit www.tradeleaves.com if you need additional information or have any
* questions.
*/


package com.tradeleaves.marketplace.testcases.uicheckonpages;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.FindFailed;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.tradeleaves.marketplace.baseautomation.Baseautomation;
import com.tradeleaves.marketplace.pages.LandingPage;
import com.tradeleaves.marketplace.pages.SupplierFlow;
import com.tradeleaves.marketplace.pages.UICheckOnPages;

import jxl.read.biff.BiffException;



public class Run_UICheckTest extends Baseautomation
{
	
	private LandingPage landingpage;
	private UICheckOnPages uicheck;
	
	
	
	private StringBuilder verificationErrors = new StringBuilder();
	static Logger log = Logger.getLogger(Run_UICheckTest.class);
	
	
	//Initialize the Page objects
	@Test(priority = 1)
	public void Initialize() 
	{
		landingpage = PageFactory.initElements(_driver, LandingPage.class);
		uicheck = PageFactory.initElements(_driver, UICheckOnPages.class);
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
	public void testUICheckOnPage() throws IOException, BiffException, ClassNotFoundException, SQLException, InterruptedException, AWTException, FindFailed
	{
		try {
			System.out.println("********UI Check Started********");
			System.out.println("--------------------------------------");
			landingpage.ValidatePageTitle();
			uicheck.doUICheckOnPages();
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
