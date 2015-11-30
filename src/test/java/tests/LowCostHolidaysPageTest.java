package tests;


import pageelements.LowCostHolidaysHomePageElements;
import testdata.ExpectedData;
import util.TestBase;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LowCostHolidaysPageTest extends TestBase {

    public ExpectedData LowCostHolidaysLogin = new ExpectedData(ExpectedData.TestData.LowCostHolidaysLogin);

    @Test(description = "Clicking on LowCost Holidays Sign in button navigates to correct page")
	public void clickGoogleSignInbtn() {
        gotoBaseURL();
        assertTrue(driver.findElement(LowCostHolidaysHomePageElements.ManageMyBookingBtn).isDisplayed(), "Sign in button is not present on the page when expected");
        driver.findElement(LowCostHolidaysHomePageElements.ManageMyBookingBtn).click();
        assertTrue(driver.getTitle().contains(LowCostHolidaysLogin.expectedpagetitle),"The page title for the page returned is " + driver.getTitle() + " when " + LowCostHolidaysLogin.expectedpagetitle + " was expected");
	}

    @Test(description = "Perform a flight search between Bristol and Gdansk where a warning is displayed")
    public void searchBristoltoGdansk() {
        gotoBaseURL();
        driver.findElement(LowCostHolidaysHomePageElements.FromDropdown).click();
        driver.findElement(LowCostHolidaysHomePageElements.FromDropdown_Bristol).click();
        driver.findElement(LowCostHolidaysHomePageElements.ToField).sendKeys("Gdansk");

        driver.findElement(LowCostHolidaysHomePageElements.SearchBtn).click();
        assertTrue(driver.findElement(LowCostHolidaysHomePageElements.WarningMessage).isDisplayed(), "");
   }

    @Test(description = "Perform a flight search between London and Algarve where no warning is displayed")
    public void searchLondontoAlgarve() {
        gotoBaseURL();
        driver.findElement(LowCostHolidaysHomePageElements.FromDropdown).click();
        driver.findElement(LowCostHolidaysHomePageElements.FromDropdown_London).click();
        driver.findElement(LowCostHolidaysHomePageElements.ToField).sendKeys("Algarve");

        driver.findElement(LowCostHolidaysHomePageElements.SearchBtn).click();
        assertFalse(driver.findElement(LowCostHolidaysHomePageElements.WarningMessage).isDisplayed(), "");
    }
}