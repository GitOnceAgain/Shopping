package groupid.LetsShop;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import groupid.PageObject.CartPage;
import groupid.PageObject.CheckoutPage;
import groupid.PageObject.ConfirmationPage;
import groupid.PageObject.ProductCatalogue;
import groupid.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	//@Test(groups= {"ErrorHandling"},retryAnalyzer=groupid.TestComponents.Retry.class)

	public void LoginErrorValidation() throws IOException, InterruptedException {
		//String ProductName = "ADIDAS ORIGINAL";
	    landingpage.LoginApplication("rajashree@gmail.com", "ajashree@4");
		Assert.assertEquals("Incorrect email or password.", landingpage.GetLoginErrorMessage());
		//System.out.println(landingpage.GetLoginErrorMessage());
		
	}
	
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String ProductName = "ADIDAS ORIGINAL";
		ProductCatalogue productcatalogue = landingpage.LoginApplication("rajashree@gmail.com", "Abcd@123");

		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(ProductName);

		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDiplay("ADIDAS ORIGINAL");
		Assert.assertTrue(match);


	}
}
