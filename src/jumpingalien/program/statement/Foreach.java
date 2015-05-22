package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.*;
import jumpingalien.model.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;
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
	
	private String getVariableName() {
		return this.name;
	}
	
	private Kind getVariableKind() {
		return this.kind;
	}
	
	private Expression getWhere() {
		return this.where;
	}
	
	private Expression getSort() {
		return this.sort;
	}
	
	private SortDirection getSortDirection() {
		return this.direction;
	}
	
	private Statement getBody() {
		return this.body;
	}
	
	private int getForEachCounter() {
		return this.forEachCounter;
	}
	
	private void setForEachCounter(int c) {
		this.forEachCounter = c;
	}
	
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		if (getBody() instanceof Break)
			this.getProgram().setWellFormed(false);
		if (getBody() instanceof Sequence){
			List<Statement> statements = ((Sequence) getBody()).getStatements();
			for (Statement s : statements){
				if (s instanceof Break)
					this.getProgram().setWellFormed(false);
			}
		}
		if (getBody() instanceof ActionStatement)
			this.getProgram().setWellFormed(false);
		if (getBody() instanceof Sequence){
			List<Statement> statements = ((Sequence) getBody()).getStatements();
			for (Statement s : statements){
				if (s instanceof ActionStatement)
					this.getProgram().setWellFormed(false);
			}
		}
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			String name = getVariableName();
			Kind kind = getVariableKind();
			ArrayList<Type> all = new ArrayList<Type>(globals.values());
			ArrayList<Type> list = new ArrayList<Type>();
			for (Type t : all)
			{
				if ((t instanceof ObjectType) && ((AllObjects)t.getValue()).isKind(kind))
					list.add(t);
			}
			System.out.println(list.size());
			if (getWhere() != null)
			{
				Type oldthis = globals.get("this");
				for (Type o : list)
					System.out.println(o.getValue());
				for (Type o : list)
				{
					System.out.println(o.getValue());
					globals.put("this", o);
					System.out.println(! (Boolean)(getWhere().evaluate(globals)));
//					if (! (Boolean)(getWhere().evaluate(globals)))
//					{
//						System.out.println("hier");
//						list.remove(o);
//					}
						
				}
				globals.put("this",oldthis);
			}
			System.out.println("hier");
			if (getSort() != null)
			{
				Type oldthis = globals.get("this");
				ArrayList<Double> values = new ArrayList<Double>();
				for (Type o : list)
				{
					globals.put("this", o);
					double val = (double) getSort().evaluate(globals);
					values.add(val);
				}
				Map<Double,Type> unsorted = new HashMap<Double,Type>();
				for (int i = 0; i<values.size(); i++)
					unsorted.put(values.get(i), list.get(i));
				Map<Double,Type> sorted = new TreeMap<Double,Type>(unsorted);
				list = new ArrayList<Type>(sorted.values());
				globals.put("this",oldthis);
			}
			System.out.println(list.size());
			Type old = globals.get(name);
			ArrayList<Type> newall = new ArrayList(list.subList(getForEachCounter(), list.size()));
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
					globals.put(name,old);
					throw new BreakException(0);
				}
			}
			globals.put(name,old);
		}
		return time;
	}
}

