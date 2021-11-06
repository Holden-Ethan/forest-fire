package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import game.Tmain;
import game.world;

public class IM implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
	public static int mx, my, md, kd, mhd, mdx, mdy;
	public static double mw = 1;
	public static boolean[] mouseclick = new boolean[3];
	public static char kt;
	public static char pressedkey = '~';
	public static boolean[ ] key = new boolean[200];

	public static void reset() {
		pressedkey = '~';
		engine.IM.md = 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		key[e.getKeyCode()] = true;
		pressedkey = e.getKeyChar();
		System.out.println(e.getKeyCode());
		System.out.println(key[e.getKeyCode()]);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		key[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//textfield.textinput(e);

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		double zoomspeed = mw*.03;
		world.mapx = (int) (world.mapx + ((e.getPreciseWheelRotation()*zoomspeed)*world.mapW*4*(((mx)-world.mapx)/(world.mapW*engine.IM.mw*4))));
		world.mapy = (int) (world.mapy + ((e.getPreciseWheelRotation()*zoomspeed)*world.mapH*4*(((my)-world.mapy)/(world.mapH*engine.IM.mw*4))));
		mw = mw - e.getPreciseWheelRotation()*zoomspeed;
		renderer.indv_layer_scaling[0][0] = mw;
		Tmain.r.indv_layer_scaling[0][1] = mw;
		Tmain.r.indv_layer_scaling[1][0] = mw;
		Tmain.r.indv_layer_scaling[1][1] = mw;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mdx = e.getX();
		mdy = e.getY();
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		md = 1;
		mx = e.getX();
		my = e.getY();
				
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		 md = 1;
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		 md = 0;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseclick[e.getButton()-1] = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseclick[e.getButton()-1] = false;
		
	}

}
