package System;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * In game store, used to restore ammunition
 * @author Navin Ramsaroop
 *
 */
public class InGameStore {
	private Font mediumFont = new Font("Times New Roman", Font.BOLD, 25);
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(mediumFont);
		
		g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 100, 200, 50);
		g.drawString("Restore Amunition", Game.WIDTH/2 - 100, Game.HEIGHT/2 - 75);
		
		
	}
}
