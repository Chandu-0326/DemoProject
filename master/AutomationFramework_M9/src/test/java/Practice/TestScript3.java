package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestScript3 {

	public static void main(String[] args) {

		//Step 1: launch the browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
		//Step 2: Login to application with valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
				
		//Step 3: Navigate to contacts link
		driver.findElement(By.linkText("Organizations")).click();
				
		//Step 4: Create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
		//Step 5: Create organization with mandatory field
		driver.findElement(By.name("accountname")).sendKeys("ParNets");
		
		//Step 6: Select chemicals in the industry dropdown
		WebElement indus = driver.findElement(By.name("industry"));
		
		Select dp = new Select(indus);
		dp.selectByVisibleText("Chemicals");
		
		//Step 7: Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(lastname.contains("chandana")) {
		System.out.println(lastname+"-----"+ "passed");
		} else {
		System.out.println(lastname+"-----"+ "failed");
		}
				
		//Step 8: Logout of application
		WebElement one = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
						
		Actions action = new Actions(driver);
		action.moveToElement(one).perform();
						
		//Step 9: close the browser
		driver.quit();
				

		
	}

}
