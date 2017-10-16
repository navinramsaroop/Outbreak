package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates bullets that the enemy shoots 
 * @author Navin Ramsaroop
 *
 */
public class Boss3Bullets extends GameObject {
	Handler handler;
	Random r = new Random();

	/**
	 * 
	 * @param x
	 * @param y
	 * @param id
	 * @param handler
	 * @param bulletVelocityX
	 * @param bulletVelocityY
	 */
	public Boss3Bullets(int x, int y, ID id, Handler handler, int bulletVelocityX, int bulletVelocityY) {
		super(x, y, id);

		this.handler = handler;
		vX = bulletVelocityX;
		vY = bulletVelocityY;
		
	}
	
	/**
	 * Update bullet location
	 */
	public void tick() {
		x += vX;
		y += vY;
		if (x > Game.WIDTH || x < 0 ) {
			handler.removeObject(this);
		}
		if (y > Game.HEIGHT || y < 0 ) {
			handler.removeObject(this);
		}
	}
	
	
	/**
	 * Show bullet on screen
	 */
	public void render(Graphics g) {

		g.setColor(Color.red);
		g.fillOval(x, y, 7, 7);

		g.setColor(Color.white);
		
		
	}
	/**
	 * @return Rectangle of the bullet
	 */
	@Override
	public Rectangle getBounds() {	
		return new Rectangle(x, y, 7, 7);
	}
}
