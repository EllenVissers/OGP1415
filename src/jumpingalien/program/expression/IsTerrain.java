package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsTerrain extends UnaryExpression {

	public IsTerrain(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	
	//Wat wordt er bedoeld met terrain?
	@Override
	protected Constant<Boolean> getResult() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Object obj = getExpression();
		Boolean result = (obj instanceof Tile);
		return new Constant<Boolean>(getSourceLocation(),result);
	}

}