package Spawners;
import System.*;
import Enemy.*;
import java.awt.Graphics;
import java.util.Random;

/**
 * Creates an spawner that produces basic enemies.
 * @author Navin Ramsaroop
 *
 */
public class EasySpawner {
	private Handler handler;
	private Health health;
	private Random r = new Random();
	
	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public EasySpawner(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}
	public void tick() {
		if(r.nextInt(1000) < 25) {
			handler.addObject(new Coin(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Coin, handler));
		}
			
			if(Health.SCORE % 150 == 0) {
				
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, 6 , 6));
				health.setLevel(health.getLevel() + 1);
			}
			if(handler.getCurrentPlayer().getHealth() == 0) {
				 Game.gameState = Game.STATE.End;
				 handler.object.clear();
				 
					if(HighScore.EASYSCORE < health.getScore() ) {
						 HighScore.EASYSCORE = health.getScore();
					}
					
					if(HighScore.EASYLEVEL < health.getLevel()) {
						HighScore.EASYLEVEL = health.getLevel();
					}
				 System.gc();
			
		}
	
	}
	public void render(Graphics g) {
		
	}
}
