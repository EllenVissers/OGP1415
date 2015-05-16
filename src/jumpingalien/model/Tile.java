package jumpingalien.model;

public class Tile extends AllObjects{

	public Tile(double pixelX, double pixelY, World world, int feature) {
		super(pixelX,pixelY,world);
		this.feature = feature;
	}
	
	private int feature;
	
	public int getFeature() {
		return this.feature;
	}
	
}
