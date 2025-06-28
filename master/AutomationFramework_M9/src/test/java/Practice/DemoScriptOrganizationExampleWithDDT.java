package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptOrganizationExampleWithDDT {

	public static void main(String[] args) throws IOException {

		FileInputStream pfis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop = new Properties();
		prop.load(pfis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");

		// Read Data from excel file
		FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\testData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String LASTNAME = wb.getSheet("organization").getRow(1).getCell(2).toString();

		// Step 1 : Launch Browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		
		// Step 2: Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step 3: Navigate to contacts link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4: Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Random(It is a class in java, It will generate random values)
		Random r = new Random();
		int random = r.nextInt(1000);

		// Step 5: Create organization with mandatory field
		driver.findElement(By.name("accountname")).sendKeys(LASTNAME);

		// Step 6: Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains(LASTNAME)) {
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
