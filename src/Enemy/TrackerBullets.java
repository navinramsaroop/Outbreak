package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a bullet object used by an enemy to to shoot
 *  the player on the screen. 
 * @author navinr
 *
 */
public class TrackerBullets extends GameObject {
	Handler handler;
	Random r = new Random();
	public  String direction = "";
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier 
	 * @param handler Handler from the main class
	 * @param direction Direction the bullet is going in (Left, Right, Up, Down). Is case sensitive. 
	 */
	public TrackerBullets(int x, int y, ID id, Handler handler, String direction) {
		super(x, y, id);
		this.direction = direction;
		this.handler = handler;

		if (direction.equals("Left")) {
			vX = -14;
			vY = 0;
		}
		if (direction.equals("Right")) {
			vX = 14;
			vY = 0;
		}
		if (direction.equals("Up")) {
			vY = -14;
			vX = 0;
		}
		if (direction.equals("Down")) {
			vY = 14;
			vX = 0;
		}
	}

	public void tick() {
		x += vX;
		y += vY;
		if (x < 0 || x > Game.WIDTH || y > Game.HEIGHT || y < 0) {
			handler.removeObject(this);
		}
	}

	public void render(Graphics g) {

		g.setColor(Color.WHITE);
		g.fillOval(x, y + 6, 5, 5);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 5, 5);
	}
}
