package jumpingalien.program.type;

import java.util.Map;
import jumpingalien.program.expression.Expression;

public class DoubleType extends Type {

	public DoubleType(Double value) {
		this.value = value;
	}
	
	public DoubleType() {
		this.value = (double) 0;
	}
	
	private Double value;
	
	public Double getValue() {
		return this.value;
	}
	
	@Override
	public Type set(Expression value, Map<String, Type> globals) {
		this.value = (Double)value.evaluate(globals);
		return this;
	}

}
