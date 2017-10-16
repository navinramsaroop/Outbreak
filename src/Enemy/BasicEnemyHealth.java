package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a new enemy that bounces around the screen at a 
 * chosen velocity, but with a health bar that will decrease
 * as the user hits it with a bullet. When the health goes 
 * to zero, the enemy is removed from the screen.
 * @author Navin Ramsaroop
 *
 */
public class BasicEnemyHealth extends GameObject{
	Random r = new Random();
	Handler handler;
	public int bossHealth = 10;
	public boolean death = false;
	
	/**
	 * 
	 * @param x X coordinate of enemy
	 * @param y Y coordinate of enemy
	 * @param id Enemy Identifier
	 * @param handler Object Handler from main class
	 * @param vX Velocity in the X direction
	 * @param vY Velocity in the Y direction
	 */
	public BasicEnemyHealth(int x, int y, ID id, Handler handler, int vX, int vY) {
		super(x, y, id);
			this.handler = handler;
			
		this.vX = vX;
		this.vY = vY;
	}

	@Override
	/**
	 * Updates the Enemy Location
	 */
	public void tick() {
		
		x += vX;
		y += vY;
		
		if( y<=0 || y >= Game.HEIGHT - 48) vY = vY * -1;
		if( x<=0 || x >= Game.WIDTH - 32) vX = vX * -1;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Bullet){
				if(handler.object.get(i).getBounds().intersects(this.getBounds())){
					bossHealth -= handler.getCurrentPlayer().getBulletSize();
					handler.removeObject(handler.object.get(i));
					if(bossHealth <= 0) {
						handler.removeObject(this);
						death = true;
						Health.COINS += 10;
					}
				}
			}
			
		}
	}

	/**
	 * Displays the enemy on the screen
	 */
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 20, 20);
		
		g.setColor(Color.green); 
		g.fillRect(x, y - 10, 2 * bossHealth, 5);
		
		g.setColor(Color.red);
		g.fillRect(x + 2 * bossHealth, y - 10, 20 - 2 *bossHealth, 5);
	}

	/**
	 * @return Rectangle that is in the same (x,y) coordinate of 
	 * the enemy with the same width and height
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
		
	}
	/**
	 * 
	 * @return health left of the enemy
	 */
	public int getHealth() {
		return bossHealth;
	}
	
}
