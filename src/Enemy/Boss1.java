package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a large block enemy that moves along the screen
 * @author Navin Ramsaroop
 *
 */
public class Boss1 extends GameObject{
	Random r = new Random();
	Handler handler;
	
	/**
	 * 
	 * @param x X coordinate of enemy
	 * @param y Y coordinate of Enemy
	 * @param ID Enemy Identifier
	 * @param handler Handler from Main Class
	 * @param vX Velocity in the X direction
	 * @param vY Velocity in the Y direction
	 */
	public Boss1(int x, int y, ID id, Handler handler, int vX, int vY) {
		super(x, y, id);
		
		this.handler = handler;
		
		this.vX = vX; 
		this.vY = vY;
	}

	@Override
	/**
	 * Updates Enemy Location
	 */
	public void tick() {
		
		x += vX;

		if( x >= Game.WIDTH ) {
			handler.removeObject(this);
		}
		
	}

	/**
	 * Displays enemy on the screen
	 */
	public void render(Graphics g) {
		g.setColor(new Color(150, 0, 0));
		g.fillRect(x, y, 50, Game.HEIGHT - 100);
		
	}

	@Override
	/**
	 * @return Rectangle representing the enemy location, width, height
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, Game.HEIGHT - 100);
	}

	
}
