package jumpingalien.model;

import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;




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
	 * @param tileSize
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
	 * 		| (worldHeight % tileSize) == 0
	 * @pre The width of the world must be divisible by the length of a tile without remainder.
	 * 		| (worldWidth % tileSize) == 0
	 * @pre The height of the visible window of the world must be smaller or equal to the height of the world itself.
	 * 		| visibleWindowHeight <= worldHeight
	 * @pre The width of the visible window of the world must be smaller or equal to the width of the world itself.
	 * 		| visibleWindowWidth <= worldWidth
	 */
	public World(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		assert (((nbTilesX*tileSize) % tileSize) == 0);
		assert (((nbTilesY*tileSize) % tileSize) == 0);
		assert (visibleWindowHeight <= (nbTilesY*tileSize));
		assert (visibleWindowWidth <= (nbTilesX*tileSize));
		this.tileSize = tileSize;
		this.nbTilesX = nbTilesX;
		this.nbTilesY = nbTilesY;
		this.visibleWindowHeight = visibleWindowHeight;
		this.visibleWindowWidth = visibleWindowWidth;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
		this.setWon(false);
		this.setGameOver(false);
		this.featureMatrix = defaultFeatureMatrix();
		this.setGameState(false);
	}

		/**
		 * Variable registering the height of the game world.
		 */
		private final int tileSize;
		/**
		 * Variable registering the width of the game world.
		 */
		private final int nbTilesX;
		/**
		 * Variable registering the length of one tile.
		 */
		private final int nbTilesY;
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
		
//		private int xPositionWindow;
//		private int yPositionWindow;
//		private Mazub currentAlien;
		private boolean gameOver;
		private boolean won;
		
		
		/**
		 * A method that returns the length of a tile.
		 */
		@Basic @Immutable
		public int getTileSize() {
			return this.tileSize;
		}
		
		@Basic @Immutable
		public int getNbTilesX(){
			return this.nbTilesX;
		}
		
		@Basic @Immutable
		public int getNbTilesY(){
			return this.nbTilesY;
		}
		
		public int getWorldHeight(){
			return this.nbTilesY*this.tileSize;
		}
		
		public int getWorldWidth(){
			return this.nbTilesX*this.tileSize;
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
		
		public int getTargetTileX(){
			return this.targetTileX;
		}
		
		public void setTargetTileX(int tile){
			this.targetTileX = tile;
		}
		
		public int getTargetTileY(){
			return this.targetTileY;
		}
		
		public void setTargetTileY(int tile){
			this.targetTileY = tile;
		}

		
		public int[] getTile(int XPosition, int YPosition) {
			int[] list = {XPosition/this.tileSize,YPosition/this.tileSize};
			return list;
		}
		
		protected void setWon(boolean won) {
			this.won = won;
		}
		
		public boolean getWon() {
			return this.won;
		}
		
		protected void setGameOver(boolean game) {
			this.gameOver = game;
		}
		
		public boolean getGameOver() {
			return this.gameOver;
		}
		
		/**
		 * Variable registering a matrix of the geographic features.
		 */
		private int[][] featureMatrix = new int[getNbTilesX()][getNbTilesY()];
		
		//private int[][] TileMatrix = new int[this.nbTilesX][this.nbTilesY];
		
		private int[][] defaultFeatureMatrix() {
			int[][] result = new int[getNbTilesX()][getNbTilesY()];
			for (int i = 0; i<getNbTilesX(); i++){
			     for (int j = 0; j<getNbTilesY(); j++){
			    	 result[i][j] = 0;
			     }
			}
			return result;
		}
		
		private int[][] getFeatureMatrix() {
			return this.featureMatrix;
		}
		
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
		public void setFeatureAt(int XTile, int YTile, int feature) {
			if ( (XTile < getNbTilesX()) && (YTile < getNbTilesY())) 
				getFeatureMatrix()[XTile][YTile] = feature;
			//featureMatrix[XTile][YTile] = feature;
		}
		
		public void setMazub(Mazub alien) {
			alien.setWorld(this);
			aliens.add(alien);
		}
		
		private boolean isStarted() {
			return isStarted;
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
		 * A method that returns all the plants in this world.
		 * @return All plants in this world.
		 * 		| this.plants
		 */
		public ArrayList<Plant> getAllPlants(){
			return plants;
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
//			if (! isStarted())
			{
				plants.add(plant);
				plant.setWorld(this);
			}
		}
		
		/**
		 * A method that returns all the slimes in this world.
		 * @return All slimes in this world.
		 * 		| this.slimes
		 */
		public ArrayList<Slime> getAllSlimes(){
			return slimes;
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
		 * A method to add a shark to this world.
		 * @param shark
		 * 		The shark we want to add.
		 * @pre The game world is not full yet
		 * 		| ! gameWorldFull()
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
		 * A method to add an alien to this world.
		 * @param alien
		 * 		The alien we want to add.
		 */
		public void addAlien(Mazub alien) {
			if (! isStarted())
			{
				aliens.add(alien);
				alien.setWorld(this);
			}
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
		
		public void startGame(){
			setGameState(true);
		}
		
//		public boolean isGameOver(){
//			if ((getCurrentAlien().getXPosition() == this.getTargetTileX()) && (getCurrentAlien().getYPosition() == this.getTargetTileY()))
//				return true;
//			if (getCurrentAlien().getHitPoints() == 0)
//				return true;
//			if ((getCurrentAlien().getXPosition() > this.getWorldWidth()) && (getCurrentAlien().getYPosition() > this.getWorldHeight()))
//				return true;
//			if ((getCurrentAlien().getXPosition() < 0 && (getCurrentAlien().getYPosition() < 0)))
//				return true;	
//			return false;			
//		}
		
		private boolean isStarted;
		
		public boolean isGameStarted(){
			if (! isStarted)
				return true;
			return false;
		}
		
//		public void changeGameState(boolean state){
//			this.isStarted = state;
//		}
		
		public void setGameState(boolean state){
			this.isStarted = state;
		}
		
		public boolean hasWon(Mazub alien){
			if ((alien.getXPosition() == this.getTargetTileX()) && (alien.getYPosition() == this.getTargetTileY()))
				return true;
			return false;
//			if ((getCurrentAlien().getXPosition() == this.getTargetTileX()) && (getCurrentAlien().getYPosition() == this.getTargetTileY()))
//				return true;
//			return false;
		}
		
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
		
		public int[] getBottomLeftPixelOfTile(int tileX, int tileY){
			int[] tilepos = {tileX*this.getTileSize(),tileY*this.getTileSize()};
			return tilepos;
			}
		
//		public Mazub getCurrentAlien(){
//			return this.currentAlien;
//		}
//		
//		public void setCurrentAlien(Mazub alien){
//			this.currentAlien = alien;
//		}
		 
		public void advanceTime(double time) {
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
		
//		public int[][] getTilePositions(int pixelLeft, int pixelBottom,
//				int pixelRight, int pixelTop){
//			
//			assert (pixelLeft >= 0 && pixelBottom >= 0 && pixelRight >= 0 && pixelTop >= 0);
//			assert (pixelLeft <= pixelRight);
//			assert (pixelTop <= pixelBottom);
//			
//			int TileSize = getTileSize();
//			int width = pixelRight - pixelLeft;
//			int height = pixelTop - pixelBottom;
//			
//			if ((width % TileSize == 0) && (height % TileSize == 0)){
//				int[][] TileMatrix = new int[(width/TileSize)*(height/TileSize)][2];
//				int index = 0;
//				for (int j = pixelBottom; j<pixelTop; j = j+TileSize){
//					for (int i = pixelLeft; i<pixelRight; i=i+TileSize){
//						TileMatrix[index] = getBottomLeftPixelOfTile(i+pixelLeft, j+pixelBottom);
//						index ++;
//					}	
//				}
//				
//			}else if((width % TileSize == 0) && (height % TileSize != 0)){
//				int[][] TileMatrix = new int[(width/TileSize)*((height/TileSize)+1)][2];
//				int index = 0;
//				for (int j = pixelBottom; j<pixelTop + TileSize; j = TileSize){
//					for (int i = pixelLeft; i<pixelRight; i=i+TileSize){
//						TileMatrix[index] = getBottomLeftPixelOfTile(i+pixelLeft, j+pixelBottom);
//						index ++;
//					}	
//				}
//			}else if((width % TileSize != 0) && (height % TileSize == 0)){
//				int[][] TileMatrix = new int[((width/TileSize)+1)*(height/TileSize)][2];
//				int index = 0;
//				for (int j = pixelBottom; j<pixelTop; j = j+TileSize){
//					for (int i = pixelLeft; i<pixelRight + TileSize; i=i+TileSize){
//						TileMatrix[index] = getBottomLeftPixelOfTile(i+pixelLeft, j+pixelBottom);
//						index ++;
//					}	
//				}
//			
//			}else{
//				int[][] TileMatrix = new int[((width/TileSize)+1)*((height/TileSize)+1)][2];
//				int index = 0;
//				for (int j = pixelBottom; j<pixelTop + TileSize; j = j+TileSize){
//					for (int i = pixelLeft; i<pixelRight + TileSize; i=i+TileSize){
//						TileMatrix[index] = getBottomLeftPixelOfTile(i+pixelLeft, j+pixelBottom);
//						index ++;
//					}	
//				}
//			}
//			return TileMatrix;
//		}
		
}
