package groupid.LetsShop;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import groupid.PageObject.CartPage;
import groupid.PageObject.CheckoutPage;
import groupid.PageObject.ConfirmationPage;
import groupid.PageObject.LandingPage;
import groupid.PageObject.OrderPage;
import groupid.PageObject.ProductCatalogue;
import groupid.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PlaceOrderTest extends BaseTest {
	String ProductName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void placeorder(HashMap<String,String> input) throws IOException, InterruptedException {
		// public void placeorder(String Email, String Password, String ProductName)
		ProductCatalogue productcatalogue = landingpage.LoginApplication(input.get("Email"), input.get("Password"));

		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("ProductName"));

		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDiplay(input.get("ProductName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.SelectCountry("India");

		ConfirmationPage confirmationpage = checkoutpage.submitButtonClick();
		String confirmMessage = confirmationpage.GetConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//System.out.print(confirmMessage);
	}

	@Test(dependsOnMethods = { "placeorder" })
	public void OrderHistoryTest() throws InterruptedException {
		ProductCatalogue productcatalogue = landingpage.LoginApplication("rajashree@gmail.com", "Rajashree@4");
		Thread.sleep(2000);
		OrderPage orderpage = productcatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(ProductName));
		System.out.println(ProductName);
	}

	@DataProvider
	public Object[][] getData()throws IOException
	{
	
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\groupid\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}

//	private List<HashMap<String, String>> getJsonDataToMap(String string) {
//		// TODO Auto-generated method stub
//		return null;
		
//		@DataProvider
//		public Object[][] getData() {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("Email", "rajashree@gmail.com");
//			map.put("Password", "Rajashree@4");
//			map.put("ProductName", "ADIDAS ORIGINAL");
//			
//			HashMap<String, String> map1 = new HashMap<String, String>();
//			map1.put("Email", "bhuimbar@gmail.com");
//			map1.put("Password", "Bhuimbar@04");
//			map1.put("ProductName", "ZARA COAT 3");
//			
//		return new Object[][] { { "rajashree@gmail.com", "Rajashree@4", "ADIDAS ORIGINAL" },
//				{ "bhuimbar@gmail.com", "Bhuimbar@04", "ZARA COAT 3" } };
	