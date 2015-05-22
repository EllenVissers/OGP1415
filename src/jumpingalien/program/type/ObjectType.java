package jumpingalien.program.type;
import java.util.Map;

import jumpingalien.model.AllObjects;
import jumpingalien.program.expression.Expression;

public class ObjectType extends Type {

	public ObjectType(AllObjects obj) {
		this.obj = obj;
	}
	
	public ObjectType() {
		this.obj = null;
	}
	
	private AllObjects obj;
	
	@Override
	public AllObjects getValue() {
		return this.obj;
	}
	
	@Override
	public Type set(Expression obj, Map<String, Type> globals) {
		this.obj = (AllObjects)obj.evaluate(globals);
		return this;
	}

}
