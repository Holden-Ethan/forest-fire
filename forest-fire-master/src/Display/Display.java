package Display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import Game.Main;
import Game.forest_generation;

public class Display {
	public int width, height;   //self explanatory variables
	public JFrame jf; 
	BufferStrategy bs;
	Graphics g;
	Canvas c; 
	BufferedImage t;
	public Display (int wid, int hgt, String name) {
		width = wid; height = hgt;
		jf = new JFrame(name);	//makes screen, sets it up, etc.
		Dimension d = new Dimension(width,height);
		jf.setPreferredSize(d);
		jf.setVisible(true);
		jf.setSize(d);
		jf.setMaximumSize(d);
		jf.setMinimumSize(d);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = new Canvas();
		c.setPreferredSize(d);
		c.setMinimumSize(d);
		c.setMaximumSize(d);
		c.setSize(d);
		jf.add(c);
		jf.pack();
		c.addKeyListener(Main.kl);	//keyboard input
		jf.addKeyListener(Main.kl);  
		jf.addMouseListener(Main.kl);
		jf.addMouseMotionListener(Main.kl);
		jf.addMouseWheelListener(Main.kl);
		c.addMouseWheelListener(Main.kl);
		c.addMouseListener(Main.kl);
		c.addMouseMotionListener(Main.kl);
	
	}
	public void render() {	//was camera moved this tick
		bs = c.getBufferStrategy();
		if(bs==null) {
			c.createBufferStrategy(2);	//rendering stuff	ETHAN: your computer can only handle 2
			return;
		}
		g = bs.getDrawGraphics();
		//draw	//render trees if camera was moved
			g.clearRect(0, 0, jf.getWidth(), jf.getHeight());
			for(int i = 0; i<forest_generation.forest.length; i++) {
				if(forest_generation.forest[i]!=null) {
					//forest_generation.forest[i].x+48)*Main.Player.zooom)
					//-Main.Player.y <= (int)((forest_generation.forest[i].y+48)*Main.Player.zooom)
					//Main.Player.x + Main.renderer.width-10 >= (int)((forest_generation.forest[i].x)*Main.Player.zooom)
					//-Main.Player.y + Main.renderer.height - 10 >= (int)((forest_generation.forest[i].y)*Main.Player.zooom)
					if((int)(Main.Player.x*Main.Player.zooom) <= (int)((forest_generation.forest[i].x+48)*Main.Player.zooom) && (int)(-Main.Player.y*Main.Player.zooom) <= (int)((forest_generation.forest[i].y+48)*Main.Player.zooom) && (int)(Main.Player.x*Main.Player.zooom) + jf.getWidth()-10 >= (int)((forest_generation.forest[i].x)*Main.Player.zooom) && (int)(-Main.Player.y*Main.Player.zooom) + jf.getHeight() - 10 >= (int)((forest_generation.forest[i].y)*Main.Player.zooom)) {
							forest_generation.forest[i].render(g); 
					}
				}
			}
		bs.show();
		g.dispose();
	}
}	