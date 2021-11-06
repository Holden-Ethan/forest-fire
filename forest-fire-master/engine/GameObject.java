package engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import game.Tmain;
import game.world;

public class GameObject {
	public int x, y, id;
	public double scale;
	public long time;
	public BufferedImage txtr;
	public GameObject (int x, int y, double scale,long time, BufferedImage txtr) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.txtr = txtr;
		this.time = time;
	}

	public void render(Graphics2D g, Camera c, int layer, int id) {
		//if(layer = 2)
		//int screenX = (int)(((x - c.x)*c.scale*engine.renderer.indv_layer_scaling[layer][0]*c.xwindowscale)+c.xoffset[layer]);
		//int screenY = (int)(((y - c.y)*c.scale*engine.renderer.indv_layer_scaling[layer][1 ]*c.ywindowscale)+c.yoffset[layer]);
		int screenX = (int)(((x - c.x)*c.scale*scale*c.xwindowscale));
		int screenY = (int)(((y - c.y)*c.scale*scale*c.ywindowscale));
		
		g.drawImage(txtr,
					(int) (world.mapx),
					(int) (world.mapy),
					(int)(txtr.getWidth()*engine.IM.mw*scale),
					(int)(txtr.getHeight()*engine.IM.mw*scale),
					null);
		g.drawRect(renderer.width/2, renderer.height/2, 10,10);

		
	}
	public boolean collidedwithGO (GameObject g) {
		if (x < g.x + g.txtr.getWidth() &&
				   x + txtr.getWidth() > g.x &&
				   y < g.y + g.txtr.getHeight() &&
				   y + txtr.getHeight() > g.y) {
			return true;
		}
		
		return false;
	}
	public boolean collided () {
		return true;
	}
}