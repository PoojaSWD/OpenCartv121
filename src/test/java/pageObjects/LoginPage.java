package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	//contructor
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}

	//locator
	

@FindBy(xpath="//input[@id='input-email']") 
WebElement txteMailAd;
@FindBy(xpath="//input[@id='input-password']")
WebElement txtpassword;
@FindBy(xpath="//input[@value='Login']")  
WebElement btnlogin;	
	
	//action

public void setEmailAdd(String emailId )
{
	txteMailAd.sendKeys(emailId);
}
public void setPasword(String password)
{
	txtpassword.sendKeys(password);
}

public void clicklongin()
{
	btnlogin.click();
}
}
