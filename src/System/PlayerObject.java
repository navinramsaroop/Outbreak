package System;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/**
 * Parent to all of the player classes 
 * @author Navin Ramsaroop
 *
 */
public abstract class PlayerObject {
	protected int x, y;
	protected ID id;
	protected int vX, vY; // velocity x, velocity y

	protected Color color = Color.blue;

	protected int HEALTH;
	protected int maxHealth;

	protected int bulletSize;

	protected int playerSpeed;

	protected int healthMultiplier;
	protected int speedMultiplier;
	protected int cannonMultiplier;
	protected int reload;
	protected int reloadMax;
	protected int currentSkin;
	protected boolean specialActivated;

	protected double specialReloadMax;
	protected double specialReload;

	protected int amunitionMax;
	protected int amunitionCurrent;

/**
 * 
 * @param x X coordinate
 * @param y Y coordinate
 * @param id Player Identifier
 */
	public PlayerObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	/*
	 * Update Player Location and data
	 */
	public abstract void tick();

	/**
	 * Display player on screen
	 * @param g
	 */
	public abstract void render(Graphics g);

	/**
	 * Return rectangle at player location with player width and height
	 * @return
	 */
	public abstract Rectangle getBounds();

	/**
	 * Sends bullets in direction specified
	 * @param direction (Left, Right, Up, Down)
	 */
	public abstract void bulletSpawn(String direction);

	public int getAmunitionMax() {
		return amunitionMax;
	}

	public void setAmunitionMax(int num) {
		amunitionMax = num;
	}

	public int getAmunition() {
		return amunitionCurrent;
	}
	
	public void setAmunition(int a) {
		amunitionCurrent = a;
	}
	
	public void setSpecialReload(int num) {
		specialReload = num; 
	}

	public boolean getSpecialActivated() {
		return specialActivated;
	}

	public void setSpecialActivated(boolean temp) {
		specialActivated = temp;
	}

	public int getReload() {
		return reload;
	}

	public void setReload(int num) {
		reload = num;
	}

	public int getHealth() {
		return HEALTH;
	}

	public void setHealth(int h) {
		HEALTH = h;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		color = c;
	}

	public int getSkin() {
		return currentSkin;
	}

	public void setSkin(int num) {
		currentSkin = num;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int mh) {
		maxHealth = mh;
	}

	public int getBulletSize() {
		return bulletSize;
	}

	public void setBulletSize(int bs) {
		bulletSize = bs;
	}

	public int getPlayerSpeed() {
		return playerSpeed;
	}

	public void setPlayerSpeed(int speed) {
		playerSpeed = speed;
	}

	public int getCoinMultiplier() {
		return healthMultiplier;
	}

	public void setHealthMultiplier(int cm) {
		healthMultiplier = cm;
	}

	public int getSpeedMultiplier() {
		return speedMultiplier;
	}

	public void setSpeedMultiplier(int sp) {
		speedMultiplier = sp;
	}

	public int getCannonMultiplier() {
		return cannonMultiplier;
	}

	public void setCannonMultiplier(int cannonM) {
		cannonMultiplier = cannonM;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public ID getID() {
		return id;
	}

	public void setVelocityX(int vX) {
		this.vX = vX;
	}

	public int getVelocityX() {
		return vX;
	}

	public void setVelocityY(int vY) {
		this.vY = vY;
	}

	public int getVelocityY() {
		return vY;
	}

	public int getReloadMax() {
		return reloadMax;
	}
}
