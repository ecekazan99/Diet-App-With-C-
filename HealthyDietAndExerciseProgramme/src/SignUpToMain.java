import java.io.IOException;
/*
* This class extends AbsMidWareCheck abstract class.
* When user selects sign up option, 
* If user doesn't enter inputs correctly, program displays related warning messages.
*/
public class SignUpToMain extends AbsMidWareCheck {
	
	// Constructor
	public SignUpToMain(String userName, String password) {
		super(userName, password);
	}
	
	// // This inner class extends AbsSignUpControl abstract class to checks user inputs.
	 class SignUpDependentChecks extends AbsSignUpControl{
		
		// Attributes
		private boolean  generalCheckOfRegist= true;
		private String userProgSelection="";
		private String elementWarn; 
		private int beginWeight;
		private int targetWeight;
		private boolean usernameRepeatErr;
		private int dateFirst;
		private int dateSecond;
		
		// When a new user enters a user name that exist already in our system return false, otherwise return true.
		@Override
		public boolean usernameRepetation() throws IOException {
			ReadFile rdf = new ReadFile();
			usernameRepeatErr =  !rdf.userNameComp(getUserName());
			return !rdf.userNameComp(getUserName());
		}

		/*
		 * Because of our programs nature while new user tries sign up we have to control his or her selections.
		 * For example, when an user selects loss weight programme although his or her target weight is bigger.
		 * This is a logical error and this method returns related error messages in string format.
		*/
		@Override
		public String contentDependedLogicalErrors() {
			String dateBasedError=" ";
			String programmeBasedError=" ";
			if(userProgSelection.equals("Loss Weight") &&  targetWeight >= beginWeight){
				programmeBasedError+="Target Weight Must Be Lower";
				setGeneralCheckOfRegist(false);
			}
			if(userProgSelection.equals("Gain Weight") &&targetWeight <= beginWeight ) {
				programmeBasedError+="Target Weight Must Be Bigger";
				setGeneralCheckOfRegist(false);
			}
			
			if(dateFirst>=dateSecond) {
				dateBasedError +="End date must be later";
				setGeneralCheckOfRegist(false);
			}
			
			return programmeBasedError + ";" + dateBasedError ;
		}

		// When user selects a wrong file format this returns error messages.
		@Override
		public String inputFileFormatError() {
			return "Please insert true file format as JPG";
		}
		
		/*
		 * This method is general checker method for sign up option. 
		 * This method returns true if user enters inputs correctly. 
		 * Otherwise, returns false.
		*/
		public boolean generalRegisCheck(String userNameR,String passwordR,String proggrammeSelecR,String currentWeightR,String targetWeightR,String begDateR,String endDateR) throws IOException {
			
			dateFirst= dateConverter(begDateR);
			dateSecond = dateConverter(endDateR);
		
			
			beginWeight = Integer.parseInt(currentWeightR);
			targetWeight = Integer.parseInt(targetWeightR);
			
			userProgSelection=  proggrammeSelecR;
			
			elementWarn = contentDependedLogicalErrors();
			
			if(warnAboutUsernameType(userNameR) & notValidPassword(passwordR)&  usernameRepetation() & isGeneralCheckOfRegist()) {
				
				return true;
			}
			else {
				
				return false;
			}
		}
		
		// This method converts date from string to integer.
		public int dateConverter(String element) {
			String result="";
			String[] divModel = element.split("-");
			for (int i = 0; i < divModel.length; i++) {
				result+=divModel[i];
			}
			return Integer.parseInt(result);
		}
	
		// Getters and Setters
		public String getUserProgSelection() {
			return userProgSelection;
		}

		public void setUserProgSelection(String userProgSelection) {
			this.userProgSelection = userProgSelection;
		}

		public int getBeginWeight() {
			return beginWeight;
		}

		public void setBeginWeight(int beginWeight) {
			this.beginWeight = beginWeight;
		}

		public int getEndWeight() {
			return targetWeight;
		}

		public void setEndWeight(int endWeight) {
			this.targetWeight = endWeight;
		}

		@Override
		public void writeInfoToUserDataFile() {
			// TODO Auto-generated method stub
			
		}

		public boolean isGeneralCheckOfRegist() {
			return generalCheckOfRegist;
		}

		public void setGeneralCheckOfRegist(boolean generalCheckOfRegist) {
			this.generalCheckOfRegist = generalCheckOfRegist;
		}

		public String getElementWarn() {
			return elementWarn;
		}

		public void setElementWarn(String elementWarn) {
			this.elementWarn = elementWarn;
		}

		public boolean isUsernameRepeatErr() {
			return usernameRepeatErr;
		}

		public void setUsernameRepeatErr(boolean usernameRepeatErr) {
			this.usernameRepeatErr = usernameRepeatErr;
		}
		

	}
}
