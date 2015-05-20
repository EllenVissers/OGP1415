package jumpingalien.model;

//import java.util.ArrayList;
import jumpingalien.program.program.Program;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Buzam extends Alien {

	private int startHitPoints = 500;
	private int maxHitPoints = 500;
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
		Object p = getProgram();
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
