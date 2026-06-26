package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.Homepage;
import pageObjects.ProductDetailsPage;
import pageObjects.SearchResultPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC006_EndToEndTest extends BaseClass {
	
	@Test(groups= {"Master"})
	public void fullEndToEndFlow()
	{
		
		System.out.println("\n===Full End-to-End Flow ===");

        SoftAssert sassert = new SoftAssert();

        // ── PHASE 1: Search ───────────────────────────────────────────────────
        System.out.println("PHASE 1: Searching for '" + p.getProperty("Search_keyword") + "'");
        Homepage hp = new Homepage(driver);
        SearchResultPage resultsPage = hp.SearchForProduct(p.getProperty("Search_keyword"));

        sassert.assertTrue(resultsPage.hasreult(), "Search should return results");
        System.out.println("  Products found: " + resultsPage.getProductCount());

        // ── PHASE 2: View Product ─────────────────────────────────────────────
        System.out.println("PHASE 2: Opening product detail page");
        ProductDetailsPage ProductPage = resultsPage.clickFirstProduct();

        String title = ProductPage.getProdcutTitle();
        String price = ProductPage.getProductPrice();
        System.out.println("  Product: " + title + " | Price: " + price);

        sassert.assertTrue(
            title.toLowerCase().contains(p.getProperty("Search_keyword")),
            "Product title should mention iphone"
        );
        sassert.assertTrue(price.startsWith("$"), "Price should start with $");

        // ── PHASE 3: Add to Cart ──────────────────────────────────────────────
        System.out.println("PHASE 3: Adding to cart (qty=1)");
        ProductPage.clickaddcart();

        boolean successAlert = ProductPage.isSuccessAlert();
        System.out.println("  Success alert displayed: " + successAlert);
        sassert.assertTrue(successAlert, "Success alert should appear");

        // ── PHASE 4: Verify Cart ──────────────────────────────────────────────
        System.out.println("PHASE 4: Verifying cart contents");
        ShoppingCartPage cartPage = ProductPage.clickviewCartInAlert();

        sassert.assertEquals(
        		cartPage.GetPageHeading(), "Shopping Cart",
            "Cart page heading match"
        );
        sassert.assertTrue(cartPage.hasProduct(), "Cart should have products");
        System.out.println("  Cart product: " + cartPage.getFirstProductName());
        System.out.println("  Cart total: " + cartPage.GetCartTotal());

        // ── PHASE 5: Proceed to Checkout & Check Stock Alert ──────────────────
        System.out.println("PHASE 5: Proceeding to Checkout");
        cartPage.clickToProceed();

        System.out.println("  Checkout page: " + cartPage.GetPageHeading());
        sassert.assertTrue(
        		cartPage.GetPageHeading().contains("Checkout"),
            "Should be on checkout page"
        );

        // ── PHASE 6: Stock Validation Check ───────────────────────────────────
        System.out.println("PHASE 6: Checking stock validation alert");
        boolean stockAlertShown = cartPage.isStockAvailable();
        System.out.println("─────────────────────────────────────────────────────────");
        System.out.println("  Stock Warning Alert Displayed: " + stockAlertShown);
        if (stockAlertShown) {
            System.out.println("  Alert Text: " + cartPage.StockUnavailableAlrt());
            System.out.println("  → 'Products marked with *** are not available...' IS VISIBLE");
        } else {
            System.out.println("  → 'Products marked with *** are not available...' is NOT visible");
            System.out.println("  → Product is available in stock at requested quantity ✅");
        }
        System.out.println("─────────────────────────────────────────────────────────");

        // For qty=1 normal flow, stock alert should NOT appear
        sassert.assertFalse(stockAlertShown,
            "Stock warning should NOT show for qty=1 normal checkout");

        sassert.assertAll();
        System.out.println("Full E2E Flow COMPLETED");
	}
	

	
}
