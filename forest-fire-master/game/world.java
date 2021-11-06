package game;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import UI.ui;
import engine.GameObject;
import engine.assetldr;
import engine.circle;
import engine.circle;
import engine.renderer; 



public class world {
	static BufferedImage map = assetldr.loadimg("saves/map.png");
	public static int speed = 1;
	public static int mapH = map.getHeight();
	public static int mapW = map.getWidth();
	public static int[][] vegetation = new int[mapW][mapH];
	public static int[][] water1 = new int[mapW][mapH];
	static GameObject[][] gos = new GameObject[mapW][mapH];
	static GameObject[][] renderdmap = new GameObject[mapW][mapH];
	public static int[][] randomseeds = new int[mapW][mapH];
	public static int[][] plantpos = new int[mapW][mapH];
	public static int mapx, mapy;
	BufferedImage tree = engine.recolor.recolor(new Color(25,92,18), assetldr.loadimg("W.png"));
	BufferedImage brush = engine.recolor.recolor(new Color(72,108,39), assetldr.loadimg("W.png"));
	BufferedImage grass = engine.recolor.recolor(new Color(101,163,95), assetldr.loadimg("W.png"));
	BufferedImage water = engine.recolor.recolor(new Color(10,10,222), assetldr.loadimg("W.png"));
	BufferedImage dirt = engine.recolor.recolor(new Color(160,118,35), assetldr.loadimg("W.png"));
	renderer r = Tmain.r;
	Random rand = new Random(); 
	public static fire f;
	public void start(String name) {
		
		for (int h = 0; h < mapH; h++) {
			for (int w = 0; w < mapW; w++) {
				Color c = new Color(map.getRGB(w, h));
				vegetation[w][h] = (int)c.getGreen()/64;
				water1[w][h]= (int) (c.getBlue()/127.5);
				}
			}
		
		//fire.heat[rand.nextInt(ui.wrldH)][rand.nextInt(ui.wrldW)] = 2;
		fire.heat[20][80] = 2;
		fire.activechunks[20][80] = true;
		
		//Render world
		for (int h = 0; h < mapH; h++) {
			for (int w = 0; w < mapW; w++) {
				randomseeds[w][h] = rand.nextInt(1000);
				if(water1[w][h]!=0) {
					gos[(w)][(h)] = new GameObject((w),(h),4,0, engine.recolor.recolor(new Color(10+rand.nextInt(10)-5,10+rand.nextInt(10)-5,222+rand.nextInt(10)-5), water));
				}else if(vegetation[w][h]==0) {
					gos[(w)][(h)] = new GameObject((w),(h),1,0, engine.recolor.recolor(new Color(160+rand.nextInt(10)-5,118+rand.nextInt(10)-5,35+rand.nextInt(10)-5), dirt));
				}else if(vegetation[w][h]==1) {
					gos[(w)][(h)] = new GameObject((w),(h),1,0, engine.recolor.recolor(new Color(101+rand.nextInt(10)-5,163+rand.nextInt(10)-5,95+rand.nextInt(10)-5), grass));
				}else if(vegetation[w][h]==2) {
					gos[(w)][(h)] = new GameObject((w),(h),1,0, engine.recolor.recolor(new Color(101+rand.nextInt(10)-5,163+rand.nextInt(10)-5,95+rand.nextInt(10)-5), brush));
				}else if(vegetation[w][h]==3) {
					gos[(w)][(h)] = new GameObject((w),(h),1,0, engine.recolor.recolor(new Color(101+rand.nextInt(10)-5,163+rand.nextInt(10)-5,95+rand.nextInt(10)-5), tree));
					r.pushtolayer(new circle((w*4)+rand.nextInt(4),(h*4)+rand.nextInt(4),rand.nextInt(2)+2,new Color(5,(int) (58+rand.nextInt(14)-7),0)),1);
					if(rand.nextInt(2)==1)r.pushtolayer(new circle((w*4)+rand.nextInt(4),(h*4)+rand.nextInt(4),rand.nextInt(2)+2,new Color(5,(int) (58+rand.nextInt(14)-7),0)),1);
				}else{
					
				}

			}
		}
		//Render map
		for (int h = 0; h < mapH; h++) {
			for (int w = 0; w < mapW; w++) {
				randomseeds[w][h] = rand.nextInt(1000);
				if(water1[w][h]!=0) {
					renderdmap[(w)][(h)] = new GameObject((w),(h),4,0, water);
				}else if(vegetation[w][h]==0) {
					renderdmap[(w)][(h)] = new GameObject((w),(h),1,0, dirt);
				}else if(vegetation[w][h]==1) {
					renderdmap[(w)][(h)] = new GameObject((w),(h),1,0, grass);
				}else if(vegetation[w][h]==2) {
					renderdmap[(w)][(h)] = new GameObject((w),(h),1,0, brush);
				}else if(vegetation[w][h]==3) {
					renderdmap[(w)][(h)] = new GameObject((w),(h),1,0, tree);
					
				}else{
					
				}

			}
		}
		BufferedImage bi = assetldr.compileimg(mapW, mapH, world.gos);
		//r.pushtolayer(new GameObject((int)(engine.renderer.height/2), (int)(engine.renderer.height/2), 1, bi),0);
		r.pushtolayer(new GameObject(0, 0, 4,0, bi),0);
		f = new fire();
		f.setName("fire");
		f.start();
		//r.cs();
	}
	static long Ftickend;
	public static void tick() {
		if (Tmain.im.key[(int)'W']) mapy += 1;
		if (Tmain.im.key[(int)'S']) mapy -= 1;
		if (Tmain.im.key[(int)'A']) mapx += 1;		//WASD control
		if (Tmain.im.key[(int)'D']) mapx -= 1;
		Tmain.r.indv_layer_offset[0][0] = mapx;
		Tmain.r.indv_layer_offset[1][0] = mapx;
		Tmain.r.indv_layer_offset[0][1] = mapy;
		Tmain.r.indv_layer_offset[1][1] = mapy;
		Ftickend = System.currentTimeMillis();
		renderer r = Tmain.r;
		if(engine.IM.mw<4) {
			r.getobj(0, 0).txtr = assetldr.compileimg(mapW, mapH, world.renderdmap);
			r.setactivelayer(1 , false);
		}else {
			r.getobj(0, 0).txtr = assetldr.compileimg(mapW, mapH, world.gos);
			r.setactivelayer(1 , true);
		}
		//if(r.getobj(0, 0)!=null) r.getobj(0, 0).txtr = assetldr.compileimg(mapW*4, mapH*4, world.gos); //fix for flicker
		//r.getobj(0, 0).x = mapx;	
		//r.getobj(0, 0).y = mapy;
		//r.getobj(0, 0).scale = Tmain.im.mw;
		
		//how would we add shapes to the layers
	}
}

