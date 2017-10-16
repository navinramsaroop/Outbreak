package System;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Pause Screen
 * @author Navin Ramsaroop
 *
 */
public class PauseScreen {

	public void render(Graphics g) {

		g.setColor(Color.blue);
		g.drawString("PAUSED",100 - 10 + 225,130);
		
	}
}
