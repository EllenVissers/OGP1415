package jumpingalien.program;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class And extends BinaryExpression {

	public And(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	@Override
	protected Constant<Boolean> getResult() {
		Boolean left = ((Constant<Boolean>)getLeftExpression()).getValue();
		Boolean right = ((Constant<Boolean>)getRightExpression()).getValue();
		return new Constant<Boolean>(getSourceLocation(),left&&right);
	}

}
