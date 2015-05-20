package jumpingalien.program.expression;
import java.util.Map;
import jumpingalien.program.type.ObjectType;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.Type;
import jumpingalien.model.Tile;
import jumpingalien.model.World;

public class GetTile extends Expression {

	public GetTile(SourceLocation loc, Expression x, Expression y) {
		super(loc);
		this.x = x;
		this.y = y;
	}
	
	private Expression x;
	private Expression y;
	
	public Expression getLeftExpression() {
		return this.x;
	}
	
	public Expression getRightExpression() {
		return this.y;
	}
	
	@SuppressWarnings("unchecked")
	public Tile evaluate(Map<String,Type> globals) {
		double x = ((Constant<Double>)getLeftExpression()).evaluate(globals);
		double y = ((Constant<Double>)getRightExpression()).evaluate(globals);
		World world = ((ObjectType)globals.get("this")).getValue().getWorld();
		int feature = world.getFeatureAt((int) Math.round(x), (int) Math.round(y));
		return new Tile(x,y,world,feature);
	}

}
