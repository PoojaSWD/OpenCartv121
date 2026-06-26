package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

	public Homepage(WebDriver driver)
	{	
		super(driver);	
	}

//Account and Registration Page
@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement lnkmyAccount;

@FindBy(xpath="//a[normalize-space()='Register']") 
WebElement lnkregister;

//@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
@FindBy(linkText="Login")
WebElement lnklogin;


//Search Product page
@FindBy(xpath="//button[@class='btn btn-default btn-lg']")  
WebElement btnsearch;

@FindBy(xpath="//input[@placeholder='Search']")  
WebElement searchbox;

public void clickMyAccount()
{
	lnkmyAccount.click();
}

public void clickRegister()
{
	lnkregister.click();
	
}
public void clickLogin()
{
	lnklogin.click();
}

public SearchResultPage SearchForProduct(String productName)
{
	searchbox.sendKeys(productName);
	btnsearch.click();
	return new SearchResultPage(driver);
}
//public void clicksearch()
//{
//	btnsearch.click();
//}
}