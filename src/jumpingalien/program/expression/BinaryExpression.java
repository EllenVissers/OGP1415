package jumpingalien.program.expression;
import java.util.Map;
import java.util.function.BiFunction;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.Type;

public class BinaryExpression<L,R,O> extends Expression {

	public BinaryExpression(SourceLocation loc, Expression left, Expression right, BiFunction<L,R,O> lambda) {
		super(loc);
		this.left = left;
		this.right = right;
		this.operator = lambda;
	}
	
	private Expression left;
	private Expression right;
	private BiFunction<L,R,O> operator;
	
	protected Expression getLeftExpression() {
		return this.left;
	}
	
	protected Expression getRightExpression() {
		return this.right;
	}
	
	private BiFunction<L,R,O> getOperator() {
		return this.operator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O evaluate(Map<String,Type> globals) {
		BiFunction<L,R,O> d = getOperator();
		L left;
		if (getLeftExpression().evaluate(globals) instanceof Type)
			left = (L) ((Type) getLeftExpression().evaluate(globals)).getValue();
		else
			left = (L) getLeftExpression().evaluate(globals);
		R right;
		if (getRightExpression().evaluate(globals) instanceof Type)
			right = (R) ((Type) getRightExpression().evaluate(globals)).getValue();
		else if (getRightExpression().evaluate(globals) instanceof IProgramFactory.Direction)
			right = (R) getRightExpression().evaluate(globals);
		else
			right = (R) getRightExpression().evaluate(globals);
		return d.apply(left,right);
	}
}
