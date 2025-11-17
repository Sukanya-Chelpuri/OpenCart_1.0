package testCases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups = {"Sanity", "Master"})
	public void verify_Login()
	{
		logger.info("***************** Starting TC002_LoginTest ***************");
		try {
			//Home Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(prop.getProperty("Email"));
			lp.setPassword(prop.getProperty("Password"));
			lp.clickLogin();

			//verifying the login
			MyAccountPage map = new MyAccountPage(driver);
			boolean targetPage = map.isMyAccountPageExists();

			Assert.assertEquals(targetPage,true, "Login Failed");
			
		}
		catch (Exception e) {
			logger.info("TC002_LoginTest case failed");
		}
		
		logger.info("***************** Finished TC002_LoginTest ***************");

	}
}
