package com.w2a.zoho.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;

import com.w2a.zoho.extentListners.ExtentListeners;
import com.w2a.zoho.utilities.Constants;
import com.w2a.zoho.utilities.DriverFactory;
import com.w2a.zoho.utilities.DriverManager;

public class BaseTest {
	
	private WebDriver driver;
	private Properties config=new Properties();
	private FileInputStream fis;
	public Logger log=Logger.getLogger(BaseTest.class);
	public boolean grid=false;
	
	private String defaultUserName;
	private String defaultPassword;
	
	
	public String getDefaultUserName() {
		return defaultUserName;
	}

	public void setDefaultUserName(String defaultUserName) {
		this.defaultUserName = defaultUserName;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	@BeforeSuite
	public void setUpFreameWork() {
		configureLogging();
		DriverFactory.setGridPath(Constants.GRID_PATH);
		DriverFactory.setConfigPropertyFile(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		
		if (System.getProperty("os.name").contains("mac")) {
			DriverFactory.setChromeDriverExePath(System.getProperty("user.dir")+Constants.CHROME_PATH_MAC);
			DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir")+Constants.GECKO_PATH_MAC);
		}else {
			DriverFactory.setChromeDriverExePath(System.getProperty("user.dir")+Constants.CHROME_PATH_WINDIOWS);
			DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir")+Constants.GECKO_PATH_WINDIOWS);
		}
		
		try {
			fis=new FileInputStream(DriverFactory.getConfigPropertyFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			config.load(fis);
			log.info("Configuration File Loaded!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void configureLogging() {
		String log4jConfigFile=System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}
	

	
	public void logInfo(String message) {
		ExtentListeners.testReport.get().info(message);
	}
	
	public void openBrowser(String browser)  {
		
		
		if (System.getenv("ExecutionType")!=null && System.getenv("ExecutionType").equals("Grid")){
			grid=true;
		}
		DriverFactory.setRemote(grid);
		System.out.println("Parameter Selection : " + grid);
		System.out.println("Is Remote : " + DriverFactory.isRemote());
		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap=null;
			if (browser.equals("firefox")) {
				cap=DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);
			}else if (browser.equals("chrome")) {
				cap=DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			}else if (browser.equals("ie")) {
				cap=DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplorer");
				cap.setPlatform(Platform.WINDOWS);
			}
			
			try {
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
				log.info("Starting the session on grid!!!");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			if(browser.equals("firefox")){
				System.setProperty("webdriver.gecko.driver", DriverFactory.getGeckoDriverExePath());
				driver=new FirefoxDriver();
				log.info("Firefox Launched!!!");
			}else if(browser.equals("chrome")){
				System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverExePath());
				Map<String, Object> prefs=new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manage_enabled", false);
				ChromeOptions options=new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				
				driver=new ChromeDriver(options);
				log.info("Chrome Launched!!!");
			}
		}
		
		
		
		
		DriverManager.setDriver(driver);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		setDefaultUserName(config.getProperty("defaultUserName"));
		setDefaultPassword(config.getProperty("defaultPassword"));
	}
	
	public void quit() {
		DriverManager.getDriver().quit();
	}
}


