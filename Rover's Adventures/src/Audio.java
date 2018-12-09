import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	
	static Clip starCollection;

	//1. Rover Movement Sound Method 
		//1.1 Define location of movement clip
		//1.2 Play sound using AudioInputStream 
	    //1.3 Open and Start Rover Movement clip

	/**
	 * Loads the star collection sound
	 */
	public static void collectStar() {
		
		//1. Fruit Audio Clip 
		try {

			//1.1. Define location of Fruit Audio Clip
			File collectStarURL = new File("sounds/coin.wav");

			//1.2 . Audio Interface
			starCollection = AudioSystem.getClip();
			
			AudioInputStream starCollectionClip = AudioSystem.getAudioInputStream( collectStarURL );

			//1.3.  Open and Start Fruit Audio Clip
			starCollection.open(starCollectionClip);
			starCollection.start();


		} catch(Exception ex) {
			
			//1.4. File Not Found Error
			System.out.println("Error! fruiteat.wav not found.");
			
		}
	}

	//3. End level Sound Method 
		//3.1 Define location of end level clip
		//3.2 Play sound using AudioInputStream 
		//3.3 Open and end level clip

	//4. Death Sound Method 
		//4.1 Define location of death clip
		//4.2 Play sound using AudioInputStream 
		//4.3 Open and Start death clip
 
}
