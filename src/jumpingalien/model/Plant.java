package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
//import jumpingalien.util.Util;
import jumpingalien.model.Orientation;

public class Plant extends GameObject {
	
	public Plant(double posx, double posy, Sprite[] sprites) throws ModelException {
		super(posx,posy,-speed,0,0,0,Orientation.LEFT,sprites,1,null,false);
	}
	
//	public Plant(double pos_x, double pos_y, Sprite[] sprites) throws ModelException {
//		if (sprites == null)
//			throw new ModelException("Empty array of sprites");
//		this.setXPosition(pos_x);
//		this.setYPosition(pos_y);
//		this.setHitPoints(1);
//		this.setOrientation(Orientation.LEFT);
//		this.setXVelocity(-speed);
//		this.setYVelocity(0);
//		this.setXAcc(0);
//		this.setYAcc(0);
//		this.sprites = sprites;
//		this.setWorld(null);
//	}
	
//	/**
//	 * Variable registering the outer right position of the game world.
//	 */
//	private static double maxX = 1024;
//	/**
//	 * Variable registering the top position of the game world.
//	 */
//	private static double maxY = 768;
//	/**
//	 * Variable registering the outer right position of the game world.
//	 */
//	private static double minX = 0;
//	/**
//	 * Variable registering the bottom position of the game world.
//	 */
//	private static double minY = 0;
	
	//private Orientation orientation;
	
	private final static double speed = 0.5;
	
	private final double timeSwitch = 0.5;
	
	private final double accy = -10;

	@SuppressWarnings("unused")
	private int hitPoints;
//	private double pos_x;
//	private double pos_y;
//	private double vel_x;
//	private double vel_y;
//	private double acc_x;
//	private double acc_y;
//	private Sprite[] sprites;
//	private World world;
	
//	public double getXPosition() {
//		return this.pos_x;
//	}
//	
//	public double getYPosition() {
//		return this.pos_y;
//	}
//	
//	private void setXPosition(double pos) {
//		if (isValidXPosition(pos))
//			this.pos_x = pos;
//	}
//	
	@Override
	protected void setYPosition(double pos) {
	}
//	
//	public double getXVelocity() {
//		return this.vel_x;
//	}
//	
//	private void setXVelocity(double vel) {
//		this.vel_x = vel;
//	}
//	
//	public double getYVelocity() {
//		return this.vel_y;
//	}
//	
	@Override
	protected void setYVelocity(double vel) {
	}
//	
//	public double getXAcc() {
//		return this.acc_x;
//	}
//	
//	private void setXAcc(double acc) {
//		this.acc_x = acc;
//	}
//	
//	public double getYAcc() {
//		return this.acc_y;
//	}
//	
	@Override
	protected void setYAcc(double acc) {
	}
//	
//	private void setHitPoints(int points) {
//		if (this.canHaveAsHitPoints(points))
//		this.hitPoints = points;
//	}
//	
//	private Orientation getOrientation() {
//		return this.orientation;
//	}
//	
//	private void setOrientation(Orientation orientation) {
//		this.orientation = orientation;
//	}
//	
//	private World getWorld() {
//		return this.world;
//	}
//	
//	private void setWorld(World world) {
//		this.world = world;
//	}
//	
//	private boolean canHaveAsHitPoints(int points) {
//		return (points > 0);
//	}
//	
//	/**
//	 * Check whether the given x-position is within the bounds of the game world. 
//	 * @param 	pos  
//	 * 			The position that has to be checked.
//	 * @return  True when the position is valid.
//	 * 			| (pos < maxX) && (pos >= 0)
//	 */
//	private boolean isValidXPosition(double pos) {
//		return ((Double.compare(pos,maxX) < 0) && (Util.fuzzyGreaterThanOrEqualTo(pos,minX)));
//	}
//	
//	/**
//	 * Check whether the given y-position is within the bounds of the game world. 
//	 * @param 	pos  
//	 * 			The position that has to be checked.
//	 * @return  True when the position is valid.
//	 * 			| (pos < maxY) && (pos >= 0)
//	 */
//	private boolean isValidYPosition(double pos) {
//		return ((Double.compare(pos,maxY) < 0) && (Util.fuzzyGreaterThanOrEqualTo(pos,minY)));
//	}
	
	private boolean onGround() {
		int[] tileposfirst = getWorld().getTile((int) Math.round(getXPosition()),(int) Math.round(getYPosition()));
		if (getWorld().getFeatureAt(tileposfirst[0],tileposfirst[1]) == 1)
			return true;
		int[] tileposlast = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()),(int) Math.round(getYPosition()));
		if (getWorld().getFeatureAt(tileposlast[0],tileposlast[1]) == 1)
			return true;
		else
		{
			int d = tileposlast[0] - tileposfirst[0];
			if (d < 2)
				return false;
			else
			{
				for (int i = 1; i < d; i++) {
					if (getWorld().getFeatureAt(tileposfirst[0] + i, tileposfirst[1]) == 1)
						return true;
				}
				return false;
			}
		}
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
	
	@Override
	protected void terminate() {
		terminate();
		//delay van 0.6s
	}
	
	private void Fall(double time) {
		// Check ook of nieuwe positie buiten wereld + doe meteen iets
		// check of collision met andere elementen, stenen, shark, ...
		this.setYPosition(this.getYPosition() + accy*time);
	}
	
	private void Move(double time) {
		double s;
		// Checken als nieuwe positie buiten wereld: dood, opschuiven, ...
		if (reachesTimeSwitch(time))
		{
			double t1 = (timeSwitch - timer);
			double t2 = ((time+timer) - timeSwitch);
			if (this.getOrientation() == Orientation.RIGHT)
			{
				s = this.getXPosition() + (t1-t2)*speed;
				this.setOrientation(Orientation.LEFT);
			}
			else
			{
				s = this.getXPosition() - (t1-t2)*speed;
				this.setOrientation(Orientation.RIGHT);
			}
			timer = t2;	
		}
		else
		{
			if (this.getOrientation() == Orientation.RIGHT)
				s = this.getXPosition() + time*speed;
			else
				s = this.getXPosition() - time*speed;
			timer = timer + time;
		}
		try {
			this.CheckWorld(s);
		} catch (ImpassableTerrainException exc) {
			// Als exception gegooid wordt, moet de horizontale positie niet veranderen, want dt is de lengte van 1 pixel.
		}
	}
	
	private double getDT() {
		List<Double> l = new ArrayList<Double>();
		if (getXVelocity() != 0)
			l.add(1/Math.abs(getXVelocity()/100));
		if (getYVelocity() != 0)
			l.add(1/Math.abs(getYVelocity()/100));
		if (getXAcc() != 0)
			l.add((Math.sqrt(2*Math.abs(getXAcc()/100)+Math.pow(getXVelocity()/100,2)) - Math.abs(getXVelocity()/100))/Math.abs(getXAcc()/100));
		if (getYAcc() != 0)
			l.add((Math.sqrt(2*Math.abs(getYAcc()/100)+Math.pow(getYVelocity()/100,2)) - Math.abs(getYVelocity()/100))/Math.abs(getYAcc()/100));
		if (l.size() == 1)
			return l.get(0);
		if (l.size() == 2)
			return Math.min(l.get(0), l.get(1));
		if (l.size() == 3)
			return Math.min(Math.min(l.get(0), l.get(1)),l.get(2));
		else
			return Math.min(Math.min(l.get(0), l.get(1)),Math.min(l.get(2),l.get(3)));
	}
	
	private void advance(double t) {
		if (! (onGround()))
			Fall(t);
		if (! isTerminated())
			Move(t);
	}
	
	public void advanceTime(double time) {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		double dt = getDT();
		int i = (int) (time/dt);
		double r = time%dt;
		for (int p = 0; p<i; p++) {
			advance(dt);
		}
		advance(r);
	}
}
