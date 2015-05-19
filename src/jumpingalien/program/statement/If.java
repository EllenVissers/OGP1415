package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Constant;
import jumpingalien.program.program.Program;

public class If extends Statement {

	public If(SourceLocation loc, Expression condition, Statement ifBody, Statement elseBody) {
		super(loc);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean result;
	
	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public Statement getIfBody() {
		return this.ifBody;
	}
	
	public Statement getElseBody() {
		return this.elseBody;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void evaluate() {
		if (((Constant<Boolean>) getCondition()).evaluate())
		{
			Program.timer -= 0.001;
			if (! (Program.timer < 0))
				getIfBody().evaluate();
		}
		else
		{
			Program.timer -= 0.001;
			if (! (Program.timer < 0))
				getElseBody().evaluate();
		}
	}
	
}
