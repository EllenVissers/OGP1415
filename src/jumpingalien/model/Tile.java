package jumpingalien.model;
import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class Tile extends AllObjects{

	/**
	 * Initialize a new tile object with given position, world and feature.
	 * @param 	pixelX
	 * 			The horizontal position of the bottom-left pixel of the tile.
	 * @param 	pixelY
	 * 			The vertical position of the bottom-left pixel of the tile.
	 * @param 	world
	 * 			The world this tile object is part of.
	 * @param 	feature
	 * 			The feature this tile object has.
	 */
	public Tile(double pixelX, double pixelY, World world, int feature) {
		super(pixelX,pixelY,world);
		this.feature = feature;
	}
	
	/**
	 * Variable registering the feature attributed to this tile object.
	 */
	private int feature;
	
	/**
	 * Return the feature of this tile.
	 * @return	The feature of this tile.
	 */
	public int getFeature() {
		return this.feature;
	}

	/**
	 * Return the horizontal velocity of this tile object.
	 * @return	Returns 0 because a tile does not move.
	 */
	@Basic @Override
	public double getXVelocity() {
		return 0;
	}

	/**
	 * Return the vertical velocity of this tile object.
	 * @return	Returns 0 because a tile object does not move.
	 */
	@Basic @Override
	public double getYVelocity() {
		return 0;
	}

	/**
	 * Return the horizontal acceleration of this tile object.
	 * @return	Returns 0 because a tile does not move.
	 */
	@Basic @Override
	public double getXAcc() {
		return 0;
	}

	/**
	 * Return the vertical acceleration of this tile object.
	 * @return	Returns 0 because a tile does not move.
	 */
	@Basic @Override
	public double getYAcc() {
		return 0;
	}
	
	/**
	 * Return whether the object is terminated.
	 * @return	Returns false, because a tile object cannot be terminated.
	 */
	@Override
	public boolean isTerminated() {
		return false;
	}

	/**
	 * Return the number of hitpoints of this game object.
	 * @return	Returns 0 because a tile object does not have any hitpoints.
	 */
	@Basic @Override
	public int getHitPoints() {
		return 0;
	}
	
	/**
	 * Return whether the tile object is passable.
	 * @return	Returns true if the tile object is passable.
	 */
	@Override
	public boolean isPassable() {
		return (getFeature() != 1);
	}
	
	/**
	 * Return whether the tile object is filled with magma.
	 * @return	Returns true if the tile object is filled with magma.
	 */
	@Override
	public boolean isMagma() {
		return (getFeature() == 3);
	}
	
	/**
	 * Return whether the tile object is filled with air.
	 * @return	Returns true if the tile object is filled with air.
	 */
	@Override
	public boolean isAir() {
		return (getFeature() == 0);
	}
	
	/**
	 * Return whether the tile object is filled with water.
	 * @return	Returns true if the tile object is filled with water.
	 */
	@Override
	public boolean isWater() {
		return (getFeature() == 2);
	}

	/**
	 * Return whether the tile object is ducking.
	 * @return	Returns false because a tile object cannot duck.
	 */
	@Override
	public boolean isDucking() {
		return false;
	}

	/**
	 * Return whether the tile object is jumping.
	 * @return	Returns false because a tile object cannot jump.
	 */
	@Override
	public boolean isJumping() {
		return false;
	}

	/**
	 * Return whether the tile object is moving.
	 * @return	Returns false because a tile object cannot move.
	 */
	@Override
	public boolean isMoving(Direction direction) {
		return false;
	}
	
	/**
	 * Return the width of the tile object.
	 */
	@Override
	public double getWidth() {
		return (getWorld().getTileSize());
	}

	/**
	 * Return the height of the tile object.
	 */
	@Override
	public double getHeight() {
		return (getWorld().getTileSize());
	}
}
