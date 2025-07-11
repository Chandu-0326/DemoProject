package genericUtility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptWithDDTandGU {

	public static void main(String[] args) throws IOException {

		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtilit eutil = new ExcelFileUtilit();

		// To Read data from propertyfile
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		
		//To Read Data from Excel file
		eutil.toReadDataFromExcel("Contacts", 1, 2);

		// Step 1: launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

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
		WebElement one = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		Actions action = new Actions(driver);
		action.moveToElement(one).perform();

		// Step 8: close the browser
		driver.quit();

	}

}
