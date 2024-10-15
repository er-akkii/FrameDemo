package Generic_Methods;


	import java.io.File;
	import java.text.DateFormat;
	import java.text.SimpleDateFormat;
	import java.time.Duration;
	import java.util.Calendar;
	import java.util.Date;
	import java.util.List;
	import java.util.Set;

	import org.openqa.selenium.By;
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.Point;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.Color;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.google.common.io.Files;

	import io.github.bonigarcia.wdm.WebDriverManager;


	/**
	 * In This class there are all generic methods which is used to automate our
	 * test case or web application.
	 * 
	 * @author Admin -this user name of this user.
	 *
	 */
	public class GenericMethods {

		private ExtentReports reports;
		private ExtentTest extTest;
		private WebDriver driver;

		/**
		 * This constructor is used to generate date,report, user name,O.S. name and
		 * server name.
		 * 
		 * @param reportName   -This parameter is used to give the report name.
		 * @param testCaseName -This parameter is used to give the TestCase name.
		 */
		public String toCreatDAte() {

			DateFormat dateformate = new SimpleDateFormat("dd_MM_yyyy___hh_mm_ss");
			String date = dateformate.format(new Date());
			return date;
		}

		public void toGenerateReport() {

			String date = toCreatDAte();

			ExtentSparkReporter exspark = new ExtentSparkReporter("Automation_Report\\report" + date + ".html");
			reports = new ExtentReports();
			reports.attachReporter(exspark);

		}

		public void createTest(String testCaseName) {
			try {
				extTest = reports.createTest(testCaseName);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void toKnowAboutSystem() {

			reports.setSystemInfo("user name is - ", System.getProperty("user.name"));
			reports.setSystemInfo("Os name is - ", System.getProperty("os.name"));
			reports.setSystemInfo("Server name is - ", System.getProperty("QA server"));

		}

		/**
		 * @return
		 */
		public WebDriver getDriverValue() {

			if (driver == null) {

				extTest.log(Status.FAIL, "driver has null value");
			}
			return driver;
		}

		/**
		 * @param driver
		 * @return
		 */
		public WebDriver setDriver(WebDriver driver) {

			this.driver = driver;

			return driver;
		}

		/**
		 * This method is used to flush your generated report.
		 */
		public void toFlushTheReport() {
			try {
				reports.flush();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to launch Browser and hit (open) url .
		 * 
		 * @param browsername -Parameter is used for launching browser as a String type
		 * @param url         -Parameter is used for hitting or open url as a String
		 *                    type
		 */
		public void ToLaunchBrowserAndHitUrl(String browsername, String url) {

			try {
				
				
				if (browsername.equalsIgnoreCase("chrome")) {
					
					WebDriverManager.chromedriver().setup();

					driver = new ChromeDriver();

					extTest.log(Status.INFO, (browsername + "browser is launched"));
				} else if (browsername.equalsIgnoreCase("firefox")) {

					WebDriverManager.firefoxdriver().setup();

					driver = new FirefoxDriver();

					extTest.log(Status.INFO, browsername + "browser is launched");

				} else if (browsername.equalsIgnoreCase("edge")) {

					WebDriverManager.edgedriver().setup();
					driver = new ChromeDriver();

					extTest.log(Status.INFO, browsername + "browser is launched");

				} else {
					extTest.log(Status.INFO, "Browser name is invailid");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			driver.get(url);

		}

		/**
		 * This Method is used to launch your browser.
		 * 
		 * @param browsername -This parameter is used to give the browser name which is
		 *                    String type.
		 */
		public void launchBeowser(String browsername) {

			try {

				if (browsername.equalsIgnoreCase("chrome")) {
					
					ChromeOptions cromOptions = new ChromeOptions();
					cromOptions.addArguments("--remote-allow-origins=*");
					
					WebDriverManager.chromedriver().setup();
	 
					driver = new ChromeDriver(cromOptions);

					extTest.log(Status.INFO, (browsername + "browser is launched"));
				} else if (browsername.equalsIgnoreCase("firefox")) {

					WebDriverManager.firefoxdriver().setup();

					driver = new FirefoxDriver();

					extTest.log(Status.INFO, browsername + "browser is launched");

				} else if (browsername.equalsIgnoreCase("edge")) {

					WebDriverManager.edgedriver().setup();
					driver = new ChromeDriver();

					extTest.log(Status.INFO, browsername + "browser is launched");

				} else {
					extTest.log(Status.INFO, "Browser name is invailid");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to hit (open) the url .
		 * 
		 * @param url -Parameter is used to give the url .
		 * @parameter -Browsername -Parameter is used to identify the browser name
		 */

		public void hitUrl(String url) {

			driver.get(url);

		}

		/**
		 * This method is used to take a screenShot of accuring error place.
		 * 
		 * @param inputboxname- parameter is used to identify the element name where
		 *                      error will come which String type.
		 */
		public void toTakeScreenShot(String inputboxname) {

			try {

				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File from = screenshot.getScreenshotAs(OutputType.FILE);
				File to = new File("Automation_Report\\screenshot " + inputboxname + ".png");
				Files.copy(from, to);
				extTest.addScreenCaptureFromPath(to.getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to search the element in HTML page with the help of all
		 * locaters.
		 * 
		 * @param locatername -parameter is used to identify the name of locaters and it
		 *                    is String type.
		 * @param locater     -parameter is used to give the locater value according to
		 *                    locater name and it is String type.
		 * @return This method returns the WebElement type of object in which the
		 *         location of founded element will be.
		 */
		public WebElement toSearchTheElementByLocaters(String locaterType, String locaterValue) {

			WebElement web = null;
			try {
				if (locaterType.equalsIgnoreCase("xpath")) {
					web = driver.findElement(By.xpath(locaterValue));
				} else if (locaterType.equalsIgnoreCase("name")) {
					web = driver.findElement(By.name(locaterValue));

				} else if (locaterType.equalsIgnoreCase("linkText")) {
					web = driver.findElement(By.linkText(locaterValue));
				} else if (locaterType.equalsIgnoreCase("class")) {
					web = driver.findElement(By.className(locaterValue));
				} else if (locaterType.equalsIgnoreCase("CSS selector")) {
					web = driver.findElement(By.cssSelector(locaterValue));
				} else if (locaterType.equalsIgnoreCase("id")) {
					web = driver.findElement(By.id(locaterValue));
				} else if (locaterType.equalsIgnoreCase("tagName")) {
					web = driver.findElement(By.tagName(locaterValue));
				} else if (locaterType.equalsIgnoreCase("PartialLinksText")) {
					web = driver.findElement(By.partialLinkText(locaterValue));
				} else {
					extTest.log(Status.FAIL, "the locater value is wrong");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return web;
		}

		/**
		 * This method is used to check every element enability and Displaybility.
		 * 
		 * @param web                 -Parameter is used to give the locater name and
		 *                            locater value ,it is WebElement type which is
		 *                            WebElement type.
		 * @param inputBoxNameForInfo -Parameter is used give information of current
		 *                            working element which is String type.
		 * @return This method returns status which value was -false before checking
		 *         enability and Displaybility of element, and if all conditions will be
		 *         checked it will return -true ,which is boolean type.
		 */
		public boolean toCheckElementEnability(WebElement web, String inputBoxNameForInfo) {

			boolean status = false;

			if (web.isDisplayed()) {

				if (web.isEnabled()) {
					extTest.log(Status.INFO, inputBoxNameForInfo + " box is Displaying and enable");

					status = true;

				} else {
					extTest.log(Status.FAIL, inputBoxNameForInfo + " box is not enable");
				}
			} else {
				extTest.log(Status.FAIL, inputBoxNameForInfo + " box is not displaying");
			}
			return status;
		}

		/**
		 * This method is used to send the any value in input box.
		 * 
		 * @param web          -Parameter is used to search the element on UI by
		 *                     locaterType and locaterValue which is WebElement type.
		 * @param value        -Parameter is used to send the value in input box which
		 *                     is String type.
		 * @param inputboxname -Parameter is used to give the message of current working
		 *                     element which is String type.
		 */
		public void toSendValueInInputBox(WebElement web, String value, String inputboxname) {

			try {
				boolean enability = toCheckElementEnability(web, inputboxname);

				web.sendKeys(value);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is use to click on any button.
		 * 
		 * @param web          -Parameter is used to search the element on UI by locater
		 *                     type and locater value which is WebElement type.
		 * @param inputboxname -parameter is used to give the message of current working
		 *                     element which is String type.
		 */
		public void ToClickAnyButton(WebElement web, String inputboxname) {

			try {

				boolean elementEnability = toCheckElementEnability(web, inputboxname);

				if (elementEnability) {
					web.click();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to Handle the dropDown by it's visible text.
		 * 
		 * @param web                             -Parameter is used to search the
		 *                                        element by locater type and locater
		 *                                        value.
		 * @param inputboxname                    -Parameter is used to give the Info of
		 *                                        current working element.
		 * @param visibalTextOfSelectingOptInDrop -parameter is used to give the visible
		 *                                        text of window for working on that.
		 */
		public void ToHandleDropDownByVisibalText(WebElement web, String inputboxname,
				String visibalTextOfSelectingOptInDrop) {
			try {

				boolean dropDownEnability = toCheckElementEnability(web, inputboxname);

				if (dropDownEnability == true) {
					Select select = new Select(web);
					select.selectByVisibleText(visibalTextOfSelectingOptInDrop);
				}
			} catch (Exception n) {
				extTest.log(Status.FAIL, "here is a exception");

			}
		}

		/**
		 * This method is used to handle drop down with Index.
		 * 
		 * @param web                           -parameter is used to search the element
		 *                                      on UI by locaterType and locaterValue.
		 * @param endexOfSelectingOPTinDropDown -parameter is used to give the index of
		 *                                      window on which we will work.
		 * @param inputboxname                  -parameter is used for giving the
		 *                                      message of working Window.
		 */
		public void ToHandleDropDownByIndexOf(WebElement web, int endexOfSelectingOPTinDropDown, String inputboxname) {

			try {
				boolean elementLocaterEnability = toCheckElementEnability(web, inputboxname);

				if (elementLocaterEnability == true) {

					Select select = new Select(web);
					select.selectByIndex(endexOfSelectingOPTinDropDown);
					extTest.log(Status.PASS, "the option is selected in dropdown");
				}
			} catch (Exception e) {
				toTakeScreenShot(inputboxname);
			}
		}

		/**
		 * This method is used to Handle the Window by Value of Attribute value / Value
		 * attribute .
		 * 
		 * @param web                   -parameter is used to search the element on UI
		 *                              by locater type and locater value.
		 * @param valueOfAttrebuteValue -parameter is used to give the value of
		 *                              attributes which will be working window.
		 * @param inputboxname          -parameter is used to give the message of
		 *                              working on Element.
		 */
		public void ToHandleDropDownByValue(WebElement web, String valueOfAttrebuteValue, String inputboxname) {

			try {

				boolean elementEnability = toCheckElementEnability(web, inputboxname);

				if (elementEnability == true) {
					Select select = new Select(web);
					select.selectByValue(valueOfAttrebuteValue);
				}
			} catch (Exception e) {
				toTakeScreenShot(inputboxname);
			}
		}

		/**
		 * This method is used to find the inner text of element.
		 * 
		 * @param web          -parameter is used to search the element on UI by locater
		 *                     type and locater value which is WebElement type.
		 * @param inputboxname -parameter is used to give the message of working Element
		 *                     which is String type.
		 * @return this method returns founded innerText of element which is String type
		 *         .
		 */
		public String ToFindTheInnerText(WebElement web, String inputboxname) {

			String innerText = "";
			try {

				boolean elementEnability = toCheckElementEnability(web, innerText);

				if (elementEnability == true) {
					innerText = web.getText();
//					return innerText;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return innerText;
		}

		/**
		 * This method is used to find the value of any attribute.
		 * 
		 * @param web           -parameter is used to search the element on UI by
		 *                      locater type and locater value which is WebElement type.
		 * @param attributename -parameter is used to give the attribute name which
		 *                      attribute value we want and it is String Type.
		 * @return This method returns founded innerText of attribute which is String
		 *         Type.
		 */
		public String ToGetAttribute(WebElement web, String attributename) {

			String valueOfAttribute = "";
			try {

				boolean elementEnability = toCheckElementEnability(web, attributename);
				if (elementEnability) {

					valueOfAttribute = web.getAttribute(attributename);
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(attributename);
			}
			return valueOfAttribute;
		}

		/**
		 * This method is used to count the all options in dropDown.
		 * 
		 * @param web          -parameter is used to search the element on UI by locater
		 *                     type and locater value which is WebElement type.
		 * @param dropDownName -parameter is used to give the message of element, on
		 *                     which we are working and it is String type.
		 */
		public void ToCountOptionsOfDropdown(WebElement web, String dropDownName) {

			try {
				boolean elementEnability = toCheckElementEnability(web, dropDownName);

				if (elementEnability) {

					Select allOption = new Select(web);
					List<WebElement> getoption = allOption.getOptions();

					for (int i = 0; i < getoption.size(); i++) {
						WebElement get = getoption.get(i);
						String text = get.getText();
						System.out.println(text);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(dropDownName);
			}
		}

		/**
		 * This method is used to know the Selected option in dropDown.
		 * 
		 * @param web          -parameter is used to search the element on UI by locater
		 *                     type and locater value which is WebElement type.
		 * @param dropDownName - this parameter is used to give the message of working
		 *                     Element which is String type.
		 */
		public void toGetSelectedValueInDrop(WebElement web, String dropDownName) {

			try {
				boolean elementEnability = toCheckElementEnability(web, dropDownName);

				if (elementEnability) {

					Select select = new Select(web);
					WebElement option = select.getFirstSelectedOption();
					System.out.println(option.getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(dropDownName);
			}
		}

		/**
		 * This method is used to mouse Over on Element.
		 * 
		 * @param web                  -parameter is used to search the element on UI by
		 *                             locater type and locater value which is
		 *                             WebElement type.
		 * @param mouseOveringLinkName -parameter is used to give the mouseOverring Link
		 *                             which is String type.
		 */

		public void toMouseOver(WebElement web, String mouseOveringLinkName) {

			Actions action = null;
			try {
				boolean elementEnability = toCheckElementEnability(web, mouseOveringLinkName);

				if (elementEnability) {
					action = new Actions(driver);
					action.moveToElement(web).build().perform();
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(mouseOveringLinkName);
			}
		}

		/**
		 * This Method is used to click on any button by Actions class.
		 * 
		 * @param web                 -parameter is used to search the element on UI by
		 *                            locater type and locater value which is WebElement
		 *                            type.
		 * @param clickingElementName -parameter is used to give the element name on
		 *                            which working ,it is String type.
		 */
		public void toClickByActions(WebElement web, String clickingElementName) {

			try {
				boolean elementEnability = toCheckElementEnability(web, clickingElementName);
				if (elementEnability) {
					Actions act = new Actions(driver);
					act.click(web).build().perform();
				}

			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(clickingElementName);
			}
		}

		/**
		 * This method is used to send a value in input box By Actions Class.
		 * 
		 * @param web          -parameter is used to search the element on UI by locater
		 *                     type and locater value which is WebElement type.
		 * @param inputBoxName -parameter is used to give the name of Element.
		 * @param sendingValue -Parameter is used to send value in input box.
		 */
		public void toSendKeysByActions(WebElement web, String inputBoxName, String sendingValue) {

			try {
				boolean elementEnability = toCheckElementEnability(web, inputBoxName);

				if (elementEnability) {
					Actions act = new Actions(driver);
					act.sendKeys(web, sendingValue).build().perform();
				}

			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(inputBoxName);
			}
		}

		/**
		 * This Method is used to handle the Frame by Index ( of Frame ).
		 * 
		 * @param web              -parameter is used to search the element on UI by
		 *                         locater type and locater value which is WebElement
		 *                         type.
		 * @param frameNameForInfo -This parameter is used to give the Info or name of
		 *                         current working Frame.
		 * @param indexOfFrame     -This parameter is used to give the index number of
		 *                         Frame for handling.
		 */

		public void toSwitchInFrameByIndex(WebElement web, String frameNameForInfo, int indexOfFrame) {

			try {
				boolean elementEnability = toCheckElementEnability(web, frameNameForInfo);

				if (elementEnability) {
					driver.switchTo().frame(indexOfFrame);
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(frameNameForInfo);
			}
		}

		/**
		 * This Method is used to handle the Frame by Attribute ( of Frame ).
		 * 
		 * @param web              -parameter is used to search the element on UI by
		 *                         locater type and locater value which is WebElement
		 *                         type.
		 * @param frameNameForInfo -This parameter is used to give the Info or name of
		 *                         current working Frame.
		 * @param FrameByNameOrId  -This method is used to give the Attribute and id
		 *                         Value of Frame for handling.
		 */

		public void toSwitchInFrameByNameOrId(WebElement web, String frameNameForInfo, String FrameByNameOrId) {

			try {
				boolean elementEnability = toCheckElementEnability(web, frameNameForInfo);

				if (elementEnability) {
					driver.switchTo().frame(FrameByNameOrId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(frameNameForInfo);
			}
		}

		/**
		 * This Method is used to handle the Frame by WebElement .
		 * 
		 * @param web               -parameter is used to search the element on UI by
		 *                          locater type and locater value which is WebElement
		 *                          type.
		 * @param frameNameForInfo  -This parameter is used to give the Info or name of
		 *                          current working Frame.
		 * @param indexOfFrame      -
		 * @param FrameByWebElement
		 */
		public void toSwitchInFrameByWebElement(WebElement web, String frameNameForInfo, String FrameByWebElement) {

			try {
				boolean elementEnability = toCheckElementEnability(web, frameNameForInfo);

				if (elementEnability) {
					driver.switchTo().frame(FrameByWebElement);
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(frameNameForInfo);
			}
		}

		/**
		 * This Method is used to Launch the all windows of current UI.
		 * 
		 * @param web        -parameter is used to search the element on UI by locater
		 *                   type and locater value which is WebElement type.
		 * @param windowName -This parameter is used to give the info of happening work
		 *                   / Element Name and it is String type.
		 */
		public void toLaunchWindow(WebElement web, String windowName, String expectedWindowTitle_Url,
				String actualWindowTitle_Url) {
			try {
				boolean enability = toCheckElementEnability(web, windowName);
				if (enability) {
					Set<String> windows = driver.getWindowHandles();
					for (String string : windows) {
						driver.switchTo().window(string);
						if (expectedWindowTitle_Url.equalsIgnoreCase(actualWindowTitle_Url)) {

						}
					}
				}
//			driver.findElement(By.linkText("Help")).click();

//			driver.findElement(By.linkText("About Us")).click();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to handle the window by TITLE .
		 * 
		 * @param web         -parameter is used to search the element on UI by locater
		 *                    type and locater value which is WebElement type.
		 * @param windowTitle -This Parameter is uses to give the title of window for
		 *                    matching in condition block which is String type.
		 * @param windowName  -This parameter is uses to give the info or message about
		 *                    current working element which is String type.
		 */
		public void toHandleWindowByTitle(String windowTitle, String windowName) {

			try {

				Set<String> windows = driver.getWindowHandles();
				for (String string : windows) {
//						System.out.println(string);
					driver.switchTo().window(string);
					String title = driver.getTitle();
					System.out.println(title);
					if (title.equalsIgnoreCase(windowTitle)) {
						System.out.println("feed");
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(windowName);
			}
		}

		/**
		 * This method is used to handle the window by TITLE .
		 * 
		 * @param web             -parameter is used to search the element on UI by
		 *                        locater type and locater value which is WebElement
		 *                        type.
		 * @param windowUrl       -This Parameter is uses to give the URL of window for
		 *                        matching in condition block which is String type.
		 * @param windowName-This parameter is uses to give the info or message about
		 *                        current working element which is String type.
		 */

		public void toHandleWindowByUrl(WebElement web, String windowUrl, String windowName) {

			try {
				boolean elementEnability = toCheckElementEnability(web, windowName);

				if (elementEnability) {
					Set<String> windows = driver.getWindowHandles();
					for (String string : windows) {
						String title = driver.getCurrentUrl();
						System.out.println(title);
						if (title.equalsIgnoreCase(windowUrl)) {

							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(windowName);
			}
		}

		/**
		 * This method is used to find out the title of current WebPage or UI.
		 * 
		 * @param elementNameGettingTitle -This parameter is used to give the message of
		 *                                getting title of current Page.
		 */

		public String toGetTitleOfPage() {

			String title = "";
			try {
				title = driver.getTitle();
				System.out.println(title);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return title;
		}

		/**
		 * This Method is used to get the the CSS value or color of Element in hexa
		 * form.
		 * 
		 * @param web                      -parameter is used to search the element on
		 *                                 UI by locater type and locater value which is
		 *                                 WebElement type.
		 * @param elementName              -This parameter is used to give the name or
		 *                                 message of current working element Which is
		 *                                 String Type.
		 * @param backgroundOrElementColor -this parameter is used to give the area name
		 *                                 (background / element) of element Which color
		 *                                 is checking and it is String type.
		 */

		public void toGetCssValueOrElementColour(WebElement web, String elementName, String backgroundOrElementColor) {

			try {
				boolean elementEnability = toCheckElementEnability(web, elementName);

				if (elementEnability) {
					String colour = web.getCssValue(backgroundOrElementColor);
					System.out.println(colour);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This Method is used to get the the CSS value or color of Element in hexa
		 * form.
		 * 
		 * @param web                      -parameter is used to search the element on
		 *                                 UI by locater type and locater value which is
		 *                                 WebElement type.
		 * @param elementName              -This parameter is used to give the name or
		 *                                 message of current working element Which is
		 *                                 String Type.
		 * @param backgroundOrElementColor -this parameter is used to give the area name
		 *                                 (background / element) of element Which color
		 *                                 is checking and it is String type.
		 */

		public void toGetElementColour_HexaForm(WebElement web, String elementName, String backgroundOrElementColor) {

			try {
				boolean elementEnability = toCheckElementEnability(web, elementName);

				if (elementEnability) {
					String colour = web.getCssValue(backgroundOrElementColor);
					String color = Color.fromString(colour).asHex();
					System.out.println(color);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This Method is used to upload the file.
		 * 
		 * @param web               -This parameter is used to search the element on UI
		 *                          by locater type and locater value which is
		 *                          WebElement type.
		 * @param uploadingFileName -This parameter is used to give the file name Which
		 *                          is String type.
		 * @param fullXpathOfFile   -This parameter is used to give the full path of
		 *                          file Which is String type.
		 */
		public void toUploadFile(WebElement web, String uploadingFileName, String fullXpathOfFile) {

			try {
				boolean elementEnability = toCheckElementEnability(web, uploadingFileName);

				if (elementEnability) {

					web.sendKeys(fullXpathOfFile);

				}
			} catch (Exception e) {
				e.printStackTrace();
				toTakeScreenShot(uploadingFileName);
			}
		}

		/**
		 * This Method is used to check the status of checkBox (Checked or unchecked).
		 * 
		 * @param web          -This parameter is used to search the element on UI by
		 *                     locater type and locater value which is WebElement type.
		 * @param checkBoxName -This parameter is used to give the name check box Which
		 *                     is String type.
		 */

		public void toCheck_CheckBoxStatus(WebElement web, String checkBoxName) {

			try {
				boolean elementEnability = toCheckElementEnability(web, checkBoxName);

				if (elementEnability) {
					ToClickAnyButton(web, checkBoxName);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * This method is used to handle the calendar date.
		 */
		public void toHandleCalanderDate() {

			try {

				Calendar calnderObj = Calendar.getInstance();
				calnderObj.add(Calendar.DATE, 80);
				Date date = calnderObj.getTime();
				DateFormat dateformetObj = new SimpleDateFormat("dd_MMM_yyyy");
				String dateformetInString = dateformetObj.format(date);
				System.out.println(dateformetInString);

				String[] split = dateformetInString.split("_");
				String dateArr = split[0];
				String monthArr = split[1];
				ToLaunchBrowserAndHitUrl("chrome", "https://erail.com/");

				Actions act = new Actions(driver);

				WebElement we = driver.findElement(By.xpath("//input[@title='Select Departure date for availability']"));
				act.click(we).build().perform();

				Thread.sleep(5000);

				WebElement we2 = driver.findElement(By.xpath("//td[contains(text(),'" + monthArr
						+ "')]//parent::tr/following-sibling::tr//td[text()='" + dateArr + "']"));

				act.click(we2).build().perform();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to apply or create the explicitly wait of element
		 * presently on UI.
		 * 
		 * @param web                   -This parameter is used to search the element on
		 *                              UI by locater type and locater value which is
		 *                              WebElement type.
		 * @param elementName           -This parameter is used to give the name of
		 *                              current working element Which is String Type.
		 * @param timeDuration          -This parameter is used to give the time
		 *                              duration for waiting, how long you want which is
		 *                              Long type.
		 * @param locaterOfCreatingWait -This parameter is used to give the full xpath
		 *                              of checking presently element which is String
		 *                              type.
		 */

		public void toCreatExplicitlyWait_Presenty(WebElement web, String elementName, long timeDuration,
				String locaterOfCreatingWait) {
			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (web.isEnabled()) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
					WebElement isSelected = wait
							.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locaterOfCreatingWait)));
				}
			} catch (Exception e) { 

				e.printStackTrace();

			}

		}

		/**
		 * This method is used to apply or create the explicitly wait to check the
		 * element Enablity on UI.
		 * 
		 * @param web                   -This parameter is used to search the element on
		 *                              UI by locater type and locater value which is
		 *                              WebElement type.
		 * 
		 * @param elementName           -This parameter is used to give the name of
		 *                              current working element Which is String Type.
		 * @param timeDuration          -This parameter is used to give the time
		 *                              duration for waiting, how long you want which is
		 *                              Long type.
		 * @param locaterOfCreatingWait -This parameter is used to give the full xpath
		 *                              of checking presently element which is String
		 *                              type.
		 */
		public void toCreatExplicitlyWait_Enability(WebElement web, String elementName, long timeDuration,
				String locaterOfCreatingWait) {

			try {

				boolean enability = toCheckElementEnability(web, elementName);
				if (web.isEnabled()) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
					WebElement isSelected = wait
							.until(ExpectedConditions.elementToBeClickable(By.xpath(locaterOfCreatingWait)));
				}
			} catch (Exception e) {

			}
		}

		/**
		 * This method is used to apply or create the emplicitly wait.
		 * 
		 * @param time_InSeconds -This parameter is used to give the time duration how
		 *                       long you want which is int type.
		 */
		public void toImplicitlyWait(int time_InSeconds) {
			try {

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time_InSeconds * 1000));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to apply or create the Thread sleep wait.
		 * 
		 * @param time_InSeconds -This parameter is used to give the time duration how
		 *                       long you want which is int type.
		 */
		public void toUseThredSleep_Wait(int time_InSeconds) {
			try {

				Thread.sleep(time_InSeconds *= 1000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to get or find the size of element in high and width.
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementName -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 */
		public void toGetSizeOfElement(WebElement web, String elementName) {

			try {

				Dimension size = web.getSize();
				int hight = size.height;
				int width = size.width;

				System.out.println("Hight of " + elementName + " = " + hight + "  / and width is = " + width);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to check the location of element in x and y form.
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementname -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 */
		public void toCheckLocationOfElement(WebElement web, String elementname) {

			try {
				Point location = web.getLocation();
				int x_Axis = location.getX();
				int y_Axis = location.getY();

				System.out.println("X_Axis of " + elementname + " is = " + x_Axis + " and y_Axis is = " + y_Axis);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to validate the location of element in x and y form.
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementname -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 * @param xAxis       -This parameter is used to give the expected x location
		 *                    which is int type.
		 * @param yAxis       -This parameter is used to give the expected y location
		 *                    which is int type.
		 *
		 */
		public void toValidateLocationOfElement(WebElement web, String elementname, int expectedXAxis, int expectedYAxis) {

			int returnLocation = 0;

			try {
				Point location = web.getLocation();
				int x_Axis = location.getX();
				int y_Axis = location.getY();

				if (expectedXAxis == x_Axis && expectedYAxis == y_Axis) {
					extTest.log(Status.PASS, "The Location of " + elementname + " is pass with actual and expected value");
				} else {
					extTest.log(Status.FAIL, "The Location of " + elementname + " is Fail with actual and expected value");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to validate the size of element in high and width form.
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementName -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 */
		public void toValidateSizeOfElement(WebElement web, String elementName, int expectedHight, int expectedWidth) {

			int returnSize = 0;

			try {

				Dimension size = web.getSize();
				int hight = size.height;
				int width = size.width;
				if (expectedHight == hight && expectedWidth == width) {
					extTest.log(Status.PASS,
							"The Hight and Width of " + elementName + "  is pass with actual and expected value");
				} else {
					extTest.log(Status.FAIL,
							"The Hight and Width of " + elementName + " is Failed with actual and expected value");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This Method is used to find the multiple elements on UI Which-all locater
		 * will be same.
		 * 
		 * @param locatername  -This parameter is used to give the locater type(name)
		 *                     which is String type.
		 * @param locaterValue -This parameter is used to give the locater value which
		 *                     is String type.
		 * @return This method returns web ,which have address of all matching element
		 *         with one locater and it is WebElement type.
		 */
		public List<WebElement> toSearchElementByFindElements(String locatername, String locaterValue) {

			List<WebElement> web = null;

			try {
				if (locatername.equalsIgnoreCase("xpath")) {
					web = driver.findElements(By.xpath(locaterValue));
				} else if (locatername.equalsIgnoreCase("name")) {
					web = driver.findElements(By.name(locaterValue));
				} else if (locatername.equalsIgnoreCase("class")) {
					web = driver.findElements(By.className(locaterValue));
				} else if (locatername.equalsIgnoreCase("id")) {
					web = driver.findElements(By.id(locaterValue));
				} else if (locatername.equalsIgnoreCase("css Selector")) {
					web = driver.findElements(By.cssSelector(locaterValue));
				} else if (locatername.equalsIgnoreCase("linkText")) {
					web = driver.findElements(By.linkText(locaterValue));
				} else if (locatername.equalsIgnoreCase("partial Link Text")) {
					web = driver.findElements(By.partialLinkText(locaterValue));
				} else if (locatername.equalsIgnoreCase("tagName")) {
					web = driver.findElements(By.tagName(locaterValue));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return web;
		}

		/**
		 * This method is used to find the inner text of element .
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementNmae -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 * @return -This method returns inner text of element which is String type.
		 */
		public String toFindInnerText(WebElement web, String elementNmae) {
			String innerText = "";

			try {
				boolean enability = toCheckElementEnability(web, elementNmae);
				if (enability) {
					innerText = web.getText();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return innerText;
		}

		/**
		 * This method is used to clear the input box or delete the unwanted written
		 * things.
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementName -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 */
		public void toClearThe_Box(WebElement web, String elementName) {

			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (enability) {
					web.clear();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to click the right button on any element .
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementName -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 */
		public void toContextClick(WebElement web, String elementName) {

			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (enability) {
					Actions act = new Actions(driver);
					act.contextClick(web).build().perform();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to drag and drop to any element.
		 * 
		 * @param web                  -This parameter is used to search the element on
		 *                             UI by locater type and locater value which is
		 *                             WebElement type.
		 * 
		 * @param elementName          -This parameter is used to give the name of
		 *                             current working element Which is String Type.
		 * @param droping_ValueAndType -this parameter is used to give the dropping
		 *                             element locater and type where we want to drop
		 *                             our dragged element which is WebElement type.
		 */

		public void toDragAndDrop(WebElement web, String elementName, WebElement droping_ValueAndType) {

			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (enability) {
					Actions act = new Actions(driver);

					act.dragAndDrop(web, droping_ValueAndType).build().perform();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to drag and drop to any element.
		 * 
		 * @param dragingWebElement -this parameter is used to give the dragging element
		 *                          locater and type which is WebElement type.
		 * @param dropingWebElement -this parameter is used to give the dropping element
		 *                          locater and type where we want to drop our dragged
		 *                          element which is WebElement type.
		 */
		public void toClickAndHold(WebElement dragingWebElement, WebElement dropingWebElement) {
			try {
				Actions act = new Actions(driver);
				act.moveToElement(dragingWebElement).clickAndHold().moveToElement(dropingWebElement).release().build()
						.perform();
				;

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * This method is used to send value in input box by Actions Class.
		 * 
		 * @param web          -This parameter is used to search the element on UI by
		 *                     locater type and locater value which is WebElement type.
		 * 
		 * @param elementName  -This parameter is used to give the name of current
		 *                     working element Which is String Type.
		 * @param sendingValue This parameter is used to give your sending value in
		 *                     input box.
		 */
		public void toSendValueByActionClass(WebElement web, String elementName, String sendingValue) {
			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (enability) {

					Actions act = new Actions(driver);
					act.sendKeys(web, sendingValue).build().perform();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to double click .
		 * 
		 * @param web -This parameter is used to search the element on UI by locater
		 *            type and locater value which is WebElement type.
		 */

		public void toDoubleClickByActions(WebElement web) {
			try {

				Actions act = new Actions(driver);
				act.doubleClick(web).build().perform();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * @param web           -This parameter is used to search the element on UI by
		 *                      locater type and locater value which is WebElement type.
		 * 
		 * @param expectedValue
		 * @param actualValue
		 * @param elementName   -This parameter is used to give the name of current
		 *                      working element Which is String Type.
		 */
		public void toValidate_IsEnability(WebElement web, String expectedValue, String actualValue, String elementName) {
			try {

				if (expectedValue.equalsIgnoreCase(actualValue)) {
					extTest.log(Status.PASS, elementName + " -Enability is passed with actual and expected value");
				} else {
					extTest.log(Status.FAIL, elementName + " -Enability is Failed with actual and expected value");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This Methi
		 * 
		 * @param web           -This parameter is used to search the element on UI by
		 *                      locater type and locater value which is WebElement type.
		 * 
		 * @param expectedValue
		 * @param actualValue
		 * @param elementName   -This parameter is used to give the name of current
		 *                      working element Which is String Type.
		 */
		public void toValidate_IsDisplaibility(WebElement web, String expectedValue, String actualValue,
				String elementName) {
			try {

				if (expectedValue.equalsIgnoreCase(actualValue)) {
					extTest.log(Status.PASS, elementName + " -Displaybility is passed with actual and expected value");
				} else {
					extTest.log(Status.FAIL, elementName + " -Displaybility is Failed with actual and expected value");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to validate the Selectibility of element .
		 * 
		 * @param web           -This parameter is used to search the element on UI by
		 *                      locater type and locater value which is WebElement type.
		 * 
		 * @param expectedValue -This parameter is used to give the expected value which
		 *                      is String type.
		 * @param actualValue   -This parameter is used to give the actual value which
		 *                      is String type.
		 * @param elementName   -This parameter is used to give the name of current
		 *                      working element Which is String Type.
		 */
		public void toValidate_IsSelectibility(WebElement web, String expectedValue, String actualValue,
				String elementName) {
			try {

				if (expectedValue.equalsIgnoreCase(actualValue)) {
					extTest.log(Status.PASS, elementName + " -Displaybility is passed with actual and expected value");
				} else {
					extTest.log(Status.FAIL, elementName + " -Displaybility is Failed with actual and expected value");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to launch browser in in-coginito mode.
		 * 
		 * @param web        -This parameter is used to search the element on UI by
		 *                   locater type and locater value which is WebElement type.
		 * 
		 * @param incoginito -This parameter is used to give value how do you want to
		 *                   launch your browser.
		 */
		public void toLaunchBrowserInIncoginitoMode(WebElement web, String incoginito) {

			try {

				ChromeOptions chromeOpt = new ChromeOptions();

				chromeOpt.addArguments(incoginito);

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		/**
		 * This method is used to remove headlines which is being written on launching
		 * webApplication like ( chrome is being controlled by...................)
		 */
		public void toRemoveChromeHeadlines() {

			try {
				ChromeOptions obj = new ChromeOptions();

				obj.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to send value in input box by java-Script
		 * 
		 * @param web          -This parameter is used to search the element on UI by
		 *                     locater type and locater value which is WebElement type.
		 * 
		 * @param elementName  -This parameter is used to give the name of current
		 *                     working element Which is String Type.
		 * @param sendingValue
		 */
		public void toSendValueByJavaScript(WebElement web, String elementName, String sendingValue) {

			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (enability) {

					JavascriptExecutor javaSc = (JavascriptExecutor) driver;
					javaSc.executeScript("arguments[0].value='" + sendingValue + "'", web);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to click on any element by java-Script
		 * 
		 * @param web         -This parameter is used to search the element on UI by
		 *                    locater type and locater value which is WebElement type.
		 * 
		 * @param elementName -This parameter is used to give the name of current
		 *                    working element Which is String Type.
		 */
		public void toClickByJavaScript(WebElement web, String elementName) {
			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (enability) {
					JavascriptExecutor javaSc = (JavascriptExecutor) driver;
					javaSc.executeScript("arguments[0].click", web);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to go on back page from you current page (UI).
		 */
		public void toGoOnBackPage() {
			try {

				driver.navigate().back();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to go on your next page from current page .
		 */
		public void toGoOnForwordPage() {
			try {

				driver.navigate().forward();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to refresh your current page (UI).
		 */
		public void toRefreshPage() {
			try {

				driver.navigate().refresh();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to maximize your current page (UI).
		 */
		public void toMaximizePage() {
			try {

				driver.manage().window().maximize();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to minimize your current page (UI).
		 */
		public void toMinimizePage() {
			try {

				driver.manage().window().minimize();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to Scrolling-up and down on your WebPage by WebElement.
		 * 
		 * @param web -This parameter is used to search the element on UI by locater
		 *            type and locater value which is WebElement type.
		 * 
		 */
		public void toScrollWebElement(WebElement web) {
			try {

				JavascriptExecutor javaSc = (JavascriptExecutor) driver;
				javaSc.executeScript("arguments[0].scrollIntoView();", web);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * This method is used to Scrolling-down on your WebPage.
		 * 
		 * @param web                    -This parameter is used to search the element
		 *                               on UI by locater type and locater value which
		 *                               is WebElement type.
		 * 
		 * @param startingDestinationNUM -This parameter is used to give the starting
		 *                               destination value (Number) which is int type.
		 * @param targetDestinationNUM   -This parameter is used to give the target
		 *                               destination value (Number) which is int type.
		 */
		public void toScrollDown(int startingDestinationNUM, int targetDestinationNUM) {
			try {

				JavascriptExecutor javaSc = (JavascriptExecutor) driver;
				javaSc.executeScript("window.scrollBy(" + startingDestinationNUM + "," + targetDestinationNUM + ");");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * This method is used to Scrolling-up on your WebPage.
		 * 
		 * @param startingDestinationNUM -This parameter is used to give the starting
		 *                               destination value (Number) which is int type.
		 * @param targetDestinationNUM   -This parameter is used to give the Target
		 *                               destination value (Number) which is int type.
		 */
		public void toScrollUp(int startingDestinationNUM, int targetDestinationNUM) {
			try {

				JavascriptExecutor javaSc = (JavascriptExecutor) driver;
				javaSc.executeScript("window.scrollBy(" + startingDestinationNUM + ",-" + targetDestinationNUM + ");");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * @param expectedPageTitle
		 */

		public void toValidate_TitleOfPage(String expectedPageTitle) {

			try {
				String actualtitle = toGetTitleOfPage();

				if (actualtitle.equalsIgnoreCase(expectedPageTitle)) {
					extTest.log(Status.PASS, actualtitle + " is our correct page title");
				} else {
					extTest.log(Status.FAIL, actualtitle + " is our correct page title");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void toValidatePageByTEXT(WebElement web, String pageName, String elementName, String expectedText) {
			try {
				boolean enability = toCheckElementEnability(web, elementName);
				if (enability) {

					String text = web.getText();

					if (expectedText.equalsIgnoreCase(text)) {
						extTest.log(Status.PASS,
								pageName + " is my current expected page and expected  text  is =  " + expectedText);
						System.out.print("Validation is successfully");
					} else {
						extTest.log(Status.FAIL,
								pageName + " is not my current expected page and expected text is =  " + expectedText);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		public void toAcceptPop_Up() {

			driver.switchTo().alert().accept();
		}

		public void toDesmissPop_Up() {
			driver.switchTo().alert().dismiss();
		}
	}


