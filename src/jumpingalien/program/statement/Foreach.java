package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import java.util.ArrayList;

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
	
	// LAMBDA UITDRUKKINGEN!!!
	
	public void evaluate() {
		// lijst met alle elementen waarover moet ge-itereerd worden!
		ArrayList<jumpingalien.part3.programs.IProgramFactory.Kind> all = new ArrayList<jumpingalien.part3.programs.IProgramFactory.Kind>();
		if (getWhere() != null) // pas lijst aan naar where!
			all = new ArrayList<jumpingalien.part3.programs.IProgramFactory.Kind>();
		if (getSort() != null) // sorteer de lijst naar het object in de juiste richting
			all = new ArrayList<jumpingalien.part3.programs.IProgramFactory.Kind>();
		for (getVariableKind() getVariableName() : all) {
			getBody().evaluate();
		}	
	}

}
