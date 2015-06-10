package tests;


import pageelements.GooglePageElements;
import testdata.ExpectedData;
import util.TestBase;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class GooglePageTest extends TestBase {

    public ExpectedData googlehome = new ExpectedData(ExpectedData.TestData.GoogleHome);
    public ExpectedData googlelogin = new ExpectedData(ExpectedData.TestData.GoogleLogin);

    @Test(description = "Clicking on Google Sign in button navigates to correct page")
	public void clickGoogleSignInbtn() {
        driver.navigate().to(googlehome.navigationURL);
        assertTrue(driver.findElement(GooglePageElements.signInBtn).isDisplayed(), "Sign in button is not present on the page when expected");
        driver.findElement(GooglePageElements.signInBtn).click();
        assertTrue(driver.getTitle().contains(googlelogin.expectedpagetitle),"The page title for the page returned  is " + driver.getTitle() + " when " + googlelogin.expectedpagetitle + "was expected");
	}

}