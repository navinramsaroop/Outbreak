package Spawners;
import System.*;
import Enemy.*;
import java.util.Random;

/**
 * First spawner programmed, is the most developed and contains
 * the widest array of enemies. Has both shooting portions and
 * avoiding obstacles. 
 * @author Navin Ramsaroop
 *
 */
public class Spawner {

	private Handler handler;
	private Health health;
	private int timer = 0;

	private Random r = new Random();
	private Boss2Super boss;
	private BossBullet3 bb3;

	/**
	 * 
	 * @param handler Handler from the main class
	 * @param health Health instance from the main class
	 */
	public Spawner(Handler handler, Health health) {
		this.handler = handler;
		this.health = health;
	}

	public void tick() {
		timer++;
		if (timer == 150) {

			if (health.getLevel() == 0) {
				timer = 0;
				health.setLevel(1);
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -96), r.nextInt(Game.HEIGHT -96), ID.BasicEnemy, 6, 6));
			} else if (health.getLevel() == 1) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
				handler.addObject(new ChangeUpEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));
				handler.addObject(new ChangeUpEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));
				handler.addObject(new ChangeUpEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));
				
			} else if (health.getLevel() == 2) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
				for(int i = 0; i < 4; i++) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				}
			} else if (health.getLevel() == 3) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
				for(int i = 0; i < 3; i++) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				}
			} else if (health.getLevel() == 4) {
				timer = -500;
				health.setLevel(health.getLevel() + 1);
				for(int i = 0; i < 9; i++) {
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				}
			}

		}
		if (health.getLevel() == 5) {
			if (timer == 100) {
				handler.removeBasicEnemy();
				handler.removeFastEnemy();
			}
			if (timer == 200) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 235) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 270) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 305) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 340) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 375) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 410) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));

			} else if (timer == 445) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 480) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 510) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 545) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 625) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));
			} else if (timer == 675) {
				handler.addObject(new Boss1(0, r.nextInt(Game.HEIGHT), ID.Boss1, handler, 4, 0));

			}else if (timer == 725) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
			}
		}
		if (health.getLevel() == 6) {
			if (timer == 100) {
				handler.addObject(new Boss2(0, r.nextInt(Game.HEIGHT), ID.Boss2, handler, 3));
			}
			if (timer == 500) {
				handler.addObject(new Boss2(0, r.nextInt(Game.HEIGHT), ID.Boss2, handler, 4));
			} else if (timer == 1000) {
				handler.addObject(new Boss2(0, r.nextInt(Game.HEIGHT), ID.Boss2, handler, 5));
			} else if (timer == 1400) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);

			}
		}

//		if (health.getLevel() >= 7 && r.nextInt(1000) < 50 && handler.object.size() < 6 && coinSpawn) {
//			handler.addObject(new Coin5(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Coin5, handler));
//			if (r.nextInt(1000) < 300) {
//				handler.addObject(new Potion(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Potion, handler));
//			}
//		}

		if (health.getLevel() == 7) {

			if (timer == 0) {
				handler.addObject(new Potion(Game.WIDTH/2, Game.HEIGHT/2, ID.Potion, handler));
				boss = new Boss2Super(0, 0, ID.Boss2Super, handler, 4);
				handler.addObject(boss);
			} else if (boss != null && boss.getHp() <= 0 && handler.countObjects() == 0) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
			}
		}
		if (health.getLevel() == 8) {
			if (timer == 100) {
				handler.addObject(new Potion(300, 200, ID.Potion, handler));
				handler.addObject(new BasicEnemy(0, 0, ID.BasicEnemy, 6, 6));
				handler.addObject(new BasicEnemy(40, 0, ID.BasicEnemy, 6, 6));
				handler.addObject(new BasicEnemy(80, 0, ID.BasicEnemy, 6, 6));
				handler.addObject(new BasicEnemy(120, 0, ID.BasicEnemy, 6, 6));

				handler.addObject(new FastEnemy(0, 0, ID.FastEnemy, 7, 7));
				handler.addObject(new FastEnemy(40, 0, ID.FastEnemy, 7, 7));
				handler.addObject(new FastEnemy(80, 0, ID.FastEnemy, 7, 7));
				handler.addObject(new FastEnemy(120, 0, ID.FastEnemy, 7, 7));
				
				
				handler.addObject(new Potion(300, Game.HEIGHT - 100, ID.Potion, handler));
				handler.addObject(new BasicEnemy(0, Game.HEIGHT - 100, ID.BasicEnemy, 6, 6));
				handler.addObject(new BasicEnemy(40, Game.HEIGHT - 100, ID.BasicEnemy, 6, 6));
				handler.addObject(new BasicEnemy(80, Game.HEIGHT - 100, ID.BasicEnemy, 6, 6));
				handler.addObject(new BasicEnemy(120, Game.HEIGHT - 100, ID.BasicEnemy, 6, 6));

				handler.addObject(new FastEnemy(0, Game.HEIGHT - 100, ID.FastEnemy, 7, 7));
				handler.addObject(new FastEnemy(40, Game.HEIGHT - 100, ID.FastEnemy, 7, 7));
				handler.addObject(new FastEnemy(80, Game.HEIGHT - 100, ID.FastEnemy, 7, 7));
				handler.addObject(new FastEnemy(120, Game.HEIGHT - 100, ID.FastEnemy, 7, 7));

				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, 9, 9));
				
			}
			if (timer == 600) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
			}
		}

		if (health.getLevel() == 9) {
			if (timer == 100) {
				handler.removeBasicEnemy();
				handler.removeFastEnemy();
			}
			if (timer == 200) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 275) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 350) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 425) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 500) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 575) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 650) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));

			} else if (timer == 725) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 800) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 850) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 925) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 975) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 1025) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));
			} else if (timer == 1100) {
				handler.addObject(new Boss1Super(0, r.nextInt(Game.HEIGHT), ID.Boss1Super, handler));

			} else if (timer == 1200) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
			}
		}
		if (health.getLevel() == 10) {
			if (timer == 50) {
				bb3 = new BossBullet3(0, Game.HEIGHT / 2 - 96, ID.BossBullet3, handler);
				handler.addObject(bb3);
			}
			if (timer > 50 && bb3.getHealth() < 0) {
				timer = 0;
				health.setLevel(health.getLevel() + 1);
			}
		}
		if (health.getLevel() == 11) {
			if (timer == 0) {
				boss = new Boss2Super(0, 0, ID.Boss2Super, handler, 3);
				boss.setHp();
				handler.addObject(boss);
			}
			if (boss != null && boss.getHp() < 0) {
				handler.addObject(new Boss2(boss.getX(), boss.getY(), ID.Boss2, handler,3));
				handler.addObject(new Boss2(boss.getX(), boss.getY(), ID.Boss2, handler, 4));
				handler.addObject(new Boss2(boss.getX(), boss.getY(), ID.Boss2, handler,5));
				handler.addObject(new Boss2(boss.getX(), boss.getY(), ID.Boss2, handler, 6));
				
				health.setLevel(health.getLevel() + 1);
			}

		}
		if(health.getLevel() == 12) {
			int counter = 0; 
			for(int i = 0; i < handler.object.size(); i++) {
				if(handler.object.get(i).getID() == ID.Boss2) {
					counter++;
				}
			}
			if(counter == 0) {
				health.setLevel(health.getLevel() + 1);
				
			}
		}
		
 		if (handler.getCurrentPlayer().getHealth() == 0) {
			Game.gameState = Game.STATE.End;
			timer = 0;
			handler.object.clear();

			if (HighScore.NORMALSCORE < health.getScore()) {
				HighScore.NORMALSCORE = health.getScore();
			}

			if (HighScore.NORMALLEVEL < health.getLevel()) {
				HighScore.NORMALLEVEL = health.getLevel();
			}

			// System.gc();
		}

	}

}
