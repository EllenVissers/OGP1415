package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;

public class Break extends Statement {

	public Break(SourceLocation loc) {
		super(loc);
	}
	
	@Override
	public void evaluate() /*throws BreakException*/ {
		/*throw new BreakException();*/
		break;
	}

}
