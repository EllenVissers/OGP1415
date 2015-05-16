package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all")
public class LessThan extends ComparisonExpression {

	public LessThan(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public Boolean evaluate() {
		double left = ((Constant<Double>)getLeftExpression()).evaluate();
		double right = ((Constant<Double>)getRightExpression()).evaluate();
		return left<right;
	}

}
