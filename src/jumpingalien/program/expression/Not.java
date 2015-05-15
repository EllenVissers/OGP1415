package jumpingalien.program.expression;

import jumpingalien.program.expression.UnaryExpression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Not extends UnaryExpression {

	public Not(SourceLocation loc, Expression value) {
		super(loc, value);
	}
	
	@Override
	public Constant<Boolean> evaluate() {
		boolean val = ((Constant<Boolean>)getExpression()).getValue();
		return new Constant<Boolean>(getSourceLocation(),! val);
	}

}
