package Player;
import System.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a bullet that the user shoots. 
 * @author Navin Ramsaroop
 *
 */
public class Bullet extends GameObject {
	Handler handler;
	Random r = new Random();
	public  String direction = "";
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler from the main class 
	 * @param direction Direction of the bullet
	 */
	public Bullet(int x, int y, ID id, Handler handler, String direction) {
		super(x, y, id);
		this.direction = direction;
		this.handler = handler;

		if (direction.equals("Left")) {
			vX = -10;
			vY = 0;
		}
		if (direction.equals("Right")) {
			vX = 10;
			vY = 0;
		}
		if (direction.equals("Up")) {
			vY = -10;
			vX = 0;
		}
		if (direction.equals("Down")) {
			vY = 10;
			vX = 0;
		}
			handler.getCurrentPlayer().setReload(0);
	}
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler from the main class 
	 * @param vX Velocity in the X direction
	 * @param vY Velocity in the Y direction
	 */
	public Bullet(int x, int y, ID id, Handler handler, int vX, int vY) {
		super(x, y, id);
		this.handler = handler;

		this.vX = vX;
		this.vY = vY;
		
		handler.getCurrentPlayer().setReload(0);
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
		g.fillOval(x, y + 12, handler.getCurrentPlayer().getBulletSize(), handler.getCurrentPlayer().getBulletSize());


	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y + 12, handler.getCurrentPlayer().getBulletSize(), handler.getCurrentPlayer().getBulletSize());
	}
}
