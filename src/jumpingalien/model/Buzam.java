package jumpingalien.model;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.program.program.Program;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

public class Buzam extends Alien {

	private int startHitPoints = 500;
	private double startVelocity = 1.0;
	private final double maxSpeed = 3.0;
	
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
		if (isMoving(IProgramFactory.Direction.LEFT) || isMoving(IProgramFactory.Direction.RIGHT) )
			Move(time);
		if (isJumping())
			Jump(time);
		else
			Fall(time);
	}

	@Override
	public Void startMove(Orientation orientation) {
		return null;
	}

	@Override
	public Void endMove(Orientation orientation) {
		return null;
	}

	@Override
	public Void startJump() {
		return null;
	}

	@Override
	public Void endJump() {
		return null;
	}

	@Override
	public Void startDuck() {
		return null;
	}

	@Override
	public Void endDuck() {
		return null;
	}

	@Override
	public Sprite getCurrentSprite() {
		return null;
	}
	
}
