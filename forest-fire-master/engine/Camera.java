package engine;

public class Camera {
	public int x,y,layers;
	public float scale;
	public static float xwindowscale;
	public static float ywindowscale;
	public float[] xoffset, yoffset;
	public Camera (int x, int y, int layers) {
		this.x = x;
		this.y = y;
		this.layers = layers;
		scale = 1;
		xoffset = new float[layers];
		yoffset = new float[layers];
	}
}
