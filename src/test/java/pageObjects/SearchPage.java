package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

	//constructor
	public SearchPage(WebDriver driver) {
		super(driver);
		
	}
	
	//locator

@FindBy(partialLinkText="iPhone") 
WebElement productnm;	


@FindBy(xpath="//button[@id='button-cart']") 
private WebElement btnaddToCart;
@FindBy(xpath="//input[@id='input-quantity']") 
private WebElement txtqty;

@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") 
WebElement msgsuccess;

public void setqty(String qty)
{
	txtqty.clear();
	txtqty.sendKeys("2");
}

public void addtocart()
{
	btnaddToCart.click();
}

public void selectProd()
{
	productnm.click();
}


public String GetMsg()
{
	return msgsuccess.getText();
}

public boolean CheckConfirmMsg()
{
	try
	{
	return (msgsuccess.isDisplayed());
	}
	catch(Exception e)
	{
		return false;
	}
}
public boolean isproductExist(String prdnm)
{
	try
	{
		return(productnm.isDisplayed());
	}
	catch(Exception e)
	{
		return false;
	}
}



}