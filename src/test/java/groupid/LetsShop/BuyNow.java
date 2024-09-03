package groupid.LetsShop;

import java.time.Duration;
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

import groupid.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BuyNow {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String ProductName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "E:\\WS_Basics\\SW-New\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");

	
		System.out.println("aaaaaaa");
		System.out.println("bbbbbbb");
		
	
		driver.findElement(By.id("userEmail")).sendKeys("rajashree@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rajashree@4");
		driver.findElement(By.name("login")).click();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

		List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type ")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// Thread.sleep(5);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean Match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(Match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build()
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		driver.findElement(By.cssSelector(".ta-item.list-group-item.ng-star-inserted:nth-of-type(2)")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit")));
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		//driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String ConfirmMessege = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(ConfirmMessege.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
