package System;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	private Handler handler;
	private Game game;

	/**
	 * Handles key input
	 * @param handler Main Handler
	 * @param game Main game class
	 */
	public KeyInput(Handler handler, Game game) {
		this.game = game;
		this.handler = handler;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (Game.gameState == Game.STATE.Game) {
			if (key == KeyEvent.VK_UP)
				handler.getCurrentPlayer().setVelocityY(-handler.getCurrentPlayer().playerSpeed);
			if (key == KeyEvent.VK_DOWN)
				handler.getCurrentPlayer().setVelocityY(handler.getCurrentPlayer().playerSpeed);
			if (key == KeyEvent.VK_LEFT)
				handler.getCurrentPlayer().setVelocityX(-handler.getCurrentPlayer().playerSpeed);
			if (key == KeyEvent.VK_RIGHT)
				handler.getCurrentPlayer().setVelocityX(handler.getCurrentPlayer().playerSpeed);

			if (key == KeyEvent.VK_1) {
				int tempX = handler.getCurrentPlayer().getX();
				int tempY = handler.getCurrentPlayer().getY();
				
				Game.currentPlayer = 0;
				handler.getCurrentPlayer().setX(tempX);
				handler.getCurrentPlayer().setY(tempY);
			}
			if (key == KeyEvent.VK_2) {
				
				int tempX = handler.getCurrentPlayer().getX();
				int tempY = handler.getCurrentPlayer().getY();
				
				Game.currentPlayer = 1;
				
				handler.getCurrentPlayer().setX(tempX);
				handler.getCurrentPlayer().setY(tempY);
				
			}
			if(key == KeyEvent.VK_3) { 
				
				int tempX = handler.getCurrentPlayer().getX();
				int tempY = handler.getCurrentPlayer().getY();
				
				Game.currentPlayer = 2;

				handler.getCurrentPlayer().setX(tempX);
				handler.getCurrentPlayer().setY(tempY);
			}
			if(key == KeyEvent.VK_4) {
				
				int tempX = handler.getCurrentPlayer().getX();
				int tempY = handler.getCurrentPlayer().getY();
				
				Game.currentPlayer = 3;
				
				handler.getCurrentPlayer().setX(tempX);
				handler.getCurrentPlayer().setY(tempY);
			}
			if(key == KeyEvent.VK_5) {
				int tempX = handler.getCurrentPlayer().getX();
				int tempY = handler.getCurrentPlayer().getY();
				
					Game.currentPlayer = 4;
				
				handler.getCurrentPlayer().setX(tempX);
				handler.getCurrentPlayer().setY(tempY);
				
			}
			if (handler.getCurrentPlayer().getReload() >= handler.getCurrentPlayer().getReloadMax() && handler.getCurrentPlayer().amunitionCurrent > 0) {
				if (key == KeyEvent.VK_W) {
					handler.getCurrentPlayer().bulletSpawn("Up");
				}
				if (key == KeyEvent.VK_A) {
					handler.getCurrentPlayer().bulletSpawn("Left");
				}
				if (key == KeyEvent.VK_S) {
					handler.getCurrentPlayer().bulletSpawn("Down");
				}
				if (key == KeyEvent.VK_D) {
					handler.getCurrentPlayer().bulletSpawn("Right");
				}
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (!Game.saveName.toLowerCase().equals("guest")) {
				game.save();
			}

			System.exit(0);
		}

		if (key == KeyEvent.VK_SPACE && (Game.gameState == Game.STATE.Pause || Game.gameState == Game.STATE.Game)) {
			if (Game.gameState == Game.STATE.Pause) {
				Game.gameState = Game.STATE.Game;
			} else {
				Game.gameState = Game.STATE.Pause;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (Game.gameState == Game.STATE.Game) {

			if (key == KeyEvent.VK_UP)
				handler.getCurrentPlayer().setVelocityY(0);
			else if (key == KeyEvent.VK_DOWN)
				handler.getCurrentPlayer().setVelocityY(0);
			else if (key == KeyEvent.VK_LEFT)
				handler.getCurrentPlayer().setVelocityX(0);
			else if (key == KeyEvent.VK_RIGHT)
				handler.getCurrentPlayer().setVelocityX(0);

		}
	}

	public void keyTyped(KeyEvent e) {

	}

}
