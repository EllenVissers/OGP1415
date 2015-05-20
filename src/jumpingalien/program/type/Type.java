package jumpingalien.program.type;
import java.util.Map;
import jumpingalien.program.expression.Expression;

public abstract class Type {
	
	public abstract Type set(Expression value, Map<String,Type> globals);
	public abstract Object getValue();

}
