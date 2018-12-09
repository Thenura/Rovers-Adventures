import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

/*
 * Name: Thenura Jayasinghe
 * Date: January 20th, 2017
 * Course Code: (ICS-3U1)
 * Title: Rover's Adventures
 * Description:
 * 			-Movement of Rover (Arrow Keys)
 * 			-Creation of Own Layout (4 Layouts)
 * 			-Multiple Levels Led By Door Icon (Accessible After Completion of First Level)
 * 			-Ball Enemies (Move Up and Down) && (Move Left and Right)
 * 			-Collision Detection (Between Rover and Enemies)
 * 			-Death Icon Shown (Time Runs Out or Collision)
 * 			-Audio (Star Collection Sound Effect)
 * 			-Respawn Allocation All Levels (Reset Where Rover Respawns to Avoid Error and Reset Board)
 * 			-Splash Screen Introduction 
 * 			-Score Label, Lives Label, HighScore Label with Name, Time Label
 * 			-Time Restriction (60 Seconds Each Level) && (Shows time remaining)
 * 			-Star Collection Restriction (Cannot Pass Level without all the stars)
 * 			-Enter Highscore Name with 3 Character Restriction
 * 			-Score is Time * Score to get more accurate score --> Complete the fastest 
 * 
 * Areas of Concern:
 * 			-Highscore is Written on All Levels in textfile, but only displays in the first level 
 * 			-Images lagging when importing from Windows OS to Mac OS 
 * 					-Main Screen Shows White Buttons on Mac, but desired colors on Windows (Same Code)
 */

/**
*This class is used to create a new RoverGUI that will start a Rover game
*@author Thenura Jayasinghe
*/
public class RoverGame {
	
	/**
	 * Main method to run program and create a new GUI
	 */
	
	//Status of Rover
	public static boolean death = false;
	
	//Lives for Rover  (Changes)
	public static int roverLives = 3;
	
	public static void main(String[] args){
		
		//1.Spash Screen 
		//1.1 New Window
		JWindow window = new JWindow();

		//2. Add JLabel (GIF) to JWindow
		window.getContentPane().add(new JLabel("", new ImageIcon("new images/splash.gif"),SwingConstants.CENTER));
		window.setBounds(300,300,550,320);

		//3. Make JWindow Visible 
		window.setVisible(true);

		//4 Assign how long image will stay on for (6 seconds)
		try {
			Thread.sleep(6000);
		}catch(InterruptedException e){}

		//5. Close Window
		window.dispose();
		
		new IntroScreen();
		
		//7.Calls highscoreinput method to show the existing highscore depending on level
		if (NextLevelScreen.level==1)
			HighScore.highscoreInput1();
		
		else if (NextLevelScreen.level==2)
			HighScore.highscoreInput2();
		
		else if (NextLevelScreen.level==3)
			HighScore.highscoreInput3();
		
		else if (NextLevelScreen.level==4)
			HighScore.highscoreInput4();
	}

}
