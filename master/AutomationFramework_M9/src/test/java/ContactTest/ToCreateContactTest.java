package ContactTest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {

	@Test(groups="smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getLookupimage().click();
		JavaUtility jutil = new JavaUtility();
		ExcelFileUtilit eutil = new ExcelFileUtilit();
		String LASTNAME = eutil.toReadDataFromExcel("Contacts", 1, 2) + jutil.toGenerateRandomNumbers();
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		Assert.fail(); //fail the test script
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderText().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));
		
		

	}

}
