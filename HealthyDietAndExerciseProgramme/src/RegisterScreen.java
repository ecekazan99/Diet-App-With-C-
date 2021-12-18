import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import java.awt.Canvas;
import java.awt.Label;
/*
 * This class created for taking data of new user.
 * If user enters the values correctly, program navigates to main screen. 
 * Otherwise, program displays warning messages.
 * Program takes the following values from user:
 * Profile Photo,User Name,Password,Gender,Age,Height,Begin Date,End Date,
 * Begin Weight,Target Weight,Nutritional Disease,Diet Selection,Programme Selection.
*/

public class RegisterScreen {
	
	// Attributes
	private JFrame frame;
	private JTextField username;
	private JTextField password;
	private JTextField txtProgrammeSelection;
	private JTextField txtDietSelection;
	private JTextField txtHeight;
	private JTextField txtCurrentWeight;
	private JTextField currKg;
	private JTextField currGr;
	private JTextField txtTargetWeight;
	private JTextField tarKg;
	private JTextField tarGr;
	private JTextField txtBeginDate;
	private JTextField txtEndDate;
	private JTextField nutriDisease;
	private JComboBox targetWeightKgComboBox;
	private JComboBox targetWeightGrComboBox;
	private JFileChooser chooser = new JFileChooser();
	private BufferedImage image ;
	// Constructor
	public RegisterScreen() throws IOException {
		initialize();
	}
	// Test
	// Here initialized register screen with creating objects from related classes and visualization library called as JSwing.
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 622, 717);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton addPhoto = new JButton("Add Photo");
		addPhoto.setBackground(Color.LIGHT_GRAY);
		addPhoto.setBounds(67, 236, 123, 25);
		frame.getContentPane().add(addPhoto);
		File img = new File("Images/UserProfileImages/defaultProfilPhoto.png");
		image = ImageIO.read(img);
		
		Icon icon = new ImageIcon("Images/UserProfileImages/defaultProfilPhoto.png");
		JButton profilePhotoButton = new JButton(icon);
		profilePhotoButton.setBounds(29, 32, 186, 200);
		frame.getContentPane().add(profilePhotoButton);
		
		addPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG Images", "jpg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(frame);
				
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						image = ImageIO.read(chooser.getSelectedFile());
						Icon newProfilePhoto = new ImageIcon(image);
						profilePhotoButton.setIcon(newProfilePhoto);
						addPhoto.setText("Change Photo");
					} catch (IOException e) {
						
						e.printStackTrace();
					}

				}
				

			}
		});
		
	
		username = new JTextField();
		username.setBounds(395, 50, 116, 22);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(395, 103, 116, 22);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JRadioButton rdbtnM = new JRadioButton("Male");
		
		rdbtnM.setBounds(364, 155, 55, 25);
		frame.getContentPane().add(rdbtnM);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnM.setSelected(false);
			}
		});
		rdbtnFemale.setBounds(440, 155, 71, 25);
		frame.getContentPane().add(rdbtnFemale);
		
		rdbtnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnFemale.setSelected(false);
			}
		});
		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setEditable(false);
		txtrUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtrUsername.setText("Username");
		txtrUsername.setBounds(246, 47, 100, 25);
		frame.getContentPane().add(txtrUsername);
		
		JTextArea txtrPassword = new JTextArea();
		txtrPassword.setEditable(false);
		txtrPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtrPassword.setText("Password");
		txtrPassword.setBounds(246, 100, 110, 22);
		frame.getContentPane().add(txtrPassword);
		
		JTextArea txtrGender = new JTextArea();
		txtrGender.setEditable(false);
		txtrGender.setText("Gender");
		txtrGender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtrGender.setBounds(246, 153, 100, 25);
		frame.getContentPane().add(txtrGender);
		
		JComboBox ageComboBox = new JComboBox();
		ageComboBox.setModel(new DefaultComboBoxModel(new String[] {"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32",
																    "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46","47",
																    "48","49","50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65"}));
		ageComboBox.setMaximumRowCount(10);
		ageComboBox.setBounds(395, 210, 41, 22);
		frame.getContentPane().add(ageComboBox);
		
		JTextArea txtrAge = new JTextArea();
		txtrAge.setEditable(false);
		txtrAge.setText("Age");
		txtrAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtrAge.setBounds(246, 207, 100, 25);
		frame.getContentPane().add(txtrAge);
		
		txtProgrammeSelection = new JTextField();
		
		txtProgrammeSelection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtProgrammeSelection.setBackground(Color.WHITE);
		txtProgrammeSelection.setEditable(false);
		txtProgrammeSelection.setText("   Programme Selection");
		txtProgrammeSelection.setBounds(34, 274, 181, 22);
		frame.getContentPane().add(txtProgrammeSelection);
		txtProgrammeSelection.setColumns(10);
		
		JComboBox<String> programSelectComboBox = new JComboBox<String>();
		programSelectComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(programSelectComboBox.getSelectedItem().toString().equals("Stay Fit")) {
					
						tarKg.setVisible(false);
						tarGr.setVisible(false);
						txtTargetWeight.setVisible(false);
						targetWeightKgComboBox.setVisible(false);
						targetWeightGrComboBox.setVisible(false);
				}
				else {
					tarKg.setVisible(true);
					tarGr.setVisible(true);
					txtTargetWeight.setVisible(true);
					targetWeightKgComboBox.setVisible(true);
					targetWeightGrComboBox.setVisible(true);
				}
					
				
			}
		});
		programSelectComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Loss Weight", "Gain Weight", "Stay Fit"}));
		programSelectComboBox.setMaximumRowCount(3);
		programSelectComboBox.setBounds(246, 275, 110, 22);
		frame.getContentPane().add(programSelectComboBox);
		
		
		txtDietSelection = new JTextField();
		txtDietSelection.setBackground(Color.WHITE);
		txtDietSelection.setEditable(false);
		txtDietSelection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDietSelection.setText("       Diet Selection");
		txtDietSelection.setColumns(10);
		txtDietSelection.setBounds(34, 311, 181, 22);
		frame.getContentPane().add(txtDietSelection);
		
		JComboBox<String> dietComboBox = new JComboBox<String>();
		dietComboBox.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Vegetarian", "Vegan"}));
		dietComboBox.setMaximumRowCount(3);
		dietComboBox.setBounds(246, 310, 110, 22);
		frame.getContentPane().add(dietComboBox);
		
		txtHeight = new JTextField();
		txtHeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeight.setText("Height");
		txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHeight.setEditable(false);
		txtHeight.setColumns(10);
		txtHeight.setBackground(Color.WHITE);
		txtHeight.setBounds(34, 346, 181, 22);
		frame.getContentPane().add(txtHeight);
		
		JComboBox heightComboBox = new JComboBox();
		heightComboBox.setModel(new DefaultComboBoxModel(new String[] {"140", "141", "142", "143", "144", "145", "146", "147", "148", "149",
				"150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", 
				"167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183",
				"184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", 
				"201", "202", "203", "204", "205", "206", "207", "208", "209", "210"}));
		heightComboBox.setBounds(246, 345, 54, 22);
		frame.getContentPane().add(heightComboBox);
		
		txtCurrentWeight = new JTextField();
		txtCurrentWeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrentWeight.setText("Current Weight");
		txtCurrentWeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCurrentWeight.setEditable(false);
		txtCurrentWeight.setColumns(10);
		txtCurrentWeight.setBackground(Color.WHITE);
		txtCurrentWeight.setBounds(34, 389, 181, 22);
		frame.getContentPane().add(txtCurrentWeight);
		
		JComboBox weightKgComboBox = new JComboBox();
		weightKgComboBox.setModel(new DefaultComboBoxModel(new String[] {"40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "235", "236", "237", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257", "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269", "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281", "282", "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293", "294", "295", "296", "297", "298", "299", "300"}));
		weightKgComboBox.setBounds(246, 390, 44, 22);
		frame.getContentPane().add(weightKgComboBox);
		
		JComboBox weightGrComboBox = new JComboBox();
		weightGrComboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "100", "200", "300", "400", "500", "600", "700", "800", "900", ""}));
		weightGrComboBox.setBounds(324, 390, 55, 22);
		frame.getContentPane().add(weightGrComboBox);
		
		currKg = new JTextField();
		currKg.setBackground(Color.GRAY);
		currKg.setText("Kg");
		currKg.setBounds(290, 390, 31, 22);
		frame.getContentPane().add(currKg);
		currKg.setColumns(10);
		
		currGr = new JTextField();
		currGr.setText("Gr");
		currGr.setColumns(10);
		currGr.setBackground(Color.GRAY);
		currGr.setBounds(377, 390, 31, 22);
		frame.getContentPane().add(currGr);
		
		txtTargetWeight = new JTextField();
		txtTargetWeight.setText("Target Weight");
		txtTargetWeight.setHorizontalAlignment(SwingConstants.CENTER);
		txtTargetWeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTargetWeight.setEditable(false);
		txtTargetWeight.setColumns(10);
		txtTargetWeight.setBackground(Color.WHITE);
		txtTargetWeight.setBounds(34, 436, 181, 22);
		frame.getContentPane().add(txtTargetWeight);
		
		targetWeightKgComboBox = new JComboBox();
		targetWeightKgComboBox.setModel(new DefaultComboBoxModel(new String[] {"40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "235", "236", "237", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257", "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269", "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281", "282", "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293", "294", "295", "296", "297", "298", "299", "300"}));
		targetWeightKgComboBox.setBounds(246, 437, 44, 22);
		frame.getContentPane().add(targetWeightKgComboBox);
		
		tarKg = new JTextField();
		tarKg.setText("Kg");
		tarKg.setColumns(10);
		tarKg.setBackground(Color.GRAY);
		tarKg.setBounds(290, 437, 31, 22);
		frame.getContentPane().add(tarKg);
		
		targetWeightGrComboBox = new JComboBox();
		targetWeightGrComboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "100", "200", "300", "400", "500", "600", "700", "800", "900"}));
		targetWeightGrComboBox.setBounds(324, 437, 55, 22);
		frame.getContentPane().add(targetWeightGrComboBox);
		
		tarGr = new JTextField();
		tarGr.setText("Gr");
		tarGr.setColumns(10);
		tarGr.setBackground(Color.GRAY);
		tarGr.setBounds(377, 437, 31, 22);
		frame.getContentPane().add(tarGr);
		
		 String datePattern = "yyyy-MM-dd";
		 SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		
		JComboBox<String> bgnDay = new JComboBox<String>();
		bgnDay.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
				"12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}));
		bgnDay.setBounds(246, 472, 44, 22);
		frame.getContentPane().add(bgnDay);
		
		JComboBox<String> bgnMonth = new JComboBox<String>();
		bgnMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07","08", "09", "10", "11","12"}));
		bgnMonth.setBounds(287, 472, 44, 22);
		frame.getContentPane().add(bgnMonth);
		
		JComboBox<String> bgnYear = new JComboBox<String>();
		bgnYear.setModel(new DefaultComboBoxModel<String>(new String[] {"2020", "2021", "2022", "2023","2024","2025","2026","2027","2028","2029","2030"}));
		bgnYear.setBounds(331, 472, 68, 22);
		frame.getContentPane().add(bgnYear);
		
		JComboBox<String> endDay = new JComboBox<String>();
		endDay.setModel(new DefaultComboBoxModel<String>(new String[]  {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
				"12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"}));
		endDay.setBounds(246, 507, 44, 22);
		frame.getContentPane().add(endDay);
		
		JComboBox<String> endMonth = new JComboBox<String>();
		endMonth.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07","08", "09", "10", "11","12"}));
		endMonth.setBounds(287, 507, 44, 22);
		frame.getContentPane().add(endMonth);
		
		JComboBox<String> endYear = new JComboBox<String>();
		endYear.setModel(new DefaultComboBoxModel<String>(new String[]{"2020", "2021", "2022", "2023","2024","2025","2026","2027","2028","2029","2030"}));
		endYear.setBounds(331, 507, 68, 22);
		frame.getContentPane().add(endYear);
		
		txtBeginDate = new JTextField();
		txtBeginDate.setText("Begin Date");
		txtBeginDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtBeginDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBeginDate.setEditable(false);
		txtBeginDate.setColumns(10);
		txtBeginDate.setBackground(Color.WHITE);
		txtBeginDate.setBounds(34, 472, 181, 22);
		frame.getContentPane().add(txtBeginDate);
		
		txtEndDate = new JTextField();
		txtEndDate.setText("End Date");
		txtEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtEndDate.setEditable(false);
		txtEndDate.setColumns(10);
		txtEndDate.setBackground(Color.WHITE);
		txtEndDate.setBounds(34, 507, 181, 22);
		frame.getContentPane().add(txtEndDate);
		
		nutriDisease = new JTextField();
		nutriDisease.setText("Nutritional Disease");
		nutriDisease.setHorizontalAlignment(SwingConstants.CENTER);
		nutriDisease.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nutriDisease.setEditable(false);
		nutriDisease.setColumns(10);
		nutriDisease.setBackground(Color.WHITE);
		nutriDisease.setBounds(34, 557, 181, 22);
		frame.getContentPane().add(nutriDisease);
		
		JComboBox diseaseComboBox = new JComboBox();
		diseaseComboBox.setModel(new DefaultComboBoxModel(new String[] {"No Disease", "Diabetes", "Celiac", "Reflux"}));
		diseaseComboBox.setBounds(243, 558, 136, 22);
		frame.getContentPane().add(diseaseComboBox);
			
		JTextArea usernameError = new JTextArea();
		usernameError.setBackground(SystemColor.menu);
		usernameError.setBounds(364, 73, 187, 16);
		frame.getContentPane().add(usernameError);
		
		JTextArea passwordError = new JTextArea();
		passwordError.setBackground(SystemColor.menu);
		passwordError.setBounds(364, 130, 187, 16);
		frame.getContentPane().add(passwordError);
		
		JTextArea targetWeightError = new JTextArea();
		targetWeightError.setFont(new Font("Monospaced", Font.BOLD, 10));
		targetWeightError.setBackground(SystemColor.menu);
		targetWeightError.setBounds(417, 440, 175, 16);
		frame.getContentPane().add(targetWeightError);
		
		JTextArea endDateError = new JTextArea();
		endDateError.setFont(new Font("Monospaced", Font.BOLD, 10));
		endDateError.setBackground(SystemColor.menu);
		endDateError.setBounds(405, 509, 187, 16);
		frame.getContentPane().add(endDateError);
		
		JButton submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				SignUpToMain sutm = new SignUpToMain(getUsername().getText(), getPassword().getText());
				SignUpToMain.SignUpDependentChecks registerGeneralCheck = sutm.new SignUpDependentChecks();
			
					try {
						if(registerGeneralCheck.generalRegisCheck(getUsername().getText(), getPassword().getText(),
						    programSelectComboBox.getSelectedItem().toString(), weightKgComboBox.getSelectedItem().toString() + weightGrComboBox.getSelectedItem().toString(),
						    targetWeightKgComboBox.getSelectedItem().toString() + targetWeightGrComboBox.getSelectedItem().toString() ,dateUnioner(bgnYear,bgnMonth,bgnDay) , dateUnioner(endYear,endMonth,endDay)))
						{
							
							try {
								
								File outputfile = new File("Images/UserProfileImages/" + username.getText()+ ".jpg");
								ImageIO.write(image, "jpg", outputfile);
								
								}catch(IOException e) {
									System.out.println("Profile fotosu yazdýrma hatasý");
									e.printStackTrace();
								}
						
							 	ReadFile rdf = new ReadFile();
							 	String gender;
							 	if(rdbtnM.isSelected()) {
						 			gender="male";
						 		}
						 		else {
						 			gender="female";
						 		}
							 	if(programSelectComboBox.getSelectedIndex()!=2) {
							 		
							 		
							 		String unionedDateFormat = dateFormatUnioner(bgnDay,bgnMonth,bgnYear) +"-"+dateFormatUnioner(endDay,endMonth,endYear);
							 		String unionedWeightFormat = weightFormatUnioner(weightKgComboBox,weightGrComboBox)+"-"+weightFormatUnioner(targetWeightKgComboBox,targetWeightGrComboBox);
							 		int height = Integer.parseInt(heightComboBox.getSelectedItem().toString());
							 		int age = Integer.parseInt(ageComboBox.getSelectedItem().toString());
							 		rdf.newUserWriter(getUsername().getText(),  getPassword().getText(), programSelectComboBox.getSelectedIndex()
							 				,unionedDateFormat,unionedWeightFormat,height,age,gender, dietComboBox.getSelectedIndex(),
							 				diseaseComboBox.getSelectedIndex(), 0 , 0);
							 		}
							 	else {
							 		String unionedDateFormat = dateFormatUnioner(bgnDay,bgnMonth,bgnYear) +"-"+dateFormatUnioner(endDay,endMonth,endYear);
							 		String beginWeightFormat = weightFormatUnioner(weightKgComboBox,weightGrComboBox);
							 		int height = Integer.parseInt(heightComboBox.getSelectedItem().toString());
							 		int age = Integer.parseInt(ageComboBox.getSelectedItem().toString());
							 		rdf.newUserWriter(getUsername().getText(),  getPassword().getText(), programSelectComboBox.getSelectedIndex()
							 				,unionedDateFormat,beginWeightFormat,height,age,gender, dietComboBox.getSelectedIndex(),
							 				diseaseComboBox.getSelectedIndex(), 0 , 0);
							 	}
							 	
							 	rdf.readFileForExistUser(getUsername().getText(),getPassword().getText());
							 	MainScreen mnscn = new MainScreen(rdf.getCurrentOnlineUser());
							 	frame.setVisible(false);
						}
						else {
							usernameError.setText(sutm.getUserWarning());
							passwordError.setText(sutm.getPasswordWarning());
							
							targetWeightError.setText(registerGeneralCheck.getElementWarn().split(";")[0]);
							endDateError.setText(registerGeneralCheck.getElementWarn().split(";")[1]);
							if(!registerGeneralCheck.isUsernameRepeatErr()) {
								usernameError.setText("Username exists try new");
							}
							System.out.println("Kullanýcnýn Girdiði bilgiler hatalýdýr.");
						}
					} catch (IOException e) {
						
						System.out.println("Programm Error");
						e.printStackTrace();
					}

			}
		});
		submitButton.setBounds(204, 608, 175, 49);
		frame.getContentPane().add(submitButton);
		this.frame.setVisible(true);
	}

	// Getters and Setters
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JTextField password) {
		this.password = password;
	}

	public JTextField getTxtProgrammeSelection() {
		return txtProgrammeSelection;
	}

	public void setTxtProgrammeSelection(JTextField txtProgrammeSelection) {
		this.txtProgrammeSelection = txtProgrammeSelection;
	}

	public JTextField getTxtDietSelection() {
		return txtDietSelection;
	}

	public void setTxtDietSelection(JTextField txtDietSelection) {
		this.txtDietSelection = txtDietSelection;
	}

	public JTextField getTxtHeight() {
		return txtHeight;
	}

	public void setTxtHeight(JTextField txtHeight) {
		this.txtHeight = txtHeight;
	}

	public JTextField getTxtCurrentWeight() {
		return txtCurrentWeight;
	}

	public void setTxtCurrentWeight(JTextField txtCurrentWeight) {
		this.txtCurrentWeight = txtCurrentWeight;
	}

	public JTextField getCurrKg() {
		return currKg;
	}

	public void setCurrKg(JTextField currKg) {
		this.currKg = currKg;
	}

	public JTextField getCurrGr() {
		return currGr;
	}

	public void setCurrGr(JTextField currGr) {
		this.currGr = currGr;
	}

	public JTextField getTxtTargetWeight() {
		return txtTargetWeight;
	}

	public void setTxtTargetWeight(JTextField txtTargetWeight) {
		this.txtTargetWeight = txtTargetWeight;
	}

	public JTextField getTarKg() {
		return tarKg;
	}

	public void setTarKg(JTextField tarKg) {
		this.tarKg = tarKg;
	}

	public JTextField getTarGr() {
		return tarGr;
	}

	public void setTarGr(JTextField tarGr) {
		this.tarGr = tarGr;
	}

	public JTextField getTxtBeginDate() {
		return txtBeginDate;
	}

	public void setTxtBeginDate(JTextField txtBeginDate) {
		this.txtBeginDate = txtBeginDate;
	}

	public JTextField getTxtEndDate() {
		return txtEndDate;
	}

	public void setTxtEndDate(JTextField txtEndDate) {
		this.txtEndDate = txtEndDate;
	}

	public JTextField getNutriDisease() {
		return nutriDisease;
	}

	public void setNutriDisease(JTextField nutriDisease) {
		this.nutriDisease = nutriDisease;
	}

	public JFileChooser getChooser() {
		return chooser;
	}

	public void setChooser(JFileChooser chooser) {
		this.chooser = chooser;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	// This method for union date's elements with "-".
	public String dateUnioner(JComboBox<String> year,JComboBox<String> month,JComboBox<String> day) {
		
		return year.getSelectedItem().toString() + "-" + month.getSelectedItem().toString() +"-" +day.getSelectedItem().toString();
	}
	
	/*
	 *  This method for union date's values to write to user data text file.
	 *  This method for union date's elements with ".".
	*/
	public String dateFormatUnioner(JComboBox<String> year,JComboBox<String> month,JComboBox<String> day) {
		
		return year.getSelectedItem().toString() + "." + month.getSelectedItem().toString() +"." +day.getSelectedItem().toString();
	}
	
	// This method for union weight's values to write to user data text file.
	public String weightFormatUnioner(JComboBox<String> fullPart,JComboBox<String> fractioanPart) {
		
		return fullPart.getSelectedItem().toString() + "." + fractioanPart.getSelectedItem().toString();
	}
}
