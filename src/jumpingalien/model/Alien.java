package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.program.program.Program;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public abstract class Alien extends GameObject {

	// CONSTRUCTORS
		/**
		 * Initialize a new alien with given position, velocity, acceleration, orientation, sprites, number of hitpoints, world,
		 * start velocity, and maximum velocity.
		 * @param 	x
		 * 			The horizontal position of the alien corresponding to the position of the outer left pixel of it's image.
		 * @param 	y
		 * 			The vertical position of the alien corresponding to the position of the lowest pixel of it's image.
		 * @param 	vx
		 * 			The horizontal velocity of the alien.
		 * @param 	vy
		 * 			The vertical velocity of the alien.
		 * @param 	ax
		 * 			The horizontal acceleration of the alien.
		 * @param 	ay
		 * 			The vertical acceleration of the alien.
		 * @param 	or
		 * 			The orientation of the alien.
		 * @param 	sprites
		 * 			The list of images for the different states of the alien.
		 * @param 	hitpoints
		 * 			The number of hitpoints of the alien.
		 * @param 	world
		 * 			The world in which the alien is situated.
		 * @param 	nb
		 * 			The number of the sprite when the alien is walking in a certain direction on the ground.
		 * @param 	startvel
		 * 			The start velocity of the alien.
		 * @param 	maxvel
		 * 			The maximum velocity of the alien.
		 * @param 	left
		 * 			A boolean registering whether the left button is pressed.
		 * @param 	right
		 * 			A boolean registering whether the right button is pressed.
		 * @param 	immune
		 * 			A boolean registering whether the alien is immune.
		 * @param 	duck
		 * 			A boolean registering whether the alien is ducking.
		 * @throws  Throws a ModelException if sprites is an emty array.
		 * 			| sprites == null
		 */	
		public Alien(double x, double y, double vx, double vy, double ax, double ay, Orientation or, Sprite[] sprites, int hitpoints,
				World world, double startvel, double maxvel, double immune,boolean duck) throws ModelException {
			super(x,y,vx,vy,ax,ay,or,sprites,hitpoints,world,false,0,null);
			setStartVel(startvel);
			setMaxVel(maxvel);
			setImmuneTime(immune);
			setDuck(duck);
		}
		
		/**
		 * Initialize a new alien with given position, velocity, acceleration, orientation, sprites, number of hitpoints, world,
		 * start velocity, maximum velocity and controlling program.
		 * @param 	x
		 * 			The horizontal position of the alien corresponding to the position of the outer left pixel of it's image.
		 * @param 	y
		 * 			The vertical position of the alien corresponding to the position of the lowest pixel of it's image.
		 * @param 	vx
		 * 			The horizontal velocity of the alien.
		 * @param 	vy
		 * 			The vertical velocity of the alien.
		 * @param 	ax
		 * 			The horizontal acceleration of the alien.
		 * @param 	ay
		 * 			The vertical acceleration of the alien.
		 * @param 	or
		 * 			The orientation of the alien.
		 * @param 	sprites
		 * 			The list of images for the different states of the alien.
		 * @param 	hitpoints
		 * 			The number of hitpoints of the alien.
		 * @param 	world
		 * 			The world in which the alien is situated.
		 * @param 	nb
		 * 			The number of the sprite when the alien is walking in a certain direction on the ground.
		 * @param 	startvel
		 * 			The start velocity of the alien.
		 * @param 	maxvel
		 * 			The maximum velocity of the alien.
		 * @param 	left
		 * 			A boolean registering whether the left button is pressed.
		 * @param 	right
		 * 			A boolean registering whether the right button is pressed.
		 * @param 	immune
		 * 			A boolean registering whether the alien is immune.
		 * @param 	duck
		 * 			A boolean registering whether the alien is ducking.
		 * @param	program
		 * 			The controlling program of this alien.
		 * @throws  Throws a ModelException if sprites is an emty array.
		 * 			| sprites == null
		 */	
		public Alien(double x, double y, double vx, double vy, double ax, double ay, Orientation or, Sprite[] sprites, int hitpoints,
				World world, double startvel, double maxvel, double immune,boolean duck,Program program) throws ModelException {
			super(x,y,vx,vy,ax,ay,or,sprites,hitpoints,world,false,0,program);
			setStartVel(startvel);
			setMaxVel(maxvel);
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
		public Alien(double pos_x, double pos_y, Sprite[] sprites) throws ModelException {
			this(pos_x,pos_y,0,0,0,0,Orientation.NONE,sprites,startHitPoints,null,startVelX,maxSpeed,0,false);
		}
		
		//ATTRIBUTES
		/**
		 * Variable registering the start velocity of Mazub.
		 */
		private double start_vel;
		/**
		 * Variable registering the maximum speed of Mazub.
		 */
		private double max_vel;
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
		 * The default value of the amount of hitpoints of a newly created Mazub.
		 */
		public static int startHitPoints = 100;
		/**
		 * Variable registering the maximum number of hitpoints of an alien.
		 */
		protected static final int maxHitPoints = 500;
		
		//GETTERS AND SETTERS
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
		protected void setStartVel(double vel) {
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
		protected void setMaxVel(double vel) {
			assert isValidMaxVelocity(vel);
			this.max_vel = vel;
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
		public abstract Void startMove(Orientation orientation);
		
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
		public abstract Void endMove(Orientation orientation);
		
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
		public abstract Void startJump();

		
		/**
		 * Stop jumping.
		 * @effect	If Mazub is moving upward, his new vertical velocity will be set to 0 with setYVelocity.
		 * 			| if (this.getYVelocity() == 0)
		 * 			|   then setYVelocity(0)
		 */
		public abstract Void endJump();
		
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
		public abstract Void startDuck();
		
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
		public abstract Void endDuck();
		
		/**
		 * Update Mazub's new position and velocity after the given time duration.
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
		public abstract void advanceTime(double time);
		
		/**
		 * A method that returns Mazub's current sprite.
		 * @pre 	The length of the sprites array must be even.
		 * @pre 	The length of the sprites array must be greater than 10.
		 * @return 	Mazub's current sprite.
		 * @post 	Mazub's current sprite is updated.
		 */
		@Basic @Override
		public abstract Sprite getCurrentSprite();
	}