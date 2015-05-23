package jumpingalien.model;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import jumpingalien.model.Orientation;
import jumpingalien.program.program.Program;


/**
 * A class of Plants involving a horizontal and vertical position, velocity and acceleration, a list of images, an orientation, 
 * a number of hitpoints, a world and a terminated state.
 * @invar 	A Plant's bottom-left position stays within the bounds of the world.
 * 			| this.isValidXPosition(this.getXPosition()) && this.isValidYPosition(this.getYPosition())
 * @invar 	A Plant's vertical velocity will always be zero.
 * 			| getYVelocity() == 0
 * @invar 	A Plant's horizontal and vertical acceleration will always be zero.
 * 			| (getYAcc() == 0) && (getXAcc() == 0)
 * @invar 	A Plant's orientation will never be NONE.
 * 			| getOrientation() != Orientation.NONE
 * @author Ellen Vissers, Nina Versin
 * @version 1.0
 */
public class Plant extends GameObject {
	
	/**
	 * Initialize a new Plant with given position and sprites.
	 * @param 	posx
	 * 			The horizontal position of the Plant corresponding to the position of the leftmost pixel of it's image.
	 * @param 	posy
	 * 			The vertical position of the Plant corresponding to the position of the lowest pixel of it's image.
	 * @param 	sprites
	 * 			The list of images for the different states of a Plant.
	 * @throws 	ModelException
	 * 			Throws an exception if sprites is an emty array.
	 * 			| sprites == null
	 */
	public Plant(double posx, double posy, Sprite[] sprites) throws ModelException {
		super(posx,posy,-speed,0,0,0,Orientation.LEFT,sprites,1,null,false,0,null);
		this.timer = 0;
	}
	
	/**
	 * Initialize a new Plant with given position, sprites and program.
	 * 			The horizontal position of the Plant corresponding to the position of the leftmost pixel of it's image.
	 * @param 	posy
	 * 			The vertical position of the Plant corresponding to the position of the lowest pixel of it's image.
	 * @param 	sprites
	 * 			The list of images for the different states of a Plant.
	 * @param	program
	 * 			The program that controls the movement of this Plant.
	 * @throws 	ModelException
	 * 			Throws an exception if sprites is an emty array.
	 * 			| sprites == null
	 */
	public Plant(double posx, double posy, Sprite[] sprites, Program program) throws ModelException {
		super(posx,posy,-speed,0,0,0,Orientation.LEFT,sprites,1,null,false,0,program);
	}
	
	/**
	 * Variable registering the speed at which a Plant moves.
	 */
	public final static double speed = 0.5;
	
	/**
	 * Variable registering the time duration for the movement in 1 direction.
	 */
	private final double timeSwitch = 0.5;

	/**
	 * Variable registering the time a Plant is moving in the same direction.
	 */
	private double timer;
	
	/**
	 * Method to set the vertical velocity to a certain value.
	 * @param 	vel
	 * 			The new vertical velocity.
	 */
	@Override
	protected void setYVelocity(double vel) {
	}
	
	/**
	 * Method to set the horizontal acceleration to a certain value.
	 * @param 	acc
	 * 			The new horizontal acceleration.
	 */
	@Override
	protected void setXAcc(double acc) {
	}

	/**
	 * Method to set the vertical acceleration to a certain value.
	 * @param 	acc
	 * 			The new vertical acceleration.
	 */
	@Override
	protected void setYAcc(double acc) {
	}
	
	/**
	 * A method to terminate a Plant.
	 * @post 	The plant is terminated.
	 * 			| new.isTerminated()
	 * @effect	The terminated state is set with setTerminated.
	 * 			| setTerminated(true)
	 * @effect	The object is removed from the world with setWorld
	 * 			| setWorld(null)
	 */
	@Override
	protected void terminate() {
		setTerminated(true);
		setWorld(null);
		removeFromAll(this);
	}
	
	public double getTimer() {
		return this.timer;
	}
	
	public void setTimer(double t) {
		this.timer = t;
	}
	
	/**
	 * Method to check whether the time switch is reached within the next time duration.
	 * @param 	time
	 * 			The length of the next time duration.
	 * @return	True if the time switch is reached.
	 */
	private boolean reachesTimeSwitch(double time) {
		return ((getTimer() + time) >= timeSwitch);
	}
	
	/**
	 * Update the position of the Plant after a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the new position is calculated.
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
		if (! isTerminated()) {
			s = getXPosition() + 100*time*getXVelocity();
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
	}
	
	/**
	 * Method to update the new position after a certain time duration.
	 * @param 	t
	 * 			The time duration in which the object moves 1 pixel.
	 * @effect 	As long as the Plant is not terminated, its position is updated with Move.
	 * 			| Move(t)
	 */
	private void advance(double t) {
//		if ( (! isTerminated()) && (t != 0)) {
//			Move(t);
//		}
		Move(t);
	}
	
	/**
	 * Update the position of the Plant after a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which the new position is calculated.
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
			getProgram().execute(getProgram().getGlobalVariables(), time);
			advanceWithDT(time);
		}
		else
		{
			if (reachesTimeSwitch(time))
			{
				double t1 = (timeSwitch - getTimer());
				double t2 = ((time+getTimer()) - timeSwitch);
				advanceWithDT(t1);
				endMove(getOrientation());
				if (getOrientation() == Orientation.LEFT)
				{
					startMove(Orientation.RIGHT);
					setOrientation(Orientation.RIGHT);
				}
				else
				{
					startMove(Orientation.LEFT);
					setOrientation(Orientation.LEFT);
				}
				advanceWithDT(t2);
			}
			else
				advanceWithDT(time);
			setTimer(getTimer()+time);
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
	public Void startMove(Orientation orientation) {
		if (orientation == Orientation.RIGHT)
			setXVelocity(speed);
		else
			setXVelocity(-speed);
		setOrientation(orientation);
		return null;
	}

	@Override
	public Void endMove(Orientation orientation) {
		setXVelocity(0);
		setTimer(0);
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
