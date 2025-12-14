package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;  //Log4j
public Properties p;
	
	@BeforeClass(groups= {"Sanity","Master","datadriven","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws Exception
	{
		
		//loading config proprties file
		 FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		 
		logger=LogManager.getLogger(this.getClass());  //log4j2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities =new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
				
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
					
			}
			else if(os.equalsIgnoreCase("Linux"))
				{
				capabilities.setPlatform(Platform.LINUX);
				}
			else
			{
				System.out.println("no matching os");
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome"); break;
			case "firefox":capabilities.setBrowserName("firefox");	break;
			case "Edge":capabilities.setBrowserName("Edge");break;
			default: System.out.println("No matching browser");return;
			}
			
			driver =new RemoteWebDriver(new URL("http://192.168.1.12:4444/wd/hub"),capabilities);
			
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))	
		{
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "Edge": driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			case "default": System.out.println("Invalid browser name"); return;
			
			}
			
			
		}
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("Appurl"));  // reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Master","datadriven","Regression"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomstring()
	{
		String generateString = RandomStringUtils.secure().nextAlphabetic(5);
		//System.out.println(generateString);
		return generateString;
	}
	
	public String randomNumber()
	{
		String generateNumber = RandomStringUtils.secure().nextNumeric(10);
		return generateNumber;
	}
	
	public String randomAlphanumeric()
	{
		String generateAlphanumeric = RandomStringUtils.secure().nextAlphanumeric(8);
		System.out.println(generateAlphanumeric+"@");
		return generateAlphanumeric+"@";
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp;
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;

		}
	
	

}
