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
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			try {
				time = checkTime(time-0.001,this);
				globals.put("timer", new DoubleType(time));
				resetCounter();
				throw new BreakException(time);
			} catch (TerminateException exc) {
				globals.put("timer", new DoubleType());
				throw new BreakException(0);
			}
		}
		return time;
	}

}
