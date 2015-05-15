package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class SquareRoot extends UnaryExpression {
	
	public SquareRoot(SourceLocation loc, Expression value) {
		super(loc,value);
	}
	
	@Override
	public Constant<Double> evaluate() {
		double result = Math.sqrt(((Constant<Double>)getExpression()).getValue());
		return new Constant<Double>(getSourceLocation(),result);
	}

}
