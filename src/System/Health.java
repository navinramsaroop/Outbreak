package System;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Health {
	

	public static int level = 0;
	public static int SCORE = 0;
	public static int COINS = 0;
	private Handler handler;
	private Font mediumFont = new Font("Times New Roman", Font.BOLD, 20);
	
	/**
	 * Sets up display containing health, level, score, number of coins, and current level.
	 * @param handler 
	 */
	public Health(Handler handler) {
		this.handler = handler;
	}
	
	/**
	 * Update Score
	 */
	public void tick() {
		handler.getCurrentPlayer().HEALTH = Game.clamp(handler.getCurrentPlayer().HEALTH, 0, handler.getCurrentPlayer().maxHealth);
			SCORE++;
	}
	
	/**
	 * Show display
	 * @param g
	 */
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.drawString("Level: " + level, 0, Game.HEIGHT - 200);
		g.drawString("~NavinRamsaroop~", 0, Game.HEIGHT - 150);

		g.setColor(Color.GREEN);
		g.fillRect(0 , 0, handler.getCurrentPlayer().HEALTH, 25);
		
		g.setColor(Color.RED);
		g.fillRect(handler.getCurrentPlayer().HEALTH, 0, handler.getCurrentPlayer().maxHealth - handler.getCurrentPlayer().HEALTH, 25);
		
		g.setColor(Color.RED);
		g.setFont(mediumFont);
		g.drawString("HEALTH", 0, 15);
		
		g.setColor(Color.white);
		g.drawString("Score: " + SCORE, 0, Game.HEIGHT - 100);
		
		g.setColor(Color.white);
		g.drawString("Coins: " + COINS, 0, Game.HEIGHT - 50);
		
	}
	
	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return SCORE;
	}
	
	/**
	 * 
	 * @return current level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Set the current level
	 * @param level
	 */
	public void setLevel(int num) {
		level = num;
	}
}
