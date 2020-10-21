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



package com.tradeleaves.marketplace;
/**
 * 
 * @author SV
 * Initializing all the base properties like - URL, Testdata, Browser...etc.
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class InitProperties {

	private final static Logger log = Logger.getLogger(InitProperties.class.getName());
	
	public final static int TIMEOUT = 70;
	public final static int MAXTIMEOUT = 120;
	public final static int PAGELOAD_TIMEOUT = 70;
	public final static int MAXPAGELOAD_TIMEOUT = 120;
	public final static int SCREEN_WIDTH = 1366;
	public final static int SCREEN_HEIGHT = 662;
	
	private final static String CONFIG_PROPERTY_FILENAME = "Config/config.properties";
	private static Properties configProperties = new Properties();
	
	public final static String WEBSITEURL = "WEBSITEURL";
	public final static String MARKETPLACEURL_IN = "MARKETPLACEURL_IN";
	public final static String MARKETPLACEURL_US = "MARKETPLACEURL_US";
	public final static String BACKOFFICEURL = "BACKOFFICEURL";
	public final static String MAILURL = "MAILURL";
	public final static String DBURL = "DBURL";
	public final static String USERNAME = "USERNAME";
	public final static String PASSWORD = "PASSWORD";
	public final static String APPLITOOLSAPIKEY = "APPLITOOLSAPIKEY"; 
	
	
	
	//Workbook Name
	public static final String WBBOOK = "WBBOOK";
		
		
	//Sheet Name
	public static final String SHEET_LANDING_PAGE = "SHEET_LANDING_PAGE";
	public static final String SHEET_REGISTER_PAGE = "SHEET_REGISTER_PAGE";
	public static final String SHEET_PERSONAL_PREFERENCES = "SHEET_PERSONAL_PREFERENCES";
	public static final String SHEET_LOGOUT = "SHEET_LOGOUT";
	public static final String SHEET_COMPANYREGISTER_PAGE = "SHEET_COMPANYREGISTER_PAGE";
	public static final String SHEET_SUBSCRIBE = "SHEET_SUBSCRIBE";	
	public static final String SHEET_FORGOT_PASSWORD = "SHEET_FORGOT_PASSWORD";
	public static final String SHEET_SIGNIN = "SHEET_SIGNIN";
	public static final String SHEET_PRODUCTVERIFICATION = "SHEET_PRODUCTVERIFICATION";
	public static final String SHEET_COMPANYVERIFICATION_PAGE = "SHEET_COMPANYVERIFICATION_PAGE";
	public static final String SHEET_SUPPLIER_BUSINESSFLOW = "SHEET_SUPPLIER_BUSINESSFLOW";
	public static final String SHEET_SUPPLIER_PRODUCTVERIFICATION = "SHEET_SUPPLIER_PRODUCTVERIFICATION";
	public static final String SHEET_BUYER_BUSINESSFLOW = "SHEET_BUYER_BUSINESSFLOW";
	public static final String SHEET_BUYER_COMPANYVERIFICATION_PAGE = "SHEET_BUYER_COMPANYVERIFICATION_PAGE";
	public static final String SHEET_UICHECK_PAGEELEMENTS = "SHEET_UICHECK_PAGEELEMENTS";
	public static final String SHEET_UICHECKON_STATICPAGES = "SHEET_UICHECKON_STATICPAGES";
	public static final String SHEET_UICHECKON_COMPREG = "SHEET_UICHECKON_COMPREG";
	
	
	
	/**
	 * Loads the properties file
	 */
	public InitProperties() {
		try {
			configProperties.load(new FileInputStream(CONFIG_PROPERTY_FILENAME));
		} catch (IOException e) {
			//log.error(e.getMessage());
		}
		assert !configProperties.isEmpty();
	}

	/**
	 * returns the value of the property denoted by key
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfigProperty(final String key) {
		String property = configProperties.getProperty(key);
		return property != null ? property.trim() : property;
	}
}