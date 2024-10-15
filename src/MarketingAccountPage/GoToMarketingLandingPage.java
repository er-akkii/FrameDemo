package MarketingAccountPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Methods.GenericMethods;

public class GoToMarketingLandingPage {

	@FindBy(xpath = "//img[@title='Create Account...']")
	private WebElement creatAccount;

	private GenericMethods gobj;

	public ToGoOnMarketing_AccountLandingPage(GenericMethods gobj) {
			
			this .gobj = gobj;
			PageFactory.initElements(gobj.getDriverValue(), this);

		}

	public ToCreat_NewAccount_LandingPage toClickCreatAccountButton() {

		gobj.ToClickAnyButton(creatAccount, "Creat Account Button");

		ToCreat_NewAccount_LandingPage createAccount = new ToCreat_NewAccount_LandingPage(gobj);
		;
		return createAccount;

	}
}
