package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Optional;
//import java.util.stream.Stream;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.model.AllObjects;

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
		ArrayList<Kind> all = ((AllObjects)kind.getClass()).getAll();
//		Stream<Kind> stream = all.stream();
//		if (getWhere() != null)
//			stream = stream.filter(t->t.getWhere() == true);
		
/*		// lijst met alle elementen waarover moet ge-itereerd worden!
		ArrayList<Kind> all = new ArrayList<Kind>();
		if (getWhere() != null) // pas lijst aan naar where!
			all = new ArrayList<Kind>();
		if (getSort() != null) // sorteer de lijst naar het object in de juiste richting
			all = new ArrayList<Kind>();
		//for (getVariableKind() getVariableName() : all) {
		for (getVariableKind() s : getVariableName())
			getBody().evaluate();
		}*/	
	}
}

//all.forEach(p -> getBody().evaluate()) ??

/*Stream treeStream = myTree.stream();
Stream filteredStream = 
		treeStream.filter(t -> ((Integer) t) % factor == 0);
Stream mappedStream = 
		filteredStream.map(t -> ((Integer)t)*((Integer)t));
Optional total = 
		mappedStream.reduce((t, u) -> ((Integer)t+(Integer)u));
*/
