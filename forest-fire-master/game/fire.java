package game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import UI.ui;
import engine.GameObject;
import engine.assetldr;
import engine.circle;
import engine.meth;
import engine.renderer;
import engine.text;

public class fire extends Thread{
	static float[][] burntness = new float[world.mapH][world.mapW];
	static int[][] wetness = new int[world.mapH][world.mapW];
	static float[][] heat = new float[world.mapH][world.mapW];
	static boolean[][] activechunks =  new boolean[world.mapH][world.mapW];
	static int speed = 10;
	static int maxburntness = 3;
	static int maxwetness = 3;
	BufferedImage W = assetldr.loadimg("W.png");
	static BufferedImage fire = engine.recolor.recolor(new Color(255,0,0), assetldr.loadimg("W.png"));
	public static void tick() {
		for (int y = 0; y < world.mapW; y++) {
			for (int x = 0; x < world.mapH; x++) {
				if(activechunks[x][y]) {
				//int treetype = Integer.parseInt(world.tilearry[i][ii]);
				//if(burntness[x][y] != 0) System.out.println(burntness[x][y]);
				final Random rand = new Random();
				
				
				if(heat[x][y] > 0 && rand.nextInt(speed) == 0){
					final int randy = y+rand.nextInt(3)-1;
					final int randx = x+rand.nextInt(3)-1;
					if(randy >= 0 && randy < world.mapW && randx >= 0 && randx < world.mapH 
					&& world.vegetation[randy][randx] != 0 && heat[randx][randy] <= 10  
					&& burntness[randx][randy] < maxburntness && wetness[randx][randy] < maxwetness) {
						heat[randx][randy]++;
						activechunks[randx][randy] = true;
					}}
				if(heat[x][y] > 10 && rand.nextInt(3) == 2) {
					burntness [x][y]++;
					//Color c = new Color(world.gos[(y)][(x)].txtr.getRGB(0, 0));
					//world.gos[(y)][(x)] = new GameObject((y),(x),1,engine.recolor.recolor(new Color(c.getRed()-5,30,30), fire));
				}
				if(burntness [x][y] >= maxburntness) {
					world.gos[(y)][(x)] = new GameObject((y),(x),1,0, engine.recolor.recolor(new Color(30,30,30), fire));
					world.renderdmap[(y)][(x)] = new GameObject((y),(x),1,0, engine.recolor.recolor(new Color(30,30,30), fire));
					heat[x][y] = 0;
					activechunks[x][y] = false;
						  
						  
	
					
				}
				//world.tilearry[randx][randy] = "2";
				//world.gos[(randy)][(randx)] = new GameObject((randy)*4,(randx)*4,1,obj);	
				if(heat[x][y] > 0) {

					world.gos[(y)][(x)] = new GameObject((y),(x),1,0,engine.recolor.recolor(new Color(255,0,0),world.gos[(y)][(x)].txtr));
					world.renderdmap[(y)][(x)] = new GameObject((y),(x),1,0,engine.recolor.recolor(new Color(255,0,0),world.renderdmap[(y)][(x)].txtr));
				}
			}	
				}
		}
		final renderer r = Tmain.r;
		//if(r.getobj(0, 0)!=null) r.getobj(0, 0).txtr = assetldr.compileimg(world.mapW, world.mapH, world.gos);
	}
	public static void fa(final int sx, final int sy, final int a) {
		int x = (int) ((sx-world.mapy)/(4*engine.IM.mw));
		int y = (int) ((sy-world.mapx)/(4*engine.IM.mw));
		System.out.println((x)+"-"+ (y));
		renderer r = Tmain.r;
		//r.pushtolayer(new text(x, y, y, 10, new Color(101,163,225)), 2);
		for (int a1 = 0; a1 < 7; a1++) {
			for (int b1 = 0; b1 < 7; b1++) {
				final Random rand = new Random();
				System.out.print((int)meth.dist(x,y,(x)-a1+3,(y)-b1+3)+" ");
				if(rand.nextInt((int) (meth.dist(x,y,(x)-a1+3,(y)-b1+3)+1)) == 0) {
					
					Color c = new Color(world.gos[(y)-b1+3][(x)-a1+3].txtr.getRGB(0, 0));
					world.gos[(y)-b1+3][(x)-a1+3] = 
					new GameObject(
							(y)-b1+3,
							(x)-a1+3,
							1,
							0,
							engine.recolor.recolor(
									new Color(c.getRed(),c.getGreen(),255),
									world.gos[(y)-b1+3][(x)-a1+3].txtr
									)
							);
				
				}
				
			}
			System.out.println();
		} 
	}
	long Ftickstart = System.currentTimeMillis();			
	long Ftickend;
	long Fticklen = 1000;
	@Override
	public void run() {
		while (true){
			Ftickend =  System.currentTimeMillis();	
			if(Ftickend-Ftickstart>=Fticklen){
				tick();
				//System.out.println(1/(float)(Ftickend-Ftickstart)*1000);
				Ftickstart = System.currentTimeMillis();
			}
			
		}
	}


}


