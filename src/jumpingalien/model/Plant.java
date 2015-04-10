package jumpingalien.model;

//import java.util.ArrayList;
//import java.util.List;

import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
//import jumpingalien.util.Util;
import jumpingalien.model.Orientation;

public class Plant extends GameObject {
	
	public Plant(double posx, double posy, Sprite[] sprites) throws ModelException {
		super(posx,posy,-speed,0,0,0,Orientation.LEFT,sprites,1,null,false);
	}
	
	private final static double speed = 0.5;
	
	private final double timeSwitch = 0.5;

//	@Override
//	protected void setYVelocity(double vel) {
//	}
//	
//	@Override
//	protected void setXAcc(double acc) {
//	}
//
//	@Override
//	protected void setYAcc(double acc) {
//	}
	
	private boolean reachesTimeSwitch(double time) {
		return ((timer + time) >= timeSwitch);
	}
	
	private double timer = 0;
	
//	/**
//	 * Check whether the given time duration is valid.
//	 * @param 	time
//	 * 			The time duration that has to be checked.
//	 * @return  True when the time is valid.
//	 * 			| (time >= 0) && (time < 0.2)
//	 */
//	private boolean isValidTime(double time) {
//		return ((time >= 0) && (time < 0.2));
//	}
//	
////	@Override
////	protected void terminate() {
////		//terminate();
////		//delay van 0.6s
////	}
	
	private void Move(double time) {
		double s;
		// Checken als nieuwe positie buiten wereld: dood, opschuiven, ...
		if (! isTerminated())
		{
			if (reachesTimeSwitch(time))
			{
				double t1 = (timeSwitch - timer);
				double t2 = ((time+timer) - timeSwitch);
				s = this.getXPosition() + 100*(t1-t2)*getXVelocity();
				if (this.getOrientation() == Orientation.RIGHT)
				{
					this.setOrientation(Orientation.LEFT);
					setXVelocity(-speed);
				}
				else
				{
					this.setOrientation(Orientation.RIGHT);
					setXVelocity(speed);
				}
				timer = t2;	
			}
			else
			{
				s = this.getXPosition() + 100*time*getXVelocity();
				timer += time;
			}
			try {
				CheckWorldH(s);
			} catch (CollisionException exc) {
				s = fixWorldH(exc,s);
			}
			try {
				CheckCollH(s);
			} catch (CollisionException exc) {
				s = fixCollH(exc,s);
			}
			setXPosition(s);
		}
		
	}
	
	private void advance(double t) {
		if ( (! isTerminated()) && (t != 0))
			Move(t);
	}
	
	@Override
	public void advanceTime(double time) {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		double dt = getDT(time,getXVelocity(),getYVelocity(),getXAcc(),getYAcc());
		int i = (int) (time/dt);
		double r = time%dt;
		//System.out.println("time: " + time + " dt: " + dt + " i: " + i + " r: " + r);
		for (int p = 0; p<i; p++) {
			advance(dt);
		}
		advance(r);
	}
}
