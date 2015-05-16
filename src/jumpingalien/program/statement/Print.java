package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Print extends Statement {

	public Print(SourceLocation loc, Expression expr) {
		super(loc);
		this.expr = expr;
	}
	
	private Expression expr;
	
	public Expression getExpression() {
		return this.expr;
	}

	@Override
	public void evaluate() {
		//System.out.println(getExpression().evaluate());
		System.out.println(getExpression().toString());
	}

}
