package jumpingalien.program;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all")
public class LessThan extends BinaryExpression {

	public LessThan(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	protected Constant<Boolean> getResult() {
		Double left = ((Constant<Double>)getLeftExpression()).getValue();
		Double right = ((Constant<Double>)getRightExpression()).getValue();
		return new Constant<Boolean>(getSourceLocation(),left<right);
	}

}
