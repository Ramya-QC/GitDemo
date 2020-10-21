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


package com.tradeleaves.marketplace.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tradeleaves.marketplace.baseautomation.CommonMethods;
import com.tradeleaves.marketplace.baseautomation.TestConstants;
import com.tradeleaves.marketplace.objectrepository.Configuration;

import jxl.read.biff.BiffException;

/**
 * 
 * @author MNANU
 *
 */

public class LandingPage 
{
	private WebDriver driver;
	   
	public LandingPage(WebDriver driver) 
	{
			this.driver = driver;
	}

    //Find element by its desired properties
	@FindBy(xpath = Configuration.LandingPage.Signin)
	public WebElement Signinlnk;
	@FindBy(xpath = Configuration.LandingPage.RegisterBtn)
	public WebElement RegisterBtn;
	
	
	//Validate the page title
	public void ValidatePageTitle() throws BiffException, IOException
	{
	CommonMethods.ValidatePageTitle(TestConstants.PAGE_TITLE);
	}
	
	//Method to click on sign in button
	public void ClickSignIn()
	{
	 CommonMethods.elementtobeVisible(Signinlnk);
	 Signinlnk.click();
	}
	
	//Method to click on register button
	public void ClickRegister()
	{
	 CommonMethods.elementtobeClickable(RegisterBtn);
	 RegisterBtn.click();
	}
	
	
}
