package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

public abstract class BinaryExpression extends Expression {

	public BinaryExpression(SourceLocation loc, Expression left, Expression right) {
		super(loc);
		this.left = left;
		this.right = right;
	}
	
	private Expression left;
	private Expression right;
	
	protected Expression getLeftExpression() {
		return this.left;
	}
	
	protected Expression getRightExpression() {
		return this.right;
	}
	
	public abstract Expression getResult();
	
}
