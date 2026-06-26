package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/*
 * ProductDetailPage - Page Object for an individual product detail page.
 *
 * Covers: product name, price, quantity, Add to Cart button,
 *         and the success/error alert messages.*/


public class ProductDetailsPage extends BasePage {
	//contructor	
	
	public ProductDetailsPage(WebDriver driver)
	{
		super(driver);
	}


//locator 

/*
 * Product title heading (e.g., "iPhone").
 * XPath: h1 inside the product page content.
 */
@FindBy (xpath="//div[@id='content']//h1")
 private WebElement Productname;

/*
 * Product price (e.g., "$123.20").
 * XPath: h2 tag with the product-price class/label.
 */
@FindBy(xpath="//ul[@class='list-unstyled']//li/h2")
private WebElement ProductPrice;

/*
 * Quantity input field on the product page.
 * XPath: input with id="input-quantity".
 */

@FindBy(xpath="//input[@id='input-quantity']")
private WebElement QuantityInput;

/*
 * "Add to Cart" button on the product detail page.
 * XPath: button with id="button-cart" — stable attribute on tutorialsninja.
 */

@FindBy(xpath="//button[@id='button-cart']")
private WebElement cartbtn;

/*
* Success alert message shown after adding to cart.
* Text: "Success: You have added iPhone to your shopping cart!"
* XPath: div with class containing 'alert-success'.
*/

/*
 * Success alert message shown after adding to cart.
 * Text: "Success: You have added iPhone to your shopping cart!"
 * XPath: div with class containing 'alert-success'.
 */
@FindBy(xpath = "//div[contains(@class,'alert-success')]")
private WebElement successAlert;


/*
 * Danger/error alert shown when stock is insufficient.
 * Text usually contains "not available in the desired quantity".

@FindBy(xpath = "//div[contains(@class,'alert-danger')]")
private WebElement dangerAlert;
 */

/*
 * The "×" close button inside the success alert.
 */
@FindBy(xpath = "//div[contains(@class,'alert-success')]//button[@class='close']")
private WebElement alertCloseButton;

/*
 * Breadcrumb navigation for verifying page context.
 * XPath: last breadcrumb item text (current page).
 */
@FindBy(xpath = "//ul[@class='breadcrumb']//li[last()]/a")
private WebElement breadcrumbCurrent;


// shopping cart link in alert 

@FindBy(xpath="//div[contains(@class,'alert-success')]//a[contains(@href,'cart')]")
WebElement linkShopcart;


//actions

/*
 * 
 * Get the product name displayed on the detail page.
 */
public String getProdcutTitle()
{
	return Productname.getText();
}

/*
* Get displayed product price string.
*/
public String getProductPrice()
{
	return ProductPrice.getText();
}


/*
 * Set quantity in the quantity input field.
 * Clears existing value first.
 */
public ProductDetailsPage setproductQty(String qty)
{
	QuantityInput.clear();
	QuantityInput.sendKeys(qty);
	
	return this;
}

/*
 * Click "Add to Cart" button.
 * Returns same page since we stay on product page after adding.
 */
public ProductDetailsPage clickaddcart()
{
	cartbtn.click();
	return this;
}

/*
 * Get the full text of the success alert message.
 * Waits for it to appear after clicking Add to Cart.
 */

public String getSuccessmsg()
{
	return successAlert.getText();
}

/*
 * Get the full text of the danger/error alert message.

public String getDangerAlertText() {
    return dangerAlert.getText();
}
 */


/*
 * Returns true if the green success alert is shown.
 */

public boolean isSuccessAlert()
{
	return successAlert.isDisplayed();
}

/*
 * Returns true if the red danger/error alert is displayed.
 * This covers the stock-not-available validation message.
 
public boolean isDangerAlertDisplayed() {
    return isDisplayed(dangerAlert);
*/

/*
 * Get the current breadcrumb text (last segment = current page).
 */

public String getCurrentBreadcrumTxt()
{
	return breadcrumbCurrent.getText();
}

/*
 * Close the success/info alert by clicking the × button.
 */
public void closeAlert()
{
	if(alertCloseButton.isDisplayed())
	{
		alertCloseButton.click();
	}
}

/*
 * Navigate to Shopping Cart page via the top nav link.
 * Clicks the "shopping cart" link inside the success alert popup.
 */
public ShoppingCartPage clickviewCartInAlert()
{
	linkShopcart.click();
	
	return new ShoppingCartPage(driver);
}

public String getCurrentUrl()
{
	return driver.getCurrentUrl();
}

}