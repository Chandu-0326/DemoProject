package Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestScript5 {

	public static void main(String[] args) {

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

		// Step 4: Click on create contact look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Step 5: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("P");

		// Step 6: Select the organization from organization look
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img")).click();
		String parentId = driver.getWindowHandle();
		Set<String> allIds = driver.getWindowHandles();
		allIds.remove(parentId);
		for (String id : allIds) {
			driver.switchTo().window(id);
		}
		driver.findElement(By.linkText("Embassy")).click();
		driver.switchTo().window(parentId);
		

		// Step 7: Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains("chandana")) {
			System.out.println(lastname + "-----" + "passed");
		} else {
			System.out.println(lastname + "-----" + "failed");
		}

		// Step 8: Logout of application
		WebElement one = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		Actions action = new Actions(driver);
		action.moveToElement(one).perform();

		// Step 9: close the browser
		driver.quit();

	}

}
