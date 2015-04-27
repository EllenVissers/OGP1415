package jumpingalien.model;
import java.util.ArrayList;
import java.util.Arrays;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import jumpingalien.model.CollisionException;

/* TEAM INFORMATION
* Ellen Vissers: 2e bachelor Burgerlijk ingenieur, CW-ELT
* Nina Versin: 2e bachelor Burgerlijk ingenieur, ELT-CW
* Repository: https://gitlab.com/EllenVissers/JumpingAlien_OGP1415
*/

/**
 * A class of aliens called Mazub involving a horizontal and vertical position, velocity and acceleration,
 * a list of images, an orientation, a number of hitpoints, a world and a variable registering whether the object is terminated.
 * It also involves a number pointing to a certain sprite, a start velocity, a maximum velocity, variables registering whether the
 * left and right buttons are pressed, a variable registering whether the alien is immune and a variable registering whether the
 * alien is ducking.
 * @invar 	Mazub's bottom-left position stays within the bounds of the world.
 * 			| this.isValidXPosition(this.getXPosition()) && this.isValidYPosition(this.getYPosition())
 * @invar 	Mazub's horizontal speed will never be greater than the maximum speed.
 * 			| this.getXVelocity() <= this.getMaxVel()
 * @author Ellen Vissers, Nina Versin
 * @version 2.0
 */
public class Mazub extends GameObject {

	// CONSTRUCTORS
	/**
	 * Initialize a new Mazub with given position, velocity, acceleration, orientation, sprites, number of hitpoints, world,
	 * start velocity, and maximum velocity.
	 * @param 	x
	 * 			The horizontal position of Mazub corresponding to the position of the outer left pixel of it's image.
	 * @param 	y
	 * 			The vertical position of Mazub corresponding to the position of the lowest pixel of it's image.
	 * @param 	vx
	 * 			The horizontal velocity of Mazub.
	 * @param 	vy
	 * 			The vertical velocity of Mazub.
	 * @param 	ax
	 * 			The horizontal acceleration of Mazub.
	 * @param 	ay
	 * 			The vertical acceleration of Mazub.
	 * @param 	or
	 * 			The orientation of Mazub.
	 * @param 	sprites
	 * 			The list of images for the different states of Mazub.
	 * @param 	hitpoints
	 * 			The number of hitpoints of Mazub.
	 * @param 	world
	 * 			The world in which Mazub is situated.
	 * @param 	nb
	 * 			The number of the sprite when Mazub is walking in a certain direction on the ground.
	 * @param 	startvel
	 * 			The start velocity of Mazub.
	 * @param 	maxvel
	 * 			The maximum velocity of Mazub.
	 * @param 	left
	 * 			A boolean registering whether the left button is pressed.
	 * @param 	right
	 * 			A boolean registering whether the right button is pressed.
	 * @param 	immune
	 * 			A boolean registering whether Mazub is immune.
	 * @param 	duck
	 * 			A boolean registering whether Mazub is ducking.
	 * @throws  Throws a ModelException if sprites is an emty array.
	 * 			| sprites == null
	 */	
	public Mazub(double x, double y, double vx, double vy, double ax, double ay, Orientation or, Sprite[] sprites, int hitpoints,
			World world, int nb, double startvel, double maxvel, boolean left, 
			boolean right, double immune,boolean duck) throws ModelException {
		super(x,y,vx,vy,ax,ay,or,sprites,hitpoints,world,false,0);
		setNb(nb);
		setStartVel(startvel);
		setMaxVel(maxvel);
		setLeftButton(left);
		setRightButton(right);
		setImmuneTime(immune);
		setDuck(duck);
	}
	
	/**
	 * Initialize a new Mazub with given position and sprites.
	 * @param 	pos_x
	 * 			The horizontal position of Mazub corresponding to the position of the outer left pixel of it's image.
	 * @param 	pos_y
	 * 			The vertical position of Mazub corresponding to the position of the lowest pixel of it's image.
	 * @param 	sprites
	 * 			The list of images for the different states of Mazub.
	 * @throws  Throws a ModelException if sprites is an emty array.
	 * 			| sprites == null
	 */	
	public Mazub(double pos_x, double pos_y, Sprite[] sprites) throws ModelException {
		this(pos_x,pos_y,0,0,0,0,Orientation.NONE,sprites,startHitPoints,null,0,startVelX,maxSpeed,false,false,0,false);
	}
	
	//ATTRIBUTES OF MAZUB
	/**
	 * Variable registering whether the left button key is pressed.
	 */
	private boolean leftButton;
	/**
	 * Variable registering whether the right button key is pressed.
	 */
	private boolean rightButton;
	/**
	 * Variable registering the start velocity of Mazub.
	 */
	private double start_vel;
	/**
	 * Variable registering the maximum speed of Mazub.
	 */
	private double max_vel;
	/**
	 * Variable registering the number the sprite of Mazub when Mazub is walking (without ducking) to the left or to the right.
	 */
	private int nb;
	/**
	 * Variable registering whether the alien is ducking.
	 */
	private boolean duck;
	/**
	 * Variable registering the time the alien will be immune.
	 */
	private double immunetime;
	
	//VARIABLES
	/**
	 * Variable registering the horizontal acceleration that applies to all Mazub's. 
	 */
	private static double accx = 0.9;
	/**
	 * Variable registering the vertical acceleration that applies to all Mazub's.
	 */
	private static final double accy = -10.0;
	/**
	 * Variable registering the maximum speed that applies to all Mazub's (when they are not ducking).
	 */
	public static double maxSpeed = 3.0;
	/**
	 * Variable registering the maximum speed while ducking that applies to all Mazub's.
	 */
	public static double maxSpeedDuck = 1.0;
	/**
	 * Variable registering the vertical start velocity that applies to all Mazub's.
	 */
	public static double startVelY = 8.0;
	/**
	 * Variable registering the default value for the horizontal start velocity.
	 */
	public static double startVelX = 1.0;
	/**
	 * Variable registering the time that Mazub does not move after it's last movement (to the right or to the left).
	 */
	private double time1;
	/**
	 * Variable registering the time that Mazub is already moving in a certain direction. 
	 * When Mazub stops or changes direction, the timer is set to 0.
	 */
	private double time2;
	/**
	 * Variable registering the time after his last movement whereafter his image is set to the first one.
	 */
	private static double timelastmovement = 1.0;
	/**
	 * Variable registering the time whereafter the image of Mazub has to change when he is still moving.
	 */
	private static double timebetween = 0.075;
	/**
	 * The default value of the amount of hitpoints of a newly created Mazub.
	 */
	public static int startHitPoints = 100;
	/**
	 * Variable registering the maximum number of hitpoints of an alien.
	 */
	protected static final int maxHitPoints = 500;
	/**
	 * A variable registering the time the alien has been in water.
	 */
	private double timerWater = 0;
	/**
	 * A variable registering the time the alien has been in magma.
	 */
	private double timerMagma = 0;
	
	//GETTERS AND SETTERS

	/**
	 * Check whether the left button key is pressed.
	 * @return 	True when the key is pressed.
	 * 			| this.leftButton
	 */
	public boolean getLeftButton() {
		return this.leftButton;
	}
	
	/**
	 * Check whether the right button key is pressed.
	 * @return 	True when the key is pressed.
	 * 			| this.rightButton
	 */
	public boolean getRightButton() {
		return this.rightButton;
	}
	
	/**
	 * Set the indicator for the left button key to true of false.
	 * @param 	left
	 * 			The variable that indicates whether the left button key is pressed or not.
	 * @post	The attribute is updated to the given boolean.
	 * 			| new.getLeftButton() == left
	 */
	private void setLeftButton(boolean left) {
		this.leftButton = left;
	}
	
	/**
	 * Set the indicator for the right button key to true of false.
	 * @param 	right
	 * 			The variable that indicates whether the right button key is pressed or not.
	 * @post	The attribute is updated to the given boolean.
	 * 			| new.getRightButton() == right
	 */
	private void setRightButton(boolean right) {
		this.rightButton = right;
	}

	
	/**
	 * Returns the value of the begin velocity of Mazub.
	 * @return 	Mazub's begin velocity.
	 * 			| this.start_vel
	 */
	@Basic
	public double getStartVel() {
		return this.start_vel;
	}
	
	/**
	 * Set the begin velocity of Mazub to a given velocity.
	 * @param 	vel
	 * 			The new begin velocity of Mazub.
	 * @pre 	The start velocity is a valid start velocity.
	 * 			| isValidStartVelocity(vel)
	 * @post 	Mazub's start velocity is updated to the given velocity.
	 * 			| new.getStartVel() = vel
	 */
	private void setStartVel(double vel) {
		assert isValidStartVelocity(vel);
		this.start_vel = vel;
	}
	
	/**
	 * Return the value of the maximum velocity of Mazub.
	 * @return 	Mazub's maximum velocity.
	 * 			| this.max_vel
	 */
	@Basic
	public double getMaxVel() {
		return this.max_vel;
	}
	
	/**
	 * Set the maximun velocity of Mazub to a given velocity.
	 * @param 	vel
	 * 			The new maximum velocity of Mazub.
	 * @pre 	The given velocity is a valid maximum velocity.
	 * 			| isValidMaxVelocity(vel)
	 * @post 	Mazub's maximum velocity is updated to the given velocity.
	 * 			| new.max_vel = vel
	 */
	private void setMaxVel(double vel) {
		assert isValidMaxVelocity(vel);
		this.max_vel = vel;
	}
	
	/**
	 * Get the number in the sublist of sprites for when Mazub keeps walking in a certain direction. 
	 * @return 	The number of the sprite in the sublist.
	 * 			| this.nb
	 */
	@Basic
	public int getNb() {
		return this.nb;
	}
	
	/**
	 * Set the number of the sprite in the sublist of sprites for when Mazub keeps walking in a certain 
	 * direction to the given number.
	 * @param 	i
	 * 			The new position in the sublist of sprites
	 * @post  	The number is updated to the given number
	 * 			| new.nb = i
	 * @pre		The new number is a valid number (lies within the ranges of the sublist)
	 * 			| isValidNb(i)
	 */
	private void setNb(int i) {
		if (! isValidNb(i))
			i = 0;
		this.nb = i;
	}
	
	/**
	 * Set the ducking state of the alien to the given one.
	 * @param duck
	 * 			The ducking state.
	 * @post    The ducking state is updated.
	 * 			| new.isDucking() == duck
	 */
	private void setDuck(boolean duck) {
		this.duck = duck;
	}
	
	/**
	 * Returns the time Mazub is immune after losing some hitpoints.
	 * @return 	The time of immunity
	 * 			| this.immunetime
	 */
	public double getImmuneTime() {
		return this.immunetime;
	}
	
	/**
	 * A method that sets the immune time to the given one.
	 * @param 	i
	 * 			The time we want to set the immune time to.
	 * @pre		The immune time is positive or zero.
	 * 			| i >= 0
	 * @post	The immune time is updated.
	 * 			| new.getImmuneTime() == i
	 */
	protected void setImmuneTime(double i) {
		assert (Util.fuzzyGreaterThanOrEqualTo(i,0));
		this.immunetime = i;
	}
	
	//CHECKERS
	/**
	 * Check whether Mazub is moving to the left.
	 * @return 	True if Mazub is moving to the left.
	 * 			| getXVelocity() < 0
	 */
	public boolean isMovingLeft() {
		return (getXVelocity() < 0);
	}
	
	/**
	 * Check whether Mazub is moving to the right.
	 * @return 	True when Mazub is moving to the right.
	 * 			| getXVelocity() > 0
	 */
	public boolean isMovingRight() {
		return (getXVelocity() > 0);
	}
	
	/**
	 * Check whether Mazub is moving.
	 * @return 	True when Mazub is moving.
	 * 			| getLeftButton() || getRightButton()
	 */
	private boolean isMoving() {
		return (getLeftButton() || getRightButton());
	}
	
	/**
	 * Check whether Mazub is jumping.
	 * @return 	True when Mazub is moving upwards.
	 * 			| this.vel_y > 0
	 */
	private boolean isJumping() {
		return (this.getYVelocity() > 0);
	}
	
	/**
	 * Check whether Mazub is falling.
	 * @return 	True when Mazub is falling (i.e. not standing on the ground or another game object).
	 * 			| (! onGround()) || (! onGameObject())
	 */
	public boolean isFalling() {
		return ((! onGround()) && (! onGameObject()));
	}
	
	/**
	 * Check whether Mazub is ducking.
	 * @return 	True when Mazub is ducking.
	 * 			| this.duck
	 */
	public boolean isDucking() {
		return this.duck;
	}
	
	/**
	 * Check whether Mazub can stand up.
	 * @return	True when Mazub can stand up.
	 * 				| for (int[] tile : tiles)
	 * 				| 	if (getWorld().getFeatureAt(tile[0],tile[1]) == 1)
	 *				|		return false;
	 *				| return true;
	 */
	private boolean canStandUp() {
		int dx = getSprites()[0].getWidth();
		int dy = getSprites()[0].getHeight();
		int[][] tiles = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
				(int) Math.round(getXPosition()+dx-2),(int) Math.round(getYPosition()+dy-2));
		for (int[] tile: tiles)
			if (getWorld().getFeatureAt(tile[0],tile[1]) == 1)
			{
				if (getOrientation() == Orientation.RIGHT)
					setMaxVel(maxSpeedDuck);
				else
					setMaxVel(-maxSpeedDuck);
				return false;
			}
		if (getOrientation() == Orientation.RIGHT)
			setMaxVel(maxSpeed);
		else
			setMaxVel(-maxSpeed);
		return true;
	}
	
	/**
	 * Check whether the given direction is valid.
	 * @param 	direction
	 * 			The direction that has to be checked.
	 * @return 	True when the given direction is valid.
	 * 			| (orientation == Orientation.LEFT) || (orientation == Orientation.RIGHT)
	 */
	private boolean isValidDirection(Orientation orientation) {
		return ((orientation == Orientation.LEFT) || (orientation == Orientation.RIGHT));
	}
	
	/**
	 * Check whether Mazub reaches his maximum speed within the given time interval.
	 * @param 	time
	 * 			The time interval in which it is checked if Mazub reaches its maximum speed.
	 * @return 	True when Mazub reaches his maximum speed.
	 * 			| (time*getAccX() + Math.abs(this.getXVelocity())) >= this.getMaxVel()
	 */
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(time*getXAcc()) + Math.abs(this.getXVelocity())) >= Math.abs(this.getMaxVel()));
	}
	
	/**
	 * Check whether the given value is even.
	 * @param 	value
	 * 			The value that has to be checked.
	 * @return  True when the value is even.
	 * 			| (value%2 == 0)
	 */
	private boolean isEven(int value) {
		return (value%2 == 0);
	}
	
	/**
	 * Check whether the given velocity is a valid begin velocity for Mazub.
	 * @param 	vel
	 * 			The velocity that has to be checked.
	 * @return 	True when the given start velocity is valid.
	 * 			| vel >= 1.0
	 */
	private boolean isValidStartVelocity(double vel) {
		return Util.fuzzyGreaterThanOrEqualTo(Math.abs(vel),1.0);
	}
	
	/**
	 * Check whether the given velocity is a valid maximum velocity for Mazub.
	 * @param 	vel
	 * 			The velocity that has to be checked.
	 * @return 	True when the given velocity is valid.
	 * 			| vel > this.getStartVel()
	 */
	private boolean isValidMaxVelocity(double vel) {
		return Util.fuzzyGreaterThanOrEqualTo(Math.abs(vel),Math.abs(getStartVel()));
	}
	
	/**
	 * Check wether the given number is a valid index for the sublist of sprites for 
	 * representing Mazub when he keeps moving in a certain direction.
	 * @param 	nb
	 * 			The number that has to be checked.
	 * @return	True when the number is valid.
	 * 			| ((nb >= 0) && (nb <= ((this.sprites.length - 10)/2)))
	 */
	private boolean isValidNb(int nb) {
		return ((nb >= 0) && (nb <= ((getSprites().length - 10)/2)));
	}
	
	/**
	 * Check whether Mazub is immune right now.
	 * @return 	Whether the immune time is greater than zero.
	 * 			| getImmuneTime() > 0
	 */
	public boolean isImmune() {
		return (getImmuneTime() > 0);
	}
	
	/**
	 * A method to add a certain amount of hitpoints.
	 * @param	points
	 * 			The amount of points we want to add.
	 * @param	maxHitPoints
	 * 			The maximum amount of hitpoints.
	 * @effect	The number of hitpoints is updated with setHitPoints.
	 * 			| setHitPoints(getHitPoints() + points);
	 * @post	If the new amount of hitpoints would be higher than 
	 * 			the maximum, it is set to the maximum.
	 * 			| if (getHitPoints() + points) > maxHitPoints
	 * 			|   then setHitPoints(maxHitPoints)
	 */
	protected void addHitPoints(int points, int maxHitPoints) {
		if (getHitPoints() < maxHitPoints)
			if ((getHitPoints() + points) > maxHitPoints)
				setHitPoints(maxHitPoints);
			else
				setHitPoints(getHitPoints() + points);
	}
	
	// METHODS
	/**
	 * Initiate horizontal movement of Mazub in a given direction.
	 * @param 	orientation
	 * 			The orientation in which Mazub starts moving.
	 * @pre		The orientation must be a valid direction, meaning left or right.
	 * 			| isValidDirection(Orientation orientation)
	 * @effect	The orientation of the new Mazub is set to the given orientation with setOrientation.
	 * 			| setOrientation(orientation)
	 * @effect	The number of sprites is set to 0 with setNb, for when Mazub is back on the ground.
	 * 			| setNb(0)
	 * @effect	The velocity of the new Mazub is set to the start velocity of Mazub with setXVelocity, 
	 * 			negative if Mazub is moving to the left.
	 * 			| if (orientation == Orientation.RIGHT)
	 * 			|   then setVelocity(this.getStartVel())
	 * 			| else
	 * 			|	setVelocity(-this.getStartVel())
	 * @effect	The horizontal acceleration of Mazub is set to the default value for the horizontal acceleration with setXAcc,
	 * 			negative if Mazub is moving to the left.
	 * 			| if (orientation == Orientation.RIGHT)
	 * 			|   then setXAcc(accx)
	 * 			| else
	 * 			|   setXAcc(-accx)
	 * @effect	The maximum velocity of Mazub is set to the default value for the maximum velocity with setMaxVel,
	 * 			negative if Mazub is moving to the left.
	 * 			| if (orientation == Orientation.RIGHT)
	 * 			|   then setMaxVel(maxSpeed)
	 * 			| else
	 * 			|   setMaxVel(-maxSpeed)
	 */
	public void startMove(Orientation orientation) {
		assert isValidDirection(orientation);
		setNb(0);
		time2 = 0;
		setOrientation(orientation);
		if (orientation == Orientation.RIGHT)
		{
			setRightButton(true);
			setXVelocity(getStartVel());
			setXAcc(accx);
			if (isDucking() || (! canStandUp()))
				setMaxVel(maxSpeedDuck);
			else
				setMaxVel(maxSpeed);
		}
		if (orientation == Orientation.LEFT)
		{
			setLeftButton(true);
			setXVelocity(-getStartVel());
			setXAcc(-accx);
			if (isDucking() || (! canStandUp()))
				setMaxVel(-maxSpeedDuck);
			else
				setMaxVel(-maxSpeed);
		}
	}
	
	/**
	 * Stop movement of Mazub in a given direction.
	 * @param orientation
	 * 			The orientation in which we want the alien to stop moving.
	 * @effect  The state of the orientations button is set to false with setLeftButton/setRightButton
	 * 			| if (orientation == Orientation.LEFT) 
	 * 			|	setLeftButton(false);
	 * 			| else
	 * 			|	setRightButton(false);
	 * @effect	If a new button is pressed the horizontal velocity is adjusted with startMove
	 * 			| if (this.getLeftButton()) 
	 * 			|	if (! (this.getOrientation() == Orientation.LEFT))
	 * 			|		this.startMove(Orientation.LEFT);
	 * @effect	If no new button is pressed the horizontal velocity and acceleration is set to zero using
	 * 			setXVelocity and setXAcc
	 * 			| setXVelocity(0)
	 * 			| setXAcc(0)
	 * 			
	 */
	public void endMove(Orientation orientation) {
		if (orientation == Orientation.LEFT) 
			setLeftButton(false);
		else
			setRightButton(false);
		if (getLeftButton()) {
			if (! (getOrientation() == Orientation.LEFT))
				startMove(Orientation.LEFT);
		}
		else if (getRightButton()) {
			if (! (getOrientation() == Orientation.RIGHT)) 
				startMove(Orientation.RIGHT);
		}
		else {
			setXVelocity(0);
			setXAcc(0);
		}
	}
	
	/**
	 * Initiate Mazub to jump.
	 * @effect	The new vertical velocity of Mazub is set to the vertical start velocity with setYVelocity, if the alien is
	 * 			standing on the ground or another game object.
	 * 			| if (onGround() || onGameObject())
	 * 			|   setYVelocity(startVelY)
	 * @effect	The number of sprites is set to 0 with setNb, for when Mazub is back on the ground, if the alien is
	 * 			standing on the ground or another game object.
	 * 			| if (onGround() || onGameObject())
	 * 			|    setNb(0)
	 * @effect	The vertical acceleration of Mazub is set to the default value for the vertical acceleration with setYAcc, if the 
	 * 			alien is standing on the ground or another game object.
	 * 			| if (onGround() || onGameObject())
	 * 			|    setYAcc(accy)
	 */
	public void startJump() {
		if (onGround() || onGameObject())
		{
			setYVelocity(startVelY);
			setYAcc(accy);
			setNb(0);
		}
	}

	
	/**
	 * Stop jumping.
	 * @effect	If Mazub is moving upward, his new vertical velocity will be set to 0 with setYVelocity.
	 * 			| if (this.getYVelocity() == 0)
	 * 			|   then setYVelocity(0)
	 */
	public void endJump() {
		if (getYVelocity() > 0)
			setYVelocity(0);
	}
	
	/**
	 * Initiate Mazub to duck.
	 * @effect 	The maximum velocity of Mazub is set to the maximum velocity while ducking with setMaxVel.
	 * 			| if (isMovingLeft())
	 *			|	setMaxVel(-maxSpeedDuck);
	 *			| else
	 *			| 	setMaxVel(maxSpeedDuck);
	 * @effect 	The number of the current sprite in a sublist is set to zero with setNb.
	 * 			| setNb(0)
	 * @effect	Mazub's ducking state is set to true with setDuck.
	 * 			| setDuck(true);
	 */
	public void startDuck() {
		if (isMovingLeft())
			setMaxVel(-maxSpeedDuck);
		else
			setMaxVel(maxSpeedDuck);
		setDuck(true);
		setNb(0);
	}
	
	/**
	 * Stop ducking.
	 * @effect 	The maximum velocity is set to the default value with setMaxVel, if the alien can stand up.
	 * 			| if ((isMovingLeft()) && (canStandUp()))
	 *			|   setMaxVel(-maxSpeed);
	 *		    | else if (canStandUp())
	 *			|   setMaxVel(maxSpeed);
	 * @effect	Mazub's ducking state is set to false with setDuck.
	 * 			| setDuck(false);
	 */
	public void endDuck() {
		if (canStandUp())
		{
			if (isMovingLeft())
				setMaxVel(-maxSpeed);
			else
				setMaxVel(maxSpeed);
		}
		setDuck(false);
	}
	
	/**
	 * A method for reducing hitpoints when the alien touches water.
	 * @param time
	 * 			The time during which the alien touches water.
	 * @post 	timerWater is adjusted
	 * 			| timerWater += time
	 * 			| int times = (int) Math.floor(timerWater*5);
	 * 			| timerWater -= times*0.2;
	 * @effect 	The proper amount of hitpoints is deducted using reduceHitPoints
	 * 			| reduceHitPoints(times*2)
	 */
	private void touchWater(double time) {
		timerWater += time;
		int times = (int) Math.floor(timerWater*5);
		timerWater -= times*0.2;
		reduceHitPoints(times*2);
	}
	
	/**
	 * A method for reducing hitpoints when the alien touches magma.
	 * @param 	time
	 * 			The time during which the alien touches magma.
	 * @effect 	The proper amount of hitpoints is deducted using reduceHitPoints
	 * 			| if (timerMagma == 0)
	 * 			|	reduceHitPoints(50);
	 * 			| else
	 * 			| 	timerMagma += time
	 * 			| 	int times = (int) Math.floor(timerMagma*5)
	 * 			| 	reduceHitPoints(times*50)
	 * @post 	timerMagma is adjusted
	 * 			| timerMagma += time
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
	 * Update the position and the velocity of Mazub after a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which Mazub's new position and velocity are calculated.
	 * @effect	The newly computed position is checked with checkWorldH and checkCollH and if necessary corrected with fixWorldH
	 * 			and fixCollH.
	 * @effect	The position is updated to the computed one with setXPosition.
	 * 			| setXPosition(s);
	 */
	private void Move(double time) {
		double s;
		if (Util.fuzzyLessThanOrEqualTo(getXVelocity(),getMaxVel()))
		{
			if (getOrientation() == Orientation.RIGHT)
				setXAcc(accx);
			else
				setXAcc(-accx);
		}
		if (reachesMaxSpeed(time))
		{
			double t1 = (getMaxVel() - getXVelocity())/(getXAcc());
			double t2 = time - t1;
			if (Util.fuzzyGreaterThanOrEqualTo(getXVelocity(),getMaxVel()))
				s = getXPosition() + 100*(getMaxVel()*time);
			else
				s = getXPosition() + 100*(getXVelocity()*t1 + 0.5*(getXAcc())*t1*t1 + getMaxVel()*t2);
			setXVelocity(getMaxVel());		
			setXAcc(0);
		}
		else
		{
			s = getXPosition() + 100*(getXVelocity()*time + 0.5*(getXAcc())*time*time);
			setXVelocity(getXVelocity() + (getXAcc())*time);
		}
		if (! isValidXPosition(s))
			terminate();
		try {
			CheckCollH(s);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				s = collH(exc,s);
			else
				s = fixCollH(exc,s);
		}
		try {
			CheckWorldH(s);
		} catch (CollisionException exc) {
			s = fixWorldH(exc,s);
		}
		setXPosition(s);
	}
	
	/**
	 * Update the position and velocity of Mazub after jumping for a given time period.
	 * @param 	time
	 * 			The time period after which Mazub's new postion and velocity are calculated.
	 * @effect	The newly computed position is checked with checkWorldV and checkCollV and if necessary corrected with fixWorldV
	 * 			and fixCollV.
	 * @effect	The position is updated to the computed one with setYPosition.
	 * 			| setYPosition(h);
	 */
	private void Jump(double time) {
		double h = getYPosition() + 100*((getYVelocity()*time) + (getYAcc()*0.5*time*time));
		this.setYVelocity(getYVelocity() + getYAcc()*time);
		if (! isValidYPosition(h))
			terminate();
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				h = collV(exc,h);
			else
				h = fixCollV(exc,h);
		}
		try {
			CheckWorldV(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		setYPosition(h);
	}
	
	/**
	 * Update the position and velocity of Mazub after falling for a given time period.
	 * @param time
	 * 			The time period after which Mazub's new postion and velocity are calculated.
	 * @effect	If the alien falls on ground or an other game object, his vertical velocity and acceleration are
	 * 			set to zero using setYVelocity and setYAcc. If not the velocity is updated using the same methods.
	 * 			| if (onGround() || onGameObject())
	 * 			|	setYVelocity(0);
	 * 			|	setYAcc(0);
	 * 			| else
	 * 			|	setYVelocity(getYVelocity() + getYAcc()*time)
	 * @effect	The newly computed position is checked with checkWorldV and checkCollV and if necessary corrected with fixWorldV
	 * 			and fixCollV.
	 * @effect	The position is updated to the computed one with setYPosition.
	 * 			| setYPosition(h);
	 */
	private void Fall(double time) {
		setYAcc(accy);
		double h = getYPosition() + 100*(getYVelocity()*time + 0.5*getYAcc()*time*time);
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				h = collV(exc,h);
			else
				h = fixCollV(exc,h);
		}
		try {
			CheckWorldV(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		setYPosition(h);
		if (onGround() || onGameObject())
		{
			setYVelocity(0);
			setYAcc(0);
		}
		else
			setYVelocity(getYVelocity() + getYAcc()*time);
	}
	
	/**
	 * Advance the timers and update Mazub's new position and velocity after the given time duration,
	 * according to the state Mazub is in.
	 * @param 	t
	 * 			The time after which the new values are computed.
	 * @effect	When Mazub is moving, his new position and velocity are set with Move.
	 * 			| Move(t)
	 * @effect	When Mazub is falling, his new position and velocity are set with Fall.
	 * 			| Fall(t)
	 * @effect	When Mazub is jumping, his new position and velocity are set with Jump.
	 * 			| this.Jump()
	 * @effect	If Mazub is touching water, its hitpoints are updated with touchWater.
	 * 			| if (medium.contains(2))
	 * 			| then touchWater(time)
	 * @effect	If Mazub is touching magma, its hitpoints are updated with touchMagma.
	 * 			| if (medium.contains(3))
	 * 			| then touchMagma(time)
	 */
	private void advance(double t) {
		if (isTerminated())
		{
			setTerminatedTime(getTerminatedTime() + t);
			if (Util.fuzzyGreaterThanOrEqualTo(getTerminatedTime(),0.6))
				getWorld().setGameOver(true);
		}
		if ((! isTerminated()) && (t != 0.0))
		{
			setTerminatedTime(0);
			if (! isMoving())
			{
				time1 += t;
				time2 = 0;
			}
			if (isMoving())
			{
				time1 = 0;
				time2 += t;
				Move(t);
			}
			if (isJumping())
			{
				time2 = 0;
				Jump(t);
			}
			else if (! isJumping())
			{
				Fall(t);
			}
			if (isImmune())
				setImmuneTime(getImmuneTime() - t);
			ArrayList<Integer> medium = CheckMedium();
			if (medium.contains(2))
				touchWater(t);
			else
				timerWater = 0;
			if (medium.contains(3))
				touchMagma(t);
			else
				timerMagma = 0;
		}
		checkWon();
	}
	
	/**
	 * A method that checks whether the player has reached the target tile.
	 * @return 	True if Mazub is situated on the target tile.
	 */
	private void checkWon() {
		int[][] tiles = getWorld().getTilePositions((int) Math.round(getXPosition()),(int) Math.round(getYPosition()),
				(int) Math.round(getXPosition()+getCurrentSprite().getWidth()-1),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-1));
		for (int[] tile : tiles)
			if ( (tile[0] == getWorld().getTargetTileX()) && (tile[1] == getWorld().getTargetTileY()) )
			{
				getWorld().setWon(true);
				getWorld().setGameOver(true);
			}
	}
	
	/**
	 * Advance the timers and update Mazub's new position and velocity after the given time duration.
	 * @param 	time
	 * 			The time duration between this position and the next one.
	 * @effect	The new position and velocity are computed with the method advance.
	 * 			| advance(dt);
	 * @throws	ModelException
	 * 			The given time is not valid (between 0 and 0.2)
	 * 			| ! isValidTime(time)
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
	
	/**
	 * Return the Sprite in the sublist that has to be returned when Mazub keeps
	 * walking in a certain direction.
	 * @param 	list
	 * 			The given sublist
	 * @return	The sprite that represents this state of Mazub.
	 * 			| list[this.getNb()]
	 */
	private Sprite Next(Sprite[] list) {
		if (time2 > timebetween)
		{
			setNb(getNb()+1);
			time2 -= timebetween;
		}
		return list[getNb()];
	}
	
	/**
	 * A method that returns Mazub's current sprite.
	 * @pre 	The length of the sprites array must be even.
	 * @pre 	The length of the sprites array must be greater than 10.
	 * @return 	Mazub's current sprite.
	 * @post 	Mazub's current sprite is updated.
	 */
	@Basic @Override
	public Sprite getCurrentSprite() {
		assert isEven(getSprites().length);
		assert (getSprites().length >= 10);
		if ((! isMoving()) && ((! isDucking()) && (canStandUp())) && (time1 > timelastmovement) )
		{
			setOrientation(Orientation.NONE);
			return getSprites()[0];
		}
		if ((! isMoving()) && ((isDucking()) || (! canStandUp()) ) && (time1 > timelastmovement) )
		{
			setOrientation(Orientation.NONE);
			return getSprites()[1];
		}
		if ((! isMoving()) && ((! isDucking()) && (canStandUp())) && (getOrientation() == Orientation.RIGHT)) 
		{
			return getSprites()[2];
		}
		if ((! isMoving()) && ((! isDucking()) && (canStandUp())) && (getOrientation() == Orientation.LEFT))
		{
			return getSprites()[3];
		}
		if ((isMovingRight()) && (getYVelocity() != 0) && (! isDucking()))
		{
			return getSprites()[4];
		}
		if ((isMovingLeft()) && (getYVelocity() != 0) && (! isDucking()))
		{
			return getSprites()[5];
		}
		if (((isDucking()) || (! canStandUp())) && ( (isMovingRight())|| ((time1 < timelastmovement) && 
				(getOrientation() == Orientation.RIGHT))))
		{
			return getSprites()[6];
		}
		if (((isDucking()) || (! canStandUp())) && ( (isMovingLeft())|| ((time1 < timelastmovement) && 
				(getOrientation() == Orientation.LEFT))))
		{
			return getSprites()[7];
		}
		int m = (getSprites().length-10)/2;
		if ((isMovingRight()) && (! isJumping()|| (getYPosition() == 0)) && (! isDucking())) 
		{
			Sprite[] sublist = Arrays.copyOfRange(getSprites(),8,8+m+1);
			return Next(sublist);
		}
		if ((isMovingLeft()) && (! isJumping() || (getYPosition() == 0)) && (! isDucking()))
		{
			Sprite[] sublist = Arrays.copyOfRange(getSprites(),9+m,9+2*m+1);
			return Next(sublist);
		}
		return getSprites()[0];
	}
}