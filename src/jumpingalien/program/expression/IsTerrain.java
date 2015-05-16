package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.model.AllObjects;
import jumpingalien.model.Tile;

public class IsTerrain extends UnaryExpression {

	public IsTerrain(SourceLocation loc, Expression obj) {
		super(loc,obj);
	}
	
	//Wat wordt er bedoeld met terrain?
	public Boolean evaluate() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		AllObjects obj = (AllObjects) getExpression().evaluate();
		return (obj instanceof Tile);
	}

}