package jumpingalien.model;

//import java.util.ArrayList;
//import java.util.List;

import java.util.Collection;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.model.World;
import jumpingalien.util.Util;

public abstract class GameObject {

	public GameObject(double x, double y, double vx, double vy, double ax, double ay, Orientation orientation, 
			Sprite[] sprites, int hitPoints, World world, boolean terminated) throws ModelException {
		if (sprites == null)
			throw new ModelException("Empty array of sprites");
		this.setXPosition(x);
		this.setYPosition(y);
		this.setXVelocity(vx);
		this.setYVelocity(vy);
		this.setXAcc(ax);
		this.setYAcc(ay);
		this.setOrientation(orientation);
		this.sprites = sprites;
		this.setHitPoints(hitPoints);
		this.setWorld(world);
		this.terminated = false;
	}
	
	private double x;
	private double y;
	private double vx;
	private double vy;
	private double ax;
	private double ay;
	private Orientation orientation;
	private Sprite[] sprites;
	private World world;
	private int hitPoints;
	//private double maxVel;
	//private double startVel;
	private boolean terminated;
	
	/**
	 * Return Mazub's current horizontal position.
	 * @return 	The horizontal position.
	 * 			| this.pos_x
	 */
	@Basic
	public double getXPosition() {
		return this.x;
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
	protected void setXPosition(double position) throws ModelException {
		//if (! isValidXPosition(position))
			//throw new ModelException("Invalid horizontal position");
		this.x = position;
	}
	
	/**
	 * Return Mazub's current vertical position.
	 * @return	The verical position.
	 * 			| this.pos_y
	 */
	@Basic
	public double getYPosition() {
		return this.y;
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
	protected void setYPosition(double position) {
		//if (! isValidYPosition(position))
			//throw new ModelException("Invalid vertical position");
		this.y = position;
	}
	
	/**
	 * Return Mazub's current horizontal velocity.
	 * @return	The horizontal velocity of Mazub.
	 * 			|this.vel_x
	 */
	@Basic
	public double getXVelocity() {
		return this.vx;
	}
	
	/**
	 * Set the horizontal velocity of Mazub to the given velocity.
	 * @param 	velocity
	 * 			The new velocity of Mazub.
	 * @post 	Mazub's horizontal velocity is updated to the given velocity.
	 * 			| new.vel_x = velocity
	 */
	protected void setXVelocity(double velocity) {
		this.vx = velocity;
	}
	
	/**
	 * Return Mazub's current vertical velocity.
	 * @return	The vertical velocity.
	 * 			| this.vel_y
	 */
	@Basic
	public double getYVelocity() {
		return this.vy;
	}
	
	/**
	 * Set the vertical velocity of Mazub to the given velocity.
	 * @param 	velocity
	 * 			The new velocity of Mazub.
	 * @post 	Mazub's vertical velocity is updated to the given velocity.
	 * 			| new.vel_y = velocity
	 */
	protected void setYVelocity(double velocity) {
		this.vy = velocity;
	}
	
	/**
	 * Return Mazub's current horizontal acceleration.
	 * @return	The horizontal acceleration of Mazub.
	 * 			|this.acc_x
	 */
	@Basic
	public double getXAcc() {
		return this.ax;
	}
	
	/**
	 * Set the horizontal acceleration of Mazub to the given acceleration.
	 * @param 	acceleration
	 * 			The new acceleration of Mazub.
	 * @post 	Mazub's horizontal acceleration is updated to the given acceleration.
	 * 			| new.acc_x = acceleration
	 */
	protected void setXAcc(double acceleration) {
		this.ax = acceleration;
	}
	
	/**
	 * Return Mazub's current vertical acceleration.
	 * @return	The vertical acceleration.
	 * 			| this.acc_y
	 */
	@Basic
	public double getYAcc() {
		return this.ay;
	}
	
	/**
	 * Set the vertical acceleration of Mazub to the given acceleration.
	 * @param 	acceleration
	 * 			The new acceleration of Mazub.
	 * @post 	Mazub's vertical acceleration is updated to the given acceleration.
	 * 			| new.acc_y = acceleration
	 */
	protected void setYAcc(double acceleration) {
		this.ay = acceleration;
	}
	
	protected Sprite[] getSprites() {
		return this.sprites;
	}
	
//	/**
//	 * Returns the value of the begin velocity of Mazub.
//	 * @return 	Mazub's begin velocity.
//	 * 			| this.start_vel
//	 */
//	@Basic
//	public double getStartVel()
//	{
//		return this.startVel;
//	}
//	
//	/**
//	 * Set the begin velocity of Mazub to a given velocity.
//	 * @param 	vel
//	 * 			The new begin velocity of Mazub.
//	 * @pre 	The start velocity is a valid start velocity.
//	 * 			| isValidStartVelocity(vel)
//	 * @post 	Mazub's start velocity is updated to the given velocity.
//	 * 			| new.start_vel = vel
//	 */
//	protected void setStartVel(double vel) {
//		//assert isValidStartVelocity(vel);
//		this.startVel = vel;
//	}
//	
//	/**
//	 * Return the value of the maximum velocity of Mazub.
//	 * @return 	Mazub's maximum velocity.
//	 * 			| this.max_vel
//	 */
//	@Basic
//	public double getMaxVel() {
//		return this.maxVel;
//	}
//	
//	/**
//	 * Set the maximun velocity of Mazub to a given velocity.
//	 * @param 	vel
//	 * 			The new maximum velocity of Mazub.
//	 * @pre 	The given velocity is a valid maximum velocity.
//	 * 			| this.isValidMaxVelocity(vel)
//	 * @post 	Mazub's maximum velocity is updated to the given velocity.
//	 * 			| new.max_vel = vel
//	 */
//	protected void setMaxVel(double vel) {
//		//assert this.isValidMaxVelocity(vel);
//		this.maxVel = vel;
//	}
	
	/**
	 * Get the orientation of Mazub.
	 * @return 	The orientation of Mazub.
	 * 			| this.orientation
	 */
	@Basic
	protected Orientation getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Set the orientation of Mazub to the given orientation.
	 * @param 	orientation
	 * 			The new orientation of Mazub.
	 * @post 	Mazub's orientation is updated to the given orientation.
	 * 			| new.orientation = orientation
	 */
	protected void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * 
	 * @param points
	 */
	protected void setHitPoints(int points) {
		this.hitPoints = points;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	protected void setWorld(World world) {
		this.world = world;
	}
	
	public Sprite getCurrentSprite() {
		assert (getSprites().length == 2);
		if (this.getOrientation() == Orientation.LEFT)
			return getSprites()[0];
		else
			return getSprites()[1];
	}
	
	protected void terminate() {
		this.terminated = true;
	}
	
	protected boolean isTerminated() {
		return this.terminated;
	}
	
	protected void CheckWorld(double s) throws ImpassableTerrainException {
		if (getOrientation() == Orientation.RIGHT) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()),(int) Math.round(getYPosition()));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new ImpassableTerrainException(this,Orientation.RIGHT);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()),
					(int) Math.round(getYPosition()+getCurrentSprite().getHeight()));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new ImpassableTerrainException(this,Orientation.RIGHT);
			else
			{
				int h = tilepos2[1] - tilepos1[1];
				if (h < 2)
					this.setXPosition(s);
				else
				{
					for (int i = 1; i < h; i++) {
						if (getWorld().getFeatureAt(tilepos1[0], tilepos1[1] + i) == 1)
							throw new ImpassableTerrainException(this,Orientation.RIGHT);
					}
					this.setXPosition(s);
				}
			}
		}
		if (getOrientation() == Orientation.LEFT)
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()),(int) Math.round(getYPosition()));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new ImpassableTerrainException(this,Orientation.LEFT);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new ImpassableTerrainException(this,Orientation.LEFT);
			else
			{
				int h = tilepos2[1] - tilepos1[1];
				if (h < 2)
					this.setXPosition(s);
				else
				{
					for (int i = 1; i < h; i++) {
						if (getWorld().getFeatureAt(tilepos1[0], tilepos1[1] + i) == 1)
							throw new ImpassableTerrainException(this,Orientation.LEFT);
					}
					this.setXPosition(s);
				}
			}
		}
	}
	
	protected double getDT(double time, double vx, double vy, double ax, double ay) {
		if ((Util.fuzzyEquals(vx, 0)) || (Util.fuzzyEquals(vy, 0)))
			return time;
		return Math.min(0.01/(Math.abs(vx) + Math.abs(ax)*time),0.01/(Math.abs(vy) + Math.abs(ay)*time));
	}
	
	private void CheckWorldVertical(double s) throws CollisionException {
		if (getYVelocity() > 0) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,true);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()),
					(int) Math.round(getYPosition()+getCurrentSprite().getHeight()));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,true);
			int h = tilepos2[0] - tilepos1[0];
			if (h >= 2)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilepos1[0] + i, tilepos1[1]) == 1)
						throw new CollisionException(this,true);
					}
			}
		}
		if (getYVelocity() < 0) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()),(int) Math.round(getYPosition()));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,false);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()) + getCurrentSprite().getWidth(),(int) Math.round(getYPosition()));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,false);
			int h = tilepos2[0] - tilepos1[0];
			if (h >= 2)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilepos1[0] + i, tilepos1[1]) == 1)
						throw new CollisionException(this,false);
					}
			}
		}
	}
	
	private void CheckWorldHorizontal(double s) throws CollisionException {
		if (getOrientation() == Orientation.RIGHT) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(s+getCurrentSprite().getWidth()),(int) Math.round(getYPosition()));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,Orientation.RIGHT);
			int[] tilepos2 = getWorld().getTile((int) Math.round(s+getCurrentSprite().getWidth()),
					(int) Math.round(getYPosition()+getCurrentSprite().getHeight()));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,Orientation.RIGHT);
			int h = tilepos2[1] - tilepos1[1];
			if (h>=2)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilepos1[0], tilepos1[1] + i) == 1)
						throw new CollisionException(this,Orientation.RIGHT);
				}
			}
		}
		if (getOrientation() == Orientation.LEFT)
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(s),(int) Math.round(getYPosition()));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,Orientation.LEFT);
			int[] tilepos2 = getWorld().getTile((int) Math.round(s),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,Orientation.LEFT);
			int h = tilepos2[1] - tilepos1[1];
			if (h >= 2)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilepos1[0], tilepos1[1] + i) == 1)
						throw new CollisionException(this,Orientation.LEFT);
					}
			}
		}
	}
	
	protected void CheckCollisionH(double s) throws CollisionException {
		CheckWorldHorizontal(s);
		CheckCollisionHorizontal(s);
		setXPosition(s);
	}
	
	protected void CheckCollisionV(double s) throws CollisionException {
		CheckWorldVertical(s);
		CheckCollisionVertical(s);
		setYPosition(s);
	}
	
	private void CheckCollisionVertical(double s) throws CollisionException {
		Collection<Plant> plants = getWorld().getAllPlants();
		Collection<Slime> slimes = getWorld().getAllSlimes();
		Collection<Shark> sharks = getWorld().getAllSharks();
		Collection<Mazub> aliens = getWorld().getAllAliens();
		double y1 = s;
		double y2 = s+getCurrentSprite().getHeight();
		if (getYVelocity() > 0)
		{
			for (Plant plant : plants)
				// is dit nodig of kan ook: plant != this ??
				if (((this instanceof Plant) && (plant != this)) &&(plant.getYPosition() < y2))
					throw new CollisionException(plant,true);
			for (Slime slime : slimes)
				if (((this instanceof Slime) && (slime != this)) &&(slime.getYPosition() < y2))
					throw new CollisionException(slime,true);
			for (Shark shark : sharks)
				if (((this instanceof Shark) && (shark != this)) && (shark.getYPosition() < y2))
					throw new CollisionException(shark,true);
			for (Mazub alien : aliens)
				if (((this instanceof Mazub) && (alien != this)) && (alien.getYPosition() < y2))
					throw new CollisionException(alien,true);
		}
		if (getYVelocity() < 0)
		{
			for (Plant plant : plants)
				if (((this instanceof Plant) && (plant != this)) &&((plant.getYPosition()+plant.getCurrentSprite().getHeight()) > y1))
					throw new CollisionException(plant,false);
			for (Slime slime : slimes)
				if (((this instanceof Slime) && (slime != this)) &&((slime.getYPosition()+slime.getCurrentSprite().getHeight()) > y1))
					throw new CollisionException(slime,false);
			for (Shark shark : sharks)
				if (((this instanceof Shark) && (shark != this)) &&((shark.getYPosition()+shark.getCurrentSprite().getHeight()) > y1))
					throw new CollisionException(shark,false);
			for (Mazub alien : aliens)
				if (((this instanceof Mazub) && (alien != this)) && ((alien.getYPosition()+alien.getCurrentSprite().getHeight()) > y1))
					throw new CollisionException(alien,false);
		}
	}
	
	private void CheckCollisionHorizontal(double s) throws CollisionException {
		Collection<Plant> plants = getWorld().getAllPlants();
		Collection<Slime> slimes = getWorld().getAllSlimes();
		Collection<Shark> sharks = getWorld().getAllSharks();
		Collection<Mazub> aliens = getWorld().getAllAliens();
		double x1 = s;
		double x2 = s+getCurrentSprite().getWidth();
		if (getOrientation() == Orientation.RIGHT)
		{
			for (Plant plant : plants)
				if (((this instanceof Plant) && (plant != this)) && (plant.getXPosition() < x2))
					throw new CollisionException(plant,Orientation.RIGHT);
			for (Slime slime : slimes)
				if (((this instanceof Slime) && (slime != this)) && (slime.getXPosition() < x2))
					throw new CollisionException(slime,Orientation.RIGHT);
			for (Shark shark : sharks)
				if (((this instanceof Shark) && (shark != this)) && (shark.getXPosition() < x2))
					throw new CollisionException(shark,Orientation.RIGHT);
			for (Mazub alien : aliens)
				if (((this instanceof Mazub) && (alien != this)) && (alien.getXPosition() < x2))
					throw new CollisionException(alien,Orientation.RIGHT);
		}
		if (getOrientation() == Orientation.LEFT)
		{
			for (Plant plant : plants)
				if (((this instanceof Plant) && (plant != this)) && ((plant.getXPosition()+plant.getCurrentSprite().getWidth()) > x1))
					throw new CollisionException(plant,Orientation.LEFT);
			for (Slime slime : slimes)
				if (((this instanceof Slime) && (slime != this)) && ((slime.getXPosition()+slime.getCurrentSprite().getWidth()) > x1))
					throw new CollisionException(slime,Orientation.LEFT);
			for (Shark shark : sharks)
				if (((this instanceof Shark) && (shark != this)) && ((shark.getXPosition()+shark.getCurrentSprite().getWidth()) > x1))
					throw new CollisionException(shark,Orientation.LEFT);
			for (Mazub alien : aliens)
				if (((this instanceof Mazub) && (alien != this)) && ((alien.getXPosition()+alien.getCurrentSprite().getWidth()) > x1))
					throw new CollisionException(alien,Orientation.LEFT);
		}
	}
	
	protected void addHitPoints(int points, int maxHitPoints) {
		if (getHitPoints() < maxHitPoints)
			if ((getHitPoints() + points) > maxHitPoints)
				setHitPoints(maxHitPoints);
			else
				setHitPoints(getHitPoints() + points);
	}
	
	protected void reduceHitPoints(int points) {
		if ((getHitPoints() - points) <= 0)
			this.terminate();
		else
			setHitPoints(getHitPoints()-points);
	}
	
	protected void fixCollisionH(CollisionException exc, double s, int maxHitPoints) {
		if (this != exc.getObject())
		{
			if (exc.getObject() instanceof Plant)
			{
				if ((this instanceof Slime) || (this instanceof Shark) || (this instanceof Plant))
					setXPosition(s);
				if (this instanceof Mazub)
					if (getHitPoints() < maxHitPoints)
					{
						addHitPoints(50,maxHitPoints);
						exc.getObject().terminate();
					}
			}
			if (exc.getObject() instanceof Slime)
			{
				if (this instanceof Plant)
					setXPosition(s);
				if (this instanceof Slime)
				{
					if (((Slime)this).getSchool().getSize() < ((Slime)exc.getObject()).getSchool().getSize())
					{
						((Slime)this).getSchool().removeSlime((Slime)this);
						((Slime)exc.getObject()).getSchool().addSlime((Slime)this);
					}
					else if (((Slime)this).getSchool().getSize() > ((Slime)exc.getObject()).getSchool().getSize())
					{
						((Slime)exc.getObject()).getSchool().removeSlime((Slime)exc.getObject());
						((Slime)this).getSchool().addSlime((Slime)exc.getObject());
					}
				}		
				if (this instanceof Shark)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50); //gebeurt dit nu dubbel (bij de beweging van beide objecten?
				}
				if (this instanceof Mazub)
				{
					reduceHitPoints(50);
					((Mazub) this).setImmune(0.6);
					exc.getObject().reduceHitPoints(50);
				}
			}
			if (exc.getObject() instanceof Shark)
			{
				if (this instanceof Plant)
					setXPosition(s);
				if (this instanceof Slime)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50);
				}
				if (this instanceof Mazub)
				{
					reduceHitPoints(50);
					((Mazub)this).setImmune(0.6);
					exc.getObject().reduceHitPoints(50);
				}
			}
			if (exc.getObject() instanceof Mazub)
			{
				if (this instanceof Plant)
				{
					terminate();
					exc.getObject().addHitPoints(50, maxHitPoints);
				}
				if (this instanceof Slime)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50);
					((Mazub)exc.getObject()).setImmune(0.6);
				}
				if (this instanceof Shark)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50);
					((Mazub)exc.getObject()).setImmune(0.6);
				}
			}
		}
	}
	
	protected void fixCollisionV(CollisionException exc, double s, int maxHitPoints) {
		if (this != exc.getObject())
		{
			if (exc.getObject() instanceof Plant)
			{
				if ((this instanceof Slime) || (this instanceof Shark) || (this instanceof Plant))
					setYPosition(s);
				if (this instanceof Mazub)
					if (getHitPoints() < maxHitPoints)
					{
						addHitPoints(50,maxHitPoints);
						exc.getObject().terminate();
					}
			}
			if (exc.getObject() instanceof Slime)
			{
				if (this instanceof Plant)
					setYPosition(s);
				if (this instanceof Slime)
				{
					if (((Slime)this).getSchool().getSize() < ((Slime)exc.getObject()).getSchool().getSize())
					{
						((Slime)this).getSchool().removeSlime((Slime)this);
						((Slime)exc.getObject()).getSchool().addSlime((Slime)this);
					}
					else if (((Slime)this).getSchool().getSize() > ((Slime)exc.getObject()).getSchool().getSize())
					{
						((Slime)exc.getObject()).getSchool().removeSlime((Slime)exc.getObject());
						((Slime)this).getSchool().addSlime((Slime)exc.getObject());
					}
				}		
				if (this instanceof Shark)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50); //gebeurt dit nu dubbel (bij de beweging van beide objecten?
				}
				if (this instanceof Mazub)
				{
					reduceHitPoints(50);
					((Mazub) this).setImmune(0.6);
					exc.getObject().reduceHitPoints(50);
				}
			}
			if (exc.getObject() instanceof Shark)
			{
				if (this instanceof Plant)
					setYPosition(s);
				if (this instanceof Slime)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50);
				}
				if (this instanceof Mazub)
				{
					reduceHitPoints(50);
					((Mazub)this).setImmune(0.6);
					exc.getObject().reduceHitPoints(50);
				}
			}
			if (exc.getObject() instanceof Mazub)
			{
				if (this instanceof Plant)
				{
					terminate();
					exc.getObject().addHitPoints(50, maxHitPoints);
				}
				if (this instanceof Slime)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50);
					((Mazub)exc.getObject()).setImmune(0.6);
				}
				if (this instanceof Shark)
				{
					reduceHitPoints(50);
					exc.getObject().reduceHitPoints(50);
					((Mazub)exc.getObject()).setImmune(0.6);
				}
			}
		}
		else
		{
			if ((exc.getVertical()) && (getYVelocity() > 0))
				setYVelocity(0);
			if (! exc.getVertical())
			{
				setYVelocity(0);
				setYAcc(0);
			}
				
		}
	}
	
	
}
