package System;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

/**
 * Menu Screen
 * @author Navin Ramsaroop
 *
 */
public class Menu {
	Handler handler;
	Random r = new Random();
	
	/**
	 * Creates Menu Screen
	 * @param handler
	 */
	public Menu (Handler handler) {
		this.handler = handler;
		
		for(int i = 0; i < 45; i++) {
			handler.addObject(new MenuEnemy(Game.WIDTH/2, Game.HEIGHT/2, ID.MenuEnemy));
		}
	}

	/**
	 * Displays menu on screen
	 * @param g
	 */
	public void render(Graphics g) {	
	
		g.setFont(new Font("Times New Roman", Font.BOLD,  35));
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 100, 200, 50);
		g.setColor(Color.white);
		g.drawString("Play",Game.WIDTH/2 - 60,Game.HEIGHT/2 - 50);
		
		g.setFont(new Font("Times New Roman", Font.BOLD,  35));
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2 - 50, 200, 50);
		g.setColor(Color.white);
		g.drawString("Instructions",Game.WIDTH/2 - 90, Game.HEIGHT/2);
		
		g.setFont(new Font("Times New Roman", Font.BOLD,  35));
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2 - 100, Game.HEIGHT/2, 200, 50);
		g.setColor(Color.white);
		g.drawString("Quit",Game.WIDTH/2 - 75 ,Game.HEIGHT/2 + 50);
		
	}
}
