import java.io.*;
import java.util.*;

/**
 * This class represents the high score of the game and 
 * writing and saving to a text file
 * 
 * @author Thenura Jayasinghe
 */

public class HighScore {
	
	static int highscore;
	static int highscore2;
	static int highscore3;
	static int highscore4;
	
	static String name;
	static String name2;
	static String name3;
	static String name4;
	
	/**
	 * Write File Method 
	 */
	public static void writeFile() {
		
		//Alter text file based on score
		if (NextLevelScreen.level ==1){
			try {
				
				//1.Opens Highscore TextFile
				FileWriter fw = new FileWriter("highscore.txt");
				
				//2. Writes values given to the Text File
				PrintWriter pw = new PrintWriter(fw);
				
				//3. Highscore and Name
				pw.println(Board.score* Board.time);
				pw.println(Board.name);
				
				pw.close();

			} catch (IOException e) {
				
				System.out.println("Error!");
				
			}
		}
		
		//Alter text file based on score
		else if (NextLevelScreen.level ==2){
			try {
				
				//1.Opens Highscore TextFile
				FileWriter fw = new FileWriter("highscore2.txt");
				
				//2. Writes values given to the Text File
				PrintWriter pw = new PrintWriter(fw);
				
				//3. Highscore and Name
				pw.println(Board.score* Board.time);
				pw.println(Board.name);
				
				pw.close();

			} catch (IOException e) {
				
				System.out.println("Error!");
				
			}
		}
		
		//Alter text file based on score
		else if (NextLevelScreen.level ==3){
			try {
				
				//1.Opens Highscore TextFile
				FileWriter fw = new FileWriter("highscore3.txt");
				
				//2. Writes values given to the Text File
				PrintWriter pw = new PrintWriter(fw);
				
				//3. Highscore and Name
				pw.println(Board.score* Board.time);
				pw.println(Board.name);
				
				pw.close();

			} catch (IOException e) {
				
				System.out.println("Error!");
				
			}
		}
		
		//Alter text file based on score
		else if (NextLevelScreen.level ==4){
			try {
				
				//1.Opens Highscore TextFile
				FileWriter fw = new FileWriter("highscore4.txt");
				
				//2. Writes values given to the Text File
				PrintWriter pw = new PrintWriter(fw);
				
				//3. Highscore and Name
				pw.println(Board.score* Board.time);
				pw.println(Board.name);
				
				pw.close();

			} catch (IOException e) {
				
				System.out.println("Error!");
				
			}
		}
	}	
	

	/**
	 * Generate Getters
	 */
	public static int getHighscore() {

		return highscore;
	}
	
	public static int getHighscore2() {

		return highscore2;
	}
	
	public static int getHighscore3() {

		return highscore3;
	}
	
	public static int getHighscore4() {

		return highscore4;
	}
	
	public static String getname() {

		return name;
	}
	
	public static String getname2() {

		return name2;
	}
	
	public static String getname3() {

		return name3;
	}
	public static String getname4() {

		return name4;
	}
	
	//Used to show highscore on the Label for level 1 
	public static void highscoreInput1() {

		try {
			
			//Opens High Score File
			Scanner input = new Scanner(new File("highscore.txt"));
			
			//Assigns highscore variable with value from text file
			highscore = input.nextInt();
			name = input.next();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}

	//Used to show highscore on the Label for level 2
	public static void highscoreInput2() {

		try {
			
			//Opens High Score File
			Scanner input = new Scanner(new File("highscore2.txt"));
			
			//Assigns highscore variable with value from text file
			highscore2= input.nextInt();
			name2= input.next();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}
	
	//Used to show highscore on the Label  for level 3
	public static void highscoreInput3() {

		try {
			
			//Opens High Score File
			Scanner input = new Scanner(new File("highscore3.txt"));
			
			//Assigns highscore variable with value from text file
			highscore3 = input.nextInt();
			name3 = input.next();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}
	
	//Used to show highscore on the Label for level 4
	public static void highscoreInput4() {

		try {
			
			//Opens High Score File
			Scanner input = new Scanner(new File("highscore4.txt"));
			
			//Assigns highscore variable with value from text file
			highscore4 = input.nextInt();
			name4 = input.next();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		}
	}
}
