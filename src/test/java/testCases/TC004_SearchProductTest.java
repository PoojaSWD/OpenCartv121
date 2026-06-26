package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Homepage;
import pageObjects.ProductDetailsPage;
import pageObjects.SearchResultPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass {
 
	/*
	 * ============================================================
	 *  SearchToCheckoutTest
	 * ============================================================
	 *  End-to-end automation test covering the full purchase flow:
	 *
	 *  TC01 - Verify Search returns results for "iphone"
	 *  TC02 - Verify Product Detail page opens correctly
	 *  TC03 - Add product to cart and verify success message
	 *  TC04 - Navigate to Cart and verify product is present
	 *  TC05 - Proceed to Checkout and validate stock warning alert
	 *         "Products marked with *** are not available in the
	 *          desired quantity or not in stock!" visibility
	 *
	 *  Site: https://tutorialsninja.com/demo/
	 *  Framework: Selenium 4 + Java + TestNG + POM
	 * ============================================================
	 */
	 String Search_keyword ="iphone";
	 String Expected_product ="iPhone";
    
	@Test(groups= {"Master"})
	public void verify_SearchProductTest()
	{
	logger.info("*****starting TC004_SearchProductTest********");
		
	// STEP 1: Open Home page and search for "iphone"
		Homepage hp=new Homepage(driver);
		//SearchResultPage  ResultPage = hp.SearchForProduct(Search_keyword);
		SearchResultPage  ResultPage = hp.SearchForProduct(p.getProperty("Search_keyword"));
	
	
		   // STEP 2: Verify search results page heading
	
			String Heading = ResultPage.getSearchHeading();
			System.out.println("Search Page heading "+Heading);
			Assert.assertTrue(Heading.toLowerCase().contains(p.getProperty("Search_keyword")));
	
			 // STEP 3: Verify at least one product is returned
	
			int count= ResultPage.getProductCount();
			System.out.println("Products found: " + count);
			Assert.assertTrue(count>0,"Expected at least 1 search result for: " + p.getProperty("Search_keyword"));
	
			// STEP 4: Print all found product names
	
			System.out.println("Found products: " +ResultPage.getProdName());
		
	
	//Click first search result and verify Product Detail page
	
			
			
			System.out.println("\n===View Product Detail ===");
			
			SoftAssert sassert = new SoftAssert();
			
			// STEP 2: Click first matching product in results
			
			
			ProductDetailsPage  ProductPage= ResultPage.clickFirstProduct();
			
			// STEP 3: Verify product title on detail page
					String prodTitle=ProductPage.getProdcutTitle();
					System.out.println("Product Title on Detail Page: " + prodTitle);
					sassert.assertTrue(prodTitle.toLowerCase().contains(Search_keyword),"Product title should contain 'iphone', but was: " + prodTitle);	

	
					// STEP 4: Verify URL contains product route
					String url =ProductPage.getCurrentUrl();
					  System.out.println("Product Page URL: " + url);
					sassert.assertTrue(url.contains("product/product"),"Url should contain product/product but it was "+url);
				
					 // STEP 5: Verify product price is displayed
					
					
				String ProdPrice=ProductPage.getProductPrice();
				System.out.println("Product product price: " + ProdPrice);
				sassert.assertTrue(ProdPrice.contains("$"),"Product price should contain $ but it was "+ProdPrice);
				
				
				
				//Add product to cart and verify success alert message
		        System.out.println("\n=== Add to Cart ===");
		        System.out.println("Adding to cart: " + prodTitle);
		        
		        
		        // STEP 2: Click "Add to Cart" button (default qty = 1)
		        ProductPage.clickaddcart();
		        
		        // STEP 3: Verify success alert is displayed
		        
		        boolean successVisible =ProductPage.isSuccessAlert();
		        System.out.println("Success alert visible: " + successVisible);
		        sassert.assertTrue(successVisible, "Success alert should be displayed after adding to cart");
		        
		        // STEP 4: Verify alert text contains product name
		        if(successVisible)
		        {
		       String alertTxt= ProductPage.getSuccessmsg();
		       System.out.println("Alert text: " + alertTxt);
		       sassert.assertTrue(alertTxt.toLowerCase().contains(Expected_product.toLowerCase()),"Alert text should mention the product name. Actual: " + alertTxt);
		        
		        
		       sassert.assertTrue(alertTxt.toLowerCase().contains("success"),"Alert text should say 'success'. Actual: " + alertTxt);
		        }
		        
		        sassert.assertAll();
		        
		        
	}
}
