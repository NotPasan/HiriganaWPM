package JapTyperGo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game extends Canvas implements Runnable    {



	private static final long serialVersionUID = -3373516989987479392L;
	private static final int WIDTH = 640, HEIGHT = WIDTH ;
	private static final int Delay =500;

	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;

	static ArrayList<String> HLIST = new ArrayList<String>();


	public Game() {

		new Window(WIDTH, HEIGHT,"EPIC",this);

		handler = new Handler();
		r= new Random();

		for(int i=0; i<1000;i++) {

			handler.addObject(new Hiragana (r.nextInt(WIDTH),0,ID.Hiragana,HLIST.get(r.nextInt(46))));
			try {
				Thread.sleep(Delay);//delay for x/1000 seconds
			} catch (InterruptedException e) {

				e.printStackTrace();
			} 
		}
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


	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		
		double numOfTicks = 60.0;
		double skipTicks = 1.0e9 /60;
		double delta = 0;
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

		handler.render(g);

		g.dispose();
		bs.show(); 


	} 



	private void tick() {

		handler.tick();
	}



	public static void main(String args[]) {

		// file = new File(".");
		// 	for(String fileNames : file.list()) System.out.println(fileNames);

		Scanner s;
		try {
			s = new Scanner(new File("HiraList.txt"));
			while (s.hasNext()){
				HLIST.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		new Game();

	}


}
