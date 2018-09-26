package JapTyperGo;

import java.awt.Graphics;

public abstract class GameObject {

		protected int x, y;
		protected ID id;
		protected int velX, velY;
		protected String h;
		
	public GameObject(int x ,int y, ID id, String h) {
		this.x =x;
		this.y= y;
		this.id = id;
		
	}
	public abstract void tick();
	public abstract void render (Graphics g);

	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	
	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public String getH() {
		return h;
	}
	public void setH(String h) {
		this.h = h;
	}
	
	
}
