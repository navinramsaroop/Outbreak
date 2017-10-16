package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyDeviceSpawner extends GameObject{
	Random r = new Random();
	Handler handler;
	int SpawnerHealth = 250;
	boolean death = false;
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler from the main class
	 */
	public EnemyDeviceSpawner(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		vX = 0;
		vY = 0;
	}
	public void tick() {
		
		if(Health.SCORE % 600 == 0) {
			handler.addObject(new TrackerBulletEnemy(x, y, ID.TrackerBulletEnemy, handler, r.nextInt(4) + 2));
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
