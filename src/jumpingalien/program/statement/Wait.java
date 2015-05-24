package jumpingalien.program.statement;
import jumpingalien.model.Plant;
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
		this.timeLeft = duration;
	}
	
	private Expression duration;
	private Expression timeLeft;
	
	private Expression getDuration() {
		return this.duration;
	}
	
	private Expression getTimeLeft() {
		return this.timeLeft;
	}
	
	private void setTimeLeft(Expression t) {
		this.timeLeft = t;
	}
	
	@Override
	public void resetDone() {
		this.setDone(false);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		double duration;
		if (getTimeLeft().evaluate(globals) instanceof Type)
			duration = ((Double)((Type) getTimeLeft().evaluate(globals)).getValue());
		else
			duration = ((Double) (getTimeLeft().evaluate(globals)));
		if (time <= duration)
		{
			setTimeLeft(new Constant<Double>(getSourceLocation(),duration-time));
			globals.put("timer",new DoubleType());
			throw new BreakException(0);
		}
		else
		{
			this.setDone(true);
			setTimeLeft(getDuration());
			globals.put("timer",new DoubleType(time-duration));
			return (time - duration);
		}
	}

}
