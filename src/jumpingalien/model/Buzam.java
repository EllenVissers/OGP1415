package jumpingalien.model;
import jumpingalien.program.program.Program;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

/**
 * A class of aliens called Buzam involving a horizontal and vertical position, velocity and acceleration,
 * a list of images, an orientation, a number of hitpoints, a world and a variable registering whether the object is terminated.
 * It also involves a number pointing to a certain sprite, a start velocity, a maximum velocity, variables registering whether the
 * left and right buttons are pressed, a variable registering whether the alien is immune, a variable registering whether the
 * alien is ducking and possibly a program controlling the object.
 * @invar 	Bazum's bottom-left position stays within the bounds of the world.
 * 			| this.isValidXPosition(this.getXPosition()) && this.isValidYPosition(this.getYPosition())
 * @invar 	Bazum's horizontal speed will never be greater than the maximum speed.
 * 			| this.getXVelocity() <= this.getMaxVel()
 * @author 	Ellen Vissers, Nina Versin
 * @version 1.0
 */
public class Buzam extends Mazub {
	
	/**
	 * Initialize a new Buzam with given position and sprites.
	 * @param 	pos_x
	 * 			The horizontal position of the bottom-left pixel of the alien.
	 * @param 	pos_y
	 * 			The vertical position of the bottom-left pixel of the alien.
	 * @param 	sprites
	 * 			The list of images for the different states of Buzam.
	 * @throws 	Throws a ModelException if sprites is an emty array.
	 * 			| sprites == null
	 */
	public Buzam(double pos_x, double pos_y, Sprite[] sprites)
			throws ModelException {
		super(pos_x, pos_y, sprites);
		setHitPoints(startHitPoints);
	}
	
	/**
	 * Initialize a new Buzam with given position, sprites and program.
	 * @param 	pos_x
	 * 			The horizontal position of the bottom-left pixel of the alien.
	 * @param 	pos_y
	 * 			The vertical position of the bottom-left pixel of the alien.
	 * @param 	sprites
	 * 			The list of images for the different states of Buzam.
	 * @param	program
	 * 			The program that controls the movement of this game object.
	 * @throws 	Throws a ModelException if sprites is an emty array.
	 * 			| sprites == null
	 */
	public Buzam(double pos_x, double pos_y, Sprite[] sprites, Program program)
			throws ModelException {
		super(pos_x, pos_y, sprites);
		setHitPoints(startHitPoints);
		setProgram(program);
	}
	
	/**
	 * Variable registering the number of hitpoints the game object has at its creation.
	 */
	private int startHitPoints = 500;
	
	/**
	 * Update Buzam's new position and velocity after the given time duration.
	 * @param 	time
	 * 			The time duration between this position and the next one.
	 * @effect	If the game object has a program, this program is executed.
	 * 			| if (getProgram() != null)
	 * 			|     getProgram().execute(getProgram().getGlobalVariables(), time);
	 * 			|     advanceWithDT(time);
	 * @effect	The new position and velocity are computed with the method advanceWithDT.
	 * 			| advanceWithDT(time)
	 * @throws	ModelException
	 * 			The given time is not valid (between 0 and 0.2)
	 * 			| ! isValidTime(time)
	 */
	@Override
	public void advanceTime(double time) {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		if (getProgram() != null)
			getProgram().execute(getProgram().getGlobalVariables(), time);
		advanceWithDT(time);
	}
	
}
