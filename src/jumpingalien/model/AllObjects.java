package jumpingalien.model;

import java.util.ArrayList;

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

}
