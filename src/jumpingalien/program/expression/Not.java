package jumpingalien.program.expression;

import jumpingalien.program.expression.UnaryExpression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Not extends UnaryExpression {

	public Not(SourceLocation loc, Expression value) {
		super(loc, value);
	}
	
	public Boolean evaluate() {
		return (! ((Constant<Boolean>)getExpression()).evaluate());
	}

}
