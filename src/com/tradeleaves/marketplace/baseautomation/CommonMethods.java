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

/**
 * 
 * @author SV
 * Class containing methods used frequently to handle the actions in Application.
 */

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



//import com.asg.pages.Driver;		


public class CommonMethods extends Baseautomation
{
	
	private static final WebDriver driver = null;
	private static final Object title = null;
	private static boolean acceptNextAlert = false;


	public static boolean clickLeftNavigationLink(WebElement element){
		if(element.isDisplayed()){
			if(element.isEnabled()){
				element.click();
				return true;
			}
			return false;
		}
		return false;
	}
	
	
	public static void enterData(WebElement element, String value) throws InterruptedException{
		element.clear();
		element.sendKeys(value);
		Thread.sleep(5000);
	}
	
	public static void Upload(WebElement element, String value) throws InterruptedException{
		//element.clear();
		Thread.sleep(3000);
		element.sendKeys(value);
		Thread.sleep(3000);
		
	}
	
	public static String getData(WebElement element){
		//element.clear();
		String x=element.getText();
		return x;
		}
	
	public static boolean verifyTextOnThePage(String expected, String closeAlertAndGetItsText) throws InterruptedException{
		if(expected.equals(closeAlertAndGetItsText))
			return true;
		else
			return false;
	}

	
	public static boolean verifyText(String expected, WebElement Element) throws InterruptedException{
		if (expected.equals(Element)) {
		System.out.println("It matches");
		} else {
        System.out.println("It does not match");
		}
		return acceptNextAlert;
	}
	
	
	public static void VerifyLinkText(WebElement element, String expected) throws InterruptedException{
		String Text = element.getText();
		Thread.sleep(2000L);
		if (Text.contentEquals(expected)) 
		{
		System.out.println("Matches");	
		} else 
		{
		System.out.println("Does not Match");
		}
		
	}
	
	
	 public static boolean verifyButton(WebElement element1) throws InterruptedException {
		    try {
		    	if(isElementPresent(element1))
		      element1.click();
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
		  }

	private static boolean isElementPresent(WebElement element1) {
		// TODO Auto-generated method stub
		return false;
	}

    
	
	//Refresh Page
	public static void PageRefresh()
	{
	driver.navigate().refresh();
	}
	
	
	public static String closeAlertAndGetItsText(WebDriver driver2) {
	    try {
	      Alert alert = driver2.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } catch(Exception e) {
	    	System.out.println();
	    }
	    finally {
	      acceptNextAlert = true;
	    }
		return null;
	  }

	public static void selectradiobutton(WebElement mappedradiobutton,String value, WebDriver driver) throws InterruptedException {
		
		if(!mappedradiobutton.isSelected())
		{
			mappedradiobutton.click();
		}
		
   }
	
	public static void SelectValue(WebElement element, String value) throws InterruptedException {
		// TODO Auto-generated method stub
		new Select(element).selectByVisibleText(value);
		Thread.sleep(5000);
	}
	
	
	public static void menuclick(WebElement link, WebDriver driver)
	{
	 Actions builder = new Actions(driver);
    builder.moveToElement(link).build().perform();
    link.click();
}
	
	public static void listmultipleselection1(WebElement element,String value,WebDriver driver) throws InterruptedException  {
		//WebElement select = driver.findElement(Locator);
		List<WebElement> options = element.findElements(By.tagName("option"));

		for (WebElement option : options) {

			if (option.getText().trim().equals(value))

				option.click();
		}
}
	/**
	 * 
	 * @author SV
	 ** Class containing methods used to retrieve the webtable values
	 *  the result display in grid as per the search criteria and print them on Console
	 */

	
	public static void getwebtablevalues1(WebElement element, String element1)
	{
	
		
		List<WebElement> rowCollection=element.findElements(By.xpath(element1));
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Number of rows in this table:"+rowCollection.size());
		System.out.println("-----------------------------------------------------------------------");
		//Here i_RowNum and i_ColNum, i am using to indicate Row and Column numbers.      
		int i_RowNum=1;
		for(WebElement rowElement:rowCollection)
		{
			  
			  //List<WebElement> colCollection1=rowCollection.get(i_RowNum).findElements(By.tagName("thead"));
			  List<WebElement> colCollection=rowElement.findElements(By.xpath("div"));
		      int i_ColNum=1;
		      for(WebElement colElement:colCollection)
		      {
		          if (!colElement.getText().trim().equals(""))
		         {
		          /********* Commented line is to print the Row number and Column number***********/
		    	  //System.out.println("Row number "+i_RowNum+"\n"+" Column number "+i_ColNum+"\n");
		          //System.out.println("Value in "+i_ColNum+"="+colElement.getText());
		    	  System.out.print(colElement.getText()+"|");
		          i_ColNum=i_ColNum+1;
		          }
		       }
		      System.out.print("\n");
		    i_RowNum=i_RowNum+1;
		 
		}
	}


	
	//Explicit Wait
	public static void elementtobeClickable(WebElement element)
		{
		WebDriverWait wait = new WebDriverWait(_driver, TestConstants.WAITTIME);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		}
	
	
	//Explicit Wait for element
		public static void elementtobeVisible(WebElement element)
		{
			WebDriverWait wait = new WebDriverWait(_driver, TestConstants.WAITTIME);
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		
	//Explicit wait for element with text
	public static void elementtobeVisible(WebElement element, String text)
		{
			WebDriverWait wait = new WebDriverWait(driver, TestConstants.WAITTIME);
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
			System.out.println(element.getText()==text);
		}
	
	//Validate the Page Title
	public static void ValidatePageTitle(WebElement element, String ExpectedPageTitle)
	{
		String ActualTitle = element.getText();
		Assert.assertEquals(ExpectedPageTitle, ActualTitle);
		System.out.println("Expected Title is: "+ExpectedPageTitle+ System.lineSeparator()+"Actual Title is: "+ActualTitle);	
	}
	
	
	//Validate the Page Title
	public static void ValidatePageTitle(String ExpectedPageTitle)
	{
	String ActualTitle = _driver.getTitle();
	System.out.println("Expected Title = "+ExpectedPageTitle);
	System.out.println("Actual Title = "+ActualTitle);
	Assert.assertEquals(ExpectedPageTitle, ActualTitle);
	System.out.println("Expected Title is: "+ExpectedPageTitle+ System.lineSeparator()+"Actual Title is: "+ActualTitle);
	}
	
	// untested java code, just for the logic
		public static void clickComboItem(WebElement input, String target) {
		    input.click(); // click input to pop up the combo list
		    System.out.println(input);
		    List<WebElement> comboItems = driver.findElements(By.cssSelector(".autoCompleteUl>li>b"));
		    for (int i = 0; i <= comboItems.size(); i++) {
		        WebElement item = comboItems.get(i);
		        if (item.getText().equals(target)) {
		            item.click();
		            break;
		        }
		    }
		}
	
		//
	public static void verifyText1(String expecteddaimmecopy,
			WebElement linkTextDAImmeCopy) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void switchWindow(String newwindowTitle) {
		String currentWindow = driver.getWindowHandle();
		System.out.println("currentWindow : "+currentWindow);
		System.out.println("Old window id : "+newwindowTitle);
		Set<String> availableWindows = driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				System.out.println("[windowId : "+windowId+" ]");
				if (driver.switchTo().window(windowId).getTitle()
						.equals(title)) {
				} else {
					driver.switchTo().window(currentWindow);
				}
			}
		}
	}
}

