package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.DoubleType;
import jumpingalien.program.type.Type;

import java.util.Map;

public class Break extends Statement {

	public Break(SourceLocation loc) {
		super(loc);
	}
	
	public void resetDone() {
		this.setDone(false);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		try {
			time = checkTime(time-0.001);
			this.setDone(true);
			globals.put("timer",new DoubleType(time));
			throw new BreakException(time);
		} catch (TerminateException exc) {
			globals.put("timer",new DoubleType());
			throw new BreakException(0);
		}
	}

}
