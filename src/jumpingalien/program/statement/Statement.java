package jumpingalien.program.statement;
import java.util.Map;
import jumpingalien.program.type.Type;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.program.Program;

public abstract class Statement{

	public Statement(SourceLocation location) {
		this.sourceLocation = location;
		this.SCounter = 0;
		this.done = false;
	}
	
	private SourceLocation sourceLocation;
	private Program program;
	private int SCounter;
	private boolean done;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	protected int getStatementCounter() {
		return this.SCounter;
	}
	
	protected void setStatementCounter(int c) {
		this.SCounter = c;
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
	
	protected void resetCounter(Statement s) {
		if (s.getStatementCounter() == 0)
			s.setStatementCounter(1);
		else
			s.setStatementCounter(0);
	}
	
	public abstract double evaluate(Map<String,Type> globals) throws BreakException;
	
	protected Program getProgram() {
		return program;
	}
}
