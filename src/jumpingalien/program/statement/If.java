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
		this.bodyToDo = null;
	}
	
	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	//private boolean result;
	private Statement bodyToDo;
	
//	protected boolean getResult() {
//		return result;
//	}
//
//	protected void setResult(boolean result) {
//		this.result = result;
//	}
	
	private Expression getCondition() {
		return this.condition;
	}
	
	public Statement getIfBody() {
		return this.ifBody;
	}
	
	public Statement getElseBody() {
		return this.elseBody;
	}
	
	private Statement getBodyToDo() {
		return this.bodyToDo;
	}
	
	private void setBodyToDo(Statement body) {
		this.bodyToDo = body;
	}
	
	public void resetDone() {
		this.setDone(false);
		getIfBody().resetDone();
		if (getElseBody() != null)
			getElseBody().resetDone();
	}
	
	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (getBodyToDo() != null)
		{
			try {
				time = getBodyToDo().evaluate(globals);
			} catch (BreakException exc) {
				time = exc.getTime();
				if (time == 0)
					throw new BreakException(0);
			}
			try {
				time = checkTime(time);
				setBodyToDo(null);
				this.setDone(true);
				globals.put("timer",new DoubleType(time));
			} catch (TerminateException exc) {
				globals.put("timer",new DoubleType());
				throw new BreakException(0);
			}
		}
		else {
			Boolean cond;
			if (getCondition().evaluate(globals) instanceof Type)
				cond = ((BoolType) getCondition().evaluate(globals)).getValue();
			else
				cond = (Boolean) getCondition().evaluate(globals);
			try {
				time = checkTime(time-0.001);
			} catch (TerminateException exc) {
				throw new BreakException(0);
			}
			Statement body = null;
			if (cond)
				body = getIfBody();
			else if (getElseBody() != null)
				body = getElseBody();
			if (body != null)
			{
				try {
					time = body.evaluate(globals);
				} catch (BreakException exc) {
					time = exc.getTime();
					if (time == 0)
						throw new BreakException(0);
				}
				try {
					time = checkTime(time);
					this.setDone(true);
					globals.put("timer",new DoubleType(time));
				} catch (TerminateException exc) {
					globals.put("timer",new DoubleType());
					throw new BreakException(0);
				}
			}
			else
				this.setDone(true);
		}
		return time;
	}
	
}
