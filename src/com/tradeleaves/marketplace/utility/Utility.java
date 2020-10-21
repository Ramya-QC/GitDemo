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


package com.tradeleaves.marketplace.utility;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.jacob.com.LibraryLoader;
import com.tradeleaves.marketplace.InitProperties;
import com.tradeleaves.marketplace.baseautomation.Baseautomation;
import com.tradeleaves.marketplace.baseautomation.TestConstants;

import autoitx4java.AutoItX;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class Utility extends Baseautomation
{

	private WebDriver driver;
	{
		this.driver=driver;
	}
	
	//Method to get the current system date and time
	public static String currentDateTime() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Calendar cal = Calendar.getInstance();
	    String cal1 = dateFormat.format(cal.getTime());
	    return cal1;
	}
	

	public static void UploadFileUsingJacobDll()throws InterruptedException, AWTException
	{
			String workingDir = System.getProperty("user.dir");
			final String jacobdllarch = System.getProperty("sun.arch.data.model").contains("32") ? "jacob-1.18-x86.dll" : "jacob-1.18-x64.dll";
			String jacobdllpath = workingDir + "\\" + jacobdllarch;
			File filejacob = new File(jacobdllpath);
			System.setProperty(LibraryLoader.JACOB_DLL_PATH,filejacob.getAbsolutePath());
			AutoItX uploadWindow = new AutoItX();
			String filepath = workingDir + "\\SeleniumWebdriverUploadFile.html";
			_driver.get(filepath);
			Thread.sleep(1000);
			UploadFile();
			/*_driver.findElement(By.id("uploadfile")).click();
			Thread.sleep(1000);
			if (uploadWindow.winWaitActive("File Upload", "", 5)) 
			{
			if (uploadWindow.winExists("File Upload")) 
			{
			uploadWindow.sleep(100);
			uploadWindow.send(filepath);
			uploadWindow.controlClick("File Upload", "", "&Open");
			}
			}*/
			}
	
	
	public static void setClipboardData(String string) {

		StringSelection stringSelection = new StringSelection(string);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		} 
	
	

	
	public static void UploadFile() throws AWTException, InterruptedException
	{
		WebElement upload_btn =  _driver.findElement(By.xpath("html/body/form/input"));

		Thread.sleep(4000);

		setClipboardData("Pictures\\Lighthouse.jpg");

		Actions builder  = new Actions(_driver);

		Action myAction = builder.click(upload_btn).release().build();

		myAction.perform();

		Robot rbt = new Robot();
		rbt.delay(4000);
		rbt.keyPress(KeyEvent.VK_CONTROL);
		rbt.keyPress(KeyEvent.VK_V);
		rbt.keyRelease(KeyEvent.VK_V);
		rbt.keyRelease(KeyEvent.VK_CONTROL);
		rbt.keyPress(KeyEvent.VK_ENTER);
		rbt.keyRelease(KeyEvent.VK_ENTER);
		rbt.delay(4000);

		//_driver.findElement(By.xpath(submit_button_xpath)).click();
	}
	
	
	public static String maskCardNumber(String cardNumber, String maskedCard) {

	    // format the number
	    int index = 0;
	    StringBuilder maskedCardNumber = new StringBuilder();
	    for (int i = 0; i < maskedCard.length(); i++) {
	        char c = maskedCard.charAt(i);
	        if (c == '#') {
	            maskedCardNumber.append(cardNumber.charAt(index));
	            index++;
	        } else if (c == 'x') {
	            maskedCardNumber.append(c);
	            index++;
	        } else {
	            maskedCardNumber.append(c);
	        }
	    }

	    // return the masked number
	    return maskedCardNumber.toString();
	}
	
	
	public static String ConnectSql(String userId)
	{
		String confirmationCode = null;
	try{
		System.out.println("Connect DB");
		
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con = DriverManager.getConnection(InitProperties.getConfigProperty(InitProperties.DBURL),InitProperties.getConfigProperty(InitProperties.USERNAME), InitProperties.getConfigProperty(InitProperties.PASSWORD));  
		//here sonoo is database name, root is username and password  
		String query = "select verify_hash from email_address_verification where email_address=?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, userId);
		ResultSet rs = stmt.executeQuery();  
		while(rs.next())
		{
			System.out.println(rs.getString(1)); 
		}
		 
		con.close();  
		}
	    catch(Exception e){ System.out.println(e);
		}  
		
		return confirmationCode;
	}
	
	
	

//***************************************************************************************************************************************	
	
	
	//Call chrome identifiers from excel sheet for chrome browser.
	public static void KeywordDataExecution(String WBBook, String sheetName) throws BiffException, IOException, InterruptedException, ClassNotFoundException, SQLException, FindFailed 
		{

	       	FileInputStream fis = new FileInputStream(WBBook);
			Workbook workbook = Workbook.getWorkbook(fis);
			Sheet sh = workbook.getSheet(sheetName);
			WebDriverWait wait = new WebDriverWait(_driver, TestConstants.WAITTIME);
			
			
	for (int j = 3; j < sh.getColumns(); j++) 
			{
			for (int i = 1; i < sh.getRows(); i++) 
				{
				    JavascriptExecutor js = ((JavascriptExecutor)_driver);
				    String locatorvalue = sh.getCell(0, i).getContents();
					//By locator = By(sh.getCell(0, i).getContents());
					//System.out.println("Locator = "+locator);
				    String dir = System.getProperty("user.dir");
					String keyword = sh.getCell(2, i).getContents();
					String value = sh.getCell(j, i).getContents();
					String sqlquery = sh.getCell(1, i).getContents();
					String Product = sh.getCell(1, i).getContents();
					String elementName = sh.getCell(1, i).getContents();
					System.out.println("********************************************************");
					System.out.println("Element_name="+elementName);
					System.out.println("1)WorkbookName = "+workbook+"***2)SheetName = "+sh+"***3)Column No. "+j+"***4)Row No."+i+"***5)Locator/Identifier = "+locatorvalue+"***6)Keyword = "+keyword+"***7)Value = "+value);
									
					if(keyword.equalsIgnoreCase("AutoIT_FileUpload"))
					{
						if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
					{
							WebElement element = _driver.findElement(By.xpath(locatorvalue));
							//js.executeScript("arguments[0].click();", element);
							element.click();
							System.out.println("Clicked");
							Thread.sleep(2000);
							System.out.println(dir);
							String autoITExecutable = "\\AutoIT\\UploadTestDynamicPath.exe ";
							String[] cmd = {dir+value};
							System.out.println(cmd);
							System.out.println(dir+value);
							System.out.println(dir+autoITExecutable);
							Process p = Runtime.getRuntime().exec(dir+autoITExecutable,cmd);
							System.out.println(p);
							p.waitFor();
					}
						else 
							{
							System.out.println("Element not found");
							}
					}
					
					else if(keyword.equalsIgnoreCase("Attach_File"))
				    {
					WebElement element = _driver.findElement(By.xpath(locatorvalue));
					//js.executeScript("arguments[0].click();", element);
					Thread.sleep(2000);
					element.click();
					System.out.println("Clicked");
				    Screen screen = new Screen();
				    Pattern filepath = new Pattern("AutoIT/FilePath.JPG");
				    Pattern openButton = new Pattern("AutoIT/OpenButton.JPG");
				    //screen.wait(filepath,10);
				    screen.wait(filepath, 10);
				    screen.type(filepath, dir+value);
				    screen.click(openButton);
				    System.out.println("Uploaded Successfully");
				    }					
					else if(keyword.equalsIgnoreCase("IsPresent_Send_Keys"))
					{
					if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
					{
					String TextValue = sh.getCell(1, i).getContents();	
					System.out.println(TextValue);
					System.out.println("Entered Locator identification");
					WebElement elementlocator = _driver.findElement(By.xpath(locatorvalue+"/*[contains(@name,"+TextValue+")]"));
					System.out.println(elementlocator);
					wait.until(ExpectedConditions.visibilityOf(elementlocator));
					js.executeScript("arguments[0].scrollIntoView(true);",elementlocator);
					elementlocator.sendKeys(value);
					System.out.println("SendKeys");
				   }
					else
				{
						System.out.println("Element not available to enter text");
				}
					}
					else if(keyword.equalsIgnoreCase("Select_Radio"))
					    {
					    System.out.println("Entered radio selection");	
						WebElement RadioValue = _driver.findElement(By.xpath(locatorvalue+"/*[contains(text(),"+value+")]/parent::*"));
						js.executeScript("arguments[0].click();", RadioValue);
					    System.out.println("Radio option clicked");
					    }
		   else if(keyword.equalsIgnoreCase("Send_Keys_JS"))
			    {
					String TextValue = sh.getCell(1, i).getContents();
					WebElement elementlocator = _driver.findElement(By.xpath(locatorvalue));
					wait.until(ExpectedConditions.visibilityOf(elementlocator));
					elementlocator.clear();
					//elementlocator.sendKeys("4222");
					js.executeScript("arguments[0].scrollIntoView(true);", elementlocator);
					/*js.executeScript("arguments[0].click();", elementlocator);*/
					js.executeScript("document.getElementById('cardNumber').value = "+value+";");
				}		
		   else if(keyword.equalsIgnoreCase("Send_Keys"))
				{
				
				WebElement elementlocator = _driver.findElement(By.xpath(locatorvalue));
				wait.until(ExpectedConditions.visibilityOf(elementlocator));
				elementlocator.clear();
				elementlocator.sendKeys(value);
			    Thread.sleep(1000);
			}
		   else if(keyword.equalsIgnoreCase("Send_Keys_Enter"))
			{
			
			WebElement elementlocator = _driver.findElement(By.xpath(locatorvalue));
			wait.until(ExpectedConditions.visibilityOf(elementlocator));
			elementlocator.clear();
			elementlocator.sendKeys(value);
		    Thread.sleep(1000);
		    elementlocator.sendKeys(Keys.ENTER);
		}
		   else if(keyword.equalsIgnoreCase("Tab_Enter"))
		   {
			WebElement element = _driver.findElement(By.xpath(locatorvalue));
			
			element.sendKeys(Keys.TAB);
		
			element.sendKeys(Keys.ENTER);
		   }
		   else if(keyword.equalsIgnoreCase("Logout_WithTab"))
		   {
			WebElement element = _driver.findElement(By.xpath(locatorvalue));
			element.click();
			element.sendKeys(Keys.TAB);
			element.sendKeys(Keys.TAB);
			element.sendKeys(Keys.TAB);
			element.sendKeys(Keys.TAB);
			
			element.sendKeys(Keys.ENTER);
		   }
		   else if(keyword.equalsIgnoreCase("Tab_SendKeys"))
		   {
			WebElement element = _driver.findElement(By.xpath(locatorvalue));
			element.sendKeys(Keys.TAB);
			Thread.sleep(3000);
			element.sendKeys(value);
		   }
		   else if(keyword.equalsIgnoreCase("Scroll_Down"))
		   {
			js.executeScript("window.scrollBy(0,250)", "");
			System.out.println("Scrolled down");
		   }
		   else if (keyword.equalsIgnoreCase("Scroll_Into")) 
			{
				WebElement element = _driver.findElement(By.xpath(locatorvalue));
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				System.out.println("Scrolled down");
			}
		   else if (keyword.equalsIgnoreCase("Wait_Until")) 
			{
			   long number = Long.parseLong(value);
			   Thread.sleep(number);
			}
		    else if(keyword.equalsIgnoreCase("Click"))
					{
					if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
					{
						           WebElement element = _driver.findElement(By.xpath(locatorvalue));
						           wait.until(ExpectedConditions.elementToBeClickable(element));
					               System.out.println("Entered Click method");	
							       System.out.println("About to wait"); 
								   System.out.println("Waited");
							       //js.executeScript("arguments[0].click();", element);
							       element.click();
							       System.out.println("Clicked");
					               Thread.sleep(2000);
				    }
					}
		   else if(keyword.equalsIgnoreCase("Submit"))
			{
			if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
			{
				           WebElement element = _driver.findElement(By.xpath(locatorvalue));
				           wait.until(ExpectedConditions.elementToBeClickable(element));
			               System.out.println("Entered Click method");	
					       System.out.println("About to wait"); 
						   System.out.println("Waited");
					       //js.executeScript("arguments[0].click();", element);
					       element.submit();
					       System.out.println("Clicked");
			               Thread.sleep(3000);
		    }
			}
				else if(keyword.equalsIgnoreCase("Validate_isEnabled"))
					{
					if(_driver.findElement(By.xpath(locatorvalue)).isEnabled())
					{
					System.out.println("Element is enabled");	
					}
					else
					{
					System.out.println("Element is disabled");
					}
					}
				else if(keyword.equalsIgnoreCase("isEnabled_Enter"))
				{
				WebElement element = _driver.findElement(By.xpath(locatorvalue));
				if(element.isEnabled())
				{
				System.out.println("Element is enabled");
				element.sendKeys(Keys.ENTER);
				}
				else
				{
				System.out.println("Element is disabled");
				}
				}
				else if(keyword.equalsIgnoreCase("Check_CheckboxValue"))
					 
				{
				   WebElement SelectValue = _driver.findElement(By.xpath(locatorvalue+"[contains(text(),"+value+")]"));
				   wait.until(ExpectedConditions.visibilityOf(SelectValue));
				   js.executeScript("arguments[0].click();", SelectValue);
				   System.out.println("Clicked");
				}
					else if(keyword.equalsIgnoreCase("Select_Value_JS"))
					 
					{
					   WebElement SelectValue = _driver.findElement(By.xpath(locatorvalue+"[contains(text(),"+value+")]"));
					   wait.until(ExpectedConditions.visibilityOf(SelectValue));
					   js.executeScript("arguments[0].click();", SelectValue);
					   System.out.println("Clicked");
					}
					else if(keyword.equalsIgnoreCase("Select_HSCode_JS"))
						 
					{
					   WebElement SelectValue = _driver.findElement(By.xpath(locatorvalue+"[contains(text(),"+value+")]"));
					   wait.until(ExpectedConditions.visibilityOf(SelectValue));
					   js.executeScript("arguments[0].click();", SelectValue);
					   System.out.println("Clicked");
					}
					
					else if(keyword.equalsIgnoreCase("Select_Value_JSEnter"))
						 
					{
					   WebElement SelectValue = _driver.findElement(By.xpath(locatorvalue+"[contains(text(),"+value+")]"));
					   wait.until(ExpectedConditions.visibilityOf(SelectValue));
					   js.executeScript("arguments[0].click();", SelectValue);
					   System.out.println("Clicked");
					   SelectValue.sendKeys(Keys.ENTER);
					}
					else if(keyword.equalsIgnoreCase("Check_EmailExists"))
						 
					{
					   WebElement SelectValue = _driver.findElement(By.xpath(locatorvalue+"[contains(text(),"+value+")]"));
					   wait.until(ExpectedConditions.visibilityOf(SelectValue));
					   js.executeScript("arguments[0].click();", SelectValue);
					   System.out.println("Clicked");
					   SelectValue.sendKeys(Keys.ENTER);
					}
					else if(keyword.equalsIgnoreCase("Select_Value"))
					{
					  if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
					  {
					   WebElement SelectValue = _driver.findElement(By.xpath(locatorvalue+"[contains(text(),"+value+")]"));
				       wait.until(ExpectedConditions.visibilityOf(SelectValue));
				       SelectValue.click();
				       Thread.sleep(1000);
					   /*Select select = new Select( _driver.findElement(By.xpath("//md-select-menu/md-content/md-option")));
					   select.selectByVisibleText(value);*/
					  }
					  else 
						{
						System.out.println("Element not available");
						}
					} 
					
					else if(keyword.equalsIgnoreCase("Navigate_Back"))
					{
					 _driver.navigate().back();
					}
					else if(keyword.equalsIgnoreCase("Navigate_Refresh"))
					{
					 _driver.navigate().refresh();
					}
					else if(keyword.equalsIgnoreCase("Click_JS"))
					{
						if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
						{
						WebElement element = _driver.findElement(By.xpath(locatorvalue));
						wait.until(ExpectedConditions.elementToBeClickable(element));
						Thread.sleep(3000);
						js.executeScript("arguments[0].click();", element);
						System.out.println("Clicked");
						Thread.sleep(2000);
						}
					}
					else if(keyword.equalsIgnoreCase("Click_Enter"))
					{
					 WebElement element = _driver.findElement(By.xpath(locatorvalue));
					 wait.until(ExpectedConditions.elementToBeClickable(element));
					 element.click();
					 Thread.sleep(2000);
					 element.sendKeys(Keys.ENTER);
					}
					else if(keyword.equalsIgnoreCase("Click_SendKeys"))
					{
					 WebElement element = _driver.findElement(By.xpath(locatorvalue));
					 wait.until(ExpectedConditions.elementToBeClickable(element));
					 element.click();
					 element.sendKeys(value);
					}
					else if (keyword.equalsIgnoreCase("Validate_Text")) 
					{
						 if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
					       {
						   WebElement element = _driver.findElement(By.xpath(locatorvalue));
						   String ActualPageTitle = element.getText();
					       String TrimActualPageTitle = ActualPageTitle.trim();
					       System.out.println(TrimActualPageTitle);
					       value.trim();
						   System.out.println("Actual Page Title is : "+TrimActualPageTitle);
						   System.out.println("Expected Title should be : "+value);
					       Assert.assertEquals(TrimActualPageTitle, value);
					       }
					}
					else if (keyword.equalsIgnoreCase("Validate_WithText")) 
					{
						 if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
					       {
						   WebElement element =  _driver.findElement(By.xpath(locatorvalue+"/*[contains(text(),"+value+")]"));
						   String ActualPageTitle = element.getText();
					       String TrimActualPageTitle = ActualPageTitle.trim();
					       System.out.println(TrimActualPageTitle);
					       value.replaceAll("\"", "");
						   System.out.println("Actual Page Title is : "+TrimActualPageTitle);
						   System.out.println("Expected Title should be : "+value);
					       Assert.assertEquals(TrimActualPageTitle, value);
					       }
					}
					else if (keyword.equalsIgnoreCase("Click_ButtonText")) 
					{
					          System.out.println("Entered Click method");
					          WebElement ButtonValue = _driver.findElement(By.xpath(locatorvalue+"/*[contains(text(),"+value+")]"));
					          System.out.println(ButtonValue);
					          wait.until(ExpectedConditions.elementToBeClickable(ButtonValue));
					          ButtonValue.click();
					          System.out.println("Clicked");
					          Thread.sleep(7000);
					}
					else if (keyword.equalsIgnoreCase("Select_BOProduct")) 
					{
					          System.out.println("Entered Click method");
					          WebElement ButtonValue = _driver.findElement(By.xpath(locatorvalue+"/*[contains(text(),"+value+")]"+Product));
					          System.out.println(ButtonValue);
					          wait.until(ExpectedConditions.elementToBeClickable(ButtonValue));
					          ButtonValue.click();
					          System.out.println("Clicked");
					          Thread.sleep(7000);
					}
					else if(keyword.equalsIgnoreCase("Get_Value"))
					{
					 WebElement element = _driver.findElement(By.xpath(locatorvalue));
					 String AttributeValue = element.getAttribute("value");
					 System.out.println("Actual Text Value = "+AttributeValue );
					 System.out.println("Expected Text Value = "+value);
					 Assert.assertEquals(AttributeValue, value);
					}
					else if(keyword.equalsIgnoreCase("Execute_SQL_Query"))
					{
						   
							Class.forName("com.mysql.jdbc.Driver");  
							Connection con = DriverManager.getConnection(InitProperties.getConfigProperty(InitProperties.DBURL),InitProperties.getConfigProperty(InitProperties.USERNAME), InitProperties.getConfigProperty(InitProperties.PASSWORD));  
							//here sonoo is database name, root is username and password  
							PreparedStatement stmt = con.prepareStatement(sqlquery);
							System.out.println(stmt+ "Statement");
							String UserID = sh.getCell(j, i).getContents();
							System.out.println(UserID);
							stmt.setString(1, UserID);
							stmt.executeUpdate();
							Thread.sleep(3000);
//							System.out.println(rs+ "RS");
							
					}
					else if(keyword.equalsIgnoreCase("Check_PageElements"))
					{
						//WebElement element = _driver.findElement(By.xpath(locatorvalue));
						Eyes eyes = new Eyes();
						eyes.setApiKey(InitProperties.getConfigProperty(InitProperties.APPLITOOLSAPIKEY));
						//eyes.open(_driver, "TradeLeaves", "TradeLeaves-Homepage", new RectangleSize(InitProperties.SCREEN_WIDTH, InitProperties.SCREEN_HEIGHT));
						//eyes.open(_driver, locatorvalue, elementName, new RectangleSize(InitProperties.SCREEN_WIDTH, InitProperties.SCREEN_HEIGHT));
						eyes.setForceFullPageScreenshot(true);
						eyes.setStitchMode(StitchMode.CSS);
							
						//eyes.setHideScrollbars(true);
					      eyes.open(_driver, elementName, value);
						//eyes.open(_driver, elementName, value, new RectangleSize(InitProperties.SCREEN_WIDTH, InitProperties.SCREEN_HEIGHT));
						//eyes.checkRegion(element);
						eyes.checkWindow(value);
						eyes.close();
					}
					
					else if(keyword.equalsIgnoreCase("Enter_Confirmation_Code"))
					 {
			            //wait.until(ExpectedConditions.visibilityOf(element));
			            String confirmationCode = null;
						Class.forName("com.mysql.jdbc.Driver");  
			            //Class.forName("something.jdbc.driver.YourFubarDriver");
			            Connection con = DriverManager.getConnection(InitProperties.getConfigProperty(InitProperties.DBURL),InitProperties.getConfigProperty(InitProperties.USERNAME), InitProperties.getConfigProperty(InitProperties.PASSWORD));  
						//here sonoo is database name, root is username and password  
						PreparedStatement stmt = con.prepareStatement(sqlquery);
						String UserID = sh.getCell(j, i).getContents();
						System.out.println(UserID);
						stmt.setString(1, UserID);
						ResultSet rs = stmt.executeQuery(); 
						WebElement element = _driver.findElement(By.xpath(locatorvalue));
						while(rs.next())
						{
							confirmationCode = rs.getString(1);
							System.out.println(rs.getString(1)); 
						}
					if(_driver.findElement(By.xpath(locatorvalue)).isDisplayed())
					{
					element.clear();
					element.sendKeys("");
					element.sendKeys(confirmationCode);
}					
}
					System.out.println("********************************************************");
}
			}
		}
}
			