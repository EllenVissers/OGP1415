package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import java.util.Map;
import jumpingalien.program.type.Type;

public abstract class Expression {

	private SourceLocation location;
	
	public Expression(SourceLocation location) {
		this.location = location;
	}
	
	public SourceLocation getSourceLocation() {
		return this.location;
	}
	
	public abstract Object evaluate(Map<String,Type> globals);

}
