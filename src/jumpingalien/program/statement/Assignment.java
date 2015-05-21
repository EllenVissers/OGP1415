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
	
	public double evaluate(Map<String,Type> globals, double time, int counter) {
		if (counter == getStatementCounter())
		{
			globals.put(getVariableName(),getVariableType().set(value,globals));
			double timer = (double) globals.get("timer").getValue();
			globals.put("timer", new DoubleType(timer-0.001));
			resetCounter();
			return (time-0.001);
		}
		return time;
	}
	
}
