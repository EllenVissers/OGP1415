package jumpingalien.model;
import java.util.Arrays;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

/* TEAM INFORMATION
* Ellen Vissers: 2e bachelor Burgerlijk ingenieur, CW-ELT
* Nina Versin: 2e bachelor Burgerlijk ingenieur, ELT-CW
* Repository: https://gitlab.com/EllenVissers/JumpingAlien_OGP1415
*/

/**
 * A class of aliens called Mazub involving a horizontal and vertical position, a horizontal and verical velocity,
 * a list of images, an orientation, a height, a number pointing to a certain sprite, a start velocity and a maximum speed.
 * @invar 	Mazub's bottom-left position stays within the bounds of the world.
 * 			| this.isValidXPosition(this.getXPosition()) && this.isValidYPosition(this.getYPosition())
 * @invar 	Mazub's horizontal speed will never be greater than the maximum speed.
 * 			| this.getXVelocity() <= this.getMaxVel()
 * 
 * @author Ellen Vissers, Nina Versin
 * @version 2.0
 */
public class Mazub {

	// CONSTRUCTORS
	/**
	 * Initialize a new Mazub with given position, velocity, images, orientation, height, number of image, start velocity and 
	 * maximum speed.
	 * @param 	pos_x
	 * 			The horizontal position of Mazub corresponding to the position of the outer left pixel of it's image.
	 * @param 	pos_y
	 * 			The vertical position of Mazub corresponding to the position of the lowest pixel of it's image.
	 * @param 	vel_x
	 * 			The horizontal velocity of Mazub.
	 * @param 	vel_y
	 * 			The vertical velocity of Mazub.
	 * @param 	acc_x
	 * 			The horizontal acceleration of Mazub.
	 * @param 	acc_y
	 * 			The vertical acceleration of Mazub.
	 * @param 	sprites
	 * 			The list of images for the different states of Mazub.
	 * @param 	orientation
	 * 			The orientation of Mazub, being left, right or none.
	 * @param 	YP
	 * 			The height of Mazub.
	 * @param 	nb
	 * 			The number of the image of Mazub when he is walking to the left or to the right.
	 * @param 	start_vel
	 * 			The start velocity of Mazub.
	 * @param 	max_vel
	 * 			The maximum speed of Mazub.
	 * @throws  Throws a ModelException if sprites is an emty array.
	 * 			| sprites == null
	 */
	public Mazub(double pos_x, double pos_y, double vel_x, double vel_y, double acc_x, double acc_y, Sprite[] sprites, Orientation orientation ,int YP, int nb,
			double start_vel, double max_vel, boolean leftButton, boolean rightButton) throws ModelException {
		if (sprites == null)
			throw new ModelException("Empty array of sprites");
		setXPosition(pos_x);
		setYPosition(pos_y);
		setXVelocity(vel_x);
		setYVelocity(vel_y);
		setXAcc(acc_x);
		setYAcc(acc_y);
		this.sprites = sprites;
		setOrientation(orientation);
		setYSize(YP);
		setNb(nb);
		setStartVel(start_vel);
		setMaxVel(max_vel);
		this.leftButton = leftButton;
		this.rightButton = rightButton;
		this.setHitPoints(startHitPoints);
	}
	
	/**
	 * Initialize a new Mazub with given position, velocity 0, given images, orientation none, the height of the default sprite 
	 * (not moving), number of image 0 and variables start velocity and maximum speed.
	 * @param 	pos_x
	 * 			The horizontal position of Mazub corresponding to the position of the outer left pixel of it's image.
	 * @param 	pos_y
	 * 			The vertical position of Mazub corresponding to the position of the lowest pixel of it's image.
	 * @param 	sprites
	 * 			The list of images for the different states of Mazub.
	 * @effect 	This new Mazub is initialized with 0 as its horizontal and vertical velocity and acceleration, NONE as its orientation, 
	 * 			0 as its sprite-number, the standard velocity as the horizontal start velocity and the standard maximum speed as its 
	 * 			maximum speed.
	 *       	| this(pos_x, pos_y, 0, 0, 0, 0, sprites, Orientation.NONE, sprites[0].getHeight(), 0, startVelX, maxSpeed) 
	 */
	public Mazub(int pos_x, int pos_y, Sprite[] sprites) {
		this(pos_x, pos_y, 0, 0, 0, 0, sprites, Orientation.NONE, sprites[0].getHeight(), 0, startVelX, maxSpeed, false, false);	
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
	 * Variable registering the horizontal position of Mazub.
	 */
	private double pos_x;
	/**
	 * Variable registering the vertical position of Mazub.
	 */
	private double pos_y;
	/**
	 * Variable registering the horizontal velocity of Mazub.
	 */
	private double vel_x;
	/**
	 * Variable registering the vertical velocity of Mazub.
	 */
	private double vel_y;
	/**
	 * Variable registering the horizontal acceleration of Mazub.
	 */
	private double acc_x;
	/**
	 * Variable registering the vertical acceleration of Mazub.
	 */
	private double acc_y;
	/**
	 * Variable registering the list of images of Mazub.
	 */
	public Sprite[] sprites;
	/**
	 * Variable registering the orientation of Mazub.
	 */
	private Orientation orientation;
	/**
	 * Variable registering the height of Mazub.
	 */
	private int YP;
	/**
	 * Variable registering the number of hitpoints of Mazub.
	 */
	private int hitPoints;
	
	//VARIABLES
	/**
	 * Variable registering the horizontal acceleration that applies to all Mazub's. 
	 */
	private static double accx = 0.9;
	/**
	 * Variable registering the vertical acceleration that applies to all Mazub's.
	 */
	private static double accy = -10.0;
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
	private static int startHitPoints = 100;
	
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
	 */
	private void setLeftButton(boolean left) {
		this.leftButton = left;
	}
	
	/**
	 * Set the indicator for the right button key to true of false.
	 * @param 	right
	 * 			The variable that indicates whether the right button key is pressed or not.
	 */
	private void setRightButton(boolean right) {
		this.rightButton = right;
	}
	
	/**
	 * Return Mazub's current horizontal position.
	 * @return 	The horizontal position.
	 * 			| this.pos_x
	 */
	@Basic
	public double getXPosition() {
		return this.pos_x;
	}
	
	/**
	 * Set the horizontal position of Mazub to the given position.
	 * @param 	position  
	 * 			The position we want to set Mazub's horizontal position to.
	 * @throws 	ModelException
	 * 			The given position is not a valid one.
	 * 			| isValidXPosition(position)
	 * @post 	Mazub's horizontal position is updated to the given position.
	 * 			| new.pos_x = position
	 */
	private void setXPosition(double position) throws ModelException {
		if (! isValidXPosition(position))
			throw new ModelException("Invalid horizontal position");
		this.pos_x = position;
	}
	
	/**
	 * Return Mazub's current vertical position.
	 * @return	The verical position.
	 * 			| this.pos_y
	 */
	@Basic
	public double getYPosition() {
		return this.pos_y;
	}
	
	/**
	 * Set the vertical position of Mazub to the given position.
	 * @param 	position 
	 * 			The position we want to set Mazub's vertical position to.
	 * @throws 	ModelException
	 * 			The given position is not a valid one.
	 * 			| isValidYPosition(position)
	 * @post 	Mazub's vertical position is updated to the given position.
	 * 			| new.pos_y = position
	 */
	private void setYPosition(double position) {
		if (! isValidYPosition(position))
			throw new ModelException("Invalid vertical position");
		this.pos_y = position;
	}
	
	/**
	 * Return Mazub's current horizontal velocity.
	 * @return	The horizontal velocity of Mazub.
	 * 			|this.vel_x
	 */
	@Basic
	public double getXVelocity() {
		return this.vel_x;
	}
	
	/**
	 * Set the horizontal velocity of Mazub to the given velocity.
	 * @param 	velocity
	 * 			The new velocity of Mazub.
	 * @post 	Mazub's horizontal velocity is updated to the given velocity.
	 * 			| new.vel_x = velocity
	 */
	private void setXVelocity(double velocity) {
		this.vel_x = velocity;
	}
	
	/**
	 * Return Mazub's current vertical velocity.
	 * @return	The vertical velocity.
	 * 			| this.vel_y
	 */
	@Basic
	public double getYVelocity() {
		return this.vel_y;
	}
	
	/**
	 * Set the vertical velocity of Mazub to the given velocity.
	 * @param 	velocity
	 * 			The new velocity of Mazub.
	 * @post 	Mazub's vertical velocity is updated to the given velocity.
	 * 			| new.vel_y = velocity
	 */
	private void setYVelocity(double velocity) {
		this.vel_y = velocity;
	}
	
	/**
	 * Return Mazub's current horizontal acceleration.
	 * @return	The horizontal acceleration of Mazub.
	 * 			|this.acc_x
	 */
	@Basic
	public double getXAcc() {
		return this.acc_x;
	}
	
	/**
	 * Set the horizontal acceleration of Mazub to the given acceleration.
	 * @param 	acceleration
	 * 			The new acceleration of Mazub.
	 * @post 	Mazub's horizontal acceleration is updated to the given acceleration.
	 * 			| new.acc_x = acceleration
	 */
	private void setXAcc(double acceleration) {
		this.acc_x = acceleration;
	}
	
	/**
	 * Return Mazub's current vertical acceleration.
	 * @return	The vertical acceleration.
	 * 			| this.acc_y
	 */
	@Basic
	public double getYAcc() {
		return this.acc_y;
	}
	
	/**
	 * Set the vertical acceleration of Mazub to the given acceleration.
	 * @param 	acceleration
	 * 			The new acceleration of Mazub.
	 * @post 	Mazub's vertical acceleration is updated to the given acceleration.
	 * 			| new.acc_y = acceleration
	 */
	private void setYAcc(double acceleration) {
		this.acc_y = acceleration;
	}
	
	/**
	 * Returns the value of the begin velocity of Mazub.
	 * @return 	Mazub's begin velocity.
	 * 			| this.start_vel
	 */
	@Basic
	public double getStartVel()
	{
		return this.start_vel;
	}
	
	/**
	 * Set the begin velocity of Mazub to a given velocity.
	 * @param 	vel
	 * 			The new begin velocity of Mazub.
	 * @pre 	The start velocity is a valid start velocity.
	 * 			| isValidStartVelocity(vel)
	 * @post 	Mazub's start velocity is updated to the given velocity.
	 * 			| new.start_vel = vel
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
	 * 			| this.isValidMaxVelocity(vel)
	 * @post 	Mazub's maximum velocity is updated to the given velocity.
	 * 			| new.max_vel = vel
	 */
	private void setMaxVel(double vel) {
		assert this.isValidMaxVelocity(vel);
		this.max_vel = vel;
	}
	
	/**
	 * Return the y-coordinate of Mazub's upper right position.
	 * @return 	The height of Mazub.
	 * 			| this.YP
	 */
	@Basic
	public int getYSize() {
		return this.YP;
	}
	
	/**
	 * Set the y-coordinate of Mazub's upper right position to a given one.
	 * @param 	size
	 * 			The new height of Mazub.
	 * @throws	ModelException
	 * 			The given size is not valid.
	 * 			| isValidSize(size)
	 * @post 	Mazub's upper right position is updated to the given size.
	 * 			| new.YP = size
	 */
	private void setYSize(int size) throws ModelException {
		if (! isValidSize(size))
			throw new ModelException("Invalid height");
		this.YP = size;
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
	 * @post	The new number is a valid number (lies within the ranges of the sublist)
	 * 			| isValidNb(i)
	 */
	private void setNb(int i) {
		if (! this.isValidNb(i))
			i = 0;
		this.nb = i;
	}
	
	/**
	 * Get the orientation of Mazub.
	 * @return 	The orientation of Mazub.
	 * 			| this.orientation
	 */
	@Basic
	public Orientation getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Set the orientation of Mazub to the given orientation.
	 * @param 	orientation
	 * 			The new orientation of Mazub.
	 * @post 	Mazub's orientation is updated to the given orientation.
	 * 			| new.orientation = orientation
	 */
	private void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * 
	 * @param points
	 */
	private void setHitPoints(int points) {
		this.hitPoints = points;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	//CHECKERS
	/**
	 * Check whether Mazub is moving to the left.
	 * @return 	True if Mazub is moving to the left.
	 * 			| this.vel_x < 0
	 */
	public boolean isMovingLeft() {
		return this.vel_x < 0;
	}
	
	/**
	 * Check whether Mazub is moving to the right.
	 * @return 	True when Mazub is moving to the right.
	 * 			| this.vel_x > 0
	 */
	public boolean isMovingRight() {
		return this.vel_x > 0;
	}
	
	/**
	 * Check whether Mazub is moving.
	 * @return 	True when Mazub is moving.
	 * 			| this.vel_x != 0
	 */
	public boolean isMoving() {
		return (this.vel_x != 0);
	}
	
	/**
	 * Check whether Mazub is jumping.
	 * @return 	True when Mazub is jumping.
	 * 			| this.vel_y != 0
	 */
	public boolean isJumping() {
		return (this.vel_y != 0);
	}
	
	/**
	 * Check whether Mazub is ducking.
	 * @return 	True when Mazub is ducking.
	 * 			| this.getYSize() < this.sprites[0].getHeight()
	 */
	private boolean isDucking() {
		return (this.getYSize() < this.sprites[0].getHeight());
	}
	
	private boolean isInTheAir() {
		return (this.getYPosition() > minY);
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
	 * Check whether the given time duration is valid.
	 * @param 	time
	 * 			The time duration that has to be checked.
	 * @return  True when the time is valid.
	 * 			| (time >= 0) && (time < 0.2)
	 */
	private boolean isValidTime(double time) {
		return ((time >= 0) && (time < 0.2));
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
		return (vel >= 1.0);
	}
	
	/**
	 * Check whether the given velocity is a valid maximum velocity for Mazub.
	 * @param 	vel
	 * 			The velocity that has to be checked.
	 * @return 	True when the given velocity is valid.
	 * 			| vel > this.getStartVel()
	 */
	private boolean isValidMaxVelocity(double vel) {
		return (vel > this.getStartVel());
	}
	
	/**
	 * Check wether the given size is a valid size for Mazub.
	 * @param	size
	 * 			The size that has to be checked.
	 * @return	True when the given size is valid.
	 * 			| ((size > 0) || (size > maxY))
	 */
	private boolean isValidSize(int size) {
		return ((size > 0) || (size > maxY));
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
		return ((nb >= 0) && (nb <= ((this.sprites.length - 10)/2)));
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
		this.setNb(0);
		time2 = 0;
		this.setOrientation(orientation);
		if (orientation == Orientation.RIGHT)
		{
			this.setRightButton(true);
			this.setXVelocity(this.getStartVel());
			this.setXAcc(accx);
			if (isDucking())
				this.setMaxVel(maxSpeedDuck);
			else
				this.setMaxVel(maxSpeed);
		}
		if (orientation == Orientation.LEFT)
		{
			this.setLeftButton(true);
			this.setXVelocity(-this.getStartVel());
			this.setXAcc(-accx);
			if (isDucking())
				this.setMaxVel(-maxSpeedDuck);
			else
				this.setMaxVel(-maxSpeed);
		}
	}
	
	/**
	 * Stop horizontal movement of Mazub in any direction.
	 * @effect	The new horizontal velocity of Mazub is set to 0 with setXVelocity.
	 * 			| setXVelocity(0)
	 * @effect	The horizontal acceleration of Mazub is set to 0 for the horizontal acceleration with setXAcc.
	 * 			| setXAcc(0)
	 */
	public void endMove() {
		if (this.getLeftButton()) {
			if (! (this.getOrientation() == Orientation.LEFT))
				this.startMove(Orientation.LEFT);
		}
		else if (this.getRightButton()) {
			if (! (this.getOrientation() == Orientation.RIGHT)) 
				this.startMove(Orientation.RIGHT);
		}
		else {
			this.setXVelocity(0);
			this.setXAcc(0);
		}
	}
	
	public void endMove(Orientation orientation) {
		if (orientation == Orientation.LEFT) 
			setLeftButton(false);
		else
			setRightButton(false);
		if (this.getLeftButton()) {
			if (! (this.getOrientation() == Orientation.LEFT))
				this.startMove(Orientation.LEFT);
		}
		else if (this.getRightButton()) {
			if (! (this.getOrientation() == Orientation.RIGHT)) 
				this.startMove(Orientation.RIGHT);
		}
		else {
			this.setXVelocity(0);
			this.setXAcc(0);
		}
	}
	
	/**
	 * Initiate Mazub to jump.
	 * @effect	The new vertical velocity of Mazub is set to the vertical start velocity with setYVelocity.
	 * 			| setYVelocity(startVelY)
	 * @effect	The number of sprites is set to 0 with setNb, for when Mazub is back on the ground.
	 * 			| setNb(0)
	 * @effect	The vertical acceleration of Mazub is set to the default value for the vertical acceleration with setYAcc.
	 * 			| setYAcc(accy)
	 */
	public void startJump() {
		if (! isInTheAir())
			this.setYVelocity(startVelY);
			this.setYAcc(accy);
			this.setNb(0);
	}
	
	/**
	 * Stop jumping.
	 * @effect	If Mazub is moving upward, his new vertical velocity will be set to 0 with setYVelocity
	 * 			| if (this.getYVelocity() == 0)
	 * 			|   then setYVelocity(0)
	 */
	public void endJump() {
		if (this.getYVelocity() > 0)
			this.setYVelocity(0);
	}
	
	/**
	 * Initiate Mazub to duck.
	 * @effect 	The maximum velocity of Mazub is set to the maximum velocity while ducking with setMaxVel.
	 * 			| setMaxVel(maxSpeedDuck)
	 * @effect 	Mazub's size is set to the size of a sprite where he's ducking with setYSize.
	 * 			| setYSize(this.sprites[1].getHeight())
	 * @effect 	The number of the current sprite in a sublist is set to zero with setNb.
	 * 			| setNb(0)
	 */
	public void startDuck() {
		if (isMovingLeft())
			this.setMaxVel(-maxSpeedDuck);
		else
			this.setMaxVel(maxSpeedDuck);
		this.setYSize(this.sprites[1].getHeight());
		this.setNb(0);
	}
	
	/**
	 * Stop ducking.
	 * @effect 	The maximum velocity is set to the default value with setMaxVel.
	 * 			| setMaxVel(maxSpeed)
	 * @effect 	Mazub's size is set to the size of a sprite where he is standing with setYSize.
	 * 			| setYSize(this.sprites[0].getHeight())
	 */
	public void endDuck() {
		if (isMovingLeft())
			this.setMaxVel(-maxSpeed);
		else
			this.setMaxVel(maxSpeed);
		this.setYSize(this.sprites[0].getHeight());
	}
	
	/**
	 * Update the position and the velocity of Mazub after a given time duration using its current position and velocity.
	 * @param 	time
	 * 			The time duration after which Mazub's new position and velocity are calculated.
	 * @throws 	OutOfRangeException
	 * 			Mazub's new position lies outside the game world.
	 * 			| ! isValidXPosition(s)
	 * @effect	If Mazub does not reach his maximum velocity, his position and velocity are updated with setXPosition
	 * 			and setXVelocity.
	 * 			| setXVelocity(this.getXVelocity() + this.getXAcc()*time)
	 * 			| && setXPosition(this.getXPosition() + 100*(this.getXVelocity()*time + 0.5*(accx)*time*time))
	 * @effect	If Mazub reaches his maximum velocity, his position is updated with the extra distance travelled while 
	 * 			he was still accelerating and the distance travelled while he was at top-speed. His velocity is set to 
	 * 			the maximum speed.
	 * 			| setXVelocity(this.getXPosition() + 100*(this.getXVelocity() * t1 + 0.5*(accx)*t1*t1 + this.getMaxVel() * t2))
	 * 			| setXVelocity(this.getMaxVel())
	 */
	private void Move(double time) throws OutOfRangeException {
		double s;
		if (reachesMaxSpeed(time))
		{
			double t1 = (this.getMaxVel() - this.getXVelocity())/(getXAcc());
			double t2 = time - t1;
			if (Math.abs(this.getXVelocity()) >= Math.abs(this.getMaxVel()))
			{
				s = this.getXPosition() + 100*(this.getMaxVel()*time);
			}
			else
				s = this.getXPosition() + 100*(this.getXVelocity() * t1 + 
						0.5*(getXAcc())*t1*t1 + this.getMaxVel() * t2);
			this.setXVelocity(this.getMaxVel());
		}
		else
		{
			s = this.getXPosition() + 100*(this.getXVelocity()*time + 0.5*(getXAcc())*time*time);
			this.setXVelocity(this.getXVelocity() + (getXAcc())*time);
		}
		if (! isValidXPosition(s))
			throw new OutOfRangeException(s,minX,maxX);
		this.setXPosition(s);
	}
	
	/**
	 * Update the position and velocity of Mazub after jumping for a given time period.
	 * @param 	time
	 * 			The time period after which Mazub's new postion and velocity are calculated.
	 * @throws	OutOfRangeException
	 * 			Mazub's new position lies outside the game world.
	 * 			| ! isValidYPosition(s)
	 * @effect	Mazub's velocity and position are updated with setYPosition and setYVelocity.
	 * 			| this.setYPosition(this.getYPosition() + 100*(this.getYVelocity()*time + this.getYAcc()*(1/2)*time*time))
	 * 			| && this.setYVelocity(this.getYVelocity() + accy*time)
	 */
	private void Jump(double time) throws OutOfRangeException {
		double h = this.getYPosition() + 100*((this.getYVelocity()*time) + (this.getYAcc()*0.5*time*time));
		if (! isValidYPosition(h))
			throw new OutOfRangeException(h,minY,maxY);
		else {
			this.setYPosition(h);
			this.setYVelocity(this.getYVelocity() + this.getYAcc()*time);
		}
	}
	
	/**
	 * Advance the timers and update Mazub's new position and velocity after the given time duration.
	 * @param 	time
	 * 			The time duration between this position and the next one.
	 * @effect	When Mazub is moving to the right, his new position is set with MoveRight.
	 * 			| this.MoveRight()
	 * @effect	When Mazub is moving to the left, his new position is set with MoveLeft.
	 * 			| this.MoveLeft()
	 * @effect	When Mazub is jumping or falling, his new position is set with Jump.
	 * 			| this.Jump()
	 * @throws	ModelException
	 * 			The given time is not valid (between 0 and 0.2)
	 * 			| ! isValidTime(time)
	 */
	public void advanceTime(double time) throws ModelException {
		if (! isValidTime(time))
			throw new ModelException("Invalid time");
		if (! isMoving())
		{
			time1 += time; time2 = 0;
		}
		if (isMoving())
		{
			time1 = 0; time2 += time;
			try {
				this.Move(time);
			}
			catch (OutOfRangeException exc) {
				if (exc.getWrong() < exc.getLow())
				{
					this.setXPosition(minX);
				}
				else
				{
					this.setXPosition(maxX-1);
				}
			}
		}
		if ((isJumping()) || (this.pos_y > 0) )
		{
			time2 = 0;
			try {
				this.Jump(time);
			}
			catch (OutOfRangeException exc) {
				if (exc.getWrong() < exc.getLow())
				{
					this.setYPosition(minY);
					this.setYVelocity(0);
					this.setYAcc(0);
				}
				else
				{
					this.setYPosition(maxY-1);
					this.setYVelocity(0);
				}
			}
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
			this.setNb(this.getNb()+1);
			time2 -= timebetween;
		}
		
		return list[this.getNb()];
	}
	
	/**
	 * A method that returns Mazub's current sprite.
	 * @pre 	The length of the sprites array must be even.
	 * @pre 	The length of the sprites array must be greater than 10.
	 * @return 	Mazub's current sprite.
	 * @post 	Mazub's current sprite is updated.
	 */
	@Basic
	public Sprite getCurrentSprite() {
		assert isEven(this.sprites.length);
		assert (this.sprites.length >= 10);
		if ((! this.isMoving()) && (! this.isDucking()) && (time1 > timelastmovement) )
		{
			this.setOrientation(Orientation.NONE);
			this.setYSize(sprites[0].getHeight());
			return this.sprites[0];
		}
		if ((! this.isMoving()) && (this.isDucking()) && (time1 > timelastmovement) )
		{
			this.setYSize(sprites[1].getHeight());
			this.setOrientation(Orientation.NONE);
			return this.sprites[1];
		}
		if ((! this.isMoving()) && (! this.isDucking()) && (this.getOrientation() == Orientation.RIGHT)) 
		{
			this.setYSize(sprites[2].getHeight());
			return this.sprites[2];
		}
		if ((! this.isMoving()) && (! this.isDucking()) && (this.getOrientation() == Orientation.LEFT))
		{
			this.setYSize(sprites[3].getHeight());
			return this.sprites[3];
		}
		if ((this.isMovingRight()) && (this.isJumping()) && (! this.isDucking()))
		{
			this.setYSize(sprites[4].getHeight());
			return this.sprites[4];
		}
		if ((this.isMovingLeft()) && (this.isJumping()) && (! this.isDucking()))
		{
			this.setYSize(sprites[5].getHeight());
			return this.sprites[5];
		}
		if ((this.isDucking()) && ( (this.isMovingRight())|| ((time1 < timelastmovement) && (this.getOrientation() == Orientation.RIGHT))))
		{
			this.setYSize(sprites[6].getHeight());
			return this.sprites[6];
		}
		if ((this.isDucking()) && ( (this.isMovingLeft())|| ((time1 < timelastmovement) && (this.getOrientation() == Orientation.LEFT))))
		{
			this.setYSize(sprites[7].getHeight());
			return this.sprites[7];
		}
		int m = (this.sprites.length-10)/2;
		if ((this.isMovingRight()) && (! this.isJumping()|| (this.getYPosition() == 0)) && (! this.isDucking())) 
		{
			Sprite[] sublist = Arrays.copyOfRange(this.sprites,8,8+m+1);
			return this.Next(sublist);
		}
		if ((this.isMovingLeft()) && (! this.isJumping() || (this.getYPosition() == 0)) && (! this.isDucking()))
		{
			Sprite[] sublist = Arrays.copyOfRange(this.sprites,9+m,9+2*m+1);
			return this.Next(sublist);
		}
		return this.sprites[0];
	}
}