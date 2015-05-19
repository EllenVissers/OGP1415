package jumpingalien.program.statement;

import java.util.Iterator;
import java.util.function.Function;
import jumpingalien.model.GameObject;
import jumpingalien.model.Orientation;
import jumpingalien.program.statement.Statement;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings("all")
public class ActionStatement<I,O> extends Statement {

	public ActionStatement(SourceLocation location, I obj, Function<I,O> lambda) {
		super(location);
		this.function = lambda;
	}
	
	public ActionStatement(SourceLocation location, I obj, IProgramFactory.Direction direction,Function<I,O> lambda) {
		super(location);
		this.function = lambda;
		this.direction = direction;
		this.obj = obj;
	}
	
	private Function<I,O> function;
	private IProgramFactory.Direction direction;
	private I obj;
	
	public Function<I,O> getFunction() {
		return this.function;
	}
	
	public I getGameObject() {
		return this.obj;
	}
	
	public Orientation getDirection() {
		if (this.direction == IProgramFactory.Direction.RIGHT)
			return Orientation.RIGHT;
		if (this.direction == IProgramFactory.Direction.LEFT)
			return Orientation.LEFT;
		return Orientation.NONE;
	}

	@Override
	public void evaluate() {
		I obj = getGameObject();
		Function<I,O> d = getFunction();
		d.apply(obj);
	}

}
