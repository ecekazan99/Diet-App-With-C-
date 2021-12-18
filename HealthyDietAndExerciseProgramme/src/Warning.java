/*
 * This interface created for common checks.
 * This interface is implemented by AbsMidWareCheck.
*/
public interface Warning {
	
	/*
	 * Checking length and character properties thanks to comparing ASCII values.
	 * Thus defines related error messages if exists and returns a boolean value which tells user name is acceptable or not.
	*/
	public boolean warnAboutUsernameType(String strUserName);
	
	/*
	 * Checking length and character properties thanks to comparing ASCII values.
	 * Thus defines related error messages if exists and returns a boolean value which tells password is acceptable or not.
	*/
	public boolean notValidPassword(String strPassword);
}
