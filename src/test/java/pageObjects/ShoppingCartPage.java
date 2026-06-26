package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 * CartPage - Page Object for the Shopping Cart page.
 * 
 *
 * Covers: cart product rows, quantities, total, checkout button.
 * All XPaths verified live against tutorialsninja.com/demo cart page.
 */
public class ShoppingCartPage extends BasePage{
	
	public ShoppingCartPage(WebDriver driver)
	{
		super(driver);
	}

	
//locator

	/*
     * Page heading "Shopping Cart".
     */
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement pageHeading;

    /*
     * Product name(s) in the cart table.
     * XPath: td elements with product name anchor tags in the cart tbody.
     */
    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement cartProductName;
    
    
    /*
    * Product name(s) in the cart table.
    * XPath: td elements with product name anchor tags in the cart tbody.
    */
    @FindBy(xpath = " //table[@class='table table-bordered']//tbody//tr//td[contains(@class,'text-left')]//a")
    private List<WebElement> cartProductNames;
 
    /*
     * Quantity input fields for each product row in cart.
     */
    @FindBy(xpath = "//table[@class='table table-bordered']//tbody//tr//td[contains(@class,'text-left')]//input[@type='text']")
    private List<WebElement> quantityInputs;
    
    /*
     * Unit price cells in the cart table.
     */
    @FindBy(xpath = "//div[@id='content']//table[@class='table table-bordered']//tbody[1]//tr[1]//td[@class='text-right'][1]")
    private List<WebElement> unitPriceCells;
    
    ////div[@id='content']//table[@class='table table-bordered']//tbody //tr[1]//td[@class='text-right'][1]
    /*
     * Cart total amount (bottom-right of the cart summary).
     */
    @FindBy(xpath = "//strong[text()='Total:']/ancestor::tr/td[2]")
    private WebElement cartTotal;
    ////table[@class='table table-bordered']//tr//td//strong[contains(text(),'Total:')]//td[@class='text-right']
   //
    ////table[@class='table table-bordered']//tr[td[strong[contains(text(),'Total')]]]//td[@class='text-right']
    /*
     * "Proceed to Checkout" button.
     * XPath: anchor with text matching checkout.
     */
    @FindBy(xpath = "//a[contains(text(),'Checkout') and contains(@class,'btn-primary')]")
    private WebElement checkoutButton;
    
    
    /*
     * "Continue Shopping" button.
     */
    @FindBy(xpath = "//a[contains(text(),'Continue Shopping')]")
    private WebElement continueShoppingButton;
    
    /*
     * Empty cart message shown when cart has no items.
     */
    @FindBy(xpath = "//div[@id='content']//p[contains(text(),'Your shopping cart is empty')]")
    private WebElement emptyCartMessage; 
    
    
    
    /*
     * Danger alert banner — appears when quantity exceeds available stock.
     * Text: "Products marked with *** are not available in the desired quantity
     *        or not in stock!"
     * XPath: div with alert-danger class containing the *** message in the cart.
     */
    @FindBy(xpath = "//div[contains(@class,'alert-danger') and contains(text(),'***')]")
    private WebElement stockUnavailableAlert;
    
    /*
     * Update quantity button for each product row.
     */
    @FindBy(xpath = "//button[@data-original-title='Update']")
    private List<WebElement> updateButtons;
    
    
//action
    /*
     * Get the Cart page heading text ("Shopping Cart").
     */
public String GetPageHeading()
{
	return pageHeading.getText();
}
	
/*
 * Returns true if the cart contains at least one product.
 */

public boolean hasProduct()
{
	return !cartProductNames.isEmpty();
}

/*
 * Get the name of the first product in the cart.
 */
public String getFirstProductName()
{
	if(cartProductNames.isEmpty())
	{
		return "";
	}
	else
	{
		return cartProductNames.get(0).getText();
	}
	
}
	
/*
 * Get all product names in the cart.
 */	
public List<String> GetAllProduct()
{
	List<String>Allprod = new ArrayList<>();
	for(WebElement cartProd:cartProductNames)
	{
		Allprod.add(cartProd.getText());
	}
	return Allprod; 
}

/*
 * Get the cart total price string (e.g., "$246.40").
 */

public String GetCartTotal()
{
	return cartTotal.getText();
}

/*
 * Click "Proceed to Checkout" button.
 * Returns CheckoutPage as the next page in the flow.
 */

public CheckoutPage clickToProceed()
{
	checkoutButton.click();
	
	return new CheckoutPage(driver);
}


/*
 * ──────────────────────────────────────────────────────────────────────────
 *  KEY ASSERTION METHOD
 * ──────────────────────────────────────────────────────────────────────────
 * Returns true if the stock-unavailable danger alert is visible.
 *
 * Alert text: "Products marked with *** are not available in the
 *              desired quantity or not in stock!"
 *
 * This alert appears on the CART page when:
 *  - You try to proceed to checkout AND
 *  - A product's requested quantity exceeds available stock.
 */

public boolean isStockAvailable()
{
	return stockUnavailableAlert.isDisplayed();
	
}

/*
 * Get the full text of the stock-unavailable alert.
 */

public String StockUnavailableAlrt()
{
	return stockUnavailableAlert.getText();
}

/*
 * Update quantity of first product in cart.
 */

public ShoppingCartPage UpdateQty(int rownum,String newqty)
{  
	if(rownum>=0 && rownum<quantityInputs.size())
	{ 
		WebElement qtyInput = quantityInputs.get(rownum);
		qtyInput.clear();
		qtyInput.sendKeys(newqty);
		updateButtons.get(rownum).click();
	}
	else
	{
		 throw new IndexOutOfBoundsException("Invalid row index: " + rownum);
	}
	
	return this;
}

/*
 * Check if cart is empty.
 */

public boolean IsCartEmpty()
{
	return emptyCartMessage.isDisplayed();
}
}
