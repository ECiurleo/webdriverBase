package util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import util.webdriver.WebDriverFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
    @BeforeClass(alwaysRun = true)
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

    //clear cookies between tests for a clean base on which to run the tests
    @BeforeMethod(alwaysRun = true)
    public void clearCookies() {
        if (driver == null) {
            driver = WebDriverFactory.getInstance(gridHubUrl, browser);
            driver.manage().timeouts().implicitlyWait(this.waitSeconds, TimeUnit.SECONDS);
        }
        driver.manage().deleteAllCookies();
        System.err.println("Cookies Cleared");

    }

    //Make sure everything is closed at the end of the tests.
    @AfterClass(alwaysRun = true)
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


    //Locator Methods
    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void switchTab() {
        ArrayList<String> tabs = new ArrayList (driver.getWindowHandles());
        System.out.println(tabs.size());
        driver.switchTo().window(tabs.get(1));
    }

    //Input methods
    public void type(By by, String testdata) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(testdata);
    }

    //action methods
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    /** Assertions */
    public boolean isLoaded() {

        //Wait for Page to Load
        System.err.println("Page Loading");
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        System.err.println("Page Loaded");
        System.err.println(driver.getCurrentUrl());

        /* Check to see if a 404 error page is loaded*/
        Assert.assertFalse(driver.getPageSource().contains("We are sorry, that page has not been found"), "A 404 error page has been returned for " + this.getClass().getSimpleName());

        return true;
    }

}


