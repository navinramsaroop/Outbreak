package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Basic tracker enemy that follows the user around the screen. Cannot be 
 * shot by the player, because it lacks a health bar. 
 * @author Navin Ramsaroop
 *
 */
public class TrackerEnemy extends GameObject{
	Random r = new Random();
	Handler handler;
	double resultantV;

	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler from the main class
	 * @param resultantV Resultant velocity of the bullet
	 */
	public TrackerEnemy(int x, int y, ID id, Handler handler, double resultantV) {
		super(x, y, id);
		
		this.handler = handler;
		this.resultantV = resultantV;
	}

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
		
//		for (int i = 0; i < handler.object.size(); i++) {
//			if(handler.object.get(i).getID() == ID.Bullet){
//				if(handler.object.get(i).getBounds().intersects(this.getBounds())){
//					bossHealth -=5;
//					handler.removeObject(handler.object.get(i));
//					if(bossHealth <= 0) {
//						handler.removeObject(this);
//						Health.COINS += 10;
//					}
//				}
//			}
//			
//		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 50, 50);
		
		g.setColor(Color.white);
		g.drawRect(x, y, 50, 50);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
	}

	
}
