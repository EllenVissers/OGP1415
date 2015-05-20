package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.Type;
import java.util.Map;

public class Break extends Statement {

	public Break(SourceLocation loc) {
		super(loc);
	}
	
	@Override
	public void evaluate(Map<String,Type> globals, double time) throws BreakException {
		throw new BreakException();
	}

}
