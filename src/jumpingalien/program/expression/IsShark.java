package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;

public class IsShark extends UnaryExpression {

	public IsShark(SourceLocation loc, Expression object) {
		super(loc, object);
	}

	@Override
	public Boolean evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		return (obj instanceof Shark);
	}

}
