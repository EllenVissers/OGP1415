package jumpingalien.model;

import java.util.ArrayList;
import java.util.List;

import jumpingalien.model.School;
//import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

//import jumpingalien.util.Util;
//import jumpingalien.model.ImpassableTerrainException;
import java.util.Collection;

public class Slime extends GameObject {

	public Slime(double pos_x, double pos_y, Sprite[] sprites, School school) throws ModelException {
		super(pos_x,pos_y,-startVelX,0,-accx,0,Orientation.LEFT,sprites,startHitPoints, null,false);
		setSchool(school);
		setMaxVel(-maxSpeed);
	}
	
//	public Slime1(double pos_x, double pos_y, Sprite[] sprites, School school) {
//		this.setXPosition(pos_x);
//		this.setYPosition(pos_y);
//		this.sprites = sprites;
//		this.setSchool(school);
//		this.setHitPoints(startHitPoints);
//		this.setOrientation(Orientation.LEFT);
//		this.setXVelocity(-startVelX);
//		this.setYVelocity(0);
//		this.setXAcc(-accx);
//		this.setYAcc(0);
//		this.setMaxVel(-maxSpeed);
//		this.setWorld(world);
//	}
	
//	private double pos_x;
//	private double pos_y;
//	private Sprite[] sprites;
	private School school;
//	private int hitPoints;
//	private Orientation orientation;
//	private double vel_x;
//	private double vel_y;
//	private double acc_x;
//	private double acc_y;
	private double maxVel;
//	private World world;
	
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
	
	private static double accy = -10;
	
	private static double accx = 0.7;
	
	private static double maxSpeed = 2.5;
	
	private static double minDT = 2;
	
	private static double maxDT = 6;
	
	private static double startVelX = 1.0;
	
	//private int collisionShark = 50;
	
	/**
	 * The default value of the amount of hitpoints of a newly created Slime.
	 */
	private static int startHitPoints = 100;
	
//	public double getXPosition() {
//		return this.pos_x;
//	}
//	
//	private void setXPosition(double pos) {
//		if (isValidXPosition(pos))
//			this.pos_x = pos;
//	}
//	
//	public double getYPosition() {
//		return this.pos_y;
//	}
//	
//	private void setYPosition(double pos) {
//		if (isValidYPosition(pos))
//			this.pos_y = pos;
//	}
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
	
	public double getMaxVel() {
		return this.maxVel;
	}
	
	protected void setMaxVel(double vel) {
		this.maxVel = vel;
	}
	
	public School getSchool() {
		return this.school;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
//	private boolean canHaveAsHitPoints(int points) {
//		return (points > 0);
//	}
//	
//	public void setHitPoints(int points) {
//		if (this.canHaveAsHitPoints(points))
//		this.hitPoints = points;
//	}
//	
//	public int getHitPoints() {
//		return this.hitPoints;
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
	
	private boolean isValidTime(double time) {
		return ((time >= 0) && (time < 0.2));
	}
	
	private double timer = 0;
	private double timeslot = randomTime();
	
	private double randomTime() {
		return (minDT + Math.random()*(maxDT-minDT));
	}
	
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed);
	}
	
	@Override
	protected void terminate() {
		
	}
	
	@Override
	protected void reduceHitPoints(int points) {
		if ((getHitPoints() - points) <= 0)
			this.terminate();
		else
			setHitPoints(getHitPoints()-points);
		Collection<Slime> slimes = this.getSchool().getSlimes();
		for (Slime slime : slimes)
			if (this != slime)
				slime.setHitPoints(getHitPoints() - 1);
	}
	
	private void Fall(double time) {
		// Check ook of nieuwe positie buiten wereld + doe meteen iets
		// check of collision met andere elementen, stenen, shark, ...
		this.setYPosition(this.getYPosition() + accy*time);
	}
	
	private void Move(double time) {
		// Checken als nieuwe positie buiten wereld: dood, opschuiven, ...
		double s;
		if ((timer+time) >= timeslot)
		{
			double t1 = timeslot - timer;
			double t2 = time - t1;
			double t3; double t4;
			if (reachesMaxSpeed(t1))
			{
				t3 = (this.getMaxVel() - this.getXVelocity())/(getXAcc());
				t4 = t1 - t3;
				s = (this.getXPosition() + t3*this.getXVelocity() + 0.5*(t3*t3 - t2*t2)*this.getXAcc() + 
						t4*this.getMaxVel() - t2*startVelX);
			}
			else
				s = (this.getXPosition() + t1*this.getXVelocity() + 0.5*(t1*t1 - t2*t2)*this.getXAcc() - t2*startVelX);
			if (this.getOrientation() == Orientation.RIGHT)
			{
				this.setXVelocity(-startVelX-t2*accx);
				this.setMaxVel(-maxSpeed);
				this.setXAcc(-accx);
				this.setOrientation(Orientation.LEFT);
			}
			else
			{
				this.setXVelocity(startVelX+t2*accx);
				this.setMaxVel(maxSpeed);
				this.setXAcc(accx);
				this.setOrientation(Orientation.RIGHT);
			}
			timer = t2;
			timeslot = randomTime();
		}
		else
		{
			double t1; double t2;
			if (reachesMaxSpeed(time))
			{
				t1 = (this.getMaxVel() - this.getXVelocity())/(getXAcc());
				t2 = time - t1;
				s = (this.getXPosition() + t1*this.getXVelocity() + 0.5*t1*t1*this.getXAcc() + t2*this.getMaxVel());
				if (this.getOrientation() == Orientation.RIGHT)
					this.setXVelocity(maxSpeed);
				else
					this.setXVelocity(-maxSpeed);
			}
			else
			{
				s = (this.getXPosition() + time*this.getXVelocity() + 0.5*time*time*this.getXAcc());
				this.setXVelocity(this.getXVelocity() + time*this.getXAcc());
			}
			timer = timer + time;
		}
		try {
			CheckCollisionH(s);
		} catch (CollisionException exc) {
			fixCollisionH(exc,s,startHitPoints);
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
