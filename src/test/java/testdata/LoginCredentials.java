package testdata;

public class LoginCredentials {
	
	//Expected Data
	public String username;
	public String password;

	public enum PageType
	{
		VALIDLOGIN,INVALIDUSERNAME,INVALIDPASSWORD;
	}

	@SuppressWarnings("incomplete-switch")
	public LoginCredentials (PageType loginPageType)
	{
		switch(loginPageType)
		{
		case VALIDLOGIN:
			username = "username";
			password = "password";
		break;
		case INVALIDUSERNAME:
			username = "wrongwrongwrong";
			password = "password";
		break;
		case INVALIDPASSWORD:
			username = "username";
			password = "wrongwrongwrong";
		break;
		}
	}
}
