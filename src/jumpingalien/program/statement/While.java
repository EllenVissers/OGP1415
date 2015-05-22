package jumpingalien.program.statement;
import java.util.List;
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
	
	private Statement getBody() {
		return this.body;
	}
	
	private int getWhileCounter() {
		return this.whileCounter;
	}
	
	private void setWhileCounter(int c) {
		this.whileCounter = c;
	}
	
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		if (getBody() instanceof Break)
			this.getProgram().setWellFormed(false);
		if (getBody() instanceof Sequence){
			List<Statement> statements = ((Sequence) getBody()).getStatements();
			for (Statement s : statements){
				if (s instanceof Break)
					this.getProgram().setWellFormed(false);
			}
		}
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			if (getWhileCounter() == 1)
			{
				try {
					time = getBody().evaluate(globals,counter);
				} catch (BreakException exc) {
					time = exc.getTime();
				}
				try {
					time = checkTime(time,this);
					globals.put("timer",new DoubleType(time));
					setWhileCounter(0);
				} catch (TerminateException exc) {
					globals.put("timer",new DoubleType());
					throw new BreakException(0);
				}
			}
			while ((Boolean)(getCondition().evaluate(globals)))
			{
				try {
					time = getBody().evaluate(globals,counter);
				} catch (BreakException exc) {
					time = exc.getTime();
				}
				try {
					time = checkTime(time-0.001,getBody());
					globals.put("timer",new DoubleType(time));
					resetCounter();
				} catch (TerminateException exc) {
					setWhileCounter(1);
					globals.put("timer",new DoubleType());
					throw new BreakException(0);
				}
			}
		}
		return time;
	}
}
