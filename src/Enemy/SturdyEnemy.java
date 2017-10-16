package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * More powerful enemy with high health. 
 * @author Navin Ramsaroop
 *
 */
public class SturdyEnemy extends GameObject{
	Random r = new Random();
	Handler handler;
	public int bossHealth = 200;
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler from the main class
	 * @param vX Velocity in the X direction
	 * @param vY Velocity in the Y direction
	 */
	public SturdyEnemy(int x, int y, ID id, Handler handler, int vX, int vY) {
		super(x, y, id);
			this.handler = handler;
			
		this.vX = vX;
		this.vY = vY;
	}

	@Override
	public void tick() {
		
		x += vX;
		y += vY;
		
		if( y<=0 || y >= Game.HEIGHT - 48) vY = vY * -1;
		if( x<=0 || x >= Game.WIDTH - 32) vX = vX * -1;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Bullet){
				if(handler.object.get(i).getBounds().intersects(this.getBounds())){
					handler.removeObject(handler.object.get(i));
					bossHealth -= handler.getCurrentPlayer().getBulletSize();
					if(bossHealth <= 0) {
						handler.removeObject(this);
						Health.COINS += 100;
					}
				}
			}
			
		}
	}
	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 50, 50);
		
		g.setColor(Color.red);
		g.drawRect(x, y, 50, 50);
		
		g.setColor(Color.green); 
		g.fillRect(x, y - 10, bossHealth/4, 7);
		
		g.setColor(Color.red);
		g.fillRect(x + bossHealth/4, y - 10, 50 - bossHealth/4, 7);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
		
	}
	public int getHealth() {
		return bossHealth;
		
	}
	
}
