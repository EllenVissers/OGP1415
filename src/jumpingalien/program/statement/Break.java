package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.program.Program;

public class Break extends Statement {

	public Break(SourceLocation loc) {
		super(loc);
	}
	
	@Override
	public void evaluate() throws BreakException {
		throw new BreakException();
	}

}
