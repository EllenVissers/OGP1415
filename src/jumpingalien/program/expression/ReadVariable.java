package jumpingalien.program.expression;
import java.util.Map;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.Type;

public class ReadVariable extends Expression {

	public ReadVariable(SourceLocation loc, String variableName, Type variableType) {
		super(loc);
		this.name = variableName;
		this.type = variableType;
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
	public Object evaluate(Map<String,Type> globals) {
		return globals.get(getVariableName());
	}
	
}
