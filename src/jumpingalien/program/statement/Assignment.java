package jumpingalien.program.statement;
import java.util.Map;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.DoubleType;
import jumpingalien.program.type.Type;

public class Assignment extends Statement {

	public Assignment(SourceLocation loc, String variableName, Type variableType, Expression value) {
		super(loc);
		this.name = variableName;
		this.type = variableType;
		this.value = value;
	}
	
	private String name;
	private Type type;
	private Expression value;
	
	public String getVariableName() {
		return this.name;
	}
	
	public Type getVariableType() {
		return this.type;
	}
	
	public Expression getExpression() {
		return this.value;
	}
	
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			try {
				time = checkTime(time-0.001,this);
				resetCounter();
				globals.put(getVariableName(),getVariableType().set(value,globals));
				globals.put("timer", new DoubleType(time));
			} catch (TerminateException exc) {
				globals.put("timer", new DoubleType());
				throw new BreakException(0);
			}
		}
		return time;
	}
	
}
