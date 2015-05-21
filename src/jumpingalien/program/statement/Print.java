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
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			try {
				time = checkTime(time-0.001,this);
				resetCounter();
				globals.put("timer", new DoubleType(time));
				System.out.println(getExpression().evaluate(globals));
			} catch (TerminateException exc) {
				globals.put("timer", new DoubleType());
				throw new BreakException(0);
			}
		}
		return time;
	}

}
