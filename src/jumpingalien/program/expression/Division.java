package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Division extends BinaryExpression {

	public Division(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public Double evaluate() throws ArithmeticException {
		double left = ((Constant<Double>)getLeftExpression()).evaluate();
		double right = ((Constant<Double>)getRightExpression()).evaluate();
		if (right == 0)
			throw new ArithmeticException();
		return left/right;
	}

}
