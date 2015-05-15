package jumpingalien.program.statement;
import jumpingalien.model.GameObject;
import jumpingalien.model.Orientation;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.expression.Constant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.program.Program;

public class StopRun extends Statement {

	public StopRun(SourceLocation loc, Expression direction) {
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
			((GameObject) Program.self).endMove(Orientation.LEFT);
		else if (((Constant<Direction>) getDirection()).getValue() == Direction.RIGHT)
			((GameObject) Program.self).endMove(Orientation.RIGHT);
	}

}