package E;
import java.awt.image.BufferedImage;

public class apparatus {
    public String name, type;
    public int xdest, ydest, speed, maxspeed, heading, water, maxWater;
    public double x, y, velocity, maxacceleration;
    public boolean autopilot;
    public BufferedImage apparatusImage;
    public apparatus (
        String name, 
        String type, 
        BufferedImage apparatusImage, 
        double x, 
        double y, 
        int heading, 
        int speed,
        double maxacceleration,
        int maxWater
        

        ){
        this.name = name;
        this.type = type;
        this.apparatusImage = apparatusImage;
        this.x = x;
        this.y = y;
        this.xdest = (int)x;
        this.xdest = (int)y;
        this.speed = speed;
        this.heading = heading;
        this.maxWater = maxWater;
        this.autopilot = false;
        this.velocity = 0;
        this.maxacceleration = maxacceleration;
    }
}
