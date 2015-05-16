package jumpingalien.model;

public class AllObjects {
	
	public AllObjects(double x, double y, World world) {
		setXPosition(x);
		setYPosition(y);
		setWorld(world);
	}
	
	protected double x;
	protected double y;
	protected World world;
	
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

}
