package groupid.PageObject;

import java.time.Duration;
//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import groupid.Abstractcomponents.Reusable;

public class LandingPage extends Reusable{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// Initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail =driver.findElement(By.id("userEmail"));
	// PageFactory
	@FindBy(id = "userEmail")
	WebElement UserEmail;

	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(name = "login")
	WebElement Submit;
	
	@FindBy(css= "[class*='flyInOut']")
	WebElement LoginError;


	public ProductCatalogue LoginApplication(String userid, String password) {
		UserEmail.sendKeys(userid);
		Password.sendKeys(password);
		Submit.click();
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}

	public void GoTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String  GetLoginErrorMessage() {
		waitForWebElementToAppear(LoginError);
		LoginError.getText();
		return LoginError.getText();
	}
}
