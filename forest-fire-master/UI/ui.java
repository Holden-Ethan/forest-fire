package UI;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.GameObject;
import engine.assetldr;
import engine.button;
import engine.meth;
import engine.renderer;
import engine.textbox;
import engine.textfield;
import game.NewWorld;
import game.Tmain;
import game.world;

public class ui {
	public static int wrldH = 51;
	public static int wrldW = 51;
	public static String ui = "main menu"; // ui screen
	static BufferedImage box1 = assetldr.loadimg("boxs/1-5_box.png");
	static BufferedImage box2 = assetldr.loadimg("boxs/1-1.5_box.png");
	public static void main() {
		switch (ui) {
			case "main menu": 
				mainmenu.m();
			break;
			case "world": 
				worldui.m();
			break;
		}
	}
}

