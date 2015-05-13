package jumpingalien.program.expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;

@SuppressWarnings(value="all")
public class SearchObj extends UnaryExpression {

	public SearchObj(SourceLocation loc, Expression dir) {
		super(loc,dir);
	}
	
	@Override
	protected Expression getResult() {
		Constant<Direction> dir = (Constant<Direction>)getExpression();
		Object obj = this;
		// TODO Auto-generated method stub
		return null;
	}

}
