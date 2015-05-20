package jumpingalien.program.statement;

import java.util.Map;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.Type;

public class Print extends Statement {

	public Print(SourceLocation loc, Expression expr) {
		super(loc);
		this.expr = expr;
	}
	
	private Expression expr;
	
	public Expression getExpression() {
		return this.expr;
	}

	@Override
	public void evaluate(Map<String,Type> globals, double time) {
		System.out.println(getExpression().evaluate(globals));
	}

}
