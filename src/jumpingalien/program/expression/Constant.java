package jumpingalien.program.expression;
import java.util.Map;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.Type;

public class Constant<T> extends Expression {

	public Constant(SourceLocation loc, T val) {
		super(loc);
		this.value = val;
	}
	
	private T value;
	
	@Override
	public T evaluate(Map<String,Type> globals) {
		return this.value;
	}
	
}
