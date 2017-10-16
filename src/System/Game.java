package System;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JOptionPane;

import Player.CannonBlaster;
import Player.DoubleFire;
import Player.MachineGun;
import Player.Player1;
import Player.ScatterFire;
import Spawners.BasicHealthSpawner;
import Spawners.CastleDefense;
import Spawners.EasySpawner;
import Spawners.ExtremeSpawner;
import Spawners.Spawner;
import Spawners.TrackerSpawner;
import Spawners.WarSpawner;

/**
 * MAIN CLASS
 * Starts up the game, sets up save state, and contains main
 * game loop.
 * @author Navin Ramsaroop
 *
 */
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Thread thread; 
	
	private boolean running = false;
	
	public static int WIDTH;
	public static int HEIGHT;
	
	//Spawners:
	private Spawner spawner;
	private ExtremeSpawner extremeSpawner;
	private CastleDefense cd;
	private EasySpawner easyspawner;
	private TrackerSpawner trackerspawner;
	private WarSpawner warspawner;
	private BasicHealthSpawner basichealthspawner;
	
	//Necessary Game Classes
	private Handler handler;
	private Health health;	
	private Menu menu;
	private Mouse mouse;
	private PauseScreen pause;
	private End end;
	private SaveState ss;
	private FrameGame frameGame;
	private Skins skin;
	private InGameStore igs;
	public static String saveName;
	public static int currentPlayer = 0;
	private static final String newLine = "\n";
	
	
	public static enum  STATE {
		Game, Menu, Pause, SelectDifficulty, End, Store, PlayerCustomization, PowerUps
	};
	public static enum  SPAWNERSTATE {
		Spawner, ExtremeSpawner, Easy, WarSpawner, EasySpawner, TinyEnemySpawner, TrackerSpawner, CastleDefenseSpawner
	};
	
	public static SPAWNERSTATE spState = SPAWNERSTATE.Spawner; //Current state of the spawner
	
	public static STATE gameState = STATE.Menu; //Current state of the game
	
	public Game() {
		
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			
			WIDTH = (int) width;
			HEIGHT = (int) height;
		
			handler = new Handler();
			menu = new Menu(handler);
			pause = new PauseScreen();
			end = new End();
			ss = new SaveState();
			health = new Health(handler);
			skin = new Skins();	
			igs = new InGameStore(); 
			
			handler.addPlayerObject(new Player1(Game.WIDTH/2, Game.HEIGHT /2, ID.Player, handler, skin));
			handler.addPlayerObject(new CannonBlaster(Game.WIDTH / 2, Game.HEIGHT/ 2, ID.DoubleFire, handler, skin));
			handler.addPlayerObject(new DoubleFire(Game.WIDTH/2, Game.HEIGHT /2, ID.CannonBlaster, handler, skin));
			handler.addPlayerObject(new ScatterFire(Game.WIDTH/2, Game.HEIGHT /2, ID.ScatterFire, handler, skin));
			handler.addPlayerObject(new MachineGun(Game.WIDTH/2, Game.HEIGHT/2, ID.MachineGun, handler, skin));
			
			String[] options = {"Exit", "Load Previous Game", "New Game" };
			int num = JOptionPane.showOptionDialog(null, "Welcome to Outbreak!" + newLine + "How would you like to proceed?", "Welcome!", 1, 3, null, options, 1);
			if(num == 1) {

				saveName = JOptionPane.showInputDialog("Please type in your name exactly as you did last time.");
				
				if(saveName != null && ss.checkExistence(saveName)) {						
						ss.readFile(saveName, handler);
						JOptionPane.showMessageDialog(null, "Welcome " + saveName );
				}
				else if(saveName == null){
					JOptionPane.showMessageDialog(null, "Name not found. Welcome guest!");
					saveName = "guest";
				}
				else if(!ss.checkExistence(saveName)) {
					while(!ss.checkExistence(saveName)) {
						saveName = JOptionPane.showInputDialog(null, "Name not found. Please try again. Type \"New Game\" to "+ "\n" + " start a new game, or \"Guest\" to play as a guest. (Do not include quotation marks)");
						if(saveName == null){
							JOptionPane.showMessageDialog(null, "Name not found. Welcome guest!");
							saveName = "guest";
							break;
						}
						else if(saveName.toLowerCase().equals("guest")) {
							JOptionPane.showMessageDialog(null, "Welcome guest!");
							break;
						}
						else if(saveName.toLowerCase().equals("new game")){
							saveName = JOptionPane.showInputDialog("Please type in your name for your new profile.");
							if(saveName == null){
								JOptionPane.showMessageDialog(null, "Name not found. Welcome guest!");
								saveName = "guest";
								break;
							}
							while(ss.checkExistence(saveName)) {
								saveName = JOptionPane.showInputDialog("Name already exists. Please pick a more distinct name.");
								if(saveName == null){
									JOptionPane.showMessageDialog(null, "Name not found. Welcome guest!");
									saveName = "guest";
									break;
								}
								if(saveName.toLowerCase().equals("guest")) {
									JOptionPane.showMessageDialog(null, "Welcome guest!");
									break;
								}
							}
							break;
						}
						else if(ss.checkExistence(saveName)) {
							ss.readFile(saveName, handler);
							JOptionPane.showMessageDialog(null, "Welcome " + saveName );
							break;
						}
						
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Welcome guest!");
				}
			}
			else if(num == 2) {
				
				saveName = JOptionPane.showInputDialog("Please type in your name for your new profile,"  + "\n"+ " or \"Guest\" to play as guest.");
				if(saveName == null) {
					JOptionPane.showMessageDialog(null, "Welcome guest!");
					saveName = "guest";
				}
				while(ss.checkExistence(saveName) && saveName != "guest") {
					saveName = JOptionPane.showInputDialog("Name already exists. Please pick a more distinct name.");
					if(saveName.toLowerCase().equals("guest")) {
						JOptionPane.showMessageDialog(null, "Welcome guest!");
						break;
					}
				}
				if(!saveName.toLowerCase().equals("guest")) {
					JOptionPane.showMessageDialog(null, "Welcome " + saveName);
				}

			}
			else {
				System.exit(0);
			}
			
			frameGame = new FrameGame(handler);
			
			this.addKeyListener(new KeyInput(handler, this));
						
			mouse  = new Mouse(handler, this, pause, health, frameGame);
			
			this.addMouseListener(mouse);

			new Window(WIDTH, HEIGHT, "", this);
	
			spawner = new Spawner(handler, health);
			extremeSpawner  = new ExtremeSpawner(handler, health);
			cd = new CastleDefense(handler, health);
			easyspawner = new EasySpawner(handler, health);
			warspawner = new WarSpawner(handler, health);
			trackerspawner = new TrackerSpawner(handler, health);
			basichealthspawner = new BasicHealthSpawner(handler,health);
			
	}
	public synchronized void start(){
		thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	public void run() {
		this.requestFocus();
		
		long lastTime = System.nanoTime();
		double ns = 20000000; // amountOfTicks 
		double delta = 0; 
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running){
				render();
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer +=1000;
			}
		}
		stop(); 
	}
	
	private void tick(){
		if(gameState == STATE.Menu) {
			handler.tick();
		}
		else if(gameState == STATE.Game){
			handler.tick();
			health.tick();
			if(spState == SPAWNERSTATE.Spawner) {
				spawner.tick();
			}
			else if(spState == SPAWNERSTATE.ExtremeSpawner){
				extremeSpawner.tick();
			}
			else if(spState == SPAWNERSTATE.CastleDefenseSpawner) {
				cd.tick();
			}
			else if(spState == SPAWNERSTATE.EasySpawner) {
				easyspawner.tick();
			}
			else if(spState == SPAWNERSTATE.WarSpawner) {
				warspawner.tick();
			}
			else if(spState == SPAWNERSTATE.TinyEnemySpawner) {
				basichealthspawner.tick();
			}
			else if(spState == SPAWNERSTATE.TrackerSpawner) {
				trackerspawner.tick();
			}
		}
		
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == STATE.Menu) {
			menu.render(g);
			handler.render(g);
		}
		else if(gameState == STATE.Game) {
			handler.render(g);
			health.render(g);
			if(spState == SPAWNERSTATE.CastleDefenseSpawner) {
				cd.render(g);
			}
		}
		
		else if(gameState == STATE.End){
			end.render(g);
		}
		if( gameState == STATE.Pause) {
			if(spState == SPAWNERSTATE.CastleDefenseSpawner) {
				igs.render(g);
			}
			else {
				pause.render(g);
			}
		}
			
		g.dispose();
		bs.show();
		
	}
	/**
	 * Save game
	 */
	public void save() {
		ss.saveFile(saveName, handler);
	}
	/**
	 * Load game
	 */
	public void load() {
		ss.readFile(saveName, handler);
	}
	
	/**
	 * Clamps the value num between the min
	 * and the max 
	 * @param num Number to be clamped
	 * @param min Minimum Value
	 * @param max Maximum Value
	 * @return
	 */
	public static int clamp (int num, int min, int max) {
		if(num >= max) {
			return num = max; 
		}
		else if(num <= min){
			return num = min;
		}
		else{
			return num;
		}
	}
	public synchronized void stop() {
		try{
			thread.join();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args0) {
		new Game();
	}

	
	
}
