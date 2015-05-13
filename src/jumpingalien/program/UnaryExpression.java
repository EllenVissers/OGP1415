package jumpingalien.program;

import jumpingalien.part3.programs.SourceLocation;

public abstract class UnaryExpression extends Expression {
	
	public UnaryExpression(SourceLocation loc, Expression value) {
		super(loc);
		this.value = value;
	}
	
	private Expression value;
	
	protected Expression getExpression() {
		return this.value;
	}
	
	protected abstract Expression getResult();
}
