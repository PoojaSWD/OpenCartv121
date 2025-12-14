package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.LoginPage;
import pageObjects.MyaccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class T003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")
	public void verify_loginDDT(String email,String pwd,String exp) throws Exception
	 {
		 	logger.info("*****starting Tc_003 LoginDDT***");
		try
		{
			Homepage hp = new Homepage(driver);
			logger.info("Clicked on my account link");
			hp.clickMyAccount();
			logger.info("Clicked on my login link");
			hp.clickLogin();
			
			//login
			LoginPage lp = new LoginPage(driver);
			logger.info("filled with username");
			lp.setEmailAdd(email);
			logger.info("filled with password");
			lp.setPasword(pwd);
			logger.info("cliked login button");
			lp.clicklongin();
			
			
			/*Data is valid - login success - test pass - logout
			5 Data is valid -- login failed - test fail
			6
			7 Data is invalid - login success
			8 Data is invalid -- login failed - test pass
			*/
			
			//myaccount
			MyaccountPage macc = new MyaccountPage(driver);
			logger.info("display My account header");
			boolean targetmsgmc = macc.isMyAccountExist();
			if(exp.equalsIgnoreCase("valid"))
			{
				if(targetmsgmc==true)
				{
					macc.clicklogout();
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("invalid"))
			{
				if(targetmsgmc==true)
				{
					macc.clicklogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}
		catch(Exception e)
		{
			e.getMessage();
			//Assert.fail();
		}
		Thread.sleep(3000);
		 logger.info("*****finished Tc_003 LoginDDT***");
	
	 }
	
}
