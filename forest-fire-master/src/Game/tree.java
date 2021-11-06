package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class tree { //basic tree class. holds position and texture
	public int x,y;
	public BufferedImage texture;
	public tree (int x,int y,BufferedImage txtr) { //initialize vareables
		this.x = x;
		this.y = y;
		texture = txtr;
	}
	
	public void render(Graphics g) { 	//draws sprite to screen based on position of camera and tree. Contains on-screen detection logic
		g.drawImage(texture, (int)((x - Main.Player.x)*Main.Player.zooom), (int)((y + Main.Player.y)*Main.Player.zooom), null);
	}
}

