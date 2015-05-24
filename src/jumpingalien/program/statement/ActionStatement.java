package jumpingalien.program.statement;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import jumpingalien.program.expression.Constant;
import jumpingalien.model.AllObjects;
import jumpingalien.model.GameObject;
import jumpingalien.model.Orientation;
import jumpingalien.model.Plant;
import jumpingalien.model.Shark;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.statement.Statement;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.*;

@SuppressWarnings("all")
public class ActionStatement<GameObject,Void> extends Statement {

	public ActionStatement(SourceLocation location, Function<GameObject,Void> lambda) {
		super(location);
		this.function = lambda;
		this.bifunction = null;
		this.direction = null;
	}
	
	public ActionStatement(SourceLocation location, Expression direction,BiFunction<GameObject,Orientation,Void> lambda) {
		super(location);
		this.bifunction = lambda;
		this.direction = direction;
		this.function = null;
	}
	
	private Function<GameObject,Void> function;
	private BiFunction<GameObject,Orientation,Void> bifunction;
	private Expression direction;
	
	public Function<GameObject,Void> getFunction() {
		return this.function;
	}
	
	public BiFunction<GameObject,Orientation,Void> getBiFunction() {
		return this.bifunction;
	}
	
	@Override
	public void resetDone() {
		this.setDone(false);
	}
	
	private Orientation getDirection(Map<String,Type> globals) {
		Direction dir;
		if (this.direction.evaluate(globals) instanceof Type)
			dir = (Direction) ((Type) this.direction.evaluate(globals)).getValue();
		else
			dir = (Direction) this.direction.evaluate(globals);
		if (dir == IProgramFactory.Direction.RIGHT)
			return Orientation.RIGHT;
		if (dir == IProgramFactory.Direction.LEFT)
			return Orientation.LEFT;
		return Orientation.NONE;
	}

	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		GameObject obj = (GameObject) ((ObjectType)globals.get("this")).getValue();
		try {
			time = checkTime(time-0.001);
			globals.put("timer", new DoubleType(time));
			this.setDone(true);
			if (getBiFunction() == null) {
				Function<GameObject,Void> f = getFunction();
				f.apply(obj);
			}
			else
			{
				Orientation d = getDirection(globals);
				BiFunction<GameObject,Orientation,Void> f = getBiFunction();
				f.apply(obj,d);
			}
		} catch (TerminateException exc) {
			globals.put("timer", new DoubleType());
			throw new BreakException(0);
		}
		return time;
	}

}
