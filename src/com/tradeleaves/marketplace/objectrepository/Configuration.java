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


package com.tradeleaves.marketplace.objectrepository;

public class Configuration 
{

	//Object_properties_on_LandingPage		
	public static class LandingPage 
		{
		public static final String Signin = ".//*[@id='main']/div[1]/md-toolbar/div/div/ul/li[3]/a";
		public static final String RegisterBtn = ".//*[@id='toolbar']/div/div/div/p";
		}
	
	
	//Object_properties_on_SigninPage		
	public static class SiginPage 
			{
			public static final String Email = ".//*[@id='input_7']";
			public static final String Password = ".//*[@id='input_8']";
			public static final String Forgotyourpassword = ".//*[@id='content']/div/div/div/div/tl-login/div/p[1]/a";
			public static final String RegisterLnk = ".//*[@id='content']/div/div/div/div/tl-login/div/p[2]/a";	
			public static final String Signin = ".//*[@id='content']/div/div/div/div/tl-login/div/form/div/button";
			public static final String RegisterBtn = ".//*[@id='toolbar']/div/div/div/p";
			}
		
   //Object_properties_on_RegisterPage	
   public static class RegisterPage
   {
           public static final String RegisterTitle = ".//*[@id='content']/div/tl-register/div/div/div[1]/h2";
	       public static final String FullName = ".//*[@id='focus-input']";
           public static final String Email = ".//*[@id='input_10']";
           public static final String PhoneCode = ".//*[@id='select_11']";
           public static final String PhoneNumber = ".//*[@id='input_13']";
           public static final String PasswordTxt = ".//*[@id='password']";
           public static final String ConfirmPassword = ".//*[@id='confirmpassword']";
           public static final String RegisterBtn = ".//*[@id='content']/div/tl-register/div/div/div[2]/div[1]/form/button";
   }
   
   //Object_properties_on_Company Register Page
   public static class CompanyRegiterPage
   {
	   public static final String AttachedFile1P1 = ".//*[@id='documents-upload']/div[2]/table/tbody/tr/td/span[contains(text(),'BrakePads')]";
	   public static final String AttachedFile1P2 = "/parent::td/parent::tr/td/*[contains(text(),'Delete')]";
   }
}
