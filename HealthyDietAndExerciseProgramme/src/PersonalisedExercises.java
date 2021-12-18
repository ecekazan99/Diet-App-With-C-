import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PersonalisedExercises extends JFrame {
	public int counter =1;
	public JFrame myFramef = new JFrame("EXERCISES");
	
	public void programmeSelection(int selection) {	
		if(selection==0)
			showExercises("lose_weight.gif");
		else if(selection==1)
			showExercises("gain_weight.gif");
		else if(selection==2)
			showExercises("keep_fit.gif");
 	}

 	public void showExercises(String gif) {
        ImageIcon imageIcon = new ImageIcon("Gifs/"+gif);
        JLabel label = new JLabel(imageIcon);
        myFramef.getContentPane().add(label);
        myFramef.pack();
        myFramef.setLocationRelativeTo(null);
        myFramef.setVisible(true);
 	}
}