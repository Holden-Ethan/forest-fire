package engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import game.Tmain;

public class button extends GameObject{
	int Xoffset;
	String text;
	static double txtrH, txtrW, s;
	public button(int x, int y,int xoffset, double d, BufferedImage txtr, String text) {
		super(x, y, d,0, txtr);
		this.text = text;
		txtrH = txtr.getHeight()*d;
		txtrW = txtr.getWidth()*d;
		s = d;
		Xoffset = xoffset;
	}

	public static int click(String text, int x, int y) {
		int mx = engine.IM.mx;
		int my = engine.IM.my;
		if(mx > x && mx < x+txtrW && my > y && my < y+txtrH && engine.IM.md == 1) {
			//engine.Input_manager.md = 0;
			System.out.println(text +"true");
			return 1;
		} else {
			//engine.Input_manager.md = 0;
			//System.out.println(text +"false");
			return 0;
		}
	}
	
	@Override
	public void render(Graphics2D g, Camera c, int layer, int id) {
		g.drawImage(txtr, x, y, (int)(txtr.getWidth()*scale), (int)(txtr.getHeight()*scale), null);
		//g.drawRect(x,y-15, 12 ,15);
		g.setFont(new Font("Courier New", Font.PLAIN, 24));
		g.setColor(new Color(0,0,0));
		g.drawString(text, x+Xoffset, (int) (y+(16.75*s)));
		//System.out.println("d");
		/*String fonts[] = 
			      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

			    for ( int i = 0; i < fonts.length; i++ )
			    {
			    	g.setFont(new Font(fonts[i], Font.PLAIN, 20));
			    g.drawString(fonts[i], 0, i*20);
			    }*/
			    
	}
}
