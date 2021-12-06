package G;

import java.awt.Color;

import E.*;
public class start {
  public static display r;
  public static boolean debug = false;
  static IM im;
    public static void main(String[] args) throws InterruptedException {
      
      System.out.println(Thread.currentThread());
      im = new IM();
      r =  new  display (1000,700,"Engine",im,3);
		  r.setName("renderer");
      r.start();
//main menu button
      r.addbutton(100, 100, "new world", imagegenerator.button(new Color(60, 148, 178), "New World", 50, 250, 30, 40, 40));
      r.addbutton(100, 160, "settings", imagegenerator.button(new Color(180 ,180, 180), "Settings", 50, 250, 52, 40, 40));
      r.addbutton(100, 220, "exit", imagegenerator.button(new Color(168 ,70, 70), "Exit", 50, 250, 90, 40, 40));
      
      long Ftickend = 0;
      long Ftickstart = 0;
      long tickend = 0;
      long tickstart = 0;
      while (true){
        Thread.sleep(1);
        if(world.running){
        tickstart = System.currentTimeMillis();
        
        if(tickstart-tickend>=500){
          
          tickend = System.currentTimeMillis();
        }

        Ftickstart = System.currentTimeMillis();
        if(Ftickstart-Ftickend>=17){
          if(world.running == true)world.runApparatus();
          Ftickend = System.currentTimeMillis();
        }
      }
      }

      //r.add(100, 100, (float) .2, load.image("rock.png"));
      //r.add(200, 200, (float) 4.124, load.image("power.png"));
      //r.add(10, 10,(float) .5, load.image("rock.png"));
    }
  }