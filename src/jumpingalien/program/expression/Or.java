package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Or extends BinaryExpression {

	public Or(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public Boolean evaluate() {
		boolean left = ((Constant<Boolean>)getLeftExpression()).evaluate();
		boolean right = ((Constant<Boolean>)getRightExpression()).evaluate();
		return (left||right);
	}

}
