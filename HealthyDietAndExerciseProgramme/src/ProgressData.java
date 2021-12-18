/*
 * This class for creating progress list and using this list to draw graph.   
*/
public class ProgressData {
	
	// Attributes
	private Date date;
	private double userWeight;
	
	// Constructor
	public ProgressData(Date date, double userWeight) {
		this.date = date;
		this.userWeight = userWeight;
	}
	
	// Getters and Setters
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getUserWeight() {
		return userWeight;
	}
	public void setUserWeight(double userWeight) {
		this.userWeight = userWeight;
	}
}
