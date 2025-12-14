package testCases;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyaccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("****starting TC002_LoginTest****");
		try
		{
			//homepage
		Homepage hp = new Homepage(driver);
		logger.info("Clicked on my account link");
		hp.clickMyAccount();
		logger.info("Clicked on my login link");
		hp.clickLogin();
		
		//login
		LoginPage lp = new LoginPage(driver);
		logger.info("filled with username");
		lp.setEmailAdd(p.getProperty("emailId"));
		logger.info("filled with password");
		lp.setPasword(p.getProperty("password"));
		logger.info("cliked login button");
		lp.clicklongin();
		
		//myaccount
		MyaccountPage macc = new MyaccountPage(driver);
		logger.info("display My account header");
		boolean targetmsgmc = macc.isMyAccountExist();
		Assert.assertTrue(targetmsgmc);	
		logger.info("logout from account");
		macc.clicklogout();
		}
		catch(Exception e)
		{
			logger.error("My account heder not display");
			Assert.fail();
		}
		
		logger.info("****finished TC002_LoginTest****");
		
	
	}
	
		
}
