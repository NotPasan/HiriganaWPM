package JapTyperGo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable    {

	
	
	private static final long serialVersionUID = -3373516989987479392L;
	private static final int WIDTH = 640, HEIGHT = WIDTH ;

	private Thread thread;
	private boolean running = false;
	
	public Game() {
		new Window(WIDTH, HEIGHT,"EPIC",this);
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//end of synch stop
	
	
	/**
	 * Game L00P star
	 * @
	 */
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double numOfTicks = 60.0;
		double skipTicks = 1.0e9 /60;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) { //RUNNING L
			long now = System.nanoTime();
			delta += (now - lastTime) / skipTicks;
			lastTime = now;
			
			while (delta>=1) { //DELTA L
				tick();
				delta --;
				
			}//END of DELTA
			
			if (running) {
				render();
			}
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: "+ frames );
				frames = 0;
			}
			
		}//END of RUNNING L
		stop();
	}//end of run 
	
	
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,0, WIDTH, HEIGHT);
		
		g.dispose();
		bs.show(); 
	} 
	
	
	
	private void tick() {
		
		}
	
	
	
	public static void main(String args[]) {
		new Game();
	}
	
	
}
