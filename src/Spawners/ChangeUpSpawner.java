package Spawners;
import System.*;
import Enemy.*;
import java.awt.Graphics;
import java.util.Random;


/**
 * Spawns only change up enemies. 
 * @author navinr
 *
 */
public class ChangeUpSpawner {
	private Handler handler;
	private Health health;
	private Random r = new Random();
	
	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public ChangeUpSpawner(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}
	public void tick() {
			if(r.nextInt(1000) < 25 && handler.object.size() < 10) {
				handler.addObject(new Coin(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Coin, handler));
			}
			if(Health.SCORE % 150 == 0) {
				handler.addObject(new ChangeUpEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), ID.ChangeUpEnemy));
				health.setLevel(health.getLevel() + 1);
			}
			if(handler.getCurrentPlayer().getHealth() == 0) {
				 Game.gameState = Game.STATE.End;
				 
					if(HighScore.CHANGEUPSCORE < health.getScore() ) {
						 HighScore.CHANGEUPSCORE = health.getScore();
					}
					
					if(HighScore.CHANGEUPLEVEL < health.getLevel()) {
						HighScore.CHANGEUPLEVEL = health.getLevel();
					}
				 handler.object.clear();
				 System.gc();
		}
	
	}
	public void render(Graphics g) {

	}
}