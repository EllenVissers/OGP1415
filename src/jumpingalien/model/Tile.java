package jumpingalien.model;

import jumpingalien.part3.programs.IProgramFactory.Direction;

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

	@Override
	public int getHitPoints() {
		return 0;
	}
	
	@Override
	public boolean isPassable() {
		return (getFeature() != 1);
	}
	
	@Override
	public boolean isMagma() {
		return (getFeature() == 3);
	}
	
	@Override
	public boolean isAir() {
		return (getFeature() == 0);
	}
	
	@Override
	public boolean isWater() {
		return (getFeature() == 2);
	}

	@Override
	public boolean isDucking() {
		return false;
	}

	@Override
	public boolean isJumping() {
		return false;
	}

	@Override
	public boolean isMoving(Direction direction) {
		return false;
	}
	
	@Override
	public double getWidth() {
		return (getWorld().getTileSize());
	}

	@Override
	public double getHeight() {
		return (getWorld().getTileSize());
	}
}
