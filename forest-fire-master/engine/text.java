package engine;

import java.awt.Color;
import java.awt.Graphics2D;

import game.Tmain;
import game.world;

public class text extends GameObject{
	int r;
	private Color co;
	String text;
	public text(int x, int y, String text, int scale, int r, Color co) {
		super(x, y, scale, 0, null);
		this.r = r;
		this.co = co;
		this.text = text;
	}

	@Override //was it individual scaling that wouldnt work? or layer/screen size scaling?
	public void render(Graphics2D g, Camera c, int layer, int id) {
		int screenX = (int)(((x - c.x)*c.scale*scale*c.xwindowscale)+c.xoffset[layer]);
		int screenY = (int)(((y - c.y)*c.scale*scale*c.ywindowscale)+c.yoffset[layer]);
		g.setColor(new Color(255,255,255));
		g.drawString(text, screenX, screenY);
	}
}
