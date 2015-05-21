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
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	public int getWhileCounter() {
		return this.whileCounter;
	}
	
	public void setWhileCounter(int c) {
		this.whileCounter = c;
	}
	
	public double evaluate(Map<String,Type> globals, double time, int counter) {
		if (counter == getStatementCounter())
		{
			if (getWhileCounter() == 1)
			{
				try {
				time = checkTime(getBody().evaluate(globals,time,counter),getBody());
				} catch(BreakException exc) {
					time = exc.getTime();
				} catch (TerminateException exc) {
				}
			}
			try {
				while ((Boolean)(getCondition().evaluate(globals)))
				{
					double timer = (double) globals.get("timer").getValue();
					globals.put("timer", new DoubleType(timer-0.001));
					time -= 0.001;
					time = checkTime(getBody().evaluate(globals,time,counter),getBody());
				}
			} catch(BreakException exc) {
				time = exc.getTime();
			} catch (TerminateException exc) {
				setWhileCounter(1);
			}
			setWhileCounter(0);
			resetCounter();
		}
		return time;
	}
}
