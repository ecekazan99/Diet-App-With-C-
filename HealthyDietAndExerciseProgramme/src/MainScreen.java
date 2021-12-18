import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
/*
 * This class created for displaying personal profile, menus, recipes, exercises, personal graph, edit weight, leader board options and motivation messages.
 * When user signed up or signed in successfully, program navigates this class.
*/
public class MainScreen {
	
	// Attributes
	private JFrame frame;
	private static User currAppUser;
	
	// Constructor
	public MainScreen(User currentUser) {
		currAppUser = currentUser;
		initialize();
		this.frame.setVisible(true);
	}
	
	// This method initializes main screen's sections (i.e. buttons with their icons, motivation messages.) which includes visualized content as well. 
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 350, 537, 346);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String motivationMessagges[] = {"My weight does not determine my worth.",
				"If you wait, all that happens is you get older.",
				"I am a better person when I have less on my plate.",
				"If I don’t eat junk, I don’t gain weight.",
				"Keep an open mind and a closed refrigerator.",
				"It’s not a diet, it’s a lifestyle change.",
				"Strive for progress, not perfection.",
				"A goal without a plan is just a wish."};
		
		Icon profileIco = new ImageIcon("Images/SystemImages/menuPersoProIco.png");
		JButton btnProfile = new JButton(profileIco);
		btnProfile.setText("My Profile");
		btnProfile.setMargin(new Insets(0,0,0,0));
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				PersonalProfile psp = new PersonalProfile();
				try {
					psp.initPersonalProfile(currAppUser);
					psp.setVisible(true);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
			
			}
		});
		btnProfile.setBounds(5, 0, 165, 100);
		frame.getContentPane().add(btnProfile);
		
		Icon menuIco = new ImageIcon("Images/SystemImages/menuMenusIco.png");
		JButton btnMenus = new JButton(menuIco);
		btnMenus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonalisedMenus psm = new PersonalisedMenus();
				try {
					psm.initPersonalisedMenus(currAppUser);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				psm.setBounds(100, 100, 1400, 740);
				psm.setVisible(true);
			}
		});
		btnMenus.setText("Menus");
		btnMenus.setBounds(175, 0, 165, 100);
		frame.getContentPane().add(btnMenus);
		
		Icon leaderBoardIco = new ImageIcon("Images/SystemImages/menuLeaderBoardIco.png");
		JButton btnLeaderBoard = new JButton(leaderBoardIco);
		btnLeaderBoard.setText("Leader Board");
		btnLeaderBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					LeaderBoard psne = new LeaderBoard();
					try {
						psne.showLeaderBoard(currAppUser.getProgrammeSelection());
					} catch (UnsupportedEncodingException | FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		});
		btnLeaderBoard.setBounds(5, 195, 165, 100);
		frame.getContentPane().add(btnLeaderBoard);
		
		Icon exerciseIco = new ImageIcon("Images/SystemImages/menuExerciseIco.png");
		JButton btnExercises = new JButton(exerciseIco);
		btnExercises.setText("Exercise");
		btnExercises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					PersonalisedExercises psne = new PersonalisedExercises();
					psne.programmeSelection(currAppUser.getProgrammeSelection());
					
			}
		});
		
		btnExercises.setBounds(350, 101, 175, 93);
		frame.getContentPane().add(btnExercises);

		Icon recipeIco = new ImageIcon("Images/SystemImages/menuRecipeIco.png");
		JButton btnRecipes = new JButton(recipeIco);
		btnRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Recipes rcp = new Recipes();
			}
		});
		btnRecipes.setText("Recipes");
		btnRecipes.setBounds(350, 0, 175, 100);
		frame.getContentPane().add(btnRecipes);
		
		
		Icon editMyWeightIco = new ImageIcon("Images/SystemImages/menuEditMyWeightIco.png");
		JButton btnEditMyWeight = new JButton(editMyWeightIco);
		btnEditMyWeight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonalProgress pesp = new PersonalProgress(currAppUser);
				
			}
		});
		btnEditMyWeight.setText("Edit My Weight");
		btnEditMyWeight.setBounds(175, 195, 165, 100);
		frame.getContentPane().add(btnEditMyWeight);
		
		
		Icon myProgressIco = new ImageIcon("Images/SystemImages/menuProgressIco.png");
		JButton btnMyprogress = new JButton(myProgressIco);
		btnMyprogress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonalisedGraph pg;
				try {
					pg = new PersonalisedGraph(currAppUser);
					pg.run();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		btnMyprogress.setText("MyProgress");
		btnMyprogress.setBounds(350, 195, 175, 100);
		frame.getContentPane().add(btnMyprogress);
		
		JTextArea txtrMotvatob = new JTextArea();
		txtrMotvatob.setBackground(SystemColor.menu);
		txtrMotvatob.setEditable(false);
		txtrMotvatob.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 13));
		motivationMessageSelector(txtrMotvatob,motivationMessagges);
		txtrMotvatob.setBounds(10, 140, 341, 22);
		frame.getContentPane().add(txtrMotvatob);
	}
	
	// This method defines motivation messages from string array.
	public void motivationMessageSelector(JTextArea givenArea,String givenMessages[]) {
		
		Random rand = new Random();
		int randNumbInx = rand.nextInt(givenMessages.length);
		givenArea.setText(givenMessages[randNumbInx]);
		
	}
	
}
