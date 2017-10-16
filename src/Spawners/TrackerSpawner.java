package Spawners;
import System.*;
import Enemy.*;
import java.awt.Graphics;
import java.util.Random;

/**
 * Spawns tracker enemies on the screen.
 * @author Navin Ramsaroop
 *
 */
public class TrackerSpawner {
	private Handler handler;
	private Health health;
	private Random r = new Random();
	
	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public TrackerSpawner(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}
	public void tick() {
			if(Health.SCORE == 0) {
				health.setLevel(health.getLevel() + 1);
				handler.addObject(new TrackerEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.TrackerEnemy, handler , health.getLevel() + 1));
			}
			if(Health.SCORE % 200 == 0) {
				health.setLevel(health.getLevel() + 1);
				if(health.getLevel() >= 10) {
					handler.addObject(new TrackerEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.TrackerEnemy, handler , 10 ));
				}
				else{
					handler.addObject(new TrackerEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.TrackerEnemy, handler , health.getLevel() + 1 ));
				}
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
