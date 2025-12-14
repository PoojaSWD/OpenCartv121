package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyaccountPage extends BasePage {

	public MyaccountPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//h2[normalize-space()='My Account']") 
	WebElement msgmyAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") 
	WebElement lnklogout;   ///added in step6)

	public boolean isMyAccountExist()
	{
		try
		{
			return (msgmyAccount.isDisplayed());
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public void clicklogout()  
	{
		lnklogout.click();
	}
	
}
