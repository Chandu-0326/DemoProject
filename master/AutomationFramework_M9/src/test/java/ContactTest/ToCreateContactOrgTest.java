package ContactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;

import elementRepository.ContactPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtilit;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactOrgTest extends BaseClass {
	
	@Test(groups="smoke")
	public void toCreateContact_003() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getLookupimage().click();
		ExcelFileUtilit eutil = new ExcelFileUtilit();
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2);
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getOrgLookUpImg().click();
		
		//Switching the window to another window
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.toSwitchWindow(driver, "Accounts"); //switching to child window
		ccp.getSelectOrgName().click();
		wutil.toSwitchWindow(driver, "Contacts"); //switching back to parent window
		
		ccp.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderText().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));
	}
}




