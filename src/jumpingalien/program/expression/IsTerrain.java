package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.Tile;

public class IsTerrain extends UnaryExpression {

	public IsTerrain(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	//Wat wordt er bedoeld met terrain?
	public boolean evaluate() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Object obj = getExpression();
		return (obj instanceof Tile);
	}

}