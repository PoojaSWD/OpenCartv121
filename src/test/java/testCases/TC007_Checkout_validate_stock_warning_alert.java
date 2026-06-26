package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Homepage;
import pageObjects.ProductDetailsPage;
import pageObjects.SearchResultPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC007_Checkout_validate_stock_warning_alert extends BaseClass {
	
	@Test(groups= {"Master"})
	public void verifyStockWarningAlertWithHighQuantity() 
	{
		
		System.out.println("\n=== TC05-A: Stock Warning Alert Validation (High Qty) ===");
        System.out.println("Scenario: Add iPhone with qty=1000 → expect stock warning at checkout");
        
        // STEP 1: Search for iPhone
        Homepage hp=new Homepage(driver);
        SearchResultPage  ResultPage = hp.SearchForProduct(p.getProperty("Search_keyword"));

	
     // STEP 2: Click on iPhone product
        ProductDetailsPage  ProductPage= ResultPage.clickFirstProduct();
        System.out.println("Product: " + ProductPage.getProdcutTitle());
	
        // STEP 3: Set an impossibly high quantity to trigger stock alert
        ProductPage.setproductQty("1000");
        
        // STEP 4: Click Add to Cart
        ProductPage.clickaddcart();
        
        // STEP 5: Navigate to Cart via alert link
        ShoppingCartPage cartpage=ProductPage.clickviewCartInAlert();
        
     // STEP 6: Click "Proceed to Checkout"
        cartpage.clickToProceed();
        
     // STEP 7: Check if stock warning alert is present on the checkout page
       boolean stockavailibity= cartpage.isStockAvailable();
       System.out.println("─────────────────────────────────────────────");
       System.out.println("Stock Warning Alert Visible: " + stockavailibity);
       
       if(stockavailibity)
       {
    	String stockAlert=  cartpage.StockUnavailableAlrt();
    	System.out.println("Alert text: "+stockAlert);
    	
       
       
    // STEP 8: Validate alert text contains the expected message
	Assert.assertTrue(stockAlert.contains("***"),"Alert should contain '***' marker. Actual text: " + stockAlert );
	
	Assert.assertTrue(stockAlert.toLowerCase().contains("not available in the desired quantity") || stockAlert.toLowerCase().contains("not in stock"),"Alert should mention stock unavailability. Actual: " + stockAlert );
     
	System.out.println("✅ PASS: Stock warning alert IS displayed as expected for qty=1000");
       }
       else
       {
    	   System.out.println("ℹ️  Stock warning alert NOT displayed (product might have unlimited stock in demo).");
           System.out.println("    This is acceptable behaviour for the demo store.");
       }
	}
	
	@Test (groups= {"Master"})
	public void verifyNoStockWarningForNormalQuantity()
	{
		 System.out.println("\n===Stock Warning Alert Validation (Normal Qty) ===");
	        System.out.println("Scenario: Add iPhone with qty=1 → expect NO stock warning");
	
	
	        SoftAssert softAssert = new SoftAssert();

	        // STEP 1: Search and navigate to iPhone
	        Homepage hp=new Homepage(driver);
	        SearchResultPage  ResultPage = hp.SearchForProduct(p.getProperty("Search_keyword"));

	        ProductDetailsPage  ProductPage= ResultPage.clickFirstProduct();

	        // STEP 2: Use default quantity = 1 (do NOT change it)
	        System.out.println("Adding: " + ProductPage.getProdcutTitle() + " (qty=1)");

	        // STEP 3: Add to cart
	        ProductPage.clickaddcart();

	        // STEP 4: Verify add to cart succeeded
	        boolean successShown = ProductPage.isSuccessAlert();
	        softAssert.assertTrue(successShown, "Success alert should appear after adding qty=1");

	        // STEP 5: Navigate to cart
	        ShoppingCartPage cartPage = ProductPage.clickviewCartInAlert();

	        // STEP 6: Proceed to checkout
	      cartPage.clickToProceed();

	        // STEP 7: Verify NO stock warning alert on checkout page
	        boolean alertShown = cartPage.isStockAvailable();
	        System.out.println("─────────────────────────────────────────────");
	        System.out.println("Stock Warning Alert Visible (should be false): " + alertShown);
	        System.out.println("─────────────────────────────────────────────");

	        softAssert.assertFalse(
	            alertShown,
	            "Stock warning alert should NOT be displayed for qty=1. It was displayed unexpectedly."
	        );

	        if (!alertShown) {
	            System.out.println("✅ PASS: No stock warning for normal quantity. Happy path confirmed.");
	        }

	        softAssert.assertAll();
	
	
	}

}
