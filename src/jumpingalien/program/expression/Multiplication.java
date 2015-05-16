package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Multiplication extends BinaryExpression {

	public Multiplication(SourceLocation loc, Expression left, Expression right) {
		super(loc,left,right);
	}
	
	public Double evaluate() {
		double left = ((Constant<Double>)getLeftExpression()).evaluate();
		double right = ((Constant<Double>)getRightExpression()).evaluate();
		return left*right;
	}

}
