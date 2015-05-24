package jumpingalien.model;
/**
 * A class of Tiles involving a horizontal and vertical position, a world and a feature.
 * 
 * @author Ellen Vissers, Nina Versin
 * @version 1.0
 */
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
		if (world == null)
			this.feature = 0;
		else
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
	 * Return whether the tile object is passable.
	 * @return	Returns true if the tile object is passable.
	 */
	public boolean isPassable() {
		return (getFeature() != 1);
	}
	
	/**
	 * Return whether the tile object is filled with magma.
	 * @return	Returns true if the tile object is filled with magma.
	 */
	public boolean isMagma() {
		return (getFeature() == 3);
	}
	
	/**
	 * Return whether the tile object is filled with air.
	 * @return	Returns true if the tile object is filled with air.
	 */
	public boolean isAir() {
		return (getFeature() == 0);
	}
	
	/**
	 * Return whether the tile object is filled with water.
	 * @return	Returns true if the tile object is filled with water.
	 */
	public boolean isWater() {
		return (getFeature() == 2);
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
