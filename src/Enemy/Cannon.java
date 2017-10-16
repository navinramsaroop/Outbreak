package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * Creates an enemy that shoots bullets at the player
 * around the screen. This enemy does not move in any
 * direction. 
 * @author Navin Ramsaroop
 *
 */
public class Cannon extends GameObject{
	private Handler handler;
	private int resultantV;
	private int tempBulletX;
	private int tempBulletY;
	private int bossHealth = 250;

	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Enemy Identifier
	 * @param handler Handler in the main class
	 * @param resultantV Resultant velocity of the bullets
	 */
	public Cannon(int x, int y, ID id, Handler handler, int resultantV) {
		super(x, y, id);
				
		this.handler = handler;
		
		vX = 0;
		vY = 0;
		
		this.resultantV = resultantV;
		
	}

	@Override
	public void tick() {
		if(Health.SCORE % 15 == 0) {
			double tempY = handler.getCurrentPlayer().getY()- y;
			double tempX = handler.getCurrentPlayer().getX() - x;
			double theta;
			
			theta = Math.atan(Math.abs(tempX/tempY));
			
			tempBulletX = (int)(resultantV * Math.sin(theta));
			tempBulletY = (int)(resultantV * Math.cos(theta));
			
			
			
			if(tempY > 0) {
				tempBulletY = Math.abs(tempBulletY);
			}
			if(tempY < 0) {
				tempBulletY = - Math.abs(tempBulletY);
			}
			if(tempX > 0) {
				tempBulletX = Math.abs(tempBulletX);
			}
			if(tempX < 0){
				tempBulletX = - Math.abs(tempBulletX);
			}
					
			if(tempBulletX == 0) {
				handler.addObject(new Boss3Bullets(handler.getCurrentPlayer().getX(), y , ID.Boss3Bullets, handler, tempBulletX, tempBulletY));	
			}
			else if(tempBulletY == 0) {
				handler.addObject(new Boss3Bullets(x, handler.getCurrentPlayer().getY() , ID.Boss3Bullets, handler, tempBulletX, tempBulletY));
			}
			else {
				handler.addObject(new Boss3Bullets(x, y , ID.Boss3Bullets, handler, tempBulletX, tempBulletY));	
			}
			
				
			
		}
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Bullet){
				GameObject go = handler.object.get(i);
				if(go.getBounds().intersects(this.getBounds())){
					bossHealth -= handler.getCurrentPlayer().getBulletSize();
					handler.removeObject(go);
					if(bossHealth <= 0) {
						handler.removeObject(this);
						Health.COINS += 50;
					}
				}
			}
			
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, (int)((double)bossHealth/250 * 50) , 50);
		
		g.setColor(Color.red);
		g.fillRect(x + (int)((double)bossHealth/250 * 50), y, (int)(50 - (double)bossHealth/250 * 50), 50);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
		
	}

	
}
