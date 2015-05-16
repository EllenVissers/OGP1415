package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.Tile;
import jumpingalien.model.GameObject;

public class GetHeight extends UnaryExpression {

	public GetHeight(SourceLocation loc, Expression object) {
		super(loc,object);
	}
	
	public Integer evaluate() throws NullPointerException {
		Object obj = getExpression();
		if (obj == null)
			throw new NullPointerException();
		if (obj instanceof Tile)
			return ((Tile)obj).getWorld().getTileSize();
		return ((GameObject)obj).getCurrentSprite().getHeight();
	}

}
