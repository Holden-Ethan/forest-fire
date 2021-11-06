package game;

import java.awt.Color;
import java.util.Random;

import engine.circle;
import engine.renderer;
import engine.smokeparticle;

public class smoke {

	public static void tick() {
		//final Random rand = new Random();
		int smokelength = 1000; //in milliseconds
		renderer r = Tmain.r;
		for (int y = 0; y < world.mapW; y++) {
			for (int x = 0; x < world.mapH; x++) {
				final Random rand = new Random();
				if(fire.activechunks[x][y]&& rand.nextInt(25) == 0) {
					r.pushtolayer(new smokeparticle(y*4,x*4,2,new Color(100,100,100,rand.nextInt(100)),System.currentTimeMillis(), 10),4);
				}
			}
		}
		for (int p = 0; p < r.layersize(4); p++) {
			if(r.getobj(4, p).time > smokelength) {
				r.removeObj(4, p);
			}
		}
	}
}
