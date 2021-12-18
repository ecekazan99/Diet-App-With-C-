import java.io.IOException;
/* 
 * This abstract class is created for sign in steps checks.
*/
public abstract class AbsSignInControl {
	// Controls user name existence.
	public abstract boolean userNotFoundError(String usernameInput) throws IOException;
	// Controls password and password validation and harmony.
	public abstract boolean usernameAndPasswordControl();
}