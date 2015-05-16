package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;

public class IsMoving extends UnaryExpression {

	public IsMoving(SourceLocation loc, Expression object, Expression direction) {
		super(loc, object);
		this.direction = direction;
	}

	private Expression direction;
	
	public Expression getDirection() {
		return this.direction;
	}
	
	@Override
	public Boolean evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		if (getDirection().evaluate() == Direction.RIGHT)
			return (obj.getXVelocity() > 0);
		else
			return (obj.getXVelocity() < 0);
	}

}
