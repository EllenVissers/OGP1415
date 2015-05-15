package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.Tile;

public class IsAir extends UnaryExpression {

	public IsAir(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	public boolean evaluate() throws IllegalArgumentException {
		Object obj = getExpression();
		if (! (obj instanceof Tile))
			throw new IllegalArgumentException();
		int x = ((Tile)obj).getXPosition();
		int y = ((Tile)obj).getYPosition();
		return (((Tile)obj).getWorld().getFeatureAt(x,y) == 0);
	}

}
