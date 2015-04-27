package jumpingalien.model;
import java.util.ArrayList;

import jumpingalien.util.Sprite;
import jumpingalien.util.ModelException;
import jumpingalien.util.Util;

/**
 * A class of sharks involving a horizontal and vertical position, velocity and acceleration, a list of images, an orientation, 
 * a number of hitpoints, a world, a maximum velocity and a variable registering whether the object is terminated.
 * @invar 	...
 * @author Ellen Vissers, Nina Versin
 * @version 1.0
 */
public class Shark extends GameObject {

	/**
	 * Initialize a new shark with a given position an list of images.
	 * @param 	posx
	 * 			The horizontal position of the shark.
	 * @param 	posy
	 * 			The vertical position of the shark.
	 * @param 	sprites
	 * 			The list of images of the shark.
	 * @throws 	ModelException
	 * 			Throws an exception if the list of images is empty.
	 */
	public Shark(double posx, double posy, Sprite[] sprites) throws ModelException {
		super(posx,posy,0,0,-accx,0,Orientation.LEFT,sprites,startHitPoints,null,false,0);
		setMaxVel(-maxSpeed);
	}
	
	//VARIABLES
	/**
	 * The default value of the amount of hitpoints of a newly created Shark.
	 */
	private static int startHitPoints = 100;
	/**
	 * Variable registering the value of the horizontal acceleration of a shark.
	 */
	public static final double accx = 1.5;
	/**
	 * Variable registering the value of the maximum speed of a shark.
	 */
	protected static final double maxSpeed = 4;
	/**
	 * Variable registering the value of the vertical start velocity for a jumping shark.
	 */
	private static final double startVelY = 2.0;
	/**
	 * Variable registering the minimum boundary of a time interval for movement.
	 */
	private static final double minDT = 1;
	/**
	 * Variable registering the maximum boundary of a time interval for movement.
	 */
	private static final double maxDT = 4;
	/**
	 * Variable registering the minimum boundary of the vertical acceleration for swimming.
	 */
	private static final double minAccy = -0.2;
	/**
	 * Variable registering the maximum boundary of the vertical acceleration for swimming.
	 */
	private static final double maxAccy = 0.2;
	/**
	 * Variable registering the maximum velocity of a shark.
	 */
	private double maxVel;
	/**
	 * Variable registering the time a shark is touching magma.
	 */
	private double timerMagma = 0;
	/**
	 * Variable registering the time a shark is touching air.
	 */
	private double timerAir = 0;
	/**
	 * Variable registering the time durations since the last jump.
	 */
	private int lastJump = 0;
	/**
	 * Variable registering the time a shark is doing the same movement.
	 */
	private double timer = 0;
	/**
	 * Variable registering the length of the current movement.
	 */
	private double timeslot = randomTime();
	
	//GETTERS AND SETTERS
	/**
	 * Return the maximum velocity of the shark.
	 * @return 	The maximum velocity.
	 * 			| this.maxVel
	 */
	private double getMaxVel() {
		return this.maxVel;
	}
	
	/**
	 * Set the maximum velocity to a given velocity.
	 * @param 	vel
	 * 			The new maximum velocity.
	 * @post 	The new maximum velocity is the given velocity.
	 * 			| new.getMaxVel() == vel
	 */
	protected void setMaxVel(double vel) {
		this.maxVel = vel;
	}
	
	//CHECKERS
	/**
	 * Check whether the shark reaches his maximum velocity within the given time duration.
	 * @param 	time
	 * 			The time duration.
	 * @return 	True if the shark reaches his maximum velocity.
	 */
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed);
	}
	
	/**
	 * Check whether a shark can jump.
	 * @return 	True if its bottom perimeter overlaps with water tiles.
	 */
	private boolean SharkcanJump() {
		int[] left = getWorld().getTile((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()));
		int[] right = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),
				(int) Math.round(getYPosition()));
		int b = right[0]-left[0]+1;
		for (int i = 0; i < b; i++)
			if ( (getWorld().getFeatureAt(left[0]+i,left[1]) == 1) || (getWorld().getFeatureAt(left[0]+i,left[1]) == 2) )
				return true;
		return false;
	}
	
	/**
	 * Check whether a shark is submerged.
	 * @return	True is all of its pixels overlap with water tiles.
	 */
	private boolean isSubmerged() {
		int[][] tileposin = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
				(int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
		for (int[] pos : tileposin)
			if (getWorld().getFeatureAt(pos[0],pos[1]) != 2)
				 return false;
		return true;
	}
	
	/**
	 * Check if the timer reaches the timeslot within the given time duration.
	 * @param 	time
	 * 			The time duration.
	 * @return	True if the timeslot is reached after adding the value time.
	 */
	private boolean reachesTimeSlot(double time) {
		return (timer+time >= timeslot);
	}	
	
	/**
	 * Check whether a shark is jumping.
	 * @return	True when it is jumping.
	 * 			| getYVelocity() > 0
	 */
	private boolean isJumping() {
		return (getYVelocity() > 0);
	}
	
	//METHODS
	/**
	 * Return a time value between the default minimum and maximum values.
	 * @return 	A random value between the minimum and maximum time.
	 */
	private double randomTime() {
		return (minDT + Math.random()*(maxDT-minDT));
	}
	
	/**
	 * Return an acceleration value between the default minimum and maximum values.
	 * @return 	A random value between the minimum and maximum acceleration.
	 */
	private double randomAccY() {
		return (minAccy + Math.random()*(maxAccy-minAccy));
	}
	
	/**
	 * Return a random signal 0 or 1.
	 */
	private int randomSignal() {
		return (int)(Math.random() * 2);
	}
	
	/**
	 * Update the position and velocity of the shark after jumping for a given time period.
	 * @param 	time
	 * 			The time period after which the new position and velocity are calculated.
	 * @effect	The velocity and position are updated with setYPosition and setYVelocity.
	 * 			| ...
	 */
	private void Jump(double time) {
		double h = this.getYPosition() + 100*(0.5*getYAcc()*time*time + getYVelocity()*time);
		double v;
		if ((isSubmerged()) && (Util.fuzzyLessThanOrEqualTo(getYVelocity(),-(getYAcc()/2)*time)))
		{
			h = getYPosition();
		}
		v = getYVelocity()+getYAcc()*time;
		try {
			CheckWorldV(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
			v = getYVelocity();
		}
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				h = collV(exc,h);
			else
				h = fixCollV(exc,h);
			v = getYVelocity();
		}
		setYVelocity(v);
		setYPosition(h);
	}
	
	/**
	 * Update the position and velocity of the shark after swimming for a given time period.
	 * @param 	time
	 * 			The time period after which the new position and velocity are calculated.
	 * @effect	The velocity and position are updated with setYPosition and setYVelocity.
	 * 			| ...
	 */
	private void Swim(double time) {
		double h = getYPosition() + 100*(0.5*getYAcc()*time*time + getYVelocity()*time);
		setYVelocity(getYVelocity()+getYAcc()*time);
		try {
			CheckWorldV(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				h = collV(exc,h);
			else
				h = fixCollV(exc,h);
		}
		setYPosition(h);
	}
	
	/**
	 * Update the position and velocity of the shark after falling for a given time period.
	 * @param 	time
	 * 			The time period after which the new position and velocity are calculated.
	 * @effect	The velocity and position are updated with setYPosition and setYVelocity.
	 * 			| ...
	 */
	private void Fall(double time) {
		//System.out.println("fall");
		double h = getYPosition() + 100*(getYVelocity()*time + accy*0.5*time*time);
		setYVelocity(getYVelocity()+accy*time);
		try {
			CheckWorldV(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				h = collV(exc,h);
			else
				h = fixCollV(exc,h);
		}
		setYPosition(h);
	}
	
	private void startMove() {
		if (randomSignal() == 1)
		{
			if (getOrientation() == Orientation.LEFT)
				setXVelocity(0);
			setOrientation(Orientation.RIGHT);
			setXAcc(accx);
			setMaxVel(maxSpeed);
		}
		else
		{
			if (getOrientation() == Orientation.RIGHT)
				setXVelocity(0);
			setXAcc(-accx);
			setOrientation(Orientation.LEFT);
			setMaxVel(-maxSpeed);
		}
	}
	
	private void endMove() {
		setXAcc(0);
	}
	
	private void startJump() {
		lastJump = 5;
		setYVelocity(startVelY);
		setYAcc(accy);
	}
	
	private void endJump() {
		setYAcc(0);
		if (getYVelocity() > 0)
			setYVelocity(0);
	}
	
	private void startSwim() {
		setYAcc(randomAccY());
		setYVelocity(0);
	}
	
	private void endSwim() {
		setYAcc(0);
		setYVelocity(0);
	}
	
	/**
	 * Update the position and velocity of the shark after moving for a given time period.
	 * @param 	time
	 * 			The time period after which the new position and velocity are calculated.
	 * @effect	The velocity and position are updated with setXPosition and setXVelocity.
	 * 			| ...
	 */
	private void Move(double time) {
		double s;
		{
			double t1; double t2;
			if (reachesMaxSpeed(time))
			{
				t1 = (this.getMaxVel() - this.getXVelocity())/(getXAcc());
				t2 = time - t1;
				s = (this.getXPosition() + 100*(t1*this.getXVelocity() + 0.5*t1*t1*this.getXAcc() + t2*this.getMaxVel()));
				if (this.getOrientation() == Orientation.RIGHT)
					this.setXVelocity(maxSpeed);
				else
					this.setXVelocity(-maxSpeed);
			}
			else
			{
				s = (this.getXPosition() + 100*(time*this.getXVelocity() + 0.5*time*time*this.getXAcc()));
				this.setXVelocity(this.getXVelocity() + time*this.getXAcc());
			}
			timer = timer + time;
		}
		try {
			CheckWorldH(s);
		} catch (CollisionException exc) {
			s = fixWorldH(exc,s);
		}
		try {
			CheckCollH(s);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				s = collH(exc,s);
			else
				s = fixCollH(exc,s);
		}
		setXPosition(s);
	}
	
	/**
	 * Update the state of the shark after touching air for a given time duration.
	 * @param 	time
	 * 			The time duration.
	 * @post 	...
	 */
	private void touchAir(double time) {
		timerAir += time;
		int times = (int) Math.floor(timerAir*5);
		timerAir -= times*0.2;
		reduceHitPoints(times*6);
	}
	
	/**
	 * Update the state of the shark after touching magma for a given time duration.
	 * @param 	time
	 * 			The time duration.
	 * @post 	...
	 */
	private void touchMagma(double time) {
		if (timerMagma == 0)
			reduceHitPoints(50);
		else
		{
			timerMagma += time;
			int times = (int) Math.floor(timerMagma*5);
			timerMagma -= times*0.2;
			reduceHitPoints(times*50);
		}
	}
	
	/**
	 * Update the state of the shark if the end of the timeslot is reached.
	 * @post 	...
	 */
	private void endOfTimeSlot() {
		endMovement();
		timeslot = randomTime();
		startMovement();
	}
	
	/**
	 * Method to update the new position after a certain time duration.
	 * @param 	time
	 * 			The time duration in which the object moves 1 pixel.
	 */
	private void advance(double time) {
		if (isTerminated())
		{
			setTerminatedTime(getTerminatedTime()+time);
			if (Util.fuzzyGreaterThanOrEqualTo(getTerminatedTime(), 0.6))
				setWorld(null);
		}
		else
		{
			if (reachesTimeSlot(time))
			{
				double t1 = timeslot - timer;
				double t2 = (timer+time) - timeslot;
				UpdatePositions(t1);
				endOfTimeSlot();
				UpdatePositions(t2);
				timer = t2;
			}
			else
			{
				UpdatePositions(time);
				timer += time;
			}
			ArrayList<Integer> medium = CheckMedium();
			if (medium.contains(0))
				touchAir(time);
			else
				timerAir = 0;
			if (medium.contains(3))
				touchMagma(time);
			else
				timerMagma = 0;
		}
	}
	
	private void startMovement() {
		startMove();
		int signal = randomSignal();
		if (lastJump > 0)
			lastJump -= 1;
		if ((SharkcanJump()) && (lastJump == 0) && (signal == 1) )
			startJump();
		else if (isSubmerged())
			startSwim();
	}
	
	private void endMovement() {
		endMove();
		endJump();
		endSwim();
	}
	
	/**
	 * Method to update the new position after a certain time duration after checking whether the timeslot is reached.
	 * @param 	time
	 * 			The time duration in which the object moves 1 pixel.
	 */
	private void UpdatePositions(double time) {
		Move(time);
		if ( (lastJump == 5) && ( ( (isJumping()) && (! isSubmerged()) ) || ( (isSubmerged()) && (getYVelocity() <= (-(getYAcc()/2)*time)) ) ) )
			Jump(time);
		else if (! isSubmerged())
			Fall(time);
		else
			Swim(time);
	}
	
	/**
	 * Update the position of the shark after a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the new position is calculated.
	 */
	@Override
	public void advanceTime(double time) throws ModelException {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		while (time > 0)
		{
			double dt = getDT(time,getXVelocity(),getYVelocity(),getXAcc(),getYAcc());
			if (Util.fuzzyGreaterThanOrEqualTo(time, dt))
				advance(dt);
			else
				advance(time);
			time -= dt;
		}
	}
	
}
