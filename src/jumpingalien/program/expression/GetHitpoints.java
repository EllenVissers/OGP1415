package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.Tile;
import jumpingalien.model.GameObject;

public class GetHitpoints extends UnaryExpression {

	public GetHitpoints(SourceLocation loc, Expression object) {
		super(loc,object);
	}
	
	public int evaluate() throws NullPointerException {
		Object obj = getExpression();
		if (obj == null)
			throw new NullPointerException();
		if (obj instanceof Tile)
			return 0; // of throw exception?
		return ((GameObject)obj).getHitPoints();
	}

}
