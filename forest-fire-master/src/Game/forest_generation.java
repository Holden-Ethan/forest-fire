package Game;

import java.awt.image.BufferedImage;
import java.util.Random; 
//asset loader has a function for converting files to strings/images

public class forest_generation {
	public static tree[] forest;
	public static int fsx, fsy;
	public static int[][] heightmap;
	public static int[][] tile_data;
	static BufferedImage hm;
	public static String map_directory;
	public static void init(String map_dir){  //called on start. trees = # of trees, size = area they spawn in
		map_directory = map_dir;
		hm = assetldr.loadimg("maps/"+map_dir+"/Hight_map.png");
		int trees = Integer.parseInt(assetldr.loadfile("maps/"+map_dir+"/map_data.txt").split(System.getProperty("line.separator"))[0].split(":")[1]);
		fsx = hm.getWidth();
		fsy = hm.getHeight();
		heightmap = new int[fsx][fsy];
		tile_data = new int[fsx][fsy];
		String[] rawdata = assetldr.loadfile("maps/"+map_dir+"/tile_data.txt").split(System.getProperty("line.separator"));
		forest = new tree[trees];	//define size of forest
		for(int x=0;x<fsx;x++) {
			String[] refinedata = rawdata[x].split(" ");
			for(int y=0;y<fsy;y++) {
				int rgb = hm.getRGB(x, y);
				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF);
				int greyscale = (r+g+b)/3;
				heightmap[x][y] = greyscale;
				tile_data[x][y] = Integer.parseInt(refinedata[y]);
			}
		}
		for (int i = 0; i < trees; i++) {	//for slot in forest
			tree t = place_tree(trees); //try to spawn a tree
			if(t != null) { //if place_tree does return a tree
				forest[i] = t; //add it to forest
			}else {	//otherwise
				i-=1; //try again
			}
		
		}
	}
	
	public static tree place_tree(int trees) {	//plant tree function
		Random random = new Random(); 	//
		int x = random.nextInt(fsx);
		int y = random.nextInt(fsy);	//random x,y
		for(int i = 0; i < trees; i++) {//for every slot in forest
			if(forest[i] != null) {	//if there is something there
				if(Math.sqrt((forest[i].x-x)*(forest[i].x-x) + (forest[i].y-y)*(forest[i].y-y)) < 50) //make sure it isn't too close
					return null; //if it is, return null
			}
		}	
		BufferedImage txtr = assetldr.loadimg("imgs/tree.png");
		return new tree(x-(txtr.getWidth()/2),y-(txtr.getHeight()/2),txtr); //if there is aren't any trees that are too close, return a tree
	}
}
