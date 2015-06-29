package testdata;

public class ExpectedData {

    //Expected Data
    public String expectedpagetitle;
    public String expectedFinalURL;

    public enum TestData
    {
        GoogleHome,GoogleLogin;
    }

    @SuppressWarnings("incomplete-switch")
    public ExpectedData (TestData testDataType)
    {
        switch(testDataType) {
            case GoogleHome:
                expectedpagetitle = "Google";
                break;

            case GoogleLogin:
                expectedpagetitle = "Sign in";
                expectedFinalURL = "accounts";
                break;

            default:
                expectedpagetitle = "";
                expectedFinalURL = "accounts";
                break;
        }
    }
}