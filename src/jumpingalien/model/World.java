package jumpingalien.model;

import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;





import jumpingalien.part3.programs.IProgramFactory;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of game worlds involving a tilesize, the number of tiles both horizontal as vertical,
 * the height and width of the visible window and the target tile.
 * @invar The height of the world must be divisible by the length of a tile without remainder.
 * 		| (getWorldHeight() % tileSize) == 0
 * @invar The width of the world must be divisible by the length of a tile without remainder.
 * 		| (getWorldWidth() % tileSize) == 0
 * @invar The height of the visible window of the world must be smaller or equal to the height of the world itself.
 * 		| visibleWindowHeight <= getWorldHeight()
 * @invar The width of the visible window of the world must be smaller or equal to the width of the world itself.
 * 		| visibleWindowWidth <= getWorldWidth()
 * @invar The game world is not full
 * 		| ! gameWorldFull()
 * 
 * @author Nina Versin, Ellen Vissers
 * @version 1.0
 */
public class World {
	
	/**
	 * Create a new world with given height, width, tile length and visible window.
	 * @param 	worldHeight
	 * 			The height of the game world in pixels.
	 * @param 	worldWidth
	 * 			The width of the game world in pixels.
	 * @param 	tileSize
	 * 			The length of one tile in pixels.
	 * @param 	visibleWindowHeight
	 * 			The height of the visible window of the world.
	 * @param 	visibleWindowWidth
	 * 			The width of the visible window of the world.
	 * @param 	windowXPosition
	 * 			The horizontal position of the visible window.
	 * @param 	windowYPosition
	 * 			The vertical position of the visible window.
	 * @pre 	The height of the world must be divisible by the length of a tile without remainder.
	 * 			| (worldHeight % tileSize) == 0
	 * @pre 	The width of the world must be divisible by the length of a tile without remainder.
	 * 			| (worldWidth % tileSize) == 0
	 * @pre 	The height of the visible window of the world must be smaller or equal to the height of the world itself.
	 * 			| visibleWindowHeight <= worldHeight
	 * @pre 	The width of the visible window of the world must be smaller or equal to the width of the world itself.
	 * 			| visibleWindowWidth <= worldWidth
	 * @effect	The won state is set with setWon.
	 * 			| setWon(false);
	 * @effect	Whether the game is over is set with setGameOver.
	 * 			| setGameOver(false);
	 * @effect	The feature matrix is set to the default with defaultFeatureMatrix.
	 * 			| this.featureMatrix = defaultFeatureMatrix();
	 * @effect	The game state is set with setGameState.
	 * 			| setGameState(false);
	 */
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		assert (((nbTilesX*tileSize) % tileSize) == 0);
		assert (((nbTilesY*tileSize) % tileSize) == 0);
		assert (visibleWindowHeight <= (nbTilesY*tileSize));
		assert (visibleWindowWidth <= (nbTilesX*tileSize));
		setTileSize(tileSize);
		setNbTilesX(nbTilesX);
		setNbTilesY(nbTilesY);
		setVisibleWindowHeight(visibleWindowHeight);
		setVisibleWindowWidth(visibleWindowWidth);
		setTargetTileX(targetTileX);
		setTargetTileY(targetTileY);
		setWon(false);
		setGameOver(false);
		this.featureMatrix = defaultFeatureMatrix();
		setGameState(false);
	}

		/**
		 * Variable registering the height of the game world.
		 */
		private int tileSize;
		/**
		 * Variable registering the width of the game world.
		 */
		private int nbTilesX;
		/**
		 * Variable registering the length of one tile.
		 */
		private int nbTilesY;
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
		private int targetTileX;
		/**
		 * Variable registering the vertical position of the visible window.
		 */
		private int targetTileY;
		/**
		 * Variable registering whether the player is game over.
		 */
		private boolean gameOver;
		/**
		 * Variable registering whether the player has won.
		 */
		private boolean won;
		/**
		 * Variable registering the maximum number of game objects in the game world.
		 */
		public static final int maxNumberOfGameObjects = 100;
		/**
		 * A variable registering whether the game has started.
		 */
		public boolean isStarted;
		/**
		 * An array list registering the collisions in this world between game objects
		 */
		private ArrayList<ArrayList<GameObject>> collided = new ArrayList<ArrayList<GameObject>>();
		
		/**
		 * A method that returns the length of a tile.
		 */
		@Basic @Immutable
		public int getTileSize() {
			return this.tileSize;
		}
		
		/**
		 * Set the tilesize to a given value.
		 * @param 	size
		 * 			The new tile size.
		 * @post 	The size is updated.
		 * 			| new.getTileSize() == size
		 */
		public void setTileSize(int size) {
			this.tileSize = size;
		}
		
		/**
		 * A method that returns the number of tiles in x direction.
		 * @return
		 * 			| this.nbTilesX
		 */
		@Basic @Immutable
		public int getNbTilesX(){
			return this.nbTilesX;
		}
		
		/**
		 * Set the number of tiles in the x direction to a given value.
		 * @param 	nb
		 * 			The new number of tiles.
		 * @post 	The number is updated.
		 * 			| new.getNbTilesX() == nb
		 */
		protected void setNbTilesX(int nb){
			this.nbTilesX = nb;
		}
		
		/**
		 * A method that returns the number of tiles in y direction.
		 * @return
		 * 			| this.nbTilesY
		 */
		@Basic @Immutable
		public int getNbTilesY() {
			return this.nbTilesY;
		}
		
		/**
		 * Set the number of tiles in the y direction to a given value.
		 * @param 	nb
		 * 			The new number of tiles.
		 * @post 	The number is updated.
		 * 			| new.getNbTilesY() == nb
		 */
		protected void setNbTilesY(int nb) {
			this.nbTilesY = nb;
		}
		
		/**
		 * A method that returns the height of the game world.
		 * @return	The height of the game world.
		 * 			| this.nbTilesY*this.tileSize
		 */
		@Basic @Immutable
		public int getWorldHeight() {
			return getNbTilesY()*getTileSize();
		}
		
		/**
		 * A method that returns the width of the game world.
		 * @return	The width of the game world.
		 * 			| this.nbTilesX*this.tileSize
		 */
		@Basic @Immutable
		public int getWorldWidth(){
			return getNbTilesX()*getTileSize();
		}
		 /**
		  * a method that returns the height of the visible window
		  * @return The height of the visible window.
		  * 		| this.visibleWindowHeight
		  */
		@Basic @Immutable
		public int getVisibleWindowHeight() {
			return this.visibleWindowHeight;
		}

		/**
		 * A method that sets the visible window height to a given one.
		 * @param 	visibleWindowHeight
		 * 			The height we want to set the height of the window to.
		 * @post	The height is updated.
		 * 			| new.getVisibleWindowHeight() == visibleWindowHeight
		 */
		private void setVisibleWindowHeight(int visibleWindowHeight) {
			this.visibleWindowHeight = visibleWindowHeight;
		}

		 /**
		  * a method that returns the width of the visible window.
		  * @return The width of the visible window.
		  * 		| this.visibleWindowWidth
		  */
		@Basic @Immutable
		public int getVisibleWindowWidth() {
			return this.visibleWindowWidth;
		}

		/**
		 * A method that sets the visible window width to a given one.
		 * @param 	visibleWindowWidth
		 * 			The width we want to set the width of the window to.
		 * @post	The width is updated.
		 * 			| new.getVisibleWindowWidth() == visibleWindowWidth
		 */
		private void setVisibleWindowWidth(int visibleWindowWidth) {
			this.visibleWindowWidth = visibleWindowWidth;
		}
		
		/**
		 * A method that returns a matrix of tile positions whithin a given window characterized by two
		 * x-positions and two y-positions
		 * @param 	left
		 * 			The leftmost x-position
		 * @param 	bottom
		 * 			The lowest y-position
		 * @param 	right
		 * 			The rigthmost x-position
		 * @param 	top
		 * 			The highest y-position
		 * @return 	A matrix of tile positions
		 * 			| int[][] result = new int[xlength*yheight][2]
		 * 			| result[pos] = val
		 * @pre 	The given positions lie within the game world.
		 * 			| (left>0) && (right > 0) && (bottom >0) && (top > 0)
		 * 			| (left<getWorldWidth()) && (right < getWorldWidth()) && 
		 * 			| (bottom < getWorldHeight()) && (top < getWorldHeight())
		 */
		public int[][] getTilePositions(int left, int bottom, int right, int top) {
			int[] bottomleft = getTile(left,bottom);
			int[] topright = getTile(right,top);
			int xlength = topright[0] - bottomleft[0] + 1;
			int yheight = topright[1] - bottomleft[1] + 1;
			int[][] result = new int[xlength*yheight][2];
			for (int j = bottomleft[1]; j <= topright[1]; j++)
				for (int i = bottomleft[0]; i <= topright[0]; i++)
				{
					int[] val = {i,j};
					int pos = (i-bottomleft[0]) + xlength*(j-bottomleft[1]);
					result[pos] = val;
				}
			return result;
		}
		
		/**
		 * A method that returns the x-coordinate of the target tile.
		 * @return	The x coordinate of the target tile.
		 * 			| this.targetTileX
		 */
		@Basic @Immutable
		public int getTargetTileX(){
			return this.targetTileX;
		}
		
		/**
		 * A method that sets the x-coordinate of the target tile to the given one.
		 * @param 	tile
		 * 			The new x-position we want to change the x-coordinate of the target tile to.
		 * @post	The x coordinate of the target tile is updated to the given tile.
		 * 			| new.getTargetTileX() == tile
		 */
		private void setTargetTileX(int tile){
			this.targetTileX = tile;
		}
		
		/**
		 * A method that returns the y-coordinate of the target tile.
		 * @return	The y coordinate of the target tile.
		 * 			| this.targetTileY
		 */
		@Basic @Immutable
		public int getTargetTileY(){
			return this.targetTileY;
		}
		
		/**
		 * A method that sets the y-coordinate of the target tile to the given one.
		 * @param 	tile
		 * 			The y-position we want to change the y-coordinate of the target tile to.
		 * @post	The y coordinate of the target tile is updated to the given tile.
		 * 			| new.getTargetTileY() == tile
		 */
		private void setTargetTileY(int tile){
			this.targetTileY = tile;
		}

		/**
		 * A method that converts pixel coordinates to tile coordinates.
		 * @param 	XPosition
		 * 			The horizontal pixel position
		 * @param 	YPosition
		 * 			The vertical pixel position
		 * @return 	An array containing the horizontal tile coordinate and the vertical tile coordinate.
		 * 			| int[] list = {XPosition/this.tileSize,YPosition/this.tileSize}
		 * @pre		The given position lie within the game world.
		 * 			| XPosition > 0 && XPosition < getWorldWidth()
		 * 			| YPosition > 0 && XPosition < getWorldHeight()
		 */
		public int[] getTile(int XPosition, int YPosition) {
			assert (XPosition > 0 && XPosition < getWorldWidth());
			assert (YPosition > 0 && XPosition < getWorldHeight());
			int[] list = {XPosition/getTileSize(),YPosition/getTileSize()};
			return list;
		}
		
		/**
		 * Returns whether the game has been won.
		 * @return	The boolean registering whether the game is won.
		 * 			| this.won
		 */
		@Basic
		public boolean getWon() {
			return this.won;
		}
		
		/**
		 * A method that changes whether the game has been won.
		 * @param 	won
		 * 			The new boolean registering whether the game is won.
		 * @post	The game state is updated.
		 * 			| new.getWon() = won
		 */
		protected void setWon(boolean won) {
			this.won = won;
		}
		
		/**
		 * Returns whether the game is game over.
		 * @return 	The boolean registering whether the game is game over.
		 * 			| this.gameOver
		 */
		@Basic
		public boolean getGameOver() {
			return this.gameOver;
		}
		
		/**
		 * A method that changes whether the game is game over.
		 * @param 	game
		 * 			The new boolean registering the game state.
		 * @post	The game state is updated.
		 * 			| new.getGameOver() = game
		 */
		protected void setGameOver(boolean game) {
			this.gameOver = game;
		}		
		
		/**
		 * Variable registering a matrix of the geographic features.
		 */
		private int[][] featureMatrix = new int[getNbTilesX()][getNbTilesY()];
		
		/**
		 * A method that creates the default matrix (a matrix filled with air)
		 * @return	A feature matrix with air in every position.
		 *			| int[][] result = new int[getNbTilesX()][getNbTilesY()]
		 * 			| result[i][j] = 0
		 */
		private int[][] defaultFeatureMatrix() {
			int[][] result = new int[getNbTilesX()][getNbTilesY()];
			for (int i = 0; i<getNbTilesX(); i++){
			     for (int j = 0; j<getNbTilesY(); j++){
			    	 result[i][j] = 0;
			     }
			}
			return result;
		}
		
		/**
		 * A method that returns the matrix with features
		 * @return	The feature matrix.
		 * 			| this.featureMatrix
		 */
		@Basic
		private int[][] getFeatureMatrix() {
			return this.featureMatrix;
		}
		
		/**
		 * A method that returns the feature of the tile at a given position.
		 * @param 	XTile
		 * 			The horizontal position we want to check.
		 * @param 	YTile
		 * 			The vertical position we want to check.
		 * @return 	The feature of a tile at a given position
		 * 			| featureMatrix[XTile][YTile]
		 */
		public int getFeatureAt(int XTile, int YTile) {
			return getFeatureMatrix()[XTile][YTile];
		}
		
		/**
		 * A method that sets the feature of a tile at a given position to the given one.
		 * @param 	XPosition
		 * 			The horizontal position of the tile we want to change.
		 * @param 	YPosition
		 * 			The vertical position of the tile we want to change.
		 * @param 	feature
		 * 			The feature we want to set the current feature of the tile to.
		 * @post	The feature matrix is updated
		 * 			| getFeatureMatrix()[XTile][YTile] = feature
		 */
		public void setFeatureAt(int XTile, int YTile, int feature) {
			if ( (XTile < getNbTilesX()) && (YTile < getNbTilesY())) 
				getFeatureMatrix()[XTile][YTile] = feature;
		}
		
		
		/**
		 * A method that adds an alien to the game world and sets the world of the alien to this world.
		 * @param 	alien
		 * 			The alien that has to be set in the world.
		 * @effect 	The world of the alien is set to this one
		 * 			| alien.setWorld(this)
		 * @post 	The alien is added to the alien list of the world
		 * 			| aliens.add(alien)
		 */
		@Raw
		public void setMazub(Mazub alien) {
			alien.setWorld(this);
			aliens.add(alien);
		}
		
		/**
		 * A method that checks whether the game is started.
		 * @return 	True if the game is started.
		 * 			| this.isStarted
		 */
		@Basic
		private boolean isStarted() {
			return isStarted;
		}
		
		/**
		 * A method that returns the arraylist of collisions.
		 * @return	The arraylist of collision.
		 * 			| this.collided
		 */
		public ArrayList<ArrayList<GameObject>> getCollided() {
			return collided;
		}
		
		/**
		 * A list of all plants in this world.
		 */
		private ArrayList<Plant> plants = new ArrayList<Plant>();
		/**
		 * A list of all slimes in this world.
		 */
		private ArrayList<Slime> slimes = new ArrayList<Slime>();
		/**
		 * A list of all sharks in this world.
		 */
		private ArrayList<Shark> sharks = new ArrayList<Shark>();
		/**
		 * A list of all aliens in this world.
		 */
		private ArrayList<Mazub> aliens = new ArrayList<Mazub>();
		/**
		 * A list of all buzams in this world.
		 */
		private ArrayList<Buzam> buzams = new ArrayList<Buzam>();
		
		/**
		 * A method that returns all the plants in this world.
		 * @return All plants in this world.
		 * 		| this.plants
		 */
		public ArrayList<Plant> getAllPlants(){
			return plants;
		}
		
		/**
		 * A method to add a plant to this world and set the world of the plant to this world.
		 * @param 	plant
		 * 			The plant we want to add.
		 * @pre 	The game world is not full yet
		 * 			| ! gameWorldFull()
		 * @effect	The world of this plant is updated with setWorld.
		 * 			| plant.setWorld(this)
		 * @post	The plant is added to the list with all plants.
		 * 			| plants.add(plant)
		 */
		@Raw
		public void addPlant(Plant plant) {
			assert (! gameWorldFull());
			if (! isStarted())
			{
				plants.add(plant);
				plant.setWorld(this);
			}
		}
		
		/**
		 * A method that returns all the slimes in this world.
		 * @return 	All slimes in this world.
		 * 			| this.slimes
		 */
		public ArrayList<Slime> getAllSlimes(){
			return slimes;
		}
		
		/**
		 * A method to add a slime to this world and set the world of the slime to this world.
		 * @param 	slime
		 * 			The slime we want to add.
		 * @pre	 	The game world is not full yet
		 * 			| ! gameWorldFull()
		 * @effect	The world of this slime is updated with setWorld.
		 * 			| slime.setWorld(this)
		 * @post	The slime is added to the list with all slimes.
		 * 			| slimes.add(slime)
		 */
		@Raw
		public void addSlime(Slime slime) {
			assert (! gameWorldFull());
			if (! isStarted())
			{
				slimes.add(slime);
				slime.setWorld(this);
			}
		}
		
		/**
		 * A method that returns all the sharks in this world.
		 * @return All sharks in this world.
		 * 		| this.sharks
		 */
		public ArrayList<Shark> getAllSharks(){
			return sharks;
		}
		
		/**
		 * A method to add a shark to this world and set the world of the shark to this world.
		 * @param 	shark
		 * 			The shark we want to add.
		 * @pre 	The game world is not full yet
		 * 			| ! gameWorldFull()
		 * @effect	The world of this shark is updated with setWorld.
		 * 			| shark.setWorld(this)
		 * @post	The shark is added to the list with all sharks.
		 * 			| sharks.add(shark)
		 */
		public void addShark(Shark shark) {
			assert (! gameWorldFull());
			if (! isStarted())
			{
				sharks.add(shark);
				shark.setWorld(this);
			}
		}
		
		/**
		 * A method that returns all the aliens in this world.
		 * @return All aliens in this world.
		 * 		| this.aliens
		 */
		public ArrayList<Mazub> getAllAliens(){
			return aliens;
		}
		
		/**
		 * A method to add a shark to this world and set the world of the shark to this world.
		 * @param 	shark
		 * 			The shark we want to add.
		 * @pre 	The game world is not full yet
		 * 			| ! gameWorldFull()
		 * @effect	The world of this shark is updated with setWorld.
		 * 			| shark.setWorld(this)
		 * @post	The shark is added to the list with all sharks.
		 * 			| sharks.add(shark)
		 */
		public void addBuzam(Buzam buzam) {
			assert (! gameWorldFull());
			if (! isStarted())
			{
				buzams.add(buzam);
				buzam.setWorld(this);
			}
		}

		public ArrayList<Buzam> getAllBuzams(){
			return buzams;
		}
		
		public ArrayList<GameObject> getAllGameObjects(){
			ArrayList<GameObject> all = new ArrayList<GameObject>();
			all.addAll(aliens);
			all.addAll(buzams);
			all.addAll(sharks);
			all.addAll(slimes);
			all.addAll(plants);
			return all;
		}
		
		public ArrayList<? extends GameObject> getAll(IProgramFactory.Kind kind) {
			if (kind == IProgramFactory.Kind.BUZAM)
				return buzams;
			if (kind == IProgramFactory.Kind.MAZUB)
				return aliens;
			if (kind == IProgramFactory.Kind.SHARK)
				return sharks;
			if (kind == IProgramFactory.Kind.SLIME)
				return slimes;
			if (kind == IProgramFactory.Kind.PLANT)
				return plants;
			return getAllGameObjects();
		}
		
		/**
		 * A method that checks whether the game world is full.
		 * @return 	Whether the sum of all game objects is smaller than maxNumberOfGameObjects.
		 * 			| (sharks.size() + plants.size() + slimes.size()) < maxNumberOfGameObjects
		 */
		private boolean gameWorldFull(){
			if ((sharks.size() + plants.size() + slimes.size()) < maxNumberOfGameObjects)
				return false;
			return true;
		}
		
		/**
		 * A method that start the game.
		 * @effect	The game state is changed with setGameState.
		 * 			| setGameState(true)
		 */
		public void startGame(){
			setGameState(true);
		}
		
		/**
		 * A method to change the state of the game to started or terminated
		 * @param 	state
		 * 			The new game state.
		 * @post	The game state is updated.
		 * 			| this.isStarted = state
		 */
		private void setGameState(boolean state){
			this.isStarted = state;
		}
		
		/**
		 * A method that returns the coordinates of the visible window.
		 * @return 	The coordinates of the visible window.
		 * 			| if (left < 0)
		 * 			|	left = 0;
		 * 			|	right = getVisibleWindowWidth()-1;
		 * 			| else if (right > nbTilesX*tileSize-1)
		 * 			|	right = nbTilesX*tileSize-1;
		 * 			|	left = right - getVisibleWindowWidth() + 1;
		 * 			| if (bottom < 0)
		 * 			|	bottom = 0;
		 * 			|	top = getVisibleWindowHeight()-1;
		 * 			| else if (top > nbTilesY*tileSize-1)
		 * 			|	top = nbTilesY*tileSize-1;
		 * 			|	bottom = top - getVisibleWindowHeight() + 1;
		 * 			| int[] coords =  {left,bottom,right,top};
		 */
		public int[] getVisibleWindow() {
			ArrayList<Mazub> aliens = getAllAliens();
			Mazub alien = aliens.get(0);
			int x = (int) Math.round(alien.getXPosition());
			int y = (int) Math.round(alien.getYPosition());
			int b = (getVisibleWindowWidth() - alien.getCurrentSprite().getWidth())/2;
			int h = (getVisibleWindowHeight() - alien.getCurrentSprite().getHeight())/2;
			int left = x - b;
			int bottom = y-h;
			int right = left + getVisibleWindowWidth()-1;
			int top = bottom + getVisibleWindowHeight()-1;
			if (left < 0)
			{
				left = 0;
				right = getVisibleWindowWidth()-1;
			}
			else if (right > nbTilesX*tileSize-1)
			{
				right = nbTilesX*tileSize-1;
				left = right - getVisibleWindowWidth() + 1;
			}
			if (bottom < 0)
			{
				bottom = 0;
				top = getVisibleWindowHeight()-1;
			}
			else if (top > nbTilesY*tileSize-1)
			{
				top = nbTilesY*tileSize-1;
				bottom = top - getVisibleWindowHeight() + 1;
			}
			int[] coords =  {left,bottom,right,top};
			return coords;
		}
		
		/**
		 * A method that returns the bottom left pixel position of a tile
		 * @param 	tileX
		 * 			The x coordinate of the tile
		 * @param 	tileY
		 * 			The y coordinate of the tile
		 * @return	The bottom left pixel position of the given tile.
		 * 			| int[] tilepos = {tileX*this.getTileSize(),tileY*this.getTileSize()}
		 */
		public int[] getBottomLeftPixelOfTile(int tileX, int tileY){
			int[] tilepos = {tileX*this.getTileSize(),tileY*this.getTileSize()};
			return tilepos;
			}
		 
		/**
		 * A method that calculates new positions and velocities for all the game objects in the game world
		 * after a given amount of time.
		 * @param 	time
		 * 			The time after which the position and velocity are updated.
		 *@effect	If the current object is a slime, its position and velocity is calculated with advanceTime.
		 * 			| for (slime :slimes)
		 * 			|	slime.advanceTime(time)
		 * @effect 	If the current object is a shark, its position and velocity is calculated with advanceTime.
		 * 			| for (shark :sharks)
		 * 			|	shark.advanceTime(time)
		 * @effect 	If the current object is a plant, its position and velocity is calculated with advanceTime.
		 * 			| for (plant :plants)
		 * 			|	plant.advanceTime(time)
		 * @effect	If the current object is an alien, its position and velocity is calculated with advanceTime.
		 * 			| for (alien :aliens)
		 * 			|	alien.advanceTime(time)
		 */
		public void advanceTime(double time) {
			collided.clear();
			ArrayList<Mazub> aliens = getAllAliens();
			ArrayList<Plant> plants = getAllPlants();
			ArrayList<Shark> sharks = getAllSharks();
			ArrayList<Slime> slimes = getAllSlimes();
			ArrayList<GameObject> objects = new ArrayList<GameObject>();
			objects.addAll(aliens);
			objects.addAll(plants);
			objects.addAll(sharks);
			objects.addAll(slimes);
			for (GameObject object : objects)
				object.advanceTime(time);
		}	
}
