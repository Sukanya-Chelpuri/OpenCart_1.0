package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.utility.RandomString;
import pageObjects.HomePage;
import pageObjects.RegisterAccount;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration() 
	{

		logger.info("************* Strating Test case *************");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("************* Clicked on My account link *************");
			hp.clickRegister();
			logger.info("************* Clicked on My register link *************");

			RegisterAccount regPage = new RegisterAccount(driver);

			logger.info("************* Providing customer data *************");
			regPage.setFirstName(randomString(5).toUpperCase());
			regPage.setLastName(randomString(6).toUpperCase());
			regPage.setEmail(randomString(10)+ "@gmail.com");
			regPage.setMobileNo(randomNumber(10));

			String pwd = randomPassword(8);
			regPage.setPassword(pwd);
			regPage.confirmPassword(pwd);
			regPage.checkPrivacyPolicy();
			regPage.clickContinue();

			String confMsg = regPage.getConfirmationMessage();
			Assert.assertEquals(confMsg, "Your Account Has Been Created!");
		}  
		catch(Exception e)
		{
			logger.error(e.getMessage());
			logger.debug("debug logs..");
			Assert.fail(); 
		}
		logger.info("************* Ending of Test case *************");
	}

}
