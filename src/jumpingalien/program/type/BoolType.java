package jumpingalien.program.type;

import java.util.Map;

import jumpingalien.program.expression.Expression;

public class BoolType extends Type {

	public BoolType(boolean bool) {
		this.bool = bool;
	}
	
	public BoolType() {
		this.bool = true;
	}
	
	private boolean bool;
	
	public boolean getBool() {
		return this.bool;
	}

	@Override
	public Type set(Expression bool, Map<String,Type> globals) {
		this.bool = (Boolean)bool.evaluate(globals);
		return this;
	}
	
}
