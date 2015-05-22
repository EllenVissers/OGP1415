package jumpingalien.program.statement;
import java.util.Map;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.DoubleType;
import jumpingalien.program.type.BoolType;
import jumpingalien.program.type.Type;

public class If extends Statement {

	public If(SourceLocation loc, Expression condition, Statement ifBody, Statement elseBody) {
		super(loc);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean result;
	
	protected boolean getResult() {
		return result;
	}

	protected void setResult(boolean result) {
		this.result = result;
	}
	
	private Expression getCondition() {
		return this.condition;
	}
	
	private Statement getIfBody() {
		return this.ifBody;
	}
	
	private Statement getElseBody() {
		return this.elseBody;
	}

	@Override
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			try {
				time = checkTime(time-0.001,this);
			} catch (TerminateException exc) {
				throw new BreakException(0);
			}
			Boolean cond;
			if (getCondition().evaluate(globals) instanceof Type)
				cond = ((BoolType) getCondition().evaluate(globals)).getValue();
			else
				cond = (Boolean) getCondition().evaluate(globals);
			if (cond)
			{
				try {
					time = getIfBody().evaluate(globals,counter);
				} catch (BreakException exc) {
					time = exc.getTime();
				}
				try {
					time = checkTime(time,this);
					globals.put("timer",new DoubleType(time));
					resetCounter();
				} catch (TerminateException exc) {
					globals.put("timer",new DoubleType());
					throw new BreakException(0);
				}
			}
			else
			{
				if (getElseBody() != null)
				{
					try {
						time = getElseBody().evaluate(globals,counter);
					} catch (BreakException exc) {
						time = exc.getTime();
					}
					try {
						time = checkTime(time,this);
						globals.put("timer",new DoubleType(time));
						resetCounter();
					} catch (TerminateException exc) {
						globals.put("timer",new DoubleType());
						throw new BreakException(0);
					}
				}
			}
		}
		return time;
	}
	
}
