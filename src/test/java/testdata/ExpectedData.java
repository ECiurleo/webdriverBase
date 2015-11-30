package testdata;

public class ExpectedData {

    //Expected Data
    public String expectedpagetitle;

    public enum TestData
    {
        LowCostHolidaysHome,LowCostHolidaysLogin;
    }

    @SuppressWarnings("incomplete-switch")
    public ExpectedData (TestData testDataType)
    {
        switch(testDataType) {
            case LowCostHolidaysHome:
                expectedpagetitle = "lowcostholidays | Search for cheap holidays, all inclusive holiday deals and city breaks";
                break;

            case LowCostHolidaysLogin:
                expectedpagetitle = "lowcostholidays | Search for cheap holidays, all inclusive holiday deals and city breaks";
                break;

            default:
                expectedpagetitle = "";
                break;
        }
    }
}