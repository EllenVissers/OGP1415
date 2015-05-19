package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

/**
 * A statement that executes the given body 
 * with the given variable set to all objects of the given kind 
 * for which the where-expression evaluates to true, 
 * sorted by the result of the given sort expression in the given direction. 
 * The where- and sort-expressions are optional, and can be null;
 * */
public class Foreach extends Statement {
	
	public Foreach(SourceLocation loc, String variableName, Kind variableKind,
			Expression where, Expression sort, SortDirection sortDirection,
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
	private Kind kind;
	private Expression where;
	private Expression sort;
	private SortDirection direction;
	private Statement body;
	
	public String getVariableName() {
		return this.name;
	}
	
	public Kind getVariableKind() {
		return this.kind;
	}
	
	public Expression getWhere() {
		return this.where;
	}
	
	public Expression getSort() {
		return this.sort;
	}
	
	public SortDirection getSortDirection() {
		return this.direction;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	// LAMBDA UITDRUKKINGEN!!!
	
	public void evaluate() {
		String name = getVariableName();
		Kind kind = getVariableKind();
		ArrayList<?> all = getProgram().getGameObject().getWorld().getAll(kind);
		if (getWhere() != null)
		{
			Stream<?> whereStream = all.stream().filter(s->(Boolean)getWhere().evaluate());
			all = whereStream.collect(Collectors.toCollection(ArrayList::new));
		}
		if (getSort() != null)
		{
			Stream<?> sortStream = all.stream().sorted();
			all = sortStream.collect(Collectors.toCollection(ArrayList::new));
		}
		// ascending!
	}
}

//all.forEach(p -> getBody().evaluate()) ??

