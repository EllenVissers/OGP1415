package jumpingalien.model;

import jumpingalien.model.School;
import jumpingalien.program.program.Program;
import java.util.ArrayList;
import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

/**
 * A class of Slimes involving a horizontal and vertical position, velocity and acceleration, a list of images, an orientation,
 * a number of hitpoints, a world, whether or not the slime is terminated and the time it has been terminated.
 * @invar A slime will never move faster than a given maximum velocity
 * 			| this.getXVelocity() <= maxSpeed
 * @invar The slime will always stay within the boundaries of the game world
 * 			| this.getXPosition() >= 0 && this.getXPosition() <= this.getWorld().getWorldWidth()
 * 			| this.getYPosition() >= 0 && this.getYPosition() <= this.getWorld().getWorldHeight()
 * @invar The orientation of a slime will never be NONE
 * 			| this.Orientation != Orientation.NONE
 * 
 * @author Ellen Vissers, Nina Versin
 * @version 1.0
 */
public class Slime extends GameObject {

	/**
	 * Initialize a new Slime with given position and sprites
	 * @param 	posx
	 * 			The horizontal position of the Slime corresponding to the position of the leftmost pixel of it's image.
	 * @param 	posy
	 * 			The vertical position of the Slime corresponding to the position of the lowest pixel of it's image.
	 * @param 	sprites
	 * 			The list of images for the different states of a Slime.
	 * @throws 	ModelException
	 * 			Throws an exception if sprites is an emty array.
	 * 			| sprites == null
	 * @effect
	 * 			| school.addSlime(this)
	 * @effect
	 * 			| setMaxVel(-maxSpeed)
	 */
	public Slime(double pos_x, double pos_y, Sprite[] sprites, School school) throws ModelException {
		this(pos_x,pos_y,sprites,school,null);
	}
	
	/**
	 * Initialize a new Slime with given position and sprites
	 * @param 	posx
	 * 			The horizontal position of the Slime corresponding to the position of the leftmost pixel of it's image.
	 * @param 	posy
	 * 			The vertical position of the Slime corresponding to the position of the lowest pixel of it's image.
	 * @param 	sprites
	 * 			The list of images for the different states of a Slime.
	 * @param	program
	 * 			The controlling program of this slime.
	 * @throws 	ModelException
	 * 			Throws an exception if sprites is an emty array.
	 * 			| sprites == null
	 * @effect
	 * 			| school.addSlime(this)
	 * @effect
	 * 			| setMaxVel(-maxSpeed)
	 */
	public Slime(double pos_x, double pos_y, Sprite[] sprites, School school, Program program) throws ModelException {
		super(pos_x,pos_y,0,0,-accx,0,Orientation.LEFT,sprites,startHitPoints, null,false,0,program);
		setSchool(school);
		school.addSlime(this);
		setMaxVel(-maxSpeed);
		this.timer = 0;
		this.timerMagma = 0;
		this.timerWater = 0;
		this.timeslot = randomTime();
	}
	
	/**
	 * Variable registering the school of this slime.
	 */
	private School school;
	/**
	 * Variable registering the maximum velocity of this slime.
	 */
	private double maxVel;
	/**
	 * Variable registering the vertical acceleration of this slime.
	 */
	private static final double accy = -10;
	/**
	 * Variable registering the horizontal acceleration of this slime.
	 */
	protected static final double accx = 0.7;
	/**
	 * Variable registering the maximum speed of this slime.
	 */
	protected static final double maxSpeed = 2.5;
	/**
	 * Variable registering the lower boundary of the time for calculating a random timeslot.
	 */
	private static double minDT = 2;
	/**
	 * Variable registering the upper boundary of the time for calculating a random timeslot.
	 */
	private static double maxDT = 6;
	/**
	 * Variable registering the time a slime is doing the same movement.
	 */
	protected double timer;
	/**
	 * Variable registering the length of the current movement.
	 */
	protected double timeslot;
	/**
	 * The default value of the amount of hitpoints of a newly created Slime.
	 */
	private static int startHitPoints = 100;
	/**
	 * Variable registering the time the slime has been in water.
	 */
	private double timerWater;
	/**
	 * Variable registering the time the slime has been in magma.
	 */
	private double timerMagma;
	
	private double getTimer() {
		return this.timer;
	}
	
	public void setTimer(double t) {
		this.timer = t;
	}
	
	private double getTimerWater() {
		return this.timerWater;
	}
	
	private void setTimerWater(double t) {
		this.timerWater = t;
	}
	
	private double getTimerMagma() {
		return this.timerMagma;
	}
	
	private void setTimerMagma(double t) {
		this.timerMagma = t;
	}
	
	private double getTimeSlot() {
		return this.timeslot;
	}
	
	public void resetTimeSlot() {
		this.timeslot = randomTime();
	}
	
	/**
	 * Method returning the maximum speed of this slime.
	 * @return
	 * 			| this.maxVel
	 */
	@Basic
	private double getMaxVel() {
		return this.maxVel;
	}
	
	/**
	 * A method that sets the maximum speed of a slime to the given one.
	 * @param vel
	 * 			The speed we want to set the maximum velocity to.
	 * @post
	 * 		| new.maxVel = vel
	 */
	protected void setMaxVel(double vel) {
		this.maxVel = vel;
	}
	
	/**
	 * Method returning the school of this slime.
	 * @return
	 * 			| this.school
	 */
	@Basic
	public School getSchool() {
		return this.school;
	}
	
	/**
	 * A method that sets the school of a slime to the given one.
	 * @param school
	 * 			The school we want to set the slime's school to.
	 * @post
	 * 		| new.school = school
	 */
	protected void setSchool(School school) {
		this.school = school;
	}
	
	/**
	 * A method return a random time value between an upper and lower boundary.
	 * @return
	 * 			| minDT + Math.random()*(maxDT-minDT)
	 */
	protected static double randomTime() {
		return (minDT + Math.random()*(maxDT-minDT));
	}
	
	/**
	 * A method for checking if the slime reaches its maximum velocity.
	 * @param time
	 * 			The time period after which we want to check the speed
	 * @return
	 * 			| ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed
	 */
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed);
	}
	
	/**
	 * A method for reducing hitpoints when the slime touches water.
	 * @param time
	 * 			The time during which the slime touches water.
	 * @effect
	 * 			| timerWater += time
	 * 			| int times = (int) Math.floor(timerWater*5)
	 * 			| reduceHitPoints(times*2)
	 * @post
	 * 			| timerWater += time
	 * 			| int times = (int) Math.floor(timerWater*5)
	 */
	private void touchWater(double time) {
		setTimerWater(getTimerWater() + time);
		int times = (int) Math.floor(getTimerWater()*5);
		setTimerWater(getTimerWater() - times*0.2);
		reduceHitPoints(times*2);
	}
	
	/**
	 * A method for reducing hitpoints when the slime touches magma.
	 * @param time
	 * 			The time during which the slime touches magma.
	 * @effect
	 * 			| if (timerMagma == 0)
	 * 			|	reduceHitPoints(50);
	 * 			| else
	 * 			| 	timerMagma += time
	 * 			| 	int times = (int) Math.floor(timerMagma*5)
	 * 			|	timerMagma -= times*0.2;
	 * 			| 	reduceHitPoints(times*50)
	 * @post
	 * 			| timerWater += time
	 * 			| int times = (int) Math.floor(timerWater*5)
	 * 			| timerMagma -= times*0.2;
	 */
	private void touchMagma(double time) {
		if (getTimerMagma() == 0)
			reduceHitPoints(50);
		else
		{
			setTimerMagma(getTimerMagma()+time);
			int times = (int) Math.floor(getTimerMagma()*5);
			setTimerMagma(getTimerMagma()-times*0.2);
			reduceHitPoints(times*50);
		}
	}
	
	/**
	 * A method for deducting hitpoints of this slime and the one's in its school.
	 * @param points
	 * 			The amount of hitpoints we want to deduct.
	 * @effect
	 * 			| if ((getHitPoints() - points) <= 0)
	 * 			|	setHitPoints(0)
	 * 			|	terminate()
	 * 			| setHitPoints(getHitPoints()-points)
	 * @effect
	 * 			| for (Slime slime : slimes)
	 * 			| 	if ((slime.getHitPoints() - 1) <= 0)
	 * 			|		slime.setHitPoints(0);
	 * 			|		slime.terminate();
	 * 			|	else
	 * 			|		slime.setHitPoints(slime.getHitPoints() - 1);
	 */
	@Override
	protected void reduceHitPoints(int points) {
		if ((getHitPoints() - points) <= 0)
		{
			setHitPoints(0);
			terminate();
		}
		else
			setHitPoints(getHitPoints()-points);
		ArrayList<Slime> slimes = getSchool().getSlimes();
		slimes.remove(this);
		for (Slime slime : slimes)
		{
			if ((slime.getHitPoints() - 1) <= 0)
			{
				slime.setHitPoints(0);
				slime.terminate();
			}
			else
				slime.setHitPoints(slime.getHitPoints() - 1);
		}
	}
	
	/**
	 * Update the position and velocity of the slime after falling for a given time period.
	 * @param time
	 * 			The time period during which the slime falls.
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
	 * @effect	The position is updated to the computed one with setYPosition, the vertical velocity is updated with setYVelocity.
	 * 			| setYPosition(h)
	 * 			| setYVelocity(getYVelocity() + accy*time);
	 */
	private void Fall(double time) {
		double h = getYPosition() + 100*(getYVelocity()*time + 0.5*accy*time*time);
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
		setYVelocity(getYVelocity() + accy*time);
	}
	
	private boolean reachesTimeSlot(double time) {
		return ((getTimer()+time)>=getTimeSlot());
	}
	
	/**
	 * Update the position and the velocity of the slime after a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the slime's new position and velocity are calculated.
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
		if (reachesMaxSpeed(time)) {
			double t1 = (this.getMaxVel() - this.getXVelocity())/(getXAcc());
			double t2 = time - t1;
			s = (getXPosition() + 100*(t1*getXVelocity() + 0.5*t1*t1*getXAcc() + t2*getMaxVel()));
			setXVelocity(getMaxVel());
		}
		else
		{
			s = (getXPosition() + 100*(time*getXVelocity() + 0.5*time*time*getXAcc()));
			setXVelocity(getXVelocity()+time*getXAcc());
		}
		setTimer(getTimer()+time);
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
	 * A method for advancing time for a slime while updating movements, positions,...
	 * @param 	t
	 * 			The time we want to updated this slime
	 * @effect 	If the slime died, it is removed from its school and world
	 * 			| if (isTerminated())
	 * 			|	setTerminatedTime(getTerminatedTime() + t);
	 * 			|	if (Util.fuzzyGreaterThanOrEqualTo(getTerminatedTime(), 0.6))
	 * 			|		getSchool().removeSlime(this)
	 * 			|		setWorld(null)
	 * @effect	If the slime is touching water, its hitpoints are updated with touchWater.
	 * 			| if (medium.contains(2))
	 * 			| then touchWater(time)
	 * @effect	If the slime is touching magma, its hitpoints are updated with touchMagma.
	 * 			| if (medium.contains(3))
	 * 			| then touchMagma(time)
	 * @effect  Its horizontal position is updated with Move.
	 * 			| Move(t)
	 * @effect	If the slime is not on the ground or another game object, its vertical position is updated with Fall.
	 * 			| if (onGround() || onGameObject())
	 * 			|	setYVelocity(0)
	 * 			| else
	 * 			|	Fall(t)
	 */
	protected void advance(double t) {
		if (isTerminated())
		{
			setTerminatedTime(getTerminatedTime() + t);
			if (Util.fuzzyGreaterThanOrEqualTo(getTerminatedTime(), 0.6))
				{
				getSchool().removeSlime(this);
				setWorld(null);
				}
		}
		else
		{
			Move(t);
			setTerminatedTime(0);
			if (onGround() || onGameObject())
				setYVelocity(0);
			else
				Fall(t);
			ArrayList<Integer> medium = CheckMedium();
			if (medium.contains(2))
				touchWater(t);
			else
				setTimerWater(0);
			if (medium.contains(3))
				touchMagma(t);
			else
				setTimerMagma(0);	
		}
	}
	
	/**
	 * Update the slime's new position and velocity after the given time duration.
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
	public void advanceTime(double time) {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		if (getProgram() != null)
		{
			getProgram().setGameObject(this);
			getProgram().execute(getProgram().getGlobalVariables(),time);
			advanceWithDT(time);
		}
		else
		{
			if (reachesTimeSlot(time)) {
				double t1 = getTimeSlot() - getTimer();
				double t2 = time - t1;
				advanceWithDT(t1);
				endMove(getOrientation());
				resetTimeSlot();
				startMove();
				advanceWithDT(t2);
			}
			else
				advanceWithDT(time);
		}
	}

	@Override
	public Void startMove(Orientation orientation) {
		if (getOrientation() != orientation) {
			if (orientation == Orientation.RIGHT)
			{
				setXAcc(accx);
				setMaxVel(maxSpeed);
			}
			else
			{
				setXAcc(-accx);
				setMaxVel(-maxSpeed);
			}
			setXVelocity(0);
			setOrientation(orientation);
		}
		return null;
	}
	
	public void startMove() {
		int val = (int) Math.round(Math.random()*10);
		if (val%2 == 0)
			startMove(Orientation.RIGHT);
		else
			startMove(Orientation.LEFT);
	}

	@Override
	public Void endMove(Orientation orientation) {
		if (getOrientation() == orientation) {
			setXAcc(0);
			setXVelocity(0);
			setTimer(0);
		}
		return null;
	}

	@Override
	public Void startJump() {
		return null;
	}

	@Override
	public Void endJump() {
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

	@Override
	public boolean isDucking() {
		return false;
	}
}
