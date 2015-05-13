package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;

public class Foreach extends Statement {
	
	public Foreach(SourceLocation loc, String variableName, jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where, Expression sort, jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body) {
		super(loc);
		this.name = variableName;
		this.kind = variableKind;
		this.where = where;
		this.sort = sort;
		this.direction = sortDirection;
		this.body = body;
	}
	
	private String name;
	private jumpingalien.part3.programs.IProgramFactory.Kind kind;
	private Expression where;
	private Expression sort;
	private jumpingalien.part3.programs.IProgramFactory.SortDirection direction;
	private Statement body;
	
	public String getVariableName() {
		return this.name;
	}
	
	public jumpingalien.part3.programs.IProgramFactory.Kind getVariableKind() {
		return this.kind;
	}
	
	public Expression getWhere() {
		return this.where;
	}
	
	public Expression getSort() {
		return this.sort;
	}
	
	public jumpingalien.part3.programs.IProgramFactory.SortDirection getSortDirection() {
		return this.direction;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	public Statement getResult() {
		// TODO Auto-generated method stub
		return null;
	}

}
