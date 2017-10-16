package System;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Enemies that appear on menu screen
 * @author Navin Ramsaroop
 *
 */
public class MenuEnemy extends GameObject{
	Random r = new Random();
	Color color; 
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id MenuEnemy identifier
	 */
	public MenuEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		
		vX = r.nextInt(15) - 10;
		vY = r.nextInt(15) - 10;
	}

	@Override
	public void tick() {
		x += vX;
		y += vY;
		
		if( y<=0 || y >= Game.HEIGHT - 48) vY = vY * -1;
		if( x<=0 || x >= Game.WIDTH - 32) vX = vX * -1;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, 15, 15);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
		
	}

	
}
