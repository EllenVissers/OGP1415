package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;

@SuppressWarnings(value="all")
public class SearchObj extends Expression {

	public SearchObj(SourceLocation loc, Expression dir) {
		super(loc);
		this.direction = dir;
	}
	
	private Expression direction;
	
	public Expression getDirection() {
		return this.direction;
	}
	
	public Object evaluate() {
		Constant<Direction> dir = (Constant<Direction>)getDirection();
		Object obj = this;
		// TODO Auto-generated method stub
		return null;
	}

}
