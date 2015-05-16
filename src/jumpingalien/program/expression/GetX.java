package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.part3.programs.SourceLocation;

public class GetX extends UnaryExpression {

	public GetX(SourceLocation loc, Expression expr) {
		super(loc, expr);
	}

	@Override
	public Double evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		return (obj.getXPosition());
	}

}
