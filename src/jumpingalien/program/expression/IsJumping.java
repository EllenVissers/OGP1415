package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.part3.programs.SourceLocation;

public class IsJumping extends UnaryExpression {

	public IsJumping(SourceLocation loc, Expression object) {
		super(loc, object);
	}

	@Override
	public Boolean evaluate() throws NullPointerException{
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		return (((AllObjects)obj).getYVelocity() != 0);
	}

}
