package jumpingalien.program.expression;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.SourceLocation;

public class IsWater extends UnaryExpression {

	public IsWater(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	@Override
	public Constant<Boolean> evaluate() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Object obj = getExpression();
		if (! (obj instanceof Tile))
			throw new IllegalArgumentException();
		int x = ((Tile)obj).getXPosition();
		int y = ((Tile)obj).getYPosition();
		Boolean result = (((Tile)obj).getWorld().getFeatureAt(x,y) == 2);
		return new Constant<Boolean>(getSourceLocation(),result);
	}

}
