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

	@Override
	public double getXVelocity() {
		return 0;
	}

	@Override
	public double getYVelocity() {
		return 0;
	}

	@Override
	public double getXAcc() {
		return 0;
	}

	@Override
	public double getYAcc() {
		return 0;
	}
	
	@Override
	public boolean isTerminated() {
		return false;
	}
}
