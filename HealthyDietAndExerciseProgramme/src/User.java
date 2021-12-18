import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/*
 * This class created for keeping user's data in sign in, sign up.  
*/
public class User implements Comparable<User> {
	
	// Attributes
	private String username;
	private String password;
	private int programmeSelection;
	private Date beginDate;
	private Date endDate;
	private double beginWeight;
	private double targetWeight;
	private double currentWeight;
	private int height; 
	private int age;
	private String gender;
	private int dietSelection;
	private int nutritionDisese;
	private double progressScore;
	private int menuID;
	private List<ProgressData> progressData = new ArrayList<ProgressData>();
	private double bmi;
	private double sleepingTime;
	private int dailyCaloryNeed;
	private double dailyWaterNeed;
	
	// If user selects loss weight or gain weight program selection, program takes a target weight from user.
	public User(String username, String password, int programmeSelection,Date beginDate, Date endDate, double beginWeight, double targetWeight,int height, int age, String gender,
			 int dietSelection, int nutritionDisese) throws UnsupportedEncodingException, FileNotFoundException {
		this.username = username;
		this.password = password;
		this.programmeSelection = programmeSelection;
		this.beginDate=beginDate;
		this.endDate= endDate;
		
		this.beginWeight = beginWeight;
		this.currentWeight = getCurrentWeight();
		this.targetWeight = targetWeight;
		this.height = height;
		this.age = age;
		this.gender = gender;
		this.dietSelection = dietSelection;
		this.nutritionDisese = nutritionDisese;
		this.progressScore = 0;
		this.progressData.add(new ProgressData (beginDate,beginWeight));
		calculateValuesForUser();
	}
	
	// If user selects stay fit program selection, program doesn't ask a target weight from user.
	public User(String username, String password, int programmeSelection,Date beginDate, Date endDate, double beginWeight,int height, int age, String gender,
			 int dietSelection, int nutritionDisese) throws UnsupportedEncodingException, FileNotFoundException {
		this.username = username;
		this.password = password;
		this.programmeSelection = programmeSelection;
		this.beginDate=beginDate;
		this.endDate= endDate;
		this.beginWeight = beginWeight;
		this.currentWeight = getCurrentWeight();
		this.height = height;
		this.age = age;
		this.gender = gender;
		this.dietSelection = dietSelection;
		this.nutritionDisese = nutritionDisese;
		this.progressScore = 0;
		this.targetWeight = beginWeight;
		this.progressData.add(new ProgressData (beginDate,beginWeight));
		calculateValuesForUser();
		
	}
	@Override
	public int compareTo(User o) {
		if(this.getProgrammeSelection()==2) {
			if(Math.abs(this.beginWeight-this.currentWeight )== Math.abs(o.beginWeight-o.currentWeight)) return 0;
			else if(Math.abs(this.beginWeight-this.currentWeight)> Math.abs(o.beginWeight-o.currentWeight)) return 1;
			else return -1;
		}
		else if(this.getProgrammeSelection()==1) {
			if(this.currentWeight-this.beginWeight ==o.currentWeight-o.beginWeight) return 0;
			else if(this.currentWeight-this.beginWeight>o.currentWeight-o.beginWeight) return 1;
			else return -1;
		}
		else {
			if(this.beginWeight-this.currentWeight ==o.beginWeight-o.currentWeight) return 0;
			else if(this.beginWeight-this.currentWeight>o.beginWeight-o.currentWeight) return 1;
			else return -1;
		}
		
	}
	
	@Override
	public String toString() {
		return "User [name=" + username + ", beginWeight=" + beginWeight +", currentWeight="+currentWeight+"]";
	}
	
	// This method for editing user weight and BMI.
	public void editCurrentData (Date date, double weight) {
		//progressData.add(new ProgressData(date, weight));
		this.currentWeight = weight;
		calculateValuesForUser();
	}
	
	// This method calculates user's BMI, daily sleeping time, daily water need, daily calory need.
	private void calculateValuesForUser () {
		calculatorOfBMI();
		this.sleepingTime =sleepingTimeCalculator();
		this.dailyWaterNeed = dailyWaterNeedCalculator();
		this.dailyCaloryNeed = dailyCaloryNeedCalculator();
	}

	// This method calculates user's BMI
	private void calculatorOfBMI() {
	
		setBmi((double) (currentWeight / ( (( ((double)height) / 100) * (((double)height) / 100)))));  
	}
	
	// This method calculates daily sleeping time
	private double sleepingTimeCalculator () {
		
		return (double) (1/bmi) * age + 6; 
	}
	
	// This method calculates daily water need
	private double dailyWaterNeedCalculator() {
		if(age<=25) {
			return 3.0;
		}
		else if(age>=60) {
			return 2.5;
		}
		else {
			return 2.75;
		}
	}
	
	// This method calculates daily calory need
	private int dailyCaloryNeedCalculator() {
		if(programmeSelection==0) {
			if(gender.equals("female") && (age>=18 && age<=25)) {
				return 1800 ;
			}
			else if (gender.equals("female") && (age>25 && age<=50)) {
				return 1700 ;
			}
			else if (gender.equals("female") && age>50){
				return 1600 ;
			}

			if(gender.equals("male") && (age>=18 && age<=25)) {
				return 2500 ;
			}
			else if (gender.equals("male") && (age>25 && age<=50)) {
				return 2300 ;
			}
			else if (gender.equals("male") && age>50){
				return 2000 ;
			}
			
		}
		else if (programmeSelection==1) {
			if(gender.equals("female") && (age>=18 && age<=25)) {
				return 2500 ;
			}
			else if (gender.equals("female") && (age>25 && age<=50)) {
				return 2400 ;
			}
			else if (gender.equals("female") && age>50){
				return 2300 ;
			}
			if(gender.equals("male") && (age>=18 && age<=25)) {
				return 3000 ;
			}
			else if (gender.equals("male") && (age>25 && age<=50)) {
				return 2800 ;
			}
			else if (gender.equals("male") && age>50){
				return 2700 ;
			}
			
		}
		else {
			if(gender.equals("female") && (age>=18 && age<=25)) {
				return 2400 ;
			}
			else if (gender.equals("female") && (age>25 && age<=50)) {
				return 2300 ;
			}
			else if (gender.equals("female") && age>50){
				return 2200 ;
			}
			if(gender.equals("male") && (age>=18 && age<=25)) {
				return 2900 ;
			}
			else if (gender.equals("male") && (age>25 && age<=50)) {
				return 2700 ;
			}
			else if (gender.equals("male") && age>50){
				return 2500 ;
			}
			
		}
		return 0;
	}

	// Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProgrammeSelection() {
		return programmeSelection;
	}

	public void setProgrammeSelection(int programmeSelection) {
		this.programmeSelection = programmeSelection;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getBeginWeight() {
		return beginWeight;
	}

	public void setBeginWeight(double beginWeight) {
		this.beginWeight = beginWeight;
	}

	public double getTargetWeight() {
		return targetWeight;
	}

	public void setTargetWeight(double targetWeight) {
		this.targetWeight = targetWeight;
	}

	public double getCurrentWeight() throws UnsupportedEncodingException, FileNotFoundException {
		ReadFile rdf = new ReadFile();
		return rdf.userCurrWeightFounder(username);
	}

	public void setCurrentWeight(double currentWeight) {
		this.currentWeight = currentWeight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getDietSelection() {
		return dietSelection;
	}

	public void setDietSelection(int dietSelection) {
		this.dietSelection = dietSelection;
	}

	public int getNutritionDisese() {
		return nutritionDisese;
	}

	public void setNutritionDisese(int nutritionDisese) {
		this.nutritionDisese = nutritionDisese;
	}

	public double getProgressScore() {
		return progressScore;
	}

	public void setProgressScore(double progressScore) {
		this.progressScore = progressScore;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

	public List<ProgressData> getProgressData() {
		return progressData;
	}

	public void setProgressData(List<ProgressData> progressData) {
		this.progressData = progressData;
	}

	public double getBmi() {
		return bmi;
	}

	public void setBmi(double bmi) {
		this.bmi = bmi;
	}

	public double getSleepingTime() {
		return sleepingTime;
	}

	public void setSleepingTime(double sleepingTime) {
		this.sleepingTime = sleepingTime;
	}

	public int getDailyCaloryNeed() {
		return dailyCaloryNeed;
	}
	public void setDailyCaloryNeed(int dailyCaloryNeed) {
		this.dailyCaloryNeed = dailyCaloryNeed;
	}
	public double getDailyWaterNeed() {
		return dailyWaterNeed;
	}

	public void setDailyWaterNeed(double dailyWaterNeed) {
		this.dailyWaterNeed = dailyWaterNeed;
	}
	
	
}