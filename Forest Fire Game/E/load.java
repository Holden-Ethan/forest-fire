package E;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;



public class load {
    public static BufferedImage image(String path){
        try {
			return ImageIO.read(new File("A/"+path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
}
