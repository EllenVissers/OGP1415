package jumpingalien.program.expression;
import java.util.Map;
import jumpingalien.model.GameObject;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.type.Type;

public class Self extends Expression {

	public Self(SourceLocation loc) {
		super(loc);
	}
	
	@Override
	public GameObject evaluate(Map<String, Type> globals) {
		return (GameObject) globals.get("this").getValue();
	}

}
