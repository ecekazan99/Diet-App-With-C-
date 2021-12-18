import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JToggleButton;
/*
 * This class created for reading text files 
*/
public class ReadFile {
	
	// Attribute
	public static User currentOnlineUser;

	/*
	 * When exist user signs in to system correctly, user object is created and its
	 * attributes are taken from user data text file.
	 */
	public boolean readFileForExistUser(String username, String password) throws IOException {
		File file = new File("TextFiles/userDataRepository.txt");
		file.setWritable(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String readedLine;
		int firstLineRecog = 0;
		try {
			while ((readedLine = br.readLine()) != null) {
				if (firstLineRecog != 0) {
					String[] lineElements = readedLine.split(";");

					if (lineElements[0].equalsIgnoreCase(username) && lineElements[1].equalsIgnoreCase(password)) {
						if (lineElements[2].equals("2")) {
							String[] dateInterval = lineElements[3].split("-");
							String[] bgnDays = dateInterval[0].split("\\.");
							String[] endDays = dateInterval[1].split("\\.");
							Date bgnDate = new Date(Integer.parseInt(bgnDays[0]), Integer.parseInt(bgnDays[1]),
									Integer.parseInt(bgnDays[2]));
							Date endDate = new Date(Integer.parseInt(endDays[0]), Integer.parseInt(endDays[1]),
									Integer.parseInt(endDays[2]));
							currentOnlineUser = new User(lineElements[0], lineElements[1],
									Integer.parseInt(lineElements[2]), bgnDate, endDate,
									Double.parseDouble(lineElements[4]), Integer.parseInt(lineElements[5]),
									Integer.parseInt(lineElements[6]), lineElements[7],
									Integer.parseInt(lineElements[8]), Integer.parseInt(lineElements[9]));
							currentOnlineUser.setMenuID(Integer.parseInt(lineElements[11]));
							br.close();
							file.setWritable(false);
							return true;
						} else {
							String[] dateInterval = lineElements[3].split("-");
							String[] bgnDays = dateInterval[0].split("\\.");
							String[] endDays = dateInterval[1].split("\\.");
							Date bgnDate = new Date(Integer.parseInt(bgnDays[0]), Integer.parseInt(bgnDays[1]),
									Integer.parseInt(bgnDays[2]));
							Date endDate = new Date(Integer.parseInt(endDays[0]), Integer.parseInt(endDays[1]),
									Integer.parseInt(endDays[2]));
							String[] weightInterval = lineElements[4].split("-");

							currentOnlineUser = new User(lineElements[0], lineElements[1],
									Integer.parseInt(lineElements[2]), bgnDate, endDate,
									Double.parseDouble(weightInterval[0]), Double.parseDouble(weightInterval[1]),
									Integer.parseInt(lineElements[5]), Integer.parseInt(lineElements[6]),
									lineElements[7], Integer.parseInt(lineElements[8]),
									Integer.parseInt(lineElements[9]));
							currentOnlineUser.setMenuID(Integer.parseInt(lineElements[11]));
							br.close();
							file.setWritable(false);
							return true;
						}
					}

				}
				firstLineRecog++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		br.close();
		file.setWritable(false);
		return false;
	}

	// When user selects sign in , program checks existence of this user' name in
	public boolean userNameComp(String userName) throws IOException {
		File file = new File("TextFiles/userDataRepository.txt");
		file.setWritable(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			String[] lineElements = currentLine.split(";");
			String currUserName = lineElements[0];
			if (currUserName.equals(userName)) {
				br.close();
				file.setWritable(false);
				return true;
			}
		}
		br.close();
		file.setWritable(false);
		return false;
	}

	// When user selects sign in , program checks validation of user name and
	// password in text file.
	public boolean userNameAndPassCont(String userName, String password) throws IOException {
		File file = new File("TextFiles/userDataRepository.txt");
		file.setWritable(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			String[] lineElements = currentLine.split(";");
			String currUserName = lineElements[0];
			if (currUserName.equals(userName) && password.equals(lineElements[1])) {
				br.close();
				file.setWritable(false);
				return true;
			}
		}
		br.close();
		file.setWritable(false);
		return false;
	}

	// When new user selects sign up and registered correctly, user's data are written into the text file.
	public void newUserWriter(String userNameToR,String passwordR,int progSelecR,String begAndEndDateR,String begAndEndWightR,int heightR,int ageR,
			String gendeR,int dietSelecR,int nutritionalDiseas,double progressScoreR,int currentMenuIdR) throws IOException {
		File file = new File("TextFiles/userDataRepository.txt");
		file.setWritable(true);
		
	    FileWriter fw = new FileWriter(file,true); //the true will append the new data
	    fw.write(userNameToR + ";" +passwordR + ";" + progSelecR + ";" +begAndEndDateR + ";" +begAndEndWightR +";"+ heightR + ";" + ageR +";" + gendeR+";" +
	    dietSelecR + ";" + nutritionalDiseas +";" + progressScoreR + ";" + currentMenuIdR+"\r\n" );//appends the string to the file
	    file.setWritable(false);
	    fw.close();
	    
	    File file2 = new File("TextFiles/usernameBasedProgress.txt");
		file2.setWritable(true);
	    FileWriter progWriter = new FileWriter(file2,true);
	    String dateForProg[] = begAndEndDateR.split("-");
	    String weightForProg[] = begAndEndWightR.split("-");
	    progWriter.write(userNameToR+";"+dateForProg[0]+"-"+weightForProg[0]+"\r\n");
	    file2.setWritable(false);
	    progWriter.close();
	    
	}
	
	/*
	 * If user selects a menu from menus screen, this menu is founded by menu id in menu text file. This method returns selected menu.
	 * Otherwise, returns empty.
	 */
	public String userSavedMenuReaded(int menuID) throws IOException {
		File file = new File("TextFiles/menuData.txt");
		file.setWritable(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String currentLine;
		String foundSavedMenu = "";
		int firstLineRecog = 0;
		while ((currentLine = br.readLine()) != null) {
			if (firstLineRecog != 0) {
				String[] lineElements = currentLine.split(";");
				int currMenuID = Integer.parseInt(lineElements[0]);

				if (currMenuID == menuID) {
					System.out.println("Menu Id" + currMenuID);
					foundSavedMenu += ("Menu Id:" + lineElements[0] + "\r\n");
					foundSavedMenu += (nutriDiseInterp(Integer.parseInt(lineElements[2])) + " and "
							+ dietSelecInterpret(Integer.parseInt(lineElements[1])) + "\r\n");
					foundSavedMenu += "Nutritional Values :\r\n";
					foundSavedMenu += ("Calory :" + lineElements[3] + "\r\n");
					foundSavedMenu += ("Protein:" + lineElements[4] + "\r\n");
					foundSavedMenu += ("Carbonhydrat:" + lineElements[5] + "\r\n");
					foundSavedMenu += ("Fat:" + lineElements[6] + "\r\n");
					String elementsOfMenu[] = lineElements[7].split(",");
					for (int i = 0; i < elementsOfMenu.length; i++) {

						foundSavedMenu += (elementsOfMenu[i]);
						if (elementsOfMenu[i].equalsIgnoreCase("dinner") || elementsOfMenu[i].equalsIgnoreCase("snack")
								|| elementsOfMenu[i].equalsIgnoreCase("breakfast")
								|| elementsOfMenu[i].equalsIgnoreCase("lunch")) {
							foundSavedMenu += " : \r\n";
						} else {
							foundSavedMenu += "\r\n";
						}
					}
					br.close();
					file.setWritable(false);
					return foundSavedMenu;
				}
			}
			firstLineRecog++;
		}
		br.close();
		file.setWritable(false);
		return "Empty";
	}

	// This method displays menus from menu text file depending on user's data. 
	public void userBasedMenuSelector(User givenUser, List<MenuAndValues> menuslist, int dietType, int diseaseIndex,
			int caloryNeed) throws IOException {
		File file = new File("TextFiles/menuData.txt");
		file.setWritable(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String currentLine;
		int firstLineRecog = 0;
		while ((currentLine = br.readLine()) != null) {
			if (firstLineRecog != 0) {
				String[] lineElements = currentLine.split(";");
				int dietSelectionOfMenu = Integer.parseInt(lineElements[1]);
				int nutritionalDiseaseOfMenu = Integer.parseInt(lineElements[2]);
				int caloryValueOfMenu = Integer.parseInt(lineElements[3]);
				if (givenUser.getNutritionDisese() == nutritionalDiseaseOfMenu
						&& givenUser.getDietSelection() == dietSelectionOfMenu
						&& currentOnlineUser.getDailyCaloryNeed() > (caloryValueOfMenu - 100)
						&& currentOnlineUser.getDailyCaloryNeed() < (caloryValueOfMenu + 100)) {
					String descriptionOfMenu = ("Menu ID:" + lineElements[0] + "\r\n");
					descriptionOfMenu += ("Diet Selection :" + dietSelecInterpret(dietSelectionOfMenu) + "\r\n");
					descriptionOfMenu += ("Nutritional Disease :" + nutriDiseInterp(nutritionalDiseaseOfMenu) + "\r\n");
					descriptionOfMenu += ("Calory :" + lineElements[3] + "\r\n");
					descriptionOfMenu += ("Protein :" + lineElements[4] + "\r\n");
					descriptionOfMenu += ("Carbonhydrat :" + lineElements[5] + "\r\n");
					descriptionOfMenu += ("Fat :" + lineElements[6] + "\r\n");

					String menuDesParts[] = lineElements[7].split(",");
					for (int i = 0; i < menuDesParts.length; i++) {
						if (menuDesParts[i].equalsIgnoreCase("dinner") || menuDesParts[i].equalsIgnoreCase("snack")
								|| menuDesParts[i].equalsIgnoreCase("breakfast")
								|| menuDesParts[i].equalsIgnoreCase("lunch")) {
							descriptionOfMenu += "_" + menuDesParts[i] + "_" + "\r\n";
						} else {
							descriptionOfMenu += (menuDesParts[i] + "\r\n");
						}
					}

					JTextArea newTextArea = new JTextArea();
					newTextArea.setPreferredSize(new Dimension(300, 750));
					Font font = new Font("MV Boli", Font.BOLD, 14);
					newTextArea.setFont(font);
					newTextArea.setText(descriptionOfMenu);
					newTextArea.setEditable(false);
					newTextArea.setMargin(new Insets(30, 30, 30, 30));
					MenuAndValues currAddedMenu = new MenuAndValues(Integer.parseInt(lineElements[0]),
							dietSelecInterpret(dietSelectionOfMenu), nutriDiseInterp(nutritionalDiseaseOfMenu),
							Integer.parseInt(lineElements[3]), Integer.parseInt(lineElements[4]),
							Integer.parseInt(lineElements[5]), Integer.parseInt(lineElements[6]), descriptionOfMenu,
							newTextArea);

					menuslist.add(currAddedMenu);

				}
			}
			firstLineRecog++;
		}
		br.close();
		file.setWritable(false);
	}

	 // When user selects a menu, this menu's id is written to user data text file.  
	 public void writeNewMenuID(int newMenuId,String usernameGiven) throws IOException {
		File fileForm = new File("TextFiles/userDataRepository.txt");
		fileForm.setWritable(true);
		 BufferedReader file = new BufferedReader(new FileReader(fileForm));
		
	        StringBuffer strBuffForEdit = new StringBuffer();
	        String line;
	        String willChangeTo="";
	        while ((line = file.readLine()) != null) {
	        	String[] currUserLine=line.split(";");
	        	if(currUserLine[0].equals(usernameGiven)) {
	        		for (int i = 0; i < currUserLine.length-1; i++) {
	        			willChangeTo+=currUserLine[i]+";";
					}
	        		willChangeTo +=newMenuId;
	        		strBuffForEdit.append(willChangeTo);
	        	}
	        	else {
	            strBuffForEdit.append(line);
	        	}
	        	
	            strBuffForEdit.append('\n');
	        }
	        file.close();
	        String inputStr = strBuffForEdit.toString();
	        fileForm.setWritable(false);
	        
	        
	        File fileFormOut = new File("TextFiles/userDataRepository.txt");
	        fileFormOut.setWritable(true);
	        
	        FileOutputStream fileOut = new FileOutputStream(fileFormOut);
	        fileOut.write(inputStr.getBytes());
	        fileFormOut.setWritable(false);
	        fileOut.close();
	       
	    	
	 }
	 
	 // This method for writing exist user's current date and current weight to progress file.
	 public void addNewValToProgFile(String onlineUserr,String givenDate,double givenNewWeight) throws IOException {
		    File fileFormRead = new File("TextFiles/usernameBasedProgress.txt");
		    fileFormRead.setWritable(true);
		 BufferedReader file = new BufferedReader(new FileReader(fileFormRead));
	        StringBuffer strBuffForEdit = new StringBuffer();
	        String line;
	        String willChangeTo="";
	        while ((line = file.readLine()) != null) {
	        	String[] currUserLine=line.split(";");
	        	if(currUserLine[0].equals(onlineUserr)) {
	        		for (int i = 0; i < currUserLine.length; i++) {
	        			willChangeTo+=currUserLine[i]+";";
					}
	        		willChangeTo +=givenDate+"-"+givenNewWeight;
	        		strBuffForEdit.append(willChangeTo);
	        	}
	        	else {
	            strBuffForEdit.append(line);
	        	}
	        	
	            strBuffForEdit.append('\n');
	        }
	        file.close();
	        String inputStr = strBuffForEdit.toString();
	        fileFormRead.setWritable(false);
	        
	        File fileFormOut = new File("TextFiles/usernameBasedProgress.txt");
	        fileFormOut.setWritable(true);
	        FileOutputStream fileOut = new FileOutputStream(fileFormOut);
	        fileOut.write(inputStr.getBytes());
	        fileFormOut.setWritable(false);
	        fileOut.close();
	        
	 }
	 
	/*
	 * If user searches a recipe and this recipe exists in recipe text file, returns recipe.
	 * Otherwise, returns "No Element Found" message.
	*/
	public String userSearchForRecipe(String givenUserRecipe) throws IOException {
			File file = new File("TextFiles/recipeData.txt");
			file.setWritable(true);
			BufferedReader br = new BufferedReader(new InputStreamReader (new FileInputStream(file), "UTF8"));
			String currentLine;
			String currentRecipeToShow="";
			while((currentLine=br.readLine()) != null){
				String [] lineElements = currentLine.split(";");
				String currRecipeName = lineElements[0];
				if(currRecipeName.equalsIgnoreCase(givenUserRecipe)) {
					currentRecipeToShow+="Recipe Name : " +currRecipeName+" \r\n";
					String linesOfRecipes[] = lineElements[1].split("\\.");
					for (int i = 0; i < linesOfRecipes.length; i++) {
						currentRecipeToShow+=linesOfRecipes[i]+".\r\n";
					}
					file.setWritable(false);
					br.close();
					return currentRecipeToShow;
					
				}
			}
			file.setWritable(false);
			br.close();
			return "No Element Found";
			
	}
	//This method fills user weights progress data
	public List<ProgressData> userProgListFiller(String username) throws IOException {
		List<ProgressData> filledUserProgList = new ArrayList<ProgressData>();
		File file = new File("TextFiles/usernameBasedProgress.txt");
		file.setWritable(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String currentLine;
		int firstLinDistin = 0;
		while ((currentLine = br.readLine()) != null) {
			if (firstLinDistin != 0) {
				if (currentLine.split(";")[0].equals(username)) {
					for (int i = 1; i < currentLine.split(";").length; i++) {
						Date dateToWriteIntoList = new Date( Integer.parseInt(currentLine.split(";")[i].split("-")[0].split("\\.")[0]),
							Integer.parseInt(currentLine.split(";")[i].split("-")[0].split("\\.")[1]),
							Integer.parseInt(currentLine.split(";")[i].split("-")[0].split("\\.")[2]));
						filledUserProgList.add(new ProgressData(dateToWriteIntoList, Double.parseDouble(currentLine.split(";")[i].split("-")[1])));
					}
				}
			}
			firstLinDistin++;

		}
		file.setWritable(false);
		br.close();
		return filledUserProgList;

	}
			
	// This method for interpreting user' nutritional disease from numeric values to string values.
	private String nutriDiseInterp(int diseaseIndex) {
		if(diseaseIndex==0) {
			return "No Disease";
		}
		else if (diseaseIndex == 1) {
			return "Diabetes";
		}
		else if(diseaseIndex == 2) {
			return "Celiac";
		}
		else {
			return "Reflux";
		}
	}
	
	public double userCurrWeightFounder(String userName) throws UnsupportedEncodingException, FileNotFoundException {
		File file = new File("TextFiles/usernameBasedProgress.txt");
		file.setWritable(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
		String readedLine;
		int firstLineRecog = 0;
		try {
			while ((readedLine = br.readLine()) != null) {
				if (firstLineRecog != 0) {
					if (readedLine.split(";")[0].equals(userName)) {
						file.setWritable(false);
						return (Double.parseDouble((readedLine.split(";")[readedLine.split(";").length - 1]).split("-")[1]));
					}
				}
				firstLineRecog++;
			}
		} catch (Exception e) {
			System.out.println("Error While Reading File");
		}
		file.setWritable(false);
		return 0.0;
	}
	// This method for interpreting user' diet selection from numeric values to string values.
	private String dietSelecInterpret(int dietIndex) {
		if(dietIndex==0) {
			return "Normal";
		}
		else if(dietIndex ==1) {
			return "Vegeterian";
		}
		else {
			return "Vegan";
		}
	}
	
	// Getter
	public User getCurrentOnlineUser() {
		return currentOnlineUser;
	}

}
