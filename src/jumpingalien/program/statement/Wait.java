package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Constant;

public class Wait extends Statement {

	public Wait(SourceLocation loc, Expression duration) {
		super(loc);
		this.duration = duration;
	}
	
	private Expression duration;
	
	public Expression getDuration() {
		return this.duration;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void evaluate() {
		int time = (int)(double)(((Constant<Double>) getDuration()).evaluate());
		//wait(time);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

}
