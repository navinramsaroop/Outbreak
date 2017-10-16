package Spawners;
import System.*;
import Enemy.*;
import java.awt.Graphics;
import java.util.Random;

/**
 * Creates a war level, heavily relying on enemies that shoot 
 * at the player.
 * @author Navin Ramsaroop
 *
 */
public class WarSpawner {
	private Handler handler;
	private Health health;
	private Random r = new Random();
	
	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public WarSpawner(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}
	public void tick() {
		if(Health.SCORE == 1) {
			handler.addObject(new EnemyDeviceSpawner(Game.HEIGHT/2 - 96, Game.WIDTH/2 - 96, ID.EnemySpawnerDevice, handler));
			handler.addObject(new Boss2(0, 0, ID.Boss2, handler, 4));
			health.setLevel(health.getLevel() + 1);
		}
		if(health.getLevel() == 1 && handler.object.size() == 0) {
			health.setLevel(health.getLevel() + 1);
			 handler.addObject(new Cannon(r.nextInt(Game.WIDTH) - 100, r.nextInt(Game.HEIGHT) - 100, ID.Cannon, handler, 11));
			 handler.addObject(new Cannon(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Cannon, handler, 11));
		}
		 if(health.getLevel() == 2 &&  handler.object.size() == 0) {
			 handler.addObject(new Boss2Super(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Boss2Super, handler, 5));
			 handler.addObject(new Cannon(r.nextInt(Game.WIDTH) - 100, r.nextInt(Game.HEIGHT) - 100, ID.Cannon, handler, 12));
			 health.setLevel(health.getLevel() + 1);			 
		 }
		 if(health.getLevel() == 3 && handler.object.size() == 0) {
			 
		 }
		if(handler.getCurrentPlayer().getHealth() == 0) {
			 Game.gameState = Game.STATE.End;
			 handler.object.clear();

				if(HighScore.WARSCORE < health.getScore() ) {
					 HighScore.WARSCORE = health.getScore();
				}
				
				if(HighScore.WARLEVEL < health.getLevel()) {
					HighScore.WARLEVEL = health.getLevel();
				}
			 System.gc();
		
		}
		
		 
	}
	public void render(Graphics g) {
		
	}
}
