package Practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import elementRepository.ContactInfoPage;
import elementRepository.ContactPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import elementRepository.LoginPage;
import genericUtility.ExcelFileUtilit;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGuAndPom {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtilit eutil = new ExcelFileUtilit();
		WebDriverUtility wutil = new WebDriverUtility();

		// To read data from property file
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// To read Data from excel file
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);

		// Test Scripts
		// Step 1: launch the browser
		WebDriver driver = null; // (If we don't initialize means we can't use)
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		wutil.ToMaximize(driver); // WebDriverUtility
		wutil.waitForElement(driver); // WebDriverUtility
		driver.get(URL);
		
		//Step 2: Login to application with valid credentials
		LoginPage lp = new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginbutton().click();
		
		//Step 3: Navigate to contact link
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		
		//Step 4: Click on create contact look up image
		ContactPage cp = new ContactPage(driver);
		cp.getLookupimage().click();
		
		//Step 5: Create contact with mandatory field
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		
		//Step 6: Save and verify
		ccp.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderText().getText();
		if(lastname.contains(LASTNAME)) {
			System.out.println(lastname + "---Passed");
		} else {
			System.out.println(lastname + "---Failed");
		}
		
		//Step 7: Logout of appication
		wutil.toMouseHover(driver, hp.getLogoutEle());
		hp.getSingoutLink().click();
		
		//Step 8: Close browser
		driver.quit();

	}

}
