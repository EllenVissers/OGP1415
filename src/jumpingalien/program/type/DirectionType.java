package jumpingalien.program.type;

import java.util.Map;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.expression.Expression;

public class DirectionType extends Type {

	public DirectionType(Direction dir) {
		this.dir = dir;
	}
	
	public DirectionType() {
		this.dir = Direction.RIGHT;
	}
	
	private Direction dir;
	
	public Direction getValue() {
		return this.dir;
	}
	
	@Override
	public Type set(Expression dir, Map<String, Type> globals) {
		this.dir = (Direction)dir.evaluate(globals);
		return this;
	}

}
