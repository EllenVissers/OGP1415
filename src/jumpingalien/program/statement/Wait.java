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
	public void evaluate(Map<String,Type> globals, double time) {
		globals.put("timer",new DoubleType(time-((Constant<Double>)getDuration()).evaluate(globals)));
	}

}
