package jumpingalien.model;
import java.util.Arrays;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.program.Program;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Buzam extends Alien {

	private int startHitPoints = 500;
	private double startVelocity = 1.0;
	private final double maxSpeed = 3.0;
	private int nb;
	private double time1;
	private double time2;
	private static double timelastmovement = 1.0;
	private static double timebetween = 0.075;
	
	public Buzam(double pos_x, double pos_y, Sprite[] sprites)
			throws ModelException {
		super(pos_x, pos_y, sprites);
		setHitPoints(startHitPoints);
		setStartVel(startVelocity);
		setMaxVel(maxSpeed);
	}
	
	public Buzam(double pos_x, double pos_y, Sprite[] sprites, Program program)
			throws ModelException {
		super(pos_x, pos_y, sprites);
		setHitPoints(startHitPoints);
		setStartVel(startVelocity);
		setMaxVel(maxSpeed);
		setProgram(program);
		setNb(nb);
	}
	
	@Basic
	public int getNb() {
		return this.nb;
	}
	
	private void setNb(int i) {
		if (! isValidNb(i))
			i = 0;
		this.nb = i;
	}
	
	private boolean isValidNb(int nb) {
		return ((nb >= 0) && (nb <= ((getSprites().length - 10)/2)));
	}
	
	@Override
	public void advanceTime(double time) {
		if (! (isValidTime(time)))
			throw new ModelException("Invalid time");
		if (getProgram() != null)
		{
			getProgram().execute(getProgram().getGlobalVariables(), time);
			advanceWithDT(time);
		}
	}
	
	public void advanceWithDT(double time) {
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
	
	public void advance(double time) {
		if (! isMoving())
		{
			time2 += time;
			time1 = 0;
		}
		if (isMoving(IProgramFactory.Direction.LEFT) || isMoving(IProgramFactory.Direction.RIGHT) )
		{
			time1 = 0;
			time2 += time;
			Move(time);
		}
		if (isJumping())
		{
			time2 = 0;
			Jump(time);
		}
		else
			Fall(time);
	}

	@Override
	public Void startMove(Orientation orientation) {
		time2 = 0;
		if (orientation == Orientation.RIGHT)
		{
			setXVelocity(startVelocity);
			setXAcc(Alien.accx);
		}
		else
		{
			setXVelocity(-startVelocity);
			setXAcc(-Alien.accx);
		}
		setOrientation(orientation);
		return null;
	}

	@Override
	public Void endMove(Orientation orientation) {
		setXVelocity(0);
		setXAcc(0);
		return null;
	}

	@Override
	public Void startJump() {
		setYVelocity(Alien.startVelY);
		setYAcc(Alien.accy);
		return null;
	}

	@Override
	public Void endJump() {
		if (getYVelocity() > 0)
			setYVelocity(0);
		return null;
	}

	@Override
	public Void startDuck() {
		setMaxVel(maxSpeedDuck);
		return null;
	}

	@Override
	public Void endDuck() {
		setMaxVel(maxSpeed);
		return null;
	}
	
	private boolean isMoving() {
		return (isMoving(Direction.RIGHT) || isMoving(Direction.LEFT));
	}
	
	private boolean canStandUp() {
		int dx = getSprites()[0].getWidth();
		int dy = getSprites()[0].getHeight();
		int[][] tiles = getWorld().getTilePositions((int) Math.round(getXPosition()+1),(int) Math.round(getYPosition()+1),
				(int) Math.round(getXPosition()+dx-2),(int) Math.round(getYPosition()+dy-2));
		for (int[] tile: tiles)
			if (getWorld().getFeatureAt(tile[0],tile[1]) == 1)
			{
				if (getOrientation() == Orientation.RIGHT)
					setMaxVel(maxSpeedDuck);
				else
					setMaxVel(-maxSpeedDuck);
				return false;
			}
		if (getOrientation() == Orientation.RIGHT)
			setMaxVel(maxSpeed);
		else
			setMaxVel(-maxSpeed);
		return true;
	}
	
	private Sprite Next(Sprite[] list) {
		if (time2 > timebetween)
		{
			setNb(getNb()+1);
			time2 -= timebetween;
		}
		return list[getNb()];
	}

	@Basic @Override
	public Sprite getCurrentSprite() {
		assert (getSprites().length%2 == 0);
		assert (getSprites().length >= 10);
		if ((! isMoving()) && ((! isDucking()) && (canStandUp())) && (time1 > timelastmovement) )
		{
			setOrientation(Orientation.NONE);
			return getSprites()[0];
		}
		if ((! isMoving()) && ((isDucking()) || (! canStandUp()) ) && (time1 > timelastmovement) )
		{
			setOrientation(Orientation.NONE);
			return getSprites()[1];
		}
		if ((! isMoving()) && ((! isDucking()) && (canStandUp())) && (getOrientation() == Orientation.RIGHT)) 
		{
			return getSprites()[2];
		}
		if ((! isMoving()) && ((! isDucking()) && (canStandUp())) && (getOrientation() == Orientation.LEFT))
		{
			return getSprites()[3];
		}
		if ((isMovingRight()) && (getYVelocity() != 0) && (! isDucking()))
		{
			return getSprites()[4];
		}
		if ((isMovingLeft()) && (getYVelocity() != 0) && (! isDucking()))
		{
			return getSprites()[5];
		}
		if (((isDucking()) || (! canStandUp())) && ( (isMovingRight())|| ((time1 < timelastmovement) && 
				(getOrientation() == Orientation.RIGHT))))
		{
			return getSprites()[6];
		}
		if (((isDucking()) || (! canStandUp())) && ( (isMovingLeft())|| ((time1 < timelastmovement) && 
				(getOrientation() == Orientation.LEFT))))
		{
			return getSprites()[7];
		}
		int m = (getSprites().length-10)/2;
		if ((isMovingRight()) && (! isJumping()|| (getYPosition() == 0)) && (! isDucking())) 
		{
			Sprite[] sublist = Arrays.copyOfRange(getSprites(),8,8+m+1);
			return Next(sublist);
		}
		if ((isMovingLeft()) && (! isJumping() || (getYPosition() == 0)) && (! isDucking()))
		{
			Sprite[] sublist = Arrays.copyOfRange(getSprites(),9+m,9+2*m+1);
			return Next(sublist);
		}
		return getSprites()[0];
	}
	
}
