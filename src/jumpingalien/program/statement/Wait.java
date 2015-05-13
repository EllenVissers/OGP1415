package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Wait extends Statement {

	public Wait(SourceLocation loc, Expression duration) {
		super(loc);
		this.duration = duration;
	}
	
	private Expression duration;
	
	public Expression getDuration() {
		return this.duration;
	}
	
	@Override
	public Statement getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
