import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
/*
 * This class created for searching and displaying recipe.
*/
public class Recipes extends JFrame {

	// Attributes
	private JPanel contentPane;
	private JTextField recipeSearchUser;
	private JTextArea txtrRecipeName;

	// Constructor
	public Recipes() {
		this.setVisible(true);
		setBounds(550, 200, 885, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(5, 5, 857, 558);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea txtrRecpes = new JTextArea();
		txtrRecpes.setEditable(false);
		txtrRecpes.setBackground(SystemColor.control);
		txtrRecpes.setFont(new Font("MV Boli", Font.BOLD, 21));
		txtrRecpes.setText("RECIPES");
		txtrRecpes.setBounds(376, 24, 105, 31);
		panel.add(txtrRecpes);
		
		recipeSearchUser = new JTextField();
		recipeSearchUser.setBounds(232, 117, 227, 30);
		panel.add(recipeSearchUser);
		recipeSearchUser.setColumns(10);
		
		txtrRecipeName = new JTextArea();
		txtrRecipeName.setEditable(false);
		txtrRecipeName.setBackground(SystemColor.controlShadow);
		txtrRecipeName.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtrRecipeName.setText("Recipe Name :");
		txtrRecipeName.setBounds(109, 117, 125, 29);
		panel.add(txtrRecipeName);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(107, 146, 619, 325);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea displayTextArea = new JTextArea();
		displayTextArea.setFont(new Font("MV Boli", Font.PLAIN, 14));
		displayTextArea.setBounds(0, 0, 619, 325);
		
		panel_1.add(displayTextArea);
		displayTextArea.setEditable(false);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//userRecipeSearch
				ReadFile rdfr = new ReadFile();
				try {
					
					displayTextArea.setText(rdfr.userSearchForRecipe(recipeSearchUser.getText()));
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			
			}
		});
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.setBounds(460, 117, 125, 29);
		panel.add(btnSearch);
		
		
		JTextArea txtrPleaseInsertRecipe = new JTextArea();
		txtrPleaseInsertRecipe.setEditable(false);
		txtrPleaseInsertRecipe.setBackground(SystemColor.controlHighlight);
		txtrPleaseInsertRecipe.setText("Please Insert Recipe with Blankes Ex: \"Lentil Soup\"");
		txtrPleaseInsertRecipe.setBounds(460, 484, 291, 16);
		panel.add(txtrPleaseInsertRecipe);
	}
}
