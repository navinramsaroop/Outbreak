package Spawners;
import System.*;
import Enemy.*;
import java.awt.Graphics;
import java.util.Random;

/**
 * Creates a spawner that only produces fast enemies on the screen.
 * @author Navin Ramsaroop
 *
 */
public class ExtremeSpawner {
	private Handler handler;
	private Health health;
	private Random r = new Random();
	
	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public ExtremeSpawner(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}
	public void tick() {
		if(r.nextInt(1000) < 25 && handler.object.size() < 10) {
			handler.addObject(new Coin(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Coin, handler));
		}
		if(r.nextInt(1000) < 15 && handler.object.size() < 10) {
			handler.addObject(new Coin5(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Coin5, handler));
		}
			if(Health.SCORE % 150 == 0) {
				
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				health.setLevel(health.getLevel() + 1);
			}
			if(handler.getCurrentPlayer().getHealth() == 0) {
				 Game.gameState = Game.STATE.End;
				 handler.object.clear();
				 
					if(HighScore.EXTREMESCORE < health.getScore() ) {
						 HighScore.EXTREMESCORE = health.getScore();
					}
					
					if(HighScore.EXTREMELEVEL < health.getLevel()) {
						HighScore.EXTREMELEVEL = health.getLevel();
					}
				 System.gc();
			
		}
	
	}
	public void render(Graphics g) {
		
	}
}
