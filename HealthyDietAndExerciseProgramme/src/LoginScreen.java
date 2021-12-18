import java.awt.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.Font;
/*
 * This class created for login screen which allows to user sign up, sign in, show and hide password selection.
 * This is very very first screen for user. When program runs, this main works and depending on the user selection navigates sign in or sign up screen.
 * When user correctly input data user name and password, the user is navigated to main screen by this class 
 * and at the other hand user is warned by error messages.
 * Otherwise, if user selects sign up, this class navigates sign up screen.
 * Thanks to hide and show password button users are able to hide their password for security.
*/

public class LoginScreen {

	// Attributes
	private JFrame loginScreen;
	private JTextField username;
	private JPasswordField passwordUnvisible;
	private JTextField passwordVisible;
	private boolean flagShow = false;
	private static LoginScreen loginWindow;
	private JTextArea usernameErrorMessage;
	private JTextArea passwordErrorMessage;
	
	// This is our program's main
	public static void main(String[] args) throws FileNotFoundException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginWindow = new LoginScreen();
					loginWindow.loginScreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	
	// Constructor
	public LoginScreen() {
		initialize();
	}

	
	// Here initialized login screen with creating objects from related classes and visualization library called as JSwing.
	private void initialize() {
		loginScreen = new JFrame();
		loginScreen.setTitle("HEALTHY DIET AND EXERCISE");
		loginScreen.setBounds(700, 350, 452, 316);
		loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginScreen.getContentPane().setLayout(null);
		
		JTextArea programName = new JTextArea();
		programName.setEditable(false);
		programName.setFont(new Font("MV Boli", Font.PLAIN, 10));
		programName.setBackground(new Color(221,40,40));
		programName.setForeground(new Color (255,255,255));
		programName.setText("    HEALTHY DIET & \r\nEXERCISE PROGRAMME ");
		programName.setBounds(169, 35, 127, 38);
		loginScreen.getContentPane().add(programName);
		
		username = new JTextField();
		username.setBounds(210, 125, 116, 22);
		loginScreen.getContentPane().add(username);
		username.setColumns(10);
		
		
		JTextArea usernamePanel = new JTextArea();
		usernamePanel.setFont(new Font("MV Boli", Font.PLAIN, 14));
		usernamePanel.setBackground(new Color(230,230,230));
		usernamePanel.setText(" UserName");
		usernamePanel.setBounds(110, 123, 88, 25);
		loginScreen.getContentPane().add(usernamePanel);
		
		JTextArea passwordPanel = new JTextArea();
		passwordPanel.setFont(new Font("MV Boli", Font.PLAIN, 14));
		passwordPanel.setBackground(new Color(230,230,230));
		passwordPanel.setText(" Password");
		passwordPanel.setBounds(110, 161, 88, 25);
		loginScreen.getContentPane().add(passwordPanel);
		
		JButton signInButton = new JButton("Sign In");
		signInButton.setBounds(110, 204, 97, 25);
		loginScreen.getContentPane().add(signInButton);
		//    
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					SignInToMain sgn = null;
						try {
					
							sgn = new SignInToMain(username.getText(),String.valueOf(passwordUnvisible.getPassword()));
						
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					try {
						if(sgn.fromSignInToMainGaneralChecker(username.getText(), String.valueOf(passwordUnvisible.getPassword())))
						{
							try 
							{	
								ReadFile rdf = new ReadFile();
								rdf.readFileForExistUser(username.getText(),String.valueOf(passwordUnvisible.getPassword()));
								
								MainScreen masn = new MainScreen(rdf.getCurrentOnlineUser());
								loginWindow.loginScreen.dispose();			
							} catch (IOException e) {
							
								e.printStackTrace();
							}
						}
						else {
							usernameErrorMessage.setText(sgn.getUserWarning());
							passwordErrorMessage.setText(sgn.getPasswordWarning());
							
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}
		);
		
		
		JButton signUpButton = new JButton("Sign Up ");
		signUpButton.setBounds(226, 204, 97, 25);
		loginScreen.getContentPane().add(signUpButton);
		
		passwordUnvisible = new JPasswordField();
		passwordUnvisible.setBounds(210, 163, 116, 22);
		loginScreen.getContentPane().add(passwordUnvisible);
		
		
		passwordVisible = new JTextField();
		passwordVisible.setBounds(210, 163, 116, 22);
		loginScreen.getContentPane().add(passwordVisible);
		passwordVisible.setColumns(10);
		
		Icon iconEye = new ImageIcon("Images/SystemImages/smallEyeIco.png");
		JButton showPassword = new JButton(iconEye);
		showPassword.setFont(new Font("Tahoma", Font.PLAIN, 6));
		showPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flagShow) {
					flagShow = false;
				}
				else {
					flagShow = true;
				}
				if(flagShow) {
					passwordVisible.setText(String.valueOf(passwordUnvisible.getPassword()));
					passwordVisible.setVisible(true);
					passwordUnvisible.setVisible(false);
				}
				else {	
					passwordVisible.setVisible(false);
					passwordUnvisible.setText(passwordVisible.getText());
					passwordUnvisible.setVisible(true);
				}
			}
		});
		showPassword.setBounds(338, 164, 39, 22);
		loginScreen.getContentPane().add(showPassword);
		
		ImageIcon imageIconBack = new ImageIcon("Images/SystemImages/loginScreenBackImage.png");
	    JLabel labelLoginBack = new JLabel(imageIconBack);
	    labelLoginBack.setBounds(0, 0, 432, 269);
		loginScreen.getContentPane().add(labelLoginBack);
		
		usernameErrorMessage = new JTextArea();
		usernameErrorMessage.setEditable(false);
		usernameErrorMessage.setBackground(SystemColor.control);
		usernameErrorMessage.setBounds(110, 148, 238, 15);
		loginScreen.getContentPane().add(usernameErrorMessage);
		
		passwordErrorMessage = new JTextArea();
		passwordErrorMessage.setEditable(false);
		passwordErrorMessage.setBackground(SystemColor.control);
		passwordErrorMessage.setBounds(110, 188, 228, 16);
		loginScreen.getContentPane().add(passwordErrorMessage);
	
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					RegisterScreen rgstscrn = new RegisterScreen();
					loginWindow.loginScreen.setVisible(false);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
