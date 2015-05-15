package jumpingalien.program.statement;
import jumpingalien.model.Orientation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.program.Program;
import jumpingalien.program.expression.Constant;
import jumpingalien.model.GameObject;

public class StartRun extends Statement {

	public StartRun(SourceLocation loc, Expression direction) {
		super(loc);
		this.direction = direction;
	}
	
	private Expression direction;
	
	public Expression getDirection() {
		return this.direction;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void evaluate() {
		if (((Constant<Direction>) getDirection()).getValue() == Direction.LEFT)
			((GameObject) Program.self).startMove(Orientation.LEFT);
		else if (((Constant<Direction>) getDirection()).getValue() == Direction.RIGHT)
			((GameObject) Program.self).startMove(Orientation.RIGHT);
	}

}
