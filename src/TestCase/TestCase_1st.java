package TestCase;

	import java.util.Map;

	import org.openqa.selenium.support.PageFactory;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

import Generic_Methods.ExcelDataReading;
import Generic_Methods.GenericMethods;
	import vtiger.Marketing.Leads.LandingPage.ToCreatNewLeadsLandingPage;
	import vtiger.Marketing.Leads.LandingPage.ToGoOnMarketingLeadsLandingPage;
	import vtiger.Pages.MyHomePage.home.HomePage;
	import MarketingAccountPage.ToCreateNewAccountLandingPage;
	import MarketingAccountPage.GoToMarketingLandingPage;
	import Login_Page.LoginPage;

	public class TestCase_1st extends BaseTestCase {

		private GenericMethods gobj;

		public TestCase_1st(GenericMethods gobj) {

			this.gobj = gobj;
		}

		public void report_TestID_Browser_URL() {

			gobj.toGenerateReport();

			gobj.createTest("Tc001");

			gobj.launchBeowser("chrome");

			gobj.hitUrl("http://localhost:8888/");

		}
	@Test
		public static void goAndVlidateMarketingAccounts() {

			GenericMethods gobj = new GenericMethods();

			TestCase_1st testCase = new TestCase_1st(gobj);
			testCase.report_TestID_Browser_URL();

			Map<String, String> value = ExcelDataReading.getRowData(1,
					"C:\\Users\\Admin\\3D Objects\\Practice File\\Vtiger_Automation\\src\\main\\resources\\ExcelData.xlsx",
					"Sheet1");
			System.out.println("value = " + value);
//			int dataSize = ExcelData.list.size();
//			for (int i = 0; i < dataSize; i++) {
	//
//				Map<String, String> mapData = ExcelData.list.get(i);

//			HomePage homePage = new LoginPage(gobj).vailidLoginPage(value);
	//
//			ToGoOnMarketing_AccountLandingPage mrktAcountLandingPage = homePage.goToMarketingAccount();
	////
//			ToCreat_NewAccount_LandingPage creatNewAccount = mrktAcountLandingPage.toClickCreatAccountButton();
//			Map<String, String> value1 = ExcelData.getRowData(4,
//					"C:\\Users\\Admin\\3D Objects\\Practice File\\Vtiger_Automation\\src\\main\\resources\\ExcelData.xlsx",
//					"Sheet1");
//			System.out.println("Value1 = " + value1);
//			creatNewAccount.fillAllInputBoxes(value1);

//				ToValidateCreatedNewAccount validateCreatedAcc = creatNewAccount.clickOnSaveButton();
	//
//				validateCreatedAcc.toValidateNewCreatedAccount();

		}
	@BeforeSuite
	public void GenerateReport() {
		
	}
	@BeforeTest
	public void ConnectToDetaBase() {
		
	}
	@BeforeClass
	public void launchBrowser() {
		
	}
	@BeforeMethod
	public void loginBrowser() {
		
	}
	@AfterMethod
		public void logOut() {
			
		}
	@AfterClass
	public void quiteBrowser() {
		
	}
	@BeforeTest
	public void DisConnectToDetaBase() {
		
	}
	@AfterSuite
	public void flushReport() {
		
	}
	//@Test
//			public void goAndValidateMarketingLeads() {
	//
//				GenericMethods gobj = new GenericMethods();
	//
//				report_TestID_Browser_URL();
//				
//				LoginPage login = PageFactory.initElements(gobj.getDriverValue(), LoginPage.class);
//				HomePage homePage = login.vailidLoginPage();
	//
//				ToGoOnMarketingLeadsLandingPage leadsLandingPage = homePage.goToMarketingLeads();

//		     leadsLandingPage.toCheck_CheckBox();
//				leadsLandingPage..toCheckColour();
//				leadsLandingPage.toClickOnDeletButton();
//				leadsLandingPage.alartPop_Up();

//				ToCreatNewLeadsLandingPage ceratNewLeads = leadsLandingPage.clickOnCreatLeadsButton();
//				ceratNewLeads.toFillAllInputBoxes();
//				ValidateCreatedLeadsPage validateLeads = ceratNewLeads.toClickOnSaveButton();

//				validateLeads.validateLeads();

			}

}
