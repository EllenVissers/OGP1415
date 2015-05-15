package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class LessThanOrEqualTo extends BinaryExpression {

	public LessThanOrEqualTo(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	public Constant<Boolean> evaluate() {
		double left = ((Constant<Double>)getLeftExpression()).getValue();
		double right = ((Constant<Double>)getRightExpression()).getValue();
		return new Constant<Boolean>(getSourceLocation(),left<=right);
	}

}
