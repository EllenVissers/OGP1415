package jumpingalien.program.expression;
import jumpingalien.model.Alien;
import jumpingalien.model.AllObjects;
import jumpingalien.part3.programs.SourceLocation;

public class IsDucking extends UnaryExpression {

	public IsDucking(SourceLocation loc, Expression object) {
		super(loc, object);
	}

	@Override
	public Boolean evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		if (! (obj instanceof Alien))
			return false;
		else
			return ((Alien)obj).isDucking();
	}

}
