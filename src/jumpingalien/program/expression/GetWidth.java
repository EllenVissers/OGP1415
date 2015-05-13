package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.GameObject;
import jumpingalien.model.Tile;

public class GetWidth extends UnaryExpression {

	public GetWidth(SourceLocation loc, Expression object) {
		super(loc,object);
	}
	
	
	// Class Tile moet nog worden aangemaakt
	@Override
	protected Constant<Double> getResult() throws NullPointerException {
		Object obj = getExpression();
		if (obj == null)
			throw new NullPointerException();
		if (obj instanceof Tile)
			return new Constant<Double>(getSourceLocation(),(double)((Tile)obj).getWorld().getTileSize());
		Double result = (double)((GameObject)obj).getCurrentSprite().getWidth();
		return new Constant<Double>(getSourceLocation(),result);
	}

}
