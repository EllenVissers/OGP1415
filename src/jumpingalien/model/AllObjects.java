package jumpingalien.model;
import java.util.ArrayList;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.IProgramFactory.Kind;

public abstract class AllObjects {
	
	public AllObjects(double x, double y, World world) {
		setXPosition(x);
		setYPosition(y);
		setWorld(world);
		addToAll(this);
	}
	
	protected double x;
	protected double y;
	protected World world;
	private ArrayList<AllObjects> all = new ArrayList<AllObjects>();
	
	public double getXPosition() {
		return this.x;
	}
	
	protected void setXPosition(double newx) {
		this.x = newx;
	}
	
	public double getYPosition() {
		return this.y;
	}
	
	protected void setYPosition(double newy) {
		this.y = newy;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	protected void setWorld(World newWorld) {
		this.world = newWorld;
	}
	
	public void addToAll(AllObjects obj) {
		all.add(obj);
	}
	
	public void removeFromAll(AllObjects obj) {
		all.remove(obj);
	}

	public ArrayList<AllObjects> getAll() {
		return all;
	}
	
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

	public abstract double getXVelocity();
	public abstract double getYVelocity();
	public abstract double getXAcc();
	public abstract double getYAcc();
	public abstract boolean isTerminated();
	public abstract int getHitPoints();
	public abstract boolean isPassable();
	public abstract boolean isMagma();
	public abstract boolean isAir();
	public abstract boolean isWater();
	public abstract boolean isMoving(IProgramFactory.Direction direction);
	public abstract boolean isDucking();
	public abstract boolean isJumping();
	public abstract double getWidth();
	public abstract double getHeight();
}
