package Game;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.SwingUtilities;

public class camera {
	public int x, y;
	public float zooom;
	double movementspeed;
	public camera (int x, int y, double movspeed) {	//initialize position
		this.x = x;
		this.y = y;
		zooom = 1;
		movementspeed = movspeed;
	}//87 = w, 83 = s, 65 = a, 68 = d 69 = e 
	
	public void oninput() { //moves based on WASD
		if(Main.kl.move[0] && -y>0 ) { 
			y += 10;
		}
		if(Main.kl.move[2]) { //bruh moment
			y -= 10;
		}
		if(Main.kl.move[3]) { //bruh moment
			x += 10;
		}
		if(Main.kl.move[1] && x>0) {
			x -= 10;
		}
	}
	
	void clrzoom (KeyEvent e) {
		if(e.getKeyChar() == 'z') {
			zooom = 1;
		}
	}
	
	int oldmousex, oldmousey;
	
	public void mousemovebegin (MouseEvent e) {
		oldmousex = e.getX(); //10
		oldmousey = e.getY(); //10

	}
	
	public void mousemove (MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			y -= oldmousey-e.getY();
			x += oldmousex-e.getX();
			oldmousex = e.getX(); //10
			oldmousey = e.getY(); //10
		}
	}
	public void zoom (MouseWheelEvent e) {
		if (e.getWheelRotation() == -1) {
			zooom -= (e.getPreciseWheelRotation())*0.1;
		}else if(zooom > 0) {
			zooom -= (e.getPreciseWheelRotation())*0.1;
		}
	}
	/*public void mousemove (MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			y += -(oldmousey-e.getY())*movementspeed*-1;
			x += ((oldmousex-e.getX())*movementspeed*-1);
		}
	}*/
}
