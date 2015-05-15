package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.program.Program;
import jumpingalien.program.type.Type;
import jumpingalien.program.expression.Constant;

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
	
	public void evaluate() {
		//if (((Constant) getExpression()).getValue() == getVariableType().getType())        fout!
		//getVariableType() getVariableName() = ((Constant)getValue()).getValue();
		Program.globalVariableValues.put(getVariableName(),(Constant<?>) getExpression());
	}
	
}
