package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class While extends Statement {

	public While(SourceLocation loc, Expression condition, Statement body) {
		super(loc);
		this.condition = condition;
		this.body = body;
	}
	
	private Expression condition;
	private Statement body;
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	public Statement getResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
