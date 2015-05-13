package jumpingalien.program.type;
import jumpingalien.part3.programs.SourceLocation;

public class Type {

	public Type(SourceLocation loc) {
		this.sourceLocation = loc;
	}
	
	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}

}
