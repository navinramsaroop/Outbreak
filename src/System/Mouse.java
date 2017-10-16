package System;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Handles mouse events
 */
public class Mouse extends MouseAdapter {
	Handler handler;
	Game game;
	PauseScreen pause;
	Health health;
	FrameGame frameGame;
	
	/**
	 * 
	 * @param handler
	 * @param game
	 * @param pause
	 * @param health
	 * @param frameGame
	 */
	public Mouse(Handler handler, Game game, PauseScreen pause, Health health, FrameGame frameGame) {
		this.handler = handler;
		this.game = game;
		this.pause = pause;
		this.health = health;
		this.frameGame = frameGame;
	}

	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		 if (Game.gameState == Game.STATE.Menu) {
			if (mouseOver(mouseX, mouseY, Game.WIDTH/2 - 100, Game.HEIGHT/2 - 100, 200, 50)) {
				Game.gameState = Game.STATE.SelectDifficulty;
				frameGame.setFrameVisibility(true);
				frameGame.checkState();
				

			}
			if(mouseOver(mouseX, mouseY, Game.WIDTH/2 - 100, Game.HEIGHT/2 - 50, 200, 50)) {
				//if (Desktop.isDesktopSupported()) { //Taken and modified from stack overflow
				    try{   
				        Desktop.getDesktop().open(new File(getClass().getResource("Instructions.pdf").getFile()));
				    } catch (IOException ex) {
				    	JOptionPane.showMessageDialog(null, "Error loading instructions");
				    	ex.printStackTrace();
				        
				    }
			//	}
			}
			if (mouseOver(mouseX, mouseY, Game.WIDTH/2 - 100, Game.HEIGHT/2, 200, 50)) {
				if(!Game.saveName.toLowerCase().equals("guest")){
					game.save();
			}			
				System.exit(0);
			}
		 }
		if (Game.gameState == Game.STATE.End) {
			if (mouseOver(mouseX, mouseY, 225, 200, 200, 50)) {
				Game.gameState = Game.STATE.SelectDifficulty;
				frameGame.setFrameVisibility(true);
				handler.resetGame();
				health.setLevel(0);
			}
			if (mouseOver(mouseX, mouseY, 225, 250, 200, 50)) {
				Game.gameState = Game.STATE.Menu;
				handler.resetGame();
				health.setLevel(0);
			}
		}
		if(Game.gameState == Game.STATE.Pause) {
			if(Game.SPAWNERSTATE.CastleDefenseSpawner == Game.spState) {
				if(mouseOver(mouseX, mouseY, Game.WIDTH/2 - 100, Game.HEIGHT/2 - 100, 200, 50)) {
					if(Health.COINS >= 500) {
						Health.COINS -= 500; 
						handler.getCurrentPlayer().setAmunition(handler.getCurrentPlayer().amunitionMax);
					}
				}
			}
		}
	}

	public boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if (mouseX > x && mouseX < (x + width) && mouseY > y && mouseY < y + height) {
			return true;
		}
		return false;
	}

	public void mouseReleased() {

	}


}
