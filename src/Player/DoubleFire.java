package Player;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates a player instance that shoots two bullets
 * in the direction the user chooses. Strong player, normal 
 * amunition, speed, and size.
 * @author navinr
 *
 */
public class DoubleFire extends PlayerObject {
	Handler handler;
	Random r = new Random();
	Skins skin;
	Image img;
	
	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id Player Identifier
	 * @param handler Handler from the main class
	 * @param skin Skin representing what image is 
	 * to be rendered for this player
	 */
	public DoubleFire(int x, int y, ID id, Handler handler, Skins skin) {
		super(x, y, id);
		this.handler = handler;
		this.skin = skin;
		
		vX = 0;
		vY = 0;

		color = Color.blue;
		HEALTH = 200;
		maxHealth = 200;
		bulletSize = 6;
		playerSpeed = 8;
		healthMultiplier = 1;
		speedMultiplier = 1;
		cannonMultiplier = 1;
		currentSkin = 9;
		reload = 55;
		reloadMax = 55;
		
		amunitionMax = 250;
		amunitionCurrent = 250;
		
	}
	
	public void tick() {

		x += vX;
		y += vY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 25);
		y = Game.clamp(y, 0, Game.HEIGHT - 50);
		
		if(reload < reloadMax){
			reload = reload + 200/reloadMax;
		}
		
		for (int i = 0; i < handler.object.size(); i++) {

			if (handler.object.get(i).getID() == ID.BasicEnemy) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 10;
				}

			} else if (handler.object.get(i).getID() == ID.FastEverwingBoss) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 25;
					handler.removeObject(handler.object.get(i));
				}

			} else if (handler.object.get(i).getID() == ID.Potion) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH += 50;
					handler.removeObject(handler.object.get(i));
				}

			} else if (handler.object.get(i).getID() == ID.TrackerEnemy) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 3;
				}

			} else if (handler.object.get(i).getID() == ID.Coin) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					Health.COINS++;
					handler.removeObject(handler.object.get(i));
				}

			} else if (handler.object.get(i).getID() == ID.Coin5) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					Health.COINS += 5;
					handler.removeObject(handler.object.get(i));
				}

			} else if (handler.object.get(i).getID() == ID.FastEnemy) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 10;

				}

			} else if (handler.object.get(i).getID() == ID.BasicEnemyHealth) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 7;

				}
			} else if (handler.object.get(i).getID() == ID.Boss3Bullets) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 7;

				}

			} else if (handler.object.get(i).getID() == ID.Boss1) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 7;

				}

			} else if (handler.object.get(i).getID() == ID.Boss1Super) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 7;

				}

			} else if (handler.object.get(i).getID() == ID.Boss2) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 7;

				}

			} else if (handler.object.get(i).getID() == ID.Boss2Super) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 7;

				}

			} else if (handler.object.get(i).getID() == ID.ChangeUpEnemy) {

				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 7;

				}

			} else if (handler.object.get(i).getID() == ID.TrackerBullets) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 6;

				}
			} else if (handler.object.get(i).getID() == ID.TrackerBulletEnemy) {
				if (getBounds().intersects(handler.object.get(i).getBounds())) {
					HEALTH -= 10;

				}
			}
		}

	}

	public void render(Graphics g) {
		
		img = skin.getPicture(currentSkin).getScaledInstance(30, 30, 0);
		g.drawImage(img, x, y, null);
		
		g.setColor(Color.gray);
		g.fillRect(Game.WIDTH - 250, 0, 200, 20);
		
		g.setColor(Color.green);
		g.fillRect(Game.WIDTH - 250, 0, (int)((double)reload/reloadMax*200), 20);
		
		g.setColor(Color.white);
		g.fillRect(Game.WIDTH - 250, 20, (int)((double)amunitionCurrent/amunitionMax * 200) , 20);
		
		g.drawString("Reload", 0 , Game.WIDTH - 200);
		
		g.setColor(Color.red);
		g.drawString("AMUNITION", Game.WIDTH - 250, 20);
		
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
	}

	public void bulletSpawn(String direction) {
		amunitionCurrent--;
		if(direction.equals("Up")) {
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX(), handler.getCurrentPlayer().getY(),ID.Bullet, handler, "Up"));
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX() + 15, handler.getCurrentPlayer().getY(), ID.Bullet, handler, "Up"));
		}
		if(direction.equals("Down")) {
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX(), handler.getCurrentPlayer().getY(),ID.Bullet, handler, "Down"));	
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX() + 15, handler.getCurrentPlayer().getY(), ID.Bullet, handler, "Down"));
		}	
		if(direction.equals("Right")) {
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX(), handler.getCurrentPlayer().getY(),ID.Bullet, handler, "Right"));
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX(),handler.getCurrentPlayer().getY() + 15, ID.Bullet, handler, "Right"));
		}
		if(direction.equals("Left")) {
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX(), handler.getCurrentPlayer().getY(),ID.Bullet, handler, "Left"));
			handler.addObject(new Bullet(handler.getCurrentPlayer().getX(),handler.getCurrentPlayer().getY() + 15, ID.Bullet, handler, "Left"));
		}
	}

}
