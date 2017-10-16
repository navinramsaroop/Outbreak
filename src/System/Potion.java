package System;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a potion that can heal user health
 * @author Navin Ramsaroop
 *
 */
public class Potion extends GameObject{
	Handler handler; 
	Random r = new Random();
	public Potion(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		vX = r.nextInt(6) - 10;
		vY = r.nextInt(6) - 5;
	}
	
	public void tick() {
		x += vX;
		y += vY;
		if( x > Game.WIDTH || y > Game.HEIGHT || x < 0 || y < 0) {
			handler.removeObject(this);
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillOval(x, y,  30, 30);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,5,5);
	}
}
