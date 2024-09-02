package groupid.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import groupid.PageObject.CartPage;
import groupid.PageObject.CheckoutPage;
import groupid.PageObject.ConfirmationPage;
import groupid.PageObject.LandingPage;
import groupid.PageObject.ProductCatalogue;
import groupid.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingpage;
	public ProductCatalogue productcatalogue;
	public ConfirmationPage confirmationpage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingpage = launchApplication();
	}

	@Given("^I logged in with Username (.+) and Password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productcatalogue = landingpage.LoginApplication(username, password);
	}

	@When("^I add product (.+) to Cart$")
	public void i_add_product_to_cart(String ProductName) throws InterruptedException {
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(ProductName);
	}

	@When("^Chechout (.+) and submit the order$")
	public void checkout_adidas_original_and_submit_the_order(String ProductName) {
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.VerifyProductDiplay(ProductName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.SelectCountry("India");
		confirmationpage = checkoutpage.submitButtonClick();
	}
	
	@Then("{string} messsage is displayed on confirmation")
	public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationpage.GetConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();

	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
	Assert.assertEquals(strArg1, landingpage.GetLoginErrorMessage());
    driver.close();   }

}