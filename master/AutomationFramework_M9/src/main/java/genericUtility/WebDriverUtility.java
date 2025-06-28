package genericUtility;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Durations;

public class WebDriverUtility {
	/**
	 * This method is used to maximize the window provided driver
	 * 
	 * @param driver
	 */
	public void ToMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to minimize window provided driver
	 * 
	 * @param driver
	 */
	public void ToMinimize(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will wait until the element loaded in webpage (Implicitly wait)
	 * @param driver
	 */
	public void waitForElement(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	/**
	 * This method will wait until the element is clickable provied with driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void elementToBeClickAble(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will wait until the element is visible provided with driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void visibilityOfTheElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to handle dropdown using index
	 * 
	 * @param element
	 * @param index
	 */
	public void toHandleDropdown(WebElement element, int index) {
		Select sd = new Select(element);
		sd.selectByIndex(index);
	}
	
	/**
	 * This method is used to handle dropdown using value
	 * 
	 * @param element
	 * @param value
	 */
	public void toHandleDropdown(WebElement element, String value) {
		Select sd = new Select(element);
		sd.deselectByValue(value);
	}
	
	/**
	 * This method is used to handle dropdown using text
	 * 
	 * @param text
	 * @param element
	 */
	public void toHandleDropdown(String text, WebElement element) {
		Select sd = new Select(element);
		sd.deselectByVisibleText(text);
	}
	
	/**
	 * This method is used to handle frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void toHandleFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to handle frame using id or name
	 * 
	 * @param driver
	 * @param id_name
	 */
	public void toHandleFrame(WebDriver driver, String id_name) {
		driver.switchTo().frame(id_name);
	}
	
	/**
	 * This method is used to handle frame using webelement
	 * 
	 * @param driver
	 * @param element
	 */
	public void toHandleFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to Switch back to the main page
	 * 
	 * @param driver
	 */
	public void toSwitchBackFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to mouse hover provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toMouseHover(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to perform right click action provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void toRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}
	
	/**
	 * This method is used to perform double click action provided driver and element
	 * 
	 * @param driver
	 * @param element
	 */
	public void doubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	/**
	 * This method is used to perform drag and drop action provided driver and element
	 * 
	 * @param driver
	 * @param src
	 * @param target
	 */
	public void toDragAndDrop(WebDriver driver, WebElement src, WebElement target) {
		Actions action = new Actions(driver);
		action.dragAndDrop(src,target).perform();
	}
	
	/**
	 * This method is used to handle alert popup by accepting it
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to handle alert popup by dismissing it
	 * 
	 * @param driver
	 */
	public void toHandleAlertPopupByDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method is used to handle alert popup by capturing text and accepting it
	 * 
	 * @param driver
	 * @return
	 */
	public String toHandleAlertPopupAndCaptureText(WebDriver driver) {
		Alert alertpopup = driver.switchTo().alert();
		String popupMsg = alertpopup.getText();
		alertpopup.accept();
		return popupMsg;
	}
	
	/**
	 * This method is used to take screenshot of of an entire webpage provided with driver and screenshot
	 * 
	 * @param driver
	 * @param screenshotname
	 * @throws IOException
	 */
	public String toTakesScreenshot(WebDriver driver, String screenshotname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File src = new File("./errorShot/" + screenshotname + ".jpeg");
		FileHandler.copy(temp, src);
		String completePath = src.getAbsolutePath();
		return completePath;
	}
	
	/**
	 * This method is used to switch driver control to window provided driver and partial title
	 * 
	 * @param driver
	 * @param partialTitle
	 */
	public void toSwitchWindow(WebDriver driver, String partialTitle) {
		Set<String> allIds = driver.getWindowHandles();
		for(String id : allIds) {
			String titleOfWebpage = driver.switchTo().window(id).getTitle();
			if(titleOfWebpage.contains(partialTitle)) {
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
