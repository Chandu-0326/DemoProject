package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {

	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastnameTextfield;
	
	@FindBy(xpath = "//input[@name='account_id']/following-sibling::img")
	private WebElement orgLookUpImg;
	
	@FindBy(linkText = "Embassy")
	private WebElement selectOrgName;
	
	@FindBy(xpath = "//input[@class='crmButton small save']")
	private WebElement saveButton;

	public WebElement getLastnameTextfield() {
		return lastnameTextfield;
	}

	public WebElement getOrgLookUpImg() {
		return orgLookUpImg;
	}
	
	public WebElement getSelectOrgName() {
		return selectOrgName;
	}
	
	public WebElement getSaveButton() {
		return saveButton;
	}

	
	
	
	
	
}
