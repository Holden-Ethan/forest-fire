package engine;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

public class assetldr {
	public static BufferedImage loadimg (String path) {	//loads img from path
		try {
			return ImageIO.read(new File("assets/"+path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String loadfile (String path) {	//loads text from path
		StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines( Paths.get("assets/"+path), StandardCharsets.UTF_8)) 
	    {
	        stream.forEach(s -> contentBuilder.append(s).append("\n"));
	    }
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}
	public static BufferedImage compileimg (int width, int height, GameObject[][] gos) {
		BufferedImage bi = new BufferedImage (width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		for(int i = 0; i < gos.length; i++) {
			for(int j = 0; j < gos[i].length; j++) {				
				g.drawImage(gos[i][j].txtr, gos[i][j].x, gos[i][j].y,(int)(gos[i][j].txtr.getWidth()*gos[i][j].scale),(int)(gos[i][j].txtr.getHeight()*gos[i][j].scale), null);
		}}
		return bi;
	}
}
