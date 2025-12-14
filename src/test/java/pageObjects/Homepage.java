package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage {

	public Homepage(WebDriver driver)
	{	
		super(driver);	
	}


@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement lnkmyAccount;

@FindBy(xpath="//a[normalize-space()='Register']") 
WebElement lnkregister;

//@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
@FindBy(linkText="Login")
WebElement lnklogin;

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
}