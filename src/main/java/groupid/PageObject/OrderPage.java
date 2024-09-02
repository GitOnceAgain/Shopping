package groupid.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import groupid.Abstractcomponents.Reusable;

public class OrderPage extends Reusable {
	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = "td:nth-child(3)")
	private List<WebElement> OrderedProductName;

	@FindBy(css = ".btn.btn-custom[routerlink='/dashboard/myorders']")
	WebElement OrderLink;

//	 By order = By.css(".btn.btn-custom[routerlink='/dashboard/myorders']");

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Boolean VerifyOrderDisplay(String productName) {
		waitForWebElementToAppear(OrderLink);
		Boolean match = OrderedProductName.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

//	public CheckoutPage goToCheckout() {
//		waitForElementToAppear(order);
//		checkoutEle.click();
//		return new CheckoutPage(driver);
//
//	}
}
