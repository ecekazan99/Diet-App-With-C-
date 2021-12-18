import java.io.IOException;
/*
 * This abstract class is created for sign up steps checks.
*/
public abstract class AbsSignUpControl{
	
	// When a new user enters a user name that exist already in our system return false, otherwise return true.
	public abstract boolean usernameRepetation() throws IOException;
	
	/*
	 * Because of our programs nature while new user tries sign up we have to control his or her selections.
	 * For example, when an user selects loss weight programme although his or her target weight is bigger.
	 * This is a logical error and this method returns related error messages in string format.
	*/
	public abstract String contentDependedLogicalErrors();
	
	// When user selects a wrong file format this returns error messages. 
	public abstract String inputFileFormatError();
	
	// When all controls successfully completed. In conclusion new user data written to file with this method.
	public abstract void writeInfoToUserDataFile();
	
}
