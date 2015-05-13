package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.GameObject;

public class GetHitpoints extends UnaryExpression {

	public GetHitpoints(SourceLocation loc, Expression object) {
		super(loc,object);
	}
	
	
	// Class Tile moet nog worden aangemaakt
	@Override
	protected Constant<Double> getResult() throws NullPointerException {
		Object obj = getExpression();
		if (obj == null)
			throw new NullPointerException();
		if (obj instanceof Tile)
			return new Constant<Double>(getSourceLocation(),(double)0);
		Double result = (double)((GameObject)obj).getHitPoints();
		return new Constant<Double>(getSourceLocation(),result);
	}

}
