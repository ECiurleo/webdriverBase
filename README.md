# webdriverBase
A base Selenium WebDriver suite written in Java and TestNG

##Setup
You need to make sure that iexplore.exe, chrome.exe and firefox.exe are on your %PATH%.

You can also configure some of the behaviour of the tests from the custom_tests.xml file;
- browser - one of firefox, iexplore, chrome
- environment - base URL generation
- waitSeconds - the number of seconds to use in the implicit wait and waitForElement methods.
- maxInstances - number of instances (to be used when running on build server)
- maxSession - number of sessions (to be used when running on build server)

##Project Structure
The project follows the standard Maven structure;
- all the tests should go in the src/test/java/tests folder. Tests should inherit from the TestBase class.
- all the expected test data should go in the src/test/java/testdata folder.
- all the page elements should go in the src/test/java/pageelements folder.
- all base and util functions should go in the src/test/java/util folder.
- Additional browser support can be added in src/test/java/util/webdriver/WebDriverFactory

TestBase class provides the driver for each test and also it takes care of closing the driver when all the tests are executed in the suite within @Before and @After annotations.

For more info around this pattern, read this wiki page: http://code.google.com/p/selenium/wiki/PageObjects


