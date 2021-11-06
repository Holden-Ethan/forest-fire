package UI;

import java.awt.image.BufferedImage;

import engine.assetldr;
import engine.button;
import engine.renderer;
import engine.text;
import engine.IM;
import game.Tmain;
import game.fire;
import game.world;

public class worldui {
	static BufferedImage box1invert = assetldr.loadimg("boxs/1-5_box_invert.png");
	static BufferedImage box1 = assetldr.loadimg("boxs/1-5_box.png");
	static BufferedImage box2invert = assetldr.loadimg("boxs/1-1.5_box_invert.png");
	static BufferedImage box2 = assetldr.loadimg("boxs/1-1.5_box.png");
	static BufferedImage helicopter = assetldr.loadimg("helicopter.png");
	static String fa = null; // fa = firefighting asset
	static int x1 = 0, y1 = 0, a = 0;
	public static void m() {
		renderer r = Tmain.r;
		int bx, by;
		String text; 
		world world = new world();
		int mx = engine.IM.mx;
		int my = engine.IM.my;
		r.clearlayer(2); 
		r.clearlayer(3);
//speed
		// SLOW
		bx = 1;
		by = (int) (engine.renderer.height)-77;
		text = ">";
		if(world.speed == 1) {
			r.pushtolayer(new button(bx,by,20,1.5,box2invert, text),2);
		}else {
			r.pushtolayer(new button(bx,by,20,1.5,box2, text),2);
		}
		if(engine.button.click(text, bx, by) == 1) {//if it is
			world.speed = 1;
			IM.reset();
		}
		
		
		// MEDIUM
		bx = 57;
		by = (int) (engine.renderer.height)-77;
		text = ">>";
		if(world.speed == 3) {
			r.pushtolayer(new button(bx,by,13,1.5,box2invert, text),2);
		}else {
			r.pushtolayer(new button(bx,by,13,1.5,box2, text),2);
		}
		if(engine.button.click(text, bx, by) == 1) {//if it is
			world.speed = 3;
			IM.reset();
		}
		
		
		// FAST
		bx = 113;
		by = (int) (engine.renderer.height)-77;
		text = ">>>";
		if(world.speed == 6) {
			r.pushtolayer(new button(bx,by,6,1.5,box2invert, text),2);
		}else {
			r.pushtolayer(new button(bx,by,6,1.5,box2, text),2);
		}
		if(engine.button.click(text, bx, by) == 1) {//if it is
			world.speed = 6;
			IM.reset();
		}
		
		/*---HELICOPTER---*/
		bx = 174 ;
		by = (int) (engine.renderer.height)-77;
		text = "HELICOPTERS";
		if(fa == "-hc") {
			r.pushtolayer(new button(bx,by,10,1.5,box1invert, text),2);
		}else {
			r.pushtolayer(new button(bx,by,10,1.5,box1, text),2);
		}
		if(engine.button.click(text, bx, by) == 1 || engine.IM.pressedkey == (int)'1') {//if it is
			if(fa != "-hc") {
				fa = "-hc";
			}else {
				fa = null;
			}
			
			IM.reset();
		}
		
	

		if(fa == "-hc") {
			bx = 174 ;
			by = (int) (engine.renderer.height)-115;
			text = "BELL 204";
			r.pushtolayer(new button(bx,by,10,1.5,box1, text),2);
			if(engine.button.click(text, bx, by) == 1 || engine.IM.pressedkey == (int)'1') {//if it is
				fa = "BELL 204";
				IM.reset();
								
				
				
			}
		}
		if(fa == "-hc" && engine.IM.md == 1) {
			System.out.println("W");
			fa = null;
			fire.fa(mx, my, my);
		}
		
		
		
		if(engine.IM.key[112]) {
			r.pushtolayer(new text(1, 20, "RENDERER", 1, 1, null), 3);
			r.pushtolayer(new text(1, 40, "Num of layers:" + r.layers.size(), 1, 1, null), 3);
			r.pushtolayer(new text(1, 60, "Layers size:", 1, 1, null), 3);
			r.pushtolayer(new text(1, 80, "0:" + r.layersize(0), 1, 1, null), 3);
			r.pushtolayer(new text(1, 100, "1:" + r.layersize(1), 1, 1, null), 3);
			r.pushtolayer(new text(1, 120, "2:" + r.layersize(2), 1, 1, null), 3);
			r.pushtolayer(new text(1, 140, "3:" + r.layersize(3), 1, 1, null), 3);
			r.pushtolayer(new text(1, 160, "4:" + r.layersize(4), 1, 1, null), 3);

			
		}
		
		engine.IM.md = 0;
	}
}
