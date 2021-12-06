package E;

import java.awt.image.BufferedImage;

public class Button {
	public int x1;
	public int y1;
	public BufferedImage txtr;
	public String name;
    Button (int  x, int  y, String name, BufferedImage txtr) {
		this.name = name;
		this.x1 = x;
		this.y1 = y;
		this.txtr = txtr;
	}
	public boolean click(int x, int y) {
		if(x >= x1 && x <= x1+txtr.getWidth() && y >= y1 && y <=y1+txtr.getHeight())return true;
		return false;
	}

}
