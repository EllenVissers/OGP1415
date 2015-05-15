package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

import java.lang.Double;

@SuppressWarnings(value="all") 
public class Addition extends BinaryExpression {

	public Addition(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	public Constant<Double> evaluate() {
		double left = ((Constant<Double>)getLeftExpression()).getValue();
		double right = ((Constant<Double>)getRightExpression()).getValue();
		return new Constant<Double>(getSourceLocation(),left+right);
	}

}
