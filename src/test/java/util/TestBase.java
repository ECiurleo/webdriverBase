package util;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import util.webdriver.WebDriverFactory;

/** Base class for all the test classes */
public class TestBase
{

    public WebDriver driver;
    public String gridHubUrl;
    public Browser browser;
    public int waitSeconds;
    public EnvironmentDetailsConfig testURLs;
    public WebDriverFactory WebDriverFactory;


    /** Before */
    @Parameters({"browser", "waitSeconds", "environment", "maxInstances", "maxSession" })
    @BeforeSuite(alwaysRun = true)
    public void init(@Optional("firefox") String browserName, @Optional("30") int waitSeconds, @Optional("dev") String environment, @Optional("1") String maxInstances, @Optional("1") String maxSession)
    {
        // Check for non-existent parameters and defaults set
        System.err.println("Entering init");
        testURLs = new EnvironmentDetailsConfig(EnvironmentDetailsConfig.NameOfEnvironment.valueOf(environment.toUpperCase()));
        gridHubUrl = PropertyLoader.loadProperty("grid2.hub");
        this.waitSeconds = waitSeconds;
        browser = new Browser();
        browser.setName(browserName);
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));
        browser.setMaxInstances(PropertyLoader.loadProperty("browser.maxInstances"));
        browser.setMaxSession(PropertyLoader.loadProperty("browser.maxSession"));
        driver = WebDriverFactory.getInstance(gridHubUrl, browser);
        System.err.println("Exiting init");
    }


    //Make sure everything is closed at the end of the tests.
    @AfterSuite(alwaysRun = true)
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }
        System.err.println("Teardown - Exiting");
    }
    ////////////////////////////////////////////////


    public void gotoBaseURL() {
        driver.get(testURLs.WEBAPP_URL);
    }
}


