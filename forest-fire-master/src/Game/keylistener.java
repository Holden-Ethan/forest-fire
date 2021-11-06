package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class keylistener implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{	
	
		boolean[] move = new boolean[4];
	
	    public void keyTyped(KeyEvent e) {
	    }

	    public void keyPressed(KeyEvent e) { //gets called when a key is pressed, info passed to player
	    	Main.Player.clrzoom(e);
	    	if(e.getKeyChar() == 'w') {
	    		move[0] = true;
	    	}else {
	    		move[0] = false;
	    	}
	    	if(e.getKeyChar() == 'a') {
	    		move[1] = true;
	    	}else {
	    		move[1] = false;
	    	}
	    	if(e.getKeyChar() == 's') {
	    		move[2] = true;
	    	}else {
	    		move[2] = false;
	    	}
	    	if(e.getKeyChar() == 'd') {
	    		move[3] = true;
	    	}else {
	    		move[3] = false;
	    	}
	    }

	    public void keyReleased(KeyEvent e) {
	    	
	    }

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Main.Player.mousemovebegin(e);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			Main.Player.mousemove(e);
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
	            Main.Player.zoom(e);
	    }
}
