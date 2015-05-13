package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;

public abstract class Statement {

	public Statement(SourceLocation location) {
		this.sourceLocation = location;
	}
	
	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public abstract Statement getResult();

}
