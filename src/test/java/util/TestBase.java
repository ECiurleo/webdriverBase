package util;


import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import testdata.ExpectedData;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/** Base class for all the test classes */
public class TestBase
{

	public WebDriver driver;
    public String gridHubUrl;
    public Browser browser;
    public int waitSeconds;
    public ExpectedData navigationURL;

    /** Before */
    @Parameters({"browser", "waitSeconds", "environment", "maxInstances", "maxSession" })
    @BeforeSuite(alwaysRun = true)
    public void init(@Optional("firefox") String browserName, @Optional("30") int waitSeconds, @Optional("dev") String environment, @Optional("1") String maxInstances, @Optional("1") String maxSession)
    {
    // Check for non-existent parameters and defaults set
        System.err.println("Entering init");
        gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
        this.waitSeconds = waitSeconds;
        browser = new Browser();
        browser.setName(browserName);
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));
        browser.setMaxInstances(PropertyLoader.loadProperty("browser.maxInstances"));
        browser.setMaxSession(PropertyLoader.loadProperty("browser.maxSession"));
        System.err.println("Exiting init");
    }

	//Quit any existing browser before creating a new one for the next method to avoid conflicts
    @BeforeMethod(alwaysRun = true)
    public void driverclear()
    {
        if (driver != null)
        {
            driver.quit();
        }
        driver = new FirefoxDriver();
    }

    //Make sure everything is closed at the end of the tests.
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
        System.err.println("Teardown - Exiting");
    }
    ////////////////////////////////////////////////


}


