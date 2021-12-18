import java.util.Comparator;

import javax.swing.JTextArea;
import javax.swing.JToggleButton;
/*
 * This class created for menu object which includes given in attributes partition.
*/
public class MenuAndValues {
	
	// Attributes
	private int menuID;
	private String dietSelecType;
	private String nutriDiseaseType;
	private int caloryValue;
	private int proteinValue;
	private int carbonhydratValue;
	private int fatValue;
	private String menuContent;
	private JTextArea textAreaOfMenu;
	private boolean theMenuSelected;

	// Constructor
	public MenuAndValues(int menuID, String dietSelecType, String nutriDiseaseType, int caloryValue, int proteinValue,
			int carbonhydratValue, int fatValue, String menuContent, JTextArea textAreaOfMenu) {
		this.menuID = menuID;
		this.dietSelecType = dietSelecType;
		this.nutriDiseaseType = nutriDiseaseType;
		this.caloryValue = caloryValue;
		this.proteinValue = proteinValue;
		this.carbonhydratValue = carbonhydratValue;
		this.fatValue = fatValue;
		this.menuContent = menuContent;
		this.textAreaOfMenu = textAreaOfMenu;
		theMenuSelected =false;
	}
	
	// Getters and Setters
	public JTextArea getTextAreaOfMenu() {
		return textAreaOfMenu;
	}


	public void setTextAreaOfMenu(JTextArea textAreaOfMenu) {
		this.textAreaOfMenu = textAreaOfMenu;
	}


	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public String getDietSelecType() {
		return dietSelecType;
	}
	public void setDietSelecType(String dietSelecType) {
		this.dietSelecType = dietSelecType;
	}
	public String getNutriDiseaseType() {
		return nutriDiseaseType;
	}
	public void setNutriDiseaseType(String nutriDiseaseType) {
		this.nutriDiseaseType = nutriDiseaseType;
	}
	public int getCaloryValue() {
		return caloryValue;
	}
	public void setCaloryValue(int caloryValue) {
		this.caloryValue = caloryValue;
	}
	public int getProteinValue() {
		return proteinValue;
	}
	public void setProteinValue(int proteinValue) {
		this.proteinValue = proteinValue;
	}
	public int getCarbonhydratValue() {
		return carbonhydratValue;
	}
	public void setCarbonhydratValue(int carbonhydratValue) {
		this.carbonhydratValue = carbonhydratValue;
	}
	public int getFatValue() {
		return fatValue;
	}
	public void setFatValue(int fatValue) {
		this.fatValue = fatValue;
	}
	public String getMenuContent() {
		return menuContent;
	}
	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}


	public boolean isTheMenuSelected() {
		return theMenuSelected;
	}


	public void setTheMenuSelected(boolean theMenuSelected) {
		this.theMenuSelected = theMenuSelected;
	}
}
