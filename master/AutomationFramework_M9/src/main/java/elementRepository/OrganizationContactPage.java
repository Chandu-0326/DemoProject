package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationContactPage {

	public OrganizationContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement organizationNameTextfield;
	
	@FindBy(name = "industry")
	private WebElement industryDropdown;
	
	@FindBy(xpath = "//option[@value='Chemicals']")
	private WebElement selectChemicals;
	
	@FindBy(xpath = "//option[@value='Energy']")
	private WebElement selectEnergy;
	
	@FindBy(name = "accounttype")
	private WebElement typeDropdown;
	
	@FindBy(xpath = "//option[@value='Customer']")
	private WebElement selectCustomer;	

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement orgSaveButton;
	
	public WebElement getOrganizationNameTextfield() {
		return organizationNameTextfield;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}
	
	public WebElement getSelectChemicals() {
		return selectChemicals;
	}
	
	public WebElement getSelectEnergy() {
		return selectEnergy;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getSelectCustomer() {
		return selectCustomer;
	}
	
	public WebElement getOrgSaveButton() {
		return orgSaveButton;
	}

	
	
	
	
}
