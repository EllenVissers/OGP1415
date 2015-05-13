package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.statement.Statement;

public class StartRun extends Statement {

	public StartRun(SourceLocation loc, Expression direction) {
		super(loc);
		this.direction = direction;
	}
	
	private Expression direction;
	
	public Expression getDirection() {
		return this.direction;
	}
	
	@Override
	public Statement getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
