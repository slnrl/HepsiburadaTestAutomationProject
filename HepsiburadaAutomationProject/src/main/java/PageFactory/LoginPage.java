package PageFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver driver;
	String productName;
	String firstProductNameInBasket;
	String secondProductNameInBasket;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public void loginAs(String username, String password) {

		WebElement usernameText = driver.findElement(By.id("txtUserName"));
		usernameText.sendKeys(username);
		WebElement btnLogin = driver.findElement(By.id("btnLogin"));
		btnLogin.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement passwodText = driver.findElement(By.id("txtPassword"));
		passwodText.sendKeys(password);
		WebElement btnEmailSelect = driver.findElement(By.id("btnEmailSelect"));
		btnEmailSelect.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	public void selectLoginMenu() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("myAccount")));
		WebElement myAccountText = driver.findElement(By.id("myAccount"));
		WebElement login = driver.findElement(By.id("login"));

		Actions action = new Actions(driver);		
		action.moveToElement(myAccountText).click().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//for(int i=0;i<5000000;i++){} //needed delay for quick transaction on the Page
		action.moveToElement(login).click().build().perform();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

	public void sendKeysForSearch(String searchText) {

		WebElement searchTextBox = driver
				.findElement(By.xpath("//*[@id=\"SearchBoxOld\"]/div/div/div[1]/div[2]/input"));
		WebElement searchButton = driver.findElement(By.xpath("//div[contains(text(),'ARA')]"));
		searchTextBox.click();
		searchTextBox.sendKeys(searchText);
		searchButton.click();
	}

	public void chooseFirstProduct() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement firstProductCard = driver.findElement(By.xpath("//*[@id=\"i0\"]/div/a/div[2]"));

		String oldTab = driver.getWindowHandle();
		firstProductCard.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		newTab.remove(oldTab);
		// change focus to new tab
		driver.switchTo().window(newTab.get(0));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		WebElement productNameWebElement = driver.findElement(By.xpath("//*[@id=\"product-name\"]"));

		productName = productNameWebElement.getText();

		WebElement allMerchantsButton = driver.findElement(By.id("merchantTabTrigger"));
		js.executeScript("arguments[0].scrollIntoView();", allMerchantsButton);
		allMerchantsButton.click();

		WebElement firstMerchantButton = driver
				.findElement(By.xpath("//*[@id=\"merchant-list\"]/tbody/tr[3]/td[4]/form/button"));
		firstMerchantButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		WebElement closeButton = driver.findElement(By.cssSelector("a.checkoutui-Modal-2iZXl"));
		closeButton.click();
		WebElement secondMerchantButton = driver
				.findElement(By.xpath("//*[@id=\"merchant-list\"]/tbody/tr[4]/td[4]/form/button"));
		secondMerchantButton.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		closeButton.click();
		WebElement shoppingCartButton = driver.findElement(By.id("shoppingCart"));
		shoppingCartButton.click();

		WebElement firstProductNameInBasketWebElement = driver.findElement(By.xpath(
				"//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[2]/section[1]/section[1]/div[3]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[2]/div[2]/a[1]"));
		firstProductNameInBasket = firstProductNameInBasketWebElement.getText();

		WebElement secondProductNameInBasketWebElement = driver.findElement(By.xpath(
				"//body/div[@id='app']/div[1]/div[1]/div[2]/div[1]/div[2]/section[1]/section[1]/div[4]/div[1]/ul[1]/li[1]/div[1]/div[1]/div[1]/div[2]/div[2]/a[1]"));
		secondProductNameInBasket = firstProductNameInBasketWebElement.getText();
	}

	public boolean isLoginSuccessful() {

		WebElement errorLogin = driver
				.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div/div[4]/div/div/div[1]/div[2]"));
		if (errorLogin.isDisplayed()) {
			return false;
		} else {

			List<WebElement> myAccountText = driver.findElements(By.xpath("//*[@id=\"myAccount\"]/span/a/span[1]"));
			if (myAccountText.get(0).getText() == "Hesabım")
				return true;
		}
		return false;
	}

	public boolean isProductEqual() {
		boolean isProductEqual = true;

		if (productName.contentEquals(firstProductNameInBasket) == false) {
			isProductEqual = false;
			return false;
		}

		if (productName.contentEquals(secondProductNameInBasket) == false) {
			isProductEqual = false;
			return false;
		}

		return isProductEqual;
	}

}
