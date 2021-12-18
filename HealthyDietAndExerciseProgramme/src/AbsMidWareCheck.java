public abstract class AbsMidWareCheck implements Warning{
	/* 
	 * This abstract class implements Warning interface. 
	 * The attributes which are given below for username and password control and warning messages.
	 * When an error occurred the related warning message settles into userWarning and passwordWarning attributes to display user.
	*/
	private String userName;
	private String password;
	private String userWarning;
	private String passwordWarning;
	
	// Constructor (userWarning and passwordWarning are not auto-defined because of that it depends on user inputs)
	public AbsMidWareCheck(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/*
	 * This method overrides from Warning interface.
	 * Checking length and character properties thanks to comparing ASCII values.
	 * Thus defines related error messages if exists and returns a boolean value which tells username is acceptable or not.
	*/
	@Override
	public boolean warnAboutUsernameType(String userNameInput) {
		String unAcceptedInput = "";
		boolean checkChars = true;
		boolean checkLength = true;

		if (userNameInput.length() > 10 || userNameInput.length() < 5) {
			unAcceptedInput += "5 to 10 characters allowed.  ";
			checkLength = false;
		}
		boolean spaceChecker = true;

		for (int i = 0; i < userNameInput.length(); i++) {
			int asciiValue = (int) userNameInput.charAt(i);
			if (asciiValue < 48 || (asciiValue > 57 && asciiValue < 97) || asciiValue > 122) {
				if (asciiValue == 32 && spaceChecker == true) {
					unAcceptedInput += "(Space) ";
					spaceChecker = false;
				} else {
					unAcceptedInput += userNameInput.charAt(i) + " ";

				}
				checkChars = false;
			}
		}
		if (!checkLength && !checkChars) {
			unAcceptedInput += " invalid for username";

		} else if (checkLength && !checkChars) {
			unAcceptedInput += " invalid for username";
		}
		setUserWarning(unAcceptedInput);
		return checkChars && checkLength;
	}

	/*
	 * This method overrides from Warning interface.
	 * Checking length and character properties thanks to comparing ASCII values.
	 * Thus defines related error messages if exists and returns a boolean value which tells password is acceptable or not.
	*/
	@Override
	public boolean notValidPassword(String passwordInput) {
		String unAcceptedInput = "";
		boolean checkChars = true;
		boolean checkLength = true;

		if (passwordInput.length() > 12 || passwordInput.length() < 6) {
			unAcceptedInput += "6 to 12 characters allowed.  ";

			checkLength = false;
		}
		boolean spaceChecker = true;
		for (int i = 0; i < passwordInput.length(); i++) {
			int asciiValue = (int) passwordInput.charAt(i);
			if (asciiValue < 48 || (asciiValue > 57 && asciiValue < 97) || asciiValue > 122) {

				if (asciiValue == 32 && spaceChecker == true) {
					unAcceptedInput += "(Space) ";
					spaceChecker = false;
				} else {
					unAcceptedInput += passwordInput.charAt(i) + " ";

				}
				checkChars = false;

			}
		}
		if (!checkLength && !checkChars) {
			unAcceptedInput += " invalid for password";

		} else if (checkLength && !checkChars) {
			unAcceptedInput += " invalid for password";
		}
		setPasswordWarning(unAcceptedInput);
		return checkChars && checkLength;
	}

	// Getters and Setters
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserWarning() {
		return userWarning;
	}

	public void setUserWarning(String userWarning) {
		this.userWarning = userWarning;
	}

	public String getPasswordWarning() {
		return passwordWarning;
	}

	public void setPasswordWarning(String passwordWarning) {
		this.passwordWarning = passwordWarning;
	}
}
