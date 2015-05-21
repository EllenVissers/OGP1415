package jumpingalien.program.statement;
import java.util.Map;
import jumpingalien.program.type.Type;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.program.Program;

public abstract class Statement{

	public Statement(SourceLocation location) {
		this.sourceLocation = location;
		this.SCounter = 0;
	}
	
	private SourceLocation sourceLocation;
	private Program program;
	private int SCounter;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public int getStatementCounter() {
		return this.SCounter;
	}
	
	public void setStatementCounter(int c) {
		this.SCounter = c;
	}
	
	public double checkTime(double time, Statement s) throws TerminateException {
		if (time > 0)
			return time;
		else
		{
			if (getStatementCounter() == 0)
				s.setStatementCounter(1);
			else
				s.setStatementCounter(0);
			throw new TerminateException();
		}
	}
	
	public void resetCounter() {
		if (getStatementCounter() == 0)
			setStatementCounter(1);
		else
			setStatementCounter(0);
	}
	
	public abstract double evaluate(Map<String,Type> globals, int counter) throws BreakException;
	
	public Program getProgram() {
		return program;
	}
}
