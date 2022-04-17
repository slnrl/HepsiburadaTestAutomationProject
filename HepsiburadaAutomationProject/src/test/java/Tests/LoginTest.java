package Tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
	@Test
	public void CheckAddingProductsToBasket() {

		selectLoginMenu();
		loginAs("test@test.com", "test123456");
		if (isLoginSuccessful()) {
			searchProduct("Lontano");
			chooseProduct();
			assertEquals(isProductEqual(), true);
		} else {
			System.out.println("Login Page is not reachable from selenium!");
		}
	}
}
