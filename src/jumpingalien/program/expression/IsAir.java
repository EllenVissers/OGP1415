package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.Tile;

public class IsAir extends UnaryExpression {

	public IsAir(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	@Override
	public Constant<Boolean> evaluate() throws IllegalArgumentException {
		Object obj = getExpression();
		if (! (obj instanceof Tile))
			throw new IllegalArgumentException();
		int x = ((Tile)obj).getXPosition();
		int y = ((Tile)obj).getYPosition();
		boolean result = (((Tile)obj).getWorld().getFeatureAt(x,y) == 0);
		return new Constant<Boolean>(getSourceLocation(),result);
	}

}
