package E;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.Font;
import java.util.Random;   
import java.util.concurrent.ThreadLocalRandom;

import G.OpenSimplexNoise;
import G.world;
import java.awt.Color;
import java.awt.RenderingHints;


public class imagegenerator {
    static int random(int range) {
        return ThreadLocalRandom.current().nextInt(range);
    }
    public static BufferedImage button(Color color, String text, int height, int width, int xoffset, int yoffset, int fontsize) {
        BufferedImage button = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = button.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);        
        g2d.setColor(new Color(color.getRed()-50,color.getGreen()-50,color.getBlue()-50));
        Font font = new Font("Arial", Font.PLAIN, fontsize);
        g2d.setFont(font);
        g2d.drawString(text, xoffset, yoffset);
        return button;
    }
    public static BufferedImage rotate(BufferedImage image, int r) {
        final double rads = Math.toRadians(90*r);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);
        return rotatedImage;
    }
    public static BufferedImage rotatedeg(BufferedImage image, int angle) {
        final double rads = Math.toRadians(-angle-180);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads,0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image,rotatedImage);
        return rotatedImage;
    }
    public static BufferedImage landgen() {
    //draw pretty stuff delete the "false" if you want to see
        if(false)return prettyimage();
        BufferedImage map = new BufferedImage(world.mapgrid.length*2, world.mapgrid[0].length*2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = map.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        System.out.println("//");
        
        for (int x = 0; x < world.mapgrid.length; x++) {
            
            for (int y = 0; y < world.mapgrid[x].length; y++) {
                if(world.mapgrid[x][y] == null){

                }else {
                Random random = new Random(world.mapgrid[x][y].r);   
                


                switch (world.mapgrid[x][y].gID) {
                    case "grass":
                    g2d.setColor(new Color(167+ random.nextInt(20), 199, 127));
                    g2d.fillRect(
                        (int) (x),
                        (int) (y),
                        (int) (1),
                        (int) (1)
                    );
                    
                    break;
                    case "brush":
                    g2d.setColor(new Color(132, 181+ random.nextInt(20), 83));
                    g2d.fillRect(
                        (int) (x),
                        (int) (y),
                        (int) (1),
                        (int) (1)
                    );
                    break;
                    case "tree":
                    g2d.setColor(new Color(83, 138+ random.nextInt(20), 28));
                    g2d.fillRect(
                        (int) (x),
                        (int) (y),
                        (int) (1),
                        (int) (1)
                    );
                    break;
                    case "house":
                    g2d.setColor(new Color(133, 133, 133));
                    g2d.fillRect(
                        (int) (x),
                        (int) (y),
                        (int) (1),
                        (int) (1)
                    );
                    break;
                    case "water":
                    g2d.setColor(new Color(42, 147+ random.nextInt(20), 173));
                    g2d.fillRect(
                        (int) (x),
                        (int) (y),
                        (int) (1),
                        (int) (1)
                    );
                    break;
                   
                    case "MAP_ERROR":
                    g2d.setColor(new Color(255, 0, 255));
                    g2d.fillRect(
                        (int) (x),
                        (int) (y),
                        (int) (1),
                        (int) (1)
                    );
                    break;
                }
                }
            }
                }
            
        

            
            //g2d.drawRect(0, 0, newWidth - 0, newHeight + 6);
            g2d.dispose();

            return map;
    }
    public static BufferedImage prettyimage() {
        BufferedImage map = new BufferedImage(world.mapgrid.length*2, world.mapgrid[0].length*2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = map.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        System.out.println("//");
        OpenSimplexNoise noise = new OpenSimplexNoise(ThreadLocalRandom.current().nextLong());
        OpenSimplexNoise housenoise = new OpenSimplexNoise(ThreadLocalRandom.current().nextLong()+1);
        final double FEATURE_SIZE = 10;
        final double houseFEATURE_SIZE = 70;
        int min = 255;
        int[][] housearray = new int[world.mapgrid.length][world.mapgrid.length];

        System.out.println(min);
        for (int x = 0; x < world.mapgrid.length; x++) {
            
            for (int y = 0; y < world.mapgrid[x].length; y++) {
                if(world.mapgrid[x][y] == null){

                }else {
                Random random = new Random(world.mapgrid[x][y].r);   

                //g2d.setColor(new Color((int) Math.abs(noise.eval((x) / FEATURE_SIZE, (y) / FEATURE_SIZE, 0.0)*255), (int) Math.abs(noise.eval((x) / FEATURE_SIZE, (y) / FEATURE_SIZE, .3)*255), 0));
                //g2d.setColor(new Color((int) Math.abs(noise.eval((x) / FEATURE_SIZE, (y) / FEATURE_SIZE, 0.0)*255), (int) Math.abs(housenoise.eval((x) / houseFEATURE_SIZE, (y) / houseFEATURE_SIZE, 0)*255), 0));
                //g2d.setColor(new Color((int) ((housenoise.eval((x) / houseFEATURE_SIZE, (y) / houseFEATURE_SIZE, 0.0) > 0) ? housenoise.eval((x) / houseFEATURE_SIZE, (y) / houseFEATURE_SIZE, 0.0)*255 : 0),  0, 0));

                g2d.fillRect(
                        (int) (x),
                        (int) (y),
                        (int) (1),
                        (int) (1)
                    );
                }
            }
        }
    
    g2d.dispose();
    return map;
    }

}
