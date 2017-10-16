package Enemy;
import System.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates an enemy that tracks the player around the 
 * screen, and shoots bullets in that direction. Player must be at
 * one of the four cardinal directions for the enemy
 * to shoot bullets at it. 
 * @author Navin Ramsaroop
 *
 */
public class TrackerBulletEnemy extends GameObject{
	Random r = new Random();
	Handler handler;
	public int bossHealth = 50;
	double resultantV = 4;
	public TrackerBulletEnemy(int xTemp, int yTemp, ID idTemp, Handler handler, double resultantV) {
		super(xTemp, yTemp, idTemp);
		
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
		if(Math.abs(tempX) < 25 && Health.SCORE % 10 == 0 && tempY > 0) {
			handler.addObject(new TrackerBullets(x + 6, y + 50, ID.TrackerBullets, handler, "Down"));
			handler.addObject(new TrackerBullets(x + 40, y + 50, ID.TrackerBullets, handler, "Down"));
		}
		else if(Math.abs(tempX) < 25 && Health.SCORE % 10 == 0 && tempY < 0) {
			handler.addObject(new TrackerBullets(x + 6, y, ID.TrackerBullets, handler, "Up"));
			handler.addObject(new TrackerBullets(x + 40, y, ID.TrackerBullets, handler, "Up"));
			
		}
		else if(Math.abs(tempY) < 25 && Health.SCORE % 10 == 0 && tempX > 0) {
			handler.addObject(new TrackerBullets(x + 50, y + 6, ID.TrackerBullets, handler, "Right"));
			handler.addObject(new TrackerBullets(x + 50, y + 40, ID.TrackerBullets, handler, "Right"));
		}
		else if(Math.abs(tempY) < 25 && Health.SCORE % 10 == 0 && tempX < 0) {
			handler.addObject(new TrackerBullets(x, y + 6, ID.TrackerBullets, handler, "Left"));
			handler.addObject(new TrackerBullets(x, y + 40, ID.TrackerBullets, handler, "Left"));
		}
		
		x += vX;	
		y += vY;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Bullet){
				if(handler.object.get(i).getBounds().intersects(this.getBounds())){
					bossHealth -=5;
					handler.removeObject(handler.object.get(i));
					if(bossHealth <= 0) {
						handler.removeObject(this);
						Health.COINS += 40;
					}
				}
			}
			
		}
		
	}

	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 50, 50);
		
		g.setColor(Color.gray);
		g.fillRect(x, y - 10, 50, 2);
		
		g.setColor(Color.red);
		g.fillRect(x, y - 10, bossHealth, 2);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 50, 50);
	}

	
}
