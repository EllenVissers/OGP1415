package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.BinaryExpression;
import jumpingalien.program.expression.Constant;

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
	
	public void evaluate() {
		while (((Constant<Boolean>) ((BinaryExpression) getCondition()).getResult()).getValue())
			getBody().evaluate();
	}
}
