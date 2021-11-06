package engine;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Tmain;
import game.world;

public class smokeparticle extends GameObject{
	int r, length;
	renderer render = Tmain.r;
	private Color co;
	long starttime;
	public smokeparticle(int x, int y, int scale, Color co,long starttime,int length) {
		super(x, y, 1, starttime, null);
		this.r = scale;
		this.co = co;
		this.starttime = starttime;
	}

	@Override //was it individual scaling that wouldnt work? or layer/screen size scaling?
	public void render(Graphics2D g, Camera c, int layer, int id) {
		/*if((System.currentTimeMillis()-starttime)  length*1000) {
			System.out.println((System.currentTimeMillis()-starttime));
			render.removeObj(4,id);
		}*/
		int screenX = (int)(((x - c.x))+c.xoffset[layer]);
		int screenY = (int)(((y - c.y))+c.yoffset[layer]);
		g.setColor(co);
		g.fillOval(//indavigual scaling   Oh, i see why it doesnt work / moving ok 
				(int)((screenX*engine.IM.mw)+world.mapx+((System.currentTimeMillis()-starttime)/100)),
				(int)((screenY*engine.IM.mw)+world.mapy+((System.currentTimeMillis()-starttime)/100)),
				(int)(r*scale*engine.IM.mw+((System.currentTimeMillis()-starttime)/1000)),
				(int)(r*scale*engine.IM.mw+((System.currentTimeMillis()-starttime)/1000))
				);
		
	}
}
