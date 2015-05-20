package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.program.Program;
import java.util.Map;
import jumpingalien.program.type.Type;

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
		globals.put("timer",new DoubleType(time-getDuration()));
	}

}
