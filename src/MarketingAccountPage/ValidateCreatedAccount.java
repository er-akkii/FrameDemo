package MarketingAccountPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Generic_Methods.GenericMethods;

public class ValidateCreatedAccount {

	private GenericMethods gobj;

	public ToValidateCreatedNewAccount(GenericMethods gobj) {
			this.gobj = gobj;
		}

	@FindBy(xpath = "//span[text()='7860xxxx77']")
	private WebElement elementName;

	public void toValidateNewCreatedAccount() {

		gobj.toValidatePageByTEXT(elementName, " Created Account Page ", "Phone Number Box", "7860xxxx77");

	}
}
