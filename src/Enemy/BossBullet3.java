package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
/**
 * Creates an enemy that moves up and down the screen sending
 * bullets
 * @author Navin Ramsaroop
 *
 */
public class BossBullet3 extends GameObject {
	Random r = new Random();
	int timer = Health.SCORE;
	Handler handler;
	public int BossBulletHealth = 200;

	/**
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param id
	 * @param handler
	 */
	public BossBullet3(int x, int y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;

	}

	/**
	 * Update player location
	 */
	public void tick() {
		
		if (Health.SCORE - timer > 25 && Health.SCORE - timer < 50) {
			vX = 1;
			vY = 0;
		}
		if (Health.SCORE - timer > 75 && Health.SCORE - timer < 85) {
			vX = 0;
			vY = 0;
		}
		if(Health.SCORE - timer == 100) {
			vY = 8;
		}
		if (Health.SCORE - timer > 105) {
			if(y > Game.HEIGHT - 50 || y < 0) {
				vY = vY * -1;
			}
			if(r.nextInt(50) < 6) {
				handler.addObject(new Boss3Bullets(x + 100, y + 50, ID.Boss3Bullets, handler, r.nextInt(5) + 5, 0));
			}
		}
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Bullet) {
				if (tempObject.getBounds().intersects(this.getBounds())) {
					BossBulletHealth -= handler.getCurrentPlayer().getBulletSize();
					handler.removeObject(tempObject);
					if (BossBulletHealth < 0) {
						handler.removeObject(this);
						Health.COINS += 30;
					}
				}
			}

		}
		x += vX;
		y += vY;
	}

	/**
	 * Show enemy on screen
	 */
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 100, 100);

		g.setColor(Color.gray);
		g.fillRect(x, y - 10, 100, 5);

		g.setColor(Color.red);
		g.fillRect(x, y - 10, BossBulletHealth / 2, 5);
	}

	/**
	 * @return Rectangle of the Boss
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 100, 100);
	}
	public int getHealth() {
		return BossBulletHealth;
	}

}
