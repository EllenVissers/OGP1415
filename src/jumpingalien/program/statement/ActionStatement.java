package jumpingalien.program.statement;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import jumpingalien.program.expression.Constant;
import jumpingalien.model.GameObject;
import jumpingalien.model.Orientation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.statement.Statement;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.*;

@SuppressWarnings("all")
public class ActionStatement<GameObject,Void> extends Statement {

	public ActionStatement(SourceLocation location, Function<GameObject,Void> lambda) {
		super(location);
		this.function = lambda;
		this.bifunction = null;
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
	
	public Orientation getDirection(Map<String,Type> globals) {
		if (((Constant<IProgramFactory.Direction>)this.direction).evaluate(globals) == IProgramFactory.Direction.RIGHT)
			return Orientation.RIGHT;
		if (((Constant<IProgramFactory.Direction>)this.direction).evaluate(globals) == IProgramFactory.Direction.LEFT)
			return Orientation.LEFT;
		return Orientation.NONE;
	}

	@Override
	public void evaluate(Map<String,Type> globals, double time) {
		GameObject obj = (GameObject)((ObjectType)globals.get("this")).getValue();
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
	}

}
