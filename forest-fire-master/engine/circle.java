package engine;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Tmain;
import game.world;

public class circle extends GameObject{
	int r;
	private Color co;
	public circle(int x, int y, int scale, Color co) {
		super(x, y, 1, 0, null);
		this.r = scale;
		this.co = co;
	}

	@Override //was it individual scaling that wouldnt work? or layer/screen size scaling?
	public void render(Graphics2D g, Camera c, int layer, int id) {
		int screenX = (int)(((x - c.x))+c.xoffset[layer]);
		int screenY = (int)(((y - c.y))+c.yoffset[layer]);
		g.setColor(co);
		g.fillOval(//indavigual scaling   Oh, i see why it doesnt work / moving ok 
				(int)((screenX*IM.mw)+world.mapx),
				(int)((screenY*IM.mw)+world.mapy),
				(int)(r*scale*IM.mw),
				(int)(r*scale*IM.mw)
				);
	}
}
