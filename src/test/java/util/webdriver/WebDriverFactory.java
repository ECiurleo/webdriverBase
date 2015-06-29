package util.webdriver;

import java.net.MalformedURLException;
import java.net.URL;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import util.Browser;

/*
 * Factory to instantiate a WebDriver object. It returns an instance of the driver (local invocation) or an instance of RemoteWebDriver
 */
public class WebDriverFactory {

    /* Browsers constants */
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String OPERA = "opera";
    public static final String INTERNET_EXPLORER = "iexplore";
    public static final String HTML_UNIT = "htmlunit";
    public static final String IPHONE = "iphone";

    /* Platform constants */
    public static final String WINDOWS = "windows";
    public static final String ANDROID = "android";
    public static final String XP = "xp";
    public static final String VISTA = "vista";
    public static final String MAC = "mac";
    public static final String LINUX = "linux";

    /*
     * Factory method to return a RemoteWebDriver instance given the url of the
     * Grid hub and a Browser instance.
     * 
     * @param gridHubUrl : grid hub URI
     * 
     * @param browser : Browser object containing info around the browser to hit
     * 
     * @return RemoteWebDriver
     */
    public static WebDriver getInstance(String gridHubUrl, Browser browser) {

        WebDriver driver = null;

        DesiredCapabilities capability = new DesiredCapabilities();
        String browserName = browser.getName();
        capability.setJavascriptEnabled(true);

        // In case there is no Hub
        if (gridHubUrl == null || gridHubUrl.length() == 0) {
            return getInstance(browserName);
        }

        if (CHROME.equals(browserName)) {
            capability = DesiredCapabilities.chrome();
        } else if (FIREFOX.equals(browserName)) {
            capability = DesiredCapabilities.firefox();
            capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
            

            return driver;
            
        } else if (OPERA.equals(browserName)) {
            capability = DesiredCapabilities.opera();
        } else if (ANDROID.equals(browserName)) {
            capability = DesiredCapabilities.android();
        } else if (IPHONE.equals(browserName)) {
            capability = DesiredCapabilities.iphone();
        } else {

            capability = DesiredCapabilities.htmlUnit();
            driver = new HtmlUnitDriver(true);

            return driver;
        }

        capability = setVersionAndPlatform(capability, browser.getVersion(), browser.getPlatform());

        // Create Remote WebDriver
        try {
            driver = new RemoteWebDriver(new URL(gridHubUrl), capability);
        } catch (MalformedURLException e) {
            Reporter.log("Unable to connect RemoteWebDriver");
            e.printStackTrace();
        }

        return driver;
    }

    /*
     * Factory method to return a WebDriver instance given the browser to hit
     * 
     * @param browser : String representing the local browser to hit
     * 
     * @return WebDriver instance
     */
    public static WebDriver getInstance(String browser) {
        WebDriver driver = null;

        if (FIREFOX.equals(browser)) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setEnableNativeEvents(false);
            driver = new FirefoxDriver(profile);
        }
        else if (CHROME.equals(browser)) {
            setChromeDriver();
            driver = new ChromeDriver();
        }
        else if (INTERNET_EXPLORER.equals(browser)) {
            setInternetExplorerDriver();
            driver = new InternetExplorerDriver();
        }
        else if (OPERA.equals(browser)) {
            driver = new OperaDriver();
        }
        else {
        	driver = new HtmlUnitDriver(true);
        }

        return driver;
    }

    /*
     * Helper method to set version and platform for a specific browser
     * 
     * @param capability : DesiredCapabilities object coming from the selected
     * browser
     * 
     * @param version : browser version
     * 
     * @param platform : browser platform
     * 
     * @return DesiredCapabilities
     */
    private static DesiredCapabilities setVersionAndPlatform(DesiredCapabilities capability, String version, String platform) {
        if (MAC.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.MAC);
        } else if (LINUX.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.LINUX);
        } else if (XP.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.XP);
        } else if (VISTA.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.VISTA);
        } else if (WINDOWS.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.WINDOWS);
        } else if (ANDROID.equalsIgnoreCase(platform)) {
            capability.setPlatform(Platform.ANDROID);
        } else {
            capability.setPlatform(Platform.ANY);
        }

        if (version != null) {
            capability.setVersion(version);
        }
        return capability;
    }

    /*
     * Helper method to set ChromeDriver location into the right system property
     */
    private static void setChromeDriver() {
        String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
        String chromeBinary = os.equals("win") ? "src/test/resources/drivers/chrome/chromedriver.exe" : "/usr/local/bin/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeBinary);
        System.setProperty("DISPLAY", ":19");
    }

    private static void setInternetExplorerDriver() {
        String os = System.getProperty("os.arch").toLowerCase(); //.substring(0, 3);
        String ieBinary = "src/test/resources/drivers/ie/" + (os.contains("64") ? "x64" : "x86") + "/IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", ieBinary);
    }

}