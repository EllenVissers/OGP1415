package jumpingalien.model;

import jumpingalien.model.School;

import java.util.ArrayList;

import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Slime extends GameObject {

	public Slime(double pos_x, double pos_y, Sprite[] sprites, School school) throws ModelException {
		super(pos_x,pos_y,0,0,-accx,0,Orientation.LEFT,sprites,startHitPoints, null,false);
		setSchool(school);
		setMaxVel(-maxSpeed);
	}
	
	private School school;
	private double maxVel;
	
	private static final double accy = -10;
	
	protected static double accx = 0.7;
	
	protected static double maxSpeed = 2.5;
	
	private static double minDT = 2;
	
	private static double maxDT = 6;
	
	/**
	 * The default value of the amount of hitpoints of a newly created Slime.
	 */
	private static int startHitPoints = 100;
	
	public double getMaxVel() {
		return this.maxVel;
	}
	
	protected void setMaxVel(double vel) {
		this.maxVel = vel;
	}
	
	public School getSchool() {
		return this.school;
	}
	
	public void setSchool(School school) {
		this.school = school;
	}
	
	protected static double timer = 0;
	protected static double timeslot = randomTime();
	
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
	protected void terminate() {
		this.terminated = true;
		this.setWorld(null);
		getSchool().removeSlime(this);
	}
	
	@Override
	protected void reduceHitPoints(int points) {
		if ((getHitPoints() - points) <= 0)
			this.terminate();
		else
			setHitPoints(getHitPoints()-points);
		ArrayList<Slime> slimes = this.getSchool().getSlimes();
		for (Slime slime : slimes)
			if (this != slime)
				slime.setHitPoints(getHitPoints() - 1);
	}
	
	private void Fall(double time) {
		//setXVelocity(0);
		double h = getYPosition() + 100*(getYVelocity()*time + 0.5*accy*time*time);
		try {
			CheckWorldVertical(h);
		} catch (CollisionException exc) {
			h = fixWorldV(exc,h);
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
			s = fixCollH(exc,s);
		}
		setXPosition(s);
	}
	
	private void advance(double t) {
		if (! isTerminated())
		{
			if (! (onGround()))
					Fall(t);
			else
				setYVelocity(0);
			if ((! isTerminated()) && (onGround()))
				Move(t);
			int medium = CheckMedium();
			if (medium == 2)
				touchWater(t);
			else
				timerWater = 0;
			if (medium == 3)
				touchMagma(t);
			else
				timerMagma = 0;	
		}
	}
	
	@Override
	public void advanceTime(double time) {
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
