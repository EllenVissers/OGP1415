package jumpingalien.program.expression;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.SourceLocation;

public class IsMagma extends UnaryExpression {

	public IsMagma(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	public boolean evaluate() throws IllegalArgumentException {
		Object obj = getExpression();
		if (! (obj instanceof Tile))
			throw new IllegalArgumentException();
		int x = ((Tile)obj).getXPosition();
		int y = ((Tile)obj).getYPosition();
		return (((Tile)obj).getWorld().getFeatureAt(x,y) == 3);
	}

}
