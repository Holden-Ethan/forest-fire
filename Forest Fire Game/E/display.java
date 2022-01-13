package E;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import G.start;
import G.world;
import java.awt.Font;
import java.awt.RenderingHints;

public class display extends Thread {
	public static int width;
    public static int height;
	public static boolean fullscrean;
    public static long[] avrage = new long[200];
    int n = 0;
    public int buffers;
	double FPS = -1;
    float defaultw, defaulth;
    JFrame jf;
    static Canvas c;
    BufferStrategy bs;
    Graphics2D g;
    IM im;
    Dimension d;
    String name;
    public ArrayList<GameObject> images = new ArrayList<GameObject>();
    GameObject currentgo;
	
    public display(int wid, int hgt, String name, IM im, int buffers) {
		
		width = wid; height = hgt; this.im = im; defaultw = wid; defaulth = hgt;
		this.buffers = buffers; this.name = name;
		
		jf = new JFrame(name);	//makes screen, sets it up, etc.
		d = new Dimension(width,height);
		jf.setPreferredSize(d);
		//jf.setUndecorated(true);
		//jf.setMinimumSize(d);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = new Canvas();
		//c.setPreferredSize(d);
		//c.setMinimumSize(d);
		//c.setSize(d);
		c.setBackground(new Color(30,30,30));
		jf.add(c);
		jf.pack();	
		c.addKeyListener(im);	//keyboard input
		jf.addKeyListener(im);  
		jf.addMouseListener(im);
		jf.addMouseMotionListener(im);
		jf.addMouseWheelListener(im);
		c.addMouseWheelListener(im);
		c.addMouseListener(im);
		c.addMouseMotionListener(im);
		//jf.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		jf.setVisible(true);
		//GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		//gd.setFullScreenWindow(jf);
		//Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(load.image("WallMouseIcon.png"), new Point(0, 0), "blank cursor");	
		//jf.setCursor(blankCursor);
		//jf.setCursor(Cursor.);
		// NORMAL Indicates that no state bits are set.
		// ICONIFIED
		// MAXIMIZED_HORIZ
		// MAXIMIZED_VERT
		// MAXIMIZED_BOTH Concatenates MAXIMIZED_HORIZ and MAXIMIZED_VERT.
		
	}
    
    public void add(int x, int y, float scale, BufferedImage txtr){
        images.add(new GameObject(x, y, scale, txtr));
    }

	public void addbutton(int  x, int  y, String name, BufferedImage txtr){
        UIdistributor.add(new Button(x, y, name, txtr));
    }
	public void removebutton(String name){
		for (int i = 0; i < UIdistributor.buttons.size(); i++){
		if(UIdistributor.buttons.get(i).name == name)UIdistributor.buttons.remove(i);
		}
    }


	int i = 0;
	int prevlayersize;

	public void render() {
		width = jf.getWidth();
		height = jf.getHeight();
		bs = c.getBufferStrategy();
		if(bs==null) {
			c.createBufferStrategy(buffers);
			return;
		}
		g = (Graphics2D)bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
		
//display stand-alone images
		compile.images(g, images,(int)d.getWidth(),(int)d.getHeight());
//if the world is active display map and aircraft
		if(world.mapgrid != null)compile.map(g);
//display UI
		UIdistributor.render(g);
//display map UI
		compile.mapui(g);
//detect process key presses
		IM.keyfunctions();
//if in debug mode display
		if(start.debug)compile.debug(g);
//display FPS
		Font font = new Font("Serif", Font.PLAIN, 16);
		g.setColor(new Color(120,120,120)); 
      	g.setFont(font);
        g.drawString("FPS: "+ FPS, 5, 16);
		g.fillRect(20, 20, 2, 2);

		bs.show();
		g.dispose();
    }
    


	long Ftickend = 0;
    long Ftickstart = 0;
	double framespersecond = 1000/58;
    @Override
	public void run() {
		System.out.println(Thread.currentThread());
		while (true){
			Ftickstart =  System.currentTimeMillis();
			if(Ftickstart-Ftickend>=framespersecond){
				FPS = 1000/(Ftickstart-Ftickend);
				Ftickend = System.currentTimeMillis();
				//System.out.println(jf.);
				render();
				//System.out.println(1/(float)(Ftickend-Ftickstart)*1000000000);
				
				
			}
			
		}
	}

    





}