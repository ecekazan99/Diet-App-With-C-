import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.io.IOException;
import java.util.List;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * This class created for taking user' current weight,current date for editing weight.
 */
public class PersonalProgress extends JFrame {

	// Attributes
	private JPanel contentPane;
	private JTextField userNewWeight;
	private JTextField userNewDate;

	// Constructor
	public PersonalProgress(User currOnlineUserr) {
		this.setVisible(true);
		setBounds(100, 100, 680, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		userNewWeight = new JTextField();
		userNewWeight.setBounds(237, 99, 116, 22);
		contentPane.add(userNewWeight);
		userNewWeight.setColumns(10);
		JTextArea txtrEdtWeght = new JTextArea();
		txtrEdtWeght.setFont(new Font("MV Boli", Font.BOLD, 16));
		txtrEdtWeght.setText("EDIT WEIGHT\r\n");
		txtrEdtWeght.setBounds(220, 32, 140, 30);
		contentPane.add(txtrEdtWeght);

		JTextArea txtrPleaseInsertYour = new JTextArea();
		txtrPleaseInsertYour.setEditable(false);
		txtrPleaseInsertYour.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		txtrPleaseInsertYour.setText("Please Insert Your Current Weight As : 56.5 (0-1000) \r\n");
		txtrPleaseInsertYour.setBounds(364, 100, 285, 22);
		contentPane.add(txtrPleaseInsertYour);

		JTextArea txtrPleaseInsertDate = new JTextArea();
		txtrPleaseInsertDate.setEditable(false);
		txtrPleaseInsertDate.setText("Please Insert Date As  (dd/mm/yy) : 2.6.2020 (2010-2030) \r\n");
		txtrPleaseInsertDate.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		txtrPleaseInsertDate.setBounds(364, 135, 295, 22);
		contentPane.add(txtrPleaseInsertDate);

		JTextArea warningField = new JTextArea();
		warningField.setBackground(SystemColor.control);
		warningField.setBounds(194, 166,400, 22);
		contentPane.add(warningField);

		userNewDate = new JTextField();
		userNewDate.setColumns(10);
		userNewDate.setBounds(237, 134, 116, 22);
		contentPane.add(userNewDate);

		JButton editWeightBut = new JButton("Edit Weight");
		editWeightBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadFile rdfc = new ReadFile();
				try {
					 List<ProgressData> filledUserProgList = rdfc.userProgListFiller(currOnlineUserr.getUsername());
					warningField.setText("");
					if (editWeightFormatChechker(userNewDate.getText(),userNewWeight.getText())) {
						if(expAndInterChecker(currOnlineUserr.getBeginDate(),currOnlineUserr.getEndDate(),filledUserProgList.get(filledUserProgList.size()-1).getDate(),userNewDate.getText())) {
						rdfc.addNewValToProgFile(currOnlineUserr.getUsername(), userNewDate.getText(),
								Double.parseDouble(userNewWeight.getText()));
						currOnlineUserr.editCurrentData(
								new Date(Integer.parseInt(userNewDate.getText().split("\\.")[0]),
										Integer.parseInt(userNewDate.getText().split("\\.")[1]),
										Integer.parseInt(userNewDate.getText().split("\\.")[2])),
								Double.parseDouble(userNewWeight.getText()));
						warningField.setText("New data edded succesfuly");
						}
						else {
							warningField.setText("Date must be between : " + currOnlineUserr.getBeginDate().toString() +"-"+currOnlineUserr.getEndDate().toString() +"and after :" + filledUserProgList.get(filledUserProgList.size()-1).getDate().toString() );
						}
					} else {
						warningField.setText("Entered value is wrong");
					}
				} catch (NumberFormatException | IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		editWeightBut.setBounds(228, 191, 125, 25);
		contentPane.add(editWeightBut);

		JTextArea txtrWeight = new JTextArea();
		txtrWeight.setText("Weight : ");
		txtrWeight.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		txtrWeight.setEditable(false);
		txtrWeight.setBounds(155, 100, 51, 22);
		contentPane.add(txtrWeight);

		JTextArea txtrDate = new JTextArea();
		txtrDate.setText("Date :");
		txtrDate.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		txtrDate.setEditable(false);
		txtrDate.setBounds(162, 135, 44, 22);
		contentPane.add(txtrDate);

	}
	
	//Here checking new input of date value is in correct interval or not. 
	public boolean expAndInterChecker(Date beginDateOfUser,Date endDateOfUser,Date lastDateOfUser,String givenDateOfUser) {
		String begDateFormat = "";
		begDateFormat+=beginDateOfUser.getYear();
		if (beginDateOfUser.getMonth() < 10) {
			begDateFormat += "0" + beginDateOfUser.getMonth();
		}
		else {
			begDateFormat+=beginDateOfUser.getMonth();
		}
		if (beginDateOfUser.getDay() < 10) {
			begDateFormat += "0" + beginDateOfUser.getDay();
		}
		else
		{
			begDateFormat+=beginDateOfUser.getDay();
		}
		int begDateIntForm = Integer.parseInt(begDateFormat);
		
		String endDateFormat = "";
		endDateFormat+=endDateOfUser.getYear();
		
		if (endDateOfUser.getMonth() < 10) {
			endDateFormat += "0" + endDateOfUser.getMonth();
		}
		else {
			endDateFormat+=endDateOfUser.getMonth();
		}
		
		if (endDateOfUser.getDay() < 10) {
			endDateFormat += "0" + endDateOfUser.getDay();
		}
		else
		{
			endDateFormat+=endDateOfUser.getDay();
		}
		int endDateIntForm = Integer.parseInt(endDateFormat);
		
		
		String lastEditDateOfUser = "";
		lastEditDateOfUser+=lastDateOfUser.getYear();
		
		if (lastDateOfUser.getMonth() < 10) {
			lastEditDateOfUser += "0" + lastDateOfUser.getMonth();
		}
		else {
			lastEditDateOfUser+=lastDateOfUser.getMonth();
		}
		
		if (lastDateOfUser.getDay() < 10) {
			lastEditDateOfUser += "0" + lastDateOfUser.getDay();
		}
		else
		{
			lastEditDateOfUser+=lastDateOfUser.getDay();
		}
		int lastDateIntForm = Integer.parseInt(lastEditDateOfUser);
		
		String[] givenDateArr = givenDateOfUser.split("\\.");
		String givenDateFormat="";
		for (int i = givenDateArr.length - 1; i >=0; i--) {
			if(givenDateArr[i].length()==1) {
				givenDateFormat+="0";
			}
			givenDateFormat+=givenDateArr[i];
		}
		int givenDateIntForm = Integer.parseInt(givenDateFormat);
		
		if(givenDateIntForm>lastDateIntForm && givenDateIntForm < endDateIntForm && givenDateIntForm> begDateIntForm) { 
			
			return true;
		}
		System.out.println("User input  date : " + givenDateIntForm +"\n" + "End Date : " + endDateIntForm +"\n" +"Beg date" +begDateIntForm +"\n"+"Last Inputted" + lastDateIntForm);
		return false;	
	}
	
	//Here checking format is okey or not
	public boolean editWeightFormatChechker(String givenDate,String userNewWeight) {
		String splittedForm[];
		try {
			splittedForm = givenDate.split("\\.");
			for (int i = 0; i < splittedForm.length; i++) {
				int val = Integer.parseInt(splittedForm[i]);
				if (i == 0 && (val <= 0 || val > 31)) {
					return false;
				} else if (i == 1 && (val <= 0 || val > 12)) {
					return false;
				} else if (i == 2 && (val <= 2010 || val > 2030)) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		if (splittedForm.length != 3) {
			return false;
		}
		try {
			double newWeiOfUser = Double.parseDouble(userNewWeight);
			if (newWeiOfUser > 1000 || newWeiOfUser <= 0) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// Getters and Setters
	public JPanel getContentPanel() {
		return contentPane;
	}

	public void setContentPanel(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTextField getUserNewWeight() {
		return userNewWeight;
	}

	public void setUserNewWeight(JTextField userNewWeight) {
		this.userNewWeight = userNewWeight;
	}

	public JTextField getUserNewDate() {
		return userNewDate;
	}

	public void setUserNewDate(JTextField userNewDate) {
		this.userNewDate = userNewDate;
	}

}
