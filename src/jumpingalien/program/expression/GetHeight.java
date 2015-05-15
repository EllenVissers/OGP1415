package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.Tile;
import jumpingalien.model.GameObject;

public class GetHeight extends UnaryExpression {

	public GetHeight(SourceLocation loc, Expression object) {
		super(loc,object);
	}
	
	
	// Class Tile moet nog worden aangemaakt
	@Override
	public Constant<Double> evaluate() throws NullPointerException {
		Object obj = getExpression();
		if (obj == null)
			throw new NullPointerException();
		if (obj instanceof Tile)
			return new Constant<Double>(getSourceLocation(),(double)((Tile)obj).getWorld().getTileSize());
		Double result = (double)((GameObject)obj).getCurrentSprite().getHeight();
		return new Constant<Double>(getSourceLocation(),result);
	}

}
