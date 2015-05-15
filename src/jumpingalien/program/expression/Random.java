package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Random extends UnaryExpression {

	public Random(SourceLocation loc, Expression max) {
		super(loc,max);
	}
	
	public double evaluate() {
		return (Math.random() * ((Constant<Double>)getExpression()).getValue());
	}

}
