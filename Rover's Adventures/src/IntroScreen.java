import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents the intro screen which appears when
 * the game has started
 * 
 * @author Thenura Jayasinghe
 */
public class IntroScreen extends JFrame implements ActionListener {
	
	//Intro Panel
	JPanel introPanel = new JPanel();

	//Intro Panel Buttons
	JButton startButton = new JButton("Start Game");
	JButton exitButton = new JButton("Exit Game");
	
	//JLabel Images
	JLabel title = new JLabel();
	JLabel rover = new JLabel(); 
	ImageIcon roverImage = new ImageIcon("new images/rover.png");
	ImageIcon titleImage = new ImageIcon("new images/roveradventure.png");
	
	/**
	 * Beginning Screen Constructor
	 */
	public IntroScreen () {
		
		//1.1 Set up the GUI and Set Bounds of the JFrame 
		setLayout(null);
		this.setBounds(0, 0, 871, 659);
				
		//1.2 Set The Title and Exit Option
		setTitle("Rover's Adventures");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//4. Make GUI Visible
		setVisible(true);		
				
		//5. Call End Panel Method
		introPanel();
	}
	
	/**
	 * Beginning Screen Pnael
	 */
	private void introPanel() {
		
		//1. Setup the Panel and make the panel visible
		introPanel.setLayout(null);
		introPanel.setVisible(true);
		Font buttonFont = new Font ("Arial",Font.BOLD,45);
		
		//x,y,w,h
		//2. Set Bounds of the panel, start and exit button and images
		introPanel.setBounds(0,0,871,659);
		startButton.setBounds(215,378,403,112);
		exitButton.setBounds(215,496,403,112);
		
		title.setIcon(titleImage);
		title.setBounds(25,89,488,187);
		
		rover.setIcon(roverImage);
		rover.setBounds(512,26,323,321);
		
		//3. Change Color
		introPanel.setBackground (new Color(13,140,238));
		startButton.setBackground(new Color(4,40,67));
		exitButton.setBackground(new Color(4,40,67));
		
		startButton.setForeground(Color.WHITE);
		exitButton.setForeground(Color.WHITE);
	
		//4. Set Font
		startButton.setFont(buttonFont);
		exitButton.setFont(buttonFont);

		//5. Add Action Listener
		startButton.addActionListener(this);
		exitButton.addActionListener(this);

		//6. Add objects to Panel
		introPanel.add(startButton);
		introPanel.add(exitButton);
		introPanel.add(rover);
		introPanel.add(title);
		
		//7. Add Panel
		this.add(introPanel);
		

	}

	public void actionPerformed(ActionEvent e) {
		
		//8. Action If Start Button Clicked
		if (e.getSource() == startButton){
			
			//8.1 Make Intro Screen Non Visible
			setVisible(false);
			
			//8.2 Open RoverGUI to start new game
			new RoverGUI();
		}
		//9. Action If Exit Button Clicked
		if (e.getSource() == exitButton){

			//9.1 Exit Program
			System.exit(0);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		}
	}
}
