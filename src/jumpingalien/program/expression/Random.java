package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings(value="all") 
public class Random extends UnaryExpression {

	public Random(SourceLocation loc, Expression max) {
		super(loc,max);
	}
	
	@Override
	public Constant<Double> evaluate() {
		double value = Math.random() * ((Constant<Double>)getExpression()).getValue();
		return new Constant<Double>(getSourceLocation(),value);
	}

}
