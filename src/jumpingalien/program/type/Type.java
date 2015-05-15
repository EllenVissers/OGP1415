package jumpingalien.program.type;

public enum Type {
	Double, Bool, GameObject , Direction;
	
	public Class<?> getType() {
		if (this == Type.Bool)
			return java.lang.Boolean.class;
		else if (this == Type.Double)
			return java.lang.Double.class;
		else if (this == Type.GameObject)
			return jumpingalien.model.GameObject.class;
		else
			return jumpingalien.part3.programs.IProgramFactory.Direction.class;
	}
}
