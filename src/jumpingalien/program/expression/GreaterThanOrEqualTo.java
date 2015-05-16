package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

public class GreaterThanOrEqualTo extends ComparisonExpression {

	public GreaterThanOrEqualTo(SourceLocation loc, Expression left, Expression right) {
		super(loc, left, right);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean evaluate() {
		double left = ((Constant<Double>)getLeftExpression()).evaluate();
		double right = ((Constant<Double>)getRightExpression()).evaluate();
		return (left>=right);
	}

}
