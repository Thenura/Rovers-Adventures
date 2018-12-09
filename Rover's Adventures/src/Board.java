import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;


public class Board extends JPanel implements KeyListener, ActionListener {

	/**
	 * Timer for Rover movement
	 */
	private Timer roverTimer = new Timer(250, this); 

	/**
	 * Timer for Enemy movement
	 */
	private Timer enemyTimer = new Timer(310, this);

	/**
	 * Timer for Enemy movement
	 */
	private Timer clockTimer = new Timer(1000, this);

	/**
	 * Variable for time holder
	 */
	public static int time = 60;
	/**
	 * Array to hold the game board characters from the text file
	 */
	private char[][] maze = new char[13][19];

	/**
	 * Array to hold the game board images
	 */
	private JLabel[][] cell = new JLabel[13][19];

	/**
	 * Rover object
	 */
	private Rover rover;

	/**
	 * Array of Enemy objects
	 */
	private BallEnemy[] enemy = new BallEnemy[10];

	/**
	 * track players name
	 */
	public static String name;

	/**
	 * track game score (1pt per food item eaten)
	 */
	public static int score=0;

	/**
	 * ImageIcon constant for blank
	 */
	private static final ImageIcon BLANK = new ImageIcon("new images/blank.png");

	/**
	 * ImageIcon constant for stars
	 */
	private static final ImageIcon STAR = new ImageIcon("new images/newstar.png");

	/**
	 * ImageIcon constant for door
	 */
	private static final ImageIcon DOOR = new ImageIcon("new images/door.png");

	/**
	 * ImageIcon constant for wall
	 */
	private static final ImageIcon WALL = new ImageIcon("new images/wall.png");

	/**
	 * ImageIcon constant for dead
	 */
	private static final ImageIcon DEAD = new ImageIcon("new images/dead.png");

	/**
	 * Construct the game board including the layout, background, Rover and enemies
	 * and calls the loadBoard method
	 */
	public Board() {

		//1.1 Set the layout to a grid and background to white
		setLayout (new GridLayout(13,19));
		setBackground(Color.WHITE);

		//1.2 Create Rover and the enemies
		rover = new Rover();

		for (int x = 0; x<=9; x++){
			enemy[x] = new BallEnemy();
		}

		//1.3 Load the board method 
		loadBoard(NextLevelScreen.level);

	}

	/**
	 * Loads the maze onto the screen from a text file
	 */
	public void loadBoard(int level){

		//keeps track of the row number
		int r = 0; 

		//Open the maze text file for input
		Scanner input = null;
		
		//Level 1 
		if (level ==1){
			try{
				//2.1 Check the level and corresponding file to input (if statement)
				input = new Scanner (new File ("maze.txt"));

				//2.2 Cycle through all the rows in the text file one row at a time
				while (input.hasNext()) {

					//2.2.1 Read the next line from the text file 
					maze[r] = input.nextLine().toCharArray();

					//2.2.2 For each row, cycle through all of the columns 
					for (int c = 0; c < maze[r].length; c++) {

						//2.2.2.1 Create new picture for each cell (grid)
						cell[r][c] = new  JLabel(); 

						//2.2.2.2 If the symbol is a "W", assign a wall picture
						if (maze[r][c] == 'W'){
							cell[r][c].setIcon(WALL);
						}
						//2.2.2.3 If the symbol is a "S", assign a star picture 
						else if (maze[r][c] == 'S'){
							cell[r][c].setIcon(STAR);

						}
						//2.2.2.4 If the symbol is a "D", assign a door picture 
						else if (maze[r][c] == 'D')
							cell[r][c].setIcon(DOOR);

						//2.2.2.4 If the symbol is a "B", assign a blank picture 
						else if (maze[r][c] == 'B')
							cell[r][c].setIcon(BLANK);

						//2.2.2.5 If value is from 0-9 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='0' || maze[r][c] =='2'|| maze[r][c] =='4' || maze[r][c] =='6' ){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(3);
						}
						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='5'|| maze[r][c] =='7'|| maze[r][c] =='8'|| maze[r][c] =='9'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(0);
						}

						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='1'|| maze[r][c] =='3'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(1);
						}
						//2.2.2.6 If symbol is "R", assign rover picture, set row and column  
						else if (maze[r][c] == 'R') {
							cell[r][c].setIcon(rover.getIcon());
							rover.setRow(r);
							rover.setColumn(c);
							rover.setDirection(3);
						}

						//2.2.2.7 If the symbol is a "D", assign a wall picture 
						else if (maze[r][c] == 'F')
							cell[r][c].setIcon(WALL);

						//2.2.2.8 If the symbol is a "U", assign a wall picture 
						else if (maze[r][c] == 'U')
							cell[r][c].setIcon(WALL);

						//2.2.2.9 If the symbol is a "M", assign a wall picture 
						else if (maze[r][c] == 'M')
							cell[r][c].setIcon(WALL);

						//2.2.2.10 If the symbol is a "L", assign a wall picture 
						else if (maze[r][c] == 'L')
							cell[r][c].setIcon(WALL);

						else if (maze[r][c] == 'J')
							cell[r][c].setIcon(BLANK);
						
						//2.2.2.11 Add current cell to the panel 
						add(cell[r][c]);
					}		
					//2.2.3 Increase the row to cycle through the columns 
					r++;


				}
				//2.3 Close text file 
				input.close();

			} catch (FileNotFoundException e) {

				System.out.println("File not found");

			}
		}
		
		//Level 2
		else if (level ==2){

			enemyTimer.start();
			clockTimer.start();
			score = 0;
			time=60;
			rover.setDead(false);

			try{
				//2.1 Check the level and corresponding file to input (if statement)
				input = new Scanner (new File ("maze2.txt"));

				//2.2 Cycle through all the rows in the text file one row at a time
				while (input.hasNext()) {

					//2.2.1 Read the next line from the text file 
					maze[r] = input.nextLine().toCharArray();

					//2.2.2 For each row, cycle through all of the columns 
					for (int c = 0; c < maze[r].length; c++) {

						//2.2.2.1 Create new picture for each cell (grid)
						cell[r][c] = new  JLabel(); 

						//2.2.2.2 If the symbol is a "W", assign a wall picture
						if (maze[r][c] == 'W'){
							cell[r][c].setIcon(WALL);
						}
						//2.2.2.3 If the symbol is a "S", assign a star picture 
						else if (maze[r][c] == 'S'){
							cell[r][c].setIcon(STAR);

						}
						//2.2.2.4 If the symbol is a "D", assign a door picture 
						else if (maze[r][c] == 'D')
							cell[r][c].setIcon(DOOR);

						//2.2.2.4 If the symbol is a "B", assign a blank picture 
						else if (maze[r][c] == 'B')
							cell[r][c].setIcon(BLANK);

						//2.2.2.5 If value is from 0-9 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='0' || maze[r][c] =='1'|| maze[r][c] =='2'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(3);
						}
						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='9'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(0);
						}

						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='3'|| maze[r][c] =='4'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(1);
						}

						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='5'|| maze[r][c] =='6'|| maze[r][c] =='7'|| maze[r][c] =='8'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(2);
						}
						//2.2.2.6 If symbol is "R", assign rover picture, set row and column  
						else if (maze[r][c] == 'R') {
							cell[r][c].setIcon(rover.getIcon());
							rover.setRow(r);
							rover.setColumn(c);
							rover.setDirection(3);
						}

						//2.2.2.7 If the symbol is a "D", assign a wall picture 
						else if (maze[r][c] == 'F')
							cell[r][c].setIcon(WALL);

						//2.2.2.8 If the symbol is a "U", assign a wall picture 
						else if (maze[r][c] == 'U')
							cell[r][c].setIcon(WALL);

						//2.2.2.9 If the symbol is a "M", assign a wall picture 
						else if (maze[r][c] == 'M')
							cell[r][c].setIcon(WALL);

						//2.2.2.10 If the symbol is a "L", assign a wall picture 
						else if (maze[r][c] == 'L')
							cell[r][c].setIcon(WALL);

						//2.2.2.11 Add current cell to the panel 
						add(cell[r][c]);
					}		
					//2.2.3 Increase the row to cycle through the columns 
					r++;


				}
				//2.3 Close text file 
				input.close();

			} catch (FileNotFoundException e) {

				System.out.println("File not found");

			}
		}
		
		//Level 3
		else if (level ==3){

			enemyTimer.start();
			clockTimer.start();
			score = 0;
			time=60;
			rover.setDead(false);

			try{
				//2.1 Check the level and corresponding file to input (if statement)
				input = new Scanner (new File ("maze3.txt"));

				//2.2 Cycle through all the rows in the text file one row at a time
				while (input.hasNext()) {

					//2.2.1 Read the next line from the text file 
					maze[r] = input.nextLine().toCharArray();

					//2.2.2 For each row, cycle through all of the columns 
					for (int c = 0; c < maze[r].length; c++) {

						//2.2.2.1 Create new picture for each cell (grid)
						cell[r][c] = new  JLabel(); 

						//2.2.2.2 If the symbol is a "W", assign a wall picture
						if (maze[r][c] == 'W'){
							cell[r][c].setIcon(WALL);
						}
						//2.2.2.3 If the symbol is a "S", assign a star picture 
						else if (maze[r][c] == 'S'){
							cell[r][c].setIcon(STAR);

						}
						//2.2.2.4 If the symbol is a "D", assign a door picture 
						else if (maze[r][c] == 'D')
							cell[r][c].setIcon(DOOR);

						//2.2.2.4 If the symbol is a "B", assign a blank picture 
						else if (maze[r][c] == 'B')
							cell[r][c].setIcon(BLANK);

						//2.2.2.5 If value is from 0-9 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='0' || maze[r][c] =='2'|| maze[r][c] =='4'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(2);
						}
						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='3' || maze[r][c] =='1'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(0);
						}

						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='5'|| maze[r][c] =='7' || maze[r][c] =='9'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(1);
						}

						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='6'|| maze[r][c] =='8'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(3);
						}
						//2.2.2.6 If symbol is "R", assign rover picture, set row and column  
						else if (maze[r][c] == 'R') {
							cell[r][c].setIcon(rover.getIcon());
							rover.setRow(r);
							rover.setColumn(c);
							rover.setDirection(3);
						}

						//2.2.2.7 If the symbol is a "D", assign a wall picture 
						else if (maze[r][c] == 'F')
							cell[r][c].setIcon(WALL);

						//2.2.2.8 If the symbol is a "U", assign a wall picture 
						else if (maze[r][c] == 'U')
							cell[r][c].setIcon(WALL);

						//2.2.2.9 If the symbol is a "M", assign a wall picture 
						else if (maze[r][c] == 'M')
							cell[r][c].setIcon(WALL);

						//2.2.2.10 If the symbol is a "L", assign a wall picture 
						else if (maze[r][c] == 'L')
							cell[r][c].setIcon(WALL);

						//2.2.2.11 Add current cell to the panel 
						add(cell[r][c]);
					}		
					//2.2.3 Increase the row to cycle through the columns 
					r++;


				}
				//2.3 Close text file 
				input.close();

			} catch (FileNotFoundException e) {

				System.out.println("File not found");

			}
		}
		
		//Level 4
		else if (level ==4){

			enemyTimer.start();
			clockTimer.start();
			score = 0;
			time=60;
			rover.setDead(false);

			try{
				//2.1 Check the level and corresponding file to input (if statement)
				input = new Scanner (new File ("maze4.txt"));

				//2.2 Cycle through all the rows in the text file one row at a time
				while (input.hasNext()) {

					//2.2.1 Read the next line from the text file 
					maze[r] = input.nextLine().toCharArray();

					//2.2.2 For each row, cycle through all of the columns 
					for (int c = 0; c < maze[r].length; c++) {

						//2.2.2.1 Create new picture for each cell (grid)
						cell[r][c] = new  JLabel(); 

						//2.2.2.2 If the symbol is a "W", assign a wall picture
						if (maze[r][c] == 'W'){
							cell[r][c].setIcon(WALL);
						}
						//2.2.2.3 If the symbol is a "S", assign a star picture 
						else if (maze[r][c] == 'S'){
							cell[r][c].setIcon(STAR);

						}
						//2.2.2.4 If the symbol is a "D", assign a door picture 
						else if (maze[r][c] == 'D')
							cell[r][c].setIcon(DOOR);

						//2.2.2.4 If the symbol is a "B", assign a blank picture 
						else if (maze[r][c] == 'B')
							cell[r][c].setIcon(BLANK);

						//2.2.2.5 If value is from 0-9 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='0' || maze[r][c] =='1'|| maze[r][c] =='2'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(1);
						}
						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='3' || maze[r][c] =='4'|| maze[r][c] =='5' || maze[r][c] =='6'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(3);
						}

						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='7' || maze[r][c] =='9'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(2);
						}

						//2.2.2.5 If value is from 5 it is an ememy, assign enemy picture, set row and column  
						else if (maze[r][c] =='8'){
							int bNum = Character.getNumericValue(maze[r][c]);

							cell[r][c].setIcon(enemy[bNum].getIcon());						
							enemy[bNum].setRow(r);
							enemy[bNum].setColumn(c);
							enemy[bNum].setDirection(0);
						}
						//2.2.2.6 If symbol is "R", assign rover picture, set row and column  
						else if (maze[r][c] == 'R') {
							cell[r][c].setIcon(rover.getIcon());
							rover.setRow(r);
							rover.setColumn(c);
							rover.setDirection(3);
						}

						//2.2.2.7 If the symbol is a "D", assign a wall picture 
						else if (maze[r][c] == 'F')
							cell[r][c].setIcon(WALL);

						//2.2.2.8 If the symbol is a "U", assign a wall picture 
						else if (maze[r][c] == 'U')
							cell[r][c].setIcon(WALL);

						//2.2.2.9 If the symbol is a "M", assign a wall picture 
						else if (maze[r][c] == 'M')
							cell[r][c].setIcon(WALL);

						//2.2.2.10 If the symbol is a "L", assign a wall picture 
						else if (maze[r][c] == 'L')
							cell[r][c].setIcon(WALL);

						//2.2.2.11 Add current cell to the panel 
						add(cell[r][c]);
					}		
					//2.2.3 Increase the row to cycle through the columns 
					r++;
					
				}
				//2.3 Close text file 
				input.close();

			} catch (FileNotFoundException e) {

				System.out.println("File not found");

			}
		}
	}

	/**
	 * Handles keyboard entries - to start the game and control Rover's movements
	 */
	public void keyPressed(KeyEvent key) {

		//3.1 If Rover alive, start timer 
		if (rover.isDead()==false && enemyTimer.isRunning()==false){
			enemyTimer.start();
			clockTimer.start();
		}

		//3.2 If Rover alive and game not over 
		if (rover.isDead()==false  && score!=55 ) {

			//3.2.1 Track direction based on the key pressed
			int direction = key.getKeyCode()-37;

			//3.2.2 Set direction using (37 - left), (38 - up), (39 - right), (40 - down)
			if (direction==0 && maze[rover.getRow()][rover.getColumn()-1] != 'W')
				rover.setDirection(0);

			else if (direction==1 && maze[rover.getRow()-1][rover.getColumn()] != 'W' && maze[rover.getRow()-1][rover.getColumn()] != 'F' )
				rover.setDirection(1);

			else if (direction==2 && maze[rover.getRow()][rover.getColumn()+1] != 'W')
				rover.setDirection(2);

			else if (direction==3 && maze[rover.getRow()+1][rover.getColumn()] != 'W' && maze[rover.getRow()+1][rover.getColumn()] != 'U')
				rover.setDirection(3);

			performMove(rover);
		}
	}
	/**
	 * Allows an object to move and updates both on the maze and screen based on:
	 * the object, direction, and change in row and column
	 *
	 * @param mover either Rover or a Enemy
	 */
	private void performMove(Mover mover) {
		
		//4. Move the enemies and Rover's position 
		if (maze[mover.getNextRow()][mover.getNextColumn()] != 'W') {

			//4.1 Mover object is Rover
			if (mover == rover){

				//4.1.1. Move Rover
				rover.move();

				//4.1.2 Mark the maze at the previous to blank (removes previous image of rover)			
				//Previous row or column wasn't no wall
				if (mover.getDirection() == 0 && maze[mover.getNextRow()][mover.getNextColumn()] != 'W' )
					cell[mover.getRow()][mover.getColumn()+1].setIcon(BLANK);

				else if (mover.getDirection() == 1 && maze[mover.getNextRow()][mover.getNextColumn()] != 'W')
					cell[mover.getRow()+1][mover.getColumn()].setIcon(BLANK);

				else if (mover.getDirection() == 2 && maze[mover.getNextRow()][mover.getNextColumn()] != 'W')
					cell[mover.getRow()][mover.getColumn()-1].setIcon(BLANK);

				else if (mover.getDirection() == 3 && maze[mover.getNextRow()][mover.getNextColumn()] != 'W')
					cell[mover.getRow()-1][mover.getColumn()].setIcon(BLANK);

				//Previous row or column was a wall
				else if (mover.getDirection() == 0 && maze[mover.getNextRow()][mover.getNextColumn()] == 'W' )
					cell[mover.getRow()][mover.getColumn()+1].setIcon(BLANK);

				else if (mover.getDirection() == 1 && maze[mover.getNextRow()][mover.getNextColumn()] == 'W')
					cell[mover.getRow()+1][mover.getColumn()].setIcon(BLANK);

				else if (mover.getDirection() == 2 && maze[mover.getNextRow()][mover.getNextColumn()] == 'W')
					cell[mover.getRow()][mover.getColumn()-1].setIcon(BLANK);

				else if (mover.getDirection() == 3 && maze[mover.getNextRow()][mover.getNextColumn()] == 'W')
					cell[mover.getRow()-1][mover.getColumn()].setIcon(BLANK);

				//"U" Booster (Prevent Wall Erase)
				else if (mover.getDirection() == 1 && maze[mover.getNextRow()][mover.getNextColumn()] == 'F')
					cell[mover.getRow()+1][mover.getColumn()].setIcon(WALL);

				//"F" Booster (Prevent Wall Erase)
				else if (mover.getDirection() == 3 && maze[mover.getNextRow()][mover.getNextColumn()] == 'U')
					cell[mover.getRow()-1][mover.getColumn()].setIcon(WALL);

				//"M" Booster (Prevent Wall Erase)
				else if (mover.getDirection() == 0 && maze[mover.getNextRow()][mover.getNextColumn()] == 'M' )
					cell[mover.getRow()][mover.getColumn()+1].setIcon(WALL);

				else if (mover.getDirection() == 2 && maze[mover.getNextRow()][mover.getNextColumn()] == 'L')
					cell[mover.getRow()][mover.getColumn()-1].setIcon(WALL);

				//4.1.3 If there is any stars in the new square on the maze and the Mover is Rover then
				if (maze[rover.getRow()][rover.getColumn()]=='S'){
					//4.1.3.1. Increment the score
					score++;

					//4.1.3.2 Mark the maze at the new position to blank
					maze[rover.getRow()][rover.getColumn()]='B';

					//4.1.3.3 Mark the maze at the new position to 'eaten'
					cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon());
					
					//3.3.3 Implement collect star Sound 
					Audio.collectStar();
					
				}
				//4.1.3.4 When game is finished
				else if ((maze[mover.getRow()][mover.getColumn()] == 'D' ) && score == 55 ){

					//4.1.3.4.1 Call stop Game and nextLevel method
					stopGame();

					//4.1.3.4.2 Check and Save Highscore
					if (NextLevelScreen.level == 1){
						
						//4.1.3.4.2.1 Check if current score greater than pre-existing
						if ((score * time) > HighScore.getHighscore()){

							//4.1.3.4.2.1.1 Loop until only 3 characters entered 
							do{
								name=JOptionPane.showInputDialog("You set a new highscore! Please enter your 3 letter initials.");						
							}while (name.length()!=3);

							HighScore.writeFile();
						}
						else
							System.out.println("try again");
					}
					//4.1.3.4.3 Check and Save Highscore
					if (NextLevelScreen.level == 2){
						
						//4.1.3.4.2.3.1 Check if current score greater than pre-existing
						if ((score * time) > HighScore.getHighscore2()){
							
							//4.1.3.4.2.3.1.1 Loop until only 3 characters entered  
							do{
								name=JOptionPane.showInputDialog("You set a new highscore! Please enter your 3 letter initials.");						
							}while (name.length()!=3);

							HighScore.writeFile();
						}
						else
							System.out.println("try again");
					}
					
					//4.1.3.4.4 Check and Save Highscore
					if (NextLevelScreen.level == 3){
						
						//4.1.3.4.2.4.1 Check if current score greater than pre-existing
						if ((score * time) > HighScore.getHighscore3()){
							
							//4.1.3.4.2.4.1.1 Loop until only 3 characters entered  
							do{
								name=JOptionPane.showInputDialog("You set a new highscore! Please enter your 3 letter initials.");						
							}while (name.length()!=3);

							HighScore.writeFile();
						}
						else
							System.out.println("try again");
					}
					//4.1.3.4.5 Check and Save Highscore
					if (NextLevelScreen.level == 4){
						
						//4.1.3.4.2.5.1 Check if current score greater than pre-existing
						if ((score * time) > HighScore.getHighscore4()){
							
							//4.1.3.4.2.3.5.1 Loop until only 3 characters entered  
							do{
								name=JOptionPane.showInputDialog("You set a new highscore! Please enter your 3 letter initials.");						
							}while (name.length()!=3);

							HighScore.writeFile();
						}
						else
							System.out.println("try again");
					}
					nextLevel();
				}
				//4.1.4 Otherwise update the picture on the screen
				else
					cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon());
			}

			//4.2 Run through all the enemies
			else{

				//4.2.1 Cell where enemy is, reset to stars
				if (maze[mover.getRow()][mover.getColumn()]=='S')
					cell[mover.getRow()][mover.getColumn()].setIcon(STAR);

				//4.2.2 Otherwise reset the cell to blank
				else{
					cell[mover.getRow()][mover.getColumn()].setIcon(BLANK);
				}

				//4.2.3 Move Enemy position
				mover.move();

				//4.2.4 If collision has occured
				if (collided()) {

					//4.2.4.1 Change death to true
					rover.setDead(true);
					RoverGame.death = true;

					//4.2.4.2 Call death and checkdeath methods
					death();
					checkDeath();

				}
				//4.3 If collision hasn't occurred 
				else 
					//4.3.1 Update picture on the screen
					cell[mover.getRow()][mover.getColumn()].setIcon(mover.getIcon());	
			}
		}
	}

	/**
	 * Stop the game when Rover and a enemy 'collide'
	 */
	public void death() {

		//5.1 Set Rover as dead 
		rover.setDead(true);

		//5.2 Stop the game
		stopGame();
		
		//5.3 Set to death icon 
		cell[rover.getRow()][rover.getColumn()].setIcon(DEAD);

		//5.4 Rover Lives
		if ((RoverGame.roverLives > 0 && rover.isDead() == true) || (RoverGame.roverLives > 0 && time == 0)){

			//5.4.1 Subtract lives by 1 
			RoverGame.roverLives --;

			//5.4.2 Reset the score to 0 
			score = 0;
			
			//5.4.3 Reset the time 
			time = 60;

			//5.4.3 Reset rover and RoverGame death to false 
			rover.setDead(false);
			RoverGame.death = false;
			
			//5.4.4 Start clock timer 
			clockTimer.start();
			
			//5.4.5 Respawn in correct location for level 1
			if (NextLevelScreen.level == 1){
				rover.setRow(2);
				rover.setColumn(2);
			}
			
			//5.4.6 Respawn in correct location for level 2
			else if (NextLevelScreen.level == 2){
				rover.setRow(12);
				rover.setColumn(2);
			}
			
			//5.4.7 Respawn in correct location for level 3
			else if (NextLevelScreen.level == 3){
				rover.setRow(12);
				rover.setColumn(18);
			}

			//5.4.8 Respawn in correct location for level 4
			else if (NextLevelScreen.level == 4){
				rover.setRow(3);
				rover.setColumn(2);
			}
			
			//5.4.9 Reload the RoverGame to restart the maze
			new RoverGUI();
			
			System.out.println("Press arrow keys to reset");
		}

		//5.5 If there are no lives remaining
		else if (RoverGame.roverLives == 0 && rover.isDead() == true ){

			//5.5.1 Set Rover's death to true
			RoverGame.death = true;
			
			stopGame();
		}	
			//5.5.2 If time remaining is 0 and no lives, show endscreen 
		else if (time == 0 && RoverGame.roverLives ==0){
			stopGame();
			new EndScreen();
		}
	}

	/**
	 * Checks Game to see if Rover is dead to load play end screen
	 */
	public void checkDeath (){

		//6.1 If Rover is dead 
		if (RoverGame.roverLives==0)

			//6.1.1 Open EndScreen			
			new EndScreen();

	}

	/**
	 * Determines if Rover has collided with an Ememy
	 *
	 * @return collided
	 */
	public boolean collided () {
		//7.1 Cycle through all the enemies to verify if they have touched Rover
		for (BallEnemy e: enemy) {

			//7.1.1 If the enemy has the same row and column as Rover, return true 
			if (e.getRow()==rover.getRow() && e.getColumn()==rover.getColumn()){
				return true;
			}
		}

		//7.2 If they don't have the same row and column, return false(no touch)
		return false;
	}


	/**
	 * Stops the game timer
	 */
	public void stopGame() {
		//8.1.1 Stop the enemyTimer, clockTImer and roverTImer
		enemyTimer.stop();
		clockTimer.stop();
		roverTimer.stop();
	}

	public void moveBallEnemy() {
		//9.1 Check which level it is and choosing the way to move the enemies depending on the level. 
		
		//9.2 MoveBall Enemy for Level 1 
		if (NextLevelScreen.level == 1){		

			//9.2.1 If the level is 1, Cycle through all the enemies 
			for (int x = 0; x<=4 ; x++) {
				//9.2.1.1 For array 0-4 set direction to 1
				if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'U')
					enemy[x].setDirection(1);
				
				//9.2.1.2For array 0-4 set direction to 3
				else if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'F'){
					enemy[x].setDirection(3);

				}
				//9.2.1.3 Move the enemy 
				performMove(enemy[x]);
			}	
			
			//9.2.2. For array 5 set direction to 2
			if (maze[enemy[5].getNextRow()][enemy[5].getNextColumn()] == 'M')
				enemy[5].setDirection(2);

			//9.2.3. For array 5 set direction to 0
			else if (maze[enemy[5].getNextRow()][enemy[5].getNextColumn()] == 'L')
				enemy[5].setDirection(0);

			//9.2.4 Move the enemy 
			performMove(enemy[5]);

			//9.2.5. For array 6 set direction to 1
			if (maze[enemy[6].getNextRow()][enemy[6].getNextColumn()] == 'U')
				enemy[6].setDirection(1);

			//9.2.6. For array 6 set direction to 3
			else if (maze[enemy[6].getNextRow()][enemy[6].getNextColumn()] == 'F')
				enemy[6].setDirection(3);

			//9.2.7 Move the enemy 
			performMove(enemy[6]);

			//9.2.8 For array 7 set direction to 2
			if (maze[enemy[7].getNextRow()][enemy[7].getNextColumn()] == 'M')
				enemy[7].setDirection(2);

			//9.2.9. For array 7 set direction to 0
			else if (maze[enemy[7].getNextRow()][enemy[7].getNextColumn()] == '8')
				enemy[7].setDirection(0);
			
			//9.2.10. Move the enemy 
			performMove(enemy[7]);

			//9.2.11. For array 8 set direction to 2
			if (maze[enemy[8].getNextRow()][enemy[8].getNextColumn()] == '7')
				enemy[8].setDirection(2);

			//9.2.12. For array 6 set direction to 0
			else if (maze[enemy[8].getNextRow()][enemy[8].getNextColumn()] == 'L')
				enemy[8].setDirection(0);

			//9.2.13 Move the enemy
			performMove(enemy[8]);

			//9.2.14. For array 9 set direction to 2
			if (maze[enemy[9].getNextRow()][enemy[9].getNextColumn()] == 'M')
				enemy[9].setDirection(2);
			
			//9.2.15. For array 9 set direction to 0
			else if (maze[enemy[9].getNextRow()][enemy[9].getNextColumn()] == 'L')
				enemy[9].setDirection(0);

			//9.2.16 Move the enemy
			performMove(enemy[9]);


		}
		//9.3 MoveBall Enemy for Level 2
		else if (NextLevelScreen.level == 2){		

			//9.3.1 If the level is 2, Cycle through all the enemies 
			
			//Enemy 0-4 Array
			for (int x = 0; x<=4 ; x++) {
				
				//9.3.1.1 For array 0-4 set direction to 1
				if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'U')
					enemy[x].setDirection(1);

				//9.3.1.2 For array 0-4 set direction to 3
				else if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'F'){
					enemy[x].setDirection(3);

				}
				///9.3.1.3 For array 0-4 move the enemy
				performMove(enemy[x]);
			}	
			
			//9.3.1.4 Enemy Array 5-9
			for (int x = 5; x<=9; x++) {
				
				//9.3.1.5 For array 5-9 set direction to 2
				if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'M')
					enemy[x].setDirection(2);

				//9.3.1.6 For array 5-9 set direction to 0
				else if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'L'){
					enemy[x].setDirection(0);

				}
				//9.3.1.7 For array 5-9 move the enemy
				performMove(enemy[x]);
			}	

		}
		//9.4 MoveBall Enemy for Level 3
		else if (NextLevelScreen.level == 3){		

			//9.4.1 If the level is 3, Cycle through all the enemies 
			
			//Enemy Array 5-9
			for (int x = 5; x<=9 ; x++) {
				//9.4.1.1 For array 5-9 set direction to 1
				if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'U')
					enemy[x].setDirection(1);

				//9.4.1.2 For array 5-9 set direction to 3
				else if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'F'){
					enemy[x].setDirection(3);
				
				}
				//9.4.1.3 For array 5-9 move enemy
				performMove(enemy[x]);
			}	

			//Enemy Array 0-4
			for (int x = 0; x<=4; x++) {
				
				//9.4.1.4 For array 0-4set direction to 2
				if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'M')
					enemy[x].setDirection(2);

				//9.4.1.5 For array 0-4set direction to 0
				else if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'L'){
					enemy[x].setDirection(0);

				}
				//9.4.1.6 Move Enemy 
				performMove(enemy[x]);
			}	

		}
		
		//9.5 MoveBall Enemy for Level 4
		else if (NextLevelScreen.level == 4){		

			//9.5.1 If the level is 4, Cycle through all the enemies 
			
			//Enemy Aray 0-6
			for (int x = 0; x<=6 ; x++) {
				
				//9.5.1.1 For array 0-6 set direction to 1
				if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'U')
					enemy[x].setDirection(1);

				//9.5.1.2 For array 0-6 set direction to 3
				else if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'F'){
					enemy[x].setDirection(3);

				}
				//9.5.1.3 For array 0-6 move enemy
				performMove(enemy[x]);
			}	

			//Enemy Aray 7-9
			for (int x = 7; x<=9; x++) {
				
				//9.5.1.4 For array 7-9 set direction to 2
				if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'M')
					enemy[x].setDirection(2);

				//9.5.1.5 For array 7-9 set direction to 0
				else if (maze[enemy[x].getNextRow()][enemy[x].getNextColumn()] == 'L'){
					enemy[x].setDirection(0);

				}
				//9.5.1.6 For array 7-9 move enemy
				performMove(enemy[x]);
			}	

		}

	}

	/**
	 * Determines the source of the action as either the game timer, clock timer, rover and then performs the corresponding actions	 
	 */
	public void actionPerformed(ActionEvent e) {

		//10.1 If action is user Timer 
		if (e.getSource() == roverTimer)
			//10.1.1 Call the perform move method of Rover
			performMove(rover);

		//10.1.2 Display the highscore, lives and the current score
		RoverGUI.scoreLabel.setText("Score: " + (score * time));

		RoverGUI.livesLabel.setText("Lives: " + RoverGame.roverLives);

		if (NextLevelScreen.level == 1)
			RoverGUI.highScoreLabel.setText("Highscore: " + HighScore.getHighscore() + " by " + HighScore.getname());

		else if (NextLevelScreen.level == 2)
			RoverGUI.highScoreLabel.setText("Highscore: " + HighScore.getHighscore2() + " by " + HighScore.getname2());

		else if (NextLevelScreen.level == 3)
			RoverGUI.highScoreLabel.setText("Highscore: " + HighScore.getHighscore3() + " by " + HighScore.getname3());

		else if (NextLevelScreen.level == 4)
			RoverGUI.highScoreLabel.setText("Highscore: " + HighScore.getHighscore4() + " by " + HighScore.getname4());

		//10.2 If action is the enemyTimer
		if (e.getSource()== enemyTimer){

			//10.2.1 Call the moveBallEmemy method
			moveBallEnemy();
		}

		//10.2 If action is the clockTimer
		if (e.getSource() == clockTimer){

			//10.3 Change the time every second
			RoverGUI.timeLabel.setText("Time:" + time--);
			
			//10.4 If there is no time remaing
			if (time ==0){
				
				//10.5 Call death method 
				death();
				
				//10.6 Stop the clock from ticking 
				clockTimer.stop();
			}
		}
	}

	/**
	 * Determines if player is ready to move onto the next level 
	 */
	public void nextLevel () {
		//12.1 Rover is on the door icon and has a score of 55 (collected all stars)
		if ((maze[rover.getRow()][rover.getColumn()] == 'D') && score ==55)
			
			//12.1.1 Open Next Level Screen Class
			new NextLevelScreen();

	}

	public void keyReleased(KeyEvent arg0) {
		//Not Used		
	}

	public void keyTyped(KeyEvent arg0) {
		//Not Used		
	}
}
