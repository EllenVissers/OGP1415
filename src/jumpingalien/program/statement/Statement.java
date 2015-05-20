package jumpingalien.program.statement;
import java.util.Map;
import jumpingalien.program.type.Type;
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
	
	public abstract void evaluate(Map<String,Type> globals, double time) throws BreakException;
	
	public Program getProgram() {
		return program;
	}
}
