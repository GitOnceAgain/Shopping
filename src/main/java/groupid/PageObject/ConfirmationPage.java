package groupid.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import groupid.Abstractcomponents.Reusable;

public class ConfirmationPage extends Reusable {
        WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy (css=".hero-primary")
	WebElement confirmationMessage;
	
	 By confirmTextMessage = By.cssSelector(".hero-primary");

	public String GetConfirmationMessage() {
		waitForElementToAppear(By.cssSelector(".hero-primary"));
		return confirmationMessage.getText();
		
	}
}
