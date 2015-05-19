package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class Expression {

	private SourceLocation location;
	
	public Expression(SourceLocation location) {
		this.location = location;
	}
	
	public SourceLocation getSourceLocation() {
		return this.location;
	}
	
	public abstract Object evaluate();

}
