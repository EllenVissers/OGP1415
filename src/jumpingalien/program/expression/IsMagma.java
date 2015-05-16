package jumpingalien.program.expression;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.SourceLocation;

public class IsMagma extends UnaryExpression {

	public IsMagma(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	public Boolean evaluate() throws IllegalArgumentException {
		Object obj = getExpression();
		if (! (obj instanceof Tile))
			throw new IllegalArgumentException();
		int x = (int) Math.round(((Tile)obj).getXPosition());
		int y = (int) Math.round(((Tile)obj).getYPosition());
		return (((Tile)obj).getWorld().getFeatureAt(x,y) == 3);
	}

}
