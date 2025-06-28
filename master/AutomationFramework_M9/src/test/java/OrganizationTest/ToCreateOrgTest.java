package OrganizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;
import elementRepository.ContactPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import elementRepository.OrganizationContactPage;
import elementRepository.OrganizationInfoPage;
import elementRepository.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtilit;
import genericUtility.JavaUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateOrgTest extends BaseClass {
	 
	 @Test(groups="Regression")
	 public void toCreateContactOrg_002() throws EncryptedDocumentException, IOException {
		 HomePage hp = new HomePage(driver);
		 hp.getOrganizationLink().click();
		 OrganizationPage op = new OrganizationPage(driver);
		 op.getOrganizationLookupimage().click();
		 JavaUtility jutil = new JavaUtility(); // It provide random numbers
		 ExcelFileUtilit eutil = new ExcelFileUtilit(); 
		 String ORGNAME = eutil.toReadDataFromExcel("Organization", 1, 2)+ jutil.toGenerateRandomNumbers();
		 OrganizationContactPage ocp = new OrganizationContactPage(driver);
		 ocp.getOrganizationNameTextfield().sendKeys(ORGNAME);
		 ocp.getOrgSaveButton().click();
		 OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		 String orgname = oip.getHeaderText().getText();
		 Assert.assertTrue(orgname.contains(ORGNAME));
	}
}
