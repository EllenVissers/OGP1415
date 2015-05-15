package jumpingalien.program.expression;

import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class SquareRoot extends UnaryExpression {
	
	public SquareRoot(SourceLocation loc, Expression value) {
		super(loc,value);
	}
	
	public double evaluate() {
		return Math.sqrt(((Constant<Double>)getExpression()).getValue());
	}

}
