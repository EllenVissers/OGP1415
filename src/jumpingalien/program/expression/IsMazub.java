package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.model.Mazub;
import jumpingalien.part3.programs.SourceLocation;

public class IsMazub extends UnaryExpression {

	public IsMazub(SourceLocation loc, Expression object) {
		super(loc, object);
	}

	@Override
	public Boolean evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		return (obj instanceof Mazub);
	}

}
