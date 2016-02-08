package testdata;


public enum LoginCredentials {

    ValidLogin("username", "Pa55word", null),
    InvalidCustomerID("-invalid-", "Pa55word", "Error - Invalid login details."),
    InvalidPassword("username", "-invalid-", "Error - Invalid login details."),
    ;

    private String loginUsername;
    private String loginPassword;
    private String errorMessage;


    private LoginCredentials(String loginUsername, String loginPassword, String errorMessage) {
        this.loginUsername = loginUsername;
        this.loginPassword = loginPassword;
        this.errorMessage = errorMessage;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
