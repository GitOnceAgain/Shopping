package groupid.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import groupid.Abstractcomponents.Reusable;
  
public class CheckoutPage extends Reusable {
	 WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
	    this.driver=driver;
	    PageFactory.initElements(driver, this);
		}
 @FindBy(css="input[placeholder='Select Country']")
 WebElement Country;

 @FindBy(css=".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")
 WebElement SelectCountry ;
 
 @FindBy(css=".btnn.action__submit.ng-star-inserted")
 WebElement Submit;
 
 By results = By.cssSelector(".ta-item");
 By submitbutton = By.cssSelector(".btnn.action__submit.ng-star-inserted");
 


public void SelectCountry(String CountryName) {
	Actions a = new Actions(driver);
	a.sendKeys(Country, CountryName).build().perform();	
	waitForElementToAppear(By.cssSelector(".ta-item"));
	SelectCountry.click();
	}

public ConfirmationPage submitButtonClick() {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,1000)");
	waitForElementToAppear(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
	Submit.click();
	return new ConfirmationPage(driver);
}
}

