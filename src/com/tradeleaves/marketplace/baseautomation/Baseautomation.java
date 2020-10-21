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


package com.tradeleaves.marketplace.baseautomation;

import java.util.HashMap;

/**
 * 
 * @author MNANU
 *
 */

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tradeleaves.marketplace.InitProperties;

public class Baseautomation {

	protected static WebDriver _driver;
	
	protected static WebDriverWait wait;

	private static String URL = null;
	
		
	protected static InitProperties _properties = new InitProperties();
	

		
	//Goto Marketplace
	public void launchMarketplaceApplicationUS(String browserName) throws InterruptedException 
	{
		launchUSSite(browserName);
	}
	
	//Goto Marketplace
		public void launchMarketplaceApplicationIN(String browserName) throws InterruptedException 
		{
			launchINSite(browserName);
		}
	
	//Goto Backoffice
		public void launchBackofficeApplication(String browserName) throws InterruptedException 
		{
			launchBackoffice(browserName);
		}
	
	//Goto Mail URL
		public void launchMail(String browserName) throws InterruptedException 
		{
			launchgmail(browserName);
		}
	
		
	public WebDriver launchBrowser(String browserName) {

		//String browserName = _properties.getConfigProperty(InitProperties.BROWSER);
		  WebDriver browser = null;

		if (browserName.equalsIgnoreCase("Firefox")) 
		{
			/*FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.cache.disk.enable", false);
			firefoxProfile.containsWebDriverExtension();
			firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			browser = new FirefoxDriver(caps);*/
			
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("log", "{level: trace}");
			capabilities.setCapability("marionette", true);
			capabilities.setCapability("moz:firefoxOptions", options);
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
			browser = new FirefoxDriver(capabilities);
		} 
		else if (browserName.contentEquals("InternetExplorer"))
		{
			/*Internet explorer driver supports some important capabilities which can be used to smooth execution of test on 
			 * Internet Explorer. Some of these capabilities help us to unable java scripts, 
			 * ignore the security domain setting for IE , Persistent hovering , require window focus etc... 
			 * These capabilities ease the way the for automation using selenium webdriver on Internet explorer. 
			 * More details on the Internet explorer can be found here. 
			 * Below code will guide on how to use the desired capabilities for Internet Explorer driver. */
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
			caps.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
			caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,true);
			caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setJavascriptEnabled(true); 
			System.setProperty("webdriver.ie.driver", "Drivers/IEDriverServer.exe");
			browser = new InternetExplorerDriver();			
		} 
		else if (browserName.contentEquals("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put("plugins.plugins_disabled", new String[] {"Adobe Flash Player", "Chrome PDF Viewer"});
            chromePrefs.put("profile.default_content_settings.popups", 0);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("disable-infobars");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
           	System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
           	browser = new ChromeDriver(options);
           	
			
		}
		else if (browserName.contentEquals("Edge"))
		{
			DesiredCapabilities caps = DesiredCapabilities.edge();
			System.setProperty("webdriver.edge.driver", "Drivers/MicrosoftWebDriver.msi");
			browser = new EdgeDriver(caps);
		}
		else {
			System.out.println("Here are the correct browser options - Firefox, InternetExplorer, Chrome");
		}
		return browser;
	}

	
	//Launch Marketplace Application 
	public WebDriver launchINSite(String browserName) {

		_driver = launchBrowser(browserName);

		_driver.manage().window().maximize();
		_driver.navigate().to(_properties.getConfigProperty(InitProperties.MARKETPLACEURL_IN));

		_driver.manage().timeouts().implicitlyWait(InitProperties.TIMEOUT, TimeUnit.SECONDS);
		_driver.manage().timeouts().pageLoadTimeout(InitProperties.PAGELOAD_TIMEOUT,TimeUnit.SECONDS);

		return _driver;
	}
	
	
	//Launch Marketplace Application 
	public WebDriver launchUSSite(String browserName) {

			_driver = launchBrowser(browserName);

			_driver.manage().window().maximize();
			_driver.navigate().to(_properties.getConfigProperty(InitProperties.MARKETPLACEURL_US));

			_driver.manage().timeouts().implicitlyWait(InitProperties.TIMEOUT, TimeUnit.SECONDS);
			_driver.manage().timeouts().pageLoadTimeout(InitProperties.PAGELOAD_TIMEOUT,TimeUnit.SECONDS);

			return _driver;
		}
	
	//Launch Backoffice Application 
		public WebDriver launchBackoffice(String browserName) {

			_driver = launchBrowser(browserName);

			_driver.manage().window().maximize();
			_driver.navigate().to(_properties.getConfigProperty(InitProperties.BACKOFFICEURL));

			_driver.manage().timeouts().implicitlyWait(InitProperties.TIMEOUT, TimeUnit.SECONDS);
			_driver.manage().timeouts().pageLoadTimeout(InitProperties.PAGELOAD_TIMEOUT,TimeUnit.SECONDS);

			return _driver;
		}
		
	
	//Launch Mail
	public WebDriver launchgmail(String browserName) {

			_driver = launchBrowser(browserName);

			_driver.manage().window().maximize();
			_driver.navigate().to(_properties.getConfigProperty(InitProperties.MAILURL));
			_driver.manage().timeouts().implicitlyWait(InitProperties.TIMEOUT, TimeUnit.SECONDS);
			_driver.manage().timeouts().pageLoadTimeout(InitProperties.PAGELOAD_TIMEOUT,TimeUnit.SECONDS);

			return _driver;
	}
	
}
	
	
	