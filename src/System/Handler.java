package System;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Handles all of the objects -- both players and enemy objects.
 * @author Navin Ramsaroop
 *
 */
public class Handler {

	/**
	 * Contains all GameObjects, and handles them appropriately (ticking and rendering them).
	 */
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	/**
	 * Contains all PlayerObjects, and handles them appropriately (ticking and rendering the current one).
	 */
	public LinkedList<PlayerObject> player = new LinkedList<PlayerObject>();

	/*
	 * Updates all player and game objects.
	 */
	public void tick() {
		for (int x = 0; x < object.size(); x++) {
			object.get(x).tick();
		}
		if(Game.gameState == Game.STATE.Game)
			getCurrentPlayer().tick();
	}

	/**
	 * Display everything to the screen.
	 * @param g Graphics card that everything should be displayed onto
	 */
	public void render(Graphics g) {
		for (int x = 0; x < object.size(); x++) {
			object.get(x).render(g);
		}
		if(Game.gameState == Game.STATE.Game)
			getCurrentPlayer().render(g);
	}

	/**
	 * Add a GameObject to handler.
	 * @param addedObject
	 */
	public void addObject(GameObject obj) {
		this.object.add(obj);
	}

	/**
	 * Remove a GameObject to handler.
	 * @param removedObject
	 */
	public void removeObject(GameObject removedObject) {
		this.object.remove(removedObject);
		System.gc();
	}

	/**
	 * Add a PlayerObject to handler.
	 * @param addedObject
	 */
	public void addPlayerObject(PlayerObject obj) {
		player.add(obj);
	}
	
	/**
	 * Remove a PlayerObject to handler.
	 * @param removedObject
	 */
	public void removePlayerObject(PlayerObject obj) {
		player.remove(obj);
	}
	
	/**
	 * 
	 * @return PlayerObject that is currently being used.
	 */
	public PlayerObject getCurrentPlayer() {
		return player.get(Game.currentPlayer);
	}
	
	/**
	 * Resets frame and game after game ends.
	 */
	public void resetGame() {
		getCurrentPlayer().HEALTH = getCurrentPlayer().maxHealth;
		Health.SCORE = 0;
		getCurrentPlayer().vX = 0;
		getCurrentPlayer().vY = 0;
	}

	/**
	 * Clears screen of basic enemies
	 */
	public void removeBasicEnemy() {
		int count = 0;
		while (count < object.size()) {
			if (object.get(count).getID() != ID.BasicEnemy) {
				count++;
			} else if (object.get(count).getID() == ID.BasicEnemy) {
				removeObject(object.get(count));
			}
		}
		System.gc();
	}
	
	/**
	 * @return number of GameObjects currently in the screen
	 */
	public int countObjects() {
		return object.size();
	}
	
	/**
	 * Remove all fast enemies from the screen
	 */
	public void removeFastEnemy() {
		int count = 0;
		while (count < object.size()) {
			if (object.get(count).getID() != ID.FastEnemy) {
				count++;
			} else if (object.get(count).getID() == ID.FastEnemy) {
				removeObject(object.get(count));
			}
		}
		System.gc();
	}

}
