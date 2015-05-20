package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.*;
import jumpingalien.model.AllObjects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.TreeMap;

import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

/**
 * A statement that executes the given body 
 * with the given variable set to all objects of the given kind 
 * for which the where-expression evaluates to true, 
 * sorted by the result of the given sort expression in the given direction. 
 * The where- and sort-expressions are optional, and can be null;
 * */
@SuppressWarnings("all")
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
	
	//TODO werken met lambda uitdrukkingen! (all.forEach(p -> getBody().evaluate()))
	public void evaluate(Map<String,Type> globals, double time) {
		String name = getVariableName();
		Kind kind = getVariableKind();
		ArrayList<Type> all = new ArrayList<Type>(globals.values());
		Stream allStream = all.stream();
		Stream filteredStream = allStream.filter(t->((AllObjects)((Type)t).getValue()).isKind(kind));
		if (getWhere() != null)
		{
			Type oldthis = globals.get("this");
			for (Type o : all)
			{
				globals.put("this", o);
				if (! (Boolean)(getWhere().evaluate(globals)))
					all.remove(o);
			}
			globals.put("this",oldthis);
		}
		if (getSort() != null)
		{
			Type oldthis = globals.get("this");
			ArrayList<Double> values = new ArrayList<Double>();
			for (Type o : all)
			{
				globals.put("this", o);
				double val = (double) getSort().evaluate(globals);
				values.add(val);
				Map<Double,Type> unsorted = new HashMap<Double,Type>();
				for (int i = 0; i<values.size(); i++)
				{
					unsorted.put(values.get(i), all.get(i));
				}
				Map<Double,Type> sorted = new TreeMap<Double,Type>(unsorted);
				all = new ArrayList<Type>(sorted.values());
			}
			globals.put("this",oldthis);
		}
		Type old = globals.get(name);
		for (Type o : all)
		{
			try {
				globals.put(name, o);
				getBody().evaluate(globals,time);
			} catch (BreakException exc) {
			}
		}
		globals.put(name,old);
	}
}

