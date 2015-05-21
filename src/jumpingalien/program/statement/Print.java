package jumpingalien.program.statement;

import java.util.Map;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.DoubleType;
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
	public double evaluate(Map<String,Type> globals, double time, int counter) {
		if (counter == getStatementCounter())
		{
			double timer = (double) globals.get("timer").getValue();
			globals.put("timer", new DoubleType(timer-0.001));
			System.out.println(getExpression().evaluate(globals));
			resetCounter();
			return (time-0.001);
		}
		return time;
	}

}
