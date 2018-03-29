

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebsiteCreate {
  private WebDriver driver;
  private String baseUrl;
  private String expectedUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  private int steps = 4;
  
  //variables used to create the website
  
  //homepage variables
  private String homepageTitle = "test";
  private String homepageTopic = "test";
  
  private boolean homeShare = false;
  private boolean homePromote = false;
  private boolean homeEducate = false;
  private boolean homeSell = false;
  private boolean homeShowcase = false;
  
  private int homeExperience = 0;
  
  //themes variables
  private int theme = 0; //
  
  //domain variables
  private String domainSearch = "example";
  private String domainName = "";
  
  //plan variables
  private int plan = 0; //
  
  //user variables
  private String userEmail = "";
  private String userName = "";
  private String userPass = "";
  
  WebsiteCreate( 
		  String hpTitleInput, String hpTopicInput, boolean hpShareInput, boolean hpPromoteInput, boolean hpEducateInput, 
		  boolean hpSellInput, boolean hpShowcaseInput, int hpExpInput, int themeInput, String dSearchInput, 
		  String dNameInput, int planInput, String userEmailInput, String userNameInput, String userPassInput ){
	  
      //homepage variables
	  homepageTitle = hpTitleInput;
	  homepageTopic = hpTopicInput;
	  
	  homeShare = hpShareInput;
	  homePromote = hpPromoteInput;
	  homeEducate = hpEducateInput;
	  homeSell = hpSellInput;
	  homeShowcase = hpShowcaseInput;
	  
	  homeExperience = hpExpInput;
	  
	  //themes variables
	  theme = themeInput; //
	  
	  //domain variables
	  domainSearch = dSearchInput;
	  domainName = dNameInput;
	  
	  //plan variables
	  plan = planInput; //
	  
	  //user variables
	  userEmail = userEmailInput;
	  userName = userNameInput;
	  userPass = userPassInput;
  }
  

  public void setUp() throws Exception {
	try {
      baseUrl = "https://wordpress.com";
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\nishj\\Documents\\QA\\chromedriver_win32\\chromedriver.exe");
	  driver = new ChromeDriver();
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    
      driver.get(baseUrl);
      System.out.println("Website Loaded");
      Thread.sleep(3000);
      
      //press the "GetStarted" button
      driver.findElement(By.id("hero-cta")).click();
      Thread.sleep(3000);
      
      screenHomepage();
    }catch (Error e) {
		  
	}
  }

  //
  public void screenHomepage() throws Exception {
	  try {
	      //verify that this is the correct page url
	      expectedUrl = "https://wordpress.com/start/about?ref=homepage";
	      verifyPage(expectedUrl);
	      System.out.println("Homepage Page Loaded");
	      Thread.sleep(3000);
	      
	      //pass in the homepageTitle and homepageTopic 
	      driver.findElement(By.id("siteTitle")).clear();
	      driver.findElement(By.id("siteTitle")).sendKeys(homepageTitle);
	      driver.findElement(By.id("siteTopic")).clear();
	      driver.findElement(By.id("siteTopic")).sendKeys(homepageTopic);
	  
	      //select all goals for the site and determine the number of steps
	      if(homeSell) {
		      steps = 5;
		      driver.findElement(By.id("sell")).click();
	      }
	      if(homeShare) {
			  steps = 4;
		      driver.findElement(By.id("share")).click();
	      }
	      if(homePromote) {
		      steps = 4;
		      driver.findElement(By.id("promote")).click();
	      }
	      if(homeEducate) {
		      steps = 4;
		      driver.findElement(By.id("educate")).click();
	      }
	      if(homeShowcase) {
		      steps = 4;
	     	  driver.findElement(By.id("showcase")).click();
	      }
	  
	      //select website experience
	  
	      //press the "continue" button
	      driver.findElement(By.cssSelector("div.about__submit-wrapper > button.button.is-primary")).click();
	      Thread.sleep(3000);
	      
	      verifyHomepage();
	      if(steps == 5) {
		      screenThemes();
	      } else {
		      screenDomains();
	      }
	  }catch (Error e) {
		  
	  }
  }
  
  //
  public void screenThemes() throws Exception {
	  try {
		  //verify that this is the correct page url
	      expectedUrl = "https://wordpress.com/start/about?ref=homepage";
	      verifyPage(expectedUrl);
	      System.out.println("Themes Page Loaded");
	      Thread.sleep(3000);
	  
	      if(theme == 1) {
		      // if theme equals 1 use Shoreditch theme
		      driver.findElement(By.linkText("Pick")).click();
	      } else if(theme == 2) {
		      // if theme equals 2 use Dara theme
		      driver.findElement(By.xpath("//div[@id='primary']/span/span/div/div/div/div/div[2]/div/a")).click();
	      } else if(theme == 3) {
		      // if theme equals 2 use Karuna theme
		      driver.findElement(By.xpath("//div[@id='primary']/span/span/div/div/div/div/div[3]/div/a")).click();
	      } else {
		      // if theme does not equal 1, 2 or 3 skip theme selection
		      driver.findElement(By.xpath("//button[@type='button']")).click();
	      }
	  
	      verifyThemes();
	      screenDomains();
	  }catch (Error e) {
		  
	  }
	  
  }
  
  //
  public void screenDomains() throws Exception {
	  try {
		 //verify that this is the correct page url
	     expectedUrl = "https://wordpress.com/start/domains";
	     verifyPage(expectedUrl);
	     System.out.println("Domains Page Loaded");
	  
	      if(domainName.isEmpty()) {
	    	  /**/
	    	  
		      //if domainName is empty use domainSearch value to find domain name 
		      driver.findElement(By.id("search-component-1")).clear();
		      driver.findElement(By.id("search-component-1")).sendKeys(domainSearch);
		      
		      driver.findElement(By.cssSelector("span.segmented-control__text")).click();
		      Thread.sleep(3000);
		      
		      //Select top recommendation
		      driver.findElement(By.xpath("//button[@type='button']")).click();
		  
		      verifyDomain();
		      screenPlans();
	      } else {
		      //use domainName if not empty
		      driver.findElement(By.linkText("Already own a domain?")).click();
		  
		      verifyDomain();
		      screenMyDomain();
	      } 
	  }catch (Error e) {
		  
	  }
	  
  }
  
  //
  public void screenMyDomain() throws Exception {
	  expectedUrl = "https://wordpress.com/start/domains/transfer";
	  verifyPage(expectedUrl);
	  System.out.println("MyDomain Page Loaded");
	  
	  //
	  driver.findElement(By.cssSelector("input.form-text-input")).clear();
	  driver.findElement(By.cssSelector("input.form-text-input")).sendKeys(domainName);
      driver.findElement(By.xpath("//button[@type='button']")).click();
	  
	  verifyMyDomain();
	  screenPlans();
  }
  
  // preform all 
  public void screenPlans() throws Exception {
	  //verify that this is the correct page url
	  expectedUrl = "https://wordpress.com/start/plans";
	  verifyPage(expectedUrl);
	  
	  
	  if(steps == 5) {
		  //if steps equals 5 use business plan
		  driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	  } else {
		  if(plan == 1) {
		      // if plan equals 1 use personal plan
		      driver.findElement(By.xpath("//button[@type='button']")).click();
	      } else if(plan == 2) {
		      // if plan equals 2 use premium plan
		      driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	      } else if(plan == 3) {
		      // if plan equals 3 use business plan
		      driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	      } else {
	          // if theme does not equal 1, 2 or 3 use free plan
		      driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
	      }
	  }
	  
	  verifyPlans();
	  screenUser();
  }
  
  public void screenUser() throws Exception {
	  expectedUrl = "https://wordpress.com/start/user";
	  verifyPage(expectedUrl);
	  
	  driver.findElement(By.id("email")).clear();
	  driver.findElement(By.id("email")).sendKeys(userEmail);
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(userName);
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(userPass);
	  
	  verifyUser();
	  
	  //press continue button
	  //verify success
	  
	  tearDown();
  }
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  //Check the url to make sure it matches the expected url
  public void verifyPage(String expUrl) {
	  String currentUrl = driver.getCurrentUrl();
	  boolean result = currentUrl.equalsIgnoreCase(expUrl);
	  Assert.assertTrue(result);
  }
  
  // verify everything related to the homepage screen
  public void verifyHomepage() {
	  //check all images, javascript and json objects
  }
  
  //verify everything related to the domain screen
  public void verifyDomain() {
	//check all images, javascript and json objects
  }
  
  //verify everything related to the plans screen
  public void verifyPlans() {
	//check all images, javascript and json objects
  }
  
  //verify everything related to the user screen
  public void verifyUser() {
	//check all images, javascript and json objects
  }
  
  //verify everything related to the themes screen
  public void verifyThemes() {
	//check all images, javascript and json objects
  }
  
  //verify everything related to the myDomain screen
  public void verifyMyDomain() {
	//check all images, javascript and json objects
  }
}
