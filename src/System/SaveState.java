package System;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Saving mechanism for game
 * Manually saves each important numerical value, and manually
 * reads them when initializing the game
 * @author Navin Ramsaroop
 *
 */
public class SaveState {
	private int coins = Health.COINS;

	private int highScoreChangeUp;
	private int highLevelChangeUp;
	private int highScoreExtreme;
	private int highLevelExtreme;
	private int highScoreNormal;
	private int highLevelNormal;
	private int highScoreEasy;
	private int highLevelEasy;
	private int highWarLevel;
	private int highWarScore;
	
	private Color colorPlayer1;
	private int HEALTHPlayer1;
	private int maxHealthPlayer1;
	private int bulletSizePlayer1;
	private int playerSpeedPlayer1;
	private int coinMultiplierPlayer1;
	private int speedMultiplierPlayer1;
	private int cannonMultiplierPlayer1;
	private int currentSkinPlayer1;
	
	private Color colorCannonBlaster;
	private int HEALTHCannonBlaster;
	private int maxHealthCannonBlaster;
	private int bulletSizeCannonBlaster;
	private int playerSpeedCannonBlaster;
	private int coinMultiplierCannonBlaster;
	private int speedMultiplierCannonBlaster;
	private int cannonMultiplierCannonBlaster;
	private int currentSkinCannonBlaster;
	
	private Color colorDoubleFire;
	private int HEALTHDoubleFire;
	private int maxHealthDoubleFire;
	private int bulletSizeDoubleFire;
	private int playerSpeedDoubleFire;
	private int coinMultiplierDoubleFire;
	private int speedMultiplierDoubleFire;
	private int cannonMultiplierDoubleFire;
	private int currentSkinDoubleFire;
	
	/**
	 * Saves game
	 * @param s File name (without the ".sav")
	 * @param handler 
	 */
	public void saveFile(String s, Handler handler) {
		try{
			coins = Health.COINS;
			highScoreChangeUp = HighScore.CASTLEDEFENSESCORE;
			highLevelChangeUp = HighScore.CASTLEDEFENSELEVEL;
			highScoreExtreme = HighScore.EXTREMESCORE;
			highLevelExtreme = HighScore.EXTREMELEVEL;
			highScoreNormal = HighScore.NORMALSCORE;
			highLevelNormal = HighScore.NORMALLEVEL;
			highScoreEasy = HighScore.EASYSCORE;
			highLevelEasy = HighScore.EASYLEVEL;
			highWarLevel = HighScore.WARLEVEL;
			highWarScore = HighScore.WARSCORE;
			
			colorPlayer1 = handler.player.get(0).color;
			HEALTHPlayer1 = handler.player.get(0).HEALTH;
			maxHealthPlayer1 = handler.player.get(0).maxHealth;
			bulletSizePlayer1 = handler.player.get(0).bulletSize;
			playerSpeedPlayer1 = handler.player.get(0).playerSpeed;
			coinMultiplierPlayer1 = handler.player.get(0).healthMultiplier;
			speedMultiplierPlayer1 = handler.player.get(0).speedMultiplier;
			cannonMultiplierPlayer1 = handler.player.get(0).cannonMultiplier;
			currentSkinPlayer1 = handler.player.get(0).currentSkin;
			
			colorCannonBlaster = handler.player.get(1).color;
			HEALTHCannonBlaster = handler.player.get(1).HEALTH;
			maxHealthCannonBlaster = handler.player.get(1).maxHealth;
			bulletSizeCannonBlaster = handler.player.get(1).bulletSize;
			playerSpeedCannonBlaster = handler.player.get(1).playerSpeed;
			coinMultiplierCannonBlaster = handler.player.get(1).healthMultiplier;
			speedMultiplierCannonBlaster = handler.player.get(1).speedMultiplier;
			cannonMultiplierCannonBlaster = handler.player.get(1).cannonMultiplier;
			currentSkinCannonBlaster = handler.player.get(1).currentSkin;
			
			colorDoubleFire = handler.player.get(2).color;
			HEALTHDoubleFire = handler.player.get(2).HEALTH;
			maxHealthDoubleFire = handler.player.get(2).maxHealth;
			bulletSizeDoubleFire = handler.player.get(2).bulletSize;
			playerSpeedDoubleFire = handler.player.get(2).playerSpeed;
			coinMultiplierDoubleFire = handler.player.get(2).healthMultiplier;
			speedMultiplierDoubleFire = handler.player.get(2).speedMultiplier;
			cannonMultiplierDoubleFire = handler.player.get(2).cannonMultiplier;
			currentSkinDoubleFire = handler.player.get(2).currentSkin;
			
			FileOutputStream saveFile = new FileOutputStream(s + ".sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			
			save.writeObject(coins);
			save.writeObject(highScoreChangeUp);
			save.writeObject(highLevelChangeUp);
			save.writeObject(highScoreExtreme);
			save.writeObject(highLevelExtreme);
			save.writeObject(highScoreNormal);
			save.writeObject(highLevelNormal);
			save.writeObject(highScoreEasy);
			save.writeObject(highLevelEasy);
			save.writeObject(highWarLevel);
			save.writeObject(highWarScore);
			
			save.writeObject(colorPlayer1);
			save.writeObject(HEALTHPlayer1);
			save.writeObject(maxHealthPlayer1);
			save.writeObject(bulletSizePlayer1);
			save.writeObject(playerSpeedPlayer1);
			save.writeObject(coinMultiplierPlayer1);
			save.writeObject(speedMultiplierPlayer1);
			save.writeObject(cannonMultiplierPlayer1);
			save.writeObject(currentSkinPlayer1);
			
			save.writeObject(colorCannonBlaster);
			save.writeObject(HEALTHCannonBlaster);
			save.writeObject(maxHealthCannonBlaster);
			save.writeObject(bulletSizeCannonBlaster);
			save.writeObject(playerSpeedCannonBlaster);
			save.writeObject(coinMultiplierCannonBlaster);
			save.writeObject(speedMultiplierCannonBlaster);
			save.writeObject(cannonMultiplierCannonBlaster);
			save.writeObject(currentSkinCannonBlaster);
			
			save.writeObject(colorDoubleFire);
			save.writeObject(HEALTHDoubleFire);
			save.writeObject(maxHealthDoubleFire);
			save.writeObject(bulletSizeDoubleFire);
			save.writeObject(playerSpeedDoubleFire);
			save.writeObject(coinMultiplierDoubleFire);
			save.writeObject(speedMultiplierDoubleFire);
			save.writeObject(cannonMultiplierDoubleFire);
			save.writeObject(currentSkinDoubleFire);
			
			save.close();
			save.flush();
		}
		catch(Exception io) {
			io.printStackTrace();
		}
	}
	
	/**
	 * Reads file
	 * @param s File name without the ".sav"
	 * @param handler
	 */
	public void readFile(String s, Handler handler) {
		try {
			FileInputStream saveFile = new FileInputStream(s + ".sav");
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			Health.COINS = ((Integer) save.readObject());
			HighScore.CASTLEDEFENSESCORE = (Integer) save.readObject();
			HighScore.CASTLEDEFENSELEVEL = (Integer) save.readObject();
			HighScore.EXTREMESCORE = (Integer) save.readObject();
			HighScore.EXTREMELEVEL = (Integer) save.readObject();
			HighScore.NORMALSCORE = (Integer) save.readObject();
			HighScore.NORMALLEVEL = (Integer) save.readObject();
			HighScore.EASYSCORE = (Integer) save.readObject();
			HighScore.EASYLEVEL = (Integer) save.readObject();
			HighScore.WARLEVEL = (Integer) save.readObject();
			HighScore.WARSCORE = (Integer) save.readObject();
			
			handler.player.get(0).setColor((Color) save.readObject());
			handler.player.get(0).setHealth((Integer) save.readObject());
			handler.player.get(0).setMaxHealth((Integer) save.readObject());
			handler.player.get(0).setBulletSize((Integer) save.readObject());
			handler.player.get(0).setPlayerSpeed((Integer) save.readObject());
			handler.player.get(0).setHealthMultiplier((Integer) save.readObject());
			handler.player.get(0).setSpeedMultiplier((Integer) save.readObject());
			handler.player.get(0).setCannonMultiplier((Integer) save.readObject());
			handler.player.get(0).setSkin((Integer) save.readObject());
			
			handler.player.get(1).setColor((Color) save.readObject());
			handler.player.get(1).setMaxHealth((Integer) save.readObject());
			handler.player.get(1).setHealth((Integer) save.readObject());
			handler.player.get(1).setBulletSize((Integer) save.readObject());
			handler.player.get(1).setPlayerSpeed((Integer) save.readObject());
			handler.player.get(1).setHealthMultiplier((Integer) save.readObject());
			handler.player.get(1).setSpeedMultiplier((Integer) save.readObject());
			handler.player.get(1).setCannonMultiplier((Integer) save.readObject());
			handler.player.get(1).setSkin((Integer) save.readObject());
		
			handler.player.get(2).setColor((Color) save.readObject());
			handler.player.get(2).setHealth((Integer) save.readObject());
			handler.player.get(2).setMaxHealth((Integer) save.readObject());
			handler.player.get(2).setBulletSize((Integer) save.readObject());
			handler.player.get(2).setPlayerSpeed((Integer) save.readObject());
			handler.player.get(2).setHealthMultiplier((Integer) save.readObject());
			handler.player.get(2).setSpeedMultiplier((Integer) save.readObject());
			handler.player.get(2).setCannonMultiplier((Integer) save.readObject());
			handler.player.get(2).setSkin((Integer) save.readObject());
			
			save.close();
			
		}
		catch(Exception io) {
			io.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param s File name without the ".sav"
	 * @return True if file exists, and False if it does not exist 
	 */
	@SuppressWarnings("resource")
	public boolean checkExistence(String s) {
			try {
				new FileInputStream(s + ".sav");
				return true;
			}
			catch (Exception io){
				return false;
			}
					
	}
}
