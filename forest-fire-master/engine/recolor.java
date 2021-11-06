package engine;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



public class recolor { //basic tree class. holds position and texture
	public int x,y;
	public BufferedImage texture;	
	

    public static BufferedImage recolor(Color color ,BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
    	BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
       
    }
 

	public recolor (int x,int y,BufferedImage txtr) { //initialize vareables
		this.x = x;
		this.y = y;
		texture = txtr;
	}
}

