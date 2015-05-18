package jumpingalien.program.expression;

import java.util.function.BiFunction;

import jumpingalien.part3.programs.SourceLocation;

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
	public O evaluate() {
		BiFunction<L,R,O> d = getOperator();
		return d.apply(((L)getLeftExpression().evaluate()),((R)getRightExpression().evaluate()));
	}

}
