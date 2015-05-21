package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.DoubleType;
import jumpingalien.program.type.Type;

import java.util.Map;

public class Break extends Statement {

	public Break(SourceLocation loc) {
		super(loc);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals, double time, int counter) throws BreakException {
		if (counter == getStatementCounter())
		{
			double timer = (double) globals.get("timer").getValue();
			globals.put("timer", new DoubleType(timer-0.001));
			resetCounter();
			throw new BreakException(time-0.001);
		}
		return time;
	}

}
