package testdata;

public class ExpectedData {

    //Expected Data
    public String expectedpagetitle;
    public String navigationURL;
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
                navigationURL = "http://www.google.co.uk/";
                expectedpagetitle = "Google";
                break;

            case GoogleLogin:
                expectedpagetitle = "Sign in";
                expectedFinalURL = "accounts";
                break;

            default:
                navigationURL = "";
                expectedpagetitle = "";
                expectedFinalURL = "accounts";
                break;
        }
    }
}