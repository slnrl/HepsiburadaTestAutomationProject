package Tests;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import PageFactory.HomePage;
import PageFactory.LoginPage;

public class BaseTest {
	protected WebDriver driver;
	protected HomePage homepage;
	protected LoginPage loginpage;

	@BeforeTest
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "C:\\chrome_driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hepsiburada.com/");
	}

	@AfterTest
	public void afterClass() {
		driver.quit();
	}

	public void searchProduct(String searchProductName) {

		homepage = new HomePage(driver);
		homepage.sendKeysForSearch(searchProductName);
	}

	public void chooseProduct() {
		homepage.chooseFirstProduct();
	}

	public boolean isProductEqual() {
		return homepage.isProductEqual();
	}
	
	public boolean isLoginSuccessful() {
		return loginpage.isLoginSuccessful();
	}

	public void loginAs(String username, String password) {
		loginpage.loginAs(username, password);
	}
	
	public void selectLoginMenu() {
		loginpage = new LoginPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));	
		loginpage.selectLoginMenu();
	}

}
