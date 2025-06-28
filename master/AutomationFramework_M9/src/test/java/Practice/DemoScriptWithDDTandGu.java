package Practice;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtilit;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTandGu {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtilit eutil = new ExcelFileUtilit();
		WebDriverUtility wutil = new WebDriverUtility();

		// To read data from property file
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		// To read data from excel file
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);

		// Step 1: launch the browser
		WebDriver driver = null; //(If we don't initialize means we can't use)
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		
		wutil.ToMaximize(driver); //WebDriverUtility
		wutil.waitForElement(driver); //WebDriverUtility
		

		// Step 2: Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();

		// Step 3: Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4: Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 5: Create contact with mandatory field
		driver.findElement(By.name("lastname")).sendKeys("chandana");

		// Step 6: Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains("chandana")) {
			System.out.println(lastname + "-----" + "passed");
		} else {
			System.out.println(lastname + "-----" + "failed");
		}

		// Step 7: Logout of application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logout); //WebDriverUtility
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step 8: close the browser
		driver.close();
		
		

	}

}
