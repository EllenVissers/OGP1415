package jumpingalien.program.statement;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.program.Program;

public class StopDuck extends Statement {

	public StopDuck(SourceLocation loc) {
		super(loc);
	}
	
	@Override
	public void evaluate() {
		((GameObject) Program.self).endDuck();
	}

}
