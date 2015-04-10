package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.model.World;
import jumpingalien.util.Util;

import java.util.ArrayList;

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
	protected boolean terminated;
	
	protected boolean isValidXPosition(double pos) {
		if (getWorld() == null)
			return true;
		int xmax = getWorld().getNbTilesX() * getWorld().getTileSize();
		return (Util.fuzzyLessThanOrEqualTo(0,pos) && Util.fuzzyLessThanOrEqualTo(pos,xmax-1));
	}
	
	protected boolean isValidYPosition(double pos) {
		if (getWorld() == null)
			return true;
		int ymax = getWorld().getNbTilesY() * getWorld().getTileSize();
		return (Util.fuzzyLessThanOrEqualTo(0,pos) && Util.fuzzyLessThanOrEqualTo(pos,ymax-1));
	}
	
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
		if (! isValidXPosition(position))
			terminate();
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
		if (! isValidYPosition(position))
			terminate();
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
	
	public Sprite[] getSprites() {
		return this.sprites;
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
		if (world != getWorld())
		{
			if (world == null)
			{
				if (this instanceof Mazub)
					getWorld().getAllAliens().remove(this);
				else if (this instanceof Plant)
					getWorld().getAllPlants().remove(this);
				else if (this instanceof Shark)
					getWorld().getAllSharks().remove(this);
				else if (this instanceof Slime)
				{
					getWorld().getAllSlimes().remove(this);
					((Slime)this).getSchool().removeSlime((Slime) this);
				}
			}
			else
				this.world = world;
		}
	}
	
	public Sprite getCurrentSprite() {
		assert (this.getSprites().length == 2);
		assert (this.getSprites() != null);
		if (this.getOrientation() == Orientation.LEFT)
			return this.sprites[0];
		else
			return this.sprites[1];
	}
	
	protected void terminate() {
		this.terminated = true;
		this.setWorld(null);
		//delay 0.6s
	}
	
	protected boolean isTerminated() {
		return this.terminated;
	}
	
	protected double getDT(double time, double vx, double vy, double ax, double ay) {
		if ((Util.fuzzyEquals(vx, 0)) || (Util.fuzzyEquals(vy, 0)))
			return time;
		return Math.min(0.01/(Math.abs(vx) + Math.abs(ax)*time),0.01/(Math.abs(vy) + Math.abs(ay)*time));
	}
	
	protected double getDT2(double time, double vx, double vy, double ax, double ay) {
		return 0.000009;
	}
	
	public boolean onGround() {
		int[][] bottom = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()),
				(int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(getYPosition()));
		boolean a = false;
		for (int[] pos : bottom)
			if (getWorld().getFeatureAt(pos[0],pos[1]) == 1)
				a = true;
		if (! a)
			return false;
		int[][] tilepos = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
				(int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
		for (int[] pos : tilepos)
			if (getWorld().getFeatureAt(pos[0],pos[1]) == 1)
				return false;
		return true;
	}
	
	public boolean onGameObject() {
		ArrayList<Slime> slimes = getWorld().getAllSlimes();
		ArrayList<Shark> sharks = getWorld().getAllSharks();
		ArrayList<Mazub> aliens = getWorld().getAllAliens();
		ArrayList<GameObject> allobjects = new ArrayList<GameObject>();
		allobjects.addAll(aliens);
		allobjects.addAll(sharks);
		allobjects.addAll(slimes);
		allobjects.remove(this);
		int xmin = (int) Math.round(getXPosition()+1);
		int xmax = (int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2);
		int ymin = (int) Math.round(getYPosition());
		for (int i = 0; i<allobjects.size(); i++)
		{
			GameObject obj = allobjects.get(i);
			int xomin = (int) Math.round(obj.getXPosition()+1);
			int xomax = (int) Math.round(obj.getXPosition()+obj.getCurrentSprite().getWidth()-2);
			int yomax = (int) Math.round(obj.getYPosition()+obj.getCurrentSprite().getHeight()-1);
			if ((ymin == yomax) && (((xmin>=xomin) && (xmin<=xomax)) || ((xmax<=xomax) && (xmax>=xomin)) ))
				return true;
		}
		return false;
	}

	protected void addHitPoints(int points) {
		setHitPoints(getHitPoints() + points);
	}
	
	protected void reduceHitPoints(int points) {
		if ((getHitPoints() - points) <= 0)
			this.terminate();
		else
			setHitPoints(getHitPoints()-points);
	}
	
	protected int CheckMedium() {
		int[][] tiles = getWorld().getTilePositions((int) Math.round(getXPosition()),(int) Math.round(getYPosition()),
				(int) Math.round(getXPosition()+getCurrentSprite().getWidth()-1),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-1));
		for (int[] tile : tiles)
			if (getWorld().getFeatureAt(tile[0],tile[1]) != 1)
				return getWorld().getFeatureAt(tile[0],tile[1]);
		return -1;
	}
	
	protected boolean collidesWith(double xa, double ya, GameObject other) {
		return (!((xa + (getCurrentSprite().getWidth() - 1) < other.getXPosition())
				|| (other.getXPosition() + (other.getCurrentSprite().getWidth()-1) < xa)
				|| (ya + (getCurrentSprite().getHeight() - 1) < other.getYPosition())
				|| (other.getYPosition() + (other.getCurrentSprite().getHeight()-1) < ya) ));
	}
	
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
	
	protected void CheckCollH(double s) throws CollisionException {
		ArrayList<Plant> plants = getWorld().getAllPlants();
		ArrayList<Slime> slimes = getWorld().getAllSlimes();
		ArrayList<Shark> sharks = getWorld().getAllSharks();
		ArrayList<Mazub> aliens = getWorld().getAllAliens();
		ArrayList<GameObject> allobjects = new ArrayList<GameObject>();
		allobjects.addAll(aliens);
		allobjects.addAll(plants);
		allobjects.addAll(sharks);
		allobjects.addAll(slimes);
		if (getOrientation() == Orientation.RIGHT)
		{
			for (GameObject obj : allobjects)
				if (collidesWith(s,getYPosition(),obj))
					if (this != obj)
						throw new CollisionException(obj,Orientation.RIGHT);
		}
		if (getOrientation() == Orientation.LEFT)
		{
			for (GameObject obj : allobjects)
				if (collidesWith(s,getYPosition(),obj))
					if (this != obj)
						throw new CollisionException(obj,Orientation.LEFT);
		}
	}
	
	private void CollisionMazubPlant(Plant plant) {
		if (getHitPoints() < Mazub.maxHitPoints)
		{
			((Mazub)this).addHitPoints(50,Mazub.maxHitPoints);
			plant.terminate();
		}
	}
	
	private void CollisionMazubShark(Shark shark) {
		if (! ((Mazub)this).isImmune())
		{
			((Mazub)this).reduceHitPoints(50);
			shark.reduceHitPoints(50);
			((Mazub)this).setImmuneTime(0.6);
		}
	}
	
	private void CollisionMazubSlime(Slime slime) {
		if (! ((Mazub)this).isImmune())
		{
			((Mazub)this).reduceHitPoints(50);
			slime.reduceHitPoints(50);
			((Mazub)this).setImmuneTime(0.6);
		}
	}
	
	private void CollisionSharkSlime(Slime slime) {
		((Shark)this).reduceHitPoints(50);
		slime.reduceHitPoints(50);
	}
	
	private void CollisionSlimeSlime(Slime slime) {
		if (((Slime)this).getSchool().getSize() < slime.getSchool().getSize())
		{
			((Slime)this).getSchool().removeSlime((Slime)this);
			slime.getSchool().addSlime((Slime)this);
		}
		else if (((Slime)this).getSchool().getSize() > slime.getSchool().getSize())
		{
			slime.getSchool().removeSlime(slime);
			((Slime)this).getSchool().addSlime(slime);
		}
		if (getOrientation() == Orientation.RIGHT)
		{
			setOrientation(Orientation.LEFT);
			setXVelocity(0);
			((Slime)this).setMaxVel(-(Slime.maxSpeed));
			setXAcc(-(Slime.accx));
			if (slime.getOrientation() == Orientation.LEFT)
			{
				slime.setOrientation(Orientation.RIGHT);
				slime.setXVelocity(0);
				slime.setMaxVel(Slime.maxSpeed);
				slime.setXAcc(Slime.accx);	
			}
		}
		else
		{
			setOrientation(Orientation.RIGHT);
			setXVelocity(0);
			((Slime)this).setMaxVel(Slime.maxSpeed);
			setXAcc(Slime.accx);
			if (slime.getOrientation() == Orientation.RIGHT)
			{
				slime.setOrientation(Orientation.LEFT);
				slime.setXVelocity(0);
				slime.setMaxVel(-(Slime.maxSpeed));
				slime.setXAcc(-(Slime.accx));	
			}
		}
		Slime.timer = 0;
		Slime.timeslot = Slime.randomTime();
	}
	
	protected double fixCollH(CollisionException exc, double s) {
		if (this instanceof Mazub)
		{
			if (exc.getObject() instanceof Mazub)
				s = getXPosition();
			else if (exc.getObject() instanceof Plant)
				CollisionMazubPlant((Plant)exc.getObject());
			else if (exc.getObject() instanceof Shark)
			{
				CollisionMazubShark((Shark)exc.getObject());
				s = getXPosition();
			}
			else if (exc.getObject() instanceof Slime)
			{
				CollisionMazubSlime((Slime)exc.getObject());
				s = getXPosition();
			}
		}
		if (this instanceof Shark)
		{
			if (exc.getObject() instanceof Shark)
				s = getXPosition();
			else if (exc.getObject() instanceof Slime)
			{
				CollisionSharkSlime((Slime)exc.getObject());
				s = getXPosition();
			}
			else if (exc.getObject() instanceof Mazub)
				s = getXPosition();
		}
		if (this instanceof Slime)
		{
			if (exc.getObject() instanceof Slime)
			{
				CollisionSlimeSlime((Slime)exc.getObject());
				s = getXPosition();
			}
			else if ((exc.getObject() instanceof Mazub) || (exc.getObject() instanceof Shark))
				s = getXPosition();
		}
		return s;
	}
	
	protected void CheckWorldVertical(double s) throws CollisionException {
		if (getYVelocity() > 0) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()+1),(int) Math.round(s+getCurrentSprite().getHeight()-2));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,true);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),
					(int) Math.round(s+getCurrentSprite().getHeight()-2));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,true);
			int h = tilepos2[0] - tilepos1[0];
			if (h > 1)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilepos1[0] + i, tilepos1[1]) == 1)
						throw new CollisionException(this,true);
					}
			}
		}
		if (getYVelocity() <= 0) 
		{
			int[] tilepos1 = getWorld().getTile((int) Math.round(getXPosition()+1),(int) Math.round(s+1));
			if (getWorld().getFeatureAt(tilepos1[0],tilepos1[1]) == 1)
				throw new CollisionException(this,false);
			int[] tilepos2 = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(s+1));
			if (getWorld().getFeatureAt(tilepos2[0],tilepos2[1]) == 1)
				throw new CollisionException(this,false);
			int h = tilepos2[0] - tilepos1[0];
			if (h > 1)
			{
				for (int i = 1; i < h; i++) {
					if (getWorld().getFeatureAt(tilepos1[0] + i, tilepos1[1]) == 1)
						throw new CollisionException(this,false);
					}
			}
		}
	}
	
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
	
	protected void CheckCollV(double s) throws CollisionException {
		ArrayList<Plant> plants = getWorld().getAllPlants();
		ArrayList<Slime> slimes = getWorld().getAllSlimes();
		ArrayList<Shark> sharks = getWorld().getAllSharks();
		ArrayList<Mazub> aliens = getWorld().getAllAliens();
		ArrayList<GameObject> allobjects = new ArrayList<GameObject>();
		allobjects.addAll(aliens);
		allobjects.addAll(plants);
		allobjects.addAll(sharks);
		allobjects.addAll(slimes);
		if (getYVelocity() > 0)
		{
			for (GameObject obj : allobjects)
				if (collidesWith(getXPosition(),s,obj))
					if (this != obj)
						throw new CollisionException(obj,Orientation.RIGHT);
		}
		if (getYVelocity() <= 0)
		{
			for (GameObject obj : allobjects)
				if (collidesWith(getXPosition(),s,obj))
					if (this != obj)
						throw new CollisionException(obj,Orientation.RIGHT);
		}
	}
	
	protected double fixCollV(CollisionException exc, double h) {
		if (this instanceof Mazub)
		{
			if (exc.getObject() instanceof Mazub)
				h = getYPosition();
			else if (exc.getObject() instanceof Plant)
				CollisionMazubPlant((Plant)exc.getObject());
			else if (exc.getObject() instanceof Shark)
			{
				CollisionMazubShark((Shark)exc.getObject());
				h = getYPosition();
			}
			else if (exc.getObject() instanceof Slime)
			{
				CollisionMazubSlime((Slime)exc.getObject());
				h = getYPosition();
			}
		}
		if (this instanceof Shark)
		{
			if (exc.getObject() instanceof Shark)
				h = getYPosition();
			else if (exc.getObject() instanceof Slime)
			{
				CollisionSharkSlime((Slime)exc.getObject());
				h = getYPosition();
			}
			else if (exc.getObject() instanceof Mazub)
				h = getYPosition();
		}
		if (this instanceof Slime)
		{
			if (exc.getObject() instanceof Slime)
			{
				CollisionSlimeSlime((Slime)exc.getObject());
				h = getYPosition();
			}
			else if ((exc.getObject() instanceof Mazub) || (exc.getObject() instanceof Shark))
				h = getYPosition();
		}
		return h;
	}
	
	/**
	 * Check whether the given time duration is valid.
	 * @param 	time
	 * 			The time duration that has to be checked.
	 * @return  True when the time is valid.
	 * 			| (time >= 0) && (time < 0.2)
	 */
	protected boolean isValidTime(double time) {
		return ((time >= 0) && (time < 0.2));
	}
	
	abstract void advanceTime(double time);
	
}
