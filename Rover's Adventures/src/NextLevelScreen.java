import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class represents the next level screen which appears when
 * the game has finished
 * 
 * @author Thenura Jayasinghe
 */
public class NextLevelScreen extends JFrame implements ActionListener{

	//End Panel
	JPanel nextLevelPanel = new JPanel();

	//End Panel Buttons
	JButton level2Button = new JButton("Level 2");
	JButton level3Button = new JButton("Level 3");
	JButton level4Button = new JButton("Level 4");
	JButton exitButton = new JButton("Exit");
	
	/**
	 * Level number
	 */
	public static int level =1;
	
	/**
	 * Next Level Screen Constructor 
	 */
	public NextLevelScreen() {
		
		//1. Setup the GUI
		setLayout(null);
		
		//2. Set Bounds
		this.setBounds(0, 0, 350, 450);
		
		//3. Set The Title and Exit Option
		setTitle("Rover's Adventure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//4. Make GUI Visible
		setVisible(true);		
		
		//5. Call End Panel Method
		nextLevelPanel();
	}
	
	/**
	 * Next Level Screen Panel 
	 */
	public void nextLevelPanel(){
		
		//1. Set up Panel
		nextLevelPanel.setLayout(null);
		nextLevelPanel.setVisible(true);
		Font buttonFont = new Font ("Arial",Font.BOLD,45);
		
		//2. Set Bounds 
		nextLevelPanel.setBounds(0,0,350,450);
		level2Button.setBounds(30,10,300,100);
		level3Button.setBounds(30,110,300,100);
		level4Button.setBounds(30,210,300,100);
		exitButton.setBounds(30,310,300,100);
		
		//3. Change Color
		nextLevelPanel.setBackground(Color.BLACK);
		level2Button.setBackground(Color.YELLOW);
		level3Button.setBackground(Color.YELLOW);
		level4Button.setBackground(Color.YELLOW);
		exitButton.setBackground(Color.YELLOW);
		
		//4. Set Font
		level2Button.setFont(buttonFont);
		level3Button.setFont(buttonFont);
		level4Button.setFont(buttonFont);
		exitButton.setFont(buttonFont);

		//5. Add Action Listener
		level2Button.addActionListener(this);
		level3Button.addActionListener(this);
		level4Button.addActionListener(this);
		exitButton.addActionListener(this);

		//6. Add objects to Panel
		nextLevelPanel.add(level2Button);
		nextLevelPanel.add(level3Button);
		nextLevelPanel.add(level4Button);
		nextLevelPanel.add(exitButton);
		
		//7. Add Panel
		this.add(nextLevelPanel);
	}
	public void actionPerformed(ActionEvent e) {
		
		//6.Action If Play Button Clicked
		if(e.getSource() == level2Button){
						
			//6.1 Make End Screen Non Visible
			setVisible(false);
			
			//6.2 Closes JFrame
			dispose();

			//6.3 Opens Rover GUI
			level = 2;
			new RoverGUI();

		}	  
		
		//6.Action If Play Button Clicked
		else if(e.getSource() == level3Button){
			
			//6.1 Make End Screen Non Visible
			setVisible(false);
			
			//6.2 Closes JFrame
			dispose();

			//6.3 Opens Rover GUI
			level = 3;
			new RoverGUI();

		}
		
		//6.Action If Play Button Clicked
		else if(e.getSource() == level4Button){
			
			//6.1 Make End Screen Non Visible
			setVisible(false);
			
			//6.2 Closes JFrame
			dispose();

			//6.3 Opens Rover GUI
			level = 4;
			new RoverGUI();

		}
		
		//7. If Exit Button is Pressed
		else if(e.getSource() == exitButton){
			
			//Exits Program
			System.exit(0); 	
			
		}
	}
	

}
