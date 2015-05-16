package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.part3.programs.SourceLocation;

public class IsDead extends UnaryExpression {

	public IsDead(SourceLocation loc, Expression expr) {
		super(loc, expr);
	}

	@Override
	public Boolean evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		return obj.isTerminated();
	}

}
