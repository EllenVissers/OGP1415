package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Mazub;
import jumpingalien.model.Orientation;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart2 {

	public Facade() {
	}
	
	// OOK ALLE METHODEN UIT PART1
	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Mazub(pixelLeftX, pixelBottomY, sprites);
	}

	@Override
	public int[] getLocation(Mazub alien) {
		int[] a = {(int) Math.round(alien.getXPosition()),(int) Math.round(alien.getYPosition())};
		return a;
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		double[] b = {alien.getXVelocity(), alien.getYVelocity()};
		return b;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		double[] c = {0,0};
		if (alien.isMovingLeft())
			c[0] = -alien.getXAcc();
		if (alien.isMovingRight())
			c[0] = alien.getXAcc();
		if (alien.isJumping())
			c[1] = alien.getYAcc();
		return c;
	}

	@Override
	public int[] getSize(Mazub alien) {
		int[] d = {alien.getCurrentSprite().getWidth(), alien.getYSize()};
		return d;
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		try {
			alien.startJump();
		} catch (IllegalStateException e) {
		}
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove(Orientation.LEFT);
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove(Orientation.LEFT);
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove(Orientation.RIGHT);
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove(Orientation.RIGHT);
	}

	@Override
	public void startDuck(Mazub alien) {
		alien.startDuck();
	}

	@Override
	public void endDuck(Mazub alien) {
		alien.endDuck();
	}
	
	// EN DIE UIT FACADE 2
	
	/**
	 * Returns the current number of hitpoints of the given alien.
	 */
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitPoints();
	}

	/**
	 * Create a new game world with the given parameters.
	 * 
	 * @param tileSize
	 *            Length (in pixels) of a side of each square tile in the world
	 * @param nbTilesX
	 *            Number of tiles in the horizontal direction
	 * @param nbTilesY
	 *            Number of tiles in the vertical direction
	 * @param visibleWindowWidth
	 *            Width of the visible window, in pixels
	 * @param visibleWindowHeight
	 *            Height of the visible window, in pixels
	 * @param targetTileX
	 *            Tile x-coordinate of the target tile of the created world
	 * @param targetTileY
	 *            Tile y-coordinate of the target tile of the created world
	 */
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY, int visibleWindowWidth, int visibleWindowHeight, 
			int targetTileX, int targetTileY) {
		return new World(tileSize,nbTilesX,nbTilesY,visibleWindowWidth,visibleWindowHeight,targetTileX,targetTileY);
	}

	/**
	 * Returns the size of the given game world, in number of pixels.
	 * 
	 * @param world
	 *            The world for which to return the size.
	 * @return The size of the game world, in pixels, as an array of two
	 *         elements: width (X) and height (Y), in that order.
	 */
	public int[] getWorldSizeInPixels(World world) {
		int[] result = {world.getTileLength()*world.nbTilesX, world.getTileLength()*world.nbTilesY};
		return result;
	}

	/**
	 * Returns the length of a square tile side in the given world.
	 * 
	 * @param world
	 *            The game world for which to retrieve the tile length
	 * 
	 * @return The length of a square tile side, expressed as a number of
	 *         pixels.
	 */
	public int getTileLength(World world) {
		return world.getTileLength();
	}

	/**
	 * Starts the game that is played in the given world.
	 * After this method has been invoked, no further game objects will be added
	 * via {@link IFacadePart2#addPlant(World, Plant)},
	 * {@link IFacadePart2#addShark(World, Shark)},
	 * {@link IFacadePart2#addSlime(World, Slime)}, or
	 * {@link IFacadePart2#setMazub(World, Mazub)}), and no geological features
	 * will be changed via
	 * {@link IFacadePart2#setGeologicalFeature(World, int, int, int)}.
	 * 
	 * @param world
	 * 			The world for which to start the game.
	 */
	public void startGame(World world) {
		
	}

	/**
	 * Returns whether the game, played in the given game world, is over.
	 * The game is over when Mazub has died, or has reached the target tile.
	 * 
	 * @param world
	 *            The world for which to check whether the game is over
	 * @return true if the game is over, false otherwise.
	 */
	public boolean isGameOver(World world) {
		
	}

	/**
	 * Returns whether the game played in the given world has finished and the
	 * player has won. The player wins when Mazub has reached the target tile.
	 * 
	 * @param world
	 *            The world for which to check whether the player won
	 * @return true if the game is over and the player has won; false otherwise.
	 */
	public boolean didPlayerWin(World world) {
		
	}

	/**
	 * Advance the time for the world and all its objects by the given amount.
	 * 
	 * This method replaces {@link IFacadePart2#advanceTime(Mazub, double)}.
	 * 
	 * @param world
	 *            The world whose time needs to advance
	 * @param dt
	 *            The time interval (in seconds) by which to advance the given
	 *            world's time.
	 */
	public void advanceTime(World world, double dt) {
		
	}

	/**
	 * @deprecated This method no longer needs to be implemented in your facade.
	 *             It has been replaced by
	 *             {@link IFacadePart2#advanceTime(World, double)}.
	 */
	@Override
	@Deprecated
	/*default*/ public void advanceTime(Mazub alien, double dt) {
		throw new IllegalStateException(
				"This method should no longer be implemented or called");
	};

	/**
	 * Return the coordinates of the rectangular visible window that moves
	 * together with Mazub.
	 * 
	 * @return The pixel coordinates of the visible window, in the order
	 *         <b>left, bottom, right, top</b>.
	 */
	public int[] getVisibleWindow(World world) {
		
	}

	/**
	 * Returns the bottom left pixel coordinate of the tile at the given tile
	 * position.
	 * 
	 * @param world
	 *            The world from which to retrieve the tile.
	 * @param tileX
	 *            The x-position x_T of the tile
	 * @param tileY
	 *            The y-position y_T of the tile
	 * @return An array which contains the x-coordinate and y-coordinate of the
	 *         bottom left pixel of the given tile, in that order.
	 */
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		int[] result = {tileX*world.getTileLength(),tileY*world.getTileLength()};
		return result;
	}

	/**
	 * Returns the tile positions of all tiles within the given rectangular
	 * region.
	 * 
	 * @param world
	 *            The world from which the tile positions should be returned.
	 * @param pixelLeft
	 *            The x-coordinate of the left side of the rectangular region.
	 * @param pixelBottom
	 *            The y-coordinate of the bottom side of the rectangular region.
	 * @param pixelRight
	 *            The x-coordinate of the right side of the rectangular region.
	 * @param pixelTop
	 *            The y-coordinate of the top side of the rectangular region.
	 * 
	 * @return An array of tile positions, where each position (x_T, y_T) is
	 *         represented as an array of 2 elements, containing the horizontal
	 *         (x_T) and vertical (y_T) coordinate of a tile in that order.
	 *         The returned array is ordered from left to right,
	 *         bottom to top: all positions of the bottom row (ordered from
	 *         small to large x_T) precede the positions of the row above that.
	 * 
	 */
	public int[][] getTilePositionsIn(World world, int pixelLeft, int pixelBottom, int pixelRight, int pixelTop) {
		
	}

	/**
	 * Returns the geological feature of the tile with its bottom left pixel at
	 * the given position.
	 * 
	 * @param world
	 *            The world containing the tile for which the
	 *            geological feature should be returned.
	 * 
	 * @param pixelX
	 *            The x-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * @param pixelY
	 *            The y-position of the pixel at the bottom left of the tile for
	 *            which the geological feature should be returned.
	 * 
	 * @return The type of the tile with the given bottom left pixel position,
	 *         where
	 *         <ul>
	 *         <li>the value 0 is returned for an <b>air</b> tile;</li>
	 *         <li>the value 1 is returned for a <b>solid ground</b> tile;</li>
	 *         <li>the value 2 is returned for a <b>water</b> tile;</li>
	 *         <li>the value 3 is returned for a <b>magma</b> tile.</li>
	 *         </ul>
	 * 
	 * @note This method must return its result in constant time.
	 * 
	 * @throw ModelException if the given position does not correspond to the
	 *        bottom left pixel of a tile.
	 */
	public int getGeologicalFeature(World world, int pixelX, int pixelY) throws ModelException {
		int[] tilepos = world.getTile(pixelX,pixelY);
		return world.getFeatureAt(tilepos[0], tilepos[1]); // geeft nog tiletype ipv nummer gelinkt aan tiletype
	}

	/**
	 * Modify the geological type of a specific tile in the given world to a
	 * given type.
	 * 
	 * @param world
	 *            The world in which the geological type of a tile needs to be
	 *            modified
	 * @param tileX
	 *            The x-position x_T of the tile for which the type needs to be
	 *            modified
	 * @param tileY
	 *            The y-position y_T of the tile for which the type needs to be
	 *            modified
	 * @param tileType
	 *            The new type for the given tile, where
	 *            <ul>
	 *            <li>the value 0 is provided for an <b>air</b> tile;</li>
	 *            <li>the value 1 is provided for a <b>solid ground</b> tile;</li>
	 *            <li>the value 2 is provided for a <b>water</b> tile;</li>
	 *            <li>the value 3 is provided for a <b>magma</b> tile.</li>
	 *            </ul>
	 */
	public void setGeologicalFeature(World world, int tileX, int tileY, int tileType) {
		
	}

	/**
	 * Sets the given alien as the player's character in the given world.
	 * 
	 * @param world
	 *            The world for which to set the player's character.
	 * @param mazub
	 *            The alien to be set as the player's character.
	 */
	public void setMazub(World world, Mazub alien) {
		
	}

	/**
	 * Returns whether the given alien is currently immune against enemies (see
	 * section 1.2.5 of the assignment).
	 * 
	 * @param alien
	 *            The alien for which to retrieve the immunity status.
	 * @return True if the given alien is immune against other enemies (i.e.,
	 *         there are no interactions between the alien and enemy objects).
	 */
	public boolean isImmune(Mazub alien) {
		return alien.isImmune();
	}

	/**
	 * Creates a new plant, located at the provided pixel location (x, y).
	 * The returned plant should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the plant's initial position
	 * @param y
	 *            The y-coordinate of the plant's initial position
	 * @param sprites
	 *            An array of sprites for the new plant
	 * 
	 * @return A new plant, located at the provided location. The returned plant
	 *         should not belong to a world.
	 */
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		return new Plant(x,y,sprites);
	}

	/**
	 * Add the given plant as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the plant should be added.
	 * @param plant
	 *            The plant that needs to be added to the world.
	 */
	public void addPlant(World world, Plant plant) {
		
	}

	/**
	 * Returns all the plants currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all plants.
	 * @return All plants that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each plant may only be returned once.
	 */
	public Collection<Plant> getPlants(World world) {
		return world.getAllPlants();
	}

	/**
	 * Returns the current location of the given plant.
	 * 
	 * @param plant
	 *            The plant of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given plant's bottom left pixel in the world.
	 */
	public int[] getLocation(Plant plant) {
		int[] p = {(int) Math.round(plant.getXPosition()), (int) Math.round(plant.getYPosition())};
		return p;
	}

	/**
	 * Return the current sprite image for the given plant.
	 * 
	 * @param plant
	 *            The plant for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given plant, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}

	/**
	 * Creates a new shark, located at the provided pixel location (x, y).
	 * The returned shark should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the shark's initial position
	 * @param y
	 *            The y-coordinate of the shark's initial position
	 * @param sprites
	 *            An array of sprites for the new shark
	 * 
	 * @return A new shark, located at the provided location. The returned shark
	 *         should not belong to a world.
	 */
	public Shark createShark(int x, int y, Sprite[] sprites) {
		return new Shark(x, y, sprites);
	}

	/**
	 * Add the given shark as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the shark should be added.
	 * @param shark
	 *            The shark that needs to be added to the world.
	 */
	public void addShark(World world, Shark shark) {
		
	}

	/**
	 * Returns all the sharks currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all sharks.
	 * @return All sharks that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each shark may only be returned once.
	 */
	public Collection<Shark> getSharks(World world) {
		return world.getAllSharks();
	}

	/**
	 * Returns the current location of the given shark.
	 * 
	 * @param shark
	 *            The shark of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given shark's bottom left pixel in the world.
	 */
	public int[] getLocation(Shark shark) {
		int[] p = { (int) Math.round(shark.getXPosition()), (int) Math.round(shark.getYPosition()) };
		return p;
	}

	/**
	 * Return the current sprite image for the given shark.
	 * 
	 * @param shark
	 *            The shark for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given shark, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}

	/**
	 * Creates a new slime school.
	 * 
	 * @return A new school for slimes, without any members.
	 */
	public School createSchool() {
		return new School();
	}

	/**
	 * Creates a new slime, located at the provided pixel location (x, y).
	 * The returned slime should not belong to a world.
	 * 
	 * @param x
	 *            The x-coordinate of the slime's initial position
	 * @param y
	 *            The y-coordinate of the slime's initial position
	 * @param sprites
	 *            An array of sprites for the new slime
	 * @param school
	 *            The initial school to which the new slime belongs
	 * 
	 * @return A new slime, located at the provided location and part of the
	 *         given school. The returned slime should not belong to a world.
	 */
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		return new Slime(x, y, sprites, school);
	}

	/**
	 * Add the given slime as a game object to the given world.
	 * 
	 * @param world
	 *            The world to which the slime should be added.
	 * @param slime
	 *            The slime that needs to be added to the world.
	 */
	public void addSlime(World world, Slime slime) {
		
	}

	/**
	 * Returns all the slimes currently located in the given world.
	 * 
	 * @param world
	 *            The world for which to retrieve all slimes.
	 * @return All slimes that are located somewhere in the given world. There
	 *         are no restrictions on the type or order of the returned
	 *         collection, but each slime may only be returned once.
	 */
	public Collection<Slime> getSlimes(World world) {
		return world.getAllSlimes();
	}

	/**
	 * Returns the current location of the given slime.
	 * 
	 * @param slime
	 *            The slime of which to find the location
	 * @return An array, consisting of 2 integers {x, y}, that represents the
	 *         coordinates of the given slime's bottom left pixel in the world.
	 */
	public int[] getLocation(Slime slime) {
		int[] p = {(int) Math.round(slime.getXPosition()), (int) Math.round(slime.getYPosition())};
		return p;
	}

	/**
	 * Return the current sprite image for the given slime.
	 * 
	 * @param slime
	 *            The slime for which to get the current sprite image.
	 * 
	 * @return The current sprite image for the given slime, determined by its
	 *         orientation as defined in the assignment.
	 */
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}

	/**
	 * Returns the current school to which the given slime belongs.
	 * 
	 * @param slime
	 *            The slime for which to retrieve the school.
	 * 
	 * @return The current school of the given slime.
	 */
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}


}
