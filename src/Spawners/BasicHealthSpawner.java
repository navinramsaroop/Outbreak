package Spawners;
import System.*;
import Enemy.*;
import java.awt.Graphics;
import java.util.Random;

/**
 *  EnemySpawner that adds a BasicHealthEnemy and Tracker enemies to the screen.
 * @author Navin Ramsaroop
 *
 */
public class BasicHealthSpawner {
	private Handler handler;
	private Health health;
	private Random r = new Random();
	
	
	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public BasicHealthSpawner(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}
	public void tick() {
			if(r.nextInt(1000) < 25 && handler.object.size() < 10) {
				handler.addObject(new Coin(r.nextInt(600), r.nextInt(400), ID.Coin, handler));
			}
			if(r.nextInt(1000) < 15 && handler.object.size() < 10) {
				handler.addObject(new Coin5(r.nextInt(600), r.nextInt(400), ID.Coin5, handler));
			}
		
			if(Health.SCORE == 1) {
				handler.addObject(new TrackerEnemy(0, 0, ID.TrackerEnemy, handler, 7));
			}
			if(Health.SCORE % 150 == 0 && Health.level < 10) {
				handler.addObject(new BasicEnemyHealth(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemyHealth, handler, r.nextInt(7), r.nextInt(7)));
				handler.addObject(new BasicEnemyHealth(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemyHealth, handler, r.nextInt(7), r.nextInt(7)));
				health.setLevel(health.getLevel() + 1);
			}
			if(Health.SCORE % 150 == 0 && Health.level >= 10 && Health.level < 20) {
				handler.addObject(new BasicEnemyHealth(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemyHealth, handler, r.nextInt(12), r.nextInt(15)));
				handler.addObject(new BasicEnemyHealth(r.nextInt(Game.WIDTH),r.nextInt( Game.HEIGHT), ID.BasicEnemyHealth, handler, r.nextInt(12), r.nextInt(15)));
				health.setLevel(health.getLevel() + 1);
			}
			if(Health.SCORE % 150 == 0 && Health.level >= 20) {
				handler.addObject(new Boss2(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.BasicEnemyHealth, handler, 3));
				health.setLevel(health.getLevel() + 1);
			}
			if(handler.getCurrentPlayer().getHealth() == 0) {
				 Game.gameState = Game.STATE.End;
				 
					if(HighScore.TINYENEMYSCORE < health.getScore() ) {
						 HighScore.TINYENEMYSCORE = health.getScore();
					}
					
					if(HighScore.TINYENEMYLEVEL < health.getLevel()) {
						HighScore.TINYENEMYLEVEL = health.getLevel();
					}
				 handler.object.clear();
				 System.gc();
		}
	
	}
	public void render(Graphics g) {

	}
}