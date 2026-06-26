package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Homepage;
import pageObjects.ProductDetailsPage;
import pageObjects.SearchResultPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC005_AddToCartPage extends BaseClass  {

	@Test(groups={"Master"})
	public void verify_addTocart()
	{
		logger.info("******Starting TC005_AddToCartPage******");
		
		SoftAssert sassert = new SoftAssert();
		//Search → Product Detail → Add to Cart
		Homepage hp =new Homepage(driver);
		SearchResultPage  ResultPage = hp.SearchForProduct(p.getProperty("Search_keyword"));
		ProductDetailsPage  ProductPage= ResultPage.clickFirstProduct();
		ProductPage.clickaddcart();
		
		// STEP 2: Click "View Cart" link inside the success alert
		ShoppingCartPage cartpage =ProductPage.clickviewCartInAlert();
		
		 // STEP 3: Verify cart page heading
		String cartPageHead=cartpage.GetPageHeading();
		System.out.println("Cart page heading is "+cartPageHead);
		//sassert.assertTrue(cartPageHead.toLowerCase().contains("Shopping Cart"), "Cart heading should be 'Shopping Cart', but was: " + cartPageHead);
		
		// STEP 4: Verify product is in the cart
		boolean hasproduct =cartpage.hasProduct();
		sassert.assertTrue(hasproduct, "Cart should contain at least one product");
		
		//Verify the correct product is in cart
		if(hasproduct)
		{
			String firstProduct =cartpage.getFirstProductName();
			System.out.println("Product in Cart: " + firstProduct);
			sassert.assertTrue(firstProduct.toLowerCase().contains(p.getProperty("Expected_keyword").toLowerCase()), "the correct product should be in cart but it was "+firstProduct);
		}
		
		//Verify cart total is shown
		String cartTotal =cartpage.GetCartTotal();
		System.out.println("Cart Total: " + cartTotal);
		sassert.assertTrue(cartTotal.contains("$"), "the total price should be in cart with $,but was  "+cartTotal);
		
		sassert.assertAll();
		
		logger.info("**********FinishedTC005_AddToCartPage*********");
		
	}
		
		
	
}
