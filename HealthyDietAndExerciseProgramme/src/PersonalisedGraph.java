import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/*
 * This class created for displaying user's personal progress graph which depending on weights and dates. 
*/
public class PersonalisedGraph {
	
	// Attributes
    private JFrame frame;
    private User currOnlineUser;
    
    public PersonalisedGraph ( User givenCurrOnlineUser) throws IOException {
    	this.currOnlineUser = givenCurrOnlineUser;	
    }
    
    // This method for showing graph
    public void run() {
        SwingUtilities.invokeLater(() -> {
			try {
				new PersonalisedGraph(currOnlineUser).createAndShowGui();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		});
    }
  
    // This method for creating personal graph frame.
    private void createAndShowGui() throws IOException {  	
    	frame = new JFrame("Personal Progress Graph");
        GraphDrawer drawer = new GraphDrawer();
        frame.getContentPane().add(drawer);
        frame.pack();
        frame.setVisible(true);
    }

    // This inner class for creating graph and its contents.
    @SuppressWarnings("serial")
    class GraphDrawer extends JPanel {
        private double startX = 100; 
        private double startY = 100;
        private double endX = 1800; 
        private double endY = 900; 
        private double unitX;
        private double unitY; 
        private int sizeOfList; 
        private  List<ProgressData> progressDataRepo = new ArrayList<ProgressData>();
        
        // Constructor
        public GraphDrawer() throws IOException {
        	ReadFile rdf=new ReadFile();
        	int size=rdf.userProgListFiller(currOnlineUser.getUsername()).size();
        	int index=0;
        	if(size>=20) {
        		index=size-20;
        	}
        	for (int i = index; i < size; i++) {
				progressDataRepo.add(rdf.userProgListFiller(currOnlineUser.getUsername()).get(i));
			}
        	sizeOfList=progressDataRepo.size();
            this.unitX=(endX - startX) / sizeOfList;
            this.unitY=(endY - startY) / sizeOfList;
        }
        
        // This method sorts weight values.
        void insertionSort(double arr[]) 
        { 
            int n = arr.length; 
            for (int i = 1; i < n; ++i) { 
                double key = arr[i];  
                int j = i - 1; 
                  
                while (j >= 0 && arr[j] > key) { 
                    arr[j + 1] = arr[j]; 
                    j = j - 1; 
                } 
                arr[j + 1] = key;
            } 
        }
        
        // This method gets max weight before sort operation.
        public double getMaxValue(double[] numbers){
        	double maxValue = numbers[0];
        	  for(int i=1;i < numbers.length;i++){
        	    if(numbers[i] > maxValue){
        		  maxValue = numbers[i];
        		}
        	  }
        	  return maxValue;
        }
        
        // This method gets min weight before sort operation.
        public double getMinValue(double[] numbers){
        	  double minValue = numbers[0];
        	  for(int i=1;i<numbers.length;i++){
        	    if(numbers[i] < minValue){
        		  minValue = numbers[i];
        		}
        	  }
        	  return minValue;
        }
        
        // This method creates graph, graph's date and weight values, graph lines.
        @Override
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.MAGENTA);
            g2d.drawString("Weights(kg)", 70, 10);
            g2d.drawString("Dates(dd.mm)", 1805, 905);
            double heightCoefficient =0;   
            double[] weights=new double[progressDataRepo.size()];  
            double[] tempOfWeights=new double[progressDataRepo.size()];  
            double[] heightOfWeights=new double[progressDataRepo.size()];  
            for (int i = 0; i < weights.length; i++) {
            	weights[i]=progressDataRepo.get(i).getUserWeight();	
            	tempOfWeights[i]=progressDataRepo.get(i).getUserWeight();
			}
            
            heightCoefficient=(endY-unitY)/((getMaxValue(weights)-(getMaxValue(weights)%10))+20-(getMinValue(weights)-(getMinValue(weights)%10)));
           
            for (int i = 0; i < weights.length; i++) {
            	heightOfWeights[i]=endY-(unitY+heightCoefficient*(Math.abs((getMinValue(weights)-(getMinValue(weights)%10))-weights[i])));
			}
            
            insertionSort(weights); 
            double leastWeight=weights[0]; 
           
            g2d.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < tempOfWeights.length-1; i++) {
                g2d.draw(new Line2D.Double(startX+ unitX*i,(heightOfWeights[i]), startX+ unitX*i, endY));
            }
            g2d.draw(new Line2D.Double(startX+ unitX*(tempOfWeights.length-1),(heightOfWeights[tempOfWeights.length-1]),startX+ unitX*(tempOfWeights.length-1),endY));
           
            double maxHeight=endY-(unitY+heightCoefficient*(Math.abs(leastWeight-((getMaxValue(weights)-(getMaxValue(weights)%10))+20)))); 
            if(maxHeight>startY) {
            	maxHeight=startY;
            }
            
            g2d.setColor(Color.BLACK);
            g2d.draw(new Line2D.Double(startX, maxHeight, startX, endY));
            g2d.draw(new Line2D.Double(startX, endY, endX, endY));
           
            for (int i = 0; i < progressDataRepo.size(); i++) {
            	g2d.drawString(progressDataRepo.get(i).getDate().getDay()+"."+progressDataRepo.get(i).getDate().getMonth()+"."+progressDataRepo.get(i).getDate().getYear(),(int)(75+ unitX*i),930);	
            }
            
            for (double i = (getMinValue(weights)-(getMinValue(weights)%10)); i <= ((getMaxValue(weights)-getMaxValue(weights)%10)+10); i+=10) {
				g2d.drawString(String.valueOf(i), 60, (int) (endY-(unitY+heightCoefficient*(Math.abs((leastWeight-(leastWeight%10))-i)))));
			}
          
            g2d.setColor(Color.RED);
            for (int i = 0; i < weights.length-1; i++) 
			{
               g2d.draw(new Line2D.Double(startX+ unitX*i, heightOfWeights[i], unitX+startX+unitX*i,  heightOfWeights[i+1]));
            }
            
            g2d.setColor(Color.BLACK);
            for (int j = 0; j < tempOfWeights.length-1; j++) {
            	g2d.drawString(String.valueOf(tempOfWeights[j]), (int)(startX+ unitX*(j)),(int)(heightOfWeights[j]));
			}
            g2d.drawString(String.valueOf(tempOfWeights[tempOfWeights.length-1]), (int)(startX+ unitX*(tempOfWeights.length-1)),(int)(heightOfWeights[tempOfWeights.length-1]));
        	}
        	
        // This method for getting preferred size for frame
        @Override
        public Dimension getPreferredSize() {
        	return new Dimension((int)endX + 100, (int)endY + 100);
        }
    }

    // Getters and Setters
	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public User getCurrOnlineUser() {
		return currOnlineUser;
	}


	public void setCurrOnlineUser(User currOnlineUser) {
		this.currOnlineUser = currOnlineUser;
	}
    
    
}
