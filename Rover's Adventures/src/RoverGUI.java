import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *This class creates a Rover GUI that extends the JFrame class. It has a Board (JPanel) and 
 *Includes a constructor method that sets up the frame and adds a key listener to the board. 
 *@author Thenura Jayasinghe
 */
public class RoverGUI extends JFrame  {
	
	//Board 
	Board board = new Board();
	 
	//Score Board
	JPanel scoreBoard = new JPanel();

	//Score Board Label
	public static JLabel scoreLabel = new JLabel();
	
	//High Score Label
	public static JLabel highScoreLabel = new JLabel ();
	
	//Remaining Lives Label 
	public static JLabel livesLabel = new JLabel();
	
	//Remaining Time Label 
	public static JLabel timeLabel = new JLabel();
	
	/**
	 * Rover GUI constructor
	 */
	public RoverGUI () {
		
		//1.1 Setup the GUI
		setLayout(null);
		setSize(950,740);
		setTitle("Rover's Adventure");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//1.2 Set the Bounds of the board and scoreBoard
		board.setBounds (0,0,950,650);
		scoreBoard.setBounds (0,650,950,90);
		
		//1.3 Add KeyListener to the board 
		addKeyListener(board);

		//1.4 Add the board and scoreBoard to the JFrame
		add(board);
		add(scoreBoard);
		
		//1.5 Make GUI Visible 
		setVisible(true);
		
		//1.6 Call ScoreBoard method 
		scoreBoardPanelSetup();

	}

	/**
	 * Panel Setup for scoreboard
	 */
	private void scoreBoardPanelSetup() {

		//1. Setup ScoreBoard Panel
		scoreBoard.setLayout(null);
		scoreBoard.setBackground (new Color(40,26,11)); 
		
		//2. Setup Font
		Font subTitleFont = new Font ("Arial",Font.BOLD,28);
		scoreLabel.setFont(subTitleFont);
		highScoreLabel.setFont(subTitleFont);
		livesLabel.setFont(subTitleFont);
		timeLabel.setFont(subTitleFont);
		
		//3. Change color 
		scoreLabel.setForeground(Color.WHITE);
		highScoreLabel.setForeground(Color.WHITE);
		livesLabel.setForeground(Color.WHITE);
		timeLabel.setForeground(Color.WHITE);
		
		//3. Setup ScoreBoard, Lives, and Highscore and time Label
		scoreLabel.setBounds(0,0,250,40);
		highScoreLabel.setBounds(0, 30, 500, 40);
		livesLabel.setBounds(400,0,500,40);
		timeLabel.setBounds(400,30,500,40);
						
		//4. Add ScoreBoard, Lives, and Highscore and time Label to Panel 
		scoreBoard.add(scoreLabel);
		scoreBoard.add(highScoreLabel);
		scoreBoard.add(livesLabel);
		scoreBoard.add(timeLabel);

	}
}
