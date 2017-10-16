package Enemy;
import System.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Creates an enemy that changes velocity every time it 
 * hits a wall.
 * @author Navin Ramsaroop
 *
 */
public class ChangeUpEnemy extends GameObject{
	Random r = new Random();
	public ChangeUpEnemy(int xTemp, int yTemp, ID idTemp) {
		super(xTemp, yTemp, idTemp);
			
		vX = 5;
		vY = 5;
	}

	@Override
	public void tick() {
		x += vX;
		y += vY;
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 48);
		
		if( y <= 0 || y >= Game.HEIGHT - 48) {
			if(vY < 0) {
				vY = r.nextInt(8)  + 5;	
			}
			else if(vY > 0) {
				 vY = -(r.nextInt(8)  + 5);
			}
				
			
		}
		
		if( x <= 0 || x >= Game.WIDTH - 32) {
			if(vX < 0) {
				vX = r.nextInt(8)  + 5;	
			}
			else if(vX > 0) {
				 vX = -(r.nextInt(8)  + 5);
			}
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 15, 15);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
		
	}

	
}
