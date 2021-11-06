package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

public class renderer extends Thread {
	public static int width;
	public static int height;
	public static long[] avrage = new long[200];
	int n = 0; 
	public int buffers;
	float defaultw, defaulth;
	JFrame jf;
	Canvas c;
	BufferStrategy bs;
	Graphics2D g;
	IM im;
 	Camera cam;
 	String name;
	public List<ArrayList<GameObject>> layers = new ArrayList<ArrayList<GameObject>>();
	public static double[][] indv_layer_scaling;
	public static double[][] indv_layer_offset;
	public static boolean[] active_layers;
	GameObject currentgo;
	public renderer (int wid, int hgt, String name, Camera cam, IM im, int buffers) {
		width = wid; height = hgt; this.cam = cam; this.im = im; defaultw = wid; defaulth = hgt;
		this.buffers = buffers; this.name = name;
		jf = new JFrame(name);	//makes screen, sets it up, etc.
		Dimension d = new Dimension(width,height);
		indv_layer_scaling = new double[cam.layers][2];
		indv_layer_offset = new double[cam.layers][2];
		active_layers = new boolean[cam.layers];
		Arrays.fill(active_layers, true);
		jf.setPreferredSize(d);
		jf.setVisible(true);
		jf.setSize(d);
		jf.setMinimumSize(d);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = new Canvas();
		c.setPreferredSize(d);
		c.setMinimumSize(d);
		c.setSize(d);
		c.setBackground(new Color(10,10,10));
		jf.add(c);
		jf.pack();
		c.addKeyListener(im);	//keyboard input
		jf.addKeyListener(im);  
		jf.addMouseListener(im);
		jf.addMouseMotionListener(im);
		jf.addMouseWheelListener(im);
		c.addMouseWheelListener(im);
		c.addMouseListener(im);
		c.addMouseMotionListener(im);	
		//jf.setCursor(Cursor.);
		for (int i = 0; i < cam.layers; i++) {
			layers.add(new ArrayList<GameObject>());
			indv_layer_scaling[i][0] = 1;
			indv_layer_scaling[i][1] = 1;
			indv_layer_offset[i][0] = 0;
			indv_layer_offset[i][1] = 0;
		}
	}
	public void setactivelayer(int layer, boolean active) {
		active_layers[layer] = active;
		
	}
	
	public void replacetolayer(GameObject o, int layer) {
		layers.get(layer).clear();
		layers.get(layer).add(o);
	}	//what shapes do you need?
	//siclest 		circles  np
	public GameObject getobj(int layer, int id) {
		try{
			//System.out.println(layers.get(layer).size());
			return layers.get(layer).get(id);
		}catch(Exception e){
			System.out.println("Exception in getobj");
			return null;
		}
	}
	//I could make circle class, and you just add it with pushtolayer ok
	//btw im gona use the circles to make trees and smoke  thats a good idea. that should be easier to make
	public void clearlayer(int layer) {
		if(layer == -1) {
			for (int is = 0; is <= layers.size(); is = is + 2) {
				layers.get(is).clear();
				}
		}else {
			layers.get(layer).clear();
		}
		
	}
	public void removeObj(int layer, int id) {
		layers.get(layer).remove(id);
		
	}
	public String layerinfo(int layer,int info) {
		//0 = size
		if(info == 0)return Integer.toString(layers.get(layer).size());
		return null;
		
	}
	
	public int pushtolayer(GameObject o, int layer) {
 
		layers.get(layer).add(o);
		return(layers.get(layer).size());
	}
	public int layersize(int layer) {
		return(layers.get(layer).size());
	}
	GameObject mouse;
	public void mouse(GameObject gameObject) {
		// TODO Auto-generated method stub
		mouse = gameObject;
	}

	int i = 0;
	int prevlayersize;

	public void render() {
		width = jf.getWidth();
		height = jf.getHeight();
		cam.xwindowscale = width/defaultw;
		cam.ywindowscale = height/defaulth;
		bs = c.getBufferStrategy();
		if(bs==null) {
			c.createBufferStrategy(buffers);
			return;
		}
		g = (Graphics2D)bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);

			for(int layer = 0; layer < layers.size(); layer++) {
				
				if(layers.get(layer).size() != 0 && active_layers[layer]) {
				for(int i = 0; i < layers.get(layer).size(); i++) {
					try {
					currentgo = layers.get(layer).get(i);
					int screenX = (int)(((currentgo.x - cam.x)*cam.scale*cam.xwindowscale)+cam.xoffset[1]);
					int screenY = (int)(((currentgo.y - cam.y)*cam.scale*cam.ywindowscale)+cam.yoffset[1]);
					if(screenX > -10 && screenX < width) {
						if(screenY > -10 && screenY < height) {
							if(currentgo != null) currentgo.render(g, cam, 2,i);	//2 = ui, 1 = fore, 0 = back
						}
					}
					}catch(Exception NullPointerException){System.out.println("Render Error at layer "+layer+" and index " + i);
					}
				}
				}
				
				//prevlayersize = layers.get(layer).size();
			}	
		//g.drawImage(mouse.txtr,engine.Input_manager.my,engine.Input_manager.my,mouse.txtr.getWidth(),mouse.txtr.getHeight(),null);
		

		bs.show();
		g.dispose();
		//avrage[n] = System.nanoTime()-Ftickstart;
		//System.out.println(1/(float)(System.nanoTime()-Ftickstart)*1000000000);
		//Ftickstart = System.nanoTime();
		//n++;
		//if(n >= 200)n=0;
	}
	long Ftickstart = System.currentTimeMillis();			
	long Ftickend;
	long Fticklen = 16;
	@Override
	public void run() {
		while (true){
			Ftickend =  System.currentTimeMillis();	
			if(Ftickend-Ftickstart>=Fticklen){
				render();
				//ystem.out.println(1/(float)(Ftickend-Ftickstart)*1000);
				Ftickstart = System.currentTimeMillis();
			}
			
		}
	}





}
