package jumpingalien.program.expression;

import java.util.function.Function;
import jumpingalien.part3.programs.SourceLocation;
import java.util.Map;
import jumpingalien.program.type.Type;

public class UnaryExpression<I,O> extends Expression {

	public UnaryExpression(SourceLocation loc, Expression expr, Function<I,O> lambda) {
		super(loc);
		this.expr = expr;
		this.operator = lambda;
	}
	
	private Expression expr;
	private Function<I,O> operator;
	
	protected Expression getExpression() {
		return this.expr;
	}
	
	private Function<I,O> getOperator() {
		return this.operator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O evaluate(Map<String,Type> globals) {
		Function<I,O> d = getOperator();
		return d.apply((I) getExpression().evaluate(globals));
	}

}