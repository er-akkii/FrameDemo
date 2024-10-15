package Generic_Methods;

import org.openqa.selenium.WebElement;
public class FliteBooking {

	
			public static void main(String[] args) {
				
				GenericMethods gobj = new GenericMethods();
				
				gobj.toGenerateReport();
				gobj.createTest("TC001");
				
				gobj.ToLaunchBrowserAndHitUrl("chrome", "https://www.makemytrip.com/flights/");
				
				WebElement toCity = gobj.toSearchTheElementByLocaters("xpath", "//input[@id=\"toCity\"]");
				gobj.ToClickAnyButton(toCity, "To City Name");
				
			}
		}


