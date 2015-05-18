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
	public void evaluate() {
		double time = (double) (getDuration().evaluate());
		//wait(time);
		long dt = (long) Math.round(time);
		try {
			Thread.sleep(dt);
		} catch (InterruptedException e) {
		}
	}

}
