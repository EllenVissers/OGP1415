package jumpingalien.program.expression;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.SourceLocation;

public class IsMagma extends UnaryExpression {

	public IsMagma(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	@Override
	public Constant<Boolean> evaluate() throws IllegalArgumentException {
		Object obj = getExpression();
		if (! (obj instanceof Tile))
			throw new IllegalArgumentException();
		int x = ((Tile)obj).getXPosition();
		int y = ((Tile)obj).getYPosition();
		boolean result = (((Tile)obj).getWorld().getFeatureAt(x,y) == 3);
		return new Constant<Boolean>(getSourceLocation(),result);
	}

}
