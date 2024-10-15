package MarketingAccountPage;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Methods.GenericMethods;

public class ToCreateNewAccountLandingPage {

	@FindBy(xpath = "//input[@class='crmbutton small save']")
	private WebElement saveButton;

	@FindBy(xpath = "")
	private WebElement cancelButton;

	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement accountName;

	@FindBy(xpath = "//input[@name='website']")
	private WebElement webSiteName;

	@FindBy(xpath = "//input[@id='phone']")
	private WebElement mobileNumber;

	private GenericMethods gobj;

	public ToCreat_NewAccount_LandingPage (GenericMethods gobj) {
			this .gobj = gobj;
			PageFactory.initElements(gobj.getDriverValue(), this);
		}

	public ToValidateCreatedNewAccount clickOnSaveButton() {

		gobj.ToClickAnyButton(saveButton, "Save Button of created Account");

		ToValidateCreatedNewAccount validateAccount = new ToValidateCreatedNewAccount(gobj);
		return validateAccount;

	}

	public void clickOnCanceleButton() {

		gobj.ToClickAnyButton(cancelButton, "Cancel Button of created Account ");

	}

	public void fillAllInputBoxes(Map<String, String> mapData) {

		String accountValue = mapData.get("AccountValue");
		String webSiteNameValue = mapData.get("WebSiteName");
		String mobileNumberValue = mapData.get("Mobile_Number");

		gobj.toSendValueInInputBox(accountName, accountValue, "Account Name box");

		gobj.toSendValueInInputBox(webSiteName, webSiteNameValue, "WebSiteName box");

		gobj.toSendValueInInputBox(mobileNumber, mobileNumberValue, "mobile Number Box");

	}
}
