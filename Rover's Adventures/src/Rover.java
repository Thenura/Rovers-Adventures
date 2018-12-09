import javax.swing.ImageIcon;

public class Rover extends Mover {
	
	/**
	 * constant for ImageIcons representing rover
	 */
	public static final ImageIcon IMAGE = new ImageIcon("new images/RoverPic.png");
	
	
	public Rover() {
		
		//Rover constructor to set ImageIcon
		this.setIcon(IMAGE);
		
	}

}
