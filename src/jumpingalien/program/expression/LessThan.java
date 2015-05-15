package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all")
public class LessThan extends BinaryExpression {

	public LessThan(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public boolean evaluate() {
		double left = ((Constant<Double>)getLeftExpression()).getValue();
		double right = ((Constant<Double>)getRightExpression()).getValue();
		return left<right;
	}

}
