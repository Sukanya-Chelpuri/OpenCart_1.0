package testCases;

import java.io.FileInputStream;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void verify_loginDDT(String email,String pwd,String expRes) 
	{
		try{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			//verifying the login
			MyAccountPage map = new MyAccountPage(driver);
			boolean targetPage = map.isMyAccountPageExists();

			if(expRes.equalsIgnoreCase("Valid")) 
			{
				if(targetPage == true)
				{
					map.clickLogout();
					Assert.assertTrue(true);
				}
				else 
				{
					Assert.assertTrue(false);
				}
			}
			else 
			{
				if(targetPage == true)
				{
					map.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
		}catch (Exception e) {
			Assert.fail();
		}
		logger.info("******************** End of TC003_LoginDDT ***************************");
	}
}
