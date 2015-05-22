package jumpingalien.model;
import java.util.ArrayList;
import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.program.program.Program;
import jumpingalien.util.Sprite;
import jumpingalien.util.ModelException;
import jumpingalien.util.Util;

/**
 * A class of sharks involving a horizontal and vertical position, velocity and acceleration, a list of images, an orientation, 
 * a number of hitpoints, a world, a maximum velocity,a variable registering whether the shark is terminated, and the time it has been terminated.
 * @author Ellen Vissers, Nina Versin
 * @version 1.0
 * @invar A shark will never move faster than a given maximum velocity
 * 			| this.getXVelocity() <= maxSpeed
 * @invar The shark will always stay within the boundaries of the game world
 * 			| this.getXPosition() >= 0 && this.getXPosition() <= this.getWorld().getWorldWidth()
 * 			| this.getYPosition() >= 0 && this.getYPosition() <= this.getWorld().getWorldHeight()
 * @invar The orientation of a shark will never be NONE
 * 			| this.Orientation != Orientation.NONE
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
	 * @effect	The maximum speed is set with setMaxVel.
	 * 			| setMaxVel(-maxSpeed)
	 */
	public Shark(double posx, double posy, Sprite[] sprites) throws ModelException {
		super(posx,posy,0,0,-accx,0,Orientation.LEFT,sprites,startHitPoints,null,false,0,null);
		setMaxVel(-maxSpeed);
	}
	
	/**
	 * Initialize a new shark with a given position an list of images.
	 * @param 	posx
	 * 			The horizontal position of the shark.
	 * @param 	posy
	 * 			The vertical position of the shark.
	 * @param 	sprites
	 * 			The list of images of the shark.
	 * @param	program
	 * 			The controlling program of this shark.
	 * @throws 	ModelException
	 * 			Throws an exception if the list of images is empty.
	 * @effect	The maximum speed is set with setMaxVel.
	 * 			| setMaxVel(-maxSpeed)
	 */
	public Shark(double posx, double posy, Sprite[] sprites, Program program) throws ModelException {
		super(posx,posy,0,0,-accx,0,Orientation.LEFT,sprites,startHitPoints,null,false,0,program);
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
	@Basic
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
	 *			| (Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed
	 */
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed);
	}
	
	/**
	 * Check whether a shark can jump.
	 * @return 	True if its bottom perimeter overlaps with water tiles.
	 *			| int[] left = getWorld().getTile((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()));
	 * 			| int[] right = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),
	 * 			| (int) Math.round(getYPosition()));
	 * 			| (getWorld().getFeatureAt(left[0]+i,left[1]) == 1) || (getWorld().getFeatureAt(left[0]+i,left[1]) == 2)
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
	 *			| int[][] tileposin = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
	 * 			| (int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
	 * 			| for (int[] pos : tileposin)
	 * 			|	if (getWorld().getFeatureAt(pos[0],pos[1]) != 2)
	 * 			|		return false;
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
	 *			| timer+time >= timeslot
	 */
	private boolean reachesTimeSlot(double time) {
		return (timer+time >= timeslot);
	}	
	
//	/**
//	 * Check whether a shark is jumping.
//	 * @return	True when it is jumping.
//	 * 			| getYVelocity() > 0
//	 */
//	private boolean isJumping() {
//		return (getYVelocity() > 0);
//	}
	
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
	 *			| minDT + Math.random()*(maxDT-minDT)
	 */
	private double randomAccY() {
		return (minAccy + Math.random()*(maxAccy-minAccy));
	}
	
	/**
	 * Return a random signal 0 or 1.
	 * @return	A random signal 0 or 1.
	 * 			| minAccy + Math.random()*(maxAccy-minAccy)
	 */
	private int randomSignal() {
		return (int)(Math.random() * 2);
	}
	
	/**
	 * Update the position and the velocity of the shark after jumping for a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the shark's new position and velocity are calculated.
	 * @effect	The newly computed position is checked with checkWorldV and checkCollV and if necessary corrected with fixWorldV
	 * 			and fixCollV.
	 * 			| try {
	 *			| 	CheckCollV(h);
	 *			| } catch (CollisionException exc) {
	 *			| 	if (exc.getCollided())
	 *			| 		h = collV(exc,h);
	 *			| 	else
	 *			| 		h = fixCollV(exc,h);
	 *			| }
	 *			| try {
	 *			| 	CheckWorldV(h);
	 *			| } catch (CollisionException exc) {
	 *			| 	h = fixWorldV(exc,h);
	 *	 		| }
	 * @effect	The position is updated to the computed one with setYPosition.
	 * 			| setYPosition(h);
	 * @effect	The velocity is updated to the computed one with setYVelocity.
	 * 			| setYVelocity(v);
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
	 * Update the position and the velocity of the shark after swimming for a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the shark's new position and velocity are calculated.
	 * @effect	The newly computed position is checked with checkWorldV and checkCollV and if necessary corrected with fixWorldV
	 * 			and fixCollV.
	 * 			| try {
	 *			| 	CheckCollV(h);
	 *			| } catch (CollisionException exc) {
	 *			| 	if (exc.getCollided())
	 *			| 		h = collV(exc,h);
	 *			| 	else
	 *			| 		h = fixCollV(exc,h);
	 *			| }
	 *			| try {
	 *			| 	CheckWorldV(h);
	 *			| } catch (CollisionException exc) {
	 *			| 	h = fixWorldV(exc,h);
	 *	 		| }
	 * @effect	The position is updated to the computed one with setYPosition.
	 * 			| h = getYPosition() + 100*(0.5*getYAcc()*time*time + getYVelocity()*time)
	 * 			| setYPosition(h);
	 * @effect	The velocity is updated to the computed one with setYVelocity.
	 * 			| setYVelocity(getYVelocity()+getYAcc()*time);
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
	 * Update the position and the velocity of the shark after falling for a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the shark's new position and velocity are calculated.
	 * @effect	The newly computed position is checked with checkWorldV and checkCollV and if necessary corrected with fixWorldV
	 * 			and fixCollV.
	 * 			| try {
	 *			| 	CheckCollV(h);
	 *			| } catch (CollisionException exc) {
	 *			| 	if (exc.getCollided())
	 *			| 		h = collV(exc,h);
	 *			| 	else
	 *			| 		h = fixCollV(exc,h);
	 *			| }
	 *			| try {
	 *			| 	CheckWorldV(h);
	 *			| } catch (CollisionException exc) {
	 *			| 	h = fixWorldV(exc,h);
	 *	 		| }
	 * @effect	The position is updated to the computed one with setYPosition.
	 * 			| h = getYPosition() + 100*(getYVelocity()*time + accy*0.5*time*time);
	 * 			| setYPosition(h);
	 * @effect	The velocity is updated to the computed one with setYVelocity.
	 * 			| setYVelocity(getYVelocity()+accy*time);
	 */
	private void Fall(double time) {
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
	
	/**
	 * A method for initiating the shark to move.
	 * @effect 	Orientation, (maximimum) velocity and acceleration are updated if the random signal is 1.
	 * 			| if (randomSignal() == 1)
	 * 			| 	if (getOrientation() == Orientation.LEFT)
	 * 			|		setXVelocity(0);
	 * 			|	setOrientation(Orientation.RIGHT);
	 * 			|	setXAcc(accx);
	 * 			|	setMaxVel(maxSpeed);
	 * @effect 	Orientation, (maximimum) velocity and acceleration are updated if the random signal is 0.
	 * 			| if (randomSignal() == 0)
	 * 			| 	if (getOrientation() == Orientation.RIGHT)
	 * 			|		setXVelocity(0);
	 * 			|	setOrientation(Orientation.LEFT);
	 * 			|	setXAcc(-accx);
	 * 			|	setMaxVel(-maxSpeed);
	 */
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
	
	@Override
	public Void startMove(Orientation orientation) {
		if ((getOrientation() != orientation) || ((getOrientation() == orientation) && (getXVelocity() == 0))) {
			if (orientation == Orientation.RIGHT)
				setXAcc(accx);
			else
				setXAcc(-accx);
			setXVelocity(0);
			setOrientation(orientation);
		}
		return null;
	}
	
	/**
	 * A method to stop movement of the shark
	 * @effect	The horizontal acceleration is set to zero with setXAcc.
	 * 			| setXAcc(0) 
	 */
	private void endMove() {
		setXAcc(0);
		setXVelocity(0);
	}

	@Override
	public Void endMove(Orientation orientation) {
		if (getOrientation() == orientation) {
			setXAcc(0);
			setXVelocity(0);
		}
		return null;
	}

	@Override
	public Void startDuck() {
		return null;
	}

	@Override
	public Void endDuck() {
		return null;
	}
	
	/**
	 * A method to initiate the shark to jump
	 * @effect	The vertical velocity and acceleration are updated with setYVelocity and setYAcc.
	 * 			| setYVelocity(startVelY)
	 * 			| setYAcc(accy)
	 * @post	The number of jumping periods to the next one is set to 5.
	 * 			| lastJump = 5
	 */
	@Override
	public Void startJump() {
		lastJump = 5;
		setYVelocity(startVelY);
		setYAcc(accy);
		return null;
	}
	
	/**
	 * A method to end all horizontal movement of the shark
	 * @effect	The vertical acceleration is set to zero with setYAcc.
	 * 			| setYAcc(0)
	 * @effect	If the shark is moving upward, its vertical velocity is set to zero with setYVelocity.
	 * 			| if (getYVelocity() > 0)
	 * 			|	setYVelocity(0)
	 */
	@Override
	public Void endJump() {
		setYAcc(0);
		if (getYVelocity() > 0)
			setYVelocity(0);
		return null;
	}
	
	/**
	 * A method to initiate the shark to swin
	 * @effect	The vertical velocity and acceleration are updated with setYVelocity and setYAcc.
	 * 			| setYAcc(randomAccY())
	 * 			| setYVelocity(0)
	 */
	private void startSwim() {
		setYAcc(randomAccY());
		setYVelocity(0);
	}
	
	/**
	 * A method to stop all swimming action of the shark.
	 * @effect 	The vertical velocity and acceleration are set to zero with setYVelocity and setYAcc.
	 * 			| setYAcc(0)
	 * 			| setYVelocity(0)
	 */
	private void endSwim() {
		setYAcc(0);
		setYVelocity(0);
	}
	
	/**
	 * Update the position and the velocity of the shark after a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the shark's new position and velocity are calculated.
	 * @effect	The newly computed position is checked with checkWorldH and checkCollH and if necessary corrected with fixWorldH
	 * 			and fixCollH.
	 * 			| try {
	 *			| 	CheckCollH(s);
	 *			| } catch (CollisionException exc) {
	 *			| 	if (exc.getCollided())
	 *			| 		s = collH(exc,s);
	 *			| 	else
	 *			| 		s = fixCollH(exc,s);
	 *			| }
	 *			| try {
	 *			| 	CheckWorldH(s);
	 *			| } catch (CollisionException exc) {
	 *			| 	s = fixWorldH(exc,s);
	 *	 		| }
	 * @effect	The position is updated to the computed one with setXPosition.
	 * 			| setXPosition(s);
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
	 * A method for reducing hitpoints when the shark touches air.
	 * @param 	time
	 * 			The time during which the shark touches water.
	 * @effect	The number of hitpoints is reduced with reduceHitPoints.
	 * 			| timerAir += time
	 * 			| int times = (int) Math.floor(timerAir*5)
	 * 			| reduceHitPoints(times*6)
	 * @post	The timer for air is updated.
	 * 			| timerAir += time
	 * 			| int times = (int) Math.floor(timerAir*5)
	 * 			| timerAir -= times*0.2;
	 */
	private void touchAir(double time) {
		timerAir += time;
		int times = (int) Math.floor(timerAir*5);
		timerAir -= times*0.2;
		reduceHitPoints(times*6);
	}
	
	/**
	 * A method for reducing hitpoints when the shark touches magma.
	 * @param time
	 * 			The time during which the shark touches magma.
	 * @effect	The number of hitpoints is reduced with reduceHitPoints.
	 * 			| if (timerMagma == 0)
	 * 			|	reduceHitPoints(50);
	 * 			| else
	 * 			| 	timerMagma += time
	 * 			| 	int times = (int) Math.floor(timerMagma*5)
	 * 			|	timerMagma -= times*0.2;
	 * 			| 	reduceHitPoints(times*50)
	 * @post	The timer for magma is updated.
	 * 			| timerMagma += time
	 * 			| int times = (int) Math.floor(timerMagma*5)
	 * 			| timerMagma -= times*0.2;
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
	 * @effect	All movement is ended with endMovement.
	 * 			| endMovement()
	 * @effect	After setting a new time slot, new movement is started with startMovement.
	 * 			| timeslot = randomTime()
	 * 			| startMovement()
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
			UpdatePositions(time);
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
	
	/**
	 * A method to initiate all movement of the shark
	 * @effect	Horizontal movement is started with startMove.
	 * 			| startMove()
	 * @post	The minimum number of jumps to the next one is updated.
	 * 			| if (lastJump > 0)
	 * 			|	lastJump -= 1
	 * @effect	If the shark gets the right signal and is able to jump, a jumping movement is started with startJump.
	 * 			| if ((SharkcanJump()) && (lastJump == 0) && (signal == 1) )
	 * 			|	startJump()
	 * @effect	If the shark is not jumping and he is submerged, a swimming movement is started with startSwim.
	 * 			| if (isSubmerged())
	 * 			|	startSwim()
	 */
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
	
	/**
	 * A method to end all movement of this shark.
	 * @effect	All movement is ended with endMove, endJump and endSwim.
	 * 			| endMove() 
	 * 			| endJump()
	 * 			| endSwim()
	 */
	private void endMovement() {
		endMove();
		endJump();
		endSwim();
	}
	
	/**
	 * Method to update the new position after a certain time duration after checking whether the timeslot is reached.
	 * @param 	time
	 * 			The time duration in which the object moves 1 pixel.
	 * @effect	The horizontal position and velocity are updated with Move.
	 * 			| Move(time)
	 * @effect 	Its vertical position and velocity are updated with Jump, Fall or Swim, according to their state.
	 * 			| if ( (lastJump == 5) && ( ( (isJumping()) && (! isSubmerged()) ) || ( (isSubmerged()) && (getYVelocity() <= (-(getYAcc()/2)*time)) ) ) )
	 * 			|	Jump(time) 
	 * 			| else if (! isSubmerged())
	 * 			|	Fall(time)
	 * 			| else
	 * 			|	Swim(time)
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
	 * Update the new position and velocity after the given time duration.
	 * @param 	time
	 * 			The time duration between this position and the next one.
	 * @effect	The time it takes to move 1 pixel is computed with getDT.
	 * 			| getDT(time,getXVelocity(),getYVelocity(),getXAcc(),getYAcc())
	 * @effect	The new position and velocity are computed with the method advance.
	 * 			| advance(dt)
	 * @throws	ModelException
	 * 			The given time is not valid (between 0 and 0.2)
	 * 			| ! isValidTime(time)
	 */
	@Override
	public void advanceTime(double time) throws ModelException {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		if (getProgram() != null)
			getProgram().execute(getProgram().getGlobalVariables(), time);
		else
		{
			if (reachesTimeSlot(time))
			{
				double t1 = timeslot - timer;
				double t2 = (timer+time) - timeslot;
				advanceWithDT(t1);
				endOfTimeSlot();
				advanceWithDT(t2);
			}
			else
				advanceWithDT(time);
		}
	}
	
	public void advanceWithDT(double time) {
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

	@Override
	public boolean isDucking() {
		return false;
	}
	
}
