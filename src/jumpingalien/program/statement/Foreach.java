package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.*;
import jumpingalien.model.AllObjects;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
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
		this.forEachCounter = 0;
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
	private int forEachCounter;
	
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
	
	public int getForEachCounter() {
		return this.forEachCounter;
	}
	
	public void setForEachCounter(int c) {
		this.forEachCounter = c;
	}
	
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
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
			ArrayList<Type> newall = new ArrayList(all.subList(getForEachCounter(), all.size()));
			for (Type o : newall)
			{
				try {
					globals.put(name, o);
					time = getBody().evaluate(globals,counter);
				} catch (BreakException exc) {
					time = exc.getTime();
				}
				try {
					
					time = checkTime(time,getBody());
					globals.put("timer", new DoubleType(time));
					resetCounter();
					setForEachCounter(0);
				} catch (TerminateException exc) {
					setForEachCounter(all.indexOf(o));
					globals.put("timer",new DoubleType());
					throw new BreakException(0);
				}
				globals.put(name,old);
			}
		}
		return time;
	}
}

