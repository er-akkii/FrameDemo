package Login_Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Methods.GenericMethods;
import vtiger.Pages.MyHomePage.home.HomePage;

public class LoginPage {

	@FindBy(name = "user_name")
	private WebElement userName;

	@FindBy(name = "user_password")
	private WebElement userPswrd;

	@FindBy(name = "Login")
	private WebElement loginBtn;

	private GenericMethods gobj;

	public LoginPage(GenericMethods gobj) {

		this.gobj = gobj;
		PageFactory.initElements(gobj.getDriverValue(), this);
	}

	public HomePage vailidLoginPage(Map<String, String> mapData) {
		// WebElement username = gobj.toSearchTheElementByLocaters("name", "user_name");
		// // We can use webElement like this
		String user_name = mapData.get("user_name");
		String user_pass = mapData.get("user_name");

		gobj.toSendValueInInputBox(userName, user_name, "User Name box");

		gobj.toSendValueInInputBox(userPswrd, user_pass, "User Password box");

		gobj.ToClickAnyButton(loginBtn, "Login Button");

		HomePage homePage = new HomePage(gobj);

		return homePage;
	}

	public void invailidlogin() {

		gobj.toGenerateReport();

		gobj.createTest("Tc001");

		gobj.launchBeowser("chrome");

		gobj.hitUrl("http://localhost:8888/");

		WebElement username = gobj.toSearchTheElementByLocaters("name", "user_name");
		gobj.toSendValueInInputBox(username, "akshay", "User Name box");

		WebElement userPassword = gobj.toSearchTheElementByLocaters("name", "user_password");
		gobj.toSendValueInInputBox(userPassword, "akshay", "User Password box");

		WebElement loginButton = gobj.toSearchTheElementByLocaters("name", "Login");
		gobj.ToClickAnyButton(loginButton, "Login Button");

	}

	public void selectLanguage() {

		WebElement language = gobj.toSearchTheElementByLocaters("name", "login_language");
		gobj.ToHandleDropDownByIndexOf(language, 0, "Language Dropdown");

	}

	public void selectColour() {

		WebElement color = gobj.toSearchTheElementByLocaters("name", "login_theme");
		gobj.ToHandleDropDownByIndexOf(color, 0, "Color Dropdown");

	}

}
