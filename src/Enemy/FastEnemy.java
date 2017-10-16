package Enemy;
import System.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


/**
 * Out-dated enemy. This enemy is the same as the BasicEnemy, except
 * is blue. This was created before the programmer could choose what velocity 
 * the enemy could be.
 * @author Navin Ramsaroop
 *
 */
public class FastEnemy extends GameObject{
	Random r = new Random();
	public FastEnemy(int xTemp, int yTemp, ID idTemp, int vX, int vY) {
		super(xTemp, yTemp, idTemp);
				
		this.vX = vX;
		this.vY = vY;
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
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 15, 15);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
	}

	
}
