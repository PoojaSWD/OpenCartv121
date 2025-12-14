package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	

@FindBy(xpath="//input[@id='input-firstname']") 
WebElement txtfirstName;
@FindBy(xpath="//input[@id='input-lastname']")  
WebElement txtlastName;
@FindBy(xpath="//input[@id='input-email']")  
WebElement txteMail;
@FindBy(xpath="//input[@id='input-telephone']")
WebElement txttelephone;
@FindBy(xpath="//input[@id='input-password']")  
WebElement txtpassword;
@FindBy(xpath="//input[@id='input-confirm']") 
WebElement txtpasswordConfirm;
@FindBy(xpath="//input[@name='agree']")  
WebElement chckagree;
@FindBy(xpath="//input[@value='Continue']") 
WebElement btncontinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") 
WebElement msgConfirm;
//Your Account Has Been Created!

public void setfirstName(String fname)
{
	txtfirstName.sendKeys(fname);
}	
public void setLastName(String lname)
{
	txtlastName.sendKeys(lname);
}

public void setEmail(String email)
{
	txteMail.sendKeys(email);
}

public void setTelephone(String telephone)
{
	txttelephone.sendKeys(telephone);
}

public void setPassword(String pwd)
{
	txtpassword.sendKeys(pwd);
}

public void setRepassword(String pwd)
{
	txtpasswordConfirm.sendKeys(pwd);
}

public void setPrivacyPolicy()
{
	chckagree.click();
}

public void clickContinue()
{
	//sol1
	btncontinue.click();
	/*
	  	//sol2
	
	Actions act= new Actions(driver);
	act.moveToElement(btncontinue).click().perform();
	
	//sol3
	btncontinue.submit();
	
	//sol4
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("argument[0].click();",btncontinue);

	//sol5
	btncontinue.sendKeys(Keys.ENTER);
	
	//sol6
	
	WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
	mywait.until(ExpectedCondition.elementToBeClickable(btncontinue).click();)
 */
}	
public String getConfirmationMsg() {
try
{
	return(msgConfirm.getText());
}
catch(Exception e)
{
	return(e.getMessage());
}
}
}
