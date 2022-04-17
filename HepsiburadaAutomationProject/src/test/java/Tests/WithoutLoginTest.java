package Tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;


public class WithoutLoginTest extends BaseTest{
  @Test
  public void CheckAddingProductsToBasket() {	  
	  
	  searchProduct("Lontano");
	  chooseProduct();
	  assertEquals(isProductEqual(), true);
  }
}
