package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class StopRun extends Statement {

	public StopRun(SourceLocation loc, Expression direction) {
		super(loc);
		this.direction = direction;
	}
	
	private Expression direction;
	
	public Expression getDirection() {
		return this.direction;
	}
	
	@Override
	public void evaluate() {
		// TODO Auto-generated method stub
	}

}