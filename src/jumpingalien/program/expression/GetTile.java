package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
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
	public Tile evaluate() {
		// TODO Auto-generated method stub
		// this?
		double x = ((Constant<Double>)getLeftExpression()).evaluate();
		double y = ((Constant<Double>)getRightExpression()).evaluate();
		World world = null; //this.getWorld()
		int feature = world.getFeatureAt((int) Math.round(x), (int) Math.round(y));
		return new Tile(x,y,world,feature);
	}

}
