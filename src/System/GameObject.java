package System;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Parent class for any object that goes on the screen.
 *
 */
public abstract class GameObject {
	protected int x, y;
	protected ID id;
	protected int vX, vY; // velocity x, velocity y

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;

	}

	/**
	 * Updates Player Location
	 */
	public abstract void tick();

	/**
	 * Show user on the screen
	 * @param g Graphics object used to display 
	 * objects on the screen
	 */
	public abstract void render(Graphics g);

	/**
	 * 
	 * @return Rectangle at the x,y coordinate of the enemy, with the same 
	 * width and height
	 */
	public abstract Rectangle getBounds();

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
}
