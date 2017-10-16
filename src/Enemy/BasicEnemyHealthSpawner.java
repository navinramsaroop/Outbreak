package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a enemy that adds a new basic enemy that has a health bar every 
 * time the final increments by 50. This spawner is actually an enemy
 * on the screen that can be killed by player bullets.
 * @author navinr
 *
 */
public class BasicEnemyHealthSpawner extends GameObject{
	Random r = new Random();
	Handler handler;
	int SpawnerHealth = 200;
	boolean death = false;
	int enemyVx;
	int enemyVy;
	
	/**
	 *  
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Spawner Identifier
	 * @param handler Handler from the main class
	 * @param enemyVx X Velocity of the newly created enemies
	 * @param enemyVy Y Velocity of the newly created enemies
	 */
	public BasicEnemyHealthSpawner(int x, int y, ID id, Handler handler, int enemyVx, int enemyVy) {
		super(x, y, id);
		this.handler = handler;
		
		this.enemyVx = enemyVx;
		this.enemyVy = enemyVy;
		
		vX = 0;
		vY = 0;
	}

	@Override
	public void tick() {
		if(Health.SCORE % 50 == 0) {
			handler.addObject(new BasicEnemyHealth(x, y, ID.BasicEnemyHealth, handler,enemyVx, enemyVy));
		}
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Bullet){
				if(handler.object.get(i).getBounds().intersects(this.getBounds())){
					SpawnerHealth -= handler.getCurrentPlayer().getBulletSize();
					handler.removeObject(handler.object.get(i));
					if(SpawnerHealth < 0) {
						death = true;
						handler.removeObject(this);
						Health.COINS += 50;
					}
				}
			}
			
		}
		
	}
	 public boolean getDeath() {
		 return death;
	 }
	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x , y, SpawnerHealth/5, 50);
		
		g.setColor(Color.RED);
		g.fillRect(x + SpawnerHealth/5, y, 50 - SpawnerHealth/5, 50);
		
		
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
		
	}
	public int getHealth() {
		return SpawnerHealth;
	}
	
}
