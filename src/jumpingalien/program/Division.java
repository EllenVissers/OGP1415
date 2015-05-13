package jumpingalien.program;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Division extends BinaryExpression {

	public Division(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	protected Constant<Double> getResult() throws ArithmeticException {
		Double left = ((Constant<Double>)getLeftExpression()).getValue();
		Double right = ((Constant<Double>)getRightExpression()).getValue();
		if (right == 0)
			throw new ArithmeticException();
		return new Constant<Double>(getSourceLocation(),left/right);
	}

}
