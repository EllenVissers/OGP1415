package jumpingalien.program.expression;
import jumpingalien.model.AllObjects;
import jumpingalien.model.Plant;
import jumpingalien.part3.programs.SourceLocation;

public class IsPlant extends UnaryExpression {

	public IsPlant(SourceLocation loc, Expression object) {
		super(loc, object);
	}

	@Override
	public Boolean evaluate() throws NullPointerException {
		AllObjects obj = (AllObjects) getExpression().evaluate();
		if (obj == null)
			throw new NullPointerException();
		return (obj instanceof Plant);
	}

}
