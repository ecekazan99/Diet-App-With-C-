import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.Font;
/* This class created for displaying personalized menus which concerns about following parameters:
* 1) Nutritional Disease 
* 2) Program Selection (Loss Weight, Gain Weight, Stay Fit)
* 3) Daily Calory Need
* Also menus screen allows to user sorting menus which depending on following type:
* 1) Sort by Protein Value
* 2) Sort by Carbonhydrat Value
* 3) Sort by Fat Value
* 4) Sort by Calory Value
*/
public class PersonalisedMenus extends JFrame {
	
	// Attributes
	private User currOnlineUser;
	private List <MenuAndValues> suitableMenuslist = new ArrayList<MenuAndValues>();
	private int currMenuID=0;
	private JPanel panel ;
	private JScrollPane scrollPane;

	// This method finds menus from menu text file depending on user's data.
	public void menuDefiner(User currOnlineUserGiven) throws IOException {
		ReadFile rdf = new ReadFile();
		rdf.userBasedMenuSelector(currOnlineUserGiven, suitableMenuslist, currOnlineUserGiven.getDietSelection(), currOnlineUserGiven.getNutritionDisese(),currOnlineUserGiven.getDailyCaloryNeed());
	}
	
	// This method displays menus from menu text file depending on user's data.
	public void setTextAreaElements() {
		
		for (Iterator<MenuAndValues> iterator = suitableMenuslist.iterator(); iterator.hasNext();) {
			
			MenuAndValues menuAndValues = (MenuAndValues) iterator.next();
			menuAndValues.getTextAreaOfMenu().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					for (Iterator<MenuAndValues> iterator2 = suitableMenuslist.iterator(); iterator2.hasNext();) {
						
						MenuAndValues menuAndValues2 = (MenuAndValues) iterator2.next();
						if(menuAndValues2.isTheMenuSelected()) {
							menuAndValues2.getTextAreaOfMenu().setBackground(new Color(255,255,255));
							menuAndValues2.setTheMenuSelected(false);
						}
						
					}
					setCurrMenuID(menuAndValues.getMenuID());
					menuAndValues.setTheMenuSelected(true);
					menuAndValues.getTextAreaOfMenu().setBackground(new Color(212, 238, 241));
				}
			});
			panel.add(menuAndValues.getTextAreaOfMenu());
			
			
		}
		getContentPane().add(scrollPane);
	}
	
	/*
	 *  This method displays Calory Value, Carbonhydrat Value, Fat Value, Protein Value buttons.
	 *  If user selects anyone of this selections, program displays menus depending on that selection.
	*/
	public void initPersonalisedMenus(User currOnlineUserG) throws IOException {
		this.currOnlineUser=currOnlineUserG;
		getContentPane().setLayout(null);
		JTextArea txtrPersonalisedExercises = new JTextArea();
		txtrPersonalisedExercises.setEditable(false);
		txtrPersonalisedExercises.setBackground(SystemColor.scrollbar);
		txtrPersonalisedExercises.setFont(new Font("MV Boli", Font.BOLD, 18));
		txtrPersonalisedExercises.setText("Personalised Menus");
		txtrPersonalisedExercises.setBounds(600, 25, 193, 34);
		getContentPane().add(txtrPersonalisedExercises);
		menuDefiner(currOnlineUserG);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 10, 10);
		
		
	    scrollPane = new JScrollPane(panel);
		panel.setLayout(new GridLayout(0, 4, 15, 15));
		scrollPane.setBounds(100, 101, 1250, 490);
		
		setTextAreaElements();
		
		JTextArea txtrSortMenusBy = new JTextArea();
		txtrSortMenusBy.setFont(new Font("Monospaced", Font.BOLD, 16));
		txtrSortMenusBy.setText("Sort Menus By");
		txtrSortMenusBy.setEditable(false);
		txtrSortMenusBy.setBounds(168, 70, 150, 33);
		getContentPane().add(txtrSortMenusBy);
		
		JToggleButton tglbtnCaloryValue = new JToggleButton("Calory Value");
		JToggleButton tglbtnCarbonhydratValue = new JToggleButton("Carbonhydrat Value");
		JToggleButton tglbtnFatValue = new JToggleButton("Fat Value");
		JToggleButton tglbtnProteinValue = new JToggleButton("Protein Value");
		
		tglbtnProteinValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Collections.sort(suitableMenuslist, new SortByProtein());
				Collections.reverse(suitableMenuslist); 
				if(tglbtnCarbonhydratValue.isSelected())
					tglbtnCarbonhydratValue.setSelected(false);
				if(tglbtnCaloryValue.isSelected())
					tglbtnCaloryValue.setSelected(false);
				if(tglbtnFatValue.isSelected())
					tglbtnFatValue.setSelected(false);
				panel.setVisible(false);
				panel.setVisible(true);
				
				setTextAreaElements();
			
			}
		});
		tglbtnCaloryValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Collections.sort(suitableMenuslist, new SortByCalory());
				Collections.reverse(suitableMenuslist); 
				if(tglbtnCarbonhydratValue.isSelected())
					tglbtnCarbonhydratValue.setSelected(false);
				if(tglbtnProteinValue.isSelected())
					tglbtnProteinValue.setSelected(false);
				if(tglbtnFatValue.isSelected())
					tglbtnFatValue.setSelected(false);
				panel.setVisible(false);
				panel.setVisible(true);
				
				setTextAreaElements();
			
			}
		});
		tglbtnCarbonhydratValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Collections.sort(suitableMenuslist, new SortByCarbonhydrat());
				Collections.reverse(suitableMenuslist); 
				if(tglbtnProteinValue.isSelected())
					tglbtnProteinValue.setSelected(false);
				if(tglbtnCaloryValue.isSelected())
					tglbtnCaloryValue.setSelected(false);
				if(tglbtnFatValue.isSelected())
					tglbtnFatValue.setSelected(false);
				panel.setVisible(false);
				panel.setVisible(true);
				
				setTextAreaElements();
			
			}
		});
		tglbtnFatValue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Collections.sort(suitableMenuslist, new SortByFat());
				Collections.reverse(suitableMenuslist); 
				if(tglbtnCarbonhydratValue.isSelected())
					tglbtnCarbonhydratValue.setSelected(false);
				if(tglbtnCaloryValue.isSelected())
					tglbtnCaloryValue.setSelected(false);
				if(tglbtnProteinValue.isSelected())
					tglbtnProteinValue.setSelected(false);
				panel.setVisible(false);
				panel.setVisible(true);
				
				setTextAreaElements();
			
			}
		});
		tglbtnCaloryValue.setBounds(317, 68, 160, 33);
		getContentPane().add(tglbtnCaloryValue);
		
		
		tglbtnCarbonhydratValue.setBounds(475, 68, 160, 33);
		getContentPane().add(tglbtnCarbonhydratValue);
		
		
		tglbtnFatValue.setBounds(787, 68, 160, 33);
		getContentPane().add(tglbtnFatValue);
		
		
		tglbtnProteinValue.setBounds(631, 68, 160, 33);
		getContentPane().add(tglbtnProteinValue);
	

		JTextArea infoAboutContent = new JTextArea();
		infoAboutContent.setText("This table shows (+-)100 Calory of your need");
		infoAboutContent.setFont(new Font("Monospaced", Font.BOLD, 11));
		infoAboutContent.setBackground(new Color(230,230,230));  
		infoAboutContent.setEditable(false);
		infoAboutContent.setBounds(950, 600, 350, 33);
		getContentPane().add(infoAboutContent);
		
		JButton saveSelectedMenu = new JButton("Save Selected Menu");
		saveSelectedMenu.setBounds(620,600,200, 35);
		saveSelectedMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReadFile rdf = new ReadFile();
				
				currOnlineUserG.setMenuID(getCurrMenuID());
				
				try {
					
					rdf.writeNewMenuID(getCurrMenuID(),currOnlineUserG.getUsername());
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		getContentPane().add(saveSelectedMenu);
		saveSelectedMenu.setVisible(true);
	
		
	}
	
	// This inner class for sorting menus which depending on protein value.
	class SortByProtein implements Comparator<MenuAndValues> 
	{ 
	
		@Override
		public int compare(MenuAndValues mav1, MenuAndValues mav2) {
		
			return mav1.getProteinValue()-mav2.getProteinValue();
		} 
	}
	
	// This inner class for sorting menus which depending on calory value.
	class SortByCalory implements Comparator<MenuAndValues> 
	{ 
	   
		@Override
		public int compare(MenuAndValues mav1, MenuAndValues mav2) {
		
			return mav1.getCaloryValue()-mav2.getCaloryValue();
		} 
	}
	
	// This inner class for sorting menus which depending on fat value.
	class SortByFat implements Comparator<MenuAndValues> 
	{ 
	     
		@Override
		public int compare(MenuAndValues mav1, MenuAndValues mav2) {
		
			return mav1.getFatValue()-mav2.getFatValue();
		} 
	}
	
	// This inner class for sorting menus which depending on carbonhdyrat value.
	class SortByCarbonhydrat implements Comparator<MenuAndValues> 
	{ 
	    
		@Override
		public int compare(MenuAndValues mav1, MenuAndValues mav2) {
		
			return mav1.getCarbonhydratValue()-mav2.getCarbonhydratValue();
		} 
	}
	
	// Getters and Setters
	public User getCurrOnlineUser() {
		return currOnlineUser;
	}
	public void setCurrOnlineUser(User currOnlineUser) {
		this.currOnlineUser = currOnlineUser;
	}
	public List<MenuAndValues> getSuitableMenuslist() {
		return suitableMenuslist;
	}
	public void setSuitableMenuslist(List<MenuAndValues> suitableMenuslist) {
		this.suitableMenuslist = suitableMenuslist;
	}
	public int getCurrMenuID() {
		return currMenuID;
	}
	public void setCurrMenuID(int currMenuID) {
		this.currMenuID = currMenuID;
	}
	
}
