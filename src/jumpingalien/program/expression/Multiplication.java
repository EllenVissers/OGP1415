package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Multiplication extends BinaryExpression {

	public Multiplication(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	public Constant<Double> evaluate() {
		Double left = ((Constant<Double>)getLeftExpression()).getValue();
		Double right = ((Constant<Double>)getRightExpression()).getValue();
		return new Constant<Double>(getSourceLocation(),left*right);
	}

}
