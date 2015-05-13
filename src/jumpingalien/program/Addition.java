package jumpingalien.program;
import jumpingalien.part3.programs.SourceLocation;

import java.lang.Double;

@SuppressWarnings(value="all") 
public class Addition extends BinaryExpression {

	public Addition(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	protected Constant<Double> getResult() {
		Double left = ((Constant<Double>)getLeftExpression()).getValue();
		Double right = ((Constant<Double>)getRightExpression()).getValue();
		return new Constant<Double>(getSourceLocation(),left+right);
	}

}
