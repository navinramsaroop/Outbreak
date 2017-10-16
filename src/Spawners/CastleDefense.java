package Spawners;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Enemy.*;
import System.Game;
import System.Handler;
import System.Health;
import System.HighScore;
import System.ID;


/**
 * Spawner for the Castle defense level
 * @author Navin Ramsaroop
 *
 */
public class CastleDefense {
	private Handler handler;
	private Health health;
	private Random r = new Random();
	private Font bigFont = new Font("Times New Roman" , Font.BOLD, 50);
	private String instructions = new String("Don't let any enemy reach the right side of the screen!");
	private Rectangle rect = new Rectangle(Game.WIDTH - 50, 0, 150, Game.HEIGHT);
	private Rectangle playerRect = new Rectangle(Game.WIDTH - 50, 0, 150, 50);
	private int castleHealth = 300;
	private int healthYPosition = 0;
	
	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public CastleDefense(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}
	
	public void tick() {
		if(Health.SCORE == 150){
			handler.addObject(new BasicEnemyHealth(0 , r.nextInt(Game.HEIGHT - 100), ID.BasicEnemyHealth, handler, 4, 5));
			handler.addObject(new BasicEnemyHealth(0 , r.nextInt(Game.HEIGHT - 100), ID.BasicEnemyHealth, handler, 4, 0));
			
			health.setLevel(health.getLevel() + 1);
			
		}
		if(handler.object.size() == 0 && health.getLevel() < 5) {
			health.setLevel(health.getLevel() + 1);
			for(int i = 0; i < health.getLevel(); i++) {
				handler.addObject(new BasicEnemyHealth(0 , r.nextInt(Game.HEIGHT - 100), ID.BasicEnemyHealth, handler, 4, 0));
			}
		}
		if(handler.object.size() == 0 && health.getLevel() == 5) {
			handler.addObject(new Boss2(0, 0, ID.Boss2, handler, 5));
			health.setLevel(health.getLevel() + 1);
		}
		if(handler.object.size() == 0 && health.getLevel() == 6) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new Boss2(0, 0, ID.Boss2, handler, 5));
			
			handler.addObject(new BasicEnemyHealth(0 , r.nextInt(Game.HEIGHT - 100), ID.BasicEnemyHealth, handler, 4, 0));
		}
		if(handler.object.size() == 0 && health.getLevel() == 7) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new TrackerBulletEnemy(0, 0, ID.Boss2, handler, 4));
		}
		if(handler.object.size() == 0 && health.getLevel() == 8) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new Cannon(0, + 150, ID.Cannon, handler, 6));
			handler.addObject(new Cannon(0, Game.HEIGHT - 150, ID.Cannon, handler, 5));
		}
		if(handler.object.size() == 0 && health.getLevel() == 9) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new BasicEnemyHealthSpawner(0, Game.HEIGHT/2, ID.BasicEnemyHealthSpawner, handler, 5, 0 ));
		}
		if(handler.object.size() == 0 && health.getLevel() == 10) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new BasicEnemyHealthSpawner(0, Game.HEIGHT/2 - 200, ID.BasicEnemyHealthSpawner, handler, 5, 0));
			handler.addObject(new BasicEnemyHealthSpawner(0, Game.HEIGHT/2 + 200, ID.BasicEnemyHealthSpawner, handler,5, 0));
			
		}

		if(handler.object.size() == 0 && health.getLevel() == 11) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new SturdyEnemy(0, Game.WIDTH/2, ID.SturdyEnemy, handler, 1, 0));
		}
		if(handler.object.size() == 0 && health.getLevel() == 12) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new SturdyEnemy(0, Game.WIDTH/2, ID.SturdyEnemy, handler, 1, 0));
			handler.addObject(new SturdyEnemy(0, Game.WIDTH/2 - 100, ID.SturdyEnemy, handler, 2, 0));
			handler.addObject(new SturdyEnemy(0, Game.WIDTH/2 + 100, ID.SturdyEnemy, handler, 1, 0));
			
		}
		if(handler.object.size() == 0 && health.getLevel() == 13) {
			health.setLevel(health.getLevel() + 1);
			handler.addObject(new SturdyEnemy(0, Game.WIDTH/2, ID.SturdyEnemy, handler, 1, 7));
			handler.addObject(new SturdyEnemy(0, Game.WIDTH/2 - 100, ID.SturdyEnemy, handler, 2, 0));
			handler.addObject(new SturdyEnemy(0, Game.WIDTH/2 + 100, ID.SturdyEnemy, handler, 1, 0));
			
		}
		if(handler.getCurrentPlayer().getHealth() == 0) {
			endGame();
		}
		for(int i = 0; i < handler.object.size(); i++) {
			ID id = handler.object.get(i).getID();
			if(id != ID.Bullet && id != ID.TrackerBullets && id != ID.Boss3Bullets) {
				if(rect.intersects(handler.object.get(i).getBounds())) {
					handler.removeObject(handler.object.get(i));
					 castleHealth -= 10;
					 if(castleHealth <= 0) {
						 endGame();
					 }
				}
			}
		}    
		if(handler.getCurrentPlayer().getBounds().intersects(playerRect)) {
			handler.getCurrentPlayer().setHealth(handler.getCurrentPlayer().getHealth() + 1);
		}
		healthYPosition++;
		if(healthYPosition > Game.HEIGHT) {
			healthYPosition = 0;
		}
		playerRect.setLocation(Game.WIDTH - 50, healthYPosition);
	}
	public void endGame() {
		 Game.gameState = Game.STATE.End;
		 handler.object.clear();
		 castleHealth = 300;
		 
			if(HighScore.CASTLEDEFENSESCORE < health.getScore() ) {
				 HighScore.CASTLEDEFENSESCORE = health.getScore();
			}
			
			if(HighScore.CASTLEDEFENSELEVEL < health.getLevel()) {
				HighScore.CASTLEDEFENSELEVEL = health.getLevel();
			}
		 System.gc();
	
	}
	public void render(Graphics g) {
		if(Health.SCORE < 150) {
			g.setColor(Color.red);
			g.setFont(bigFont);
			g.drawString(instructions, 200, Game.HEIGHT/2);
		}
		else {
			g.setColor(Color.white);
			g.fillRect(Game.WIDTH - 50, 0, 150, Game.HEIGHT);
			
			g.setColor(Color.GRAY);
			g.fillRect(Game.WIDTH/2 -150,0, 300, 25);
			
			g.setColor(Color.green);
			g.fillRect(Game.WIDTH/2 - 150, 0, castleHealth, 25);
			
			g.setColor(Color.BLACK);
			g.drawString("CASTLE HEALTH", Game.WIDTH/2 - 150, 15);
			
			g.setColor(Color.MAGENTA);
			g.fillRect(Game.WIDTH - 50, healthYPosition, 150, 25);
		}
	}
	public Rectangle getBounds(){
		return rect;
	}
}
