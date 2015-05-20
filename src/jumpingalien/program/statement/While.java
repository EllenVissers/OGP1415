package jumpingalien.program.statement;
import java.util.Map;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.Type;

public class While extends Statement {

	public While(SourceLocation loc, Expression condition, Statement body) {
		super(loc);
		this.condition = condition;
		this.body = body;
	}
	
	private Expression condition;
	private Statement body;
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	public void evaluate(Map<String,Type> globals, double time) {
		try {
			while ((Boolean)(getCondition().evaluate(globals)))
			{
				getBody().evaluate(globals,time);
			}
		} catch(BreakException exc) {
		}
		
			
	}
}
