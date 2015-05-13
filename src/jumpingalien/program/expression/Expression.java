package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public class Expression {

	private SourceLocation location;
	
	public Expression(SourceLocation location) {
		this.location = location;
	}
	
	protected SourceLocation getSourceLocation() {
		return this.location;
	}
}
