package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates an enemy that simply bounces around the initial game
 * frame at a chosen speed. 
 * @author Navin Ramsaroop
 *
 */
public class BasicEnemy extends GameObject{
	Random r = new Random();

	/**
	 * 
	 * @param x X coordinate of basic enemy
	 * @param y Y coordinate of basic enemy
	 * @param id Identifier of the Enemy ("Basic Enemy")
	 * @param vX Velocity in the X direction
	 * @param vY Velocity in the Y direction
	 */
	public BasicEnemy(int x, int y, ID id, int vX, int vY) {
		super(x, y, id);
				
		this.vX = vX;
		this.vY = vY;
	}

	@Override
	/**
	 * Updates Enemy location
	 */
	public void tick() {
		
		x += vX;
		y += vY;
		
		if( y<=0 || y >= Game.HEIGHT - 48) vY = vY * -1;
		if( x<=0 || x >= Game.WIDTH - 32) vX = vX * -1;
		
	}

	@Override
	/**
	 * Displays enemy on screen
	 */
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 15, 15);
	}

	@Override
	/**
	 * Returns a rectangle at the location and with 
	 * the size of the enemy
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
		
	}

	
}
