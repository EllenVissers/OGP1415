package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsPassable extends UnaryExpression {

	public IsPassable(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	@Override
	protected Constant<Boolean> getResult() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Object obj = getExpression();
		if (! (obj instanceof Tile))
			throw new IllegalArgumentException();
		int x = obj.getXPosition();
		int y = obj.getYPosition();
		Boolean result = ! (obj.getWorld().getFeatureAt(x,y) == 1);
		return new Constant<Boolean>(getSourceLocation(),result);
	}

}