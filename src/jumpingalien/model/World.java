package jumpingalien.model;

import java.util.ArrayList;
//import java.util.List;
import java.util.Collection;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of game worlds with a certain height, width, tile length and height, width and position of the visible
 * window.
 * @author Nina Versin, Ellen Vissers
 * @version 1.0
 */
public class World {
	
	/**
	 * Create a new world with given height, width, tile length and visible window.
	 * @param worldHeight
	 * 		The height of the game world in pixels.
	 * @param worldWidth
	 * 		The width of the game world in pixels.
	 * @param tileLength
	 * 		The length of one tile in pixels.
	 * @param visibleWindowHeight
	 * 		The height of the visible window of the world.
	 * @param visibleWindowWidth
	 * 		The width of the visible window of the world.
	 * @param windowXPosition
	 * 		The horizontal position of the visible window.
	 * @param windowYPosition
	 * 		The vertical position of the visible window.
	 * @pre The height of the world must be divisible by the length of a tile without remainder.
	 * 		| (worldHeight % tileLength) == 0
	 * @pre The width of the world must be divisible by the length of a tile without remainder.
	 * 		| (worldWidth % tileLength) == 0
	 * @pre The height of the visible window of the world must be smaller or equal to the height of the world itself.
	 * 		| visibleWindowHeight <= worldHeight
	 * @pre The width of the visible window of the world must be smaller or equal to the width of the world itself.
	 * 		| visibleWindowWidth <= worldWidth
	 */
	public World(int worldHeight, int worldWidth, int tileLength, int visibleWindowHeight,
			int visibleWindowWidth, int windowXPosition, int windowYPosition) {
		assert ((worldHeight % tileLength) == 0);
		assert ((worldWidth % tileLength) == 0);
		assert (visibleWindowHeight <= worldHeight);
		assert (visibleWindowWidth <= worldWidth);
		
		this.worldHeight = worldHeight;
		this.worldWidth = worldWidth;
		this.tileLength = tileLength;
		this.visibleWindowHeight = visibleWindowHeight;
		this.visibleWindowWidth = visibleWindowWidth;
		this.windowXPosition = windowXPosition;
		this.windowYPosition = windowYPosition;
	}
		/**
		 * Variable registering the height of the game world.
		 */
		private final int worldHeight;
		/**
		 * Variable registering the width of the game world.
		 */
		private final int worldWidth;
		/**
		 * Variable registering the length of one tile.
		 */
		private final int tileLength;
		/**
		 * Variable registering the height of the visible window.
		 */
		private int visibleWindowHeight;
		/**
		 * Variable registering the width of the visible window.
		 */
		private int visibleWindowWidth;
		/**
		 * Variable registering the horizontal position of the visible window.
		 */
		private int windowXPosition;
		/**
		 * Variable registering the vertical position of the visible window.
		 */
		private int windowYPosition;
		
		/**
		 * A method that returns the heigth of the world.
		 */
		@Basic @Immutable
		public int getWorldHeight() {
			return this.worldHeight;
		}
		
		/**
		 * A method that returns the width of the world.
		 */
		@Basic @Immutable
		public int getWorldWidth() {
			return this.worldWidth;
		}
		
		/**
		 * A method that returns the length of a tile.
		 */
		@Basic @Immutable
		public int getTileLength() {
			return this.tileLength;
		}
		 /**
		  * a method that returns the height of the visible window
		  * @return The height of the visible window.
		  * 		| this.visibleWindowHeight
		  */
		public int getVisibleWindowHeight() {
			return this.visibleWindowHeight;
		}

		/**
		 * A method that sets the visible window height to a given one.
		 * @param visibleWindowHeight
		 * 		The height we want to set the height of the window to.
		 */
		public void setVisibleWindowHeight(int visibleWindowHeight) {
			this.visibleWindowHeight = visibleWindowHeight;
		}

		 /**
		  * a method that returns the width of the visible window.
		  * @return The width of the visible window.
		  * 		| this.visibleWindowWidth
		  */
		public int getVisibleWindowWidth() {
			return this.visibleWindowWidth;
		}

		/**
		 * A method that sets the visible window width to a given one.
		 * @param visibleWindowWidth
		 * 		The width we want to set the width of the window to.
		 */
		public void setVisibleWindowWidth(int visibleWindowWidth) {
			this.visibleWindowWidth = visibleWindowWidth;
		}

		/**
		 * A method that returns the horizontal position of the visible window.
		 * @return The horizontal position of the window.
		 * 			| this.windowXPosition
		 */
		public int getWindowXPosition() {
			return this.windowXPosition;
		}

		/**
		 * A method that sets the horizontal position of the visible to a given one.
		 * @param windowXPosition
		 * 		The position we want to set the horizontal position of the window to.
		 */
		public void setWindowXPosition(int windowXPosition) {
			this.windowXPosition = windowXPosition;
		}

		/**
		 * A method that returns the vertical position of the visible window.
		 * @return The vertical position of the window.
		 * 			| this.windowYPosition
		 */
		public int getWindowYPosition() {
			return this.windowYPosition;
		}

		/**
		 * A method that sets the vertical position of the visible to a given one.
		 * @param windowYPosition
		 * 		The position we want to set the vertical position of the window to.
		 */
		public void setWindowYPosition(int windowYPosition) {
			this.windowYPosition = windowYPosition;
		}
		
		public int[] getTile(int XPosition, int YPosition) {
			int[] list = {XPosition/this.tileLength,YPosition/this.tileLength};
			return list;
		}
		
		/**
		 * Variable registering a matrix of the geographic features.
		 */
		private int[][] featureMatrix = new int[this.getWorldWidth()/this.tileLength][this.getWorldHeight()/this.tileLength];
		
		/**
		 * A method that sets the feature of all tiles to the default value AIR.
		 * @param list
		 * 		The matrix of features we want to reset.
		 */
		public void resetFeatureList(int[][] matrix) {
			for (int i = 0; i<matrix.length; i++){
			     for (int j = 0; j<matrix[i].length; j++){
			    	 matrix[i][j] = 0;
			     }
			}
		}
		
		/**
		 * A method that returns the feature of the tile at a given position.
		 * @param XPosition
		 * 		The horizontal position we want to check.
		 * @param YPosition
		 * 		The vertical position we want to check.
		 * @return The feature of a tile at a given position
		 * 		| featureMatrix[XPosition][YPosition]
		 */
		public int getFeatureAt(int XTile, int YTile) {
			return featureMatrix[XTile][YTile];
		}
		
		/**
		 * A method that sets the feature of a tile at a given position to the given one.
		 * @param XPosition
		 * 		The horizontal position of the tile we want to change.
		 * @param YPosition
		 * 		The vertical position of the tile we want to change.
		 * @param feature
		 * 		The feature we want to set the current feature of the tile to.
		 */
		public void setFeatureAt(int XPosition, int YPosition, int feature) {
			featureMatrix[XPosition][YPosition] = feature;
		}
		
		/**
		 * A list of all plants in this world.
		 */
		private Collection<Plant> plants = new ArrayList<Plant>();
		/**
		 * A list of all slimes in this world.
		 */
		private Collection<Slime> slimes = new ArrayList<Slime>();
		/**
		 * A list of all sharks in this world.
		 */
		private Collection<Shark> sharks = new ArrayList<Shark>();
		/**
		 * A list of all aliens in this world.
		 */
		private Collection<Mazub> aliens = new ArrayList<Mazub>();
		
		/**
		 * A method that returns all the plants in this world.
		 * @return All plants in this world.
		 * 		| this.plants
		 */
		public Collection<Plant> getAllPlants(){
			return this.plants;
		}
		
		/**
		 * A method to add a plant to this world.
		 * @param plant
		 * 		The plant we want to add.
		 * @pre The game world is not full yet
		 * 		| ! gameWorldFull()
		 */
		public void addPlant(Plant plant) {
			assert (! gameWorldFull());
			this.plants.add(plant);
		}
		
		/**
		 * A method that returns all the slimes in this world.
		 * @return All slimes in this world.
		 * 		| this.slimes
		 */
		public Collection<Slime> getAllSlimes(){
			return this.slimes;
		}
		
		/**
		 * A method to add a slime to this world.
		 * @param slime
		 * 		The slime we want to add.
		 * @pre The game world is not full yet
		 * 		| ! gameWorldFull()
		 */
		public void addSlime(Slime slime) {
			assert (! gameWorldFull());
			this.slimes.add(slime);
		}
		
		/**
		 * A method that returns all the sharks in this world.
		 * @return All sharks in this world.
		 * 		| this.sharks
		 */
		public Collection<Shark> getAllSharks(){
			return this.sharks;
		}
		
		/**
		 * A method to add a shark to this world.
		 * @param shark
		 * 		The shark we want to add.
		 * @pre The game world is not full yet
		 * 		| ! gameWorldFull()
		 */
		public void add(Shark shark) {
			assert (! gameWorldFull());
			this.sharks.add(shark);
		}
		
		/**
		 * A method that returns all the aliens in this world.
		 * @return All aliens in this world.
		 * 		| this.aliens
		 */
		public Collection<Mazub> getAllAliens(){
			return this.aliens;
		}
		
		/**
		 * A method to add an alien to this world.
		 * @param alien
		 * 		The alien we want to add.
		 */
		public void add(Mazub alien) {
			this.aliens.add(alien);
		}
		
		/**
		 * A method that checks whether the game world is full.
		 * @return Whether the sum of all game objects is smaller than 100.
		 * 		| (sharks.size() + plants.size() + slimes.size()) < 100
		 */
		public boolean gameWorldFull(){
			if ((sharks.size() + plants.size() + slimes.size()) < 100)
				return false;
			return true;
		}
		
		
}

