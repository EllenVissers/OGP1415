package jumpingalien.model;

import jumpingalien.util.Sprite;
//import jumpingalien.util.Util;
import jumpingalien.util.ModelException;
import jumpingalien.model.ImpassableTerrainException;

import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

public class Shark extends GameObject {

	public Shark(double posx, double posy, Sprite[] sprites) throws ModelException {
		super(posx,posy,0,0,0,0,Orientation.LEFT,sprites,startHitPoints,null,false);
		setXVelocity(-startVelX);
		setXAcc(-accx);
		setMaxVel(-maxSpeed);
		//begin vel/acc adhv positie van shark
	}
	
	/**
	 * The default value of the amount of hitpoints of a newly created Shark.
	 */
	private static int startHitPoints = 100;
	
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
	
	private double accy = -10;
	
	private double accx = 1.5;
	
	private double maxSpeed = 4;
	
	private double startVelX = 1.0;

	private double startVelY = 2.0;

	private double minDT = 1;
	
	private double maxDT = 4;

	private double minAccy = -0.2;
	
	private double maxAccy = 0.2;
	
//	private double pos_x;
//	private double pos_y;
	private Sprite[] sprites;
//	private int hitPoints;
//	private Orientation orientation;
//	private double vel_x;
//	private double vel_y;
//	private double acc_x;
//	private double acc_y;
	private double maxVel;
//	private World world;
	
//	private void setXPosition(double pos) {
//		if (isValidXPosition(pos))
//			this.pos_x = pos;
//	}
//	
//	public double getXPosition() {
//		return this.pos_x;
//	}
//	
//	private void setYPosition(double pos) {
//		if (isValidYPosition(pos))
//			this.pos_y = pos;
//	}
//	
//	public double getYPosition() {
//		return this.pos_y;
//	}
	
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
//	public double getXVelocity() {
//		return this.vel_x;
//	}
//	
	@Override
	protected void setXVelocity(double vel) {
	}
//	
//	public double getYVelocity() {
//		return this.vel_y;
//	}
//	
//	private void setYVelocity(double vel) {
//		this.vel_y = vel;
//	}
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
//	private void setYAcc(double acc) {
//		if (isValidYPosition(acc))
//			this.acc_y = acc;
//	}
	
	public double getMaxVel() {
		return this.maxVel;
	}
	
	private void setMaxVel(double vel) {
		this.maxVel = vel;
	}
	
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

	private boolean isValidTime(double time) {
		return ((time >= 0) && (time < 0.2));
	}
	
	private double randomTime() {
		return (minDT + Math.random()*(maxDT-minDT));
	}
	
	private double randomAccY() {
		return (minAccy + Math.random()*(maxAccy-minAccy));
	}
	
	private int randomSignal() {
		return (int)(Math.random() * 2);
	}
	
	@Override
	protected void terminate() {
		
	}
	
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed);
	}
	
	private int lastJump = 0; // nog optellen!
	private double timer = 0;
	private double timeslot = randomTime();
	
	private boolean touchingWater() {
		int[] left = getWorld().getTile((int) Math.round(getXPosition()),(int) Math.round(getYPosition()));
		int[] right = getWorld().getTile((int) Math.round(getXPosition() + getCurrentSprite().getWidth()),
				(int) Math.round(getYPosition()));
		int b = right[0]-left[0];
		for (int i = 0; i<b+1; i++)
			if (getWorld().getFeatureAt(left[0] + i,left[1]) == 2)
				return true;
		return false;
	}
	
	private boolean isSubmerged() {
		int[] bottomleft = getWorld().getTile((int) Math.round(getXPosition()),(int) Math.round(getYPosition()));
		int[] topright = getWorld().getTile((int) Math.round(getXPosition() + getCurrentSprite().getWidth()),
				(int) Math.round(getYPosition() + getCurrentSprite().getHeight()));
		int b = topright[0]-bottomleft[0];
		int h = topright[1]-bottomleft[1];
		for (int i = 0; i<b+1; i++)
			for (int j=0; j<h+1; j++)
				if (getWorld().getFeatureAt(bottomleft[0] + i,bottomleft[1] + j) != 2)
					return false;
		return true;
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
	
	private void Jump(double time) {
		//Wat te doen als buiten wereld: binnen wereld zetten of dood?
		this.setYPosition((int) Math.round(this.getYPosition() + 0.5*accy*time*time + startVelY*time));
	}
	
	private void Swim(double time) {
		//Zolang dat er volledig in water is, anders accy = 0.
		this.setYAcc(randomAccY());
		this.setYPosition(this.getYPosition() + 0.5*this.getYAcc()*time*time);
	}
	
	private void Fall(double time) {
		//Vanaf het moment dat hij in water is, niet meer vallen!
		this.setYPosition(this.getYPosition() + accy*0.5*time*time);
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
			this.CheckWorld(s);
		} catch (ImpassableTerrainException exc) {
			// Als exception gegooid wordt, moet de horizontale positie niet veranderen, want dt is de lengte van 1 pixel.
		}
	}
	
	private void advance(double t) {
		if ((lastJump > 4) && (randomSignal() == 1) && (touchingWater()))
			this.Jump(t);
		else if (isSubmerged())
			this.Swim(t);
		else
			this.Fall(t);
		Move(t); // Beweegt altijd?
	}
	
	public void advanceTime(double time) throws ModelException {
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
	
	public Sprite getCurrentSprite() {
		assert (this.sprites.length == 2);
		if (this.getOrientation() == Orientation.LEFT)
			return this.sprites[0];
		else
			return this.sprites[1];
	}
	
}
