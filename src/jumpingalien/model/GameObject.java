package jumpingalien.model;
import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import jumpingalien.model.World;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.program.Program;
import jumpingalien.program.type.ObjectType;

import java.util.ArrayList;

/**
 * A class of game objects involving a horizontal and vertical position, velocity and acceleration, an orientation,
 * a list of images, a number of hitpoints, a world, a variable registering whether the object is terminated, the time
 * the object has already been terminated and a program.
 * @invar	The horizontal and vertical position are always valid.
 * 			| isValidXPosition(getXPosition()) && isValidYPosition(getYPosition())
 * @invar 	The horizontal speed will never be greater than the maximum speed.
 * 			| getXVelocity() <= getMaxVel()
 * @author 	Ellen Vissers, Nina Versin
 * @version 2.0
 */
public abstract class GameObject extends AllObjects {

	/**
	 * Initialize a new Game Object with given position, velocity, acceleration, orientation, sprites, hitpoints, world and
	 * variable registering its state.
	 * @param 	x
	 * 			The horizontal position.
	 * @param 	y
	 * 			The vertical position.
	 * @param 	vx
	 * 			The horizontal velocity.
	 * @param 	vy
	 * 			The vertical velocity.
	 * @param 	ax
	 * 			The horizontal acceleration.
	 * @param 	ay
	 * 			The vertical acceleration.
	 * @param 	orientation
	 * 			The orientation of the object.
	 * @param 	sprites
	 * 			The list of images of the object.
	 * @param 	hitPoints
	 * 			The number of hitpoints.
	 * @param 	world
	 * 			The world in which the object is situated.
	 * @param 	terminated
	 * 			The variable registering its state, whether the object is terminated.
	 * @param	program
	 * 			The program that controls the movement of this game object.
	 * @throws 	ModelException
	 * 			Throws an exception if the list of images is empty.
	 * 			|sprites == null
	 */
	public GameObject(double x, double y, double vx, double vy, double ax, double ay, Orientation orientation, 
			Sprite[] sprites, int hitPoints, World world, boolean terminated, double terminatedTime, Program program) 
					throws ModelException {
		super(x,y,world);
		if (sprites == null)
			throw new ModelException("Empty array of sprites");
		setXVelocity(vx);
		setYVelocity(vy);
		setXAcc(ax);
		setYAcc(ay);
		setOrientation(orientation);
		this.sprites = sprites;
		setHitPoints(hitPoints);
		setTerminated(terminated);
		setTerminatedTime(terminatedTime);
		setProgram(program);
	}
	
	//VARIABLES
	/**
	 * Variable registering the horizontal velocity.
	 */
	private double vx;
	/**
	 * Variable registering the vertical velocity.
	 */
	private double vy;
	/**
	 * Variable registering the horizontal acceleration.
	 */
	private double ax;
	/**
	 * Variable registering the vertical acceleration.
	 */
	private double ay;
	/**
	 * Variable registering the vertical acceleration of a game object.
	 */
	protected final double accy = -10;
	/**
	 * Variable registering the orientation of the game object (left or right).
	 */
	private Orientation orientation;
	/**
	 * Variable registering the list of images of the game object.
	 */
	private Sprite[] sprites;
	/**
	 * Variable registering the number of hitpoints.
	 */
	private int hitPoints;
	/**
	 * Variable registering the state of the game object (true if it is terminated).
	 */
	protected boolean terminated;
	/**
	 * Variable registering the program that controls the movement of this game object.
	 */
	private Program program;
	/**
	 * Variable registering the time that has passed since the object was terminated.
	 */
	private double terminatedTime;
	/**
	 * Variable registering the number of hitpoints an alien gets when it collides with a Plant.
	 */
	private int MazubPlant = 50;
	/**
	 * Variable registering the number of hitpoints an alien and a shark lose when they collide.
	 */
	private int MazubShark = 50;
	/**
	 * Variable registering the number of hitpoints an alien and a slime lose when they collide.
	 */
	private int MazubSlime = 50;
	/**
	 * Variable registering the time the immunity of an alien lasts.
	 */
	private double immuneDT = 0.6;
	/**
	 * Variable registering the number of hitpoints a shark and a slime lose when they collide.
	 */
	private int SharkSlime = 50;
	/**
	 * Variable registering the number of hitpoints an mazub and a buzam lose when they collide.
	 */
	private int MazubBuzam = 50;
	
	//GETTERS AND SETTERS
	
	/**
	 * Return the current horizontal velocity of the game object.
	 * @return	The horizontal velocity.
	 * 			|this.vx
	 */
	@Basic
	public double getXVelocity() {
		return this.vx;
	}
	
	/**
	 * Set the horizontal velocity of the game object to the given velocity.
	 * @param 	velocity
	 * 			The new velocity of the game object.
	 * @post 	The horizontal velocity is updated to the given velocity.
	 * 			| new.vx = velocity
	 */
	protected void setXVelocity(double velocity) {
		this.vx = velocity;
	}
	
	/**
	 * Return the current vertical velocity of the game object.
	 * @return	The vertical velocity.
	 * 			| this.vy
	 */
	@Basic
	public double getYVelocity() {
		return this.vy;
	}
	
	/**
	 * Set the vertical velocity of the game object to the given velocity.
	 * @param 	velocity
	 * 			The new velocity of the game object.
	 * @post 	The vertical velocity is updated to the given velocity.
	 * 			| new.vy = velocity
	 */
	protected void setYVelocity(double velocity) {
		this.vy = velocity;
	}
	
	/**
	 * Return the current horizontal acceleration of the game object.
	 * @return	The horizontal acceleration.
	 * 			|this.ax
	 */
	@Basic
	public double getXAcc() {
		return this.ax;
	}
	
	/**
	 * Set the horizontal acceleration of the game object to the given acceleration.
	 * @param 	acceleration
	 * 			The new acceleration of the game object.
	 * @post 	The horizontal acceleration is updated to the given acceleration.
	 * 			| new.ax = acceleration
	 */
	protected void setXAcc(double acceleration) {
		this.ax = acceleration;
	}
	
	/**
	 * Return the current vertical acceleration of the game object.
	 * @return	The vertical acceleration.
	 * 			| this.ay
	 */
	@Basic
	public double getYAcc() {
		return this.ay;
	}
	
	/**
	 * Set the vertical acceleration of the game object to the given acceleration.
	 * @param 	acceleration
	 * 			The new acceleration of the game object.
	 * @post 	The vertical acceleration is updated to the given acceleration.
	 * 			| new.ay = acceleration
	 */
	protected void setYAcc(double acceleration) {
		this.ay = acceleration;
	}
	
	/**
	 * Return the list of images of the game object.
	 * @return 	The list of sprites.
	 * 			| this.sprites
	 */
	public Sprite[] getSprites() {
		return this.sprites;
	}
	
	/**
	 * Return the orientation of the game object.
	 * @return 	The orientation of the game object.
	 * 			| this.orientation
	 */
	@Basic
	public Orientation getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Set the orientation of the game object to the given orientation.
	 * @param 	orientation
	 * 			The new orientation.
	 * @post 	The orientation is updated to the given orientation.
	 * 			| new.orientation = orientation
	 */
	protected void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Return the current number of hitpoints of the game object.
	 * @return 	The current number of hitpoints.
	 * 			|this.hitPoints
	 */
	@Basic
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	/**
	 * Set the number of hitpoints to the given value.
	 * @param 	points
	 * 			The new number of hitpoints.
	 * @post 	If the given number of hitpoints is invalid, the object is terminated.
	 * 			| if (! isValidYPosition(position))
	 * 			| then terminate()
	 */
	protected void setHitPoints(int points) {
		if (! isValidNbHitPoints(points))
			terminate();
		this.hitPoints = points;
	}
	
	/**
	 * Return the y-coordinate of the upper right position.
	 * @return 	The height of the game object.
	 * 			| getCurrentSprite().getHeight()
	 */
	@Basic
	public int getYSize() {
		return getCurrentSprite().getHeight();
	}
	
	/**
	 * Return the x-coordinate of the upper right position.
	 * @return 	The height of the game object.
	 * 			| getCurrentSprite().getWidth()
	 */
	@Basic
	public int getXSize() {
		return getCurrentSprite().getWidth();
	}
	
	/**
	 * Set the time the object is terminated to the given time.
	 * @param 	time
	 * 			The new terminated time.
	 * @post	The terminated time is updated.
	 * 			| new.getTerminatedTime() == time
	 */
	protected void setTerminatedTime(double time) {
		this.terminatedTime = time;
	}
	
	/**
	 * Return the time the object is terminated.
	 * @return	Return the terminated time.
	 * 			| this.terminatedTime
	 */
	@Basic
	public double getTerminatedTime() {
		return this.terminatedTime;
	}
	
	/**
	 * Set the controlling program to the given program.
	 * @param 	program
	 * 			The controlling program of this game object.
	 */
	protected void setProgram(Program program) {
		this.program = program;
		if (program != null)
			program.addVariable("this", new ObjectType(this));
	}
	
	/**
	 * Return the program that controls this game object's movement.
	 * @return	The controlling program.
	 * 			| this.program
	 */
	public Program getProgram() {
		return this.program;
	}
	
	/**
	 * Return the length of the time interval in which the time duration has to be split up.
	 * @param 	time
	 * 			The given time duration.
	 * @param 	vx
	 * 			The horizontal velocity.
	 * @param 	vy
	 * 			The vertical velocity.
	 * @param 	ax
	 * 			The horizontal acceleration.
	 * @param 	ay
	 * 			The vertical acceleration.
	 * @return	The new time duration that is used to update position and velocity.
	 * 			| Math.min((0.01/(Math.abs(vx) + Math.abs(ax)*time)),(0.01/(Math.abs(vy) + Math.abs(ay)*time)));
	 * @post	If the game object is not moving, the original time is returned.
	 * 			| if (t == Double.POSITIVE_INFINITY)
				| then t = time
	 */
	protected double getDT(double time, double vx, double vy, double ax, double ay) {
		double t = Math.min((0.01/(Math.abs(vx) + Math.abs(ax)*time)),(0.01/(Math.abs(vy) + Math.abs(ay)*time)));
		if (t == Double.POSITIVE_INFINITY)
			t = time;
		return t;
	}
	
	//CHECKERS
	
	/**
	 * Check whether the given number of points is a valid number of hitpoints.
	 * @param 	points
	 * 			The number that has to be checked.
	 * @return	True if the number is valid.
	 * 			| points > 0
	 */
	private boolean isValidNbHitPoints(int points) {
		return (points > 0);
	}
	
	/**
	 * Check whether the given time duration is valid.
	 * @param 	time
	 * 			The time duration that has to be checked.
	 * @return  True when the time is valid.
	 * 			| (time >= 0) && (time < 0.2)
	 */
	protected boolean isValidTime(double time) {
		return ((time >= 0) && (time <= 0.2));
	}
	
	/**
	 * Check whether the game object is terminated.
	 * @return	True when the game object is terminated.
	 * 			| this.terminated > 0.6
	 */
	@Basic
	public boolean isTerminated() {
		return this.terminated;
	}
	
	/**
	 * Set the terminated state to the given state.
	 * @param 	term
	 * 			The new terminated state.
	 * @post	The terminated state is updated.
	 * 			| new.isTerminated() == term
	 */
	protected void setTerminated(boolean term) {
		this.terminated = term;
	}
	
	//METHODS
	/**
	 * Terminate the game object.
	 * @effect	The terminated state is updated with setTerminated.
	 * 			| setTerminated(true)
	 * @post	The object is terminated.
	 * 			|new.isTerminated() == true
	 */
	protected void terminate() {
		setTerminated(true);
		removeFromAll(this);
	}
	
	/**
	 * Update the position of the object after a given time duration using its current position and velocity.
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
	
	/**
	 * Advance the timers and update the object's new position, velocity and hitpoints after the given time duration,
	 * according to the state the object is in.
	 * @param 	time
	 * 			The time after which the new values are computed.
	 * @effect	The position and velocity are updated with Move, Jump, Fall and Swim (according to the specific object).
	 * @effect	The hitpoints are updated with touchWater, touchMagma and touchAir (specified for each game object).
	 */
	protected abstract void advance(double time);
	
	/**
	 * Check whether the game object is standing on impassable terrain.
	 * @return 	True if the game object is standing on impassable terrain and is not overlapping with impassable terrain.
	 */
	protected boolean onGround() {
		int[][] bottom = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()),
				(int) Math.round(getXPosition()+getXSize()-2),(int) Math.round(getYPosition()));
		boolean a = false;
		for (int[] pos : bottom)
			if (getWorld().getFeatureAt(pos[0],pos[1]) == 1)
				a = true;
		if (! a)
			return false;
		int[][] tilepos = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
				(int) Math.round(getXPosition()+getXSize()-2),(int) Math.round(getYPosition()+getYSize()-2));
		for (int[] pos : tilepos)
			if (getWorld().getFeatureAt(pos[0],pos[1]) == 1)
				return false;
		return true;
	}
	
	/**
	 * Converts the given direction to an orientation.
	 * @param 	dir
	 * 			Direction that needs to be converted.
	 * @return	The orientation that corresponds to the given direction.
	 */
	private Orientation setToOrientation(IProgramFactory.Direction dir) {
		if (dir == Direction.RIGHT)
			return Orientation.RIGHT;
		else if (dir == Direction.LEFT)
			return Orientation.LEFT;
		return Orientation.NONE;
	}
	
	/**
	 * Check whether the object is moving in the given direction.
	 * @param 	direction
	 * 			The direction that needs to be checked.
	 * @return	True if the object is moving in the given direction.
	 */
	public boolean isMoving(IProgramFactory.Direction direction) {
		Orientation or = setToOrientation(direction);
		if ((or == Orientation.RIGHT) && (getXVelocity() > 0))
				return true;
		else if ((or == Orientation.LEFT) && (getXVelocity() < 0))
				return true;
		return false;
	}
	
	/**
	 * Check whether the object is jumping.
	 * @return	True if the object is jumping.
	 */
	public boolean isJumping() {
		return (getYVelocity() > 0);
	}
	
	/**
	 * Check whether the object is ducking.
	 * @return	True if the object is ducking.
	 */
	public abstract boolean isDucking();
	
	/**
	 * Return the width of the object.
	 */
	@Override
	public double getWidth() {
		return (getCurrentSprite().getWidth());
	}
	
	/**
	 * Return the height of the object.
	 */
	@Override
	public double getHeight() {
		return (getCurrentSprite().getHeight());
	}
	
	/**
	 * Check whether the game object is standing on another game object.
	 * @return 	True if the game object is standing on another game object.
	 * 			| if ((collidesWith(getXPosition(),getYPosition()-1,obj)) && (ymin == yomax))
	 *			| return true;
	 */
	protected boolean onGameObject() {
		ArrayList<Slime> slimes = getWorld().getAllSlimes();
		ArrayList<Shark> sharks = getWorld().getAllSharks();
		ArrayList<Mazub> aliens = getWorld().getAllAliens();
		ArrayList<Buzam> buzams = getWorld().getAllBuzams();
		ArrayList<GameObject> allobjects = new ArrayList<GameObject>();
		allobjects.addAll(aliens);
		allobjects.addAll(sharks);
		allobjects.addAll(slimes);
		allobjects.addAll(buzams);
		allobjects.remove(this);
		int ymin = (int) Math.round(getYPosition());
		for (GameObject obj : allobjects)
		{
			int yomax = (int) Math.round(obj.getYPosition()+obj.getCurrentSprite().getHeight()-1);
			if ((collidesWith(getXPosition(),getYPosition()-1,obj)) && (ymin == yomax))
				return true;
		}
		return false;
	}
	
	/**
	 * Method to decrease the number of hitpoints with a given value.
	 * @param 	points
	 * 			The amount by which the number has to be decreased.
	 * @post	The number of hitpoints is decreased with the given amount.
	 * 			|new.getHitpoints() == this.getHitPoints() - points
	 * @post	If the new number of hitpoints is below or equal to zero, the game object is terminated.
	 * 			| if ((this.getHitPoints() - points) < 0)
	 * 			| then terminate()
	 */
	protected void reduceHitPoints(int points) {
		if ((getHitPoints() - points) <= 0)
		{
			terminate();
			setHitPoints(0);
		}
		else
			setHitPoints(getHitPoints()-points);
	}
	
	/**
	 * Return the medium in which the game object is situated.
	 * @return 	The integer number that corresponds to the medium in which the game object is situated.
	 */
	protected ArrayList<Integer> CheckMedium() {
		int[][] tiles = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
				(int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
		ArrayList<Integer> medium = new ArrayList<Integer>();
		for (int[] tile : tiles){
			int m = getWorld().getFeatureAt(tile[0],tile[1]);
			if ((m != 1) && (! medium.contains(m)));
				medium.add(m);	
		}
		return medium;
	}
	
	/**
	 * Check whether the game object is colliding with another game object at its new position.
	 * @param 	xa
	 * 			The new horizontal position of the game object.
	 * @param 	ya
	 * 			The new vertical position of the game object.
	 * @param 	other
	 * 			The other game object this object could collide with.
	 * @return 	True if the game object collides with the given other game object.
	 * 			| (((!(xamax <= xo)) && (!(xomax <= x)) && (!(yamax <= yo)) && (!(yomax <= y))))
	 */
	private boolean collidesWith(double xa, double ya, GameObject other) {
		int x = (int) Math.round(xa);
		int y = (int) Math.round(ya);
		int xamax = (int) Math.round(xa + getXSize() - 1);
		int yamax = (int) Math.round(ya + getYSize() - 1);
		int xo = (int) Math.round(other.getXPosition());
		int yo = (int) Math.round(other.getYPosition());
		int xomax = (int) Math.round(other.getXPosition() + other.getXSize()-1);
		int yomax = (int) Math.round(other.getYPosition() + other.getYSize()-1);
		return (((!(xamax <= xo))
				&& (!(xomax <= x))
				&& (!(yamax <= yo))
				&& (!(yomax <= y))));
	}
	
	/**
	 * Check whether the game object horizontally collides with the game world.
	 * @param 	s
	 * 			The updated horizontal position.
	 * @throws 	CollisionException
	 * 			Throws an exception if the object collides with impassable terrain of the game world.
	 */
	protected void CheckWorldH(double s) throws CollisionException {
		if (getOrientation() == Orientation.RIGHT) 
		{
			int[] tilebottom = getWorld().getTile((int) Math.round(s+getCurrentSprite().getWidth()-2),(int) Math.round(getYPosition()+1));
			if (getWorld().getFeatureAt(tilebottom[0],tilebottom[1]) == 1)
				throw new CollisionException(this,Orientation.RIGHT);
			int[] tiletop = getWorld().getTile((int) Math.round(s+getCurrentSprite().getWidth()-2),
					(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
			if (getWorld().getFeatureAt(tiletop[0],tiletop[1]) == 1)
				throw new CollisionException(this,Orientation.RIGHT);
			int h = tiletop[1] - tilebottom[1];
			if (h>1)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilebottom[0], tilebottom[1] + i) == 1)
						throw new CollisionException(this,Orientation.RIGHT);
				}
			}
		}
		if (getOrientation() == Orientation.LEFT)
		{
			int[] tilebottom = getWorld().getTile((int) Math.round(s+1),(int) Math.round(getYPosition()+1));
			if (getWorld().getFeatureAt(tilebottom[0],tilebottom[1]) == 1)
				throw new CollisionException(this,Orientation.LEFT);
			int[] tiletop = getWorld().getTile((int) Math.round(s+1),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
			if (getWorld().getFeatureAt(tiletop[0],tiletop[1]) == 1)
				throw new CollisionException(this,Orientation.LEFT);
			int h = tiletop[1] - tilebottom[1];
			if (h > 1)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilebottom[0], tilebottom[1] + i) == 1)
						throw new CollisionException(this,Orientation.LEFT);
					}
			}
		}
	}
	
	/**
	 * Update the horizontal position after a horizontal collision with the game world.
	 * @param 	exc
	 * 			The CollisionException that is thrown after the collision.
	 * @param 	s
	 * 			The originally updated horizontal position of the game object.
	 * @return  The new horizontal position of the game object.
	 * 			| getXPosition()
	 * @post	If slimes or sharks collide with impassable terrain, their orientation is changed.
	 */
	protected double fixWorldH(CollisionException exc, double s) {
		if (this instanceof Slime)
		{
			if (getOrientation() == Orientation.RIGHT)
			{
				setOrientation(Orientation.LEFT);
				setXVelocity(0);
				((Slime)this).setMaxVel(-(Slime.maxSpeed));
				setXAcc(-(Slime.accx));
			}
			else
			{
				setOrientation(Orientation.RIGHT);
				setXVelocity(0);
				((Slime)this).setMaxVel(Slime.maxSpeed);
				setXAcc(Slime.accx);
			}
		}
		if (this instanceof Shark)
		{
			if (getOrientation() == Orientation.RIGHT)
			{
				setOrientation(Orientation.LEFT);
				setXVelocity(0);
				((Shark)this).setMaxVel(-(Shark.maxSpeed));
				setXAcc(-(Shark.accx));
			}
			else
			{
				setOrientation(Orientation.RIGHT);
				setXVelocity(0);
				((Shark)this).setMaxVel(Shark.maxSpeed);
				setXAcc(Shark.accx);
			}
		}
		return getXPosition();
	}
	
	/**
	 * Check whether the game object horizontally collides with another game object.
	 * @param 	s
	 * 			The updated horizontal position.
	 * @throws 	CollisionException
	 * 			Throws an exception if the object collides with another game object of the game world.
	 */
	protected void CheckCollH(double s) throws CollisionException {
		ArrayList<Plant> plants = getWorld().getAllPlants();
		ArrayList<Slime> slimes = getWorld().getAllSlimes();
		ArrayList<Shark> sharks = getWorld().getAllSharks();
		ArrayList<Mazub> aliens = getWorld().getAllAliens();
		ArrayList<Buzam> buzams = getWorld().getAllBuzams();
		ArrayList<GameObject> allobjects = new ArrayList<GameObject>();
		allobjects.addAll(aliens);
		if (this instanceof Mazub)
			allobjects.addAll(plants);
		allobjects.addAll(sharks);
		allobjects.addAll(slimes);
		allobjects.addAll(buzams);
		allobjects.remove(this);
		if (this instanceof Plant)
		{
			allobjects.removeAll(plants);
			allobjects.removeAll(sharks);
			allobjects.removeAll(slimes);
		}
		for (GameObject obj : allobjects)
		{
			if ((collidesWith(s,getYPosition(),obj)) && ((int) Math.round(s) != (int) Math.round(getXPosition())))
			{
				ArrayList<GameObject> coll = new ArrayList<GameObject>();
				coll.add(obj);
				coll.add(this);
				if (! (getWorld().getCollided().contains(coll)))
				{
					ArrayList<GameObject> coll2 = new ArrayList<GameObject>();
					coll2.add(this);
					coll2.add(obj);
					getWorld().getCollided().add(coll2);
					throw new CollisionException(obj,getOrientation(),false);
				}
				else
					throw new CollisionException(obj,getOrientation(),true);
			}
		}
	}
	
	/**
	 * Update the states of a Plant and an alien after collision.
	 * @param 	mazub
	 * 			The Mazub with which the game object Plant collides.
	 * @param 	plant
	 * 			The Plant with which the game object Mazub collides.
	 * @effect	If Mazub has not reached his maximum number of hitpoints, the plant is terminated with terminate and the number of
	 * 			hitpoints of Mazub is updated with addHitPoints.
	 * 			| mazub.addHitPoints(MazubPlant,Mazub.maxHitPoints)
	 * 			| plant.terminate()
	 */
	private void CollisionMazubPlant(Mazub mazub, Plant plant) {
		if (getHitPoints() < Mazub.maxHitPoints)
		{
			mazub.addHitPoints(MazubPlant,Mazub.maxHitPoints);
			plant.terminate();
		}
	}
	
	/**
	 * Update the states of a Shark and an alien after collision.
	 * @param 	mazub
	 * 			The Mazub with which the game object Shark collides.
	 * @param 	shark
	 * 			The Shark with which the game object Mazub collides.
	 * @param	hit
	 * 			A boolean registering whether the object has to get hit or not (Mazub's bottom perimeter cannot get hit).
	 * @effect	The number of hitpoints of the shark is updated with reduceHitPoints.
	 * 			| shark.reduceHitPoints(MazubShark)
	 * @effect	If Mazub is not immune, his number of hitpoints is updated with reduceHitPoints and his immune time is
	 * 			set with setImmuneTime.
	 * 			| mazub.reduceHitPoints(MazubShark)
	 * 			| mazub.setImmuneTime(immuneDT)
	 */
	private void CollisionMazubShark(Mazub mazub, Shark shark, boolean hit) {
		if ((! mazub.isImmune()) && hit)
		{
			mazub.reduceHitPoints(MazubShark);
			mazub.setImmuneTime(immuneDT);
		}
		shark.reduceHitPoints(MazubShark);
	}
	
	/**
	 * Update the states of a Slime and an alien after collision.
	 * @param 	mazub
	 * 			The Mazub with which the game object Slime collides.
	 * @param 	slime
	 * 			The Slime with which the game object Mazub collides.
	 * @param	hit
	 * 			A boolean registering whether the object has to get hit or not (Mazub's bottom perimeter cannot get hit).
	 * @effect	The number of hitpoints of the slime is updated with reduceHitPoints.
	 * 			| slime.reduceHitPoints(MazubSlime)
	 * @effect	If Mazub is not immune, his number of hitpoints is updated with reduceHitPoints and his immune time is
	 * 			set with setImmuneTime.
	 * 			| mazub.reduceHitPoints(MazubSlime)
	 * 			| mazub.setImmuneTime(immuneDT)
	 */
	private void CollisionMazubSlime(Mazub mazub, Slime slime, boolean hit) {
		if ((! mazub.isImmune()) && hit)
		{
			mazub.reduceHitPoints(MazubSlime);
			mazub.setImmuneTime(immuneDT);
		}
		slime.reduceHitPoints(MazubSlime);
	}
	
	/**
	 * Update the states of a Buzam and a Mazub after collision.
	 * @param 	mazub
	 * 			The Mazub with which the game object Buzam collides.
	 * @param 	buzam
	 * 			The Buzam with which the game object Mazub collides.
	 * @param	hit
	 * 			A boolean registering whether the object has to get hit or not (Mazub's bottom perimeter cannot get hit).
	 * @effect	If the alien is not immunue, the number of hitpoints of the aliens is updated with reduceHitPoints and their 
	 * 			immune time is set with setImmuneTime.
	 * 			| alien.reduceHitPoints(MazubSlime)
	 * 			| alien.setImmuneTime(immuneDT)
	 */
	private void CollisionMazubBuzam(Mazub mazub, Buzam buzam, boolean hit) {
		if (! (mazub instanceof Buzam))
		{
			if ((! mazub.isImmune()) && hit)
			{
				mazub.reduceHitPoints(MazubBuzam);
				mazub.setImmuneTime(immuneDT);
			}
			if ((! mazub.isImmune()) && hit)
			{
				buzam.reduceHitPoints(MazubBuzam);
				buzam.setImmuneTime(immuneDT);
			}
		}
	}
	
	/**
	 * Update the states of a Shark and a Slime after collision.
	 * @param 	shark
	 * 			The Shark with which the game object Slime collides.
	 * @param 	slime
	 * 			The Slime with which the game object Shark collides.
	 * @effect	The number of hitpoints of the shark is updated with reduceHitPoints.
	 * 			| shark.reduceHitPoints(SharkSlime)
	 * @effect	The number of hitpoints of the slime is updated with reduceHitPoints.
	 * 			| slime.reduceHitPoints(SharkSlime)
	 */
	private void CollisionSharkSlime(Shark shark, Slime slime) {
		shark.reduceHitPoints(SharkSlime);
		slime.reduceHitPoints(SharkSlime);
	}
	
	/**
	 * Update the states of a Slime and a Slime after collision.
	 * @param 	slime
	 * 			The Slime with which the game object Slime collides.
	 * @post	The slime of the smaller school joined the larger school of the other slime.
	 * 			| if (getSchool().getSize() < slime.getSchool().getSize())
	 * 			| then new.getSchool() == slime.getSchool()
	 */
	private void CollisionSlimeSlime(Slime slime) {
		if (((Slime)this).getSchool() != slime.getSchool())
		{
			int size1 = ((Slime)this).getSchool().getSize();
			int size2 = slime.getSchool().getSize();
			if (size1 < size2)
			{
				((Slime)this).getSchool().removeSlime(((Slime)this));
				slime.getSchool().addSlime(((Slime)this));
			}
			else if (size2 < size1)
			{
				slime.getSchool().removeSlime(slime);
				((Slime)this).getSchool().addSlime(slime);
			}
		}
		((Slime)this).setTimer(0);
		((Slime)this).resetTimeSlot();
	}
	
	/**
	 * Update the state of the game object after a horizontal collision with another game object.
	 * @param 	exc
	 * 			The CollisionException that is thrown after the collision.
	 * @param 	s
	 * 			The originally updated horizontal position of the game object.
	 * @return  The new horizontal position of the game object.
	 * @effect	If the object is not a plant, the new position is calculated with collH.
	 * 			| collH(exc,s)
	 * @effect	If two objects collide, their state is updated with Collision_Obj1_Obj2.
	 * 			| CollisionMazubPlant((Mazub)this,(Plant)obj)
	 */
	protected double fixCollH(CollisionException exc, double s) {
		GameObject obj = exc.getObject();
		if (this instanceof Mazub)
		{
			if (obj instanceof Plant)
				CollisionMazubPlant((Mazub)this,(Plant)obj);
			else if (obj instanceof Shark)
				CollisionMazubShark((Mazub)this,(Shark)obj,true);
			else if (obj instanceof Slime)
				CollisionMazubSlime((Mazub)this,(Slime)obj,true);
			else if (obj instanceof Buzam)
				CollisionMazubBuzam((Mazub)this,(Buzam)obj,true);
			return collH(exc,s);
		}
		if (this instanceof Shark)
		{
			if (obj instanceof Mazub)
				CollisionMazubShark((Mazub)obj,(Shark)this,true);
			else if (obj instanceof Slime)
				CollisionSharkSlime((Shark)this,(Slime)obj);
			return collH(exc,s);
		}
		if (this instanceof Slime)
		{
			if (obj instanceof Mazub)
				CollisionMazubSlime((Mazub)obj,(Slime)this,true);
			else if (obj instanceof Shark)
				CollisionSharkSlime((Shark)obj,(Slime)this);
			else if (obj instanceof Slime)
				CollisionSlimeSlime((Slime)obj);
			return collH(exc,s);
		}
		return s;
	}
	
	/**
	 * Update the horizontal position after a horizontal collision with another game object.
	 * @param 	exc
	 * 			The CollisionException that is thrown after the collision.
	 * @param 	s
	 * 			The originally updated horizontal position of the game object.
	 * @return	The new horizontal position.
	 * 			| if (obj instanceof Plant)
	 *			| 	return s;
	 *			| else 
	 *			| 	return getXPosition();
	 *@post		If two slimes or two sharks collide, their orientation is changed away from the collision.
	 */
	protected double collH(CollisionException exc, double s) {
		GameObject obj = exc.getObject();
		if ((this instanceof Slime) && (obj instanceof Slime))
		{
			if (getOrientation() == Orientation.RIGHT)
			{
				setOrientation(Orientation.LEFT);
				setXVelocity(0);
				((Slime)this).setMaxVel(-(Slime.maxSpeed));
				setXAcc(-(Slime.accx));
				if (obj.getOrientation() == Orientation.LEFT)
				{
					obj.setOrientation(Orientation.RIGHT);
					obj.setXVelocity(0);
					((Slime)obj).setMaxVel(Slime.maxSpeed);
					obj.setXAcc(Slime.accx);	
				}
			}
			else
			{
				setOrientation(Orientation.RIGHT);
				setXVelocity(0);
				((Slime)this).setMaxVel(Slime.maxSpeed);
				setXAcc(Slime.accx);
				if (obj.getOrientation() == Orientation.RIGHT)
				{
					obj.setOrientation(Orientation.LEFT);
					obj.setXVelocity(0);
					((Slime)obj).setMaxVel(-(Slime.maxSpeed));
					obj.setXAcc(-(Slime.accx));	
				}
			}
		}
		else if ((this instanceof Shark) && (obj instanceof Shark))
		{
			if (getOrientation() == Orientation.RIGHT)
			{
				setOrientation(Orientation.LEFT);
				setXVelocity(0);
				((Shark)this).setMaxVel(-(Shark.maxSpeed));
				setXAcc(-(Shark.accx));
				if (obj.getOrientation() == Orientation.LEFT)
				{
					obj.setOrientation(Orientation.RIGHT);
					obj.setXVelocity(0);
					((Shark)obj).setMaxVel(Shark.maxSpeed);
					obj.setXAcc(Shark.accx);	
				}
			}
			else
			{
				setOrientation(Orientation.RIGHT);
				setXVelocity(0);
				((Shark)this).setMaxVel(Shark.maxSpeed);
				setXAcc(Shark.accx);
				if (obj.getOrientation() == Orientation.RIGHT)
				{
					obj.setOrientation(Orientation.LEFT);
					obj.setXVelocity(0);
					((Shark)obj).setMaxVel(-(Shark.maxSpeed));
					obj.setXAcc(-(Shark.accx));	
				}
			}
		}
		if (obj instanceof Plant)
			return s;
		else 
			return getXPosition();
		
	}
	
	/**
	 * Check whether the game object vertically collides with the game world.
	 * @param 	h
	 * 			The new vertical position.
	 * @throws 	CollisionException
	 * 			Throws an exception if the object collides with impassable terrain of the game world.
	 */
	protected void CheckWorldV(double h) throws CollisionException {
		if (getYVelocity() > 0) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()+1),(int) Math.round(h+getCurrentSprite().getHeight()-2));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,true);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),
					(int) Math.round(h+getCurrentSprite().getHeight()-2));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,true);
			int dh = tilepos2[0] - tilepos1[0];
			if (dh > 1)
			{
				for (int i = 1; i < dh; i++) {
					if (getWorld().getFeatureAt(tilepos1[0] + i, tilepos1[1]) == 1)
						throw new CollisionException(this,true);
					}
			}
		}
		if (getYVelocity() <= 0) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()+1),(int) Math.round(h+1));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,false);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(h+1));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,false);
			int dh = tilepos2[0] - tilepos1[0];
			if (dh > 1)
			{
				for (int i = 1; i < dh; i++) {
					if (getWorld().getFeatureAt(tilepos1[0] + i, tilepos1[1]) == 1)
						throw new CollisionException(this,false);
					}
			}
		}
	}
	
	/**
	 * Update the vertical position after a horizontal collision with the game world.
	 * @param 	exc
	 * 			The CollisionException that is thrown after the collision.
	 * @param 	s
	 * 			The originally updated horizontal position of the game object.
	 * @return  The new horizontal position of the game object.
	 * 			| getYPosition()
	 * @post	If the vertical velocity of the game object is positive, it is set to zero.
	 * 			| if (getYVelocity() > 0)
	 * 			| then (new.getYVelocity() == 0)
	 * @post	If the object was moving downward, the vertical velocity and acceleration are set to zero.
	 * 			| if (! exc.getVertical())
	 * 			| then (setYVelocity(0) && setYAcc(0))
	 */
	protected double fixWorldV(CollisionException exc, double h) {
		if ((exc.getVertical()) && (getYVelocity() > 0))
			setYVelocity(0);
		if (! exc.getVertical())
		{
			setYVelocity(0);
			setYAcc(0);
		}
		return getYPosition();
	}
	
	/**
	 * Check whether the game object vertically collides with another game object.
	 * @param 	s
	 * 			The updated vertical position.
	 * @throws 	CollisionException
	 * 			Throws an exception if the object collides with another game object of the game world.
	 */
	protected void CheckCollV(double s) throws CollisionException {
		ArrayList<Plant> plants = getWorld().getAllPlants();
		ArrayList<Slime> slimes = getWorld().getAllSlimes();
		ArrayList<Shark> sharks = getWorld().getAllSharks();
		ArrayList<Mazub> aliens = getWorld().getAllAliens();
		ArrayList<Buzam> buzams = getWorld().getAllBuzams();
		ArrayList<GameObject> allobjects = new ArrayList<GameObject>();
		allobjects.addAll(aliens);
		if (this instanceof Mazub)
			allobjects.addAll(plants);
		allobjects.addAll(sharks);
		allobjects.addAll(slimes);
		allobjects.addAll(buzams);
		allobjects.remove(this);
		if (this instanceof Plant)
		{
			allobjects.removeAll(sharks);
			allobjects.removeAll(slimes);
		}
		for (GameObject obj : allobjects)
		{
			if ((collidesWith(getXPosition(),s,obj)) && ((int) Math.round(s) != (int) Math.round(getYPosition())))
			{
				ArrayList<GameObject> coll = new ArrayList<GameObject>();
				coll.add(obj);
				coll.add(this);
				ArrayList<GameObject> coll2 = new ArrayList<GameObject>();
				coll2.add(this);
				coll2.add(obj);
				if ((! (getWorld().getCollided().contains(coll))) && (! (getWorld().getCollided().contains(coll2))))
				{
					getWorld().getCollided().add(coll2);
					throw new CollisionException(obj,(getYVelocity() > 0),false);
				}
				else
					throw new CollisionException(obj,(getYVelocity() > 0),true);
			}
		}
	}
	
	/**
	 * Update the state of the game object after a vertical collision with another game object.
	 * @param 	exc
	 * 			The CollisionException that is thrown after the collision.
	 * @param 	h
	 * 			The originally updated vertical position of the game object.
	 * @return  The new vertical position of the game object.
	 * @effect	If the object is not a plant, the new position is calculated with collV.
	 * 			| collV(exc,s)
	 * @effect	If two objects collide, their state is updated with Collision_Obj1_Obj2.
	 * 			| CollisionMazubPlant((Mazub)this,(Plant)obj)
	 */
	protected double fixCollV(CollisionException exc, double h) {
		GameObject obj = exc.getObject();
		if (this instanceof Mazub)
		{
			if (obj instanceof Plant)
				CollisionMazubPlant((Mazub)this,(Plant)exc.getObject());
			else if (obj instanceof Shark)
			{
				if (exc.getVertical())
					CollisionMazubShark((Mazub)this,(Shark)obj,true);
				else
					CollisionMazubShark((Mazub)this,(Shark)obj,false);
			}
			else if (obj instanceof Slime)
			{
				if (exc.getVertical())
					CollisionMazubSlime((Mazub)this,(Slime)obj,true);
				else
					CollisionMazubSlime((Mazub)this,(Slime)obj,false);
			}
			else if (obj instanceof Buzam)
			{
				if (exc.getVertical())
					CollisionMazubBuzam((Mazub)this,(Buzam)obj,true);
				else
					CollisionMazubBuzam((Mazub)this,(Buzam)obj,false);
			}
			return collV(exc,h);
		}
		if (this instanceof Shark)
		{
			if (obj instanceof Mazub)
			{
				if (exc.getVertical())
					CollisionMazubShark((Mazub)obj,(Shark)this,false);
				else
					CollisionMazubShark((Mazub)obj,(Shark)this,true);
			}
			else if (obj instanceof Slime)
				CollisionSharkSlime((Shark)this,(Slime)obj);
			else if (obj instanceof Plant)
				return h;
			return collV(exc,h);
		}
		if (this instanceof Slime)
		{
			if (obj instanceof Slime)
				CollisionSlimeSlime((Slime)obj);
			else if (obj instanceof Mazub)
			{
				if (exc.getVertical())
					CollisionMazubSlime((Mazub)obj,(Slime)this,false);
				else
					CollisionMazubSlime((Mazub)obj,(Slime)this,true);
			}
			else if (obj instanceof Shark)
				CollisionSharkSlime((Shark)obj,(Slime)this);
			else if (obj instanceof Plant)
				return h;
			return collV(exc,h);
		}
		return h;
	}
	
	/**
	 * Update the vertical position after a vertical collision with another game object.
	 * @param 	exc
	 * 			The CollisionException that is thrown after the collision.
	 * @param 	h
	 * 			The originally updated vertical position of the game object.
	 * @return 	The new vertical position of the game object.
	 * 			| if (obj instanceof Plant)
	 *			|	return h
	 *			| else 
	 *			| 	return getYPosition()
	 */
	protected double collV(CollisionException exc, double h) {
		GameObject obj = exc.getObject();
		if (obj instanceof Plant)
			return h;
		else 
			return getYPosition();
	}
	
	/**
	 * Return the current sprite of the game object.
	 * @pre 	The length of the sprites array must be 2.
	 * 			| (getSprites().length == 2)
	 * @pre		The array of sprites is not null.
	 * 			| (getSprites() != null)
	 * @return 	The object's current sprite.
	 * 			| if (this.getOrientation() == Orientation.LEFT)
	 *			|	return getSprites()[0]
	 *			| else
	 *			|	return getSprites()[1]
	 * @post 	The current sprite of the object is updated.
	 */
	public Sprite getCurrentSprite() {
		assert (getSprites().length == 2);
		assert (getSprites() != null);
		if (this.getOrientation() == Orientation.LEFT)
			return getSprites()[0];
		else
			return getSprites()[1];
	}
	
	/**
	 * Starts the movement in the given direction.
	 * @param 	orientation
	 * 			The orientation of the movement.
	 */
	public abstract Void startMove(Orientation orientation);
	/**
	 * Ends the movement in the given direction.
	 * @param 	orientation
	 * 			The direction of the movement that needs to be ended.
	 */
	public abstract Void endMove(Orientation orientation);
	/**
	 * Starts jumping.
	 */
	public abstract Void startJump();
	/**
	 * Ends jumping.
	 */
	public abstract Void endJump();
	/**
	 * Starts ducking.
	 */
	public abstract Void startDuck();
	/**
	 * Ends ducking.
	 */
	public abstract Void endDuck();
	
	/**
	 * Advance the timers and update the game object's position and velocity after the given time duration.
	 * @param 	time
	 * 			The time duration between this position and the next one.
	 */
	public void advanceTime(double time) {
	}
}
