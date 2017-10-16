package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a moving block similar to Boss1, but moves up and down
 * @author navinr
 *
 */
public class Boss1Super extends GameObject{
	Random r = new Random();
	Handler handler;
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler from the main class
	 */
	public Boss1Super(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		vX = 5;
		if(r.nextInt(2) == 1) {
			vY = -5;
		}
		else {
			vY = 5;
		}
	}

	@Override
	/**
	 * Updates Enemy Location
	 */
	public void tick() {

		x += vX;
		y += vY;
		if(y < 0 || y > Game.HEIGHT) {
			vY = -vY;
		}
		if( x >= Game.WIDTH ) {
			handler.removeObject(this);
		}
		
	}

	/**
	 * Shows enemy on the screen
	 */
	public void render(Graphics g) {
		g.setColor(new Color(150, 0, 0));
		g.fillRect(x, y, 50, Game.HEIGHT - 100);
		
	}

	/**
	 * @return Rectangle at the location of the enemy with the same width and height 
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, Game.HEIGHT - 100);
	}

	
}
