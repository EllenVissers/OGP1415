package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

import java.util.Map;

import jumpingalien.program.type.*;
import jumpingalien.program.expression.Constant;

@SuppressWarnings("all")
public class Wait extends Statement {

	public Wait(SourceLocation loc, Expression duration) {
		super(loc);
		this.duration = duration;
	}
	
	private Expression duration;
	
	public Expression getDuration() {
		return this.duration;
	}
	
	@Override
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException{
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			double newtime;
			if (getDuration().evaluate(globals) instanceof Type)
				newtime = time-((Double)((Type) getDuration().evaluate(globals)).getValue());
			else
				newtime = time-((Double) (getDuration().evaluate(globals)));
			try {
				time = checkTime(newtime,this);
				globals.put("timer",new DoubleType(time));
				resetCounter();
			} catch (TerminateException exc) {
				globals.put("timer",new DoubleType());
				throw new BreakException(0);
			}
		}
		return time;
	}

}
