package jumpingalien.model;

import jumpingalien.model.School;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Slime {

	public Slime(double pos_x, double pos_y, Sprite[] sprites, School school) {
		this.setXPosition(pos_x);
		this.setYPosition(pos_y);
		this.sprites = sprites;
		this.setSchool(school);
		this.setHitPoints(startHitPoints);
		this.setOrientation(Orientation.LEFT);
		this.setXVelocity(-startVelX);
		this.setXAcc(-accx);
		this.setMaxVel(-maxSpeed);
	}
	
	private double pos_x;
	private double pos_y;
	private Sprite[] sprites;
	private School school;
	private int hitPoints;
	private Orientation orientation;
	private double vel_x;
	private double acc_x;
	private double maxVel;
	
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
	
	private double accy = -10;
	
	private double accx = 0.7;
	
	private double maxSpeed = 2.5;
	
	private double minDT = 2;
	
	private double maxDT = 6;
	
	private double startVelX = 1.0;
	
	private int collisionShark = 50;
	
	/**
	 * The default value of the amount of hitpoints of a newly created Slime.
	 */
	private static int startHitPoints = 100;
	
	public double getXPosition() {
		return this.pos_x;
	}
	
	private void setXPosition(double pos) {
		if (isValidXPosition(pos))
			this.pos_x = pos;
	}
	
	public double getYPosition() {
		return this.pos_y;
	}
	
	private void setYPosition(double pos) {
		if (isValidYPosition(pos))
			this.pos_y = pos;
	}
	
	public double getXVelocity() {
		return this.vel_x;
	}
	
	private void setXVelocity(double vel) {
		if (isValidYPosition(vel))
			this.vel_x = vel;
	}
	
	public double getXAcc() {
		return this.acc_x;
	}
	
	private void setXAcc(double acc) {
		if (isValidYPosition(acc))
			this.acc_x = acc;
	}
	
	public double getMaxVel() {
		return this.maxVel;
	}
	
	private void setMaxVel(double vel) {
		this.maxVel = vel;
	}
	
	public School getSchool() {
		return this.school;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
	private boolean canHaveAsHitPoints(int points) {
		return (points > 0);
	}
	
	public void setHitPoints(int points) {
		if (this.canHaveAsHitPoints(points))
		this.hitPoints = points;
	}
	
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	private Orientation getOrientation() {
		return this.orientation;
	}
	
	private void setOrientation(Orientation orientation) {
		this.orientation = orientation;
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
		this.setXPosition(s);
	}
	
	public void advanceTime(double time) {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		if (! (onSolidGround()))
			Fall(time);
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
