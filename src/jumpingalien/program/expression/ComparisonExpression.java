package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class ComparisonExpression extends BinaryExpression {

	public ComparisonExpression(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public abstract Boolean evaluate();
	
}
