package jumpingalien.model;

import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import jumpingalien.model.Orientation;

public class Plant {
	
	public Plant(double pos_x, double pos_y, Sprite[] sprites) throws ModelException {
		if (sprites == null)
			throw new ModelException("Empty array of sprites");
		this.setXPosition(pos_x);
		this.setYPosition(pos_y);
		this.setHitPoints(1);
		this.setOrientation(Orientation.LEFT);
		this.sprites = sprites;
	}
	
	/**
	 * Variable registering the outer right position of the game world.
	 */
	private static double maxX = 1024;
	/**
	 * Variable registering the top position of the game world.
	 */
	private static double maxY = 768;
	/**
	 * Variable registering the outer right position of the game world.
	 */
	private static double minX = 0;
	/**
	 * Variable registering the bottom position of the game world.
	 */
	private static double minY = 0;
	
	private Orientation orientation;
	
	private final double speed = 0.5;
	
	private final double timeSwitch = 0.5;
	
	private final double accy = -10;

	@SuppressWarnings("unused")
	private int hitPoints;
	private double pos_x;
	private double pos_y;
	private Sprite[] sprites;
	
	public double getXPosition() {
		return this.pos_x;
	}
	
	public double getYPosition() {
		return this.pos_y;
	}
	
	private void setXPosition(double pos) {
		if (isValidXPosition(pos))
			this.pos_x = pos;
	}
	
	private void setYPosition(double pos) {
		if (isValidYPosition(pos))
			this.pos_y = pos;
	}
	
	private void setHitPoints(int points) {
		if (this.canHaveAsHitPoints(points))
		this.hitPoints = points;
	}
	
	private Orientation getOrientation() {
		return this.orientation;
	}
	
	private void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	private boolean canHaveAsHitPoints(int points) {
		return (points > 0);
	}
	
	/**
	 * Check whether the given x-position is within the bounds of the game world. 
	 * @param 	pos  
	 * 			The position that has to be checked.
	 * @return  True when the position is valid.
	 * 			| (pos < maxX) && (pos >= 0)
	 */
	private boolean isValidXPosition(double pos) {
		return ((Double.compare(pos,maxX) < 0) && (Util.fuzzyGreaterThanOrEqualTo(pos,minX)));
	}
	
	/**
	 * Check whether the given y-position is within the bounds of the game world. 
	 * @param 	pos  
	 * 			The position that has to be checked.
	 * @return  True when the position is valid.
	 * 			| (pos < maxY) && (pos >= 0)
	 */
	private boolean isValidYPosition(double pos) {
		return ((Double.compare(pos,maxY) < 0) && (Util.fuzzyGreaterThanOrEqualTo(pos,minY)));
	}
	
	private boolean onSolidGround() {
		
	}
	
	private boolean reachesTimeSwitch(double time) {
		return ((timer + time) >= timeSwitch);
	}
	
	private double timer = 0;
	
	/**
	 * Check whether the given time duration is valid.
	 * @param 	time
	 * 			The time duration that has to be checked.
	 * @return  True when the time is valid.
	 * 			| (time >= 0) && (time < 0.2)
	 */
	private boolean isValidTime(double time) {
		return ((time >= 0) && (time < 0.2));
	}
	
	private void Fall(double time) {
		// Check ook of nieuwe positie buiten wereld + doe meteen iets
		// check of collision met andere elementen, stenen, shark, ...
		this.setYPosition(this.getYPosition() + accy*time);
	}
	
	private void Move(double time) {
		// Checken als nieuwe positie buiten wereld: dood, opschuiven, ...
		if (reachesTimeSwitch(time))
		{
			double t1 = (timeSwitch - timer);
			double t2 = ((time+timer) - timeSwitch);
			if (this.getOrientation() == Orientation.RIGHT)
			{
				this.setXPosition(this.getXPosition() + (t1-t2)*speed);
				this.setOrientation(Orientation.LEFT);
			}
			else
			{
				this.setXPosition(this.getXPosition() - (t1-t2)*speed);
				this.setOrientation(Orientation.RIGHT);
			}
			timer = t2;	
		}
		else
		{
			if (this.getOrientation() == Orientation.RIGHT)
				this.setXPosition(this.getXPosition() + time*speed);
			else
				this.setXPosition(this.getXPosition() - time*speed);
			timer = timer + time;
		}
	}
	
	public void advanceTime(double time) {
		if (! (onSolidGround()))
			Fall(time);
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		try {
			Move(time);
		}
		catch (ImpassableTerrainException exc) {
			// Wat als botst tegen blok, water, ander element, ...?
		}
	}
	
	public Sprite getCurrentSprite() {
		assert (this.sprites.length == 2);
		if (this.getOrientation() == Orientation.LEFT)
			return this.sprites[0];
		else
			return this.sprites[1];
	}
}
