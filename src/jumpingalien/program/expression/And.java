package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class And extends BinaryExpression {

	public And(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public boolean evaluate() {
		boolean left = ((Constant<Boolean>)getLeftExpression()).getValue();
		boolean right = ((Constant<Boolean>)getRightExpression()).getValue();
		return left&&right;
	}

}
