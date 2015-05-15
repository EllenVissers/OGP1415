package jumpingalien.program.statement;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.program.Program;

public class StartJump extends Statement {

	public StartJump(SourceLocation loc) {
		super(loc);
	}
	
	@Override
	public void evaluate() {
		((GameObject) Program.self).startJump();
	}

}
