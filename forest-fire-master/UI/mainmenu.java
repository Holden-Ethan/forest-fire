package UI;

import java.awt.Color;
import java.awt.image.BufferedImage;

import engine.GameObject;
import engine.assetldr;
import engine.button;
import engine.renderer;
import game.Tmain;
import game.world;

public class mainmenu {
	static BufferedImage box1 = assetldr.loadimg("boxs/1-5_box.png");
	static BufferedImage box2 = assetldr.loadimg("boxs/1-1.5_box.png");
	public static void m() {
		renderer r = Tmain.r;
		int bx, by;
		String text;
		world world = new world();
		
		r.clearlayer(2);
	
		/*---LOAD WORLD---*/
		bx = (int) (engine.renderer.width/2)-100;
		by = 80;
		text = "LOAD WORLD";
		r.pushtolayer(new button(bx,by,53,2,box1, text),2);	
		if(engine.button.click(text, bx, by) == 1) {//if it is
			
			r.clearlayer(2);
			world.start("world");
			ui.ui = "world";
		}
		for (int i = 0; i <= engine.IM.mouseclick.length; i = i + 2) {
			//System.out.println(i);
			//System.out.println(engine.IM.mouseclick[i]);
		}
		
	}
	
}
