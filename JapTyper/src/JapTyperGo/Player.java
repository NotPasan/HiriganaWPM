package JapTyperGo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {

	Random r = new Random();
	public Player(int x, int y, ID id) {
		super(x,y,id,"No"); 
		
		velX=r.nextInt(5); velY = r.nextInt(8);
	}
	
	public void tick () {
		
		x+= velX;
		y+= velY;
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 32, 32);
	}
}
