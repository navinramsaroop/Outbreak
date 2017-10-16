package System;
import java.awt.Color;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Handles menu (to choose which game the user wants to play)
 * @author navinr
 *
 */
public class FrameGame implements MouseListener, ActionListener {
	private JFrame frame = new JFrame();
	private JPanel selectDifficulty = new JPanel();
	private Handler handler;
	private JButton CastleDefense = new JButton("Castle Defense");
	private JButton Extreme = new JButton("Extreme");
	private JButton Normal = new JButton("Normal");
	private JButton Easy = new JButton("Tracker");
	
	private JButton back = new JButton("Back");
	private JButton Store = new JButton("Store");
	private JLabel normalHighScore = new JLabel();
	private JLabel CastleDefenseHS  = new JLabel();
	private JLabel ExtremeHS = new JLabel();
	private JLabel easyHighScore = new JLabel();
	private JButton War = new JButton();
	private JLabel WarHighScore = new JLabel();
	
	private JButton TinyEnemy = new JButton("Tiny Enemy");
	private JLabel TinyEnemyHighScore = new JLabel();

	private JButton playerCustomization = new JButton("Player Customization");
	private JButton powerUps = new JButton("Power Ups");
	
	private String[] pNamesString = {"Normal Player", "Cannon Blaster", "Double Fire"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox playerNames = new JComboBox(pNamesString);
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox playerNamesCustomization = new JComboBox(pNamesString);
	private JLabel playerIcon = new JLabel();
	private JLabel playerIconCustomization = new JLabel();
	
	private JPanel storePanel = new JPanel();
	private JButton backStorePanel = new JButton("Back");
	
	private JPanel powerUpsPanel = new JPanel(); 
	private JButton increasedHealth = new JButton("Increased Health");
	private JButton increasedSpeed = new JButton("Increase Player Speed");
	private JButton strongerCannon = new JButton("Stronger Cannon");
	private JLabel incHealthLabel = new JLabel();
	private JLabel incSpeedLabel = new JLabel();
	private JLabel incCannonLabel = new JLabel();
	private JButton powerUpBack = new JButton("Back");
	private JLabel coinLabel = new JLabel("Coins: " + Health.COINS);
	private JPanel panelHold = new JPanel();
	private JButton backPlayerCustom = new JButton("Back");
	private JPanel playerCustomPanel = new JPanel();
	
	public static int TOTALIMAGES = 10;
	
	private ImageIcon[] skinImages = new ImageIcon[TOTALIMAGES];
	private JButton[] colorChoice = new JButton[TOTALIMAGES];
	
	
	/**
	 * 
	 * @param handler Handler from main class
	 */
	public FrameGame(Handler handler) {
		this.handler = handler;
		
		frame.setSize(600, 600  / 12 * 9);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
				
		skinImages[0] = new ImageIcon(getClass().getResource("Whale.png"));
		skinImages[1] = new ImageIcon(getClass().getResource("Popsicle.png"));
		skinImages[2] = new ImageIcon(getClass().getResource("GradientPurpleRed.png"));
		skinImages[3] = new ImageIcon(getClass().getResource("BlueHexagons.png"));
		skinImages[4] = new ImageIcon(getClass().getResource("CheckeredPattern.png"));
		skinImages[5] = new ImageIcon(getClass().getResource("RedLasers.png"));
		skinImages[6] = new ImageIcon(getClass().getResource("Pacman.png"));
		skinImages[7] = new ImageIcon(getClass().getResource("Mushroom.png"));
		skinImages[8] = new ImageIcon(getClass().getResource("Cupcake.png"));
		skinImages[9] = new ImageIcon(getClass().getResource("PacManMonster.png"));
		
		setSelectDifficulty();
		setStore();
		setPowerUpsPanel();
		setCustomization();
		
		frame.add(powerUpsPanel);
		panelHold = powerUpsPanel;
	}
	
	/**
	 * Updates which panel should be present on the screen.
	 */
	public void checkState() {
		if(Game.gameState == Game.STATE.SelectDifficulty) {
			panelHold.setVisible(false);
			frame.remove(panelHold);
			frame.add(selectDifficulty);
			panelHold = selectDifficulty;
			panelHold.setVisible(true);
		}
		if(Game.gameState == Game.STATE.Store) {
			panelHold.setVisible(false);
			frame.remove(panelHold);
			frame.add(storePanel);
			panelHold = storePanel;
			panelHold.setVisible(true);
		}
		if(Game.gameState == Game.STATE.PowerUps) {
			panelHold.setVisible(false);
			frame.remove(panelHold);
			frame.add(powerUpsPanel);
			panelHold = powerUpsPanel;
			panelHold.setVisible(true);
		
		}
		if(Game.gameState == Game.STATE.PlayerCustomization) {
			panelHold.setVisible(false);
			frame.remove(panelHold);
			frame.add(playerCustomPanel);
			panelHold = playerCustomPanel;
			panelHold.setVisible(true);
		}
		frame.setVisible(true);
	}
	
	/**
	 * Initializes the panel with the powerups.
	 */
	private void setPowerUpsPanel() {
		powerUpsPanel.setLayout(null);
		powerUpsPanel.setBackground(Color.black);
		
		increasedHealth.setBounds(0, 0, 200, 50);
		increasedHealth.addMouseListener(this);
		increasedHealth.setFont(new Font("Times New Roman", Font.BOLD, 20));
		increasedHealth.setToolTipText("Current Health:" + handler.getCurrentPlayer().maxHealth);
		powerUpsPanel.add(increasedHealth);
		incHealthLabel.setBounds(0,50, 200, 40);
		incHealthLabel.setText("         Upgrade for " + handler.getCurrentPlayer().healthMultiplier * 50 + " coins");
		incHealthLabel.setForeground(Color.yellow);
		powerUpsPanel.add(incHealthLabel);
		
		increasedSpeed.setBounds(0, 100, 200, 50);
		increasedSpeed.addMouseListener(this);
		increasedSpeed.setFont(new Font("Times New Roman", Font.BOLD, 20));
		increasedSpeed.setToolTipText("Current Speed: " + handler.getCurrentPlayer().playerSpeed);
		powerUpsPanel.add(increasedSpeed);
		incSpeedLabel.setBounds(0,150, 200, 40);
		incSpeedLabel.setText("         Upgrade for " + handler.getCurrentPlayer().speedMultiplier * 75 + " coins");
		incSpeedLabel.setForeground(Color.yellow);
		powerUpsPanel.add(incSpeedLabel);
		
		strongerCannon.setBounds(0, 200, 200, 50);
		strongerCannon.addMouseListener(this);
		strongerCannon.setFont(new Font("Times New Roman", Font.BOLD, 20));
		strongerCannon.setToolTipText("Current Cannon Strength: " + handler.getCurrentPlayer().bulletSize);
		powerUpsPanel.add(strongerCannon);
		incCannonLabel.setBounds(0,250, 200, 40);
		incCannonLabel.setText("         Upgrade for " + handler.getCurrentPlayer().cannonMultiplier * 100 + " coins");
		incCannonLabel.setForeground(Color.yellow);
		powerUpsPanel.add(incCannonLabel);
		
		powerUpBack.setBounds(200, 300, 200, 50);
		powerUpBack.setFont(new Font("Times New Roman", Font.BOLD, 20));
		powerUpBack.addMouseListener(this);
		powerUpsPanel.add(powerUpBack);
		
		coinLabel.setForeground(Color.YELLOW);
		coinLabel.setBounds(180, 340, 300,50);
		coinLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
		powerUpsPanel.add(coinLabel);
	
	}
	
	/**
	 * Initializes the store panel.
	 */
	private void setStore() {
		storePanel.setLayout(null);
		storePanel.setBackground(Color.yellow);
		
		playerCustomization.setFont(new Font("Times New Roman", Font.BOLD, 35));
		playerCustomization.setBounds(125, 100, 350, 100);
		playerCustomization.addMouseListener(this);
		storePanel.add(playerCustomization);
		
		powerUps.setFont(new Font("Times New Roman", Font.BOLD, 35));
		powerUps.setBounds(125, 200, 350, 100);
		powerUps.addMouseListener(this);
		storePanel.add(powerUps);
		
		backStorePanel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		backStorePanel.setBounds(220, 300, 150, 100);
		backStorePanel.addMouseListener(this);
		storePanel.add(backStorePanel);
		
	}
	
	/**
	 * Initializes the Player customization panel.
	 */
	private void setCustomization() {
		playerCustomPanel.setLayout(new FlowLayout());
		playerCustomPanel.setBackground(Color.black);
		
		backPlayerCustom.addMouseListener(this);
		playerCustomPanel.add(backPlayerCustom);
		
		playerIconCustomization.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
		
		playerNamesCustomization.setSelectedItem(Game.currentPlayer);
		playerNamesCustomization.addActionListener(this);
		playerCustomPanel.add(playerNamesCustomization);
		
		playerCustomPanel.add(playerIconCustomization);
		
		for(int x = 0; x < TOTALIMAGES; x++) {
			colorChoice[x]  = new JButton();
			colorChoice[x].setSize(100,100);
			colorChoice[x].setIcon(skinImages[x]);
			colorChoice[x].addMouseListener(this);
			
			playerCustomPanel.add(colorChoice[x]);
		}	

		
	}
	
	/**
	 * Initializes the panel containing all of the game options.
	 */
	private void setSelectDifficulty() {
		selectDifficulty.setLayout(null);
		selectDifficulty.setBackground(Color.BLACK);
		
		Normal.setBounds(0, 0, 200, 50);
		Normal.addMouseListener(this);
		Normal.setFont(new Font("Times New Roman", Font.BOLD, 35));
		Normal.setToolTipText("Adventure Mode");
		selectDifficulty.add(Normal);
		normalHighScore.setBounds(0,50, 200, 40);
		normalHighScore.setText("    High Score: "  + HighScore.NORMALSCORE +  "       Level: " + HighScore.NORMALLEVEL);
		normalHighScore.setForeground(Color.yellow);
		selectDifficulty.add(normalHighScore);
		
		CastleDefense.setBounds(0, 100, 200, 50);
		CastleDefense.addMouseListener(this);
		CastleDefense.setFont(new Font("Times New Roman", Font.BOLD, 20));
		CastleDefense.setToolTipText("Defend your castle at all costs!");
		selectDifficulty.add(CastleDefense);
		CastleDefenseHS.setBounds(0,150, 200, 40);
		CastleDefenseHS.setText("    High Score: "  + HighScore.CASTLEDEFENSESCORE +  "       Level: " + HighScore.CASTLEDEFENSELEVEL);
		CastleDefenseHS.setForeground(Color.yellow);
		selectDifficulty.add(CastleDefenseHS);
		
		Extreme.setBounds(0, 200, 200, 50);
		Extreme.addMouseListener(this);
		Extreme.setFont(new Font("Times New Roman", Font.BOLD, 35));
		Extreme.setToolTipText("");
		selectDifficulty.add(Extreme);
		ExtremeHS.setBounds(0,250, 200, 40);
		ExtremeHS.setText("    High Score: "  + HighScore.EXTREMESCORE +  "       Level: " + HighScore.EXTREMELEVEL);
		ExtremeHS.setForeground(Color.yellow);
		selectDifficulty.add(ExtremeHS);
		
		Easy.setBounds(200, 0, 200, 50);
		Easy.addMouseListener(this);
		Easy.setFont(new Font("Times New Roman", Font.BOLD, 35));
		Easy.setToolTipText("You're being hunted!");
		selectDifficulty.add(Easy);
		easyHighScore.setBounds(200,50, 200, 40);
		easyHighScore.setText("    High Score: "  + HighScore.EASYSCORE +  "       Level: " + HighScore.EASYLEVEL);
		easyHighScore.setForeground(Color.yellow);
		selectDifficulty.add(easyHighScore);
		
		War.setBounds(200, 100, 200, 50);
		War.addMouseListener(this);
		War.setFont(new Font("Times New Roman", Font.BOLD, 25));
		War.setToolTipText("Rectangular style war");
		selectDifficulty.add(War);
		WarHighScore.setBounds(200,150, 200, 40);
		WarHighScore.setText("    High Score: "  + HighScore.WARSCORE +  "       Level: " + HighScore.WARLEVEL);
		WarHighScore.setForeground(Color.yellow);
		selectDifficulty.add(WarHighScore);
		War.setText("War");
		
		TinyEnemy.setBounds(200, 200, 200, 50);
		TinyEnemy.addMouseListener(this);
		TinyEnemy.setFont(new Font("Times New Roman", Font.BOLD, 25));
		TinyEnemy.setToolTipText("Tiny enemy barage!");
		selectDifficulty.add(TinyEnemy);
		TinyEnemyHighScore.setBounds(200,250, 200, 40);
		TinyEnemyHighScore.setText("    High Score: "  + HighScore.TINYENEMYSCORE +  "   Level: " + HighScore.TINYENEMYLEVEL);
		TinyEnemyHighScore.setForeground(Color.yellow);
		selectDifficulty.add(TinyEnemyHighScore);
		
		back.setBounds(0, 340, 200, 50);
		back.setFont(new Font("Times New Roman", Font.BOLD, 35));
		back.addMouseListener(this);
		selectDifficulty.add(back);
		
		Store.setBounds(200, 340, 200, 50);
		Store.setFont(new Font("Times New Roman", Font.BOLD, 35));
		Store.addMouseListener(this);
		selectDifficulty.add(Store);
		
		playerIcon.setBounds(450, 100, 200,200);
		playerIcon.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
		selectDifficulty.add(playerIcon);
		
		playerNames.setSelectedItem(Game.currentPlayer);
		playerNames.setBounds(400, 275, 200,50);
		playerNames.addActionListener(this);
		selectDifficulty.add(playerNames);
		
		selectDifficulty.setVisible(true);
	}
	
	/**
	 * 
	 * @param boolean True for frame to be set visible, false for it to be removed from screen.
	 */
	public void setFrameVisibility(Boolean b) {
		frame.setVisible(b);
		if(b) {
			normalHighScore.setText("    High Score: "  + HighScore.NORMALSCORE +  "     Level: " + HighScore.NORMALLEVEL);
			CastleDefenseHS.setText("    High Score: "  + HighScore.CASTLEDEFENSESCORE +  "     Level: " + HighScore.CASTLEDEFENSELEVEL);
			ExtremeHS.setText("    High Score: "  + HighScore.EXTREMESCORE +  "     Level: " + HighScore.EXTREMELEVEL);
			WarHighScore.setText("    High Score: "  + HighScore.WARSCORE +  "     Level: " + HighScore.WARLEVEL);
			easyHighScore.setText("    High Score: "  + HighScore.EASYSCORE +  "     Level: " + HighScore.EASYLEVEL);
			TinyEnemyHighScore.setText("     High Score: " + HighScore.TINYENEMYSCORE + "     Level: " + HighScore.TINYENEMYLEVEL);
			incHealthLabel.setText("         Upgrade for " + handler.getCurrentPlayer().healthMultiplier * 50 + " coins");
			incSpeedLabel.setText("         Upgrade for " + handler.getCurrentPlayer().speedMultiplier * 75 + " coins");
			incCannonLabel.setText("         Upgrade for " + handler.getCurrentPlayer().cannonMultiplier * 100 + " coins");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		coinLabel.setText("Coins: " + Health.COINS);
		Object key = e.getSource();
		
		if(key == powerUpBack) {
			powerUpBack.setSize(200,50);
			Game.gameState = Game.STATE.Store;
			checkState();
		}
		if(key == TinyEnemy){
			TinyEnemy.setSize(200,50);
			Game.currentPlayer = 1;
			handler.getCurrentPlayer().HEALTH = handler.getCurrentPlayer().maxHealth;
			Game.gameState = Game.STATE.Game;
			Game.spState = Game.SPAWNERSTATE.TinyEnemySpawner;
			handler.object.clear();
			System.gc();
			
			frame.setVisible(false);
		}
		if(e.getSource() == Normal) {
			Normal.setSize(200,50);
			Game.gameState = Game.STATE.Game;
			Game.spState = Game.SPAWNERSTATE.Spawner;
			handler.getCurrentPlayer().HEALTH = handler.getCurrentPlayer().maxHealth;
			handler.object.clear();
			System.gc();
			//handler.addPlayerObject(new Player1(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler, skin));
			frame.setVisible(false);
		}
		if(e.getSource() == CastleDefense) {
			CastleDefense.setSize(200,50);
			Game.gameState = Game.STATE.Game;
			Game.spState = Game.SPAWNERSTATE.CastleDefenseSpawner;
			handler.getCurrentPlayer().HEALTH = handler.getCurrentPlayer().maxHealth;
			handler.object.clear();
			System.gc();
			//handler.addPlayerObject(new Player1(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler, skin));
			frame.setVisible(false);
		}
		if(e.getSource() == Extreme) {
			Extreme.setSize(200,50);
			Game.gameState = Game.STATE.Game;
			Game.spState = Game.SPAWNERSTATE.ExtremeSpawner;
			handler.getCurrentPlayer().HEALTH = handler.getCurrentPlayer().maxHealth;
			handler.object.clear();
			System.gc();
			//handler.addPlayerObject(new Player1(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler, skin));
			frame.setVisible(false);
		}
		if(e.getSource() == Easy) {
			Easy.setSize(200,50);
			Game.gameState = Game.STATE.Game;
			Game.spState = Game.SPAWNERSTATE.TrackerSpawner;
			handler.getCurrentPlayer().HEALTH = handler.getCurrentPlayer().maxHealth;
			handler.object.clear();
			System.gc();
			//handler.addPlayerObject(new Player1(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler, skin));
			frame.setVisible(false);
		}
		if(e.getSource() == War) {
			Game.gameState = Game.STATE.Game;
			Game.spState = Game.SPAWNERSTATE.WarSpawner;
			handler.getCurrentPlayer().HEALTH = handler.getCurrentPlayer().maxHealth;
			handler.object.clear();
			System.gc();
			//handler.addPlayerObject(new Player1(Game.WIDTH / 2, Game.HEIGHT / 2, ID.Player, handler, skin));
			frame.setVisible(false);
		}
		if(e.getSource() == back) {
			back.setSize(200,50);
			Game.gameState = Game.STATE.Menu;
			frame.remove(panelHold);
			frame.setVisible(false);
		}
		if(e.getSource() == playerCustomization) {
			playerCustomization.setSize(350, 100);
			Game.gameState = Game.STATE.PlayerCustomization;
			frame.remove(panelHold);
			frame.setVisible(false);
			checkState();
		}
		if(key == Store) {
			Store.setSize(200,50);
			Game.gameState = Game.STATE.Store;
			checkState();
		}
		for(int y = 0; y < TOTALIMAGES; y++) {
			if(key == colorChoice[y]) {
				handler.getCurrentPlayer().setSkin(y);
				playerIcon.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
				playerIconCustomization.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
			}
		}
		
		if(key == powerUps) {
			powerUps.setSize(350,100);
			Game.gameState = Game.STATE.PowerUps;
			checkState();
		}
		if(key == backPlayerCustom) {
			Game.gameState = Game.STATE.Store;
			checkState();
		}
		if(key == backStorePanel) {
			backStorePanel.setSize(150,100);
			Game.gameState = Game.STATE.SelectDifficulty;
			checkState();
		}
		if(key == increasedHealth && Health.COINS >= handler.getCurrentPlayer().healthMultiplier  * 50) {
			increasedHealth.setSize(200,50);
			
			if(handler.getCurrentPlayer().maxHealth < 400) {
				incHealthLabel.setText("         Upgrade for " + handler.getCurrentPlayer().healthMultiplier * 50 + " coins");
				increasedHealth.setToolTipText("Current Health:" + handler.getCurrentPlayer().maxHealth);
				handler.getCurrentPlayer().maxHealth += 25;
				Health.COINS -= handler.getCurrentPlayer().healthMultiplier * 50;
				handler.getCurrentPlayer().healthMultiplier += 1;
				coinLabel.setText("Coins: " + Health.COINS);
			}
			if(handler.getCurrentPlayer().maxHealth >= 400) {
				incHealthLabel.setText("Health Maxed");
				incHealthLabel.removeMouseListener(this);
				increasedHealth.setToolTipText("Current Health:" + handler.getCurrentPlayer().maxHealth);
			}
		}
		if(key == increasedSpeed && Health.COINS >= handler.getCurrentPlayer().speedMultiplier  * 75) {
			
			if(handler.getCurrentPlayer().playerSpeed < 13) {
				increasedSpeed.setSize(200,50);
				incSpeedLabel.setText("         Upgrade for " + handler.getCurrentPlayer().speedMultiplier * 75 + " coins");
				increasedSpeed.setToolTipText("Current Speed: " + handler.getCurrentPlayer().playerSpeed);
				handler.getCurrentPlayer().playerSpeed += 1;
				Health.COINS -= handler.getCurrentPlayer().speedMultiplier * 75;
				handler.getCurrentPlayer().speedMultiplier += 1;
				coinLabel.setText("Coins: " + Health.COINS);
			}
			if(handler.getCurrentPlayer().playerSpeed >= 13) {
				incSpeedLabel.setText("Player Speed Maxed");
				increasedSpeed.setToolTipText("Current Speed: " + handler.getCurrentPlayer().playerSpeed);
				incSpeedLabel.removeMouseListener(this);
			}
			
		}
		if(key == strongerCannon && Health.COINS >= handler.getCurrentPlayer().cannonMultiplier  * 100) {
			if(handler.getCurrentPlayer().bulletSize < 13) {
				strongerCannon.setSize(200,50);
				incCannonLabel.setText("         Upgrade for " +  handler.getCurrentPlayer().cannonMultiplier * 100 + " coins");
				strongerCannon.setToolTipText("Current Cannon Strength: " + handler.getCurrentPlayer().bulletSize);
				handler.getCurrentPlayer().bulletSize += 1;
				Health.COINS -= handler.getCurrentPlayer().cannonMultiplier * 100;
				handler.getCurrentPlayer().cannonMultiplier += 1;
				coinLabel.setText("Coins: " + Health.COINS);
			}
			if(handler.getCurrentPlayer().bulletSize >= 13) {
				incCannonLabel.setText("Cannon Strength Maxed");
				strongerCannon.setToolTipText("Current Cannon Strength: " + handler.getCurrentPlayer().bulletSize);
			}
		}
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == Store) {
			Store.setForeground(Color.BLUE);
			growComponent(Store);
		}
		if(e.getSource() == Normal ) {
			Normal.setForeground(Color.RED);
			growComponent(Normal);
		}
		if(e.getSource() == CastleDefense){
			CastleDefense.setForeground(Color.RED);
			growComponent(CastleDefense);
		}
		if( e.getSource() == back ){
			back.setForeground(Color.BLUE);
			growComponent(back);
		}
		if( e.getSource() == powerUpBack ){
			powerUpBack.setForeground(Color.YELLOW);
			growComponent(powerUpBack);
		}
		if(e.getSource() == Extreme) {
			Extreme.setForeground(Color.RED);
			growComponent(Extreme);
		}
		if(e.getSource() == Easy) {
			Easy.setForeground(Color.RED);
			growComponent(Easy);
		}
		if(e.getSource() == playerCustomization) {
			playerCustomization.setForeground(Color.RED);
			growComponent(playerCustomization);
		}
		if(e.getSource() == War) {
			War.setForeground(Color.RED);
			growComponent(War);
		}
		if(e.getSource() == TinyEnemy) {
			TinyEnemy.setForeground(Color.RED);
			growComponent(TinyEnemy);
		}
		if(e.getSource() == powerUps) {
			powerUps.setForeground(Color.RED);
			growComponent(powerUps);
		}
		if(e.getSource() ==  increasedHealth) {
			if(Health.COINS >= handler.getCurrentPlayer().healthMultiplier * 50) {
				increasedHealth.setForeground(Color.RED);
				growComponent(increasedHealth);
			}
			else {
				increasedHealth.setEnabled(false);
				incHealthLabel.setText("         You need " + handler.getCurrentPlayer().healthMultiplier * 50 + " coins!");
			}
		}
		if(e.getSource() ==  increasedSpeed) {
			if(Health.COINS >= handler.getCurrentPlayer().speedMultiplier * 75) {
				increasedSpeed.setForeground(Color.RED);
				growComponent(increasedSpeed);
			}
			else {
				increasedSpeed.setEnabled(false);
				incSpeedLabel.setText("         You need " + handler.getCurrentPlayer().speedMultiplier * 75 + " coins!");
			}
		}
		if(e.getSource() ==  strongerCannon) {
			if(Health.COINS >= handler.getCurrentPlayer().cannonMultiplier * 100) {
				strongerCannon.setForeground(Color.RED);
				growComponent(strongerCannon);
			}
			else {
				strongerCannon.setEnabled(false);
				incCannonLabel.setText("         You need " + handler.getCurrentPlayer().cannonMultiplier * 100 + " coins!");
			}
		}
	}
	

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == Normal ) {
			Normal.setForeground(null);
			Normal.setSize(200,50);
		}
		if(e.getSource() == Store) {
			Store.setForeground(null);
			Store.setSize(200,50);
		}
		if(e.getSource() == War) {
			War.setForeground(null);
			War.setSize(200,50);
		}
		if(e.getSource() == TinyEnemy) {
			TinyEnemy.setForeground(null);
			TinyEnemy.setSize(200,50);
		}
		if(e.getSource() == CastleDefense){
			CastleDefense.setForeground(null);
			CastleDefense.setSize(200,50);
		}
		if( e.getSource() == back ){
			back.setForeground(null);
			back.setSize(200,50);
		}
		if( e.getSource() == powerUpBack ){
			powerUpBack.setForeground(null);
			powerUpBack.setSize(200,50);
		}
		if(e.getSource() == Extreme) {
			Extreme.setForeground(null);
			Extreme.setSize(200,50);
		}
		if(e.getSource() == Easy) {
			Easy.setForeground(null);
			Easy.setSize(200,50);
		}
		if(e.getSource() == playerCustomization) {
			playerCustomization.setForeground(null);
			playerCustomization.setSize(350,100);
		}
		if(e.getSource() == powerUps) {
			powerUps.setForeground(null);
			powerUps.setSize(350,100);
		}
		if(e.getSource() ==  increasedHealth) {
			increasedHealth.setEnabled(true);
			increasedHealth.setForeground(null);
			incHealthLabel.setText("         Upgrade for " + handler.getCurrentPlayer().healthMultiplier * 50 +" coins!");
			if(Health.COINS >= handler.getCurrentPlayer().healthMultiplier * 50) {
				increasedHealth.setSize(200,50);
			}
		
		}
		if(e.getSource() ==  increasedSpeed) {
			increasedSpeed.setForeground(null);
			increasedSpeed.setEnabled(true);
			incSpeedLabel.setText("         Upgrade for " + handler.getCurrentPlayer().speedMultiplier * 75 + " coins!");
			if(Health.COINS > handler.getCurrentPlayer().speedMultiplier * 75) {
				increasedSpeed.setSize(200,50);
			}
		
		}
		if(e.getSource() ==  strongerCannon) {
			strongerCannon.setForeground(null);
			strongerCannon.setEnabled(true);
			incCannonLabel.setText("         Upgrade for " + handler.getCurrentPlayer().cannonMultiplier * 100 + " coins!");
			
			if(Health.COINS > handler.getCurrentPlayer().cannonMultiplier * 100) {
				strongerCannon.setSize(200,50);
			}
		}
	}
	private void growComponent(JButton button) {
		button.setSize(button.getWidth() + 7, button.getHeight() + 7);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == playerNames ) {
			String name = (String) playerNames.getSelectedItem();
			if(name.equals("Normal Player")) {
				Game.currentPlayer = 0;
				playerIcon.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
			}
			if(name.equals("Cannon Blaster")){
				Game.currentPlayer = 1;
				playerIcon.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
			}
			if(name.equals("Double Fire")) {
				Game.currentPlayer = 2;
				playerIcon.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
			}
		}
		if(e.getSource() == playerNamesCustomization) {
			String name = (String) playerNamesCustomization.getSelectedItem();
			if(name.equals("Normal Player")) {
				Game.currentPlayer = 0;
				playerIconCustomization.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
			}
			if(name.equals("Cannon Blaster")){
				Game.currentPlayer = 1;
				playerIconCustomization.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
			}
			if(name.equals("Double Fire")) {
				Game.currentPlayer = 2;
				playerIconCustomization.setIcon(skinImages[handler.getCurrentPlayer().currentSkin]);
			}
		}
	}
}
