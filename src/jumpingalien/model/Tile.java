package jumpingalien.model;

public class Tile {

	public Tile(int pixelX, int pixelY, World world, int feature) {
		this.x = pixelX;
		this.y = pixelY;
		this.world = world;
		this.feature = feature;
	}
	
	private int x;
	private int y;
	private int feature;
	private World world;
	
	public int getXPosition() {
		return this.x;
	}
	
	public int getYPosition() {
		return this.y;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public int getFeature() {
		return this.feature;
	}
	
}
