package System;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Displays end screen (used after the user loses or 
 * quits)
 * @author navinr
 *
 */
public class End {
	
	
	public void render(Graphics g) {
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString("Score: " + Integer.toString(Health.SCORE),  Game.WIDTH/2  + 25, Game.HEIGHT / 2 - 75);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString("Level: " + Integer.toString(Health.level),  Game.WIDTH/2 - 250, Game.HEIGHT / 2 - 75);
		
		g.setFont(new Font("Times New Roman", Font.BOLD, 50));
		g.setColor(Color.white);
		g.drawString("GAME OVER", Game.WIDTH/2 - 120, Game.HEIGHT / 2 - 150);
		
		
		g.setFont(new Font("Times New Roman", Font.BOLD,  35));
		g.setColor(Color.white);
		g.drawRect(225, 200, 200, 50);
		g.setColor(Color.white);
		g.drawString("Try Again?", 240 ,235);
		
		g.setFont(new Font("Times New Roman", Font.BOLD,  35));
		g.setColor(Color.white);
		g.drawRect(225, 250, 200, 50);
		g.setColor(Color.white);
		g.drawString("Menu", 290 ,285);
		
	}
}
