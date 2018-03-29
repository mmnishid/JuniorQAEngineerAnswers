package com.wordpress.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WordpressWebsite {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	try {
      baseUrl = "http://babelstone.co.uk/Unicode/whatisit.html";
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\nishj\\Documents\\QA\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    
      driver.get(baseUrl);
      System.out.println("Website Loaded");
      Thread.sleep(3000);
      
      testWordpressWebsite();
      
      driver.quit();
      //driver.quit();
    }catch (Error e) {
		  
	}
  }

  @Test
  public void testWordpressWebsite() throws Exception {
	try { 
      //
      driver.findElement(By.id("edittextin")).clear();
      driver.findElement(By.id("edittextin")).sendKeys("ABC");
      System.out.println("ABC entered");
      Thread.sleep(3000);
      
      //
      driver.findElement(By.id("convertbutton")).click();
      System.out.println("Identify button pressed");
      Thread.sleep(3000);
      
      String output = driver.findElement(By.id("edittextout")).getAttribute("value");
      System.out.println("Output:\n" + output);
      Assert.assertEquals("U+0041 : LATIN CAPITAL LETTER A\n" + 
      		              "U+0042 : LATIN CAPITAL LETTER B\n" + 
      		              "U+0043 : LATIN CAPITAL LETTER C\n" + 
      		              "", output);
      System.out.println("Returned Correct Value");
	} catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  //
  public void incorrectTestCase001() throws Exception {
	try { 
      //
      driver.findElement(By.id("edittextin")).clear();
      driver.findElement(By.id("edittextin")).sendKeys("ABC");
      System.out.println("ABC entered");
      Thread.sleep(3000);
      
      //
      driver.findElement(By.id("convertbutton")).click();
      System.out.println("Identify button pressed");
      Thread.sleep(3000);
      
      String output = driver.findElement(By.id("edittextout")).getAttribute("value");
      System.out.println("Output:\n" + output);
      Assert.assertEquals("U+0041 : LATIN CAPITAL LETTER A\n" + 
      		              "U+0042 : LATIN CAPITAL LETTER B\n" + 
      		              "U+0043 : LATIN CAPITAL LETTER C\n" + 
      		              "", output);
      System.out.println("Returned Correct Value");
	} catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }
  
  public static void main(String[] args) {
    try {
    	WordpressWebsite juniorQA2 = new WordpressWebsite();
		juniorQA2.setUp();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
