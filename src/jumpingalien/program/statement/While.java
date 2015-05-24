package jumpingalien.program.statement;
import java.util.Map;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.DoubleType;
import jumpingalien.program.type.Type;

public class While extends Statement {

	public While(SourceLocation loc, Expression condition, Statement body) {
		super(loc);
		this.condition = condition;
		this.body = body;
		this.whileCounter = 0;
	}
	
	private Expression condition;
	private Statement body;
	private int whileCounter;
	
	private Expression getCondition() {
		return this.condition;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	private int getWhileCounter() {
		return this.whileCounter;
	}
	
	private void setWhileCounter(int c) {
		this.whileCounter = c;
	}
	
	@Override
	public void resetDone() {
		this.setDone(false);
		getBody().resetDone();
	}
	
	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (getBody().isDone())
			getBody().resetDone();
		if (getWhileCounter() == 1)
		{
			try {
				time = getBody().evaluate(globals);
				setWhileCounter(0);
				globals.put("timer",new DoubleType(time));
				getBody().resetDone();
			} catch (BreakException exc) {
				setWhileCounter(1);
				globals.put("timer",new DoubleType());
				throw new BreakException(0);
			}
		}
		while ((Boolean) getCondition().evaluate(globals))
		{
			try {
				time = checkTime(time-0.001); //evaluate condition
			} catch (TerminateException exc) { 
				throw new BreakException(0); // evaluatie niet compleet
			}
			try {
				time = getBody().evaluate(globals);
				setWhileCounter(0);
				globals.put("timer",new DoubleType(time));
				getBody().resetDone();
			} catch (BreakException exc) {
				setWhileCounter(1);
				globals.put("timer",new DoubleType());
				throw new BreakException(0);
			}
		}
		this.setDone(true);
		return time;
	}
}
