package jumpingalien.model;

import jumpingalien.model.School;

import java.util.ArrayList;

import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Slime extends GameObject {

	public Slime(double pos_x, double pos_y, Sprite[] sprites, School school) throws ModelException {
		super(pos_x,pos_y,0,0,-accx,0,Orientation.LEFT,sprites,startHitPoints, null,false,0);
		setSchool(school);
		school.addSlime(this);
		setMaxVel(-maxSpeed);
	}
	
	private School school;
	private double maxVel;
	
	private static final double accy = -10;
	
	protected static final double accx = 0.7;
	
	protected static final double maxSpeed = 2.5;
	
	private static double minDT = 2;
	
	private static double maxDT = 6;
	
	protected static double timer = 0;
	protected static double timeslot = randomTime();
	
	/**
	 * The default value of the amount of hitpoints of a newly created Slime.
	 */
	private static int startHitPoints = 100;
	
	private double getMaxVel() {
		return this.maxVel;
	}
	
	protected void setMaxVel(double vel) {
		this.maxVel = vel;
	}
	
	public School getSchool() {
		return this.school;
	}
	
	protected void setSchool(School school) {
		this.school = school;
	}
	
	protected static double randomTime() {
		return (minDT + Math.random()*(maxDT-minDT));
	}
	
	private boolean reachesMaxSpeed(double time) {
		return ((Math.abs(this.getXVelocity()) + Math.abs(this.getXAcc()*time)) >= maxSpeed);
	}
	
	private double timerWater = 0;
	private double timerMagma = 0;
	
	private void touchWater(double time) {
		timerWater += time;
		int times = (int) Math.floor(timerWater*5);
		timerWater -= times*0.2;
		reduceHitPoints(times*2);
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
	
	@Override
	protected void reduceHitPoints(int points) {
		if ((getHitPoints() - points) <= 0)
		{
			setHitPoints(0);
			terminate();
		}
		else
			setHitPoints(getHitPoints()-points);
		ArrayList<Slime> slimes = getSchool().getSlimes();
		slimes.remove(this);
		for (Slime slime : slimes)
		{
			if ((slime.getHitPoints() - 1) <= 0)
			{
				slime.setHitPoints(0);
				slime.terminate();
			}
			else
				slime.setHitPoints(slime.getHitPoints() - 1);
		}
	}
	
	private void Fall(double time) {
		double h = getYPosition() + 100*(getYVelocity()*time + 0.5*accy*time*time);
		try {
			CheckWorldV(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
		}
		try {
			CheckCollV(h);
		} catch (CollisionException exc) {
			if (exc.getCollided())
				h = collV(exc,h);
			else
				h = fixCollV(exc,h);
		}
		setYPosition(h);
		setYVelocity(getYVelocity() + accy*time);
	}
	
	private void Move(double time) {
		double s;
		if ((timer+time) >= timeslot)
		{
			double t1 = timeslot - timer;
			double t2 = time - t1;
			double t3; double t4;
			if (reachesMaxSpeed(t1))
			{
				t3 = (this.getMaxVel() - this.getXVelocity())/(getXAcc());
				t4 = t1 - t3;
				s = (this.getXPosition() + 100*(t3*this.getXVelocity() + 0.5*(t3*t3 - t2*t2)*this.getXAcc() + 
						t4*this.getMaxVel()));
			}
			else
				s = (this.getXPosition() + 100*(t1*this.getXVelocity() + 0.5*(t1*t1 - t2*t2)*this.getXAcc()));
			if (this.getOrientation() == Orientation.RIGHT)
			{
				this.setXVelocity(-t2*accx);
				this.setMaxVel(-maxSpeed);
				this.setXAcc(-accx);
				this.setOrientation(Orientation.LEFT);
			}
			else
			{
				this.setXVelocity(t2*accx);
				this.setMaxVel(maxSpeed);
				this.setXAcc(accx);
				this.setOrientation(Orientation.RIGHT);
			}
			timer = t2;
			timeslot = randomTime();
		}
		else
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
			if (exc.getCollided())
				s = collH(exc,s);
			else
				s = fixCollH(exc,s);
		}
		setXPosition(s);
	}
	
	private void advance(double t) {
		if (isTerminated())
		{
			setTerminatedTime(getTerminatedTime() + t);
			if (Util.fuzzyGreaterThanOrEqualTo(getTerminatedTime(), 0.6))
				{
				getSchool().removeSlime(this);
				setWorld(null);
				}
		}
		else
		{
			Move(t);
			setTerminatedTime(0);
			if (onGround() || onGameObject())
				setYVelocity(0);
			else
				Fall(t);
			ArrayList<Integer> medium = CheckMedium();
			if (medium.contains(2))
				touchWater(t);
			else
				timerWater = 0;
			if (medium.contains(3))
				touchMagma(t);
			else
				timerMagma = 0;	
		}
	}
	
	@Override
	public void advanceTime(double time) {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
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
	
}
