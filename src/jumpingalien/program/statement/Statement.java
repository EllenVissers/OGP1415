package jumpingalien.program.statement;
import java.util.Map;
import jumpingalien.program.type.Type;
import jumpingalien.part3.programs.SourceLocation;

public abstract class Statement{

	public Statement(SourceLocation location) {
		this.sourceLocation = location;
		this.done = false;
	}
	
	private SourceLocation sourceLocation;
	private boolean done;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public boolean isDone() {
		return this.done;
	}
	
	protected void setDone(boolean bool) {
		this.done = bool;
	}
	
	public abstract void resetDone();
	
	public double checkTime(double time) throws TerminateException {
		if (time >= 0)
			return time;
		else
			throw new TerminateException();
	}
	
	public abstract double evaluate(Map<String,Type> globals) throws BreakException;
}
