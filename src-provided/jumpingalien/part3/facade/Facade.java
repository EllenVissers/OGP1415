package jumpingalien.part3.facade;

import java.util.Collection;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Orientation;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.program.Program;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart3 {

	public Facade() {
	}

	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitPoints();
	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY, int visibleWindowWidth, int visibleWindowHeight, 
			int targetTileX,int targetTileY) {
		return new World(tileSize,nbTilesX,nbTilesY,visibleWindowWidth,visibleWindowHeight,targetTileX,targetTileY);
	}

	@Override
	public int[] getWorldSizeInPixels(World world) {
		int[] result = {world.getWorldWidth(), world.getWorldHeight()};
		return result;
	}

	@Override
	public int getTileLength(World world) {
		return world.getTileSize();
	}

	@Override
	public void startGame(World world) {
		world.startGame();
	}

	@Override
	public boolean isGameOver(World world) {
		return world.getGameOver();
	}

	@Override
	public boolean didPlayerWin(World world) {
		return world.getWon();
	}

	@Override
	public void advanceTime(World world, double dt) {
		world.advanceTime(dt);
	}

	@Override
	public int[] getVisibleWindow(World world) {
		return world.getVisibleWindow();
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return world.getBottomLeftPixelOfTile(tileX, tileY);
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft, int pixelBottom, int pixelRight, int pixelTop) {
		return world.getTilePositions(pixelLeft, pixelBottom, pixelRight, pixelTop);
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY) throws ModelException {
		int[] tilepos = world.getTile(pixelX,pixelY);
		return world.getFeatureAt(tilepos[0], tilepos[1]);
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,int tileType) {
		world.setFeatureAt(tileX,tileY,tileType);
	}

	@Override
	public void setMazub(World world, Mazub alien) {
		world.setMazub(alien);
	}

	@Override
	public boolean isImmune(Mazub alien) {
		return alien.isImmune();
	}

	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		return new Plant(x,y,sprites);
	}

	@Override
	public void addPlant(World world, Plant plant) {
		world.addPlant(plant);
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getAllPlants();
	}

	@Override
	public int[] getLocation(Plant plant) {
		int[] p = {(int) Math.round(plant.getXPosition()), (int) Math.round(plant.getYPosition())};
		return p;
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) {
		return new Shark(x,y,sprites);
	}

	@Override
	public void addShark(World world, Shark shark) {
		world.addShark(shark);
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getAllSharks();
	}

	@Override
	public int[] getLocation(Shark shark) {
		int[] p = {(int) Math.round(shark.getXPosition()), (int) Math.round(shark.getYPosition())};
		return p;
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}

	@Override
	public School createSchool() {
		return new School();
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) {
		return new Slime(x,y,sprites,school);
	}

	@Override
	public void addSlime(World world, Slime slime) {
		world.addSlime(slime);
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getAllSlimes();
	}

	@Override
	public int[] getLocation(Slime slime) {
		int[] p = {(int) Math.round(slime.getXPosition()), (int) Math.round(slime.getYPosition())};
		return p;
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}

	@Override
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Mazub(pixelLeftX,pixelBottomY,sprites);
	}

	@Override
	public int[] getLocation(Mazub alien) {
		int[] p = {(int) Math.round(alien.getXPosition()), (int) Math.round(alien.getYPosition())};
		return p;
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		double[] p = {alien.getXVelocity(), alien.getYVelocity()};
		return p;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		double[] p = {alien.getXAcc(), alien.getYAcc()};
		return p;
	}

	@Override
	public int[] getSize(Mazub alien) {
		int[] p = {alien.getXSize(), alien.getYSize()};
		return p;
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		alien.startJump();
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

	@Override
	public Buzam createBuzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		return new Buzam(pixelLeftX,pixelBottomY,sprites);
	}

	@Override
	public Buzam createBuzamWithProgram(int pixelLeftX, int pixelBottomY, Sprite[] sprites, Program program) {
		return new Buzam(pixelLeftX,pixelBottomY,sprites,program);
	}

	@Override
	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites,	Program program) {
		return new Plant(x,y,sprites,program);
	}

	@Override
	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites,	Program program) {
		return new Shark(x,y,sprites,program);
	}

	@Override
	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites,	School school, Program program) {
		return new Slime(x,y,sprites,school,program);
	}

	@Override
	public ParseOutcome<?> parse(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWellFormed(Program program) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addBuzam(World world, Buzam buzam) {
		world.addBuzam(buzam);
	}

	@Override
	public int[] getLocation(Buzam alien) {
		int[] result = {(int) Math.round(alien.getXPosition()),(int) Math.round(alien.getYPosition())};
		return result;
	}

	@Override
	public double[] getVelocity(Buzam alien) {
		double[] result = {alien.getXVelocity(),alien.getYVelocity()};
		return result;
	}

	@Override
	public double[] getAcceleration(Buzam alien) {
		double[] result = {alien.getXAcc(),alien.getYAcc()};
		return result;
	}

	@Override
	public int[] getSize(Buzam alien) {
		int[] result = {alien.getXSize(),alien.getYSize()};
		return result;
	}

	@Override
	public Sprite getCurrentSprite(Buzam alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public int getNbHitPoints(Buzam alien) {
		return alien.getHitPoints();
	}

}
