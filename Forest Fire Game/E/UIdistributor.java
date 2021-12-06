package E;

import java.awt.Color;
import java.util.ArrayList;

import G.fire;
import G.start;
import G.world;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


public class UIdistributor {
    public static tile mapclickid = null;
    public static apparatus selectedApparatus = null;
    String clickedbutton;
    public static ArrayList<Button> buttons = new ArrayList<Button>();
//checks if click in on a buttion
    public static boolean checkclick(int x, int y) {
        boolean clicked = false;
       
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).click(x, y)) {
                buttonaction(buttons.get(i).name, x, y);
                //System.out.println(true);
                clicked = true;
            }

        }
    
        return clicked;
    }
//renders buttons, caled from display.java
    public static void render(Graphics2D g) {
        for (int i = 0; i < buttons.size(); i++) {
            g.drawImage(buttons.get(i).txtr, buttons.get(i).x1, buttons.get(i).y1, buttons.get(i).txtr.getWidth(),
                    buttons.get(i).txtr.getHeight(), null);

        }
        

    }
//checks if you clicked on a buttion apparatus
    public static boolean apparatusclick(int x, int y) {
        

        for(int i = 0; i < world.apparatus.size(); i++){
            int dx = (int) (IM.tox + (world.apparatus.get(i).x*IM.scale) - x);
            int dy = (int) (IM.toy + (world.apparatus.get(i).y*IM.scale) - y);
            System.out.println(Math.sqrt((dx*dx)+(dy*dy)));
            if(Math.sqrt((dx*dx)+(dy*dy))<30)selectedApparatus = world.apparatus.get(i);
            
            //System.out.println(Math.abs(Math.pow(dx, 2)+Math.pow(dy, 2)));
            
        }
        if(selectedApparatus == null)return true;
        return false;
    }

//i dont think this does anything but i'm scared to delete it
    public static void mapclick(int x, int y, int r) {
        
        try {
            fire.waterdrop(x, y);
            //mapclickid=-1;
        } catch (Exception e) {
            //TODO: handle exception
        }
        //mapclickid = null;

    }
//adds button
    public static void add(Button button) {
        buttons.add(button);
    }

//code for all buttons is here:

    public static void buttonaction(String name, int x, int y) {
        switch (name) {
            case "new world":
                buttons.clear();
                world.start(500);
                world.running = true;
                break;

            case "exit":
                System.exit(0);
                break;

            case "settings":
                buttons.clear();
                start.r.addbutton(100, 160, "fullscrean", imagegenerator.button(new Color(180 ,180, 180), "Settings", 50, 250, 52, 40, 40));
                break;
            case "drop here":
                selectedApparatus.autopilot = true;
                selectedApparatus.xdest = (int) (((IM.lastclick[3][0]-IM.tox)/IM.scale));
                selectedApparatus.ydest = (int) (((IM.lastclick[3][1]-IM.toy)/IM.scale));
                start.r.removebutton("go here");
                start.r.removebutton("drop here");
                break;
            case "go here":
                selectedApparatus.xdest = (int) (((IM.lastclick[3][0]-IM.tox)/IM.scale));
                selectedApparatus.ydest = (int) (((IM.lastclick[3][1]-IM.toy)/IM.scale));
                start.r.removebutton("go here");
                start.r.removebutton("drop here");
                break;
            default:
              // code block
          }
    }
    public static void OBJbutton(tile id){
        if(mapclickid != id){
            mapclickid = id;
        }else{ 
            mapclickid = null;
            mapclickid.gID = "null";

        }
    }
    
}
