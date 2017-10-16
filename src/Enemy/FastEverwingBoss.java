package Enemy;
import System.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a spawn of basic health enemies
 *  that all lie on top of each other, moving from the 
 *  left side of the screen to the right.
 * @author Navin Ramsaroop
 *
 */
public class FastEverwingBoss extends GameObject{
	Random r = new Random();
	Handler handler;
	public int bossHealth = 15;
	
	/**
	 * 
	 * @param x X coordinate of where the spawn should start
	 * @param y Y coordinate of the top most enemy in the spawn
	 * @param id Enemy Identifier
	 * @param handler Handler from the main class
	 */
	public FastEverwingBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
			this.handler = handler;
			
		vX = 7;
		vY = 0;
	}

	@Override
	public void tick() {
		
		x += vX;
		
		if(x > Game.WIDTH) {
			handler.removeObject(this);
		}
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Bullet){
				if(handler.object.get(i).getBounds().intersects(this.getBounds())){
					bossHealth -= handler.getCurrentPlayer().getBulletSize();
					handler.removeObject(handler.object.get(i));
					if(bossHealth <= 0) {
						handler.removeObject(this);
						Health.COINS += 5;
					}
				}
			}
			
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 15, 15);
		
		g.setColor(Color.green); 
		g.fillRect(x, y - 10, bossHealth, 5);
		
		g.setColor(Color.red);
		g.fillRect(x + bossHealth, y - 10, 15 - bossHealth, 5);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
		
	}
	public int getHealth() {
		return bossHealth;
		
	}
	
}
