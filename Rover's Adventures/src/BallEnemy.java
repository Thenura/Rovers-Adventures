import javax.swing.ImageIcon;

/**
 * This class is used to create BallEnemy objects.
 * It includes a constant ImageIcon  to hold the enemy picture
 * and a constructor method that sets the Enemy image
 *
 * @author Thenura Jayasinghe
 */
public class BallEnemy extends Mover {
	
	/**
	 * Constant for ImageIcons representing enemies
	 */
	public static final ImageIcon IMAGE = new ImageIcon("new images/enemy.png");
			
	
	/**
	 * Enemy constructor
	 *
	 * @param bNum BallEnemy number - 0, 1, 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 
	 */
	public BallEnemy() {
		
		//BallEnemy constructor to set ImageIcon depending on enemy number 
		this.setIcon(IMAGE);
		
	}

}
