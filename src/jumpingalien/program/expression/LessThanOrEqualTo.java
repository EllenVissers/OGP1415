package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class LessThanOrEqualTo extends ComparisonExpression {

	public LessThanOrEqualTo(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public boolean evaluate() {
		double left = ((Constant<Double>)getLeftExpression()).getValue();
		double right = ((Constant<Double>)getRightExpression()).getValue();
		return (left<=right);
	}

}
