package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.program.Program;

public abstract class Statement{

	public Statement(SourceLocation location) {
		this.sourceLocation = location;
	}
	
	private SourceLocation sourceLocation;
	private Program program;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public abstract void evaluate();
	
	public Program getProgram() {
		return program;
	}
}
