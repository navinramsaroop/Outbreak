package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;


/**
 * Creates an enemy that tracks the player around the screen.
 * @author Navin  Ramsaroop
 *
 */
public class Boss2 extends GameObject{
	Random r = new Random();
	Handler handler;
	public int bossHealth = 50;
	public int tempVelocityX;
	int resultantV;
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler from main class
	 * @param resultantV Velocity of the Enemy
	 */
	public Boss2(int x, int y, ID id, Handler handler, int resultantV) {		
		super(x, y, id);
		
		this.handler = handler;
		this.resultantV = resultantV;
	}

	/**
	 * Update enemy location
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
			if(handler.object.get(i).getID() == ID.Bullet){
				if(handler.object.get(i).getBounds().intersects(this.getBounds())){
					bossHealth -= handler.getCurrentPlayer().getBulletSize();
					handler.removeObject(handler.object.get(i));
					if(bossHealth <= 0) {
						handler.removeObject(this);
						Health.COINS += 25;
						handler.addObject(new BasicEnemyHealth(x, y, ID.BasicEnemyHealth, handler, 6, 6));
						handler.addObject(new BasicEnemyHealth(x, y, ID.BasicEnemyHealth, handler, 6,-6));
						handler.addObject(new BasicEnemyHealth(x, y, ID.BasicEnemyHealth, handler, -6, 6));
						handler.addObject(new BasicEnemyHealth(x, y, ID.BasicEnemyHealth, handler, -6, -6));
					}
				}
			}
			
		}
		
	}

	/**
	 * Show enemy on screen
	 */
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 50, 50);
		
		g.setColor(Color.white);
		g.drawRect(x, y, 50, 50);
		
		g.setColor(Color.gray);
		g.fillRect(x, y - 10, 50, 2);
		
		g.setColor(Color.red);
		g.fillRect(x, y - 10, bossHealth, 2);
	}

	/**
	 * @return Rectangle of the enemy
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
	}

	
}
