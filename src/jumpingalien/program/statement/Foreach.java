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
			if (getWhere() != null)
			{
				Type oldthis = globals.get("this");
				ArrayList<Type> wherelist = new ArrayList<Type>();
				for (Type o : list)
				{
					globals.put("this", o);
					if ((Boolean)(getWhere().evaluate(globals)))
						wherelist.add(o);
				}
				list = wherelist;
				globals.put("this",oldthis);
			}
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
				Map<Double,List<Type>> unsorted = new HashMap<Double,List<Type>>();
				for (int i = 0; i<values.size(); i++)
				{
					Double key = values.get(i);
					List<Type> l;
					if (unsorted.containsKey(key))
						l = unsorted.get(key);
					else
						l = new ArrayList<Type>();
					l.add(list.get(i));
					unsorted.put(key, l);
				}
				Map<Double,List<Type>> sorted;
				if (unsorted.size() > 1)
					sorted = new TreeMap<Double,List<Type>>(unsorted);
				else
					sorted = unsorted;
				List<List<Type>> sortedlist = new ArrayList<List<Type>>(sorted.values());
				list = new ArrayList<Type>();
				if (getSortDirection() == SortDirection.ASCENDING)
				{
					for (int i = 0; i<sorted.size(); i++)
						for (int j = 0; j<sortedlist.get(i).size(); j++)
							list.add(sortedlist.get(i).get(j));
				}
				else
				{
					for (int i = sorted.size()-1; i>=0; i--)
						for (int j = sortedlist.get(i).size()-1; j>=0; j--)
							list.add(sortedlist.get(i).get(j));
				}
				globals.put("this",oldthis);
			}
			Type old = globals.get(name);
			ArrayList<Type> newall = new ArrayList(list.subList(getForEachCounter(), list.size()));
			for (Type o : newall)
			{
				try {
					globals.put(name, o);
					getBody().setStatementCounter(counter);
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

