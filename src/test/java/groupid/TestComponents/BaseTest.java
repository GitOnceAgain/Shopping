package groupid.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import groupid.PageObject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;

	public WebDriver InitializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"E:\\OnceAgain\\LetsShop\\src\\main\\java\\groupid\\resources\\GlobaData.properties");
		// FileInputStream fis = new
		// FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\groupid\\resources\\GlobaData.properties");
		// If the path is to long use above System.getProprty("user.dir")+ before\\src
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"):prop.getProperty("browser");
		 //prop.getProperty("browser");
		
		
		//if (browserName.equalsIgnoreCase("chrome")) {
			//WebDriverManager.chromedriver().setup();
			//// System.setProperty("webdriver.chrome.driver"),
			//// "E:\\WS_Basics\\SW-New\\ChromeDriver\\chromedriver.exe");
			//driver = new ChromeDriver();
			
			
			
			
			if (browserName.contains("chrome")) {
				ChromeOptions options = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				if(browserName.contains("headless")){
				options.addArguments("headless");
				}		
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));//full screen

			
			
			

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//System.setProperty("webdriver.gecko.driver","E:\\FirefoxDriver2022\\geckodriver");
			// Firefox
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		return driver;
	}

	//public class DataReader {
		public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
			String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent,
					new TypeReference<List<HashMap<String, String>>>() {
					});
			return data;
		}

		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
			FileUtils.copyFile(source,file);
		    //return System.getProperty(("user.dir")+ "//reports"+testCaseName+".png");
		    return System.getProperty("user.dir")+"/reports" +testCaseName+".png";
		
		}
		
		@BeforeMethod(alwaysRun = true)
		public LandingPage launchApplication() throws IOException {
			driver = InitializeDriver();

			landingpage = new LandingPage(driver);
			landingpage.GoTo();
			return landingpage;
		}

		@AfterMethod(alwaysRun = true)
		public void TearDown() {

			driver.close();
		}
	}
