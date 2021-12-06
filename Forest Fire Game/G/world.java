package G;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;   
import java.util.Random;   
import java.awt.Color;
import java.awt.Graphics2D;

import E.*;
public class world {
    public static fire f;
    public static boolean running = false;
    public static tile mapgrid[][];
    public static int mapsize = 1;
    public static double ACRES_BURNED, BUILDINGS_DESTROYED;
    static display r = start.r;
    public static ArrayList<apparatus> apparatus = new ArrayList<apparatus>();

    public static void start(int size) {
        
        mapsize = size;
        System.out.println(Thread.currentThread());
        ACRES_BURNED = 0; BUILDINGS_DESTROYED = 0;
        apparatus.add(new apparatus(
            "H" + ThreadLocalRandom.current().nextInt(100), //callsigh
            "bell 204", //type
            load.image("Bell 205.png"), //image
            100, //x cord
            100, //y cord
            0, //heding
            20, //max speed
            .01, //max acceleration
            9 //max water
        ));
        apparatus.add(new apparatus(
            "B" + ThreadLocalRandom.current().nextInt(100), //callsigh
            "Bulldozer", //type
            load.image("Bell 205.png"), //image
            100, //x cord
            100, //y cord
            0, //heding
            2, //max speed
            .01, //max acceleration
            0 //max water
        ));

        
        mapgrid = new tile[size][size];
        OpenSimplexNoise noise = new OpenSimplexNoise(ThreadLocalRandom.current().nextLong());
        OpenSimplexNoise housenoise = new OpenSimplexNoise(ThreadLocalRandom.current().nextLong()+1);
        final double FEATURE_SIZE = 30;
        final double houseFEATURE_SIZE = 100;
        int min = 255;
        int[][] housearray = new int[world.mapgrid.length][world.mapgrid.length];
//house perlin noise
        for (int x = 0; x < world.mapgrid.length; x++) {
            
            for (int y = 0; y < world.mapgrid[x].length; y++) {
                housearray[x][y] = 255-(int) Math.abs(((noise.eval((x) / FEATURE_SIZE, (y) / FEATURE_SIZE, 0.0)*50)+((housenoise.eval((x) / houseFEATURE_SIZE, (y) / houseFEATURE_SIZE, 0.0) > 0) ? housenoise.eval((x) / houseFEATURE_SIZE, (y) / houseFEATURE_SIZE, 0.0)*205 : 0)));
                min = (housearray[x][y] < min) ? housearray[x][y] : min;
            }
        }
//creats map:

//fragile handle with care
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double i = Math.abs(noise.eval((x) / FEATURE_SIZE, (y) / FEATURE_SIZE, 0.0))+(ThreadLocalRandom.current().nextInt(100)*.001);
                try{
                    if (Math.abs(noise.eval((x) / FEATURE_SIZE, (y) / FEATURE_SIZE, 0.0))+(ThreadLocalRandom.current().nextInt(100)*.0001) <= .03) {

                        mapgrid[x][y] = new tile("water", ThreadLocalRandom.current().nextInt(100000), 0);

                    } else if (rand((int) (Math.pow((housearray[x][y]-min) * 255/(255-min+0.0), 2)/1000.0)+1) ==0 && rand(7) == 0 && housearray[x][y] < 230) {

                        mapgrid[x][y] = new tile("house", ThreadLocalRandom.current().nextInt(100000), 0);

                    } else if (i <= .2) {

                        mapgrid[x][y] = new tile("tree", ThreadLocalRandom.current().nextInt(100000), 0);

                    } else if (i <= .4) {

                        mapgrid[x][y] = new tile("brush", ThreadLocalRandom.current().nextInt(100000), 0);

                    } else if (i > .4) {

                        mapgrid[x][y] = new tile("grass", ThreadLocalRandom.current().nextInt(100000), 0);

                    } else {

                        mapgrid[x][y] = new tile("MAP_ERROR", ThreadLocalRandom.current().nextInt(100000), 0);
                }
                }catch(Exception e){}
                
            }
        }
        
        System.out.println("go");
//center the map
        IM.scale = display.height/(size+0.0);
        IM.tox = (display.width-(size*IM.scale))/2;
        IM.toy = 0;
//genarates the map image
        compile.map = imagegenerator.landgen();

//starts fire thread, simulation and initial fire
        f =  new  fire (size,"Engine");
		f.setName("fire");
        f.start();

        runApparatus();
    }

    public static void runApparatus() {
    
        for (int i = 0; i < apparatus.size(); i++) {
            apparatus ap = apparatus.get(i);
            boolean atDestination = false;


            
                if(Math.sqrt(((ap.x-ap.xdest)*(ap.x-ap.xdest))+((ap.y-ap.ydest)*(ap.y-ap.ydest))) < .5){
                atDestination = true;
                ap.velocity = 0;
            }

            
            switch(apparatus.get(i).type) {
                case "bell 204":
                    //apparatus movment
                    move(ap, atDestination);

                    //pick up water
                    fillWater(ap, atDestination);

                    ///drop water
                    dropWater(ap, atDestination); 

                    //autopilot
                    //go to fire
                    autopilotGoToFire(ap, atDestination);
                    //go to water
                    autopilotGoToWater(ap, atDestination);
                break;
                case "Bulldozer":
                    move(ap, atDestination);
                break;
                default:
                  // code block
              }
        
        }    
    }
    static void bulldoze(apparatus ap, boolean atDestination){
    
    }


    static void autopilotGoToWater(apparatus ap, boolean atDestination){
        if(atDestination && ap.autopilot && ap.water == 0){
            int xs = (int) ap.x;
            int ys = (int) ap.y;
            int closeX = xs;
            int closeY = ys;
            double closeDist = 200;
            for (int ox = xs-50; ox < xs+50; ox++) {
                for (int oy = ys-50; oy < ys+50; oy++) {
                    if(mapgrid[ox][oy].gID == "water" && Math.sqrt((xs-ox)*(xs-ox)+(ys-oy)*(ys-oy)) < closeDist ){
                        closeDist = Math.sqrt((xs-ox)*(xs-ox)+(ys-oy)*(ys-oy));
                        closeX = ox;
                        closeY = oy;
                    }
                }
            }
            //if(closeDist > 1){
                ap.xdest = closeX;
                ap.ydest = closeY;
            //}

        }
    }
    
    
    static void autopilotGoToFire(apparatus ap, boolean atDestination){
        if(atDestination && ap.autopilot && ap.water != 0){
            int xs = (int) ap.x;
            int ys = (int) ap.y;
            int closeX = xs;
            int closeY = ys;
            double closeDist = 200;
            for (int ox = xs-50; ox < xs+50; ox++) {
                for (int oy = ys-50; oy < ys+50; oy++) {
                    if(fire.active[ox][oy] && Math.sqrt((xs-ox)*(xs-ox)+(ys-oy)*(ys-oy)) < closeDist ){
                        closeDist = Math.sqrt((xs-ox)*(xs-ox)+(ys-oy)*(ys-oy)) + checkNeighbor(ox, oy);
                        closeX = ox;
                        closeY = oy;
                    }
                }
            }
            //if(closeDist > 1){
                ap.xdest = closeX;
                ap.ydest = closeY;
            //}

        }
    }


    static void dropWater(apparatus ap, boolean atDestination) {
        if(
                fire.active[(int)ap.xdest][(int)ap.ydest] && 
                atDestination &&
                ap.water >= 9
            ){
                BufferedImage map = compile.map;
                Graphics2D g2d = map.createGraphics();
                //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Random random = new Random();   
                for (int ox = -1; ox <= 1; ox++) {
                    for (int oy = -1; oy <= 1; oy++) {
                            int x = ap.xdest+ox;
                            int y = ap.ydest+oy;
                        if(mapgrid[x][y].fire != 0 && fire.active[x][y]) {
                            int ii = 100 + random.nextInt(15);
                            g2d.setColor(new Color(ii, ii, ii));
                            g2d.fillRect(
                                (int) (x),
                                (int) (y),
                                (int) (1),
                                (int) (1)
                            );
                            fire.active[x][y] = false;
                        }
                        
                    }
                }
                ap.water = 0;
            }
    }


    static void move(apparatus ap, boolean atDestination){
        System.out.println(ap.speed);
        if(!atDestination){
        if(ap.velocity < 1)ap.velocity += ap.maxacceleration;
        ap.heading = (int) Math.toDegrees(Math.atan2((ap.xdest)-ap.x, (ap.ydest)-ap.y));
        ap.x += Math.sin(Math.toRadians(ap.heading))*(ap.speed*0.017*ap.velocity);
        ap.y += Math.cos(Math.toRadians(ap.heading))*(ap.speed*0.017*ap.velocity);
        }
        if(Math.sqrt(((ap.x-ap.xdest)*(ap.x-ap.xdest))+((ap.y-ap.ydest)*(ap.y-ap.ydest))) < (ap.velocity * ap.velocity)/(2*ap.maxacceleration)){
            System.out.println((ap.velocity * ap.velocity)/(2*ap.maxacceleration));
            ap.velocity -= ap.maxacceleration*2;
        }
    }


    static void fillWater(apparatus ap, boolean atDestination){
        if(
                mapgrid[(int)ap.xdest][(int)ap.ydest].gID == "water" && 
                atDestination &&
                ap.maxWater > ap.water
                )ap.water = ap.maxWater;
    }


    static int checkNeighbor(int x, int y){
        int count = 0;
        for (int ox = x-2; ox < x+2; ox++) {
            for (int oy = y-2; oy < y+2; oy++) {
                if(mapgrid[ox][oy].fire > 0){
                    count++;
                }
            }
        }
        return count;
    }
    
    static int rand(int range){ return ThreadLocalRandom.current().nextInt(range);}
    static void print(int x){System.out.println(x);}
    static void rand(String x){System.out.println(x);}
}
