package jumpingalien.program;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Substraction extends BinaryExpression {

	public Substraction(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	protected Constant<Double> getResult() {
		Double left = ((Constant<Double>)getLeftExpression()).getValue();
		Double right = ((Constant<Double>)getRightExpression()).getValue();
		return new Constant<Double>(getSourceLocation(),left-right);
	}

}
