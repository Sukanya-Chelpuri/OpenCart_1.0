package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterAccount extends BasePage{

	public RegisterAccount(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstname;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastname;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfrmPwd;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPolicy;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;


	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);
	}

	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);
	}

	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setMobileNo(String mobile)
	{
		txtTelephone.sendKeys(mobile);
	}

	public void setPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}

	public void confirmPassword(String pwd)
	{
		txtConfrmPwd.sendKeys(pwd);
	}
	
	public void checkPrivacyPolicy()
	{
		chkPolicy.click();
	}

	public void clickContinue() 
	{
		btnContinue.click();

		//Solution 2
		//btnContinue.submit();

		//sol 3
		// Actions act = new Actions(driver);
		// act.moveToElement(btnContinue).click().perform();

		//sol 4
		// WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// myWait.until(ExpectedConditions.elementToBeClickable(btnContinue));

		//sol 5
		//btnContinue.sendKeys(Keys.RETURN);

	}

	public String getConfirmationMessage() 
	{
		try {
			return msgConfirmation.getText();
		}catch (Exception e) {
			return e.getMessage();
		}
	}


}
