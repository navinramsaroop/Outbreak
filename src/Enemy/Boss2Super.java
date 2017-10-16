package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Bigger version of Boss2, that tracks user around
 * the screen. After enemy is killed, four Boss2 Objects
 * appear.
 * @author Navin Ramsaroop
 *
 */
public class Boss2Super extends GameObject{
	Random r = new Random();
	Handler handler;
	public int superBossHealth = 200;
	int resultantV;
	
	/**
	 *  
	 * @param x X coordinate
	 * @param y Y coordinate 
	 * @param id Enemy Identifier
	 * @param handler Handler from main class
	 * @param resultantV Velocity of enemy
	 */
	public Boss2Super(int x, int y, ID id, Handler handler, int resultantV) {
		super(x, y, id);
		
		this.handler = handler;
		this.resultantV = resultantV;
	}

	/**
	 * Updates Player Location
	 */
	public void tick() {
		double tempY = handler.getCurrentPlayer().getY() - y;
		double tempX = handler.getCurrentPlayer().getX() - x;
		double theta;
		
		theta = Math.atan(Math.abs(tempX/tempY));
		
		vX = (int)(resultantV * Math.sin(theta));
		vY = (int)(resultantV * Math.cos(theta));
		
		if(tempY > 0) {
			vY = Math.abs(vY);
		}
		if(tempY < 0) {
			vY = - Math.abs(vY);
		}
		if(tempX > 0) {
			vX = Math.abs(vX);
		}
		if(tempX < 0){
			vX = - Math.abs(vX);
		}
		
		x += vX;	
		y += vY;

		
		for (int i = 0; i < handler.object.size(); i++) {
			if( handler.object.get(i).getID() == ID.Bullet){
				if( handler.object.get(i).getBounds().intersects(this.getBounds())){
					superBossHealth -= handler.getCurrentPlayer().getBulletSize();
					handler.removeObject( handler.object.get(i));
					if(superBossHealth < 0) {
						if(superBossHealth <= 0) {
							handler.removeObject(this);
							Health.COINS += 50;
							handler.addObject(new Boss2(x, y, ID.BasicEnemyHealth, handler,3));
							handler.addObject(new Boss2(x, y, ID.BasicEnemyHealth, handler,4));
							handler.addObject(new Boss2(x, y, ID.BasicEnemyHealth, handler,5));
							handler.addObject(new Boss2(x, y, ID.BasicEnemyHealth, handler,6));
						}
					}
				}
			}
			
		}
		
	}
	/**
	 * 
	 * @return Health of the boss (int)
	 */
	public int getHp() {
		return superBossHealth;
	}
	
	/**
	 * Resets HP to initial amount, 200
	 */
	public void setHp() {
		superBossHealth = 200;
	}
	/**
	 * Shows Enemy on the screen
	 */
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 100, 100);
		
		g.setColor(Color.gray);
		g.fillRect(x, y - 10, 100, 5);
		
		g.setColor(Color.red);
		g.fillRect(x, y - 10, superBossHealth/2, 5);
	}

	/**
	 * @return Rectangle of the enemy
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 100, 100);
	}

	
}
