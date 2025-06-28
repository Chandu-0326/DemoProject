package OrganizationTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.HomePage;
import elementRepository.OrganizationContactPage;
import elementRepository.OrganizationInfoPage;
import elementRepository.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtilit;
import genericUtility.JavaUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateOrgWithTypeTest extends BaseClass {
	
	@Test(groups="Regression")
	public void toCreateOrgWithType_005() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrganizationLookupimage().click();
		JavaUtility jutil = new JavaUtility();
		ExcelFileUtilit eutil = new ExcelFileUtilit();
		String ORGNAME = eutil.toReadDataFromExcel("Organization", 1, 2) + jutil.toGenerateRandomNumbers();
		OrganizationContactPage ocp = new OrganizationContactPage(driver);
		ocp.getOrganizationNameTextfield().sendKeys(ORGNAME);
		ocp.getIndustryDropdown().click();
		ocp.getSelectEnergy().click();
		ocp.getTypeDropdown().click();
		ocp.getSelectCustomer().click();
		ocp.getOrgSaveButton().click();
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String orgname = oip.getHeaderText().getText();
		Assert.assertTrue(orgname.contains(ORGNAME));
		
	}
	
}
