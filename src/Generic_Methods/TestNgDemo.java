package Generic_Methods;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class TestNgDemo {

	@BeforeMethod
	public void Login(Method m) { //// Method class is used as parameter to find the next to execute @test method
									//// "name".
		String methodName = m.getName();
		System.out.println("Method Nmae = " + methodName);
		System.out.println("Login Method.....");
	}
//If you want to store the excel data in @BeforeMethod but for every test case the excel data is not need so in that case we use Method class 
//	as parameter with @BeforeMethod and pass the variable of "Method Parameter"(Which test case name is about to come next) at the place of 
//	sheet name but in excel sheet and test case name should be same for which test case excel data is needed.

	@Test
	public void test1() {
		System.out.println("Test1......");
	}

//    Initilize extent test in before suite and call the generate report method of util class in before Method.
	@Test
	public void test2() {
		System.out.println("Test2......");
	}

// there are three task for me - 1-extends, 2 - snapshot, 3- extent report
	@Test()
	public void test3() {
		int a = 0 / 3;
//		 There are multiple ways to read data from xml file in every language but not excel , xml data reading is fast than excel , xml data changed it will not show on git..
//		What is test suite - Test suite is collection of test cases which all to execute.

//		If we use "groups" tag as suite label than it will execute all classes/package/methods but  if u use as test level than it will execute only that mentions classes.
		System.out.println("Test3......");
	}

	@AfterMethod
	public void logOut(ITestResult result) {
		int testResult = result.getStatus();
		System.out.println("Test Result = " + testResult);
		System.out.println("Log-Out Method.........");
	}
}
