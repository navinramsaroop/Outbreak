package System;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Skins {
	private Image[] img = new Image[FrameGame.TOTALIMAGES];
	private Image imageFinal = null;
	public Skins() {
		try {
			
			img[0] = ImageIO.read(getClass().getResource("Whale.png"));
			img[1] = ImageIO.read(getClass().getResource("Popsicle.png"));
			img[2] = ImageIO.read(getClass().getResource("GradientPurpleRed.png"));
			img[3] = ImageIO.read(getClass().getResource("BlueHexagons.png"));
			img[4] = ImageIO.read(getClass().getResource("CheckeredPattern.png"));
			img[5] = ImageIO.read(getClass().getResource("RedLasers.png"));
			img[6] = ImageIO.read(getClass().getResource("Pacman.png"));
			img[7] = ImageIO.read(getClass().getResource("Mushroom.png"));
			img[8] = ImageIO.read(getClass().getResource("Cupcake.png"));
			img[9] = ImageIO.read(getClass().getResource("PacManMonster.png"));


		} catch (IOException e) {
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getStackTrace());
		}		
	}

	public Image getPicture(int skinPlayer) {
		imageFinal = img[skinPlayer];
		return imageFinal;
	}
	

}
