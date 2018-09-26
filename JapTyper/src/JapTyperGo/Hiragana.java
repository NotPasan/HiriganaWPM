package JapTyperGo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;


public class Hiragana extends GameObject {
	Random r = new Random();
	String Temp;
	public Hiragana(int x, int y, ID id, String h) {
		super(x, y, id, h);
		Temp=h;
		velX=0; velY = (8);
	}

	public void tick () {
		x+= velX;
		y+= velY;
	}
	
	public void render(Graphics g) {
		
		Font font = new Font ("Minchō", Font.BOLD + Font.PLAIN,50);
	   

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(Temp,x ,y );
	}
	
}
