package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class textbox extends GameObject{
	String text;
	public textbox(int x, int y, int scale, BufferedImage txtr, String text) {
		super(x, y, scale,(Long)null, txtr);
		this.text = text;
	}

	@Override
	public void render(Graphics2D g, Camera c, int layer, int id) {
		g.drawImage(txtr, x, y, (int)(txtr.getWidth()*scale), (int)(txtr.getHeight()*scale), null);
		g.setFont(new Font("Courier New", Font.PLAIN, 24));
		g.setColor(new Color(0,0,0));
		g.drawString(text, x+125-(text.length()*13)/2, y+33);
	}
}
