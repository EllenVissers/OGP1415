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
	
	private String getVariableName() {
		return this.name;
	}
	
	private Type getVariableType() {
		return this.type;
	}
	
	protected Expression getExpression() {
		return this.value;
	}
	
	public void resetDone() {
		this.setDone(false);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		try {
			time = checkTime(time-0.001);
			this.setDone(true);
			globals.put("timer", new DoubleType(time));
			globals.put(getVariableName(), getVariableType().set(value,globals));
		} catch (TerminateException exc) {
			globals.put("timer", new DoubleType());
			throw new BreakException(0);
		}
		return time;
	}
	
}
