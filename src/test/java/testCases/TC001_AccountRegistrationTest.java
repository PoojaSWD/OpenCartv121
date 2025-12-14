package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		try
		{
		logger.info("***Strating TC001_AccountRegistrationTest****" );
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on my account link");
		hp.clickRegister();
		logger.info("Clicked on my Register link");
		
		
		AccountRegistrationPage Acctregipg = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details");
		Acctregipg.setfirstName(randomstring().toUpperCase());
		Acctregipg.setLastName(randomstring().toUpperCase());
		Acctregipg.setEmail(randomstring()+"@gmail.com");
		Acctregipg.setTelephone(randomNumber());
		
	String password = randomAlphanumeric();
	
		Acctregipg.setPassword(password);
		Acctregipg.setRepassword(password);
		Acctregipg.setPrivacyPolicy();
		Acctregipg.clickContinue();
		
		logger.info("validating expected message..");
		
	String confrmmsg=Acctregipg.getConfirmationMsg();
	if(confrmmsg.equals("Your Account Has Been Created!"))
	{
		AssertJUnit.assertTrue(true);
	}
	
	else
	{
		logger.error("Test failed");
		logger.debug("Debug log...");
		AssertJUnit.assertTrue(false);
	}
	//Assert.assertEquals(confrmmsg, "Your Account Has Been Created!");
		} 
		catch (Exception e)
		{
			AssertJUnit.fail();
			
		}
		logger.info("***finished TC001_AccountRegistrationTest***");
}	
	
	
	
}
 
	
	

