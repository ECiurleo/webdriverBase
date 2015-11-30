package pageelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LowCostHolidaysHomePageElements {

public static By ManageMyBookingBtn = By.xpath("//*[@id=\"divHeader2\"]/div/div/div/div[5]/a");

public static By FromDropdown = By.id("sddDepartureAirportID");
public static By ToField = By.id("divDestinationAutoComplete");
public static By SearchBtn = By.id("btnSearch");
public static By MonthDropdown = By.id("calDepartureDate_MonthYear");
public static By DayDropdown = By.id("calDepartureDate_Day");

public static By WarningMessage = By.className("warning");

public static By FromDropdown_Bristol = By.xpath("//*[@id=\"sddDepartureAirportID\"]//option[11]");
public static By FromDropdown_London = By.xpath("//*[@id=\"sddDepartureAirportID\"]//option[11]");

}

