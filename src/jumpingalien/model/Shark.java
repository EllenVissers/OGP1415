package jumpingalien.model;

import jumpingalien.util.Sprite;
//import jumpingalien.util.Util;
import jumpingalien.util.ModelException;
//import java.util.Random;

public class Shark extends GameObject {

	public Shark(double posx, double posy, Sprite[] sprites) throws ModelException {
		super(posx,posy,0,0,-accx,0,Orientation.LEFT,sprites,startHitPoints,null,false);
		setMaxVel(-maxSpeed);
		//begin vel/acc adhv positie van shark
	}
	
	/**
	 * The default value of the amount of hitpoints of a newly created Shark.
	 */
	private static int startHitPoints = 100;
	
	
	private double accy = -10;
	
	protected static double accx = 1.5;
	
	protected static double maxSpeed = 4;
	
	//private double startVelX = 1.0;

	private double startVelY = 2.0;

	private double minDT = 1;
	
	private double maxDT = 4;

	private double minAccy = -0.2;
	
	private double maxAccy = 0.2;
	
	private double maxVel;
//	
//	@Override
//	protected void setXVelocity(double vel) {
//	}
	
	public double getMaxVel() {
		return this.maxVel;
	}
	
	protected void setMaxVel(double vel) {
		this.maxVel = vel;
	}
//
//	private boolean isValidTime(double time) {
//		return ((time >= 0) && (time < 0.2));
//	}
	
	private double randomTime() {
		return (minDT + Math.random()*(maxDT-minDT));
	}
	
	private double randomAccY() {
		return (minAccy + Math.random()*(maxAccy-minAccy));
	}
	
	private int randomSignal() {
		return (int)(Math.random() * 2);
	}
	
//	@Override
//	protected void terminate() {
//		
//	}
	
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed);
	}
	
	private int lastJump = 0;
	private double timer = 0;
	private double timeslot = randomTime();
	
	private boolean SharkcanJump() {
		int[] left = getWorld().getTile((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()));
		int[] right = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),
				(int) Math.round(getYPosition()));
		int b = right[0]-left[0]+1;
		for (int i = 0; i < b; i++)
			if ( (getWorld().getFeatureAt(left[0]+i,left[1]) == 1) || (getWorld().getFeatureAt(left[0]+i,left[1]) == 2) )
				return true;
		return false;
	}
	
	private boolean isSubmerged() {
		int[][] tileposin = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
				(int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
//		int[] bottomleft = getWorld().getTile((int) Math.round(getXPosition() + 1), (int) Math.round(getYPosition() + 1));
//		int[] topright = getWorld().getTile((int) Math.round(getXPosition()+getCurrentSprite().getWidth()-2),
//				(int) Math.round(getYPosition()+getCurrentSprite().getHeight()-2));
//		int b = topright[0] - bottomleft[0] + 1;
//		int h = topright[1] - bottomleft[1] + 1;
//		for (int i = 0; i<b; i++)
//			for (int j = 0; j<h; j++)
//				if (getWorld().getFeatureAt(bottomleft[0] + i,bottomleft[1] + j) != 2)
//					return false;
//		return true;
		for (int[] pos : tileposin)
			if (getWorld().getFeatureAt(pos[0],pos[1]) != 2)
				 return false;
		return true;
	}
	
	private void Jump(double time) {
		lastJump = 5;
		if (getYVelocity() == 0)
			setYVelocity(startVelY);
		//Wat te doen als buiten wereld: binnen wereld zetten of dood?
		double h = this.getYPosition() + 0.5*accy*time*time + getYVelocity()*time;
		setYVelocity(getYVelocity()+accy*time);
		try {
			CheckWorldVertical(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			h = fixCollV(exc,h);
		}
		setYPosition(h);
	}
	
	private void Swim(double time) {
		//Zolang dat volledig in water is, anders accy = 0.
		if (getYAcc() == 0)
			setYAcc(randomAccY());
		double h = getYPosition() + 100*(0.5*getYAcc()*time*time + getYVelocity()*time);
		setYVelocity(getYVelocity()+getYAcc()*time);
		try {
			CheckWorldVertical(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			h = fixCollV(exc,h);
		}
		setYPosition(h);
	}
	
	private void Fall(double time) {
		//Vanaf het moment dat hij in water is, niet meer vallen!
		double h = getYPosition() + 100*(getYVelocity()*time + accy*0.5*time*time);
		setYVelocity(getYVelocity()+accy*time);
		try {
			CheckWorldVertical(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			h = fixCollV(exc,h);
		}
		setYPosition(h);
	}
	
	private void Move(double time) {
		if (getXAcc() == 0)
			if (randomSignal() == 1)
			{
				setXAcc(accx);
				setOrientation(Orientation.RIGHT);
				setMaxVel(maxSpeed);
			}
			else
			{
				setXAcc(-accx);
				setOrientation(Orientation.LEFT);
				setMaxVel(-maxSpeed);
			}
		// Checken als nieuwe positie buiten wereld: dood, opschuiven, ...
		double s;
		{
			double t1; double t2;
			if (reachesMaxSpeed(time))
			{
				t1 = (this.getMaxVel() - this.getXVelocity())/(getXAcc());
				t2 = time - t1;
				s = (this.getXPosition() + 100*(t1*this.getXVelocity() + 0.5*t1*t1*this.getXAcc() + t2*this.getMaxVel()));
				if (this.getOrientation() == Orientation.RIGHT)
					this.setXVelocity(maxSpeed);
				else
					this.setXVelocity(-maxSpeed);
			}
			else
			{
				s = (this.getXPosition() + 100*(time*this.getXVelocity() + 0.5*time*time*this.getXAcc()));
				this.setXVelocity(this.getXVelocity() + time*this.getXAcc());
			}
			timer = timer + time;
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
	
	private double timerMagma = 0;
	private double timerAir = 0;
	
	private void touchAir(double time) {
		timerAir += time;
		int times = (int) Math.floor(timerAir*5);
		timerAir -= times*0.2;
		reduceHitPoints(times*6);
	}
	
	private void touchMagma(double time) {
		if (timerMagma == 0)
			reduceHitPoints(50);
		else
		{
			timerMagma += time;
			int times = (int) Math.floor(timerMagma*5);
			timerMagma -= times*0.2;
			reduceHitPoints(times*50);
		}
	}
	
	private boolean reachesTimeSlot(double time) {
		return (timer+time >= timeslot);
	}	
	
	private boolean isJumping() {
		return (getYVelocity() > 0);
	}
	
	private void endOfTimeSlot() {
		setYVelocity(0);
		setYAcc(0);
		setXVelocity(0);
		setXAcc(0);
		timeslot = randomTime();
	}
	
	private void advance(double time) {
		if (! isTerminated())
		{
			if (reachesTimeSlot(time))
			{
				double t1 = timeslot - timer;
				double t2 = (timer+time) - timeslot;
				advanceWithTimeSlot(t1);
				endOfTimeSlot();
				advanceWithTimeSlot(t2);
				timer = t2;
			}
			else
			{
				advanceWithTimeSlot(time);
				timer += time;
			}
			int medium = CheckMedium();
			if (medium == 0)
				touchAir(time);
			else
				timerAir = 0;
			if (medium == 3)
				touchMagma(time);
			else
				timerMagma = 0;
		}
	}
	
	private void advanceWithTimeSlot(double time) {
		Move(time);
		if (lastJump > 0)
			lastJump -= 1;
		if ((SharkcanJump()) && (lastJump == 0) && (randomSignal() == 1) )
			Jump(time);
		else if ((! SharkcanJump()) || ((! isSubmerged()) && (! isJumping())))
			Fall(time);
		else if (isSubmerged())
			Swim(time);
	}
	
	@Override
	public void advanceTime(double time) throws ModelException {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		double dt = getDT(time,getXVelocity(),getYVelocity(),getXAcc(),getYAcc());
		int i = (int) (time/dt);
		double r = time%dt;
		for (int p = 0; p<i; p++) {
			advance(dt);
		}
		advance(r);
	}
	
}
