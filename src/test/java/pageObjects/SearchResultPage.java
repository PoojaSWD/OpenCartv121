package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BasePage {
//constructor
	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

//locators
	 /*
     * Page heading "Search - iphone".
     * XPath: h1 tag inside the page content area.
     */
	
@FindBy(xpath="//div[@id='content']/h1")
 WebElement SearchHeading;

/*
 * All product name links shown in the search result grid.
 * XPath: anchor tags inside product caption divs.
 */

@FindBy(xpath="//div[@class='caption']//h4/a")
private List<WebElement> ProdLink;
/*
 * First product's name link in result list.
 * Targets the first item — typically "iPhone" when searching "iphone".
 */

@FindBy(xpath = "(//div[@class='caption']//h4/a)[1]")
private WebElement firstProductLink;
/*
 * "No product matched" message shown when 0 results.
 * Useful for asserting search returns results.
 */

@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criteria')]")
private WebElement noResultsMessage;
/*
 * Product thumbnail images in list view.
 */

@FindBy(xpath = "//div[@class='product-thumb']//img")
private List<WebElement> productImages;

/*
 * All product containers (used to count results).
 */
@FindBy(xpath = "//div[@class='product-thumb']")
private List<WebElement> productThumbs;
	
//actions 
/*
 * Get the search results page heading text.
 * Expected: "Search - iphone"
 */
public String getSearchHeading()
{
	return SearchHeading.getText();
}
/*
 * Get total count of products returned in search.
 */	
public int getProductCount()
{
	return productThumbs.size();
	
}
/*
 * Get the name of the first product in results.
 */

public String GetFirstProdName()
{
	return firstProductLink.getText();
}

/*
* Click on the first product in search results.
* Returns ProductDetailPage as the next page in flow.
*/
public ProductDetailsPage clickFirstProduct()
{
	firstProductLink.click();
	
	return new ProductDetailsPage(driver);
}

/*
 * Click on a product by its exact name (case-insensitive partial match).
 */
public ProductDetailsPage clickProductByName(String prodName)
{
	for(WebElement link:ProdLink)
	{
		if(link.getText().trim().toLowerCase().contains(prodName.toLowerCase()))
		{
			link.click();
			
		}
	}
	return new ProductDetailsPage(driver);
}

/*
* Returns true if search returned at least one result.
*/
public boolean hasreult()
{
	return !productThumbs.isEmpty();
}

/*
* Get the text of all product names as a list.
*/
public List<String> getProdName()
{
	List<String>Productnames= new ArrayList<>();
	for(WebElement prodElemt:ProdLink)
	{
		String text=prodElemt.getText().trim();
		if(!text.isEmpty())
		{
			Productnames.add(text);
		}
	}
	return Productnames;
}


}
