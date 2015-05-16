package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.model.Slime;
import jumpingalien.part3.programs.SourceLocation;

public class IsSlime extends UnaryExpression {

	public IsSlime(SourceLocation loc, Expression object) {
		super(loc, object);
	}

	@Override
	public Boolean evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		return (obj instanceof Slime);
	}

}
