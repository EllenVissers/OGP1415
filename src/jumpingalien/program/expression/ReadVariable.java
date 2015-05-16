package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.program.Program;
import jumpingalien.program.type.Type;

public class ReadVariable extends Expression {

	public ReadVariable(SourceLocation loc, String variableName, Type variableType) {
		super(loc);
		this.name = variableName;
		this.type = variableType;
		Program.variableValues.put(variableName, null);
	}
	
	private String name;
	private Type type;
	
	public String getVariableName() {
		return this.name;
	}
	
	public Type getVariableType() {
		return this.type;
	}

	@Override
	public Object evaluate() {
		// TODO Auto-generated method stub
		int val = 0;
		// return getGlobalVariables().getValue(getVariableName()) -> key-value list
		return new Constant<>(getSourceLocation(),val);
	}
	
}
