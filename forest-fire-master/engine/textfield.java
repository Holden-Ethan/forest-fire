
package engine;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class textfield extends GameObject{
	static String text;
	Boolean istyping;
	int x,y;
	static String tftype;
	String vtext;
	String cursor = " ";
	public textfield(int x, int y, int scale, BufferedImage txtr, String type) {
		super(x, y, scale,(Long)null, txtr);
		this.x = x;
		this.y = y;
		//this.text = text;
		tftype = type;
		
	}

	public void tick() {
		int mx = engine.IM.mx;
		int my = engine.IM.my;
		
		
		if(mx > x && mx < x+200 && my > y && my < y+60) {
			//System.out.println(text +"true");
			istyping = true;
		} else {
			//System.out.println(text +"false");
			istyping = false;
		}
		if(istyping) {
			System.out.println((int)engine.IM.pressedkey);
			if(tftype== "a") {
				if(engine.IM.pressedkey<=125 && engine.IM.pressedkey>=32 && text != null) {
					text = text + engine.IM.pressedkey;
					engine.IM.pressedkey = '~';
				}
				if(engine.IM.pressedkey<=125 && engine.IM.pressedkey>=32 && text == null) {
					text = Character.toString(engine.IM.pressedkey);
					engine.IM.pressedkey = '~';
				}
			}
				
			if((int)engine.IM.pressedkey == 8 && text.length() > 0) {
				System.out.println("bs");
				text = text.substring(0, text.length()-1);
				engine.IM.pressedkey = '~';
			}
		}
		//System.out.println(istyping);
		if(istyping == true) {
			if(System.currentTimeMillis()/1000 % 2 == 0) {
				cursor = "|";
			}
			vtext = text + cursor; 
		}else {
			vtext = text;
		}
	}
	
	@Override
	public void render(Graphics2D g, Camera c, int layer, int id) {
		g.drawImage(txtr, x, y, (int)(txtr.getWidth()*scale), (int)(txtr.getHeight()*scale), null);
		g.setFont(new Font("Courier New", Font.PLAIN, 24));
		
		if(text != null)g.drawString(vtext, x+20, y+37);
		
	}
}
