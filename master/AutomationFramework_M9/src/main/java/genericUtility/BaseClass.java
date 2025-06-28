package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import elementRepository.LoginPage;

public class BaseClass {
	PropertyFileUtility putil = new PropertyFileUtility();
	ExcelFileUtilit eutil = new ExcelFileUtilit();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver; //Listeners

	@BeforeSuite(groups= { "smoke", "Regression"})
	public void beforeSuiteConfig() {
		System.out.println("---DataBase Connection Established---");
	}

	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups= { "smoke", "Regression"})
	public void beforeClassConfig(/*String BROWSER*/) throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		sDriver = driver; //Listeners
		
		System.out.println("Browser got Lanched");
		wutil.ToMaximize(driver);
		System.out.println("Browser got Maximized");
		wutil.waitForElement(driver);
		driver.get(URL);
	}

	@BeforeMethod(groups= { "smoke", "Regression"})
	public void beforeMthodConfig() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginbutton().click();
		System.out.println("Logged in to Vtiger");
	}

	@AfterClass(groups= { "smoke", "Regression"})
	public void afterClassConfig() {
		System.out.println("Browser got closed");
		driver.quit();
	}

	@AfterSuite(groups= { "smoke", "Regression"})
	public void afterSuiteConfig() {
		System.out.println("---DataBase Connection DisConnected---");
	}

}
