package Game;

import Display.Display;

public class Main {
	public static Display renderer;	//self explanatory variables
	public static camera Player;
	public static keylistener kl = new keylistener();
	static boolean running = true;
	static boolean rendering = true;
	//static int tickcap = 60;
	public static void main(String[] args) {
		renderer = new Display(800,600,"Forest Fire");	//initialize renderer
		Player = new camera(0,0,0.1);	//init camera
		//int frame = 0;	//tick rate tracking variables
		//int time = (int) System.currentTimeMillis()/1000;
		forest_generation.init("test_map");
		while (running){	//tick rate locker (sometimes skips a second of ticks. IDK why)
			/*if((int)System.currentTimeMillis()/1000>time&&frame<tickcap) {
				frame++;
				//renderer.update();
			}else {
				frame = 0;
				time = (int)System.currentTimeMillis()/1000;
			}*/
			renderer.render();
			Player.oninput();
			
		}
    }

}
