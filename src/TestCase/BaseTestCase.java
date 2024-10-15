package TestCase;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Generic_Methods.ExcelDataReading;
import Generic_Methods.GenericMethods;
import Login_Page.LoginPage;

public class BaseTestCase {

	private GenericMethods gobj;

	public BaseTestCase(GenericMethods gobj) {

		this.gobj = gobj;
	}

	@BeforeSuite
	public void GenerateReport() {
		System.out.println("Before suite");
		gobj.toGenerateReport();

	}

	@BeforeTest
	public void ConnectToDetaBase() {
		System.out.println("Before test");
		TestCase_1st testCase = new TestCase_1st(gobj);
		testCase.report_TestID_Browser_URL();

		Map<String, String> value = ExcelDataReading.getRowData(1,
				"C:\\Users\\Admin\\3D Objects\\Practice File\\Vtiger_Automation\\src\\main\\resources\\ExcelData.xlsx",
				"Sheet1");

	}

	@BeforeMethod
	public void loginBrowser() {
		System.out.println("Before method");
		HomePage homePage = new LoginPage(gobj).vailidLoginPage(value);
	}

	@BeforeClass
	public void launchBrowser() {
		System.out.println("Before class");
		gobj.createTest("Tc001");
		gobj.launchBeowser("chrome");
		gobj.hitUrl("http://localhost:8888/");

	}

	@Test
	public void test() {
		System.out.println("test");
	}
}
