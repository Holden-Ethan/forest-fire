package E;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.ColorUIResource;
import G.world;

public class compile {
    public static BufferedImage map = null;
    public static BufferedImage bell205 = load.image("Bell 205.png");
    public static apparatus clickedutility;
    public static void map(Graphics2D g){
//draws map image
        double s = IM.scale;
        
        if(world.mapgrid != null && world.mapgrid[0][0] != null){
            g.drawImage(map,(int) IM.tox,(int) IM.toy, (int)(world.mapgrid.length*2*s),(int)(world.mapgrid[0].length*2*s), null);
        }
//draws apparatus
        {   
        final BufferedImage renderd = new BufferedImage(display.c.getWidth(), display.c.getHeight(), 2);
        for (int i = 0; i < world.apparatus.size(); i++) {
            BufferedImage image = world.apparatus.get(i).apparatusImage;
            final double rads = Math.toRadians(-world.apparatus.get(i).heading-180);
            final double sin = Math.abs(Math.sin(rads));
            final double cos = Math.abs(Math.cos(rads));
            final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
            final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
            final AffineTransform at = new AffineTransform();
            at.translate((w / 2), h / 2);
            at.translate(((world.apparatus.get(i).x * s) + IM.tox) - (image.getWidth() / 2),((world.apparatus.get(i).y * s) + IM.toy) - (image.getHeight() / 2));
            at.rotate(rads,0, 0);
            at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
            //at.scale(.75, .75);
            final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            rotateOp.filter(image,renderd);
            
        }
        g.drawImage(renderd,0,0,
        null);
    }
    }

//draws box in the uper righthand corner
    public static void mapui(Graphics2D g) {
        if(world.running){

    //damage report box
        int boxx = display.width-220; int boxy = 10;
        g.setColor(new Color(20,20,20,200));
        g.fillRect(boxx, boxy, 210, 100);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(new Color(230,230,230));
        g.drawString(withSuffix((int)world.ACRES_BURNED) + " acres burned", boxx + 10, boxy + 25);
        g.drawString(withSuffix((int)world.BUILDINGS_DESTROYED) + " buildings destroyed", boxx + 10, boxy + 55);
    //apparatus report box
        if(UIdistributor.selectedApparatus != null){
        boxx = 10; boxy = 10;
        g.setColor(new Color(20,20,20,200));
        g.fillRect(boxx, boxy, 210, 100);
        g.drawString(UIdistributor.selectedApparatus.x + " " + UIdistributor.selectedApparatus.y, 100, 100);
        //Callsign
        font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        g.setColor(new Color(180,180,180));
        g.drawString("Callsign", boxx + 10, boxy + 20);

        font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(new Color(230,230,230));
        g.drawString(UIdistributor.selectedApparatus.name, boxx + 10, boxy + 40);
        
        //Type
        font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        g.setColor(new Color(180,180,180));
        g.drawString("Type", boxx + 10, boxy + 60);

        font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(new Color(230,230,230));
        g.drawString(UIdistributor.selectedApparatus.type, boxx + 10, boxy + 80);
        
        //Water
        font = new Font("Arial", Font.PLAIN, 12);
        g.setFont(font);
        g.setColor(new Color(180,180,180));
        g.drawString("Water", boxx + 10, boxy + 100);

        font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setColor(new Color(230,230,230));
        g.drawString(UIdistributor.selectedApparatus.water +"", boxx + 10, boxy + 120);

        g.drawImage(UIdistributor.selectedApparatus.apparatusImage, boxx + (200-UIdistributor.selectedApparatus.apparatusImage.getWidth()), boxy + 10, UIdistributor.selectedApparatus.apparatusImage.getWidth(), UIdistributor.selectedApparatus.apparatusImage.getHeight(),null);
        //g.drawString(UIdistributor.selectedApparatus.type, boxx + 10, boxy + 50);
        }
    }}

//creats sufix for big numbers ie. 2348 to 2.34K
    public static String withSuffix(long count) {
    if (count < 1000) return "" + count;
    int exp = (int) (Math.log(count) / Math.log(1000));
    return String.format("%.1f %c",count / Math.pow(1000, exp),"kMGTPE".charAt(exp-1));
}

//displays debug info
public static void debug(Graphics2D g) {
    g.drawString("scale: " + IM.scale, 10, 100);
    for(int i = 0; i < IM.keycode.length; i++){
        //IM.keycode[i]=false;
        g.setColor(new Color(20,20,20));
        if(IM.keycode[i])g.setColor(new Color(200,20,20));
        g.drawString((char)i+"", i*10, 10);
    }
    try{
        g.setColor(new Color(20,20,20));
        g.drawString("" + world.mapgrid[(int)((((IM.mx-IM.tox)/IM.scale)))][(int)((((IM.my-IM.toy)/IM.scale)))].fire  + " X: " +(int)((((IM.mx-IM.tox)/IM.scale))) + " Y: " + (int)((((IM.my-IM.toy)/IM.scale)))
        ,IM.mx,IM.my);
        g.drawString("Active tile?" + G.fire.active[(int)((((IM.mx-IM.tox)/IM.scale)))][(int)((((IM.my-IM.toy)/IM.scale)))] 
        ,IM.mx,IM.my+20);
    } catch ( Exception e){

    }
    
}
//does nothing but i spent a week trying to get it to work so i dont want to delete it

	public static void images(Graphics2D g, ArrayList<GameObject>  objects, int width, int height) {
        double s = IM.scale;
        for (int i = 0; i < objects.size(); i++){
            
            //g.drawImage(objects.get(i).txtr, (int) objects.get(i).x, (int) objects.get(i).y, null);
            g.drawImage(objects.get(i).txtr,
            (int) ((objects.get(i).x*s)+IM.tox),
            (int) ((objects.get(i).y*s)+IM.toy),
            (int) (objects.get(i).txtr.getWidth()*objects.get(i).scale*s),
            (int) (objects.get(i).txtr.getHeight()*objects.get(i).scale*s),
            null);
            //g.drawImage(objects.get(i).txtr, 
            //AffineTransform.getTranslateInstance(objects.get(i).x, objects.get(i).y),
            //null);
            //AffineTransform.getScaleInstance(sx, sy)
            /*for (int x = 0; x < objects.get(i).txtr.getWidth(); x++){
                for (int y = 0; y < objects.get(i).txtr.getHeight(); y++){
                    float  objx = objects.get(i).x;
                    float  objy = objects.get(i).y;
                    float  objscale = (float) (objects.get(i).scale * IM.scale);
                    Color c = new Color(
                        objects.get(i).txtr.getRGB( x, y),true
                    );
                    //System.out.println("R:" + c.getRed() + " G:" + c.getGreen() + " B:" + c.getBlue());
                    int range;
                    float  px = (x * objscale) + objx;
                    float  py = (y * objscale) + objy;
                    float  px1 = px + objscale;
                    float  py1 = py + objscale;
                    float area = 0;
                    //System.out.println(px +" - "+ py);
                    if(Math.round(py1) == py1){
                        range = Math.round(py1-(int)(py)); 
                    } else {
                        range = (int)py1-(int)(py)+1;
                    }
                    //System.out.println(range);
                    for (int rx = 0; rx <= range; rx++){ 
                        //System.out.println(rx);
                        for (int ry = 0; ry <= range; ry++){ 
                            area = 0;
                            if(rx == 0 && ry == 0){
                                area = ((int)(px+1)-px)*((int)(py+1)-py);
                                //System.out.println("1,1 "+area);
                            }
                            if(ry == 0 && rx != 0 && rx != range){
                                //area = (int)(py+1)-py;
                                //System.out.println("1,2 "+area);
                            }
                            if (ry == 0 && rx == range){
                                area = ((int)(py+1)-py)*(px-(int)(px));
                                //System.out.println("1,3 "+area);
                            }


                            if(rx == 0 && ry != 0 && ry != range){
                                //area = (int)(px+1)-px;
                                //System.out.println("2,1 "+area);
                            }
                            if(rx != 0 && rx != range && ry != 0 && ry != range){
                                //area = 1;
                                //System.out.println("2,2 "+area);
                            }
                            if(rx == range && ry != 0 && ry != range){
                                //area = (px-(int)(px));
                                //System.out.println("2,3 "+area);
                            }


                            if(ry == range && rx == 0){
                                area = ((int)(px+1)-px)*(py1-(int)py1);
                                //System.out.println("3,1 "+area);
                            }
                            if(ry == range && rx == range){
                                area = (px1-(int)px1)*(py1-(int)py1);
                                //System.out.println("3,1 "+area);
                            }
                            


                            if(range == 1){
                                area = (px1-px)*(py1-py);
                            }


                            
                            g.setColor(new Color(c.getRed(),c.getGreen(),c.getBlue(),(int) (c.getAlpha()*area)));  
                            g.drawLine((int)px+rx,(int)py+ry,(int)px+rx,(int)py+ry); 
                        }
                    }
                    //.setColor(new Color(255,0,0,255));
                    //g.setColor(new Color(c.getRed(),c.getGreen(),c.getBlue(),(int) (c.getAlpha()*area)));
                    //g.drawLine();
                }
                            
            }
            */
        }
    }
    
    

}

