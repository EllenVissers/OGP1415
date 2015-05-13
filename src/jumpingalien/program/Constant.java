package jumpingalien.program;
import jumpingalien.part3.programs.SourceLocation;

public class Constant<T> extends Expression {

	public Constant(SourceLocation loc, T val) {
		super(loc);
		this.value = val;
	}
	
	private T value;
	
	public T getValue() {
		return this.value;
	}
	
}
