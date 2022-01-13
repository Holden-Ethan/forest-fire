package E;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.Color;


import G.fire;
import G.start;
import G.world;

//import org.graalvm.compiler.asm.amd64.AMD64Address.Scale;

public class IM implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
    static int mx = 0, my = 0, mcb = 0, rotation = 0; //mouse clicked button
    boolean dragged = false;
    public static double tox;
    public static double toy;
    public static double scale = 1;
    public static int lastclick [][] = new int[5][2];
    static double MW_Rotation = 1;
    static boolean keycode[] = new boolean[200];

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //if(!(scale - (e.getWheelRotation()*.04*scale)>=6) && !(scale - (e.getWheelRotation()*.04*scale)<=display.height/(world.mapgrid[0].length+0.0))){
            // scale = scale - (e.getWheelRotation()*.04*scale);
            // if(scale >= 7 ){scale = 7; return;}
            // if(scale <= .5){scale = .5; return;}
            // tox = (tox + ((e.getWheelRotation()*.04*scale)*world.mapsize*2*(((mx-tox)/(world.mapsize*2*scale)))));
            // toy = (toy + ((e.getWheelRotation()*.04*scale)*world.mapsize*2*(((my-toy)/(world.mapsize*2*scale)))));
            
        
        //}
        //System.out.println(mx);
        //Thread.currentThread();
    }
    int mdx = 0;
    int mdy = 0;
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
       
      
       if(mcb == 3){
        
        tox += e.getX() - mdx;
        if(tox < 5-(world.mapsize*2*scale)){tox = 5-(world.mapsize*2*scale);}
        if(tox > display.width-5){tox = display.width-5;}
        toy += e.getY() - mdy;
        if(toy < 5-(world.mapsize*2*scale)){toy = 5-(world.mapsize*2*scale);}
        if(toy > display.height-5){toy = display.height-5;}
        mdx = e.getX();
        mdy = e.getY();
       }
        mx = e.getX();
        my = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        mx = e.getX();
        my = e.getY();
        mdx = e.getX();
        mdy = e.getY();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
        //System.out.println("mouseClicked");
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //System.out.println(e.getClickCount());
        mcb = e.getButton();
        //if(e.getButton() == )

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        lastclick[e.getButton()][0] = e.getX();
        lastclick[e.getButton()][1] = e.getY();
        System.out.println(e.getButton());
        // TODO Auto-generated method stub
        if(e.getButton()==1){
        if(!UIdistributor.checkclick(e.getX(),e.getY())){
            UIdistributor.mapclick((int)((((e.getX()-tox)/scale))/2),(int)((((e.getY()-toy)/scale))/2), rotation);
            if(UIdistributor.apparatusclick(e.getX(), e.getY()))UIdistributor.selectedApparatus = null;
            }
        }
        if(UIdistributor.selectedApparatus != null && e.getButton()==3){
            start.r.addbutton(e.getX(), e.getY(), "go here", imagegenerator.button(new Color(100 ,100, 100), "go here", 30, 88, 10, 22, 20));
            start.r.addbutton(e.getX(), e.getY()+35, UIdistributor.selectedApparatus.action, imagegenerator.button(new Color(100 ,100, 100), UIdistributor.selectedApparatus.action, 30, 95, 10, 22, 20));

        }
        dragged = false;
        mcb = -1;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getKeyChar() == 'e')rotation++;
        if(rotation >= 4) rotation = 0;
        if(e.getKeyChar() == ' ')fire.simulate = true;
        if(e.getKeyChar() == '`')start.debug = true;


    }

    @Override
    public void keyPressed(KeyEvent e) {
        keycode[e.getKeyCode()]= true;
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keycode[e.getKeyCode()]= false;
    }

    public static void keyfunctions() {
//WASD movment
        if(keycode[(char)'A']){
            tox+=10;
            start.r.removebutton("go here");
            start.r.removebutton("drop here");}
        if(keycode[(char)'D']){
            tox-=10;
            start.r.removebutton("drop here");
            start.r.removebutton("go here");}
        if(keycode[(char)'S']){
            toy-=10;
            start.r.removebutton("drop here");
            start.r.removebutton("go here");}
        if(keycode[(char)'W']){
            toy+=10;
            start.r.removebutton("drop here");
            start.r.removebutton("go here");}
//zoom in and out
        if(keycode[(char)'Q']){
            scale+=.02*scale; 
            tox -= ((.02*scale)*world.mapsize*2*((((display.width/2)-tox)/(world.mapsize*2*scale))));
            toy -= ((.02*scale)*world.mapsize*2*((((display.height/2)-toy)/(world.mapsize*2*scale))));
            start.r.removebutton("go here");
            start.r.removebutton("drop here");
        }
        if(keycode[(char)'E']){
            scale-=.02*scale; 
            tox += ((.02*scale)*world.mapsize*2*((((display.width/2)-tox)/(world.mapsize*2*scale))));
            toy += ((.02*scale)*world.mapsize*2*((((display.height/2)-toy)/(world.mapsize*2*scale))));
            start.r.removebutton("go here");
            start.r.removebutton("drop here");
        }
    }
    

}
