package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;
	public Logger logger; //log4j
	public Properties prop;

	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException 
	{
		logger = LogManager.getLogger(this.getClass());	
		logger.info("***************** inside before class **********************");

		// Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		prop = new Properties();
		prop.load(file);


		//checking whether the environment is remote or local
		if(prop.getProperty("Envirnmnt").equalsIgnoreCase("remote")) {
			logger.info("***************** inside remote driver **********************");

			DesiredCapabilities capbilities = new DesiredCapabilities();

			if(os.equalsIgnoreCase("windows"))
			{
				capbilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("Mac")) 
			{
				capbilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux")) 
			{
				capbilities.setPlatform(Platform.LINUX);
			}
			else
			{
				logger.info("No Matching Operating System");
				return;
			}

			//			switch(os.toLowerCase())
			//			{
			//			case "windows" : capbilities.setPlatform(Platform.WIN11); break;
			//			case "mac" : capbilities.setPlatform(Platform.MAC); break;
			//			case "linux" : capbilities.setPlatform(Platform.LINUX); break;
			//			default: logger.info("No Matching Operating System");return;
			//			}

			switch(br.toLowerCase())
			{
			case "chrome": capbilities.setBrowserName("chrome"); break;
			case "firefox": capbilities.setBrowserName("firefox"); break;
			case "edge": capbilities.setBrowserName("MicrosoftEdge"); break;
			default: logger.info("No matching browser"); return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capbilities);
		}


		if(prop.getProperty("Envirnmnt").equalsIgnoreCase("local"))
		{
			logger.info("***************** inside local driver **********************");
			switch(br.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "firefox": driver = new FirefoxDriver();break;
			case "edge": driver = new EdgeDriver();break;
			default: logger.info("No matching browser"); return;
			}
		}

		sdriver = driver;
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("appURL")); 
		logger.info("***************** url loaded  **********************");
		driver.manage().window().maximize();
		logger.info("***************** maximized window **********************");
	}

	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void tearDown() 
	{
		logger.info("************************ After class**********************");
		driver.quit();
	}

	public String randomString(int num) {
		String generatedString = RandomStringUtils.randomAlphabetic(num);
		return generatedString;

	}

	public String randomNumber(int digits) {
		String generatedNumber = RandomStringUtils.randomNumeric(digits);
		return generatedNumber;
	}

	public String randomPassword(int digits) {
		String generatedPwd = RandomStringUtils.randomAlphanumeric(10);
		return generatedPwd;
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File targetFile=new File("./screenshots/" + tname + "_" + timeStamp + ".png");

		FileUtils.copyFile(sourceFile, targetFile);
		return targetFile.getAbsolutePath();
	}
}
