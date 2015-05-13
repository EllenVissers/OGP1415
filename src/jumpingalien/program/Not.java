package jumpingalien.program;

import jumpingalien.program.UnaryExpression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Not extends UnaryExpression {

	public Not(SourceLocation loc, Expression value) {
		super(loc, value);
	}
	
	@Override
	protected Constant<Boolean> getResult() {
		Boolean val = ((Constant<Boolean>)getExpression()).getValue();
		return new Constant<Boolean>(getSourceLocation(),! val);
	}

}
