package E;
import java.awt.image.BufferedImage;

public class apparatus {
    public String name, type, action;
    public int xdest, ydest, speed, maxspeed, heading, water, maxWater;
    public double x, y, velocity, maxacceleration;
    public boolean active;
    public BufferedImage apparatusImage;
    public apparatus (
        String name, 
        String type,
        String action, 
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
        this.action = action;
        this.apparatusImage = apparatusImage;
        this.x = x;
        this.y = y;
        this.xdest = (int)x;
        this.ydest = (int)y;
        this.speed = speed;
        this.heading = heading;
        this.maxWater = maxWater;
        this.active = false;
        this.velocity = 0;
        this.maxacceleration = maxacceleration;
    }
}
