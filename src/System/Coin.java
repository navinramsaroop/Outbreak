package System;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a coin instance worth 1 unit.
 * @author navinr
 *
 */
public class Coin extends GameObject{
	Handler handler; 
	Random r = new Random();
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Coin Identifier
	 * @param handler
	 */
	public Coin(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		vX = 5;
		vY = 5;
	}
	
	public void tick() {
		x += vX;
		y += vY;
		

		if( y<=0 || y >= Game.HEIGHT - 48) vY = vY * -1;
		if( x<=0 || x >= Game.WIDTH - 32) vX = vX * -1;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y, 20, 20);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,20);
	}
}
