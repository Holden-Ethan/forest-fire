package game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

import UI.ui;
import engine.Camera;
import engine.GameObject;
import engine.IM;
import engine.assetldr;
import engine.button;
import engine.circle;
import engine.renderer;

public class Tmain {
	static IM im;
	public static renderer r;
	static Camera c;
	static NewWorld Nworld;
	
	public static void main(String[] args) {
		c = new Camera(0,0,5);
		im = new IM();
		r =  new  renderer (1300,800,"Engine",c,im,3);
		r.setName("renderer");
		r.start();	//renderer class manages itself in order to remain in other thread.

 //its broken help
		long Ftickstart = System.currentTimeMillis();			
		long Ftickend;
		long Fticklen = 16;
		long firestart= System.currentTimeMillis();
		long fireend;
		long firelen = 1000 / world.speed;
		world world = new world();
		ui ui = new ui();
		fire fire = new fire();
		smoke smoke = new smoke();
		ui.main();
		int t = 0;
		while (true) { 
			Ftickend = System.currentTimeMillis();
			//i added ticks Cool! And happy birthday! thanks
			//FPS ticks 
			
			//if(Ftickend-Ftickstart>=Fticklen){
			if(true) {
				//r.pushtolayer(new circle(1,(int) (1/(float)(Ftickend-Ftickstart)*1000),1,2,new Color(5,58,0)),1);
				//for (int i = 0; i < r.layersize(1); i++) {
				//	r.getobj(1, i).x=r.getobj(1, i).x+2;	
				//	}i also started makeing a helicopter thing tha
				//How do you use it? click the hc button and the cluck 2 points on the map the 2 places you click sould turn green
				//System.out.println("MAIN FPS: " + 1/(float)(Ftickend-Ftickstart)*1000);//should the game run at 58fps or 62fps?
				Ftickstart = System.currentTimeMillis();
				ui.main();
				if (ui.ui == "world") {
				world.tick();
				
				}
				
				
			  }
			//fire ticks
			fireend = System.currentTimeMillis();
			if(fireend-firestart>=firelen && ui.ui == "world") {
				r.pushtolayer(new circle(1,64,2,new Color(90,0,0)),1);
				//System.out.println(1/(float)(fireend-firestart)*1000);
				//System.out.println((float)(fireend-firestart));
				firestart = System.currentTimeMillis();
				/*if (ui.ui == "world") {
					fire.tick();
					smoke.tick();
				}*/
				//System.out.println(System.currentTimeMillis()-fireend);
			}
			engine.IM.reset();
			
		}
	}
}
