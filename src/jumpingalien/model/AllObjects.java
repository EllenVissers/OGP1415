package jumpingalien.model;
import java.util.ArrayList;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.util.Util;

public abstract class AllObjects {
	
	/**
	 * Initialize a new object with given positions and world.
	 * @param 	x
	 * 			The horizontal position of the bottom-left pixel of the object.
	 * @param 	y
	 * 			The vertical position of the bottom-left pixel of the object.
	 * @param 	world
	 * 			The world in which the object is situated.
	 */
	public AllObjects(double x, double y, World world) {
		setXPosition(x);
		setYPosition(y);
		setWorld(world);
		addToAll(this);
	}
	
	/**
	 * Variable registering the horizontal position of the bottom-left pixel of the object.
	 */
	protected double x;
	/**
	 * Variable registering the vertical position of the bottom-left pixel of the object.
	 */
	protected double y;
	/**
	 * Variable registering the world in which the object is situated.
	 */
	protected World world;
	/**
	 * Variable registering the list of all objects.
	 */
	private ArrayList<AllObjects> all = new ArrayList<AllObjects>();
	
	/**
	 * Return the horizontal position of the object.
	 * @return	The horizontal position of the bottom-left pixel of the object.
	 */
	public double getXPosition() {
		return this.x;
	}
	
	/**
	 * Set the horizontal position of the object to the given value.
	 * @param 	newx
	 * 			The new horizontal position.
	 * @pre		The new position must be a valid position.
	 * 			| isValidXPosition(newx)
	 */
	protected void setXPosition(double newx) {
		if ((! isValidXPosition(newx)) && (this instanceof GameObject))
			((GameObject)this).terminate();
		if (isValidXPosition(newx))
			this.x = newx;
	}
	
	/**
	 * Check whether the given position is a valid horizontal position.
	 * @param 	pos
	 * 			The given position.
	 * @return	True if the position is valid.
	 * 			| (pos > 0) && (pos < getWorld().getNbTilesX() * getWorld().getTileSize())
	 */
	protected boolean isValidXPosition(double pos) {
		if (getWorld() == null)
			return true;
		int xmax = getWorld().getNbTilesX() * getWorld().getTileSize();
		return (Util.fuzzyLessThanOrEqualTo(0,pos) && Util.fuzzyLessThanOrEqualTo(pos,xmax-1));
	}
	
	/**
	 * Return the vertical position of the object.
	 * @return	The vertical position of the bottom-left pixel of the object.
	 */
	public double getYPosition() {
		return this.y;
	}
	
	/**
	 * Set the vertical position of the object to the given value.
	 * @param 	newy
	 * 			The new vertical position of the object.
	 * @pre		The new position must be a valid one.
	 * 			| isValidYPosition(newy)
	 */
	protected void setYPosition(double newy) {
		if ((! isValidYPosition(newy)) && (this instanceof GameObject))
			((GameObject)this).terminate();
		if (isValidYPosition(newy))
			this.y = newy;
	}
	
	/**
	 * Check whether the given position is a valid vertical position.
	 * @param 	pos
	 * 			The given position.
	 * @return	True if the position is valid.
	 * 			| (pos >= 0) && (pos < getWorld().getNbTilesY() * getWorld().getTileSize())
	 */
	protected boolean isValidYPosition(double pos) {
		if (getWorld() == null)
			return true;
		int ymax = getWorld().getNbTilesY() * getWorld().getTileSize();
		return (Util.fuzzyLessThanOrEqualTo(0,pos) && Util.fuzzyLessThanOrEqualTo(pos,ymax-1));
	}
	
	/**
	 * Return the world in which the object is situated.
	 * @return	The world in which the object is situated.
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Set the game world to the given world.
	 * @param 	world
	 * 			The new game world.
	 * @post	If the new world is null, the object is removed from the old world.
	 * 			| if (this instanceof Mazub)
	 *			| 	getWorld().getAllAliens().remove(this);
	 *			| else if (this instanceof Plant)
	 *			|	getWorld().getAllPlants().remove(this);
	 *			| else if (this instanceof Shark)
	 *			|	getWorld().getAllSharks().remove(this);
	 *			| else if (this instanceof Slime)
	 *			|	getWorld().getAllSlimes().remove(this);
	 *			| else if (this instanceof Buzam)
	 *			|   getWorld().getAllBuzams().remove(this);
	 */
	protected void setWorld(World newWorld) {
		if (newWorld != getWorld())
		{
			if (newWorld == null)
			{
				if (this instanceof Mazub)
					getWorld().getAllAliens().remove(this);
				else if (this instanceof Plant)
					getWorld().getAllPlants().remove(this);
				else if (this instanceof Shark)
					getWorld().getAllSharks().remove(this);
				else if (this instanceof Slime)
					getWorld().getAllSlimes().remove(this);
				else if (this instanceof Buzam)
					getWorld().getAllBuzams().remove(this);
			}
			this.world = newWorld;
		}
	}
	
	/**
	 * Add the given object to the list of all objects.
	 * @param 	obj
	 * 			The object that has to be added.
	 */
	public void addToAll(AllObjects obj) {
		all.add(obj);
	}
	
	/**
	 * Remove the given object from the list of all objects.
	 * @param 	obj
	 * 			The object that has to be removed.
	 */
	public void removeFromAll(AllObjects obj) {
		all.remove(obj);
	}

	/**
	 * Return the list of all objects.
	 * @return	The list of all objects.
	 */
	public ArrayList<AllObjects> getAll() {
		return all;
	}
	
	/**
	 * Check whether the object is an instance of the given kind.
	 * @param 	kind
	 * 			The kind the object has to be checked against.
	 * @return	True if the object is an instance of the given kind.
	 */
	public boolean isKind(Kind kind) {
		if (kind == Kind.MAZUB)
			return (this instanceof Mazub);
		if (kind == Kind.BUZAM)
			return (this instanceof Buzam);
		if (kind == Kind.SLIME)
			return (this instanceof Slime);
		if (kind == Kind.SHARK)
			return (this instanceof Shark);
		if (kind == Kind.PLANT)
			return (this instanceof Plant);
		if (kind == Kind.TERRAIN)
			return (this instanceof Tile);
		return true;
	}

	//public abstract double getXVelocity();
	//public abstract double getYVelocity();
	//public abstract double getXAcc();
	//public abstract double getYAcc();
	public abstract double getWidth();
	public abstract double getHeight();
}
