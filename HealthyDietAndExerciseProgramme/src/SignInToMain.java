import java.io.IOException;
/*
 * This class extends AbsMidWareCheck abstract class.
 * When user selects sign in option, 
 * If user doesn't enter to system correctly, program displays warning messages.
*/
public class SignInToMain extends AbsMidWareCheck{
	
	// Attribute
	private SignInExistenceChecks sec;
	
	// Constructor
	public SignInToMain(String userName,String password) throws IOException {
		super(userName,password);
		sec = new SignInExistenceChecks();
		fromSignInToMainGaneralChecker(userName,password);
	}
	
	// This inner class extends AbsSignInControl abstract class to checks user inputs.
	public class SignInExistenceChecks extends AbsSignInControl {
		
		// Controls user name existence.
		@Override
		public boolean userNotFoundError(String username){
				ReadFile rdf = new ReadFile();
			 	try {
					if(rdf.userNameComp(username)) {
						return true;
					}
					else {
						setUserWarning("username not found");
						return false;
					}
				} catch (IOException e) {
					
					e.printStackTrace();
					return false;
				}
		}
	
		// Controls password and password validation and harmony.
		@Override
		public boolean usernameAndPasswordControl() {
			ReadFile rdf = new ReadFile();
			try {
				if(rdf.userNameAndPassCont(getUserName(),getPassword())) {
					return true;
				}
				else {
					setPasswordWarning("Wrong Password");
					return false;
				}
			}catch(IOException e) {
				
				e.printStackTrace();
				return false;
			}
			
		}
	}
	
	/*
	 * This method is general checker method for sign in option. 
	 * This method returns true if user name and password types are correct.  
	 * Otherwise, returns false.
	*/
	public boolean fromSignInToMainGaneralChecker(String username,String password) throws IOException {
		
		 if(warnAboutUsernameType(username) && notValidPassword(password) && getSec().userNotFoundError(username) && getSec().usernameAndPasswordControl()) {
			 return true;
		 }
		 return false;
		 
	}

	// Getter and Setter
	public SignInExistenceChecks getSec() {
		return sec;
	}

	public void setSec(SignInExistenceChecks sec) {
		this.sec = sec;
	}	
	
}
