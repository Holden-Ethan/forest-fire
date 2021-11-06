package game;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Random;

import engine.assetldr;


public class NewWorld {
	public NewWorld() {
		System.out.println("[The World]");
	}
	public void createforest(int worldH, int worldW){
        int wH = worldH;
        int wW = worldW;
        
		String filepath = "assets/saves/world.txt";
	    try {
	        File myObj = new File(filepath);
	        
	        if (myObj.createNewFile()) {//file does not exist
	        	
	          
	        } else { //file exist
	        	
	          System.out.println("Successfully wrote to the file.");
	          
	        }
	        
	      } catch (IOException e) {
	        System.out.println("FW1");
	        e.printStackTrace();
	      }
	    try {
	    	
	        FileWriter myWriter = new FileWriter(filepath);
	        int x = 0;
	        int x1 = 0;
	        for (int i = 0; i < wH; i++) {
	        	x=x+1;
        		
	        	
        		 for (int ii = 0; ii < wW; ii++) {
        			 BufferedImage img = assetldr.loadimg("saves/map.png");
        			 Color c = new Color(img.getRGB(i, ii));
        			
        			 myWriter.write(c.getGreen() +"-");
        			 
        		 }
        		 myWriter.write("\n");
        		//System.out.println(i);
        	}
	        Random rand = new Random(); 
	        //myWriter.write((char)(10+32)+","+(char)(10+32)+"-2|");
	        //myWriter.write();
	        myWriter.close();
    		

	        
	        
	      } catch (IOException e) {
	        System.out.println("FW1");
	        e.printStackTrace();
	      }
	}

}
