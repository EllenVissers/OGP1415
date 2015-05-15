package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Division extends BinaryExpression {

	public Division(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	public Constant<Double> evaluate() throws ArithmeticException {
		double left = ((Constant<Double>)getLeftExpression()).getValue();
		double right = ((Constant<Double>)getRightExpression()).getValue();
		if (right == 0)
			throw new ArithmeticException();
		return new Constant<Double>(getSourceLocation(),left/right);
	}

}
